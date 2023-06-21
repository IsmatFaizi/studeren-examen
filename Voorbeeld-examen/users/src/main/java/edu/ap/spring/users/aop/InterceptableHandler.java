package edu.ap.spring.users.aop;

import edu.ap.spring.users.redis.RedisService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Set;


@Aspect
@Component
public class InterceptableHandler {
    @Autowired
    private RedisService service;

    @Around("@annotation(edu.ap.spring.users.aop.Interceptable) && execution(public String login(..))")
    public String controlBalance(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        String email = (String) args[0];
        String password = (String) args[1];
        String key = "users:" + bytesToHex(email + password) + ":*";
        Set<String> keys = service.keys(key);

        if (keys.size() == 1) {
            String foundKey = keys.iterator().next();
            String[] splitKey = foundKey.split(":");
            service.setBit("login", Integer.valueOf(splitKey[2]), true);
        }

        return (String) joinPoint.proceed();
    }


    private String bytesToHex(String str) {
        String retString = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest((str).getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < encodedhash.length; i++) {
                String hex = Integer.toHexString(0xff & encodedhash[i]);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            retString = hexString.toString();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return retString;
    }
}

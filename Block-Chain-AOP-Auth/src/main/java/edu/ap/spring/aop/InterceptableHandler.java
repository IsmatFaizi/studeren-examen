package edu.ap.spring.aop;


import edu.ap.spring.service.Wallet;
import edu.ap.spring.transaction.Transaction;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class InterceptableHandler {

    @Around("@annotation(edu.ap.spring.aop.Interceptable) && execution(* edu.ap.spring.service.Wallet.sendFunds(..))")
    public Transaction controlBalance(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        float value = (float) args[1];
        Wallet wallet = (Wallet) joinPoint.getTarget();
        Transaction transaction = null;

        if (wallet.getBalance() < value) {
            System.out.println("# Not Enough funds to send transaction. Transaction Discarded.");
        } else {
            transaction = (Transaction) joinPoint.proceed();
        }

        return transaction;
    }

    @Around("@annotation(edu.ap.spring.aop.Interceptable) && execution(public * *(..))")
    public String restrict(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();

        String result;

        if (!isAdmin(request)) {
            result = "redirect:/login";
        } else {
            result = (String) joinPoint.proceed();
        }
        return result;
    }

    private static boolean isAdmin(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        boolean result = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Authorization") && cookie.getValue().equals("admin")) {
                    result = true;
                }
            }
        }
        return result;
    }
}

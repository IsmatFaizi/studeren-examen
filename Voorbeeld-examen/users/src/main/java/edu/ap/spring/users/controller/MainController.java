package edu.ap.spring.users.controller;

import edu.ap.spring.users.aop.Interceptable;
import edu.ap.spring.users.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Set;

@Controller
public class MainController {
    @Autowired
    private RedisService service;


    @GetMapping("/signup")
    public String showSignup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        Long userId = service.incr("user:counter");
        String key = "users:" + bytesToHex(email + password) + ":" + userId;
        service.setKey(key, email);

        model.addAttribute("message", "SIGNED UP");
        return "signup";
    }


    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @Interceptable
    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        String key = "users:" + bytesToHex(email + password) + ":*";
        Set<String> keys = service.keys(key);

        if(keys.isEmpty()){
            model.addAttribute("message", "NOT LOGGED IN");
        }
        else {
            model.addAttribute("message", "LOGGED IN");
        }

        return "login";
    }

    @GetMapping("/user/{userId}")
    public String showEmail(@PathVariable("userId") String userId, Model model) {
        String key = "users:*:" + userId;
        Set<String> keys = service.keys(key);
        String email = service.getKey(keys.iterator().next());
        model.addAttribute("email", email);
        return "email";
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

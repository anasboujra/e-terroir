package com.site.eterroir.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Map;

public class SecurityService {

    public static boolean isAdmin(){
        Map<String, String> user = getLoggedUser();
        if(user.get("authority").contains("ADMIN_ROLE")) {
            return true;
        }
        return false;
    }

    public static boolean isClient(){
        Map<String, String> user = getLoggedUser();
        if(user.get("authority").contains("CLIENT_ROLE")) {
            return true;
        }
        return false;
    }

    public static boolean isCooperative(){
        Map<String, String> user = getLoggedUser();
        if(user.get("authority").contains("COOPERATIVE_ROLE")) {
            return true;
        }
        return false;
    }

    public static Map<String, String> getLoggedUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) auth.getPrincipal();
        String authority = auth.getAuthorities().toString();
        Map<String, String> user = new HashMap<>();
        user.put("email", email);
        user.put("authority", authority);
        return user;
    }
}

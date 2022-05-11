package com.site.eterroir.security;

public class SecurityConstants {
    public static final String SECRET = "dont tell anyone 99";
    public static final Long EXPIRATION_TIME = 14 * 24 * 60 * 60 * 1000l;  // 14 jours
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
}

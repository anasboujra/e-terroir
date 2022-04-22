package com.site.eterroir.security;

public class SecurityConstants {
    public static final String SECRET = "try this one ;)";
    public static final Long EXPIRATION_TIME = 5 * 60 * 1000l;  // 5 min
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
}

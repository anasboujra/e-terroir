package com.site.eterroir.security;

public class SecurityPermissions {

    public static final String LOGIN_URL = "/api/connexion/**";

    public static final String[] CREATE_ACCOUNT = {
            "/api/clients/**"
    };

    public static final String[] PUBLIC = {
            "/api/produits/**",
            "/api/categories/**",
            "/api/matieres/**",
            "/api/origines/**",
            "/api/cooperatives/**"
    };

}

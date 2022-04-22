package com.site.eterroir.security;

public class SecurityPermissions {

    public static final String LOGIN_URL = "/api/connexion";

    public static final String[] VISITOR_PER = {
            "/api/produits/**"
    };

    public static final String[] ADMIN_PER = {
            "/api/admins/**",
            "/api/matieres-premieres/**"
    };

    public static final String[] COOPERATIVE_PER = {
            "/api/commandes/**"
    };

    public static final String[] CLIENT_PER = {
            "/api/commandes/**"
    };
}

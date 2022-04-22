package com.site.eterroir.security.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.site.eterroir.security.SecurityConstants.*;
import static com.site.eterroir.security.SecurityPermissions.LOGIN_URL;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if(request.getServletPath().equals(LOGIN_URL)){
            filterChain.doFilter(request, response);
        } else {
            String token = request.getHeader(HEADER_NAME);
            if(token != null && token.startsWith(TOKEN_PREFIX)){
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.substring(TOKEN_PREFIX.length()))
                        .getBody();
                String email = claims.getSubject();
                List<Map<String, String >> roles = (ArrayList<Map<String, String>>) claims.get("roles");
                List<GrantedAuthority> authorities = new ArrayList<>();
                roles.forEach(role ->{
                    authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                });
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(email, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

}

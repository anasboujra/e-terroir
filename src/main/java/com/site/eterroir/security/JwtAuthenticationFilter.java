package com.site.eterroir.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.site.eterroir.model.Utilisateur;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.site.eterroir.security.SecurityConstants.*;
import static com.site.eterroir.security.SecurityPermissions.LOGIN_URL;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   ObjectMapper objectMapper){
        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;
        setFilterProcessesUrl(LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        Utilisateur user = null;
        try {
            user = objectMapper.readValue(request.getInputStream(), Utilisateur.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("Trying to login with Email: {}", user.getEmail());
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),
                user.getMotDePasse()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {

        User springUser = (User)authentication.getPrincipal();
        String accessToken = Jwts.builder()
                .setSubject(springUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .claim("roles", springUser.getAuthorities())
                .compact();
        response.addHeader(HEADER_NAME, TOKEN_PREFIX + accessToken);
        log.info("Success : The access token is generated and sent in the header");
    }
}

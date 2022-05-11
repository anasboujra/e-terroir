package com.site.eterroir.service.implementation;

import com.site.eterroir.model.Admin;
import com.site.eterroir.model.Client;
import com.site.eterroir.model.Cooperative;
import com.site.eterroir.repository.AdminRepo;
import com.site.eterroir.repository.ClientRepo;
import com.site.eterroir.repository.CooperativeRepo;
import com.site.eterroir.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService, UserDetailsService {

    private final AdminRepo adminRepo;
    private final CooperativeRepo cooperativeRepo;
    private final ClientRepo clientRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User utilisateur;
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        Admin admin = adminRepo.findByEmail(email);
        if(admin != null){
            authorities.add(new SimpleGrantedAuthority("ADMIN_ROLE"));
            utilisateur = new User(admin.getEmail(), admin.getMotDePasse(), authorities);
        } else {
            Cooperative cooperative = cooperativeRepo.findByEmail(email);
            if(cooperative != null){
                authorities.add(new SimpleGrantedAuthority("COOPERATIVE_ROLE"));
                utilisateur = new User(cooperative.getEmail(), cooperative.getMotDePasse(), authorities);
            } else {
                Client client = clientRepo.findByEmail(email);
                if(client != null){
                    authorities.add(new SimpleGrantedAuthority("CLIENT_ROLE"));
                    utilisateur = new User(client.getEmail(), client.getMotDePasse(), authorities);
                } else {
                    throw new UsernameNotFoundException("User not found");
                }
            }
        }
        return utilisateur;
    }

}

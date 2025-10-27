package com.gestioneleves.api.service.usersDetailsService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gestioneleves.api.entity.AppUser;
import com.gestioneleves.api.repository.AppUserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(" Tentative d’authentification pour : " + username);

        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + username));
                 System.out.println("Utilisateur trouvé : " + user.getUsername() + " - rôle : " + user.getRole());

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) 
                .roles(user.getRole().name()) 
                .build();
    }
}


package com.upiiz.SecurityInDataBase.services;

import com.upiiz.SecurityInDataBase.entities.UserEntity;
import com.upiiz.SecurityInDataBase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Requerimos consultar un usuario por username
        // Con to dos sus details
        // Quien lo realiza - Repository
        UserEntity userEntity = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        // En userEntity = Sus datos, roles, permisos
        // authorities = roles y permisos
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // Roles
        userEntity.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRolEnum().name()));
        });
        // Permisos
        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermisions().stream())
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));
        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isAccountNoLocked(),
                userEntity.isCredentialNoExpired(),
                authorities
        );
    }
}

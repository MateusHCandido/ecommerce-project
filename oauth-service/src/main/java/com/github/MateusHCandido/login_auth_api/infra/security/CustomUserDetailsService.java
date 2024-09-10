package com.github.MateusHCandido.login_auth_api.infra.security;

import com.github.MateusHCandido.login_auth_api.domain.user.User;
import com.github.MateusHCandido.login_auth_api.infra.dto.RegisterRequestDTO;
import com.github.MateusHCandido.login_auth_api.infra.security.exception.UndefinedRoleException;
import com.github.MateusHCandido.login_auth_api.infra.security.exception.UserAlreadyExistException;
import com.github.MateusHCandido.login_auth_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;


    public User createUser(RegisterRequestDTO dto){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<User> user = repository.findByEmail(dto.email());
        if (user.isPresent()) throw new UserAlreadyExistException("User already exist. Try with another email");

        User newUser = new User();
        newUser.setEmail(dto.email());
        newUser.setPassword(passwordEncoder.encode(dto.password()));

        switch (dto.role()){
            case "A":
                newUser.setRole("ROLE_ADMIN");
                break;
            case "B":
                newUser.setRole("ROLE_USER");
                break;
            default:
                throw new UndefinedRoleException("the permission entered was not found as user or administrator");
        }

        repository.save(newUser);

        return newUser;
    }

    public User findByEmail(String email){
        return repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public User verifyEmailAndPassword(String email, String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = findByEmail(email);

        if(passwordEncoder.matches(password, user.getPassword())) return user;
        else return null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}

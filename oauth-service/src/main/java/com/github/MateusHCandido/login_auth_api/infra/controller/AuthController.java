package com.github.MateusHCandido.login_auth_api.infra.controller;

import com.github.MateusHCandido.login_auth_api.domain.user.User;
import com.github.MateusHCandido.login_auth_api.infra.dto.LoginRequestDTO;
import com.github.MateusHCandido.login_auth_api.infra.dto.RegisterRequestDTO;
import com.github.MateusHCandido.login_auth_api.infra.dto.ResponseDTO;
import com.github.MateusHCandido.login_auth_api.infra.security.CustomUserDetailsService;
import com.github.MateusHCandido.login_auth_api.infra.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CustomUserDetailsService service;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO dto){
        try{
            User newUser = service.createUser(dto);
            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getEmail(), token));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        try{
            User user = service.verifyEmailAndPassword(body.email(), body.password());
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getEmail(), token));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage(), null));
        }
    }
}

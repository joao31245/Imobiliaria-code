package com.jotace.imob.controller;

import com.jotace.imob.dto.LoginRequestDTO;
import com.jotace.imob.dto.LoginResponse;
import com.jotace.imob.dto.RegisterRequestDTO;
import com.jotace.imob.dto.RegisterResponseDTO;
import com.jotace.imob.service.user.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return authService.login(loginRequestDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
        return authService.register(registerRequestDTO, uriComponentsBuilder);
    }



}

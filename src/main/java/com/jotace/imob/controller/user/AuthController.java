package com.jotace.imob.controller.user;

import com.jotace.imob.dto.user.*;
import com.jotace.imob.infra.security.TokenService;
import com.jotace.imob.service.user.UserService;
import com.jotace.imob.service.user.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class AuthController {

    private final AuthService authService;

    private final TokenService tokenService;

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return authService.login(loginRequestDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
        return authService.register(registerRequestDTO, uriComponentsBuilder);
    }

    @GetMapping("/token")
    public ResponseEntity<GetUserDTO> getUserByToken(@RequestBody LoginResponse loginResponse) {
        var token = tokenService.validateToken(loginResponse.token());

        return ResponseEntity.ok(new GetUserDTO(userService.findUserByEmail(token)));

    }

}

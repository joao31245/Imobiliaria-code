package com.jotace.imob.service.user.auth;

import com.jotace.imob.dto.LoginRequestDTO;
import com.jotace.imob.dto.LoginResponse;
import com.jotace.imob.dto.RegisterRequestDTO;
import com.jotace.imob.dto.RegisterResponseDTO;
import com.jotace.imob.entity.user.User;
import com.jotace.imob.infra.security.TokenService;
import com.jotace.imob.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;
    public ResponseEntity<LoginResponse> login(LoginRequestDTO loginRequestDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password());

        var authentication = authenticationManager.authenticate(usernamePassword);

        var user = (User) authentication.getPrincipal();


        if(user == null) {
            return ResponseEntity.badRequest().body(new LoginResponse("Deu certo não"));
        }

        var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(token));

    }

    public ResponseEntity<RegisterResponseDTO> register(RegisterRequestDTO registerRequestDTO, UriComponentsBuilder uriBuilder) {



        var encryptedPassword = bCryptPasswordEncoder.encode(registerRequestDTO.password());



        var user = new User(registerRequestDTO, encryptedPassword);

        userService.createUser(user);

        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new RegisterResponseDTO(user));

    }



}

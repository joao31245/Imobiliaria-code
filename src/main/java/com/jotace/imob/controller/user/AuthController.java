package com.jotace.imob.controller.user;

import com.jotace.imob.dto.user.*;
import com.jotace.imob.infra.security.TokenService;
import com.jotace.imob.service.user.UserService;
import com.jotace.imob.service.user.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "auth", produces = ("application/json"))
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class AuthController {

    private final AuthService authService;

    private final TokenService tokenService;

    private final UserService userService;

    @PostMapping("/login")
    @Transactional
    @Operation(summary = "Log in a new User and devolve a token", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Worked!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return authService.login(loginRequestDTO);
    }

    @PostMapping("/register")
    @Transactional
    @Operation(summary = "Create a new User", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Worked!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
        return authService.register(registerRequestDTO, uriComponentsBuilder);
    }

    @GetMapping("/token")
    @Operation(summary = "Devolve a new User by the token", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Worked!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<GetUserDTO> getUserByToken(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            var tokenValidationResult = tokenService.validateToken(token);
            if (tokenValidationResult != null) {
                return ResponseEntity.ok(new GetUserDTO(userService.findUserByEmail(tokenValidationResult)));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}

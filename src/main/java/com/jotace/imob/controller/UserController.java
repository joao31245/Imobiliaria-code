package com.jotace.imob.controller;

import com.jotace.imob.dto.GetUserDTO;
import com.jotace.imob.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class UserController {

    private final UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<GetUserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}

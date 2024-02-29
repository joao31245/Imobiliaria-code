package com.jotace.imob.controller.user;

import com.jotace.imob.dto.user.GetUserDTO;
import com.jotace.imob.dto.user.UpdateRequestDTO;
import com.jotace.imob.dto.user.UpdateResponseDTO;
import com.jotace.imob.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get/{id}")
    public ResponseEntity<GetUserDTO> getOneUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<UpdateResponseDTO> updateUser(UpdateRequestDTO updateRequestDTO, @PathVariable String name) {
        return userService.update(updateRequestDTO, name);
    }


}

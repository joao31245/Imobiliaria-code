package com.jotace.imob.service.user;

import com.jotace.imob.dto.user.GetUserDTO;
import com.jotace.imob.dto.user.UpdateRequestDTO;
import com.jotace.imob.dto.user.UpdateResponseDTO;
import com.jotace.imob.entity.user.User;
import com.jotace.imob.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public List<GetUserDTO> getAllUsers() {
        var users = userRepository.findAll();

        return users.stream().map(GetUserDTO::new).toList();

    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
    
    public GetUserDTO findUserById(Long id) {
        return new GetUserDTO(userRepository.findUserById(id));
    }

    public User findById(Long id) {
        return userRepository.findUserById(id);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public ResponseEntity<UpdateResponseDTO> update(UpdateRequestDTO updateRequestDTO, Long id) {
        var user = this.findById(id);

        if(!user.getName().equals(updateRequestDTO.name())) {
            user.setName(updateRequestDTO.name());
        }

        if(!user.getEmail().equals(updateRequestDTO.email())) {
            user.setEmail(updateRequestDTO.email());
        }

        if(!user.getPhone().equals(updateRequestDTO.phone())) {
            user.setPhone(updateRequestDTO.phone());
        }

        return ResponseEntity.ok(new UpdateResponseDTO(user));
    }

    public User findUserByName(String name) { return userRepository.findUserByName(name);
    }
}

package com.jotace.imob.service.user;

import com.jotace.imob.dto.GetUserDTO;
import com.jotace.imob.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
    
    public GetUserDTO findUserById(Long id) {
        return new GetUserDTO(userRepository.findUserById(id));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}

package com.jotace.imob.dto.user;

import com.jotace.imob.entity.user.User;

public record UpdateResponseDTO(
        Long id,
        String name,
        String email,
        String phone
) {
    public UpdateResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPhone());
    }
}

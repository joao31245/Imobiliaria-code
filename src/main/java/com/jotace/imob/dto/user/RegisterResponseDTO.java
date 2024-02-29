package com.jotace.imob.dto.user;

import com.jotace.imob.entity.user.User;

public record RegisterResponseDTO(
        Long id,
        String name,
        String email


) {
    public RegisterResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}

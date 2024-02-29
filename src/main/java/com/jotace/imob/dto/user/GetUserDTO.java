package com.jotace.imob.dto.user;

import com.jotace.imob.entity.user.User;
import com.jotace.imob.entity.user.UserType;

public record GetUserDTO(
        Long id,
        String name,
        String email,
        String phone,
        UserType userType

) {

    public GetUserDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(),user.getPhone(), user.getUserType());
    }
}

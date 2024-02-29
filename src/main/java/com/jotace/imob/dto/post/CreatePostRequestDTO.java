package com.jotace.imob.dto.post;

import com.jotace.imob.entity.post.PostType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePostRequestDTO(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotNull
        int rooms,
        @NotNull
        int bathrooms,
        @NotNull
        boolean wifi,
        @NotNull
        boolean pool,
        @NotBlank
        PostType postType,
        @NotBlank
        String zipcode,
        @NotBlank
        String street,
        @NotBlank
        String neighborhood,
        @NotBlank
        String city,
        @NotBlank
        String state,
        @NotBlank
        String country,
        @NotBlank
        String number,
        @NotNull
        Long userId
) {
}

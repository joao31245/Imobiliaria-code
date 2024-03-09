package com.jotace.imob.dto.post;

import com.jotace.imob.entity.post.PostType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreatePostRequestDTO(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotNull
        int rooms,
        @NotNull
        BigDecimal price,
        @NotNull
        int bathrooms,
        @NotNull
        boolean wifi,
        @NotNull
        boolean pool,
        @NotNull
        PostType postType,
        @NotBlank
        String zipcode,
        @NotBlank
        String neighborhood,
        @NotBlank
        String city,
        @NotBlank
        String state,
        @NotBlank
        String country
) {
}

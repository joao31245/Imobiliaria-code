package com.jotace.imob.dto.post;

import com.jotace.imob.entity.post.Post;

import java.math.BigDecimal;

public record CreatePostResponseDTO(
        Long id,
        String title,
        String description,
        BigDecimal price,
        int rooms,
        int bathrooms,
        boolean wifi,
        boolean pool,
        Long userId
) {

    public CreatePostResponseDTO(Post post) {
        this(post.getId(), post.getTitle(), post.getDescription(), post.getPrice(), post.getRooms(),
                post.getBathRooms(), post.isWifi(), post.isPool(), post.getUser().getId());
    }
}

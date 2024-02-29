package com.jotace.imob.dto.post;

import com.jotace.imob.entity.post.Post;

public record CreatePostResponseDTO(
        Long id,
        String title,
        String description,
        int rooms,
        int bathrooms,
        boolean wifi,
        boolean pool,
        Long userId
) {

    public CreatePostResponseDTO(Post post) {
        this(post.getId(), post.getTitle(), post.getDescription(), post.getRooms(),
                post.getBathRooms(), post.isWifi(), post.isPool(), post.getUser().getId());
    }
}

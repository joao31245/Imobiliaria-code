package com.jotace.imob.dto.post;

import com.jotace.imob.entity.post.Post;
import com.jotace.imob.entity.post.PostType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public record GetPostDTO(
        Long id,
        String imageBase64,
        String title,
        String description,
        int rooms,
        int bathRooms,
        boolean pool,
        boolean wifi,
        PostType postType,
        String zipcode,
        String street,
        String neighborhood,
        String city,
        String state,
        String country

) {
    public GetPostDTO(Post post) throws SQLException, IOException {
        this(post.getId(), Mapper.convertBlobToBase64(post.getImage()), post.getTitle(),
                post.getDescription(), post.getRooms(),
                post.getBathRooms(), post.isPool(), post.isWifi(),
                post.getPostType(), post.getZipCode(), post.getStreet(),
                post.getNeighborhood(), post.getCity(), post.getState(),
                post.getCountry());
    }


}

class Mapper {
    public static String convertBlobToBase64(Blob blob) throws SQLException, IOException {
        byte[] bytes = blob.getBytes(1, (int) blob.length());
        return Base64.getEncoder().encodeToString(bytes);
    }
}

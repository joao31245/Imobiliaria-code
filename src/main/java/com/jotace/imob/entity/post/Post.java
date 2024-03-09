package com.jotace.imob.entity.post;

import com.jotace.imob.dto.post.CreatePostRequestDTO;
import com.jotace.imob.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "posts")
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private Blob image;

    private String title;

    private String description;

    private BigDecimal price;

    private int rooms;

    private int bathRooms;

    private boolean pool;

    private boolean wifi;

    private PostType postType;

    private String zipCode;

    private String street;

    private String neighborhood;

    private String city;

    private String state;

    private String country;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private LocalDateTime creationTime;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedTime;

    public Post(CreatePostRequestDTO createPostRequestDTO) {
        this.title = createPostRequestDTO.title();
        this.city =createPostRequestDTO.city();
        this.price = createPostRequestDTO.price();
        this.country = createPostRequestDTO.country();
        this.state = createPostRequestDTO.state();
        this.zipCode = createPostRequestDTO.zipcode();
        this.postType = createPostRequestDTO.postType();
        this.description = createPostRequestDTO.description();
        this.rooms = createPostRequestDTO.rooms();
        this.bathRooms = createPostRequestDTO.bathrooms();
        this.pool = createPostRequestDTO.pool();
        this.wifi = createPostRequestDTO.wifi();
    }
}

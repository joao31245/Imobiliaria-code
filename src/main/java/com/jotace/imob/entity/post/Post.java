package com.jotace.imob.entity.post;

import com.jotace.imob.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "posts")
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private Blob mainImage;

    @Lob
    private List<Blob> images;

    private String title;

    private String description;

    private String rooms;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private LocalDateTime creationTime;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedTime;
}

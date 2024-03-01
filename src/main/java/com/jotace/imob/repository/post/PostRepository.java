package com.jotace.imob.repository.post;

import com.jotace.imob.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findPostById(Long id);
}

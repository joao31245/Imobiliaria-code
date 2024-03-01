package com.jotace.imob.controller.post;

import com.jotace.imob.dto.post.CreatePostRequestDTO;
import com.jotace.imob.dto.post.CreatePostResponseDTO;
import com.jotace.imob.dto.post.GetPostDTO;
import com.jotace.imob.dto.post.InsertImageResponseDTO;
import com.jotace.imob.service.post.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
public class PostController {


    private final PostService postService;
    @PostMapping("/create")
    public ResponseEntity<CreatePostResponseDTO> createPost(@RequestBody @Valid CreatePostRequestDTO createPostRequestDTO, UriComponentsBuilder uriComponentsBuilder) {

        return postService.createPost(createPostRequestDTO, uriComponentsBuilder);
    }

    @PutMapping("/insert/image/{id}")
    public ResponseEntity<InsertImageResponseDTO> insertImage(@RequestParam("file") MultipartFile multipartFile,
                                                              @PathVariable Long id) {
        return postService.insertImage(multipartFile, id);
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<List<GetPostDTO>> getAllPosts() {
       return postService.getAllPosts();
    }

}

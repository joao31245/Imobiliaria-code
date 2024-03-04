package com.jotace.imob.controller.post;

import com.jotace.imob.dto.post.CreatePostRequestDTO;
import com.jotace.imob.dto.post.CreatePostResponseDTO;
import com.jotace.imob.dto.post.GetPostDTO;
import com.jotace.imob.dto.post.InsertImageResponseDTO;
import com.jotace.imob.service.post.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "post", produces = ("application/json"))
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class PostController {


    private final PostService postService;
    @PostMapping("/create")
    @Transactional
    @Operation(summary = "Create a new Post", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Worked!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<CreatePostResponseDTO> createPost(@RequestBody @Valid CreatePostRequestDTO createPostRequestDTO, UriComponentsBuilder uriComponentsBuilder) {

        return postService.createPost(createPostRequestDTO, uriComponentsBuilder);
    }

    @PutMapping("/insert/image/{id}")
    @Operation(summary = "Update specific post")
    public ResponseEntity<InsertImageResponseDTO> insertImage(@RequestParam("file") MultipartFile multipartFile,
                                                              @PathVariable Long id) {
        return postService.insertImage(multipartFile, id);
    }

    @GetMapping("/getAllPosts")
    @Operation(summary = "Pull all posts from database.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Worked!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<List<GetPostDTO>> getAllPosts() {
       return postService.getAllPosts();
    }

}

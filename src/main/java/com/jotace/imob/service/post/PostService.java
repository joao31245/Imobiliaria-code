package com.jotace.imob.service.post;

import com.jotace.imob.dto.post.CreatePostRequestDTO;
import com.jotace.imob.dto.post.CreatePostResponseDTO;
import com.jotace.imob.dto.post.GetPostDTO;
import com.jotace.imob.dto.post.InsertImageResponseDTO;
import com.jotace.imob.entity.post.Post;
import com.jotace.imob.infra.security.TokenService;
import com.jotace.imob.repository.post.PostRepository;
import com.jotace.imob.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final TokenService tokenService;
    public ResponseEntity<CreatePostResponseDTO> createPost(CreatePostRequestDTO createPostRequestDTO, UriComponentsBuilder uriBuilder, String token) {
        var post = new Post(createPostRequestDTO);
        var userEmail = tokenService.validateToken(token.replace("Bearer ", ""));
        var user = userService.findUserByEmail(userEmail);
        post.setUser(user);
        postRepository.save(post);
        var uri = uriBuilder.path("/post/{id}").buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(uri).body(new CreatePostResponseDTO(post));
    }

    public ResponseEntity<InsertImageResponseDTO> insertImage(MultipartFile file, Long id) {
        try {
            byte[] fileBytes = file.getBytes();

            Blob blob = new SerialBlob(fileBytes);

            var post = postRepository.findPostById(id);

            post.setImage(blob);

            postRepository.save(post);

            return ResponseEntity.ok().build();
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<List<GetPostDTO>> getAllPosts() {
        try {
            var posts = postRepository.findAll();
            List<GetPostDTO> dtos = posts.stream()
                    .map(post -> {
                        try {
                            return new GetPostDTO(post);
                        } catch (SQLException | IOException e) {
                            e.printStackTrace();
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(dtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<GetPostDTO> getPostById(Long id) throws SQLException, IOException {
        return ResponseEntity.ok(new GetPostDTO( (Post) postRepository.findPostById(id)));
    }
}

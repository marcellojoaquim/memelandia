package com.marcello.post_server.controller;

import com.marcello.post_server.domain.dto.PostDTO;
import com.marcello.post_server.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDTO> salvar(@RequestBody PostDTO postDTO) {
        PostDTO saved = postService.save(postDTO);
        return ResponseEntity.ok(saved);
    }
}

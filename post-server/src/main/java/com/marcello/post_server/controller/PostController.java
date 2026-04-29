package com.marcello.post_server.controller;

import com.marcello.post_server.domain.dto.PostDTO;
import com.marcello.post_server.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDTO> salvar(@RequestBody PostDTO postDTO) {
        log.info("Salvando o PostDTO");
        PostDTO saved = postService.save(postDTO);
        return ResponseEntity.ok(saved);
    }
}

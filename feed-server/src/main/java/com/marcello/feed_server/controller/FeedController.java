package com.marcello.feed_server.controller;

import com.marcello.feed_server.domain.dto.CategoriaDTO;
import com.marcello.feed_server.domain.dto.PostDTO;
import com.marcello.feed_server.service.FeedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("feed")
@Slf4j
public class FeedController {

    private final FeedService feedService;

    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @GetMapping
    public ResponseEntity<Page<PostDTO>> feed(Pageable pageable) {
        log.info("Chamando o Feed de memes");
        return ResponseEntity.ok(feedService.feed(pageable));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<Page<PostDTO>> findByCategoria(@PathVariable String categoria, Pageable pageable) {
        log.info("Chamando o Feed de memes por categoria");
        return ResponseEntity.ok(feedService.findByCategoria(categoria, pageable));
    }

    @GetMapping("/usuario/{email}")
    public ResponseEntity<Page<PostDTO>> findByUsuarioEmail(@PathVariable String email, Pageable pageable) {
        log.info("Chamando o Memes por email do usuario");
        return ResponseEntity.ok(feedService.findByUsuarioEmail(email, pageable));
    }

    @GetMapping("/meme-do-dia")
    public ResponseEntity<PostDTO> findMemeDoDia() {
        log.info("Chamando o Meme do dia");
        return ResponseEntity.ok(feedService.memeDoDia());
    }

    @GetMapping("categorias")
    public ResponseEntity<Page<CategoriaDTO>> findCategorias(Pageable pageable) {
        log.info("Chamando os categorias");
        return ResponseEntity.ok(feedService.findCategorias(pageable));
    }
}

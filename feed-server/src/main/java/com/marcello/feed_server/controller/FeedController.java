package com.marcello.feed_server.controller;

import com.marcello.feed_server.domain.dto.PostDTO;
import com.marcello.feed_server.service.FeedService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("feed")
public class FeedController {

    private final FeedService feedService;

    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @GetMapping
    public ResponseEntity<Page<PostDTO>> feed(Pageable pageable) {
        return ResponseEntity.ok(feedService.feed(pageable));
    }
}

package com.marcello.meme_server.controller;

import com.marcello.meme_server.service.impl.MemeServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memes")
public class MemeController {

    private final MemeServiceImpl memeService;

    public MemeController(MemeServiceImpl memeService) {
        this.memeService = memeService;
    }


}

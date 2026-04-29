package com.marcello.feed_server.service;

import com.marcello.feed_server.domain.dto.CategoriaDTO;
import com.marcello.feed_server.domain.dto.PostDTO;
import com.marcello.feed_server.domain.entity.Post;
import com.marcello.feed_server.integration.IMemeService;
import com.marcello.feed_server.repository.FeedRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FeedService {

    private final FeedRepository feedRepository;
    private final IMemeService memeService;

    public FeedService(FeedRepository feedRepository, IMemeService memeService) {
        this.feedRepository = feedRepository;
        this.memeService = memeService;
    }

    public Page<PostDTO> feed(Pageable pageable) {
        Page<Post> posts = feedRepository.findAll(pageable);

        return posts.map(post -> {
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post, postDTO);
            return postDTO;
        });
    }

    public Page<PostDTO> findByCategoria(String categoria, Pageable pageable) {
        Page<Post> posts = feedRepository.findByNomeCategoria(categoria, pageable);

        return posts.map(post -> {
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post, postDTO);
            return postDTO;
        });
    }

    public Page<PostDTO> findByUsuarioEmail(String email, Pageable pageable) {
        Page<Post> posts = feedRepository.findByEmailUsuario(email, pageable);

        return posts.map(post -> {
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post, postDTO);
            return postDTO;
        });
    }

    public PostDTO memeDoDia() {
        Post memeDoDia = feedRepository.findMemeDoDia();
        PostDTO dto = new PostDTO();
        BeanUtils.copyProperties(memeDoDia, dto);
        return dto;
    }

    public Page<CategoriaDTO> findCategorias(Pageable pageable) {
        return memeService.buscarCategorias(pageable);
    }


}

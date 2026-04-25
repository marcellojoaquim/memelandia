package com.marcello.post_server.service;

import com.marcello.post_server.domain.dto.MemeDTO;
import com.marcello.post_server.domain.dto.PostDTO;
import com.marcello.post_server.domain.entity.Post;
import com.marcello.post_server.integration.IUsurarioService;
import com.marcello.post_server.producer.MemeProducer;
import com.marcello.post_server.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final IUsurarioService usurarioService;
    private final MemeProducer memeProducer;
    private final ModelMapper modelMapper;

    public PostService(PostRepository postRepository, IUsurarioService usurarioService, MemeProducer memeProducer, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.usurarioService = usurarioService;
        this.memeProducer = memeProducer;
        this.modelMapper = modelMapper;
    }

    public PostDTO save(PostDTO postDTO) {
        usurarioService.buscarUsuario(postDTO.getEmailUsuario());

        Post post = modelMapper.map(postDTO, Post.class);
        post.setDataPublicacao(Instant.now());
        Post saved = postRepository.save(post);

        MemeDTO memeDTO = modelMapper.map(saved, MemeDTO.class);

        memeProducer.enviarMeme(memeDTO);

        return modelMapper.map(saved, PostDTO.class);
    }
}

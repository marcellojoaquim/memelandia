package com.marcello.feed_server.service;

import com.marcello.feed_server.domain.dto.PostDTO;
import com.marcello.feed_server.domain.entity.Post;
import com.marcello.feed_server.repository.FeedRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FeedService {

    private final FeedRepository feedRepository;

    public FeedService(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    public Page<PostDTO> feed(Pageable pageable) {
        Page<Post> posts = feedRepository.findAll(pageable);

        return posts.map(post -> {
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post, postDTO);
            return postDTO;
        });
    }
}

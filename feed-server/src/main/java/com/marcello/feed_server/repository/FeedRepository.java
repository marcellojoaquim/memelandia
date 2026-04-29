package com.marcello.feed_server.repository;

import com.marcello.feed_server.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends MongoRepository<Post, String> {

    Page<Post> findByNomeCategoria(String categoria, Pageable pageable);
    Page<Post> findByEmailUsuario(String email, Pageable pageable);

    @Aggregation(pipeline = { "{ $sample: { size: 1 } }"})
    Post findMemeDoDia();
}

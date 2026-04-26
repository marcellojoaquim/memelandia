package com.marcello.feed_server.repository;

import com.marcello.feed_server.domain.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends MongoRepository<Post, String> {
}

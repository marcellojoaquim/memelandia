package com.marcello.meme_server.repository;

import com.marcello.meme_server.model.entity.Meme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemeRepository extends JpaRepository<Meme, Long> {
}

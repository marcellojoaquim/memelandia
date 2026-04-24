package com.marcello.meme_server.repository;

import com.marcello.meme_server.model.entity.Meme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemeRepository extends JpaRepository<Meme, Long> {

    @EntityGraph(attributePaths = "categoria")
    Page<Meme> findByCategoriaId(Long id, Pageable pageable);
}

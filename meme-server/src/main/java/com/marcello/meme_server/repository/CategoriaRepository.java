package com.marcello.meme_server.repository;

import com.marcello.meme_server.model.entity.Categoria;
import com.marcello.meme_server.model.entity.Meme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    boolean existsByNome(String nome);
    Optional<Categoria> findByNome(String nome);
}

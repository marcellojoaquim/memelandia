package com.marcello.meme_server.repository;

import com.marcello.meme_server.model.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    boolean exisByNome(String nome);
    Optional<Categoria> findByNome(String nome);
}

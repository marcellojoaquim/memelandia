package com.marcello.meme_server.service;

import com.marcello.meme_server.model.dto.MemeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MemeService {

    MemeDTO salvar(MemeDTO memeDTO);
    Optional<MemeDTO> buscarPorId(Long id);
    Page<MemeDTO> findByCategoriaId(Long id, Pageable pageable);
    Page<MemeDTO> findAll(Pageable pageable);
    Page<MemeDTO> findByNome(String nome, Pageable pageable);
    Page<MemeDTO> findByCategoriaNome(String nome, Pageable pageable);
}

package com.marcello.meme_server.service;

import com.marcello.meme_server.model.dto.CategoriaDTO;
import com.marcello.meme_server.model.entity.Categoria;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

public interface CategoriaService {

    Optional<CategoriaDTO> bucarPorNome(String nome);
    Optional<CategoriaDTO> buscarPorId(Long id);
    CategoriaDTO salvar(CategoriaDTO categoriaDTO);
    CategoriaDTO findByNomeOrCreate(String nomeCategoria);
}

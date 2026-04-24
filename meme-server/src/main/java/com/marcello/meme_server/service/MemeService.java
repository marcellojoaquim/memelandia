package com.marcello.meme_server.service;

import com.marcello.meme_server.model.dto.MemeDTO;

import java.util.Optional;

public interface MemeService {

    MemeDTO salvar(MemeDTO memeDTO);
    Optional<MemeDTO> buscarPorId(Long id);
}

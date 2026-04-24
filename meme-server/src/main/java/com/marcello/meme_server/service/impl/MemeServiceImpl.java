package com.marcello.meme_server.service.impl;

import com.marcello.meme_server.model.dto.MemeDTO;
import com.marcello.meme_server.model.entity.Categoria;
import com.marcello.meme_server.model.entity.Meme;
import com.marcello.meme_server.repository.CategoriaRepository;
import com.marcello.meme_server.repository.MemeRepository;
import com.marcello.meme_server.service.CategoriaService;
import com.marcello.meme_server.service.MemeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;

import java.time.Instant;
import java.util.Optional;

public class MemeServiceImpl implements MemeService {

    private final MemeRepository memeRepository;
    private final CategoriaService categoriaService;
    private final ModelMapper modelMapper;

    public MemeServiceImpl(MemeRepository memeRepository, CategoriaService categoriaService, ModelMapper modelMapper) {
        this.memeRepository = memeRepository;
        this.categoriaService = categoriaService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public MemeDTO salvar( MemeDTO memeDTO) {
        var categoriaDTO = categoriaService.findByNomeOrCreate(memeDTO.getNomeCategoria());
        var categoria = modelMapper.map(categoriaDTO, Categoria.class);

        Meme meme = modelMapper.map(memeDTO, Meme.class);
        meme.setCategoria(categoria);
        meme.setDataCadastro(Instant.now());

        Meme saved = memeRepository.save(meme);

        return modelMapper.map(saved, MemeDTO.class);
    }

    @Override
    public Optional<MemeDTO> buscarPorId(Long id) {
        Meme meme = memeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Meme não encontrado"));
        return Optional.of(modelMapper.map(meme, MemeDTO.class));
    }
}

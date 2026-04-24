package com.marcello.meme_server.service.impl;

import com.marcello.meme_server.model.dto.MemeDTO;
import com.marcello.meme_server.model.entity.Categoria;
import com.marcello.meme_server.model.entity.Meme;
import com.marcello.meme_server.repository.MemeRepository;
import com.marcello.meme_server.service.CategoriaService;
import com.marcello.meme_server.service.MemeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Cacheable(value = "meme", key = "#id")
    public Optional<MemeDTO> buscarPorId(Long id) {
        Meme meme = memeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Meme não encontrado"));
        return Optional.of(modelMapper.map(meme, MemeDTO.class));
    }

    @Override
    public PageImpl<MemeDTO> findByCategoriaId(Long id, Pageable pageable) {
        Page<Meme> memes = memeRepository.findByCategoriaId(id, pageable);

        var memesDTO = memes.stream()
                .map(meme -> modelMapper.map(meme, MemeDTO.class))
                .collect(Collectors.toUnmodifiableList());

        return new PageImpl<>(memesDTO, pageable, memes.getTotalElements());
    }

    @Override
    public PageImpl<MemeDTO> findAll(Pageable pageable) {
        Page<Meme> memes = memeRepository.findAll(pageable);

        var memesDTO = memes.stream()
                .map(meme -> modelMapper.map(meme, MemeDTO.class))
                .collect(Collectors.toUnmodifiableList());

        return new PageImpl<>(memesDTO, pageable, memes.getTotalElements());
    }

    @Override
    public Optional<MemeDTO> findByNome(String nome) {
        var meme = memeRepository.findByNome(nome)
                .orElseThrow(() -> new EntityNotFoundException("Meme não encontrado."));

        return Optional.of(modelMapper.map(meme, MemeDTO.class));
    }

    @Override
    public PageImpl<MemeDTO> findByCategoriaNome(String nome, Pageable pageable) {
        Page<Meme> memes = memeRepository.findByCategoriaNome(nome);
        var memesDTO = memes.stream()
                .map(meme -> modelMapper.map(meme, MemeDTO.class))
                .collect(Collectors.toUnmodifiableList());
        return new PageImpl<>(memesDTO, pageable, memes.getTotalElements());
    }
}

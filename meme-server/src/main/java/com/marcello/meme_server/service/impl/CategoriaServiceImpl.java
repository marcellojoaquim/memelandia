package com.marcello.meme_server.service.impl;

import com.marcello.meme_server.model.dto.CategoriaDTO;
import com.marcello.meme_server.model.entity.Categoria;
import com.marcello.meme_server.repository.CategoriaRepository;
import com.marcello.meme_server.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ModelMapper modelMapper;

    public CategoriaServiceImpl(ModelMapper modelMapper, CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<CategoriaDTO> bucarPorNome(String nome) {
        Optional<Categoria> categoria = categoriaRepository.findByNome(nome);

        if(categoria.isEmpty()) {
            throw new EntityNotFoundException("Categoria não encontrada");
        }
        return Optional.of(modelMapper.map(categoria, CategoriaDTO.class));
    }

    @Override
    public Optional<CategoriaDTO> buscarPorId(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isEmpty()) {
            throw new EntityNotFoundException("Categoria não encontrada");
        }
        return Optional.of(modelMapper.map(categoria, CategoriaDTO.class));
    }

    @Override
    public CategoriaDTO salvar(CategoriaDTO categoriaDTO) {
        Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);
        categoria.setDataCadastro(Instant.now());
        Categoria saved = categoriaRepository.save(categoria);
        return modelMapper.map(saved, CategoriaDTO.class);
    }
}

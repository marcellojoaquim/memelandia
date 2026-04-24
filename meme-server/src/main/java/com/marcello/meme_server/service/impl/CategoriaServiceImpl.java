package com.marcello.meme_server.service.impl;

import com.marcello.meme_server.exception.BusinessException;
import com.marcello.meme_server.model.dto.CategoriaDTO;
import com.marcello.meme_server.model.entity.Categoria;
import com.marcello.meme_server.repository.CategoriaRepository;
import com.marcello.meme_server.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "categoria", key = "#nome")
    public Optional<CategoriaDTO> bucarPorNome(String nome) {
        var categoria = categoriaRepository.findByNome(nome)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
        return Optional.of(modelMapper.map(categoria, CategoriaDTO.class));
    }

    @Override
    @Cacheable(value = "categoria", key = "#id")
    public Optional<CategoriaDTO> buscarPorId(Long id) {
        var categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
        return Optional.of(modelMapper.map(categoria, CategoriaDTO.class));
    }

    @Override
    public CategoriaDTO salvar(CategoriaDTO categoriaDTO) {
        if(categoriaRepository.existsByNome(categoriaDTO.getNome())) {
            throw new BusinessException("Já existe uma categoria com este nome");
        }
        Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);
        categoria.setDataCadastro(Instant.now());
        Categoria saved = categoriaRepository.save(categoria);
        return modelMapper.map(saved, CategoriaDTO.class);
    }

    @Override
    @Transactional
    public CategoriaDTO findByNomeOrCreate(String nomeCategoria) {
        var dataCadastro = Instant.now();
        Categoria categoria = categoriaRepository.findByNome(nomeCategoria)
                .orElseGet(() -> {
                    Categoria nova = new Categoria(nomeCategoria, nomeCategoria, dataCadastro);
                    return categoriaRepository.save(nova);
                });
        return modelMapper.map(categoria, CategoriaDTO.class);
    }
}

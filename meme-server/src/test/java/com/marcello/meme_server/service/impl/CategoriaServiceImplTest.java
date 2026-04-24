package com.marcello.meme_server.service.impl;

import com.marcello.meme_server.exception.BusinessException;
import com.marcello.meme_server.model.dto.CategoriaDTO;
import com.marcello.meme_server.model.entity.Categoria;
import com.marcello.meme_server.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceImplTest {

    @InjectMocks
    private CategoriaServiceImpl service;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private ModelMapper modelMapper;

    private Categoria categoria;
    private CategoriaDTO categoriaDTO;
    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        categoria = new Categoria(id, "Teste", "Teste", Instant.now());
        categoriaDTO = new CategoriaDTO(id, "Teste", "Teste");
    }

    @Test
    void bucarPorNome_deveRetornarCategoriaDTO() {
        when(categoriaRepository.findByNome("Teste")).thenReturn(Optional.of(categoria));
        when(modelMapper.map(categoria, CategoriaDTO.class)).thenReturn(categoriaDTO);

        Optional<CategoriaDTO> result = service.bucarPorNome("Teste");

        assertTrue(result.isPresent());
        assertEquals("Teste", result.get().getNome());
        verify(categoriaRepository).findByNome("Teste");
    }

    @Test
    void bucarPorNome_deveLancarExcecao() {
        when(categoriaRepository.findByNome("Teste")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.bucarPorNome("Teste"));
    }

    @Test
    void buscarPorId_deveRetornarCategoriaDTO() {
        when(categoriaRepository.findById(id)).thenReturn(Optional.of(categoria));
        when(modelMapper.map(categoria, CategoriaDTO.class)).thenReturn(categoriaDTO);

        Optional<CategoriaDTO> result = service.buscarPorId(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        verify(categoriaRepository).findById(id);
    }

    @Test
    void buscarPorId_deveLancarExcecao() {
        when(categoriaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.buscarPorId(id));
    }

    @Test
    void salvar_deveSalvarCategoria() {
        when(categoriaRepository.existsByNome("Teste")).thenReturn(false);
        when(modelMapper.map(categoriaDTO, Categoria.class)).thenReturn(categoria);
        when(categoriaRepository.save(any())).thenReturn(categoria);
        when(modelMapper.map(categoria, CategoriaDTO.class)).thenReturn(categoriaDTO);

        CategoriaDTO result = service.salvar(categoriaDTO);

        assertNotNull(result);
        assertEquals("Teste", result.getNome());
        verify(categoriaRepository).save(any());
    }

    @Test
    void salvar_deveLancarExcecaoQuandoNomeDuplicado() {
        when(categoriaRepository.existsByNome("Teste")).thenReturn(true);

        assertThrows(BusinessException.class, () -> service.salvar(categoriaDTO));
    }

    @Test
    void findByNomeOrCreate_deveRetornarExistente() {
        when(categoriaRepository.findByNome("Teste")).thenReturn(Optional.of(categoria));
        when(modelMapper.map(categoria, CategoriaDTO.class)).thenReturn(categoriaDTO);

        CategoriaDTO result = service.findByNomeOrCreate("Teste");

        assertNotNull(result);
        verify(categoriaRepository, never()).save(any());
    }

    @Test
    void findByNomeOrCreate_deveCriarNovaCategoria() {
        when(categoriaRepository.findByNome("Teste")).thenReturn(Optional.empty());
        when(categoriaRepository.save(any())).thenReturn(categoria);
        when(modelMapper.map(categoria, CategoriaDTO.class)).thenReturn(categoriaDTO);

        CategoriaDTO result = service.findByNomeOrCreate("Teste");

        assertNotNull(result);
        verify(categoriaRepository).save(any());
    }
}
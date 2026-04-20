package com.marcello.cliente_service.service.impl;

import static org.mockito.Mockito.*;

import com.marcello.cliente_service.exception.BusinessException;
import com.marcello.cliente_service.model.dto.UsuarioDTO;
import com.marcello.cliente_service.model.entity.Usuario;
import com.marcello.cliente_service.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class UsuarioServiceImplTest {

    @InjectMocks
    UsuarioServiceImpl usuarioService;

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    ModelMapper modelMapper;

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;
    private final Long ID = 1L;
    private final String EMAIL = "email@email.com";
    private final Instant DATA = Instant.now();
    private final String EMAIL_INEXISTENTE =  "inexistente@teste.com";

    @BeforeEach
    void setUp() {
        usuarioDTO = UsuarioDTO.builder()
                .nome("Marcello")
                .email("marcello@email.com")
                .dataCadastro(DATA).build();

        usuario = new Usuario(
                ID,
                usuarioDTO.getNome(),
                usuarioDTO.getEmail(),
                usuarioDTO.getDataCadastro()
        );

    }

    @Test
    @DisplayName("Deve salvar um usuário.")
    void save() {
        when(modelMapper.map(usuarioDTO, Usuario.class)).thenReturn(usuario);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        when(modelMapper.map(usuario, UsuarioDTO.class)).thenReturn(usuarioDTO);

        UsuarioDTO result = usuarioService.save(usuarioDTO);

        verify(usuarioRepository, times(1)).save(usuario);
        assertEquals(result.getEmail(), usuario.getEmail());
        assertNotNull(usuario.getId());
    }

    @Test
    @DisplayName("Deve atualizar um usuario por email.")
    void update() {
        when(modelMapper.map(usuario, UsuarioDTO.class)).thenReturn(usuarioDTO);
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.ofNullable(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        UsuarioDTO result = usuarioService.update(ID, usuarioDTO);

        assertEquals(usuario.getEmail(), result.getEmail());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    @DisplayName("Deve retornar um usuario por id.")
    void findById() {
        when(modelMapper.map(usuario, UsuarioDTO.class)).thenReturn(usuarioDTO);
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.ofNullable(usuario));

        Optional<UsuarioDTO> result = usuarioService.findById(ID);

        assertTrue(result.isPresent());
        assertNotNull(result.get().getNome());
        assertEquals(usuario.getEmail(), result.get().getEmail());
        verify(usuarioRepository,times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Deve retornar um usuario por email")
    void findByEmail() {
        when(modelMapper.map(usuario, UsuarioDTO.class)).thenReturn(usuarioDTO);
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.of(usuario));

        Optional<UsuarioDTO> result = usuarioService.findByEmail(EMAIL);

        assertTrue(result.isPresent());
        assertNotNull(result.get().getNome());
        assertEquals(usuario.getEmail(), result.get().getEmail());
        verify(usuarioRepository, times(1)).findByEmail(anyString());
    }

    @Test
    @DisplayName("Deve retornar todos os usuarios paginados")
    void buscarTodos() {
        List<Usuario> usuarios = List.of(usuario);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Usuario> usuarioPage = new PageImpl<>(usuarios, pageable, usuarios.size());

        when(modelMapper.map(any(Usuario.class), eq(UsuarioDTO.class)))
                .thenAnswer(invocation -> {
                    Usuario u = invocation.getArgument(0);
                    return new UsuarioDTO(u.getNome(), u.getEmail(), u.getDataCadastro());
                });

        when(usuarioRepository.findAll(pageable)).thenReturn(usuarioPage);
        Page<UsuarioDTO> usuarioDTOPage = usuarioService.buscarTodos(pageable);

        assertNotNull(usuarioDTOPage);
        assertEquals(1, usuarioDTOPage.getContent().size());
    }

    @Test
    @DisplayName("Deve remover um usuario por id")
    void deletar() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
        doNothing().when(usuarioRepository).deleteById(anyLong());
        usuarioService.deletar(ID);

        verify(usuarioRepository, times(1)).deleteById(anyLong());
    }

    @Test
    @DisplayName("Deve validar se um usuario existe atraves do email")
    void existsByEmail() {
        when(usuarioRepository.existsByEmail(anyString())).thenReturn(true);
        boolean result = usuarioService.existsByEmail(EMAIL);

        assertTrue(result);
    }

    @Test
    @DisplayName("Deve lançar uma exception quando buscar usuario com email que existe")
    void buscarComEmailInexistente() {
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            usuarioService.findByEmail(EMAIL_INEXISTENTE);
        });

        verify(usuarioRepository, times(1)).findByEmail(anyString());
    }

    @Test
    @DisplayName("Deve lançar uma exception quando buscar usuario com email que existe")
    void buscarComIdInexistente() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        EntityNotFoundException entityNotFoundException = assertThrows(EntityNotFoundException.class, () -> {
            usuarioService.findByEmail(EMAIL_INEXISTENTE);
        });

        assertEquals("Usuario não encontrado", entityNotFoundException.getMessage());
        verify(usuarioRepository, times(1)).findByEmail(anyString());
    }

    @Test
    @DisplayName("Deve lançar uma exception quando deletar um usuario com id null")
    void deletarComIdNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.deletar(null);
        });

        assertEquals("Id não deve ser nulo", exception.getMessage());
        verify(usuarioRepository, never()).deleteById(anyLong());
    }

    @Test
    @DisplayName("Deve lançar uma exception quando deletar um usuario com id inexistente")
    void deletarComIdInexistente() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        EntityNotFoundException entityNotFoundException = assertThrows(EntityNotFoundException.class, () -> {
            usuarioService.deletar(ID);
        });

        assertEquals("Usuario não encontrado", entityNotFoundException.getMessage());
        verify(usuarioRepository, never()).deleteById(anyLong());
    }

    @Test
    @DisplayName("Deve lançar uma exception quando atualizar um usuario com id inexistente")
    void updateComIdInexistente() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        EntityNotFoundException entityNotFoundException = assertThrows(EntityNotFoundException.class, () -> {
            usuarioService.update(ID, usuarioDTO);
        });

        assertEquals("Usuario não encontrado", entityNotFoundException.getMessage());
        verify(usuarioRepository, never()).deleteById(anyLong());
    }

    @Test
    @DisplayName("Deve lançar uma exception quando atualizar um usuario com id inexistente")
    void saveComEmailExistente() {
        when(usuarioRepository.existsByEmail(anyString())).thenReturn(true);

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            usuarioService.save(usuarioDTO);
        });

        assertEquals("Email já está em uso.", exception.getMessage());
        verify(usuarioRepository, never()).save(usuario);
    }

}
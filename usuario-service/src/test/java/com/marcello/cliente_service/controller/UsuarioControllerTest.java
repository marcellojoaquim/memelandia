package com.marcello.cliente_service.controller;

import com.marcello.cliente_service.model.dto.UsuarioDTO;
import com.marcello.cliente_service.model.entity.Usuario;
import com.marcello.cliente_service.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioServiceImpl usuarioService;

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;


    @Test
    void buscarUsuarioPorEmail() throws Exception{
        usuarioDTO = UsuarioDTO.builder()
                .email("usuario@usuario.com")
                .nome("Usuario Teste Funcional").build();
        when(usuarioService.findByEmail(anyString())).thenReturn(Optional.of(usuarioDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/email?email=usuario@usuario.com")
                .with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Usuario Teste Funcional"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("usuario@usuario.com"));
    }

    @Test
    void buscarUsuarioPorId() throws Exception{
        usuarioDTO = UsuarioDTO.builder()
                .email("usuario@usuario.com")
                .nome("Usuario Teste Funcional").build();
        when(usuarioService.findById(anyLong())).thenReturn(Optional.of(usuarioDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/1")
                        .with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Usuario Teste Funcional"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("usuario@usuario.com"));
    }

    @Test
    void salvarUsuario() throws Exception{
        usuarioDTO = UsuarioDTO.builder()
                .email("usuario@usuario.com")
                .nome("Usuario Teste Funcional").build();

        when(usuarioService.save(any())).thenReturn(usuarioDTO);

        String jsonBody = """
                {
                    "nome": "Usuario Teste Funcional",
                    "email": "usuario@usuario.com"
                }
                """;

        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post("/usuarios")
                        .with(SecurityMockMvcRequestPostProcessors.jwt())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
        );

        result.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Usuario Teste Funcional"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("usuario@usuario.com"));
    }

    @Test
    void atualizarUsuario() throws Exception{
        usuarioDTO = UsuarioDTO.builder()
                .email("usuario@usuario.com")
                .nome("Usuario Teste Funcional").build();
        when(usuarioService.findById(anyLong())).thenReturn(Optional.of(usuarioDTO));
        when(usuarioService.update(anyLong(), any())).thenReturn(usuarioDTO);

        String jsonBody = """
                {
                    "nome": "Usuario Teste Funcional",
                    "email": "usuario@usuario.com"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
                        .with(SecurityMockMvcRequestPostProcessors.jwt())
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Usuario Teste Funcional"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("usuario@usuario.com"));
    }

    @Test
    void removerUsuario() throws Exception{
        usuarioDTO = UsuarioDTO.builder()
                .email("usuario@usuario.com")
                .nome("Usuario Teste Funcional").build();

        when(usuarioService.findById(anyLong())).thenReturn(Optional.of(usuarioDTO));
        doNothing().when(usuarioService).deletar(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/usuarios/1")
                        .with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
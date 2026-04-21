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
    void buscarTodosUsuarios() {
    }

    @Test
    void buscarUsuarioPorEmail() {
    }

    @Test
    void buscarUsuarioPorId() {
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
    void atualizarUsuario() {
    }

    @Test
    void removerUsuario() {
    }
}
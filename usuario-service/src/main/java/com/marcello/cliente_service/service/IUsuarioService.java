package com.marcello.cliente_service.service;

import com.marcello.cliente_service.model.dto.UsuarioDTO;
import com.marcello.cliente_service.model.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUsuarioService {

    UsuarioDTO save(UsuarioDTO usuario);
    UsuarioDTO update(Long id, UsuarioDTO usuarioDTO);
    Optional<UsuarioDTO> findById(Long id);
    Optional<UsuarioDTO> findByEmail(String email);
    Page<UsuarioDTO> buscarTodos(Pageable pageable);
    void deletar(Long id);
    boolean existsByEmail(String email);

}

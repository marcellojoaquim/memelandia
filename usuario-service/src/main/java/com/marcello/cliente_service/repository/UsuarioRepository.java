package com.marcello.cliente_service.repository;


import com.marcello.cliente_service.model.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Page<Usuario> findAll(Pageable pageable);
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsById(Long id);
}

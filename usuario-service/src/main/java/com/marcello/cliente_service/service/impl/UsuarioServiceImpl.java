package com.marcello.cliente_service.service.impl;

import com.marcello.cliente_service.exception.BusinessException;
import com.marcello.cliente_service.model.dto.UsuarioDTO;
import com.marcello.cliente_service.model.entity.Usuario;
import com.marcello.cliente_service.repository.UsuarioRepository;
import com.marcello.cliente_service.service.IUsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioRepository repository;
    private final ModelMapper modelMapper;

    public UsuarioServiceImpl(UsuarioRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UsuarioDTO save(UsuarioDTO usuario) {
        if(repository.existsByEmail(usuario.getEmail())){
            throw new BusinessException("Email já está em uso.");
        }
        var usuarioEntity = modelMapper.map(usuario, Usuario.class);
        validaDados(usuarioEntity);
        Usuario saved = repository.save(usuarioEntity);
        return modelMapper.map(saved, UsuarioDTO.class);
    }

    @Override
    @Transactional
    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
        if(id == null) {
            throw new BusinessException("Informe um id valido.");
        }

        Usuario usuarioEncontrado = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

        repository.findByEmail(usuarioDTO.getEmail())
                .ifPresent(usuarioComMesmoEmail -> {
                    if (!usuarioComMesmoEmail.getId().equals(id)) {
                        throw new BusinessException("Este e-mail já está sendo utilizado por outro usuário.");
                    }
                });

        usuarioEncontrado.setEmail(usuarioDTO.getEmail());
        usuarioEncontrado.setNome(usuarioDTO.getNome());
        Usuario saved = repository.save(usuarioEncontrado);
        return modelMapper.map(saved, UsuarioDTO.class);
    }

    @Override
    @Cacheable(value = "usuarios", key = "#id")
    public Optional<UsuarioDTO> findById(Long id) {
        var usuario = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        var result = modelMapper.map(usuario, UsuarioDTO.class);
        return Optional.of(result);
    }

    @Override
    @Cacheable(value = "usuarios", key = "#email")
    public Optional<UsuarioDTO> findByEmail(String email) {
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        var usuarioDto = modelMapper.map(usuario, UsuarioDTO.class);
        return Optional.of(usuarioDto);
    }

    @Override
    public PageImpl<UsuarioDTO> buscarTodos(Pageable pageable) {
        Page<Usuario> usuarios = repository.findAll(pageable);
        var usuariosDTO = usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toUnmodifiableList());
        return new PageImpl<>(usuariosDTO, pageable, usuarios.getTotalElements());
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "usuarios", key = "#usuario.id"),
            @CacheEvict(value = "usuarios", key = "#usuario.email")
    })
    public void deletar(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Id não deve ser nulo");
        }
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        repository.deleteById(usuario.getId());
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    private void validaDados(Usuario usuario){
        if (Stream.of(usuario.getNome(), usuario.getEmail())
                .anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Dados obrigatorios ausentes.");
        }
    }
}

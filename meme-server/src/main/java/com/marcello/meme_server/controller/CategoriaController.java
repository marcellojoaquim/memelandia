package com.marcello.meme_server.controller;

import com.marcello.meme_server.model.dto.CategoriaDTO;
import com.marcello.meme_server.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CategoriaDTO>> buscarPorId(@PathVariable("id") Long id) {
        log.info("Buscando as categorias por id");
        var categoriaDTO = categoriaService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(Optional.of(categoriaDTO));
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(categoriaService.findAll(pageable));
    }

    @GetMapping("/nome")
    public ResponseEntity<Optional<CategoriaDTO>> buscarPorNome(@RequestParam("nome") String nome) {
        log.info("Buscando as categorias por nome.");
            var categoriaDTO = categoriaService.bucarPorNome(nome)
                    .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrado"));
            return ResponseEntity.ok(Optional.of(categoriaDTO));
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> salvar(@RequestBody @Valid CategoriaDTO categoriaDTO) {
        log.info("Salvando categoria");
        CategoriaDTO saved = categoriaService.salvar(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}

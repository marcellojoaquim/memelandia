package com.marcello.meme_server.controller;

import com.marcello.meme_server.model.dto.CategoriaDTO;
import com.marcello.meme_server.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CategoriaDTO>> buscarPorId(@PathVariable("id") Long id) {
        var categoriaDTO = categoriaService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(Optional.of(categoriaDTO));
    }

    @GetMapping("/nome")
    public ResponseEntity<Optional<CategoriaDTO>> buscarPorNome(@RequestParam("email") String nome) {
            var categoriaDTO = categoriaService.bucarPorNome(nome)
                    .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrado"));
            return ResponseEntity.ok(Optional.of(categoriaDTO));
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> salvar(CategoriaDTO categoriaDTO) {
        CategoriaDTO saved = categoriaService.salvar(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}

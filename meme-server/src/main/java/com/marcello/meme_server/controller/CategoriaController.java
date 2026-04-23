package com.marcello.meme_server.controller;

import com.marcello.meme_server.model.dto.CategoriaDTO;
import com.marcello.meme_server.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        try {
            Optional<CategoriaDTO> categoriaDTO = categoriaService.buscarPorId(id);
            return ResponseEntity.ok(categoriaDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nome")
    public ResponseEntity<Optional<CategoriaDTO>> buscarPorNome(@RequestParam("email") String nome) {
        try {
            Optional<CategoriaDTO> categoriaDTO = categoriaService.bucarPorNome(nome);
            return ResponseEntity.ok(categoriaDTO);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> salvar(CategoriaDTO categoriaDTO) {
        CategoriaDTO saved = categoriaService.salvar(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}

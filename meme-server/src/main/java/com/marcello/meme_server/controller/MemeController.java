package com.marcello.meme_server.controller;

import com.marcello.meme_server.model.dto.MemeDTO;
import com.marcello.meme_server.service.impl.MemeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/memes")
public class MemeController {

    private final MemeServiceImpl memeService;

    public MemeController(MemeServiceImpl memeService) {
        this.memeService = memeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Buscar todos os memes paginados")
    public PageImpl<MemeDTO> buscarTodos(Pageable pageable) {
        return memeService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(description = "Retorna um meme por id")
    public ResponseEntity<Optional<MemeDTO>> buscarPorId(@PathVariable("id") Long id) {
        MemeDTO memeDTO = memeService.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Meme não encontrado para este id"));
        return ResponseEntity.ok(Optional.of(memeDTO));
    }

    @GetMapping("/meme")
    @Operation(description = "Retorna um meme pelo nome")
    public ResponseEntity<Optional<MemeDTO>> buscarPorNome(@RequestParam("nome") String nome) {
        MemeDTO memeDTO = memeService.findByNome(nome)
                .orElseThrow(() -> new NegativeArraySizeException("Meme não encontrado"));
        return ResponseEntity.ok(Optional.of(memeDTO));
    }

    @GetMapping("/categoria/{id}")
    @Operation(description = "Buscar os memes pelo id da categoria")
    public PageImpl<MemeDTO> buscarPorCategoriaId(@PathVariable("id") Long id, Pageable pageable) {
         return memeService.findByCategoriaId(id, pageable);
    }

    @GetMapping("/categoria-nome")
    @Operation(description = "Retorna os memes por um nome de categoria")
    public PageImpl<MemeDTO> buscarPorCategoriaNome(@RequestParam("nome") String nome, Pageable pageable) {
        return memeService.findByCategoriaNome(nome, pageable);
    }
}

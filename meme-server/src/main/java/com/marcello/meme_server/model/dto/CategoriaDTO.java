package com.marcello.meme_server.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {

    private Long id;

    @NotBlank(message = "Nome da categoria é obrigatório")
    private String nome;

    @NotBlank(message = "Descrição da categoria é obrigatório")
    private String descricao;
}

package com.marcello.meme_server.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemeDTO {

    private Long id;

    @NotBlank(message = "Nome do meme é obrigatório")
    private String nome;

    @NotBlank(message = "Descricao do meme é obrigatório")
    private String descricao;

    @NotBlank(message = "Catogoria do meme é obrigatória")
    private String nomeCategoria;

}

package com.marcello.feed_server.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private String id;

    @NotBlank(message = "Nome deve ser fornecido")
    private String nome;

    @NotBlank(message = "Forneça uma url")
    private String url;

    @NotBlank(message = "Descricao obrigatoria")
    private String descricao;

    @NotBlank(message = "Informe a categoria")
    private String nomeCategoria;

    @NotBlank(message = "Informe o email do usuario")
    private String emailUsuario;
}
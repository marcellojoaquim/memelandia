package com.marcello.post_server.domain.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemeDTO {

    private String nome;

    private String descricao;

    private String nomeCategoria;
}

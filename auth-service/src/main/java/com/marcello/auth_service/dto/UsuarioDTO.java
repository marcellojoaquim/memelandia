package com.marcello.auth_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank(message = "Email é obrigatorio")
    @Email(message = "Insira um email válido")
    @Pattern(regexp=".+@.+\\..+", message = "Insira um email válido")
    private String email;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant dataCadastro;
}

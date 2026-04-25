package com.marcello.post_server.domain.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collation = "posts")
public class Post {

    @Id
    private String id;

    @NotBlank
    @Size(max = 50)
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull
    private Instant dataPublicacao;

    @NotBlank
    private String categoria;

    @NotBlank
    private String emailUsuario;

    public Post() {
    }

    public Post(String nome, String descricao, String categoria, Instant dataPublicacao, String emailUsuario) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataPublicacao = dataPublicacao;
        this.emailUsuario = emailUsuario;
        this.categoria = categoria;
    }

    public Post(String id, String nome, String descricao, String categoria, Instant dataPublicacao, String emailUsuario) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataPublicacao = dataPublicacao;
        this.emailUsuario = emailUsuario;
        this.categoria = categoria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Instant getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Instant dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }
}

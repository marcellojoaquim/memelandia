package com.marcello.post_server.domain.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "posts")
public class Post {

    @Id
    private String id;

    @NotBlank
    @Size(max = 50)
    private String nome;

    @NotBlank
    private String url;

    @NotBlank
    private String descricao;

    @NotNull
    private Instant dataPublicacao;

    @NotBlank
    private String nomeCategoria;

    @NotBlank
    private String emailUsuario;

    public Post() {
    }

    public Post(String nome, String url, String descricao, String nomeCategoria, Instant dataPublicacao, String emailUsuario) {
        this.nome = nome;
        this.url = url;
        this.descricao = descricao;
        this.dataPublicacao = dataPublicacao;
        this.emailUsuario = emailUsuario;
        this.nomeCategoria = nomeCategoria;
    }

    public Post(String id, String nome, String url, String descricao, String nomeCategoria, Instant dataPublicacao, String emailUsuario) {
        this.id = id;
        this.nome = nome;
        this.url = url;
        this.descricao = descricao;
        this.dataPublicacao = dataPublicacao;
        this.emailUsuario = emailUsuario;
        this.nomeCategoria = nomeCategoria;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
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

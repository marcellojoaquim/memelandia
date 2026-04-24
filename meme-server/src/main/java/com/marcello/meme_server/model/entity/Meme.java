package com.marcello.meme_server.model.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "meme_tb")
public class Meme {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meme_seq")
    @SequenceGenerator(name = "meme_seq", sequenceName = "seq_meme", initialValue = 1)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, name = "data_cadastro")
    private Instant dataCadastro;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Meme() {
    }

    public Meme(String nome, String descricao, Instant dataCadastro, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
        this.categoria = categoria;
    }

    public Meme(Long id, String nome, String descricao, Instant dataCadastro, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Instant getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Instant dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}

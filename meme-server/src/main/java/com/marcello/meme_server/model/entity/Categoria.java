package com.marcello.meme_server.model.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "categoria_tb")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_seq")
    @SequenceGenerator(name = "categoria_seq", sequenceName = "seq_categoria", initialValue = 1)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, name = "data_cadastro")
    private Instant dataCadastro;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    private List<Meme> meme;

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

    public List<Meme> getMeme() {
        return meme;
    }

    public void setMeme(List<Meme> meme) {
        this.meme = meme;
    }
}

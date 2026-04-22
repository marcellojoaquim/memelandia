package com.marcello.meme_server.model.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "categoria_tb")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_seq")
    @SequenceGenerator(name = "categoria_seq", sequenceName = "seq_categoria", initialValue = 1)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, name = "data_cadastro")
    private Instant dataCadastro;

    @OneToMany
    private Meme meme;
}

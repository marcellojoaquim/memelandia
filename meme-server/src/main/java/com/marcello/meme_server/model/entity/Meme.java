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
}

package com.marcello.cliente_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "usuario_tb")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq", sequenceName = "seq_usuario", initialValue = 1)
    private Long id;

    @Column(name = "usuario_nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "usuario_email", unique = true, nullable = false)
    @Email(message = "Insira um email válido")
    @Pattern(regexp=".+@.+\\..+", message = "Insira um email válido")
    private String email;

    @Column(name = "data_cadastro", nullable = false)
    private Instant dataCadastro;

    public Usuario() {
    }

    public Usuario(String nome, String email, Instant dataCadastro) {
        this.nome = nome;
        this.email = email;
        this.dataCadastro = dataCadastro;
    }

    public Usuario(Long id, String nome, String email, Instant dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataCadastro = dataCadastro;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Instant dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(id, usuario.id) && Objects.equals(nome, usuario.nome) && Objects.equals(email, usuario.email) && Objects.equals(dataCadastro, usuario.dataCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, dataCadastro);
    }
}

package com.marcello.post_server.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "usuario")
public class Usuario {

    @Id
    private String id;

    private String nome;
    private String email;
}

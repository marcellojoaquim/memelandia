package com.marcello.meme_server.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "meme_tb")
public class Meme {

    @Id

    private Long id;
}

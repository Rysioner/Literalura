package com.alura.literalura.models;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String autor;
    private double descargas;
    private String idioma;
    private String titulo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getDescargas() {
        return descargas;
    }

    public void setDescargas(double descargas) {
        this.descargas = descargas;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}

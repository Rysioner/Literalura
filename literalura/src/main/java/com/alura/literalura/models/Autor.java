package com.alura.literalura.models;

import jakarta.persistence.*;

import java.util.List;

public class Autor {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;
}

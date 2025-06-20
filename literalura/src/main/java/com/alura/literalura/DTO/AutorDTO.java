package com.alura.literalura.DTO;

public record AutorDTO (
        Long id, String autor,
        String nombre,
        Integer anoDeNacimiento,
        Integer anoDeFallecimiento
) {

}

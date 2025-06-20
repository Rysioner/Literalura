package com.alura.literalura.DTO;

public record LibroDTO(
        Long id, String autor,
        double descargas,
        String idioma,
        String titulo
) {
}

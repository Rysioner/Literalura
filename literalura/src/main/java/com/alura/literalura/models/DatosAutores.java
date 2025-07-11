package com.alura.literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosAutores(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer anoDeNacimiento,
        @JsonAlias("death_year") Integer anoDeFallecimiento
) {
}

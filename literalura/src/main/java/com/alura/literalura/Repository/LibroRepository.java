package com.alura.literalura.Repository;

import com.alura.literalura.DTO.LibroDTO;
import com.alura.literalura.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query("SELECT l FROM libros l WHERE l.idioma = :idioma")
    List<Libro> libroPorIdioma(String idioma);
}
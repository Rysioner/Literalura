package com.alura.literalura.services;

import com.alura.literalura.DTO.LibroDTO;
import com.alura.literalura.Repository.LibroRepository;
import com.alura.literalura.models.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepo;

    public List<LibroDTO> convierteDatos(List<Libro> libro) {
        return libro.stream()
                .map(l -> new LibroDTO(l.getId(), l.getAutor(), l.getDescargas(), l.getIdioma(), l.getTitulo()))
                .collect(Collectors.toList());
    }

    public List<LibroDTO> obtenerTodosLosLibros() {
        return convierteDatos(libroRepo.findAll());
    }

    public List<LibroDTO> obtenerLibroPorIdioma(String idioma) {
        return convierteDatos(libroRepo.libroPorIdioma(idioma));
    }

    /*

    public List<LibroDTO> obtenerLibrosApi() {
        return repository.findAll().stream()
                .map(l -> new LibroDTO(l.getAutor(), l.getDescargas(), l.getIdioma(), l.getTitulo()))
                .collect(Collectors.toList());
    }

    public List<LibroDTO> obtenerLibros() {
        return convierteDatos(repository.findAll());
    }
    public LibroDTO obtenerPorId(Long id) {
        Optional<Libro> libro = repository.findById(id);
        if (libro.isPresent()){
            Libro l = libro.get();
            return new LibroDTO(l.getAutor(), l.getDescargas(), l.getIdioma(), l.getTitulo());
        }
        return null;
    }

    public void guardar(Libro libro) {
        repository.save(libro);
    }*/
}

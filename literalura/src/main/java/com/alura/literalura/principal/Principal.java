package com.alura.literalura.principal;

import com.alura.literalura.Repository.LibroRepository;
import com.alura.literalura.models.*;
import com.alura.literalura.services.ConsumoAPI;
import com.alura.literalura.services.ConvertidorDatos;
import com.alura.literalura.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal{

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String url = "https://gutendex.com/books/";
    private ConvertidorDatos conversor = new ConvertidorDatos();
    private List<Autor> autores;


    public void muestraMenu() {
        System.out.println("""
                **********************************************************
                Elija la opción a través de su número:
                1) Buscar libro por título
                2) Listar libros registrados
                3) Listar autores registrados
                4) Listar autores vivos en un determinado año
                5) Listar libros por idioma
                6) Salir
                **********************************************************
                """);
    }



    public void opcionElegida(int opcion, LibroService libro, LibroRepository libroRepo) {
        var json = consumoApi.obtenerDatos(url);
        //System.out.println(json);


        switch (opcion) {
            case 1: {
                System.out.println("Ingresa el nombre del libro a buscar: ");
                String busqueda = teclado.nextLine();
                json = consumoApi.obtenerDatos(url + "?search="+busqueda.replace(" ","+"));
                var datosBusqueda = conversor.obtenerDatos(json, DatosLibreria.class);
                Optional<DatosLibros> libroBuscado = datosBusqueda.libros().stream()
                        .filter(l -> l.titulo().toUpperCase().contains(busqueda.toUpperCase()))
                        .findFirst();
                if (libroBuscado.isPresent()){
                    System.out.println("Libro encontrado");
                    System.out.println("Título: " + libroBuscado.get().titulo());
                    System.out.println("Autor: " + libroBuscado.get().autores().get(0).nombre());
                    System.out.println("Idioma: " + libroBuscado.get().idiomas().get(0));
                    System.out.println("Número de descargas: " + libroBuscado.get().numeroDeDescargas());
                    Libro librito = new Libro();
                    librito.setTitulo(libroBuscado.get().titulo());
                    librito.setAutor(libroBuscado.get().autores().get(0).nombre());
                    librito.setIdioma(libroBuscado.get().idiomas().get(0));
                    librito.setDescargas(libroBuscado.get().numeroDeDescargas());
                    libroRepo.save(librito);

            } else{
                System.out.println("No encontrado");
            }
            }
            case 2: {
                int limite = Math.toIntExact(libro.obtenerTodosLosLibros().stream().count());
                for (int i = 0; i < limite; i++) {
                    System.out.println("---------LIBRO---------");
                    System.out.println("Título: " + libro.obtenerTodosLosLibros().get(i).titulo());
                    System.out.println("Autor: " + libro.obtenerTodosLosLibros().get(i).autor());
                    System.out.println("Idioma: " + libro.obtenerTodosLosLibros().get(i).idioma());
                    System.out.println("Número de descargas: " + libro.obtenerTodosLosLibros().get(i).descargas());
                    System.out.println("-----------------------\n");
                }
            }
            case 5: {
                System.out.println("Ingresa el idioma que deseas buscar: ");
                System.out.println("es - español");
                System.out.println("en - inglés");
                System.out.println("fr - francés");
                System.out.println("pt - portugués");
                String busquedaIdioma = teclado.nextLine();
                libro.obtenerLibroPorIdioma(busquedaIdioma);
            }
        }
    }
/*
    private DatosAutores getDatosAutores() {
        System.out.println("Escribe el nombre del autor: ");
        var nombreAutor =teclado.nextLine();
        var json = consumoApi.obtenerDatos(url + "?search="+nombreAutor.replace(" ","+"));
        System.out.println(json);
        DatosAutores datos = conversor.obtenerDatos(json, DatosAutores.class);
        return datos;
    }

    private void buscarLibroPorAutor() {
        System.out.println("Escribe el nombre del autor del cual quieres ver sus libros: ");
        var nombreActor = teclado.nextLine();

        Optional<Autor> autor = autores.stream()
                .filter(a -> a.getNombre().toLowerCase().contains(nombreActor.toLowerCase()))
                .findFirst();

        if (autor.isPresent()){
            var autorEncontrado = autor.get();
            System.out.println("Autor: " + autor.get().getNombre());
            System.out.println("Fecha de Nacimiento: " + autor.get().getFechaNacimiento());
            System.out.println("Fecha de Fallecimiento: " + autor.get().getFechaFallecimiento());
            System.out.println("Libros: " + autor.get().getLibros());
        }
    }*/
}

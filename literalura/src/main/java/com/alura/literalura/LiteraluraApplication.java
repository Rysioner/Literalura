package com.alura.literalura;

import com.alura.literalura.Repository.LibroRepository;
import com.alura.literalura.principal.Principal;
import com.alura.literalura.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.Scanner;


@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}



	@Autowired
	private LibroService libro = new LibroService();
	@Autowired
	private LibroRepository libroRepo;

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal();
		Scanner teclado = new Scanner(System.in);
		while (true){
			try {
				principal.muestraMenu();
				int opcion =teclado.nextInt();

				if (opcion == 6){
					System.out.println("Finalizando aplicación.");
					break;
				}
				principal.opcionElegida(opcion, libro, libroRepo);
			} catch (InputMismatchException e){
				System.out.println("Error, ingresó un caracter inválido.");
				System.out.println("Finalizando aplicación.");
				break;
			}
		}
	}

}

package com.literalura.literalura;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {
    private boolean salir = false;
    @Autowired
    private Cliente cliente;
    private final String menu = """
            ######################################################
            ## 1 - Buscar libro por titulo.                     ##
            ## 2 - Listar libros registrados.                   ##
            ## 3 - Listar autores registrados.                  ##
            ## 4 - Listar autores vivos en un determinado año.  ##
            ## 5 - Listar libros por idioma.                    ##
            ## 6 - Finalizar.                                   ##
            ######################################################
            """;
    private Scanner teclado = new Scanner(System.in);

    public void mostrarMenu(){

        while (!salir) {
            System.out.println(menu);

            switch (teclado.nextInt()) {
                case 1:
                    System.out.println("Ingrese un titulo a buscar :");
                    teclado = new Scanner(System.in);
                    String titulo = teclado.nextLine();
                    cliente.buscarLibroPorTitulo(titulo);
                    break;

                case 2:
                    cliente.buscarLibrosRegistrados();
                    break;

                case 3:
                    cliente.buscarAutoresRegistrados();
                    break;

                case 4:
                    System.out.println("Ingrese el año a buscar :");
                    teclado = new Scanner(System.in);
                    Integer anio = teclado.nextInt();
                    cliente.buscarAutoresVivosEnAnio(anio);
                    break;

                case 5:
                    System.out.println("Ingrese un idioma a buscar :");
                    teclado = new Scanner(System.in);
                    String idioma = teclado.nextLine();
                    cliente.buscarLibrosPorIdioma(idioma);
                    break;

                case 6:
                    System.out.println("Hasta pronto!");
                    salir = true;
                    break;

                default:
                    System.out.println("Debe ingresar una opcion correcta");
            }
        }

    }
}

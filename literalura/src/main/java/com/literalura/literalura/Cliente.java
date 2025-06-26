package com.literalura.literalura;

import com.literalura.literalura.model.*;
import com.literalura.literalura.service.AutorService;
import com.literalura.literalura.service.ConsumoApi;
import com.literalura.literalura.service.DataConverter;
import com.literalura.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Cliente {
    @Autowired
    private LibroService libroService;
    @Autowired
    private AutorService autorService;
    private ConsumoApi consumoApi = new ConsumoApi();
    private static final String URL = "https://gutendex.com/books/";
    private Scanner teclado = new Scanner(System.in);
    private DataConverter convertor = new DataConverter();


    public void buscarLibroPorTitulo(String titulo){
        String response = consumoApi.obtenerDatos(URL+"?search="+titulo.replace(" ", "+"));
        ResultadoBusqueda busqueda = convertor.obtenerDatos(response, ResultadoBusqueda.class);
        if(! busqueda.results().isEmpty()){
            imprimirResultado(busqueda);
        }else {
            System.out.println("No se encontro libro con ese nombre");
        }
    }

    private void imprimirResultado(ResultadoBusqueda resultado){
        DatosLibro datosLibro = resultado.results().get(0);
        Set<Autor> autores = guardarAutores(datosLibro.autor());
        Libro libro = new Libro(datosLibro.titulo(), autores,datosLibro.idiomas(),datosLibro.numeroDescargas());
        libroService.save(libro);
        System.out.println(resultado.results().get(0));
    }

    private Set<Autor> guardarAutores(List<DatosAutor> autores){
        return autores.stream()
                .map(a -> autorService.agregarAutor(new Autor(a.nombre(),a.anioNacimiento(), a.anioMuerte())))
                .collect(Collectors.toSet());
    }

    public void buscarLibrosRegistrados(){
        List<Libro> libros = libroService.getAll();
        libros.stream()
                .map(l -> new DatosLibro(l.getTitulo(),l.getAutores().stream().map(a -> new DatosAutor(a.getFechaNacimiento(),a.getFechaMuerte(),a.getNombre())).toList(),l.getIdiomas(),l.getNumeroDescargas()))
                .toList()
                .forEach(System.out::println);
    }
    public void buscarAutoresRegistrados(){
       List<Autor> autores = autorService.todosLosAutores();
       if (autores.isEmpty()){
           System.out.println("No se encontraron autores registrados!");
       }else {
           System.out.println(autores.stream()
                   .map(a -> new DatosAutor(a.getFechaNacimiento(), a.getFechaMuerte(), a.getNombre()))
                   .collect(Collectors.toList()));
       }
    }
    public void buscarAutoresVivosEnAnio(Integer anio){
        List<Autor> autores = autorService.autoresVivosEnAnio(anio);
        if (autores.isEmpty()){
            System.out.println("No se encontraron autores vivos en ese año!");
        }else {
            System.out.println(autores.stream()
                    .map(a -> new DatosAutor(a.getFechaNacimiento(), a.getFechaMuerte(), a.getNombre()))
                    .collect(Collectors.toList()));
        }
    }
    public void buscarLibrosPorIdioma(String idioma){
        List<Libro> libros = libroService.librosPorIdioma(idioma);
        if (libros.isEmpty()){
            System.out.println("No se encontraron libros en " + idioma);
        }else {
            System.out.println(libros.stream()
                    .map(l -> new DatosLibro(l.getTitulo(), l.getAutores().stream().map(a-> new DatosAutor(a.getFechaNacimiento(),a.getFechaMuerte(),a.getNombre())).toList(),l.getIdiomas(),l.getNumeroDescargas()))
                    .collect(Collectors.toList()));
        }
    }

}

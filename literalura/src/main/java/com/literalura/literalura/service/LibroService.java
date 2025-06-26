package com.literalura.literalura.service;

import com.literalura.literalura.model.Libro;
import com.literalura.literalura.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {
    @Autowired
    private ILibroRepository repoLibro;

    public Libro save(Libro libro){
        return repoLibro.save(libro);
    }

    public List<Libro> getAll(){
        return repoLibro.findAll();
    }
    public List<Libro> librosPorIdioma(String idioma){
        return repoLibro.librosPorIdioma(idioma);
    }
}

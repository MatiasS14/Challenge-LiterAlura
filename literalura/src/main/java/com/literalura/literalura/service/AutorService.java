package com.literalura.literalura.service;

import com.literalura.literalura.model.Autor;
import com.literalura.literalura.repository.IAutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    @Autowired
    private IAutorRepository autorRepo;

    public Autor agregarAutor(Autor autor){
        Optional<Autor> busqueda =autorRepo.findByNombre(autor.getNombre());
        if (busqueda.isEmpty()){
            return autorRepo.save(autor);
        }else {
            return busqueda.get();
        }
    }

    public List<Autor> todosLosAutores(){
        return autorRepo.findAll();
    }

    public List<Autor> autoresVivosEnAnio(Integer anio){
        return autorRepo.autoresVivosEnAnio(anio);
        //        return autorRepo.findAutoresByFechaMuerteBefore(anio);
    }
}

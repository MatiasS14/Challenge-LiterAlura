package com.literalura.literalura.repository;

import com.literalura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IAutorRepository extends JpaRepository<Autor,Long> {
    Optional<Autor> findByNombre(String name);

    @Query("FROM Autor a WHERE a.fechaMuerte > :fechaMuerte")
    List<Autor> autoresVivosEnAnio(Integer fechaMuerte);
}

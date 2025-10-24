package com.ArquitecturaSpringboot.prueba.Repository;

import com.ArquitecturaSpringboot.prueba.Model.DTO.LibroDTO;
import com.ArquitecturaSpringboot.prueba.Model.Entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {
    public List<Libro> findByautor(String autor);

    @Query("SELECT l FROM Libro l WHERE l.paginas > :paginas")
    List<Libro> findLibrosConMasPaginasQue(@Param("paginas") int paginas);

}

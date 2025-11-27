package com.ArquitecturaSpringboot.prueba.Services;

import com.ArquitecturaSpringboot.prueba.Model.DTO.LibroDTO;
import com.ArquitecturaSpringboot.prueba.Model.Entities.Libro;
import com.ArquitecturaSpringboot.prueba.Repository.LibroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LibroService {

    private final LibroRepository myRepo;

    public Libro createLibro(LibroDTO libro) throws IllegalArgumentException {
        if (!StringUtils.hasText(libro.getTitulo()) ||
                !StringUtils.hasText(libro.getAutor()) ||
                libro.getPaginas()<= 0  ) {
            throw new IllegalArgumentException("El título no puede ser nulo ni vacío");
        }
        return myRepo.save(new Libro(null,libro.getTitulo(),libro.getAutor(),libro.getPaginas()));
    }

    public List<LibroDTO> getLibros() {
        return myRepo.findAll().stream().
                map(LibroService::getLibroDTO).toList();

    }



    public List<LibroDTO> searchLibroByAutor(String autor) {
        return myRepo.findByAutor(autor).stream().map(LibroService::getLibroDTO).toList();
    }

    public List<LibroDTO> getLibrosMayoresDe(int x) {
        return myRepo.findLibrosConMasPaginasQue(x).stream().map(LibroService::getLibroDTO).toList();
    }

    private static LibroDTO getLibroDTO(Libro x) {
        LibroDTO libro = new LibroDTO();
        libro.setAutor(x.getAutor());
        libro.setPaginas((x.getPaginas()));
        libro.setTitulo(x.getTitulo());
        return libro;
    }
}

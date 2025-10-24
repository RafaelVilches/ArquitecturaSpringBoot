package com.ArquitecturaSpringboot.prueba.Controller;

import com.ArquitecturaSpringboot.prueba.Model.DTO.LibroDTO;
import com.ArquitecturaSpringboot.prueba.Model.Entities.Libro;
import com.ArquitecturaSpringboot.prueba.Services.LibroService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
@AllArgsConstructor
public class LibroController {
    @Autowired
    private final LibroService myService;

    @PostMapping
    public Libro createLibro(@RequestBody LibroDTO libro)
    {
        return myService.createLibro(libro);
    }
    @GetMapping
    public List<LibroDTO> getLibros()
    {
        return myService.getLibros();
    }

    @GetMapping("/buscar")
    public LibroDTO searchLibro(@RequestParam String autor)
    {
        return myService.searchLibro(autor);
    }

    @GetMapping("/mayores")
    public List<LibroDTO> getLibrosMayoresDe(@RequestParam int x)
    {
        return myService.getLibrosMayoresDe(x);
    }
}
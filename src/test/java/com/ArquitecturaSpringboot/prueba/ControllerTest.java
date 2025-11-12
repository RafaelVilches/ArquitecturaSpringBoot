package com.ArquitecturaSpringboot.prueba;

import com.ArquitecturaSpringboot.prueba.Controller.LibroController;
import com.ArquitecturaSpringboot.prueba.Model.DTO.LibroDTO;
import com.ArquitecturaSpringboot.prueba.Model.Entities.Libro;
import com.ArquitecturaSpringboot.prueba.Repository.LibroRepository;
import com.ArquitecturaSpringboot.prueba.Services.LibroService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(LibroController.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibroService service;
    @MockBean
    private LibroRepository repository;

    private AutoCloseable mocks;

    @BeforeEach
    void setup()
    {
        mocks = MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void getByAutor_ShouldReturnLibro() throws Exception {
        //Arrange
        when(service.searchLibroByAutor("Rubius")).thenReturn(List.of(new LibroDTO()
                .autor("Rubius")
                .titulo("Libro Troll")
                .paginas(150)));
        // Act
        mockMvc.perform(get("/api/libros/buscar?autor=Rubius"))
                //Assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].autor").value("Rubius"))
                .andExpect(jsonPath("$[0].titulo").value("Libro Troll"))
                .andExpect(jsonPath("$[0].paginas").value(150));
    }
    @Test
    void createBook_ShouldReturn202() throws Exception {
        //Arrange
        when(service.createLibro(any(LibroDTO.class)))
                .thenReturn(new Libro(null,"Cuthulu","Lovecraft",300));
        // Act

        mockMvc.perform(post("/api/libros")
                        .contentType("application/json")
                        .content("""
                    {
                      "autor": "Lovecraft",
                      "titulo": "Cuthulu",
                      "paginas": 300
                    }
                """))
                //Assert
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.autor").value("Lovecraft"))
                .andExpect(jsonPath("$.titulo").value("Cuthulu"))
                .andExpect(jsonPath("$.paginas").value(300));
    }
}
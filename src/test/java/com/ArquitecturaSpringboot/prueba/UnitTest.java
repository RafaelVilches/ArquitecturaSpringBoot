package com.ArquitecturaSpringboot.prueba;

import com.ArquitecturaSpringboot.prueba.Model.DTO.LibroDTO;
import com.ArquitecturaSpringboot.prueba.Model.Entities.Libro;
import com.ArquitecturaSpringboot.prueba.Repository.LibroRepository;
import com.ArquitecturaSpringboot.prueba.Services.LibroService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UnitTest {

	@Mock
	private LibroRepository libroRepository;

	@InjectMocks
	private LibroService libroService;

	private AutoCloseable mocks;

	@BeforeEach
	void setUp() {
		mocks = MockitoAnnotations.openMocks(this);
	}
	@AfterEach
	void tearDown() throws Exception {
		mocks.close();
	}

	@Test
	void crearLibroExitosamente() {
		// Arrange
		LibroDTO dto = new LibroDTO().autor("Cervantes").titulo("Quijote").paginas(500);
		Libro libro = new Libro(1L, "Quijote", "Cervantes", 500);
		when(libroRepository.save(any(Libro.class))).thenReturn(libro);

		// Act
		Libro resultado = libroService.createLibro(dto);
		ArgumentCaptor<Libro> captor = ArgumentCaptor.forClass(Libro.class);

		// Assert
		verify(libroRepository).save(captor.capture());
		assertAll(
				()-> assertNotNull(captor.getValue()),
				() -> assertEquals("Quijote", captor.getValue().getTitulo())
		);

	}

	@Test
	void errorCrearLibroInvalido() {
		// Arrange
		LibroDTO dto = new LibroDTO().paginas(200).titulo("El principito").autor("");

		// Act & Assert
		assertThrows(IllegalArgumentException.class, () -> libroService.createLibro(dto));
		verify(libroRepository, never()).save(any());
	}

	@Test
	void buscarPorAutorDelegadoAlRepositorio() {
		// Arrange
		String autor = "martin";
		when(libroRepository.findByAutor(autor)).thenReturn(List.of());

		// Act
		libroService.searchLibroByAutor(autor);

		// Assert
		verify(libroRepository).findByAutor(autor);
	}



}

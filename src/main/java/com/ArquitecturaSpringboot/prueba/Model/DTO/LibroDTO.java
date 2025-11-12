package com.ArquitecturaSpringboot.prueba.Model.DTO;

import lombok.Data;

@Data
public class LibroDTO {
    String titulo;
    String autor;
    int paginas;
    public LibroDTO titulo(String titulo)
    {
        this.titulo = titulo;
        return this;
    }
    public LibroDTO autor(String autor)
    {
        this.autor = autor;
        return this;
    }
    public LibroDTO paginas(int paginas)
    {
        this.paginas = paginas;
        return this;
    }
}

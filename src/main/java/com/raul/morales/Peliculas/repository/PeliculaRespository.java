package com.raul.morales.Peliculas.repository;

import com.raul.morales.Peliculas.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRespository extends JpaRepository<Pelicula, Long> {
}


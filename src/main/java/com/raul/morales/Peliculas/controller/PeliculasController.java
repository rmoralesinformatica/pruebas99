package com.raul.morales.Peliculas.controller;

import com.raul.morales.Peliculas.model.Pelicula;
import com.raul.morales.Peliculas.repository.PeliculaRespository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PeliculasController {

    PeliculaRespository peliculaRespository;

    public PeliculasController(PeliculaRespository peliculaRepository) {
        this.peliculaRespository = peliculaRepository;
    }

    @GetMapping("api/crearPeliculas")
    public void crearPelicula(){
        Pelicula pelicula1 = new Pelicula("Titanic","James Cameron","drama");
        peliculaRespository.save(pelicula1);
        Pelicula pelicula2 = new Pelicula("Diablos","Jhon Martir","terror");
        peliculaRespository.save(pelicula2);
        Pelicula pelicula3 = new Pelicula("Spiderman","Lucas Diaz","Ciencia Ficcion");
        peliculaRespository.save(pelicula3);
    }
    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/api/peliculas")
    public List<Pelicula> peliculas(){
        return peliculaRespository.findAll();
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/api/pelicula/{id}")
    public ResponseEntity<Pelicula> obtenerPeliculas(@PathVariable Long id){
        Optional<Pelicula> obtenerPeliculas = peliculaRespository.findById(id);
        return obtenerPeliculas.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @CrossOrigin("http://127.0.0.1:5500")
    @PostMapping("/api/peliculas")
    public ResponseEntity<Pelicula> guardarPelicula(@RequestBody Pelicula pelicula){
        Pelicula save = peliculaRespository.save(pelicula);

        return ResponseEntity.ok(save);
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @PutMapping("/api/peliculas")
    public ResponseEntity<Pelicula> actualizarPelicula(@RequestBody Pelicula pelicula) {
        if (pelicula.getId() == null || !peliculaRespository.existsById(pelicula.getId())) {
            return ResponseEntity.badRequest().build();
        }

        Pelicula save = peliculaRespository.save(pelicula);

        return ResponseEntity.ok(save);
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @DeleteMapping("/api/pelicula/{id}")
    public ResponseEntity<Pelicula> borrarPelicula(@PathVariable Long  id) {
        if (id == null || !peliculaRespository.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }

            peliculaRespository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}

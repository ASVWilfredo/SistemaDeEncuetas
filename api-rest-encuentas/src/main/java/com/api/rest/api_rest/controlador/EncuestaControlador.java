package com.api.rest.api_rest.controlador;

import com.api.rest.api_rest.modelo.Encuesta;
import com.api.rest.api_rest.repositorios.EncuestaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class EncuestaControlador {
    @Autowired
    private EncuestaRepositorio encuestaRepositorio;

    @GetMapping("/encuestas")
    public ResponseEntity<Iterable> listarTodasLasEncuestas() {
        return new ResponseEntity<>(encuestaRepositorio.findAll(), HttpStatus.OK);
    }

    @PostMapping("/encuestas")
    public ResponseEntity<?> crearEncuesta(@RequestBody Encuesta encuesta) {
        encuesta = encuestaRepositorio.save(encuesta);
        HttpHeaders httpHeaders = new HttpHeaders();
        URI newEncuestaUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(encuesta.getId()).toUri();
        httpHeaders.setLocation(newEncuestaUri);
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/encuestas/{encuestaId}")
    public ResponseEntity<?> obtenerEncuesta(@PathVariable Long encuestaId) {
        Optional<Encuesta> encuesta = encuestaRepositorio.findById(encuestaId);
        if(encuesta.isPresent()) {
            return new ResponseEntity<>(encuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Object) null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/encuestas/{encuestaId}")
    public ResponseEntity<?> actualizarEncuesta(@RequestBody Encuesta encuesta, @PathVariable Long encuestaId) {
        encuesta.setId(encuestaId);
        encuesta = encuestaRepositorio.save(encuesta);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/encuestas/{encuestaId}")
    public ResponseEntity<?> eliminarEncuesta(@PathVariable Long encuestaId) {
        encuestaRepositorio.deleteById(encuestaId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

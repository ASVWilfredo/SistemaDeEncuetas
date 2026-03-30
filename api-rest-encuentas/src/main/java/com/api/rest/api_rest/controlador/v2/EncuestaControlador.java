package com.api.rest.api_rest.controlador.v2;

import com.api.rest.api_rest.excepciones.ExcepcionNoEncontrada;
import com.api.rest.api_rest.modelo.Encuesta;
import com.api.rest.api_rest.repositorios.EncuestaRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController("EncuestaControladorV2")
@RequestMapping("/v2")
public class EncuestaControlador {
    @Autowired
    private EncuestaRepositorio encuestaRepositorio;

    @GetMapping("/encuestas")
    public ResponseEntity<Iterable<Encuesta>> listarTodasLasEncuestas(Pageable pageable) {
        Page<Encuesta> encuestas = encuestaRepositorio.findAll(pageable);
        return new ResponseEntity<>(encuestas, HttpStatus.OK);
    }

    @PostMapping("/encuestas")
    public ResponseEntity<?> crearEncuesta(@Valid @RequestBody Encuesta encuesta) {
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
        verificarEncuesta(encuestaId);
        Optional<Encuesta> encuesta = encuestaRepositorio.findById(encuestaId);
        if(encuesta.isPresent()) {
            return new ResponseEntity<>(encuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Object) null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/encuestas/{encuestaId}")
    public ResponseEntity<?> actualizarEncuesta(@Valid @RequestBody Encuesta encuesta, @PathVariable Long encuestaId) {
        verificarEncuesta(encuestaId);
        encuesta.setId(encuestaId);
        encuestaRepositorio.save(encuesta);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/encuestas/{encuestaId}")
    public ResponseEntity<?> eliminarEncuesta(@PathVariable Long encuestaId) {
        verificarEncuesta(encuestaId);
        encuestaRepositorio.deleteById(encuestaId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    protected void verificarEncuesta(Long encuestaId) {
        Optional<Encuesta> encuesta = encuestaRepositorio.findById(encuestaId);
        if(!encuesta.isPresent()) {
            throw new ExcepcionNoEncontrada("Encuuesta con el ID: " + encuestaId + "  no encontrada");
        }
    }
}

package com.api.rest.api_rest.controlador.v2;

import com.api.rest.api_rest.modelo.Voto;
import com.api.rest.api_rest.repositorios.VotoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController("VotoControladorV2")
@RequestMapping("/v2")
public class VotoControlador {
    @Autowired
    private VotoRepositorio votoRepositorio;

    @PostMapping("/encuestas/{encuestaId}/votos")
    public ResponseEntity<?> crearVoto(@PathVariable Long encuestaId, @RequestBody Voto voto){
        voto = votoRepositorio.save(voto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(voto.getId()).toUri());
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/encuestas/{encuestaId}/votos")
    public Iterable<Voto> listarTodosLosVotos(@PathVariable Long encuestaId){
        return votoRepositorio.findByEncuesta(encuestaId);
    }
}

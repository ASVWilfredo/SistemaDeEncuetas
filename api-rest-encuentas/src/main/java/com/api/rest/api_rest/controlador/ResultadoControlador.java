package com.api.rest.api_rest.controlador;

import com.api.rest.api_rest.dto.OpcionCantidad;
import com.api.rest.api_rest.dto.ResultadoVoto;
import com.api.rest.api_rest.modelo.Voto;
import com.api.rest.api_rest.repositorios.VotoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ResultadoControlador {
    @Autowired
    private VotoRepositorio votoRepositorio;

    @GetMapping("/calcularResultado")
    public ResponseEntity<?> calcularResultado(@RequestParam Long encuestaId) {
        ResultadoVoto resultadoVoto = new ResultadoVoto();
        Iterable<Voto> votos = votoRepositorio.findByEncuesta(encuestaId);

        // Algoritmo para contar votos
        int totalVotos = 0;
        Map<Long, OpcionCantidad> tempMap = new HashMap<Long, OpcionCantidad>();
        for (Voto v : votos) {
            totalVotos++;
            // Obtenemos al OpcionCantidad correspondiente a esta opcion
            OpcionCantidad opcionCantidad = tempMap.get(v.getOpcion().getId());
            if(opcionCantidad == null) {
                opcionCantidad = new OpcionCantidad();
                opcionCantidad.setOpcionId(v.getOpcion().getId());
                tempMap.put(v.getOpcion().getId(), opcionCantidad);
            }
            opcionCantidad.setNumero(opcionCantidad.getNumero() + 1);
        }
        resultadoVoto.setTotalVotos(totalVotos);
        resultadoVoto.setResultados(tempMap.values());
        return new ResponseEntity<>(resultadoVoto,HttpStatus.OK);
    }
}

package com.api.rest.api_rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleError {
    private String titulo;
    private int status;
    private String detalle;
    private String detalleError;
    private long tiempoTransaccion;
    private String mensajeError;
    private Map<String, List<ErrorValidacion>> errors = new HashMap<>();
}

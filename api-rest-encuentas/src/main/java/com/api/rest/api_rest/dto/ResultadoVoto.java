package com.api.rest.api_rest.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class ResultadoVoto {
    private int totalVotos;
    private Collection<OpcionCantidad> resultados;
}

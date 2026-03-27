package com.api.rest.api_rest.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Encuesta {

    @Id
    @GeneratedValue
    @Column(name = "Encuesta_ID")
    private Long id;

    @Column(name = "pregunta")
    private String pregunta;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Encuesta_ID")
    @OrderBy
    private Set<Opcion> opciones;
}

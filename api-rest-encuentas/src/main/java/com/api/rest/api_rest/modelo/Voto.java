package com.api.rest.api_rest.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voto {
    @Id
    @GeneratedValue()
    @Column(name = "Voto_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Opcion_ID")
    private Opcion opcion;

}

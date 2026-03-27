package com.api.rest.api_rest.repositorios;

import com.api.rest.api_rest.modelo.Voto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepositorio extends CrudRepository<Voto, Long> {
    @Query(value ="select v.* from Opcion o, Voto v where o.Encuesta_ID= ?1 and v.Opcion_ID=o.Opcion_ID", nativeQuery = true)
    public Iterable<Voto> findByEncuesta(Long encuestaId);
}

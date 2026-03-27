package com.api.rest.api_rest.repositorios;

import com.api.rest.api_rest.modelo.Voto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepositorio extends CrudRepository<Voto, Long> {
}

package com.api.rest.api_rest.repositorios;

import com.api.rest.api_rest.modelo.Opcion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpcionReposiorio extends CrudRepository<Opcion, Long> {
}

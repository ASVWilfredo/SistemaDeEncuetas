package com.api.rest.api_rest.repositorios;

import com.api.rest.api_rest.modelo.Encuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EncuestaRepositorio extends JpaRepository<Encuesta, Long> {

}

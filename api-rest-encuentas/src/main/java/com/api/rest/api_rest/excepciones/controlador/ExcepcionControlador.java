package com.api.rest.api_rest.excepciones.controlador;

import com.api.rest.api_rest.dto.DetalleError;
import com.api.rest.api_rest.dto.ErrorValidacion;
import com.api.rest.api_rest.excepciones.ExcepcionNoEncontrada;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExcepcionControlador extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;
    @ExceptionHandler(ExcepcionNoEncontrada.class)
    public ResponseEntity<?> handleExcepcionNoEncontrada(ExcepcionNoEncontrada excepcion, HttpServletRequest request) {
        DetalleError detalleError = new DetalleError();
        detalleError.setTiempoTransaccion(new Date().getTime());
        detalleError.setStatus(HttpStatus.NOT_FOUND.value());
        detalleError.setTitulo("Recurso no encontrado");
        detalleError.setDetalle(excepcion.getClass().getName());
        detalleError.setMensajeError(excepcion.getMessage());
        return new ResponseEntity<>(detalleError, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        DetalleError detalleError = new DetalleError();
        detalleError.setTiempoTransaccion(new Date().getTime());
        detalleError.setStatus(HttpStatus.BAD_REQUEST.value());
        detalleError.setTitulo("Validacion fallida");
        detalleError.setDetalle("La validacion de entrada fallo");
        detalleError.setMensajeError(ex.getMessage());
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for(FieldError fieldError : fieldErrors) {
            List<ErrorValidacion> errorsValidacionLista = detalleError.getErrors().get(fieldError.getField());
            if(errorsValidacionLista == null) {
                errorsValidacionLista = new ArrayList<ErrorValidacion>();
                detalleError.getErrors().put(fieldError.getField(), errorsValidacionLista);
            }
            ErrorValidacion errorValidacion = new ErrorValidacion();
            errorValidacion.setCodigo(fieldError.getCode());
            errorValidacion.setMensaje(messageSource.getMessage(fieldError, null));
            errorsValidacionLista.add(errorValidacion);
        }
        return new ResponseEntity<>(detalleError, HttpStatus.BAD_REQUEST);
    }
}

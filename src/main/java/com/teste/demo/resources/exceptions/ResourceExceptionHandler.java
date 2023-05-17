package com.teste.demo.resources.exceptions;

import com.teste.demo.services.exceptions.DatabaseException;
import com.teste.demo.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

// UTILIZAMOS A ANOTATION @ControllerAdvice PARA INTERCEPTAR QUALQUER EXCEÇÃO QUE ACONTECER E ASSIM ESSE OBJETO PODERÁ...
// EXECUTAR UM POSSÍVEL TRATAMENTO.
@ControllerAdvice
public class ResourceExceptionHandler {

    // QUANDO COLOCAMOS ESSA ANOTATION É PARA QUE ESSE MÉTODO SEJA CAPAZ DE INTERCEPTAR A REQUISIÇÃO QUE DEU EXCEÇÃO(ERRO) E...
    // CAIR EXATAMENTE AQUI NESTE MÉTODO.
    // DENTRO DA ANOTATION COLOCAMOS O NOME DA EXCEÇÃO QUE ESTAREMOS INTERCEPTANDO.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}

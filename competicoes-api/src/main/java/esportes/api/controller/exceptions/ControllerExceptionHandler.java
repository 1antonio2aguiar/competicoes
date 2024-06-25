package esportes.api.controller.exceptions;

import jakarta.servlet.http.HttpServletRequest;

import esportes.api.service.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<esportes.api.controller.exceptions.StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
        String error = "Objeto não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        esportes.api.controller.exceptions.StandardError stError = new esportes.api.controller.exceptions.StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(stError);
    }
}

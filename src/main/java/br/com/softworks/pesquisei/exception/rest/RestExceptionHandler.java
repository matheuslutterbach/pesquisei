package br.com.softworks.pesquisei.exception.rest;

import br.com.softworks.pesquisei.exception.model.ServiceException;
import br.com.softworks.pesquisei.util.MapBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity handleCampoObrigatorioDTO(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String campo = ((FieldError) error).getField();
            String mensagem = error.getDefaultMessage();
            errors.put(campo, mensagem);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(MapBuilder.build("mensagem", errors));
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity serviceException(ServiceException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(MapBuilder.build("mensagem", ex.getMessage()));
    }
}

package br.unipar.dentiCare.controllers;

import br.unipar.dentiCare.exceptions.ApiErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
public class AplicationControlerAdvice {

        @ExceptionHandler(Exception.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ApiErrorMessage handleException(Exception ex) {
            return new ApiErrorMessage(ex.getMessage());
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ApiErrorMessage handleMethodNotValidException(MethodArgumentNotValidException ex) {

            ArrayList<String> listaErros = new ArrayList<>();

            for (FieldError error : ex.getBindingResult().getFieldErrors()) {
                listaErros.add(error.getField() + ": " + error.getDefaultMessage());
            }

            return new ApiErrorMessage(listaErros);
        }
}

package com.pet.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.pet.exception.MoedaInvalidaException;
import com.pet.model.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MoedaInvalidaException.class)
	public ResponseEntity<ErrorResponse> handleMoedaInvalidaException (MoedaInvalidaException ex){
		ErrorResponse error = new ErrorResponse(
				ex.getMessage(),
				LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value()
		);
		
		return ResponseEntity.badRequest().body(error);
	}
	
	// REDE DE PROTEÇÃO GLOBAL: Captura qualquer outro erro inesperado do sistema
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException (Exception ex){
        ErrorResponse error = new ErrorResponse(
                "Ocorreu um erro interno no servidor. Por favor, tente novamente mais tarde.",
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String mensagem = String.format(
            "O parâmetro '%s' deve ser um número válido. O valor enviado '%s' é inválido.", 
            ex.getName(), 
            ex.getValue()
        );

        ErrorResponse error = new ErrorResponse(
                mensagem,
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value()
        );
        
        return ResponseEntity.badRequest().body(error);
    }
	
}

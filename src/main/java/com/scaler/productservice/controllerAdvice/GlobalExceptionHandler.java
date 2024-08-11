package com.scaler.productservice.controllerAdvice;

import com.scaler.productservice.dtos.ExceptionDto;
import com.scaler.productservice.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> handleArithmeticException(){
        ResponseEntity<String> responseEntity=new ResponseEntity<>(
                "Try again from Controller Advice", HttpStatus.CONFLICT
        );
        return responseEntity;
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<String> handleArrayOutOfBoundException(){
        ResponseEntity<String> responseEntity=new ResponseEntity<>(
                "Array Error.Try again from Controller Advice", HttpStatus.CONFLICT
        );
        return responseEntity;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> handleRuntimeException(){
        ExceptionDto exceptionDto=new ExceptionDto();
        exceptionDto.setMessage("RunTime Error");
        exceptionDto.setSolution("Try again from Controller Advice");
        ResponseEntity<ExceptionDto> responseEntity=new ResponseEntity<>(
                exceptionDto,HttpStatus.FORBIDDEN
        );
        return responseEntity;
    }


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException exception){
        ExceptionDto exceptionDto=new ExceptionDto();
        exceptionDto.setMessage("given ProductId "+exception.getId()+" is not valid");
        exceptionDto.setSolution("Try another productId");
        ResponseEntity<ExceptionDto> responseEntity=new ResponseEntity<>(
                exceptionDto,HttpStatus.FORBIDDEN
        );
        return responseEntity;
    }
}

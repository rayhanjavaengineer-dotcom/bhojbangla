package com.bhojbangla.api;
import java.util.Map;import com.bhojbangla.auth.InvalidCredentialsException;import com.bhojbangla.order.OrderingUnavailableException;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.MethodArgumentNotValidException;import org.springframework.web.bind.annotation.*;
@RestControllerAdvice public class ApiExceptionHandler{
 @ExceptionHandler(IllegalArgumentException.class) ResponseEntity<?> badRequest(IllegalArgumentException e){return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",e.getMessage()));}
 @ExceptionHandler(InvalidCredentialsException.class) ResponseEntity<?> unauthorized(InvalidCredentialsException e){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error",e.getMessage()));}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<?> invalid(MethodArgumentNotValidException e){return ResponseEntity.badRequest().body(Map.of("error","Invalid request"));}
 @ExceptionHandler(OrderingUnavailableException.class) ResponseEntity<?> unavailable(OrderingUnavailableException e){return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Map.of("error",e.getMessage()));}
}


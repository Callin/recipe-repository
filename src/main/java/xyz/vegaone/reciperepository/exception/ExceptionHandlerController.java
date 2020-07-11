package xyz.vegaone.reciperepository.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RecipeErrorResponse> handleMethodArgumentException(MethodArgumentNotValidException exception) {
        RecipeErrorResponse recipeErrorResponse = new RecipeErrorResponse();

        recipeErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        recipeErrorResponse.setTimestamp(LocalDateTime.now());
        recipeErrorResponse.setError(
                exception.getBindingResult().getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining("; ")));

        return new ResponseEntity<>(recipeErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RecipeErrorResponse> handleConstraintViolationException(ConstraintViolationException exception) {
        RecipeErrorResponse recipeErrorResponse = new RecipeErrorResponse();

        recipeErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        recipeErrorResponse.setTimestamp(LocalDateTime.now());
        recipeErrorResponse.setError(exception.getMessage());

        return new ResponseEntity<>(recipeErrorResponse, HttpStatus.BAD_REQUEST);
    }
}

package med.voll.api.infra.security;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import med.voll.api.infra.exceptions.IntegrityValidation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BugHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404()
    {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e)
    {
        var error = e.getFieldErrors().stream().map(ErrorDataValidation::new).toList();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(IntegrityValidation.class)
    public ResponseEntity ErrorBussinessValidations(Exception e)
    {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity ErrorIntegrityValidations(Exception e)
    {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    private record ErrorDataValidation( String fiel, String error){
        public ErrorDataValidation(FieldError e)
        {
            this(e.getField(), e.getDefaultMessage());
        }
    }

}

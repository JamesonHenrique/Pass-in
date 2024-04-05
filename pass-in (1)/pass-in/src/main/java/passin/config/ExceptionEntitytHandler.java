package passin.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import passin.domain.event.exception.EventNotFoundException;

@ControllerAdvice

public class ExceptionEntitytHandler {
    public ResponseEntity<String> handleEventNotFoundException(
            EventNotFoundException e ){
        return ResponseEntity.notFound().build();
    }
}

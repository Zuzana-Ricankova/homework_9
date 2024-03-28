package homework_9.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class HttpStatusException extends RuntimeException{
    public HttpStatusException(String message) {
        super(message);
    }
}

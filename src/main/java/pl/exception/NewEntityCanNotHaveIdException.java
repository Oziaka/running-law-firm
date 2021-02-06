package pl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NewEntityCanNotHaveIdException extends RuntimeException {
   public NewEntityCanNotHaveIdException(String message) {
      super(message);
   }
}

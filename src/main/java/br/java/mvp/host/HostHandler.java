package br.java.mvp.host;

import br.java.mvp.application.service.exception.BusinessException;
import br.java.mvp.application.service.exception.NoContentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class HostHandler {

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<MessageError> handleNoContentException(final NoContentException ex, final WebRequest request) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<MessageError> handleBusinessException(final BusinessException ex, final WebRequest request) {
        return new ResponseEntity<>(getMessage(ex, String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value())), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageError> handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex, final WebRequest request) {
        return new ResponseEntity<>(getMessage(ex, String.valueOf(HttpStatus.BAD_REQUEST.value())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageError> handleException(final Exception ex, final WebRequest request) {
        return new ResponseEntity<>(getMessage(ex, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private MessageError getMessage(Exception e, String codigo) {
        MessageError error = new MessageError();
        error.setCodigo(codigo);
        error.setMensagem(e.getMessage());
        return error;
    }
}

package br.com.zupacademy.joao.mercadolivre.config.exceptionhandler;

import br.com.zupacademy.joao.mercadolivre.config.exceptionhandler.dto.response.ErroResponse;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionValidationHandler {

    private MessageSource message;

    public ExceptionValidationHandler(MessageSource message) {
        this.message = message;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public List<?> handler(MethodArgumentNotValidException exception) {
        List<ErroResponse> erroResponse = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(erro -> {
            erroResponse.add(new ErroResponse(erro.getField(), message.getMessage(erro, LocaleContextHolder.getLocale())));
        });

        return erroResponse;
    }
}

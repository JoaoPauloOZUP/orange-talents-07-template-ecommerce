package br.com.zupacademy.joao.mercadolivre.validator.uniquevalue.caracteristica;

import br.com.zupacademy.joao.mercadolivre.controller.dto.request.ProdutoRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UniqueCaracteristica implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProdutoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        ProdutoRequest request = (ProdutoRequest) target;

        int indiceDaCaracteristicaRepetida = request.caracteristicasRepetidas();

        if(indiceDaCaracteristicaRepetida != -1) {
            String nomeDaCaracteristica = request.getCaracteristicasRequest().get(indiceDaCaracteristicaRepetida).getNome();
            errors.rejectValue("caracteristicasRequest", null,
                    "Nome: "+nomeDaCaracteristica+", está repetido");
        }
    }
}

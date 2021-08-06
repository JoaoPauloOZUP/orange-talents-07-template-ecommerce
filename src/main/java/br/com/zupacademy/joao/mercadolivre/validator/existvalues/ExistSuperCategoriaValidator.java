package br.com.zupacademy.joao.mercadolivre.validator.existvalues;

import br.com.zupacademy.joao.mercadolivre.model.Categoria;
import br.com.zupacademy.joao.mercadolivre.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ExistSuperCategoriaValidator implements ConstraintValidator<ExistSuperCategoriaValue, Long> {

    @Autowired
    private CategoriaRepository repository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) {
            return true;
        }

        Optional<Categoria> superCategoriaOpt =  repository.findById(value);

        return !superCategoriaOpt.isEmpty();
    }
}

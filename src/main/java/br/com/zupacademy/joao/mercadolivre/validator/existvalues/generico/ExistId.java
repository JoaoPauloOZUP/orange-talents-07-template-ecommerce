package br.com.zupacademy.joao.mercadolivre.validator.existvalues.generico;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistId implements ConstraintValidator<ExistIdValue, Long> {

    @PersistenceContext
    private EntityManager manager;

    private String field;

    private Class<?> klass;

    @Override
    public void initialize(ExistIdValue constraintAnnotation) {
        field = constraintAnnotation.fieldName();
        klass = constraintAnnotation.dommainClass();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager.createQuery("SELECT 1 FROM "+klass.getName()+" WHERE "+field+" = :value");
        query.setParameter("value", value);

        return !query.getResultList().isEmpty();
    }
}

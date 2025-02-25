package br.com.zupacademy.joao.mercadolivre.validator.uniquevalue.generico;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    @PersistenceContext
    private EntityManager manager;

    private String field;
    private Class<?> klass;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        field = constraintAnnotation.fieldName();
        klass = constraintAnnotation.dommainClass();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager.createQuery("SELECT 1 FROM "+klass.getName()+" WHERE "+field+" = :value");
        query.setParameter("value", o);

        return query.getResultList().isEmpty();
    }
}

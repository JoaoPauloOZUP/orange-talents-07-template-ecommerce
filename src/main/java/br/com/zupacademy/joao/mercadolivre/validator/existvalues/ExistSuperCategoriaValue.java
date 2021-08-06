package br.com.zupacademy.joao.mercadolivre.validator.existvalues;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistSuperCategoriaValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistSuperCategoriaValue {
    String message() default "A super categoria informada não existe";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

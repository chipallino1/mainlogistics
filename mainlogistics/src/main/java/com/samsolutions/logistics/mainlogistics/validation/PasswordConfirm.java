package com.samsolutions.logistics.mainlogistics.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Custom annotation for check if password == passwordRepeat
 */


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordConfirmValidator.class)
public @interface PasswordConfirm {

    String password();

    String confirmPassword();

    String message() default "Passwords must be same";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

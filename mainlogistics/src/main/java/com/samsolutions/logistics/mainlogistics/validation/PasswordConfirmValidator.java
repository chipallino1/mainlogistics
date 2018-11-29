package com.samsolutions.logistics.mainlogistics.validation;


import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Implementation pf annotation
 */
public class PasswordConfirmValidator implements ConstraintValidator<PasswordConfirm, Object> {

    private String password;
    private String passwordConfirm;

    @Override
    public void initialize(PasswordConfirm constraintAnnotation) {
        password = constraintAnnotation.password();
        passwordConfirm = constraintAnnotation.confirmPassword();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Object objPass = BeanUtils.getProperty(value, password);
            Object objPassConfirm = BeanUtils.getProperty(value, passwordConfirm);

            boolean isValid = (objPass == null && objPassConfirm == null) || (objPass != null && objPass.equals(objPassConfirm));

            if (!isValid) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addPropertyNode(passwordConfirm).addConstraintViolation();
            }
            return isValid;
        } catch (Exception ex) {

        }
        return false;
    }
}

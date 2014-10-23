package sptest.web;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import sptest.domain.service.AuthRequest;
import sptest.domain.service.RegisterRequest;

public class AuthRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(AuthRequest.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "id", "required");
        ValidationUtils.rejectIfEmpty(errors, "password", "required");
    }
}

package sptest.web;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import sptest.domain.model.Member;
import sptest.domain.service.RegisterRequest;

public class RegisterRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(RegisterRequest.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "id", "required");
        ValidationUtils.rejectIfEmpty(errors, "password", "required");
        ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
        ValidationUtils.rejectIfEmpty(errors, "name", "required");
        RegisterRequest regReq = (RegisterRequest) o;
        if (regReq.hasConfirmPassword() && !regReq.matchPassword()) {
            errors.rejectValue("confirmPassword", "notMath");
        }
    }
}

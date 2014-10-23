package sptest.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sptest.domain.model.Member;
import sptest.domain.service.DuplicateIdException;
import sptest.domain.service.MemberRegisterService;
import sptest.domain.service.RegisterRequest;

@Controller
@RequestMapping("/regist")
public class MemberRegisterController {
    private MemberRegisterService memberRegisterService;

    @ModelAttribute("member")
    public RegisterRequest formBacking() {
        return new RegisterRequest();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String registForm() {
        return "registrationForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String regist(@ModelAttribute("member") RegisterRequest member, BindingResult errors) {
        new RegisterRequestValidator().validate(member, errors);
        if (errors.hasErrors()) {
            return "registrationForm";
        }
        try {
            memberRegisterService.register(member);
            return "registered";
        } catch (DuplicateIdException ex) {
            errors.rejectValue("id", "duplicateId");
            return "registrationForm";
        }
    }

    public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
        this.memberRegisterService = memberRegisterService;
    }
}

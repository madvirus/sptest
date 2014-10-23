package sptest.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sptest.domain.service.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private AuthService authService;

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("login")
    public AuthRequest formBacking() {
        return new AuthRequest();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String form() {
        return "loginForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(@ModelAttribute("login") AuthRequest authReq, BindingResult errors, HttpServletRequest request) {
        new AuthRequestValidator().validate(authReq, errors);
        if (errors.hasErrors()) {
            return "loginForm";
        }
        try {
            Auth auth = authService.authenticate(authReq);
            request.getSession().setAttribute("auth", auth);
            return "redirect:/main";
        } catch (IdPasswordNotMatchException ex) {
            errors.reject("invalidIdOrPassword");
            return "loginForm";
        }
    }
}

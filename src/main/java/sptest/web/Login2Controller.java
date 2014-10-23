package sptest.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sptest.domain.service.Auth;
import sptest.domain.service.AuthRequest;
import sptest.domain.service.AuthService;
import sptest.domain.service.IdPasswordNotMatchException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login2")
public class Login2Controller {

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
    public String login(@ModelAttribute("login") AuthRequest authReq, BindingResult errors, HttpServletResponse response) {
        new AuthRequestValidator().validate(authReq, errors);
        if (errors.hasErrors()) {
            return "loginForm";
        }
        try {
            Auth auth = authService.authenticate(authReq);
            Cookie authCookie = new Cookie("AC", auth.getId());
            authCookie.setPath("/");
            response.addCookie(authCookie);
            return "redirect:/main";
        } catch (IdPasswordNotMatchException ex) {
            errors.reject("invalidIdOrPassword");
            return "loginForm";
        }
    }
}

package sptest.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Logout2Controller {

    @RequestMapping("/logout2")
    public String logout(HttpServletResponse response) {
        Cookie authCookie = new Cookie("AC", "");
        authCookie.setPath("/");
        authCookie.setMaxAge(0);
        response.addCookie(authCookie);

        return "redirect:/main";
    }
}

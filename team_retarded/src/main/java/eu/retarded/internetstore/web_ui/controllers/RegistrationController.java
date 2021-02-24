package eu.retarded.internetstore.web_ui.controllers;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.RegisterUserRequest;
import eu.retarded.internetstore.core.responses.user.RegisterUserResponse;
import eu.retarded.internetstore.core.services.user.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private RegisterUserService registerUserService;

    @PostMapping(value = "/register")
    public String registration(@RequestParam(value = "username") String username,
                               @RequestParam(value = "name") String name,
                               @RequestParam(value = "surname") String surname,
                               @RequestParam(value = "email") String email,
                               @RequestParam(value = "password1") String password1,
                               @RequestParam(value = "password2") String password2) {

        @Valid


        RegisterUserRequest registerUserRequest = new RegisterUserRequest(username, password1, password2, name, surname, email);
        RegisterUserResponse registerUserResponse = registerUserService.execute(registerUserRequest);
        if (registerUserResponse.hasErrors() || registerUserResponse.getUser() == null) {
            return "redirect:/authorization?error";
        }
        User user = registerUserResponse.getUser();
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/";
    }
}

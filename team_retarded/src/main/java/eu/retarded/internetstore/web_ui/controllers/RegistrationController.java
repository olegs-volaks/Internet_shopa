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

@Controller
public class RegistrationController {

    @Autowired
    private RegisterUserService registerUserService;

    @PostMapping(value = "/register")
    public String registration(@RequestParam(value = "username", required = false) String username,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "surname", required = false) String surname,
                               @RequestParam(value = "email", required = false) String email,
                               @RequestParam(value = "password1", required = false) String password1,
                               @RequestParam(value = "password2", required = false) String password2) {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest(username, password1, name, surname, email);
        RegisterUserResponse registerUserResponse = registerUserService.execute(registerUserRequest);
        User user = registerUserResponse.getUser();
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/";
    }
}

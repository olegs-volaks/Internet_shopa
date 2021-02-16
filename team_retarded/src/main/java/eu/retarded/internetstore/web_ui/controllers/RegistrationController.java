package eu.retarded.internetstore.web_ui.controllers;

import eu.retarded.internetstore.core.requests.user.AddUserRequest;
import eu.retarded.internetstore.core.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public String registration(@RequestParam(value = "username", required = false) String username,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "surname", required = false) String surname,
                               @RequestParam(value = "email", required = false) String email,
                               @RequestParam(value = "password1", required = false) String password1,
                               @RequestParam(value = "password2", required = false) String password2) {
        AddUserRequest addUserRequest = new AddUserRequest(username, password1, name, surname, email);
        userService.addUser(addUserRequest);
        return "redirect:/";
    }
}

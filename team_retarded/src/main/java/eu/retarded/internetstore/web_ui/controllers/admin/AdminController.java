package eu.retarded.internetstore.web_ui.controllers.admin;

import eu.retarded.internetstore.core.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String main(@AuthenticationPrincipal User activeUser,
                       ModelMap modelMap) {
        return "admin/index";
    }
}

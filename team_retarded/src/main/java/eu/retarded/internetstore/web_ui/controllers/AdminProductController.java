package eu.retarded.internetstore.web_ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminProductController {

    @GetMapping("/admin/product")
    public String main(ModelMap modelMap) {
        return "/admin/product";
    }
}

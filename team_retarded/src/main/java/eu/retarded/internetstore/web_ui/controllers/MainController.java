package eu.retarded.internetstore.web_ui.controllers;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.product.ShowAllProductsRequest;
import eu.retarded.internetstore.core.services.product.ShowAllProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private ShowAllProductsService showAllProductsService;

    @GetMapping(value = "/")
    public String main(@RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "logout", required = false) String logout,
                       @AuthenticationPrincipal User activeUser,
                       ModelMap modelMap) {
        boolean isLogged = activeUser != null;
        boolean isActiveUserAdmin = false;
        if (isLogged) {
            isActiveUserAdmin = activeUser.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
        }
        Page<Product> products = showAllProductsService.execute(new ShowAllProductsRequest()).getProducts();
        modelMap.addAttribute("products", products);
        modelMap.addAttribute("error", error != null);
        modelMap.addAttribute("logout", logout != null);
        modelMap.addAttribute("active_user", activeUser);
        modelMap.addAttribute("is_logged", isLogged);
        modelMap.addAttribute("is_admin", isActiveUserAdmin);
        return "index";
    }
}

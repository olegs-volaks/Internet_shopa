package eu.retarded.internetstore.web_ui.controllers.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.product.GetProductByIdRequest;
import eu.retarded.internetstore.core.services.product.GetProductByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {

    @Autowired
    private GetProductByIdService getProductByIdService;

    @GetMapping("/product/{id}")
    public String main(@PathVariable(name = "id") String id,
                       @AuthenticationPrincipal User activeUser,
                       ModelMap modelMap) {
        long idLong = Long.parseLong(id);
        boolean isLogged = activeUser != null;
        boolean isActiveUserAdmin = false;
        int productInCart = 0;
        if (isLogged) {
            isActiveUserAdmin = activeUser.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
            productInCart = activeUser.getCart().getProducts().size();
        }
        Product product = getProductByIdService.execute(new GetProductByIdRequest(idLong)).getProduct();
        modelMap.addAttribute("product", product);
        modelMap.addAttribute("active_user", activeUser);
        modelMap.addAttribute("is_logged", isLogged);
        modelMap.addAttribute("is_admin", isActiveUserAdmin);
        return "product/index";
    }
}

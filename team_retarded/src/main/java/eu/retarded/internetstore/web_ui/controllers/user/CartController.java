package eu.retarded.internetstore.web_ui.controllers.user;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.cart.GetProductInCartRequest;
import eu.retarded.internetstore.core.services.cart.GetProductInCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
public class CartController {

    @Autowired
    private GetProductInCartService getProductInCartService;

    @GetMapping("/user/cart/{userId}")
    public String main(@PathVariable(name = "userId") String userId,
                       @AuthenticationPrincipal User activeUser,
                       ModelMap modelMap) {
        long userIdLong = Long.parseLong(userId);
        if (userIdLong != activeUser.getId()) {
            return "redirect:/user/cart/" + activeUser.getId();
        }
        boolean isActiveUserAdmin = activeUser.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
        Map<Product, Integer> productMap = getProductInCartService.execute(new GetProductInCartRequest(activeUser.getCart().getId())).getProducts();
        int productInCartCount = productMap.size();

        modelMap.addAttribute("active_user", activeUser);
        modelMap.addAttribute("is_logged", true);
        modelMap.addAttribute("is_admin", isActiveUserAdmin);
        modelMap.addAttribute("product_in_cart_count", productInCartCount);
        modelMap.addAttribute("product_map", productMap);

        return "/user/cart";
    }

    @GetMapping("/user/cart")
    public String redirect(@AuthenticationPrincipal User activeUser, ModelMap modelMap) {
        return "redirect:/user/cart/" + activeUser.getId();
    }
}

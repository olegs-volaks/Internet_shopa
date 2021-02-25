package eu.retarded.internetstore.web_ui.controllers.user;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.cart.GetProductInCartRequest;
import eu.retarded.internetstore.core.requests.user.DeleteProductFromUserCartRequest;
import eu.retarded.internetstore.core.requests.user.UpdateCountInUsersCartRequest;
import eu.retarded.internetstore.core.services.cart.GetProductInCartService;
import eu.retarded.internetstore.core.services.user.DeleteProductFromUserCartService;
import eu.retarded.internetstore.core.services.user.UpdateCountInUsersCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class CartController {

    @Autowired
    private GetProductInCartService getProductInCartService;

    @Autowired
    private UpdateCountInUsersCartService updateCountInUsersCartService;

    @Autowired
    private DeleteProductFromUserCartService deleteProductFromUserCartService;

    @GetMapping("/user/cart")
    public String main(@AuthenticationPrincipal User activeUser,
                       ModelMap modelMap) {
        boolean isActiveUserAdmin = activeUser.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
        Map<Product, Integer> productMap = getProductInCartService.execute(new GetProductInCartRequest(activeUser.getCart().getId())).getProducts();
        int productInCartCount = productMap.size();

        modelMap.addAttribute("active_user", activeUser);
        modelMap.addAttribute("is_logged", true);
        modelMap.addAttribute("is_admin", isActiveUserAdmin);
        modelMap.addAttribute("product_in_cart_count", productInCartCount);
        modelMap.addAttribute("product_map", productMap);
        modelMap.addAttribute("total_price", Cart.getPrice(productMap));

        return "/user/cart";
    }

    @PostMapping("/user/cart/change")
    public String changeCount(@RequestParam(name = "user_id") long userId,
                              @RequestParam(name = "product_id") long product_id,
                              @RequestParam(name = "count") int count) {
        updateCountInUsersCartService.execute(new UpdateCountInUsersCartRequest(userId, product_id, count));
        return "redirect:/user/cart/";
    }

    @PostMapping("/user/cart/remove")
    public String removeProductFormCart(@RequestParam(name = "user_id") long userId,
                                        @RequestParam(name = "product_id") long product_id) {
        deleteProductFromUserCartService.execute(new DeleteProductFromUserCartRequest(userId, product_id));
        return "redirect:/user/cart/";
    }
}

package eu.retarded.internetstore.web_ui.controllers.user.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.cart.GetProductInCartRequest;
import eu.retarded.internetstore.core.requests.order.GetByIdOrderRequest;
import eu.retarded.internetstore.core.services.cart.GetProductInCartService;
import eu.retarded.internetstore.core.services.order.GetByIdOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
public class OrderInfoController {

    @Autowired
    private GetProductInCartService getProductInCartService;

    @Autowired
    private GetByIdOrderService getByIdOrderService;

    @GetMapping("/user/order/info/{id}")
    public String main(@PathVariable String id,
                       @AuthenticationPrincipal User activeUser,
                       ModelMap modelMap) {
        long idLong = Long.parseLong(id);
        boolean isActiveUserAdmin = activeUser.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
        Map<Product, Integer> productMap = getProductInCartService.execute(new GetProductInCartRequest(activeUser.getCart().getId())).getProducts();
        int productInCartCount = productMap.size();
        Order order = getByIdOrderService.execute(new GetByIdOrderRequest(idLong)).getOrder();
        Map<Product, Integer> productInOrder = getProductInCartService.execute(new GetProductInCartRequest(order.getCart().getId())).getProducts();
        modelMap.addAttribute("active_user", activeUser);
        modelMap.addAttribute("is_logged", true);
        modelMap.addAttribute("is_admin", isActiveUserAdmin);
        modelMap.addAttribute("product_in_cart_count", productInCartCount);
        modelMap.addAttribute("order", order);
        modelMap.addAttribute("product_in_order", productInOrder);

        return "/user/order/info";
    }
}

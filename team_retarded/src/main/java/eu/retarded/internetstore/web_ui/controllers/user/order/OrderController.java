package eu.retarded.internetstore.web_ui.controllers.user.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.cart.GetProductInCartRequest;
import eu.retarded.internetstore.core.requests.order.GetActiveOrderListRequest;
import eu.retarded.internetstore.core.requests.order.GetClosedOrderListRequest;
import eu.retarded.internetstore.core.services.cart.GetProductInCartService;
import eu.retarded.internetstore.core.services.order.GetActiveOrderListService;
import eu.retarded.internetstore.core.services.order.GetClosedOrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    private GetActiveOrderListService getActiveOrderListService;

    @Autowired
    private GetClosedOrderListService getClosedOrderListService;

    @Autowired
    private GetProductInCartService getProductInCartService;

    @GetMapping("/user/order")
    public String main(@RequestParam(name = "success", required = false) String success,
                       @AuthenticationPrincipal User activeUser,
                       ModelMap modelMap) {
        boolean isActiveUserAdmin = activeUser.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
        Map<Product, Integer> productMap = getProductInCartService.execute(new GetProductInCartRequest(activeUser.getCart().getId())).getProducts();
        int productInCartCount = productMap.size();

        List<Order> activeOrders = getActiveOrderListService.execute(new GetActiveOrderListRequest(activeUser.getId())).getOrdersList();
        List<Order> closedOrders = getClosedOrderListService.execute(new GetClosedOrderListRequest(activeUser.getId())).getOrdersList();

        modelMap.addAttribute("active_user", activeUser);
        modelMap.addAttribute("is_logged", true);
        modelMap.addAttribute("is_admin", isActiveUserAdmin);
        modelMap.addAttribute("product_in_cart_count", productInCartCount);
        modelMap.addAttribute("active_orders", activeOrders);
        modelMap.addAttribute("closed_orders", closedOrders);
        modelMap.addAttribute("success", success);

        return "/user/order/index";
    }

}

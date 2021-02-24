package eu.retarded.internetstore.web_ui.controllers.user.order;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.cart.GetProductInCartRequest;
import eu.retarded.internetstore.core.requests.delivery.GetDeliveryListRequest;
import eu.retarded.internetstore.core.requests.order.AddOrderRequest;
import eu.retarded.internetstore.core.responses.order.AddOrderResponse;
import eu.retarded.internetstore.core.services.cart.GetProductInCartService;
import eu.retarded.internetstore.core.services.delivery.GetDeliveryListService;
import eu.retarded.internetstore.core.services.order.AddOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class ToOrderController {

    @Autowired
    private GetProductInCartService getProductInCartService;

    @Autowired
    private AddOrderService addOrderService;

    @Autowired
    private GetDeliveryListService getDeliveryListService;

    @GetMapping("/user/order/toOrder")
    public String main(@AuthenticationPrincipal User activeUser,
                       ModelMap modelMap) {
        boolean isActiveUserAdmin = activeUser.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
        Map<Product, Integer> productMap = getProductInCartService.execute(new GetProductInCartRequest(activeUser.getCart().getId())).getProducts();
        int productInCartCount = productMap.size();
        List<Delivery> deliveries = getDeliveryListService.execute(new GetDeliveryListRequest()).getDeliveriesList();

        modelMap.addAttribute("active_user", activeUser);
        modelMap.addAttribute("is_logged", true);
        modelMap.addAttribute("is_admin", isActiveUserAdmin);
        modelMap.addAttribute("product_in_cart_count", productInCartCount);
        modelMap.addAttribute("product_map", productMap);
        modelMap.addAttribute("total_price", Cart.getPrice(productMap));
        modelMap.addAttribute("deliveries", deliveries);

        return "/user/order/toOrder";
    }

    @PostMapping("/user/order/add")
    public String addOrder(@RequestParam(name = "client_name") String clientName,
                           @RequestParam(name = "client_surname") String clientSurname,
                           @RequestParam(name = "client_address") String clientAddress,
                           @RequestParam(name = "delivery-id") long deliveryId,
                           @RequestParam(name = "user_id") long userId) {
        AddOrderRequest addOrderRequest = new AddOrderRequest(clientName, clientSurname, clientAddress, deliveryId, userId);
        AddOrderResponse addOrderResponse = addOrderService.execute(addOrderRequest);
        return "redirect:/user/order?success=" + addOrderResponse.getOrder().getId();
    }
}

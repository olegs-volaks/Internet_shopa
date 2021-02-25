package eu.retarded.internetstore.web_ui.controllers.admin.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.cart.GetProductInCartRequest;
import eu.retarded.internetstore.core.requests.order.GetByIdOrderRequest;
import eu.retarded.internetstore.core.requests.order.UpdateOrderStatusRequest;
import eu.retarded.internetstore.core.services.cart.GetProductInCartService;
import eu.retarded.internetstore.core.services.order.GetByIdOrderService;
import eu.retarded.internetstore.core.services.order.UpdateOrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AdminInfoOrderController {

    @Autowired
    private GetByIdOrderService getByIdOrderService;

    @Autowired
    private GetProductInCartService getProductInCartService;

    @Autowired
    private UpdateOrderStatusService updateOrderStatusService;

    @GetMapping("/admin/order/info/{id}")
    public String main(@PathVariable String id, ModelMap modelMap) {
        long idLong = Integer.parseInt(id);
        Order order = getByIdOrderService.execute(new GetByIdOrderRequest(idLong)).getOrder();
        Map<Product, Integer> productInOrder = getProductInCartService.execute(new GetProductInCartRequest(order.getCart().getId())).getProducts();
        modelMap.addAttribute("order", order);
        modelMap.addAttribute("product_in_order", productInOrder);
        return "/admin/order/info";
    }

    @PostMapping("/admin/order/save")
    public String editProduct(@RequestParam(value = "id") long id,
                              @RequestParam(value = "order_status") int status) {
        updateOrderStatusService.execute(new UpdateOrderStatusRequest(id, status));
        return "redirect:/admin/order/info/" + id;
    }
}

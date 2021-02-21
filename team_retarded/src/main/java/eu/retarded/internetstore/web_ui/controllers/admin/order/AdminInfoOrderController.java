package eu.retarded.internetstore.web_ui.controllers.admin.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.requests.order.GetByIdOrderRequest;
import eu.retarded.internetstore.core.services.order.GetByIdOrderService;
import eu.retarded.internetstore.core.services.order.UpdateOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminInfoOrderController {

    @Autowired
    private GetByIdOrderService getByIdOrderService;

    @Autowired
    private UpdateOrderService updateOrderService;

    @GetMapping("/admin/order/info/{id}")
    public String main(@PathVariable String id, ModelMap modelMap) {
        long idLong = Integer.parseInt(id);
        Order order = getByIdOrderService.execute(new GetByIdOrderRequest(idLong)).getOrder();
        modelMap.addAttribute("order", order);
        return "/admin/order/info";
    }

    @PostMapping("/admin/order/save")
    public String infoOrder() {
        return "redirect:/admin/order/info/"; // + id; ???
    }
}

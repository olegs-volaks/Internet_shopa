package eu.retarded.internetstore.web_ui.controllers.admin.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.requests.order.GetByIdOrderRequest;
import eu.retarded.internetstore.core.responses.order.GetByIdOrderResponse;
import eu.retarded.internetstore.core.services.order.GetByIdOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminInfoOrderController {

    @Autowired
    private GetByIdOrderService getByIdOrderService;

    @GetMapping("/admin/order/info/{id}")
    public String main(@PathVariable String id, @RequestParam(name = "error", required = false) String error,
                       ModelMap modelMap) {
        long idLong = Integer.parseInt(id);
        Order order = getByIdOrderService.execute(new GetByIdOrderRequest(idLong)).getOrder();
        modelMap.addAttribute("error", error != null);
        modelMap.addAttribute("order", order);
        GetByIdOrderRequest getByIdOrderRequest = new GetByIdOrderRequest(idLong);
        GetByIdOrderResponse getByIdOrderResponse = getByIdOrderService.execute(getByIdOrderRequest);
        if (getByIdOrderResponse.hasErrors()) {
            return "redirect:/admin/order/1?error";
        }
        return "/admin/order/info";
    }
}

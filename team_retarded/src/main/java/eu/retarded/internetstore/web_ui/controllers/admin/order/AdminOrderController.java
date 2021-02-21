package eu.retarded.internetstore.web_ui.controllers.admin.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.requests.order.GetOrderListRequest;
import eu.retarded.internetstore.core.services.order.GetOrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminOrderController {

    @Autowired
    private GetOrderListService getOrderListService;

    @Value("${admin.page-size}")
    private int pageSize;

    @GetMapping("/admin/order/{page}")
    public String orderPage(@PathVariable String page, ModelMap modelMap) {
        int pageInt = Integer.parseInt(page);
        Page<Order> orderPage = getOrderListService.execute(new GetOrderListRequest(
                PageRequest.of(pageInt - 1, pageSize))).getOrdersPage();
        int totalPages = orderPage.getTotalPages();
        if (totalPages < 1) {
            totalPages = 1;
        }
        modelMap.addAttribute("orders", orderPage);
        modelMap.addAttribute("total_pages", totalPages);
        modelMap.addAttribute("current_page", pageInt);
        return "/admin/order/index";
    }


    @GetMapping("/admin/order")
    public String main(ModelMap modelMap) {
        return "redirect:/admin/order/1";
    }
}

package eu.retarded.internetstore.web_ui.controllers.admin.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.requests.delivery.AddDeliveryRequest;
import eu.retarded.internetstore.core.requests.delivery.GetDeliveryListRequest;
import eu.retarded.internetstore.core.responses.delivery.AddDeliveryResponse;
import eu.retarded.internetstore.core.services.delivery.AddDeliveryService;
import eu.retarded.internetstore.core.services.delivery.GetDeliveryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminDeliveryController {

    @Autowired
    private AddDeliveryService addDeliveryService;

    @Autowired
    private GetDeliveryListService getDeliveryListService;

    @Value("${admin.page-size}")
    private int pageSize;

    @GetMapping("/admin/delivery/{page}")
    public String deliveryPage(@PathVariable String page, @RequestParam(name = "error", required = false) String error,
                               ModelMap modelMap) {  // productPage
        int pageInt = Integer.parseInt(page);
        Page<Delivery> deliveryPage = getDeliveryListService.execute(new GetDeliveryListRequest(
                PageRequest.of(pageInt - 1, pageSize))).getDeliveriesPage();
        int totalPages = deliveryPage.getTotalPages();
        if (totalPages < 1) {
            totalPages = 1;
        }
        modelMap.addAttribute("error", error != null);
        modelMap.addAttribute("deliveries", deliveryPage); // products
        modelMap.addAttribute("total_pages", totalPages);
        modelMap.addAttribute("current_page", pageInt);
        return "/admin/delivery/index";
    }

    @GetMapping("/admin/delivery")
    public String main(ModelMap modelMap) {
        return "redirect:/admin/delivery/1";
    }

    @PostMapping("/admin/delivery/add")
    public String addDelivery(@RequestParam(value = "title") String title,
                              @RequestParam(value = "region") String region,
                              @RequestParam(value = "price") double price ) {
        AddDeliveryRequest addDeliveryRequest = new AddDeliveryRequest(title,region,price);
        AddDeliveryResponse addDeliveryResponse = addDeliveryService.execute(addDeliveryRequest);
        if (addDeliveryResponse.hasErrors()) {
            return "redirect:/admin/delivery/1?error";
        }
        return "redirect:/admin/delivery";
    }
}


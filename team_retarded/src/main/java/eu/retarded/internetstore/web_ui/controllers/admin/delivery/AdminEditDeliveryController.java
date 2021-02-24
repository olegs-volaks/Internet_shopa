package eu.retarded.internetstore.web_ui.controllers.admin.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.requests.delivery.GetByIdDeliveryRequest;
import eu.retarded.internetstore.core.requests.delivery.UpdateDeliveryRequest;
import eu.retarded.internetstore.core.responses.delivery.UpdateDeliveryResponse;
import eu.retarded.internetstore.core.services.delivery.GetByIdDeliveryService;
import eu.retarded.internetstore.core.services.delivery.UpdateDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class AdminEditDeliveryController {

    @Autowired
    private GetByIdDeliveryService getByIdDeliveryService;

    @Autowired
    private UpdateDeliveryService updateDeliveryService;


    @GetMapping("/admin/delivery/edit/{id}")
    public String main(@PathVariable String id,@RequestParam(name = "error", required = false) String error,
                       ModelMap modelMap) {
        long idLong = Integer.parseInt(id);
        Delivery delivery = getByIdDeliveryService.execute(new GetByIdDeliveryRequest(idLong)).getDelivery();
        modelMap.addAttribute("error", error != null);
        modelMap.addAttribute("delivery", delivery);
        return "/admin/delivery/edit";
    }

    @PostMapping("/admin/delivery/save")
    public String editDelivery(@RequestParam(value = "id") long id,
                               @RequestParam(value = "title") String title,
                               @RequestParam(value = "price") double price,
                               @RequestParam(value = "region") String region) {
        UpdateDeliveryRequest updateDeliveryRequest = new UpdateDeliveryRequest(id, title,region,price);
        UpdateDeliveryResponse updateDeliveryResponse = updateDeliveryService.execute(updateDeliveryRequest);
        if (updateDeliveryResponse.hasErrors()) {
            return "redirect:/admin/delivery/edit/"+id+"?error";
        }
        return "redirect:/admin/delivery/edit/" + id;
    }
}


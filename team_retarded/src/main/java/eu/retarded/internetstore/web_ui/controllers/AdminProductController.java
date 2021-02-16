package eu.retarded.internetstore.web_ui.controllers;

import eu.retarded.internetstore.core.requests.product.AddProductRequest;
import eu.retarded.internetstore.core.services.product.AddProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminProductController {

    @Autowired
    private AddProductService addProductService;

    @GetMapping("/admin/product")
    public String main(ModelMap modelMap) {
        return "/admin/product";
    }

    @PostMapping("/admin/product/add")
    public String addProduct(@RequestParam(value = "name") String name,
                             @RequestParam(value = "description") String description,
                             @RequestParam(value = "price") double price,
                             @RequestParam(value = "count", required = false) int count) {
        AddProductRequest addProductRequest = new AddProductRequest(name, description, price, count);
        addProductService.execute(addProductRequest);
        return "redirect:/admin/product";
    }
}

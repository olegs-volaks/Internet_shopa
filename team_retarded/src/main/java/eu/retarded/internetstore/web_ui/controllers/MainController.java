package eu.retarded.internetstore.web_ui.controllers;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.ShowAllProductsRequest;
import eu.retarded.internetstore.core.services.product.ShowAllProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private ShowAllProductsService showAllProductsService;

    @GetMapping(value = "/")
    public String main(ModelMap modelMap) {
        Page<Product> products = showAllProductsService.execute(new ShowAllProductsRequest()).getProducts();
        modelMap.addAttribute("products", products);
        return "index";
    }
}

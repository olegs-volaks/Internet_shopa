package eu.retarded.internetstore.web_ui.controllers.admin.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.AddProductRequest;
import eu.retarded.internetstore.core.requests.product.ShowAllProductsRequest;
import eu.retarded.internetstore.core.responses.product.AddProductResponse;
import eu.retarded.internetstore.core.services.product.AddProductService;
import eu.retarded.internetstore.core.services.product.ShowAllProductsService;
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
public class AdminProductController {

    @Autowired
    private AddProductService addProductService;

    @Autowired
    private ShowAllProductsService showAllProductsService;

    @Value("${admin.page-size}")
    private int pageSize;

    @GetMapping("/admin/product/{page}")
    public String productPage(@PathVariable String page, @RequestParam(name = "error", required = false) String error,
                              ModelMap modelMap) {
        int pageInt = Integer.parseInt(page);
        Page<Product> productPage = showAllProductsService.execute(new ShowAllProductsRequest(
                PageRequest.of(pageInt - 1, pageSize))).getProductsPage();
        int totalPages = productPage.getTotalPages();
        if (totalPages < 1) {
            totalPages = 1;
        }
        modelMap.addAttribute("error", error != null);
        modelMap.addAttribute("products", productPage);
        modelMap.addAttribute("total_pages", totalPages);
        modelMap.addAttribute("current_page", pageInt);
        return "/admin/product/index";
    }

    @GetMapping("/admin/product")
    public String main(ModelMap modelMap) {
        return "redirect:/admin/product/1";
    }

    @PostMapping("/admin/product/add")
    public String addProduct(@RequestParam(value = "name") String name,
                             @RequestParam(value = "description") String description,
                             @RequestParam(value = "price") double price,
                             @RequestParam(value = "count", required = false) int count) {
        AddProductRequest addProductRequest = new AddProductRequest(name, description, price, count);
        AddProductResponse addProductResponse = addProductService.execute(addProductRequest);
        if (addProductResponse.hasErrors()) {
            return "redirect:/admin/product/1?error";
        }
        return "redirect:/admin/product";
    }
}

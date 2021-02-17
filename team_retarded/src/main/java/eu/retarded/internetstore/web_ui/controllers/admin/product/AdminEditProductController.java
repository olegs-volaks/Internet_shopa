package eu.retarded.internetstore.web_ui.controllers.admin.product;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.GetProductByIdRequest;
import eu.retarded.internetstore.core.requests.product.UpdateProductRequest;
import eu.retarded.internetstore.core.services.category.ShowAllCategoriesService;
import eu.retarded.internetstore.core.services.product.GetProductByIdService;
import eu.retarded.internetstore.core.services.product.UpdateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class AdminEditProductController {

    @Autowired
    private GetProductByIdService getProductByIdService;

    @Autowired
    private UpdateProductService updateProductService;

    @Autowired
    private ShowAllCategoriesService showAllCategoriesService;

    @GetMapping("/admin/product/edit/{id}")
    public String main(@PathVariable String id, ModelMap modelMap) {
        long idLong = Integer.parseInt(id);
        Product product = getProductByIdService.execute(new GetProductByIdRequest(idLong)).getProduct();
        modelMap.addAttribute("product", product);
        modelMap.addAttribute("categories", new ArrayList<Category>());
        return "/admin/product/edit";
    }

    @PostMapping("/admin/product/save")
    public String editProduct(@RequestParam(value = "id") long id,
                              @RequestParam(value = "name") String name,
                              @RequestParam(value = "description") String description,
                              @RequestParam(value = "price") double price,
                              @RequestParam(value = "count", required = false) int count) {
        UpdateProductRequest updateProductRequest = new UpdateProductRequest(id, name, description, price, count);
        updateProductService.execute(updateProductRequest);
        return "redirect:/admin/product/edit/" + id;
    }
}

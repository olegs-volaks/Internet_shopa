package eu.retarded.internetstore.web_ui.controllers.admin.product;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.category.ShowAllCategoriesRequest;
import eu.retarded.internetstore.core.requests.product.AddProductToCategoryRequest;
import eu.retarded.internetstore.core.requests.product.DeleteProductFromCategoryRequest;
import eu.retarded.internetstore.core.requests.product.GetProductByIdRequest;
import eu.retarded.internetstore.core.requests.product.UpdateProductRequest;
import eu.retarded.internetstore.core.responses.product.UpdateProductResponse;
import eu.retarded.internetstore.core.services.category.ShowAllCategoriesService;
import eu.retarded.internetstore.core.services.product.AddProductToCategoryService;
import eu.retarded.internetstore.core.services.product.DeleteProductFromCategoryService;
import eu.retarded.internetstore.core.services.product.GetProductByIdService;
import eu.retarded.internetstore.core.services.product.UpdateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminEditProductController {

    @Autowired
    private GetProductByIdService getProductByIdService;

    @Autowired
    private UpdateProductService updateProductService;

    @Autowired
    private ShowAllCategoriesService showAllCategoriesService;

    @Autowired
    private AddProductToCategoryService addProductToCategoryService;

    @Autowired
    private DeleteProductFromCategoryService deleteProductFromCategoryService;

    @GetMapping("/admin/product/edit/{id}")
    public String main(@PathVariable String id,@RequestParam(name = "error", required = false) String error,
                       ModelMap modelMap) {
        long idLong = Integer.parseInt(id);
        Product product = getProductByIdService.execute(new GetProductByIdRequest(idLong)).getProduct();
        List<Category> categoryList = showAllCategoriesService.execute(new ShowAllCategoriesRequest()).getCategoriesList();
        long productCategoryId = 0;
        if (product.getCategory() != null) {
            productCategoryId = product.getCategory().getId();
        }
        modelMap.addAttribute("error", error != null);
        modelMap.addAttribute("product", product);
        modelMap.addAttribute("categories", categoryList);
        modelMap.addAttribute("product_category_id", productCategoryId);
        return "/admin/product/edit";
    }

    @PostMapping("/admin/product/save")
    public String editProduct(@RequestParam(value = "id") long id,
                              @RequestParam(value = "name") String name,
                              @RequestParam(value = "description") String description,
                              @RequestParam(value = "price") double price,
                              @RequestParam(value = "count") int count,
                              @RequestParam(value = "category-id") long categoryId) {
        UpdateProductRequest updateProductRequest = new UpdateProductRequest(id, name, description, price, count);
        if (categoryId > 0) {
            addProductToCategoryService.execute(new AddProductToCategoryRequest(categoryId, id));
        } else {
            deleteProductFromCategoryService.execute(new DeleteProductFromCategoryRequest(id));
        }
        UpdateProductResponse updateProductResponse = updateProductService.execute(updateProductRequest);
        if (updateProductResponse.hasErrors()) {
            return "redirect:/admin/product/edit/"+id+"?error";
        }
        return "redirect:/admin/product/edit/" + id;
    }
}

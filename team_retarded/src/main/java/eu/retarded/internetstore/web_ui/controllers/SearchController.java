package eu.retarded.internetstore.web_ui.controllers;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.cart.GetProductInCartRequest;
import eu.retarded.internetstore.core.requests.category.ShowAllCategoriesRequest;
import eu.retarded.internetstore.core.requests.product.SearchProductRequest;
import eu.retarded.internetstore.core.services.cart.GetProductInCartService;
import eu.retarded.internetstore.core.services.category.ShowAllCategoriesService;
import eu.retarded.internetstore.core.services.product.SearchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class SearchController {
    @Autowired
    private SearchProductService searchProductService;

    @Autowired
    private GetProductInCartService getProductInCartService;

    @Autowired
    private ShowAllCategoriesService showAllCategoriesService;

    @Value("${product.page-size}")
    private int pageSize;

    @GetMapping("/search/{page}")
    public String main(@PathVariable(name = "page") String page,
                       @RequestParam(value = "error", required = false) String error,
                       @RequestParam(name = "keyword") String keyword,
                       @AuthenticationPrincipal User activeUser,
                       ModelMap modelMap) {
         String keywordString =keyword;
        int pageInt = Integer.parseInt(page);
        boolean isLogged = activeUser != null;
        boolean isActiveUserAdmin = false;
        int productInCart = 0;
        if (isLogged) {
            isActiveUserAdmin = activeUser.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
            productInCart = getProductInCartService.execute(new GetProductInCartRequest(activeUser.getCart().getId())).getProducts().size();
        }
        List<Category> categoryList = showAllCategoriesService.execute(new ShowAllCategoriesRequest()).getCategoriesList();
        Page<Product> productPage = searchProductService
                .execute(new SearchProductRequest(keywordString, PageRequest.of(pageInt - 1, pageSize))).getProductsPage();
        int totalPages = productPage.getTotalPages();
        if (totalPages < 1) {
            totalPages = 1;
        }
        modelMap.addAttribute("keyword", keywordString);
        modelMap.addAttribute("products", productPage);
        modelMap.addAttribute("categories", categoryList);
        modelMap.addAttribute("active_user", activeUser);
        modelMap.addAttribute("is_logged", isLogged);
        modelMap.addAttribute("is_admin", isActiveUserAdmin);
        modelMap.addAttribute("error", error != null);
        modelMap.addAttribute("total_pages", totalPages);
        modelMap.addAttribute("current_page", pageInt);
        modelMap.addAttribute("product_in_cart_count", productInCart);
        return "/index";
    }

    @GetMapping("/search/{page}")
    public String main(@PathVariable(name = "{page}") String page,
                       ModelMap modelMap) {
        return "redirect:/search/" + page ;
    }
}

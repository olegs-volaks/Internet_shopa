package eu.retarded.internetstore.web_ui.controllers;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.cart.GetProductInCartRequest;
import eu.retarded.internetstore.core.requests.category.ShowAllCategoriesRequest;
import eu.retarded.internetstore.core.requests.product.ShowAllProductsRequest;
import eu.retarded.internetstore.core.services.cart.GetProductInCartService;
import eu.retarded.internetstore.core.services.category.ShowAllCategoriesService;
import eu.retarded.internetstore.core.services.product.ShowAllProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ShowAllProductsService showAllProductsService;

    @Autowired
    private GetProductInCartService getProductInCartService;

    @Autowired
    private ShowAllCategoriesService showAllCategoriesService;

    @Value("${product.page-size}")
    private int pageSize;

    @GetMapping(value = "/page/{page}")
    public String main(@PathVariable String page,
                       @RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "logout", required = false) String logout,
                       @AuthenticationPrincipal User activeUser,
                       ModelMap modelMap) {
        boolean isLogged = activeUser != null;
        boolean isActiveUserAdmin = false;
        int productInCart = 0;
        if (isLogged) {
            isActiveUserAdmin = activeUser.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
            productInCart = getProductInCartService.execute(new GetProductInCartRequest(activeUser.getCart().getId())).getProducts().size();
        }
        List<Category> categoryList = showAllCategoriesService.execute(new ShowAllCategoriesRequest()).getCategoriesList();
        int pageInt = Integer.parseInt(page);
        Page<Product> productPage = showAllProductsService.execute(new ShowAllProductsRequest(
                PageRequest.of(pageInt - 1, pageSize))).getProductsPage();
        int totalPages = productPage.getTotalPages();
        if (totalPages < 1) {
            totalPages = 1;
        }
        modelMap.addAttribute("products", productPage);
        modelMap.addAttribute("categories", categoryList);
        modelMap.addAttribute("selected_category", 0);
        modelMap.addAttribute("error", error != null);
        modelMap.addAttribute("logout", logout != null);
        modelMap.addAttribute("active_user", activeUser);
        modelMap.addAttribute("is_logged", isLogged);
        modelMap.addAttribute("is_admin", isActiveUserAdmin);
        modelMap.addAttribute("total_pages", totalPages);
        modelMap.addAttribute("current_page", pageInt);
        modelMap.addAttribute("product_in_cart_count", productInCart);
        return "index";
    }

    @GetMapping("/")
    public String main(ModelMap modelMap) {
        return "redirect:/page/1";
    }
}

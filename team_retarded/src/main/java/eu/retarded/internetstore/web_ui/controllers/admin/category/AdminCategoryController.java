package eu.retarded.internetstore.web_ui.controllers.admin.category;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.requests.category.AddCategoryRequest;
import eu.retarded.internetstore.core.requests.category.ShowAllCategoriesRequest;
import eu.retarded.internetstore.core.responses.category.AddCategoryResponse;
import eu.retarded.internetstore.core.services.category.AddCategoryService;
import eu.retarded.internetstore.core.services.category.ShowAllCategoriesService;
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
public class AdminCategoryController {
    @Autowired
    private AddCategoryService addCategoryService;

    @Autowired
    private ShowAllCategoriesService showAllCategoriesService;

    @Value("${admin.page-size}")
    private int pageSize;

    @GetMapping("/admin/category/{page}")
    public String categoryPage(@PathVariable String page,@RequestParam(name = "error", required = false) String error,
                               ModelMap modelMap) {  // productPage
        int pageInt = Integer.parseInt(page);
        Page <Category> categoryPage = showAllCategoriesService.execute(new ShowAllCategoriesRequest(
                PageRequest.of(pageInt - 1, pageSize))).getCategoriesPage();
        int totalPages = categoryPage.getTotalPages();
        if (totalPages < 1) {
            totalPages = 1;
        }
        modelMap.addAttribute("error", error != null);
        modelMap.addAttribute("categories", categoryPage); // products
        modelMap.addAttribute("total_pages", totalPages);
        modelMap.addAttribute("current_page", pageInt);
        return "/admin/category/index";
    }

    @GetMapping("/admin/category")
    public String main(ModelMap modelMap) {
        return "redirect:/admin/category/1";
    }

    @PostMapping("/admin/category/add")
    public String addDelivery(@RequestParam(value = "name") String name) {
        AddCategoryRequest addCategoryRequest = new AddCategoryRequest(name);
        AddCategoryResponse addCategoryResponse = addCategoryService.execute(addCategoryRequest);
        if (addCategoryResponse.hasErrors()) {
            return "redirect:/admin/category/1?error";
        }
        return "redirect:/admin/category";
    }
}


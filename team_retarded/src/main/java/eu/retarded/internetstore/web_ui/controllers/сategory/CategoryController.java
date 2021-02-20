package eu.retarded.internetstore.web_ui.controllers.сategory;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.requests.category.GetCategoryByIdRequest;
import eu.retarded.internetstore.core.services.category.GetCategoryByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoryController {

    @Autowired
    private GetCategoryByIdService getCategoryByIdService;

    @GetMapping("/category/{id}")
    public String main(@PathVariable(name = "id") String id, ModelMap modelMap) {
        long idLong = Long.parseLong(id);
        Category category = getCategoryByIdService.execute(new GetCategoryByIdRequest(idLong)).getCategory();
        modelMap.addAttribute("category", category);
        return "category/index";
    }
}

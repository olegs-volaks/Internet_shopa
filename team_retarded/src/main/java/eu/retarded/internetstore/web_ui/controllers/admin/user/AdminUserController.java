package eu.retarded.internetstore.web_ui.controllers.admin.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.AddUserRequest;
import eu.retarded.internetstore.core.requests.user.GetUserListRequest;
import eu.retarded.internetstore.core.responses.user.AddUserResponse;
import eu.retarded.internetstore.core.services.user.AddUserService;
import eu.retarded.internetstore.core.services.user.GetUserListService;
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

import java.util.Collections;

@Controller
public class AdminUserController {

    @Autowired
    private AddUserService addUserService;

    @Autowired
    private GetUserListService getUserListService;

    @Value("${admin.page-size}")
    private int pageSize;

    @GetMapping("/admin/user/{page}")
    public String productPage(@PathVariable String page,@RequestParam(name = "error", required = false) String error,
                              ModelMap modelMap) {
        int pageInt = Integer.parseInt(page);
        Page<User> userPage = getUserListService.execute(new GetUserListRequest
                (PageRequest.of(pageInt - 1, pageSize))).getPage();
        modelMap.addAttribute("error", error != null);
        modelMap.addAttribute("users", userPage);
        modelMap.addAttribute("total_pages", userPage.getTotalPages());
        modelMap.addAttribute("current_page", pageInt);
        return "/admin/user/index";
    }

    @GetMapping("/admin/user")
    public String main(ModelMap modelMap) {
        return "redirect:/admin/user/1";
    }

    @PostMapping("/admin/user/add")
    public String addUser(@RequestParam(value = "username") String username,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "surname") String surname,
                             @RequestParam(value = "email") String email,
                             @RequestParam(value = "password1") String password1,
                             @RequestParam(value = "password2", required = false) String password2) {
        AddUserRequest addUserRequest = new AddUserRequest(username, password1, password2, name, surname, email, Collections.singleton(1L));
        AddUserResponse addUserResponse = addUserService.execute(addUserRequest);
        if (addUserResponse.hasErrors()) {
            return "redirect:/admin/user/1?error";
        }
        return "redirect:/admin/user";
    }
}

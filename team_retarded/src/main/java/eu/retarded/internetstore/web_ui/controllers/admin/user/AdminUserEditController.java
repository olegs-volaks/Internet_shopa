package eu.retarded.internetstore.web_ui.controllers.admin.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.GetUserByIdRequest;
import eu.retarded.internetstore.core.requests.user.UpdateUserWithRoleRequest;
import eu.retarded.internetstore.core.responses.user.UpdateUserWithRoleResponse;
import eu.retarded.internetstore.core.services.user.GetUserByIdService;
import eu.retarded.internetstore.core.services.user.UpdateUserWithRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminUserEditController {
    @Autowired
    private GetUserByIdService getUserByIdService;

    @Autowired
    private UpdateUserWithRoleService updateUserWithRoleService;


    @GetMapping("/admin/user/edit/{id}")
    public String main(@PathVariable String id,@RequestParam(name = "error", required = false) String error,
                       ModelMap modelMap) {
        long idLong = Integer.parseInt(id);
        User user = getUserByIdService.execute(new GetUserByIdRequest(idLong)).getUser();
        modelMap.addAttribute("error", error != null);
        modelMap.addAttribute("user", user);
        return "/admin/user/edit";
    }

    @PostMapping("/admin/user/save")
    public String editUser(@RequestParam(value = "id") long id,
                               @RequestParam(value = "username") String username,
                               @RequestParam(value = "name") String name,
                               @RequestParam(value = "surname") String surname,
                               @RequestParam(value = "email") String email) {
        UpdateUserWithRoleRequest updateUserWithRoleRequest = new UpdateUserWithRoleRequest
                (id, username, name, surname, email, new Long[]{1L});
        updateUserWithRoleService.execute(updateUserWithRoleRequest);
        UpdateUserWithRoleResponse updateUserWithRoleResponse = updateUserWithRoleService.execute(updateUserWithRoleRequest);
        if (updateUserWithRoleResponse.hasErrors()) {
            return "redirect:/admin/user/edit/[[${user.id}]]";
        }
        return "redirect:/admin/user/edit" + id;
    }
}


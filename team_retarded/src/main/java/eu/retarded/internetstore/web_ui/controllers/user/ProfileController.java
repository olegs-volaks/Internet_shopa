package eu.retarded.internetstore.web_ui.controllers.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.cart.GetProductInCartRequest;
import eu.retarded.internetstore.core.requests.user.ChangeUserPasswordRequest;
import eu.retarded.internetstore.core.requests.user.UpdateUserRequest;
import eu.retarded.internetstore.core.responses.user.ChangeUserPasswordResponse;
import eu.retarded.internetstore.core.responses.user.UpdateUserResponse;
import eu.retarded.internetstore.core.services.cart.GetProductInCartService;
import eu.retarded.internetstore.core.services.user.ChangeUserPasswordService;
import eu.retarded.internetstore.core.services.user.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    @Autowired
    private GetProductInCartService getProductInCartService;

    @Autowired
    private UpdateUserService updateUserService;

    @Autowired
    private ChangeUserPasswordService changeUserPasswordService;

    @GetMapping("/user/profile")
    public String main(@RequestParam(name = "dataError", required = false) String dataError,
                       @AuthenticationPrincipal User activeUser,
                       ModelMap modelMap) {
        boolean isActiveUserAdmin = activeUser.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
        int productInCartCount = getProductInCartService.execute(new GetProductInCartRequest(activeUser.getCart().getId())).getProducts().size();

        modelMap.addAttribute("active_user", activeUser);
        modelMap.addAttribute("is_logged", true);
        modelMap.addAttribute("is_admin", isActiveUserAdmin);
        modelMap.addAttribute("product_in_cart_count", productInCartCount);
        modelMap.addAttribute("data_error", dataError != null);
        return "/user/profile";
    }

    @PostMapping("/user/profile/edit")
    public String editUser(@RequestParam(value = "user_id") long id,
                           @RequestParam(value = "name") String name,
                           @RequestParam(value = "surname") String surname,
                           @RequestParam(value = "email") String email) {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(id, name, surname, email);
        UpdateUserResponse updateUserResponse = updateUserService.execute(updateUserRequest);
        if (updateUserResponse.hasErrors()) {
            return "redirect:/user/profile?dataError";
        }
        return "redirect:/user/profile";
    }

    @PostMapping("/user/profile/changePassword")
    public String changePassword(@RequestParam(value = "user_id") long id,
                                 @RequestParam(value = "password1") String password1,
                                 @RequestParam(value = "password2") String password2) {
        ChangeUserPasswordResponse response = changeUserPasswordService.execute(new ChangeUserPasswordRequest(id, password1, password2));
        if (response.hasErrors() || response.getUser() == null) {
            return "redirect:/user/profile?dataError";
        }
        User user = response.getUser();
        Authentication authentication = new PreAuthenticatedAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/user/profile";
    }
}

package pl.isa.freshmenindustries.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.isa.freshmenindustries.response.Response;

@Controller
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Secured({"ROLE_ADMIN"})
    @GetMapping("/manage-users")
    public String getAllUsers(Model model, @ModelAttribute("response") Response response) {
        model.addAttribute("users", userService.getAllUsers().getData())
                .addAttribute("response", response)
                .addAttribute("content", "users");
        return "main";
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("manage-users/create")
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/manage-users";
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("manage-users/activate")
    public String activateUser(@ModelAttribute("email") String email) {
        userService.activateUser(email);
        return "redirect:/manage-users";
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("manage-users/assign-user-roles")
    public String assignUserRoles(@ModelAttribute UserRolesDTO userRolesDTO, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("response", userService.assignUserRole(userRolesDTO));
        return "redirect:/manage-users";
    }

    @GetMapping("manage-users/get-user-rolese/{id}")
    public ResponseEntity getUserRoles(@PathVariable Long id) {
        return new ResponseEntity(userService.getUserRoles(id), HttpStatus.OK);
    }

}

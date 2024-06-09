package pl.isa.freshmenindustries.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


   @Secured({"ROLE_ADMIN"})
    @GetMapping("/manage-users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers().getData())
                .addAttribute("content", "users");
        return "main";
    }

    @PostMapping("manage-users/create")
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/manage-users";
    }

    @PostMapping("manage-users/activate")
    public String activateUser(@ModelAttribute String email) {
        userService.activateUser(email);
        return "redirect:/manage-users";
    }
}

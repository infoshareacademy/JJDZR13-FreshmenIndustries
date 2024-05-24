package pl.isa.freshmenindustries.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.isa.freshmenindustries.user.User;
import pl.isa.freshmenindustries.user.UserService;

@Controller
@Slf4j
public class AuthController {

    public final UserService userService;
    public final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("content", "login_page");
        return "main";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("content", "register_page");
        return "main";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserRegisterDTO userRegisterDto) {
        User user = User.builder()
                .name(userRegisterDto.getName())
                .surname(userRegisterDto.getSurname())
                .email(userRegisterDto.getEmail())
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .isEnabled(false)
                .build();
        userService.createUser(user);
        return "redirect:/login?success";
    }
}

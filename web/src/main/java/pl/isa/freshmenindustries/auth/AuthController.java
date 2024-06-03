package pl.isa.freshmenindustries.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.isa.freshmenindustries.response.Response;
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
    public String getLoginPage(Model model, @ModelAttribute("response") Response response) {
        model.addAttribute("content", "login_page")
                .addAttribute("response", response);
        return "main";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model, @ModelAttribute("response") Response response) {
        model.addAttribute("content", "register_page")
                .addAttribute("response", response);
        return "main";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserRegisterDTO userRegisterDto, RedirectAttributes redirectAttributes) {
        if(!userService.isUserByEmailExist(userRegisterDto.getEmail())) {
            User user = User.builder()
                    .name(userRegisterDto.getName())
                    .surname(userRegisterDto.getSurname())
                    .email(userRegisterDto.getEmail())
                    .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                    .isEnabled(false)
                    .build();
            userService.createUser(user);
            redirectAttributes.addFlashAttribute("response",
                    new Response("User created", Boolean.TRUE));
            return "redirect:/login?success";
        }
        else {
            redirectAttributes.addFlashAttribute("response",new Response("User with provided email already exist", Boolean.FALSE));
            return "redirect:/register?error";
        }


    }
}

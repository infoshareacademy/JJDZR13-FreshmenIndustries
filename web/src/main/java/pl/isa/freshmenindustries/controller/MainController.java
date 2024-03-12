package pl.isa.freshmenindustries.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MainController {
    @GetMapping("/")
    String index (Model model) {
        model.addAttribute("content", "index");
        return "main";
    }
}

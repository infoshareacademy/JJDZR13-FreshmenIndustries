package pl.isa.freshmenindustries.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.isa.freshmenindustries.playGame.PlayGameService;
import pl.isa.freshmenindustries.response.Response;

@Controller
public class MainController {
    private final PlayGameService playGameService;

    public MainController(PlayGameService playGameService) {
        this.playGameService = playGameService;
    }

    @GetMapping("/")
    String index(Model model,@ModelAttribute("response") Response response) {
        model.addAttribute("playGameTopRank", playGameService.getPlayedGameTopRankListDto().getData())
                .addAttribute("response", response)
                .addAttribute("content", "index");
        return "main";
    }
}

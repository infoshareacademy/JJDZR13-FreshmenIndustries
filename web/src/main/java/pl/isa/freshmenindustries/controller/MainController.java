package pl.isa.freshmenindustries.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.isa.freshmenindustries.playGame.PlayGameService;

@Controller
public class MainController {
    private final PlayGameService playGameService;

    public MainController(PlayGameService playGameService) {
        this.playGameService = playGameService;
    }

    @GetMapping("/")
    String index (Model model) {
        model.addAttribute("playGameTopRank", playGameService.getPlayedGameTopRankListDto().getData())
            .addAttribute("content", "index");
        return "main";
    }
}

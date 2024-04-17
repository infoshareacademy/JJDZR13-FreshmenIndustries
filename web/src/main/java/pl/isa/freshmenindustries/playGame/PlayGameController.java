package pl.isa.freshmenindustries.playGame;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.isa.freshmenindustries.game.Game;
import pl.isa.freshmenindustries.game.GameService;
import pl.isa.freshmenindustries.response.Response;

import java.util.UUID;

@Controller()
public class PlayGameController {
    private final PlayGameService playGameService;

    public PlayGameController(PlayGameService playGameService) {
        this.playGameService = playGameService;
    }
    @GetMapping("/play-game/{id}")
    public String playGames(Model model,
                            @PathVariable UUID id) {
        model.addAttribute("gameId", id)
                .addAttribute("content", "playGame");
        return "main";
    }

    @PostMapping("/play-game/start")
    public String startPlayGame(@ModelAttribute("gameId") UUID gameId, RedirectAttributes redirectAttributes) {
        playGameService.startGame(gameId);
        return "redirect:/play-game/" + gameId;
    }
}

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
    @GetMapping("/play-games")
    public String games(Model model,
                        @ModelAttribute("response") Response response,
                        @ModelAttribute("game") Game game) {
        Response result = playGameService.getAllPlayGames();
        if(result.getIsSuccess()) {
            model.addAttribute("playGameList", result.getData())
//                .addAttribute("response", response)
//                .addAttribute("game", game)
                    .addAttribute("content", "playGame");
        } else {
            //TODO do dodania obsługa błędu
//            model.addAttribute();
        }
        return "main";
    }

    @PostMapping("/play-games/start/{id}")
    public String playGame(@PathVariable UUID id, Model model, RedirectAttributes redirectAttributes) {
        Response game = playGameService.startGame(id);
        return "redirect:/play-games";
    }
}

package pl.isa.freshmenindustries.playGame;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.isa.freshmenindustries.game.Game;
import pl.isa.freshmenindustries.game.GameService;
import pl.isa.freshmenindustries.response.Response;

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
}

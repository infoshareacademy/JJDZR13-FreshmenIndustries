package pl.isa.freshmenindustries.userGame;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.isa.freshmenindustries.response.Response;

@Controller
public class UserGameController {
    private final UserGameService userGameService;

    public UserGameController(UserGameService userGameService) {
        this.userGameService = userGameService;
    }

    @PostMapping("/user-game/add")
    public String createUserGame(@ModelAttribute UserGame userGame, RedirectAttributes redirectAttributes) {
        if(userGame.getPlayGameId() == null || userGame.getUserId() == null || userGame.getScore() < 1) {
            redirectAttributes.addFlashAttribute("response", new Response("Missing parameter error", Boolean.FALSE));
        } else {
            Response response = userGameService.createUserGame(userGame);
            redirectAttributes.addFlashAttribute("response", response);
        }
        return "redirect:/play-game";
    }
}

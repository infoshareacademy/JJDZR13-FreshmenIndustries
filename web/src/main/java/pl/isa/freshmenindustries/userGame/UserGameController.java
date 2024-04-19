package pl.isa.freshmenindustries.userGame;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.isa.freshmenindustries.response.Response;

@Controller
@Slf4j
public class UserGameController {
    private final UserGameService userGameService;

    public UserGameController(UserGameService userGameService) {
        this.userGameService = userGameService;
    }

    @PostMapping("/user-game/add")
    public String createUserGame(@ModelAttribute UserGame userGame, RedirectAttributes redirectAttributes) {
        if (!checkInputNumberIfIsNotNull(userGame.getScore()) || userGame.getScore() < 1) {
            redirectAttributes.addFlashAttribute("response", new Response("The user score must be greater the 0", Boolean.FALSE));
        } else {
            Response response = userGameService.createUserGame(userGame);
            redirectAttributes.addFlashAttribute("response", response);
        }
        return "redirect:/play-game/" + userGame.getPlayGameId();
    }

    private boolean checkInputNumberIfIsNotNull(int inputNumber) {
        try {
            Integer.parseInt(String.valueOf(inputNumber));
            return true;
        } catch (Exception e) {
            log.info("Create user game error. Input number is null");
            return false;
        }
    }
}

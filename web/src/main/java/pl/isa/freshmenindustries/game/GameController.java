package pl.isa.freshmenindustries.game;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.isa.freshmenindustries.response.Response;

import java.util.UUID;

@Controller()
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/manage-games")
    public String games(Model model,
                        @ModelAttribute("response") Response response,
                        @ModelAttribute("game") Game game) {
        model.addAttribute("gamesList", gameService.getAllGames().getData())
                .addAttribute("response", response)
                .addAttribute("game", game)
                .addAttribute("content", "manageGames");
        return "main";
    }

    @GetMapping("/manage-games/get/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable UUID id) {
        return new ResponseEntity<>(gameService.getGameById(id), HttpStatus.OK);
    }

    @GetMapping("start-new-game")
    public String startNewGame(Model model,
    @ModelAttribute("response") Response response,
    @ModelAttribute("game") Game game) {
        model.addAttribute("gamesList", gameService.getAllGames().getData())
                .addAttribute("response", response)
                .addAttribute("game", game)
                .addAttribute("content", "startNewGame");
        return "main";
    }

    @PostMapping("/manage-games/create")
    public String createGame(@ModelAttribute Game game, RedirectAttributes redirectAttributes) {
        if (!game.getName().isEmpty() || !game.getDescription().isEmpty()) {
            Response response = gameService.createGame(game);
            redirectAttributes.addFlashAttribute("response", response);
        } else {
            redirectAttributes.addFlashAttribute("response", new Response("Missing name or description", Boolean.FALSE));
        }
        return "redirect:/manage-games";
    }

    @PostMapping("/manage-games/update")
    public String updateGame(@ModelAttribute Game game, RedirectAttributes redirectAttributes) {
        if (!game.getName().isEmpty() || !game.getDescription().isEmpty()) {
            Response response = gameService.updateGame(game);
            redirectAttributes.addFlashAttribute("response", response);
        } else {
            redirectAttributes.addFlashAttribute("response", new Response("Missing name or description", Boolean.FALSE));
        }
        return "redirect:/manage-games";
    }

    @PostMapping("/manage-games/delete")
    public String deleteGame(@ModelAttribute Game game, RedirectAttributes redirectAttributes) {
        Response response = gameService.deleteGame(game.getId());
        redirectAttributes.addFlashAttribute("response", response);
        return "redirect:/manage-games";
    }
}

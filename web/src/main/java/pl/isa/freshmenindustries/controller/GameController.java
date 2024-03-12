package pl.isa.freshmenindustries.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.isa.freshmenindustries.game.Game;
import pl.isa.freshmenindustries.game.GameService;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Controller()
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/manage-games")
    public String games(Model model, @ModelAttribute("error") String error, @ModelAttribute("game") Game game) {
        model.addAttribute("gamesList", gameService.getAllGames())
                .addAttribute("error", error)
                .addAttribute("game", game)
                .addAttribute("content", "manageGames");
        return "main";
    }
    @GetMapping("/manage-games/get/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable UUID id) {
        return new ResponseEntity<>(gameService.getGameById(id), HttpStatus.OK);
    }

    @PostMapping("/manage-games/create")
    public String createGame(@ModelAttribute Game game, RedirectAttributes redirectAttributes) {
        try {
            gameService.createGame(game);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/manage-games";
    }

    @PostMapping("/manage-games/update")
    public String updateGame(@ModelAttribute Game game, RedirectAttributes redirectAttributes) {
        try {
            gameService.updateGame(game);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/manage-games";
    }

    @PostMapping("/manage-games/delete")
    public String deleteGame(@ModelAttribute Game game, RedirectAttributes redirectAttributes) {
        try {
            gameService.deleteGame(game.getId());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/manage-games";
    }
}

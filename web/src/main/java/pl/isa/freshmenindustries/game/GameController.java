package pl.isa.freshmenindustries.game;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.isa.freshmenindustries.auth.SecurityService;
import pl.isa.freshmenindustries.response.Response;

@Controller()
public class GameController {
    private final GameService gameService;
    private final SecurityService securityService;

    public GameController(GameService gameService, SecurityService securityService) {
        this.gameService = gameService;
        this.securityService = securityService;
    }

    @Secured({"ROLE_GAME_MASTER"})
    @GetMapping("/start-new-game")
    public String startNewGame(Model model,
                               @ModelAttribute("response") Response response,
                               @ModelAttribute("game") Game game) {
        model.addAttribute("gamesList", gameService.getAllActiveGames().getData())
                .addAttribute("response", response)
                .addAttribute("content", "startNewGame");
        return "main";
    }

    @GetMapping("/manage-games")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String games(Model model,
                        @ModelAttribute("response") Response response,
                        @ModelAttribute("game") Game game,
                        RedirectAttributes redirectAttributes) {
        if (securityService.checkIfUserHasRole("ADMIN")) {
            model.addAttribute("gamesList", gameService.getAllActiveGames().getData())
                    .addAttribute("response", response)
                    .addAttribute("game", game)
                    .addAttribute("content", "manageGames");
            return "main";
        } else {
            redirectAttributes
                    .addFlashAttribute("response", new Response("No permission for user access", Boolean.FALSE))
                    .addFlashAttribute("content", "index");
            return "redirect:/";
        }
    }

    @GetMapping("/manage-games/get/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        return new ResponseEntity<>(gameService.getGameById(id), HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
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

    @Secured({"ROLE_ADMIN"})
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

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/manage-games/delete")
    public String deleteGame(@ModelAttribute Game game, RedirectAttributes redirectAttributes) {
        Response response = gameService.deleteGame(game.getId());
        redirectAttributes.addFlashAttribute("response", response);
        return "redirect:/manage-games";
    }
}

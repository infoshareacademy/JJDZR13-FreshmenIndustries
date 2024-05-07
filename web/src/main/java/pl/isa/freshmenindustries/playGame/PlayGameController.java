package pl.isa.freshmenindustries.playGame;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.isa.freshmenindustries.game.GameService;
import pl.isa.freshmenindustries.response.Response;
import pl.isa.freshmenindustries.user.UserService;
import pl.isa.freshmenindustries.userGame.UserGameProjection;
import pl.isa.freshmenindustries.userGame.UserGameService;

import java.util.List;

@Controller()
@Slf4j
public class PlayGameController {
    private final PlayGameService playGameService;
    private final GameService gameService;
    private final UserGameService userGameService;
    private final UserService userService;

    public PlayGameController(PlayGameService playGameService, GameService gameService, UserGameService userGameService, UserService userService) {
        this.playGameService = playGameService;
        this.gameService = gameService;
        this.userGameService = userGameService;
        this.userService = userService;
    }

    @GetMapping("/played-games")
    public String playedGames(Model model) {
        Response response = playGameService.getPlayedGameListDto();
        model.addAttribute("playedGamesWithTopScoreUser", response.getData())
                .addAttribute("response", response)
                .addAttribute("content", "playedGames");
        return "main";
    }

    @GetMapping("/play-game/{playGameId}")
    public String playGames(Model model,
                            @ModelAttribute("response") Response response,
                            @PathVariable Long playGameId) {
        try {
            PlayGame playGame = playGameService.getPlayGameById(playGameId);
            List<UserGameProjection> userGames = userGameService.getUserGameDTOByPlayGameId(playGameId);
            model.addAttribute("game", gameService.getGameById(playGame.getGame().getId()))
                    .addAttribute("users", userService.getAllUsers().getData())
                    .addAttribute("userGameScores", userGames)
                    .addAttribute("playGame", playGame)
                    .addAttribute("response", response)
                    .addAttribute("content", "playGame");
        } catch (Exception e) {
            log.info(e.getMessage());
            model.addAttribute("response", new Response("General error occurred", Boolean.FALSE))
                    .addAttribute("content", "playGame");
        }
        return "main";
    }

    @PostMapping("/play-game/start")
    public String startPlayGame(@ModelAttribute("gameId") Long gameId, RedirectAttributes redirectAttributes) {
        try {
            PlayGame playGame = playGameService.startGame(gameId);
            redirectAttributes.addFlashAttribute("response", new Response("Game started successfully", Boolean.TRUE));
            return "redirect:/play-game/" + playGame.getId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("response", new Response("General error occurred", Boolean.FALSE));
            return "redirect:/play-game";
        }
    }

    @PostMapping("/play-game/end")
    public String endPlayGame(@ModelAttribute("id") Long id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("response", playGameService.endPlayGame(id));
        return "redirect:/played-games";
    }
}

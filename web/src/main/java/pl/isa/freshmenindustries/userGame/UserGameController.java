package pl.isa.freshmenindustries.userGame;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.isa.freshmenindustries.playGame.PlayGameRepository;
import pl.isa.freshmenindustries.response.Response;
import pl.isa.freshmenindustries.user.UserRepository;

@Controller
@Slf4j
public class UserGameController {
    private final UserGameService userGameService;
    private final PlayGameRepository playGameRepository;
    private final UserRepository userRepository;

    public UserGameController(UserGameService userGameService, PlayGameRepository playGameRepository, UserRepository userRepository) {
        this.userGameService = userGameService;
        this.playGameRepository = playGameRepository;
        this.userRepository = userRepository;
    }

    @Secured({"ROLE_USER", "ROLE_GAME_MASTER"})
    @PostMapping("/user-game/add")
    public String createUserGame(@ModelAttribute CreateUserGameDTO createUserGameDTO, RedirectAttributes redirectAttributes) {
        if (!checkInputNumberIfIsNotNull(createUserGameDTO.getScore()) || createUserGameDTO.getScore() < 1) {
            redirectAttributes.addFlashAttribute("response", new Response("The user score must be greater the 0", Boolean.FALSE));
        } else {
            UserGame userGame = new UserGame(
                    playGameRepository.findPlayGameById(createUserGameDTO.getPlayGameId()),
                    userRepository.getUserById(createUserGameDTO.getUserId()),
                    createUserGameDTO.getScore());
            Response response = userGameService.createUserGame(userGame);
            redirectAttributes.addFlashAttribute("response", response);
        }
        return "redirect:/play-game/" + createUserGameDTO.getPlayGameId();
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

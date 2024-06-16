package pl.isa.freshmenindustries.game;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.is;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GameTest {

    @MockBean
    GameRepository gameRepository;

    @Autowired
    WebApplicationContext context;
    @Autowired
    MockMvc mockMvc;

    static List<Game> mockerGames;

    @BeforeAll
    static void setGames() {
        mockerGames = List.of(
                new Game(1L, "Test game 1", "Testowa gra", true),
                new Game(2L, "Test game 2", "Testowa gra 2", true),
                new Game(3L, "Test game 3", "Testowa gra 3", true)
        );
    }

    @Test
    void contextTest() {
        assertNotNull(context);
    }

    @Test
    void getGameByIdTest() throws Exception {
        when(gameRepository.getGameById(1L)).thenReturn(mockerGames.get(0));
        mockMvc.perform(get("/manage-games/get/1")
                        .with(user("admin@admin.pl").password("123").roles("ADMIN"))
                )
                .andExpectAll(status()
                        .isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.name", is("Test game 1"))
                );
    }
}

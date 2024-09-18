package app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import app.controller.GameController;
import app.entity.Game;
import app.services.GameService;

@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    private Game game;

    @BeforeEach
    public void setup() {
        game = new Game();
        game.setNome("Tekken 7");
        game.setDescricao("Descrição do Tekken 7");
        game.setLink("https://example.com");
        game.setPreco(150.00);
    }

    @Test
    public void testFindById() throws Exception {
        Mockito.when(gameService.findById(1L)).thenReturn(game);

        mockMvc.perform(get("/api/rdp/game/findById/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.nome").value("Tekken 7"));
    }

    @Test
    public void testFindAll() throws Exception {
        List<Game> games = new ArrayList<>();
        games.add(game);

        Mockito.when(gameService.findAll()).thenReturn(games);

        mockMvc.perform(get("/api/rdp/game/findAll"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].nome").value("Tekken 7"));
    }

    @Test
    public void testSaveGame() throws Exception {
        Mockito.when(gameService.save(Mockito.any(Game.class))).thenReturn("Jogo Cadastrado");

        mockMvc.perform(post("/api/rdp/game/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"nome\": \"Tekken 7\", \"descricao\": \"Descrição\", \"link\": \"https://example.com\", \"preco\": 150.0 }"))
               .andExpect(status().isOk())
               .andExpect(content().string("Jogo Cadastrado"));
    }
}

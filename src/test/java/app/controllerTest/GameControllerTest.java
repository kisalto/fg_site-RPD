package app.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.controller.GameController;
import app.entity.Game;
import app.services.GameService;

@SpringBootTest
public class GameControllerTest {

    @Autowired
    private GameController gameController;

    @MockBean
    private GameService gameService;

    @BeforeEach
    public void setup() {
        Game mockGame = new Game();
        mockGame.setId(1L);
        mockGame.setNome("Street Fighter");

        List<Game> mockGames = new ArrayList<>();
        mockGames.add(mockGame);

        when(gameService.findAll()).thenReturn(mockGames);
        when(gameService.findById(1L)).thenReturn(mockGame);
        when(gameService.findByNome("Street")).thenReturn(mockGames);
    }

    @Test
    @DisplayName("INTEGRAÇÃO - FindAll deve retornar lista de jogos")
    public void testFindAllGames() {
        ResponseEntity<List<Game>> response = gameController.findAll();
        List<Game> games = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, games.size());
        assertEquals("Street Fighter", games.get(0).getNome());
    }

    @Test
    @DisplayName("INTEGRAÇÃO - FindById deve retornar jogo")
    public void testFindGameById() {
        ResponseEntity<Game> response = gameController.findById(1L);
        Game game = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, game.getId());
        assertEquals("Street Fighter", game.getNome());
    }

    @Test
    @DisplayName("INTEGRAÇÃO - FindByNome deve retornar lista de jogos com o mesmo nome")
    public void testFindGameByNome() {
        ResponseEntity<List<Game>> response = gameController.findByNome("Street");
        List<Game> games = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, games.size());
        assertEquals("Street Fighter", games.get(0).getNome());
    }

    @Test
    @DisplayName("INTEGRAÇÃO - Deve deletar o jogo")
    public void testDeleteGame() {
        when(gameService.delete(1L)).thenReturn("Jogo deletado com sucesso!");

        ResponseEntity<String> response = gameController.delete(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Jogo deletado com sucesso!", response.getBody());
    }
}

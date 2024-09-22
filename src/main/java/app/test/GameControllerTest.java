package app.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.controller.GameController;
import app.entity.Game;
import app.services.GameService;

public class GameControllerTest {

    @Mock
    private GameService gameService;

    @InjectMocks
    private GameController gameController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveGame_Success() {
        Game game = new Game();
        when(gameService.save(any(Game.class))).thenReturn("Jogo salvo com sucesso!");

        ResponseEntity<String> response = gameController.save(game);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Jogo salvo com sucesso!", response.getBody());
    }

    @Test
    public void testSaveGame_Failure() {
        Game game = new Game();
        when(gameService.save(any(Game.class))).thenThrow(new RuntimeException("Erro ao salvar"));

        ResponseEntity<String> response = gameController.save(game);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Deu erro!Erro ao salvar", response.getBody());
    }

    @Test
    public void testFindById_Success() {
        Game game = new Game();
        when(gameService.findById(1L)).thenReturn(game);

        ResponseEntity<Game> response = gameController.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(game, response.getBody());
    }

    @Test
    public void testFindById_Failure() {
        when(gameService.findById(1L)).thenThrow(new RuntimeException("Jogo não encontrado"));

        ResponseEntity<Game> response = gameController.findById(1L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void testFindAll_Success() {
        List<Game> games = new ArrayList<>();
        games.add(new Game());
        when(gameService.findAll()).thenReturn(games);

        ResponseEntity<List<Game>> response = gameController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(games, response.getBody());
    }

    @Test
    public void testDelete_Success() {
        when(gameService.delete(1L)).thenReturn("Jogo deletado com sucesso!");

        ResponseEntity<String> response = gameController.delete(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Jogo deletado com sucesso!", response.getBody());
    }
    
    @Test
    public void testFindByNome_Success() {
        List<Game> games = new ArrayList<>();
        games.add(new Game());
        when(gameService.findByNome("Street")).thenReturn(games);

        ResponseEntity<List<Game>> response = gameController.findByNome("Street");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(games, response.getBody());
    }

}

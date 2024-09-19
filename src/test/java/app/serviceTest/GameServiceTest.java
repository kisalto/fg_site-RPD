package app.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import app.entity.Game;
import app.repository.GameRepository;
import app.services.GameService;

public class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveGame() {
        Game game = new Game();
        game.setNome("Street Fighter VI");

        when(gameRepository.save(game)).thenReturn(game);

        String result = gameService.save(game);
        assertEquals("Jogo Cadastrado", result);
        verify(gameRepository, times(1)).save(game);
    }

    @Test
    public void testFindGameById() {
        Game game = new Game();
        game.setId(1L);
        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        Game foundGame = gameService.findById(1L);
        assertNotNull(foundGame);
        assertEquals(1L, foundGame.getId());
    }

    @Test
    public void testUpdateGame() {
        Game game = new Game();
        game.setNome("Mortal Kombat 11");
        long gameId = 1L;

        when(gameRepository.save(game)).thenReturn(game);

        String result = gameService.update(game, gameId);
        assertEquals("Personagem Atualizado", result);
        verify(gameRepository, times(1)).save(game);
    }

    @Test
    public void testDeleteGame() {
        long gameId = 1L;

        doNothing().when(gameRepository).deleteById(gameId);

        String result = gameService.delete(gameId);
        assertEquals("Personagem deletado com sucesso!", result);
        verify(gameRepository, times(1)).deleteById(gameId);
    }
}
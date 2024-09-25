package app.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import app.entity.Game;
import app.repository.GameRepository;
import app.services.GameService;

@SpringBootTest
public class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        Game mockGame = new Game();
        mockGame.setId(1L);
        mockGame.setNome("Street Fighter");

        List<Game> mockGames = new ArrayList<>();
        mockGames.add(mockGame);

        when(gameRepository.save(mockGame)).thenReturn(mockGame);
        when(gameRepository.findById(1L)).thenReturn(Optional.of(mockGame));
        when(gameRepository.findAll()).thenReturn(mockGames);
        when(gameRepository.findByNomeStartsWith("Street")).thenReturn(mockGames);
    }

    @Test
    @DisplayName("UNITÁRIO - Save deve salvar o jogo")
    public void testSaveGame() {
        Game game = new Game();
        game.setNome("Street Fighter");

        String response = gameService.save(game);

        assertEquals("Jogo Cadastrado", response);
    }

    @Test
    @DisplayName("UNITÁRIO - FindById deve retornar jogo")
    public void testFindGameById() {
        Game game = gameService.findById(1L);

        assertEquals(1L, game.getId());
        assertEquals("Street Fighter", game.getNome());
    }

    @Test
    @DisplayName("UNITÁRIO - FindAll deve retornar lista de jogos")
    public void testFindAllGames() {
        List<Game> games = gameService.findAll();

        assertEquals(1, games.size());
        assertEquals("Street Fighter", games.get(0).getNome());
    }

    @Test
    @DisplayName("UNITÁRIO - Delete deve deletar o jogo")
    public void testDeleteGame() {
        String response = gameService.delete(1L);

        assertEquals("Personagem deletado com sucesso!", response);
    }

    @Test
    @DisplayName("UNITÁRIO - FindByNome deve retornar lista de jogos")
    public void testFindGameByNome() {
        List<Game> games = gameService.findByNome("Street");

        assertEquals(1, games.size());
        assertEquals("Street Fighter", games.get(0).getNome());
    }

    @Test
    @DisplayName("UNITÁRIO - FindById deve retornar null se o jogo não existir")
    public void testFindGameByIdNotFound() {
        when(gameRepository.findById(2L)).thenReturn(Optional.empty());

        Game game = gameService.findById(2L);

        assertNull(game);
    }
}

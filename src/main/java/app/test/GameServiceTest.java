package app.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import app.entity.Game;
import app.repository.GameRepository;
import app.services.GameService;

// Testes unitários
public class GameServiceTest {

    // -------------------------- TESTES UNITÁRIOS -------------------------- //
    
    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // Teste unitário para salvar um jogo de luta
    @Test
    public void testSaveGameUnit() {
        Game game = new Game();
        game.setName("Street Fighter");

        when(gameRepository.save(game)).thenReturn(game);

        String result = gameService.save(game);

        assertEquals("Jogo Cadastrado", result);
        verify(gameRepository, times(1)).save(game);
    }

    // Teste unitário para atualizar um jogo de luta
    @Test
    public void testUpdateGameUnit() {
        Game game = new Game();
        game.setName("Mortal Kombat");

        when(gameRepository.save(game)).thenReturn(game);

        String result = gameService.update(game, 1L);

        assertEquals("Personagem Atualizado", result);
        assertEquals(1L, game.getId());
        verify(gameRepository, times(1)).save(game);
    }

    // Teste unitário para encontrar um jogo de luta por ID
    @Test
    public void testFindByIdUnit() {
        Game game = new Game();
        game.setId(1L);
        game.setName("Tekken");

        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        Game foundGame = gameService.findById(1L);

        assertNotNull(foundGame);
        assertEquals("Tekken", foundGame.getName());
        verify(gameRepository, times(1)).findById(1L);
    }

    // Teste unitário para encontrar todos os jogos de luta
    @Test
    public void testFindAllUnit() {
        Game game1 = new Game();
        game1.setName("Soulcalibur");

        Game game2 = new Game();
        game2.setName("Guilty Gear");

        List<Game> games = Arrays.asList(game1, game2);

        when(gameRepository.findAll()).thenReturn(games);

        List<Game> result = gameService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Soulcalibur", result.get(0).getName());
        assertEquals("Guilty Gear", result.get(1).getName());
        verify(gameRepository, times(1)).findAll();
    }

    // Teste unitário para deletar um jogo de luta
    @Test
    public void testDeleteGameUnit() {
        String result = gameService.delete(1L);

        assertEquals("Personagem deletado com sucesso!", result);
        verify(gameRepository, times(1)).deleteById(1L);
    }

    // Teste unitário para encontrar jogos de luta por nome
    @Test
    public void testFindByNomeUnit() {
        Game game1 = new Game();
        game1.setName("Dead or Alive");

        Game game2 = new Game();
        game2.setName("Dead or Alive 5");

        List<Game> games = Arrays.asList(game1, game2);

        when(gameRepository.findByNomeStartsWith("Dead or Alive")).thenReturn(games);

        List<Game> result = gameService.findByNome("Dead or Alive");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Dead or Alive", result.get(0).getName());
        assertEquals("Dead or Alive 5", result.get(1).getName());
        verify(gameRepository, times(1)).findByNomeStartsWith("Dead or Alive");
    }

    // -------------------------- TESTES DE INTEGRAÇÃO -------------------------- //

    @SpringBootTest
    public static class GameServiceIntegrationTest {

        @Autowired
        private GameService gameService;

        @Autowired
        private GameRepository gameRepository;

        // Teste de integração para salvar um jogo de luta
        @Test
        public void testSaveGameIntegration() {
            Game game = new Game();
            game.setName("Virtua Fighter");

            String result = gameService.save(game);
            assertEquals("Jogo Cadastrado", result);

            // Verifica se o jogo foi salvo corretamente no banco
            List<Game> games = gameRepository.findByNomeStartsWith("Virtua Fighter");
            assertFalse(games.isEmpty());
        }

        // Teste de integração para atualizar um jogo de luta
        @Test
        public void testUpdateGameIntegration() {
            Game game = new Game();
            game.setName("BlazBlue");

            gameService.save(game);

            game.setName("BlazBlue: Central Fiction");
            String result = gameService.update(game, game.getId());

            assertEquals("Personagem Atualizado", result);

            Game updatedGame = gameRepository.findById(game.getId()).get();
            assertEquals("BlazBlue: Central Fiction", updatedGame.getName());
        }

        // Teste de integração para encontrar um jogo de luta por ID
        @Test
        public void testFindByIdIntegration() {
            Game game = new Game();
            game.setName("Samurai Shodown");

            gameService.save(game);

            Game foundGame = gameService.findById(game.getId());

            assertNotNull(foundGame);
            assertEquals("Samurai Shodown", foundGame.getName());
        }

        // Teste de integração para deletar um jogo de luta
        @Test
        public void testDeleteGameIntegration() {
            Game game = new Game();
            game.setName("King of Fighters");

            gameService.save(game);

            String result = gameService.delete(game.getId());
            assertEquals("Personagem deletado com sucesso!", result);

            Optional<Game> deletedGame = gameRepository.findById(game.getId());
            assertTrue(deletedGame.isEmpty());
        }

        // Teste de integração para buscar jogos por nome
        @Test
        public void testFindByNomeIntegration() {
            Game game1 = new Game();
            game1.setName("Guilty Gear");

            Game game2 = new Game();
            game2.setName("Guilty Gear Strive");

            gameService.save(game1);
            gameService.save(game2);

            List<Game> result = gameService.findByNome("Guilty Gear");

            assertEquals(2, result.size());
        }
    }
}

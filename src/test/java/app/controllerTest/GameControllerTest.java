//package app.controllerTest;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import app.controller.GameController;
//import app.entity.Game;
//import app.services.GameService;
//
//@SpringBootTest
//public class GameControllerTest {
//
//    @Autowired
//    private GameController gameController;
//
//    @MockBean
//    private GameService gameService;
//
//    @BeforeEach
//    public void setup() {
//        Game mockGame = new Game();
//        mockGame.setId(1L);
//        mockGame.setNome("Street Fighter");
//
//        List<Game> mockGames = new ArrayList<>();
//        mockGames.add(mockGame);
//
//        when(gameService.findAll()).thenReturn(mockGames);
//        when(gameService.findById(1L)).thenReturn(mockGame);
//        when(gameService.findByNome("Street")).thenReturn(mockGames);
//    }
//
//    @Test
//    @DisplayName("Unitário - Salvar Jogo")
//    public void testSaveGameUnit() {
//        Game newGame = new Game();
//        newGame.setNome("Tekken");
//
//        when(gameService.save(newGame)).thenReturn("Jogo cadastrado com sucesso!");
//
//        ResponseEntity<String> response = gameController.save(newGame);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Jogo cadastrado com sucesso!", response.getBody());
//    }
//
//    @Test
//    @DisplayName("Unitário - Atualizar Jogo")
//    public void testUpdateGameUnit() {
//        Game updatedGame = new Game();
//        updatedGame.setNome("Mortal Kombat");
//
//        when(gameService.update(updatedGame, 1L)).thenReturn("Jogo atualizado com sucesso!");
//
//        ResponseEntity<String> response = gameController.update(updatedGame, 1L);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Jogo atualizado com sucesso!", response.getBody());
//    }
//
//    @Test
//    @DisplayName("Unitário - Buscar Jogo por ID")
//    public void testFindGameByIdUnit() {
//        when(gameService.findById(99L)).thenThrow(new RuntimeException("Jogo não encontrado"));
//
//        ResponseEntity<Game> response = gameController.findById(99L);
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals(null, response.getBody());
//    }
//
//    @Test
//    @DisplayName("Unitário - Deletar Jogo")
//    public void testDeleteGameUnit() {
//        when(gameService.delete(1L)).thenReturn("Jogo deletado com sucesso!");
//
//        ResponseEntity<String> response = gameController.delete(1L);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Jogo deletado com sucesso!", response.getBody());
//    }
//
//    @Test
//    @DisplayName("Unitário - Buscar Jogo por Nome - Falha")
//    public void testFindGameByNomeUnit() {
//        when(gameService.findByNome("NonExistent")).thenThrow(new RuntimeException("Jogo não encontrado"));
//
//        ResponseEntity<List<Game>> response = gameController.findByNome("NonExistent");
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals(null, response.getBody());
//    }
//
//    @Test
//    @DisplayName("INTEGRAÇÃO - FindAll")
//    public void testFindAllGames() {
//        ResponseEntity<List<Game>> response = gameController.findAll();
//        List<Game> games = response.getBody();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(1, games.size());
//        assertEquals("Street Fighter", games.get(0).getNome());
//    }
//
//    @Test
//    @DisplayName("INTEGRAÇÃO - FindById ")
//    public void testFindGameById() {
//        ResponseEntity<Game> response = gameController.findById(1L);
//        Game game = response.getBody();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(1L, game.getId());
//        assertEquals("Street Fighter", game.getNome());
//    }
//
//    @Test
//    @DisplayName("INTEGRAÇÃO - FindByNome")
//    public void testFindGameByNome() {
//        ResponseEntity<List<Game>> response = gameController.findByNome("Street");
//        List<Game> games = response.getBody();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(1, games.size());
//        assertEquals("Street Fighter", games.get(0).getNome());
//    }
//}
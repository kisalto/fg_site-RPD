package app.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.controller.FighterController;
import app.entity.Fighter;
import app.services.FighterService;

public class FighterControllerTest {

    @Mock
    private FighterService fighterService;

    @InjectMocks
    private FighterController fighterController;

    private Fighter fighter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fighter = new Fighter();
        fighter.setId(1L);
        fighter.setNome("Ryu");
    }

    @Test
    @DisplayName("Salvar Fighter - Deve retornar mensagem de sucesso")
    void testSaveFighter() {
        when(fighterService.save(fighter)).thenReturn("Personagem Cadastrado");

        ResponseEntity<String> response = fighterController.save(fighter);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Personagem Cadastrado", response.getBody());
    }

    @Test
    @DisplayName("Atualizar Fighter - Deve retornar mensagem de sucesso")
    void testUpdateFighter() {
        when(fighterService.update(fighter, 1L)).thenReturn("Personagem Atualizado");

        ResponseEntity<String> response = fighterController.update(fighter, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Personagem Atualizado", response.getBody());
    }

    @Test
    @DisplayName("Buscar Fighter por ID - Deve retornar Fighter correto")
    void testFindById() {
        when(fighterService.findById(1L)).thenReturn(fighter);

        ResponseEntity<Fighter> response = fighterController.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(fighter, response.getBody());
    }

    @Test
    @DisplayName("Buscar Todos os Fighters - Deve retornar lista de Fighters")
    void testFindAll() {
        List<Fighter> fighters = Arrays.asList(fighter);
        when(fighterService.findAll()).thenReturn(fighters);

        ResponseEntity<List<Fighter>> response = fighterController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(fighters, response.getBody());
    }

    @Test
    @DisplayName("Deletar Fighter por ID - Deve retornar mensagem de sucesso")
    void testDeleteFighter() {
        when(fighterService.delete(1L)).thenReturn("Personagem Deletado");

        ResponseEntity<String> response = fighterController.delete(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Personagem Deletado", response.getBody());
    }

    @Test
    @DisplayName("Buscar Fighter por Nome - Deve retornar lista de Fighters")
    void testFindByNome() {
        List<Fighter> fighters = Arrays.asList(fighter);
        when(fighterService.findByNome("Ryu")).thenReturn(fighters);

        ResponseEntity<List<Fighter>> response = fighterController.findByNome("Ryu");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(fighters, response.getBody());
    }

    @Test
    @DisplayName("Buscar Fighter por Nome de Jogo - Deve retornar lista de Fighters")
    void testFindByGameNome() {
        List<Fighter> fighters = Arrays.asList(fighter);
        when(fighterService.findByGameNome("Street Fighter")).thenReturn(fighters);

        ResponseEntity<List<Fighter>> response = fighterController.findByGameNome("Street Fighter");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(fighters, response.getBody());
    }
}

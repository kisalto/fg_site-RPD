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
    @DisplayName("Salvar Fighter")
    void testSaveFighter() {
        when(fighterService.save(fighter)).thenReturn("Personagem Cadastrado");

        ResponseEntity<String> response = fighterController.save(fighter);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Personagem Cadastrado", response.getBody());
    }
    @Test
    @DisplayName("Salvar Fighter - Erro")
    void testSaveFighterError() {
        when(fighterService.save(fighter)).thenThrow(new RuntimeException("Erro ao salvar"));

        ResponseEntity<String> response = fighterController.save(fighter);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Deu erro!Erro ao salvar", response.getBody());
    }

    @Test
    @DisplayName("Atualizar Fighter")
    void testUpdateFighter() {
        when(fighterService.update(fighter, 1L)).thenReturn("Personagem Atualizado");

        ResponseEntity<String> response = fighterController.update(fighter, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Personagem Atualizado", response.getBody());
    }
    @Test
    @DisplayName("Atualizar Fighter - Erro")
    void testUpdateFighterError() {
        when(fighterService.update(fighter, 1L)).thenThrow(new RuntimeException("Erro ao atualizar"));

        ResponseEntity<String> response = fighterController.update(fighter, 1L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Deu erro!Erro ao atualizar", response.getBody());
    }

    @Test
    @DisplayName("Buscar Fighter por ID")
    void testFindById() {
        when(fighterService.findById(1L)).thenReturn(fighter);

        ResponseEntity<Fighter> response = fighterController.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(fighter, response.getBody());
    }
    @Test
    @DisplayName("Buscar Fighter por ID - Falha")
    void testFindByIdError() {
        when(fighterService.findById(1L)).thenThrow(new RuntimeException("Fighter não encontrado"));

        ResponseEntity<Fighter> response = fighterController.findById(1L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    @DisplayName("FindAll")
    void testFindAll() {
        List<Fighter> fighters = Arrays.asList(fighter);
        when(fighterService.findAll()).thenReturn(fighters);

        ResponseEntity<List<Fighter>> response = fighterController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(fighters, response.getBody());
    }
    @Test
    @DisplayName("FindAll - Falha")
    void testFindAllError() {
        when(fighterService.findAll()).thenThrow(new RuntimeException("Erro ao buscar fighters"));

        ResponseEntity<List<Fighter>> response = fighterController.findAll();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    @DisplayName("Deletar Fighter por ID")
    void testDeleteFighter() {
        when(fighterService.delete(1L)).thenReturn("Personagem Deletado");

        ResponseEntity<String> response = fighterController.delete(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Personagem Deletado", response.getBody());
    }
    @Test
    @DisplayName("Deletar Fighter por ID - Falha")
    void testDeleteFighterError() {
        when(fighterService.delete(1L)).thenThrow(new RuntimeException("Erro ao deletar"));

        ResponseEntity<String> response = fighterController.delete(1L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

//    @Test
//    @DisplayName("Buscar Fighter por Nome")
//    void testFindByNome() {
//        List<Fighter> fighters = Arrays.asList(fighter);
//        when(fighterService.findByNome("Ryu")).thenReturn(fighters);
//
//        ResponseEntity<List<Fighter>> response = fighterController.findByNome("Ryu");
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(fighters, response.getBody());
//    }
//    @Test
//    @DisplayName("Buscar Fighter por Nome - Falha")
//    void testFindByNomeError() {
//        when(fighterService.findByNome("Ryu")).thenThrow(new RuntimeException("Erro ao buscar por nome"));
//
//        ResponseEntity<List<Fighter>> response = fighterController.findByNome("Ryu");
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals(null, response.getBody());
//    }

    @Test
    @DisplayName("Buscar Fighter por Nome do JOGO")
    void testFindByGameNome() {
        List<Fighter> fighters = Arrays.asList(fighter);
        when(fighterService.findByGameNome("Street Fighter")).thenReturn(fighters);

        ResponseEntity<List<Fighter>> response = fighterController.findByGameNome("Street Fighter");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(fighters, response.getBody());
    }
    @Test
    @DisplayName("Buscar Fighter por Nome de Jogo - Falha")
    void testFindByGameNomeError() {
        when(fighterService.findByGameNome("Street Fighter")).thenThrow(new RuntimeException("Erro ao buscar por jogo"));

        ResponseEntity<List<Fighter>> response = fighterController.findByGameNome("Street Fighter");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(null, response.getBody());
    }
}

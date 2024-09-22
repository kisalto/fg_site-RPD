package app.test;

import static org.junit.jupiter.api.Assertions.*;
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

import app.controller.FighterController;
import app.entity.Fighter;
import app.services.FighterService;

public class FighterControllerTest {

	 @InjectMocks
	    private FighterController fighterController;

	    @Mock
	    private FighterService fighterService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }
	    // Teste para o método save()
	    @Test
	    void testSaveFighter() {
	        Fighter fighter = new Fighter();
	        when(fighterService.save(fighter)).thenReturn("Fighter saved!");

	        ResponseEntity<String> response = fighterController.save(fighter);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals("Fighter saved!", response.getBody());
	    }

	    // Teste para o método update()
	    @Test
	    void testUpdateFighter() {
	        Fighter fighter = new Fighter();
	        long id = 1L;
	        when(fighterService.update(fighter, id)).thenReturn("Fighter updated!");

	        ResponseEntity<String> response = fighterController.update(fighter, id);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals("Fighter updated!", response.getBody());
	    }

	    // Teste para o método findById()
	    @Test
	    void testFindById() {
	        long id = 1L;
	        Fighter fighter = new Fighter();
	        when(fighterService.findById(id)).thenReturn(fighter);

	        ResponseEntity<Fighter> response = fighterController.findById(id);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertNotNull(response.getBody());
	    }

	    // Teste para o método findAll()
	    @Test
	    void testFindAll() {
	        List<Fighter> fighters = new ArrayList<>();
	        fighters.add(new Fighter());
	        when(fighterService.findAll()).thenReturn(fighters);

	        ResponseEntity<List<Fighter>> response = fighterController.findAll();
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertFalse(response.getBody().isEmpty());
	    }

	    // Teste para o método delete()
	    @Test
	    void testDeleteFighter() {
	        long id = 1L;
	        when(fighterService.delete(id)).thenReturn("Fighter deleted!");

	        ResponseEntity<String> response = fighterController.delete(id);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals("Fighter deleted!", response.getBody());
	    }

	    // Teste para o método findByNome()
	    @Test
	    void testFindByNome() {
	        String nome = "Ryu";
	        List<Fighter> fighters = new ArrayList<>();
	        fighters.add(new Fighter());
	        when(fighterService.findByNome(nome)).thenReturn(fighters);

	        ResponseEntity<List<Fighter>> response = fighterController.findByNome(nome);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertFalse(response.getBody().isEmpty());
	    }

	    // Teste para o método findByGameNome()
	    @Test
	    void testFindByGameNome() {
	        String gameNome = "Street Fighter";
	        List<Fighter> fighters = new ArrayList<>();
	        fighters.add(new Fighter());
	        when(fighterService.findByGameNome(gameNome)).thenReturn(fighters);

	        ResponseEntity<List<Fighter>> response = fighterController.findByGameNome(gameNome);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertFalse(response.getBody().isEmpty());
	    }
}

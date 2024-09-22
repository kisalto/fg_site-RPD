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

import app.entity.Fighter;
import app.repository.FighterRepository;
import app.services.FighterService;

public class FighterServiceTest {

    @InjectMocks
    private FighterService fighterService;

    @Mock
    private FighterRepository fighterRepository;

    private Fighter fighter1;
    private Fighter fighter2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        fighter1 = new Fighter(1L, "Ryu", "Lutador de artes marciais", "Tipo 1", null, null);
        fighter2 = new Fighter(2L, "Chun-Li", "Lutadora especialista em chutes", "Tipo 2", null, null);
    }

    // ====================== Testes Unitários ======================

    @Test
    public void testSaveFighter() {
        when(fighterRepository.save(fighter1)).thenReturn(fighter1);
        String result = fighterService.save(fighter1);
        assertEquals("Personagem Cadastrado", result);
    }

    @Test
    public void testUpdateFighter() {
        when(fighterRepository.save(fighter1)).thenReturn(fighter1);
        String result = fighterService.update(fighter1, 1L);
        assertEquals("Personagem Atualizado", result);
        assertEquals(1L, fighter1.getId());
    }

    @Test
    public void testFindById_Success() {
        when(fighterRepository.findById(1L)).thenReturn(Optional.of(fighter1));
        Fighter result = fighterService.findById(1L);
        assertNotNull(result);
        assertEquals(fighter1.getNome(), result.getNome());
    }

    @Test
    public void testFindById_NotFound() {
        when(fighterRepository.findById(3L)).thenReturn(Optional.empty());
        Fighter result = fighterService.findById(3L);
        assertNull(result);
    }

    @Test
    public void testFindAll() {
        when(fighterRepository.findAll()).thenReturn(Arrays.asList(fighter1, fighter2));
        List<Fighter> fighters = fighterService.findAll();
        assertEquals(2, fighters.size());
        assertEquals("Ryu", fighters.get(0).getNome());
        assertEquals("Chun-Li", fighters.get(1).getNome());
    }

    @Test
    public void testDeleteFighter() {
        doNothing().when(fighterRepository).deleteById(1L);
        String result = fighterService.delete(1L);
        assertEquals("Personagem Deletado", result);
    }

    @Test
    public void testFindByNome() {
        when(fighterRepository.findByNomeStartsWith("Ryu")).thenReturn(Arrays.asList(fighter1));
        List<Fighter> fighters = fighterService.findByNome("Ryu");
        assertEquals(1, fighters.size());
        assertEquals("Ryu", fighters.get(0).getNome());
    }

    @Test
    public void testFindByGameNome() {
        when(fighterRepository.findByGameNomeContains("Street Fighter")).thenReturn(Arrays.asList(fighter1, fighter2));
        List<Fighter> fighters = fighterService.findByGameNome("Street Fighter");
        assertEquals(2, fighters.size());
    }

    // ====================== Testes de Integração ======================

    @Test
    public void integrationTestSaveFighter() {
        Fighter newFighter = new Fighter();
        newFighter.setNome("Ken");
        newFighter.setDescricao("Lutador de artes marciais");
        newFighter.setType("Tipo 1");

        String result = fighterService.save(newFighter);
        assertEquals("Personagem Cadastrado", result);
    }

    @Test
    public void integrationTestFindById() {
        Fighter result = fighterService.findById(1L);
        assertNotNull(result);
    }

    @Test
    public void integrationTestFindAll() {
        List<Fighter> fighters = fighterService.findAll();
        assertTrue(fighters.size() > 0);
    }

    @Test
    public void integrationTestDeleteFighter() {
        String result = fighterService.delete(1L);
        assertEquals("Personagem Deletado", result);
    }
}

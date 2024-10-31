package app.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import app.entity.Fighter;
import app.repository.FighterRepository;
import app.services.FighterService;

public class FighterServiceTest {

    @Mock
    private FighterRepository fighterRepository;

    @InjectMocks
    private FighterService fighterService;

    private Fighter fighter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fighter = new Fighter();
        fighter.setId(1L);
        fighter.setNome("Ryu");
    }

    @Test
    @DisplayName("Salvar Fighter - Deve salvar e retornar mensagem de sucesso")
    void testSaveFighter() {
        when(fighterRepository.save(fighter)).thenReturn(fighter);

        String result = fighterService.save(fighter);

        assertEquals("Personagem Cadastrado", result);
        verify(fighterRepository, times(1)).save(fighter);
    }

    @Test
    @DisplayName("Atualizar Fighter - Deve atualizar e retornar mensagem de sucesso")
    void testUpdateFighter() {
        when(fighterRepository.save(fighter)).thenReturn(fighter);

        String result = fighterService.update(fighter, 1L);

        assertEquals("Personagem Atualizado", result);
        verify(fighterRepository, times(1)).save(fighter);
    }

    @Test
    @DisplayName("Buscar Fighter por ID - Deve retornar Fighter correto")
    void testFindById() {
        when(fighterRepository.findById(1L)).thenReturn(Optional.of(fighter));

        Fighter result = fighterService.findById(1L);

        assertEquals(fighter, result);
    }

    @Test
    @DisplayName("Buscar Todos os Fighters - Deve retornar lista de Fighters")
    void testFindAll() {
        List<Fighter> fighters = Arrays.asList(fighter);
        when(fighterRepository.findAll()).thenReturn(fighters);

        List<Fighter> result = fighterService.findAll();

        assertEquals(fighters, result);
    }

    @Test
    @DisplayName("Deletar Fighter por ID - Deve deletar e retornar mensagem de sucesso")
    void testDeleteFighter() {
        doNothing().when(fighterRepository).deleteById(1L);

        String result = fighterService.delete(1L);

        assertEquals("Personagem Deletado", result);
        verify(fighterRepository, times(1)).deleteById(1L);
    }

//    @Test
//    @DisplayName("Buscar Fighters por Nome - Deve retornar lista de Fighters")
//    void testFindByNome() {
//        List<Fighter> fighters = Arrays.asList(fighter);
//        when(fighterRepository.findByNome("Ryu")).thenReturn(fighters);
//
//        List<Fighter> result = fighterService.findByNome("Ryu");
//
//        assertEquals(fighters, result);
//    }

    @Test
    @DisplayName("Buscar Fighters por Nome do Jogo - Deve retornar lista de Fighters")
    void testFindByGameNome() {
        List<Fighter> fighters = Arrays.asList(fighter);
        when(fighterRepository.findByGameNomeContainsOrderByNomeAsc("Street Fighter")).thenReturn(fighters);

        List<Fighter> result = fighterService.findByGameNome("Street Fighter");

        assertEquals(fighters, result);
    }
}

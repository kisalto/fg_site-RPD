package app.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

import app.entity.Guide;
import app.entity.User;
import app.exception.NaoVeterano;
import app.repository.GuideRepository;
import app.services.GuideService;
import app.services.UserService;

public class GuideServiceTest {
    
    @InjectMocks
    GuideService guideService = new GuideService();
    
    @Mock
    GuideRepository guideRepository;

    @Mock
    UserService userService;

    Guide guide;
    User user;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        
        user = new User();
        user.setId(1L);
        user.setApelido("Veterano_Teste");
        user.setEmail("veterano@gmail.com");
        user.setIsMod(false);
        user.setIsVet(true); 
        
        guide = new Guide();
        guide.setId(1L);
        guide.setUser(user);
        
        when(userService.findById(1L)).thenReturn(user);
        when(guideRepository.save(any(Guide.class))).thenReturn(guide);
        when(guideRepository.findById(1L)).thenReturn(Optional.of(guide));
        when(guideRepository.findAll()).thenReturn(new ArrayList<>(List.of(guide)));
    }

    @Test
    @DisplayName("Teste criar guia com usuário veterano")
    void CreateVetOnTest() {
        String retorno = guideService.save(guide);
        assertEquals("Usuário criado com sucesso!", retorno);
    }

    @Test
    @DisplayName("Teste criar guia com usuário não veterano")
    void CreateVetOffTest() {
        user.setIsVet(false);  

        Exception exception = assertThrows(NaoVeterano.class, () -> {
            guideService.save(guide);
        });

        assertEquals("Voce precisa ser um usuario veterano para cadastrar um guia", exception.getMessage());
    }

    @Test
    @DisplayName("Teste atualizar guia com usuário veterano")
    void UpdateVetOnTest() {
        String retorno = guideService.update(guide, 1L);
        assertEquals("Usuário atualizado com sucesso!", retorno);
    }

    @Test
    @DisplayName("Teste atualizar guia com usuário não veterano")
    void UpdateVetOffTest() {
        user.setIsVet(false);  

        Exception exception = assertThrows(NaoVeterano.class, () -> {
            guideService.update(guide, 1L);
        });

        assertEquals("Voce precisa ser um usuario veterano para cadastrar um guia", exception.getMessage());
    }

    @Test
    @DisplayName("Teste busca guia por ID")
    void FindByIdTest() {
        Guide guiaEncontrado = guideService.findById(1L);
        assertEquals(guide, guiaEncontrado);
    }

    @Test
    @DisplayName("Teste busca guia por ID inexistente")
    void FindByIdFalseTest() {
        when(guideRepository.findById(99L)).thenReturn(Optional.empty());
        
        Guide guiaEncontrado = guideService.findById(99L);
        assertEquals(null, guiaEncontrado);
    }

    @Test
    @DisplayName("Teste encontrar todos os guias")
    void FindAllTest() {
        List<Guide> guias = guideService.findAll();
        assertEquals(1, guias.size());
        assertEquals(guide, guias.get(0));
    }

    @Test
    @DisplayName("Teste deletar guia")
    void DeleteTest() {
        guideService.delete(1L);
        when(guideRepository.findById(1L)).thenReturn(Optional.of(guide));
    }

}

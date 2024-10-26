package app.controllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import app.controller.GuideController;
import app.entity.Guide;
import app.entity.User;
import app.services.GuideService;

@WebMvcTest(GuideController.class)
public class GuideControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuideService guideService;

    private Guide guide;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        guide = new Guide();
        guide.setId(1L);
        guide.setUser(new User()); 

        when(guideService.findById(1L)).thenReturn(guide);
        when(guideService.save(any(Guide.class))).thenReturn("Guia criado com sucesso!");
        when(guideService.findAll()).thenReturn(new ArrayList<>(List.of(guide)));
    }

    @Test
    @DisplayName("Teste Create guia")
    void SaveTest() throws Exception {
        mockMvc.perform(post("/api/rdp/guide/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"user\": { \"id\": 1 } }")) 
                .andExpect(status().isOk())
                .andExpect(content().string("Guia criado com sucesso!"));
    }

    @Test
    @DisplayName("Teste Update guia")
    void UpdateTest() throws Exception {
        Mockito.when(guideService.update(Mockito.any(Guide.class), Mockito.anyLong()))
                .thenReturn("Guia atualizado com sucesso!");

        mockMvc.perform(put("/api/rdp/guide/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"user\": { \"id\": 1 } }")) 
                .andExpect(status().isOk())
                .andExpect(content().string("Guia atualizado com sucesso!"));
    }

    @Test
    @DisplayName("Teste FindById guia")
    void FindByIdTest() throws Exception {
        mockMvc.perform(get("/api/rdp/guide/findById/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"user\":{}}")); 
    }

    @Test
    @DisplayName("Teste FindAll guias")
    void FindAllTest() throws Exception {
        List<Guide> lista = new ArrayList<>();
        lista.add(guide);
        when(guideService.findAll()).thenReturn(lista);
        
        mockMvc.perform(get("/api/rdp/guide/findAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"user\":{}}]"));
    }

    @Test
    @DisplayName("Teste Delete guia")
    void DeleteTest() throws Exception {
        mockMvc.perform(delete("/api/rdp/guide/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Guia excluído com sucesso!"));
    }

    @Test
    @DisplayName("Teste Delete guia inexistente")
    void DeleteNonExistentTest() throws Exception {
        Mockito.doThrow(new RuntimeException("Guia não encontrado")).when(guideService).delete(99L);

        mockMvc.perform(delete("/api/rdp/guide/delete/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Erro ao deletar usuárioGuia não encontrado"));
    }
}

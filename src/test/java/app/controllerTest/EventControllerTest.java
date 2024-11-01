package app.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.controller.EventController;
import app.entity.Event;
import app.entity.Game;
import app.entity.User;
import app.exception.UsuarioInexistente;
import app.services.EventService;

@SpringBootTest
public class EventControllerTest {

    @Autowired
    EventController eventController;

    @MockBean
    EventService eventService;

    private Event event;

    @BeforeEach
    void setup() {

        // Criando um usuário para o evento
        User user = new User();
        user.setId(1L);
        user.setApelido("Josheetos");
        user.setEmail("Josheetos@gmail.com");
        user.setSenha("bobProgramador");
        user.setData_reg("28/09/2024");
        user.setIsMod(true);
        user.setIsVet(true);

        // Criando um jogo para o evento
        Game game = new Game();
        game.setId(1L);
        game.setNome("The King of fighters 2009");
        game.setDescricao("Jogo de luta");
        game.setLink("https://steam.com");
        game.setPreco(20.00D);

        // Criando o evento
        event = new Event();
        event.setId(1L);
        event.setTitulo("Campeonato estadual de KOF");
        event.setDescricao("Campeonato de veteranos de KOF 2009");
        event.setLink("https://Kof_play.com.br");
        event.setDate("12/10/2024");

        ArrayList<User> usuarios = new ArrayList<>();
        ArrayList<Game> games = new ArrayList<>();

        usuarios.add(user);
        games.add(game);

        event.setUser(usuarios);
        event.setGame(games);

  
        Mockito.when(eventService.save(Mockito.any(Event.class))).thenReturn("Evento criado com sucesso");
    }

    @Test
    @DisplayName("Teste -- Criar Evento")
    void tentarCriarEvento() {

        ResponseEntity<String> retorno = eventController.save(event);

        assertEquals(HttpStatus.OK, retorno.getStatusCode());
        
        assertEquals("Evento criado com sucesso", retorno.getBody());
        
        Mockito.verify(eventService).save(Mockito.any(Event.class));
    }
    
    @Test
    @DisplayName("Teste -- Falha ao Criar evento")
    void FalhaCriarEvento() {
    	
    	Mockito.when(eventService.save(Mockito.any(Event.class)))
    	.thenThrow( new RuntimeException("Erro ao criar evento"));
    	
    	ResponseEntity<String> retorno = eventController.save(event);
    	
    	assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
    	assertEquals("Erro ao criar evento", retorno.getBody());
    	
    	Mockito.verify(eventService).save(Mockito.any(Event.class));
    }
    @Test
    @DisplayName("Teste -- Atualizar um evento")
    void AtualizarEvento() {
    	
    	Mockito.when(eventService.update(Mockito.any(Event.class), Mockito.anyLong()))
    	.thenReturn("Evento Atualizado com sucesso");
    	
    	ResponseEntity<String> retorno = eventController.update(event, 1L);
    	
    	assertEquals(HttpStatus.OK, retorno.getStatusCode());
    	assertEquals("Evento Atualizado com sucesso", retorno.getBody());
    	
    	Mockito.verify(eventService).update(Mockito.any(Event.class), Mockito.anyLong() );
    	
    }
    @Test
    @DisplayName("Teste -- Falha ao atualizar um evento")
    void AtualizarEventoFalha() {
    	
    	Mockito.when(eventService.update(Mockito.any(Event.class), Mockito.anyLong()))
    	.thenThrow(new RuntimeException("Erro ao Atualizar o evento"));
    	
    	ResponseEntity<String> retorno = eventController.update(event, 1L);
    	
    	assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
    	assertEquals("Erro ao Atualizar o evento", retorno.getBody());
    	
    	Mockito.verify(eventService).update(Mockito.any(Event.class), Mockito.anyLong());
    }
    
    @Test
    @DisplayName("Teste -- Tentar deletar um evento")
    void DeletarEvento() {
    	
    	String msg = "Deletado com sucesso";
    	when(eventService.delete(Mockito.anyLong())).thenReturn(msg);
    	
    	ResponseEntity<String> retorno = eventController.delete(1L);
    	
    	assertEquals(HttpStatus.OK, retorno.getStatusCode());
    	assertEquals("Deletado com sucesso", retorno.getBody());
    	
    	Mockito.verify(eventService).delete(Mockito.anyLong());
    	
    }
    
    @Test
    @DisplayName("Teste -- Falha ao deletar um evento")
    void DeletarEventoFalha() {
    	String msgErro = "Evento não encontrado";
    	
    	when(eventService.delete(Mockito.anyLong()))
    	.thenThrow(new RuntimeException(msgErro));
    	
    	ResponseEntity<String> retorno = eventController.delete(1L);
    	
    	assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
    	assertEquals(msgErro, retorno.getBody());
    	
    	Mockito.verify(eventService).delete(Mockito.anyLong());
    }
    @Test
    @DisplayName("Teste -- Buscar todos os eventos")
    void BuscarTodosEventos() {
        // Criar usuários
        User user1 = new User();
        user1.setId(1L);
        user1.setApelido("Josheetos");
        user1.setEmail("Josheetos@gmail.com");
        user1.setSenha("bobProgramador");
        user1.setData_reg("28/09/2024");
        user1.setIsMod(true);
        user1.setIsVet(true);

        User user2 = new User();
        user2.setId(2L);
        user2.setApelido("Josh");
        user2.setEmail("Joshetos@gmail.com");
        user2.setSenha("bobao");
        user2.setData_reg("28/08/2024");
        user2.setIsMod(true);
        user2.setIsVet(true);

        // Criar jogos
        Game game1 = new Game();
        game1.setId(1L);
        game1.setNome("The King of Fighters 2009");
        game1.setDescricao("Jogo de luta");
        game1.setLink("https://steam.com");
        game1.setPreco(20.00D);

        Game game2 = new Game();
        game2.setId(2L);
        game2.setNome("The King of Fighters 2011");
        game2.setDescricao("Jogo de luta");
        game2.setLink("https://steam.com");
        game2.setPreco(20.00D);

        ArrayList<User> usuarios = new ArrayList<>();
        usuarios.add(user1);
        usuarios.add(user2);

        ArrayList<Game> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);

        ArrayList<Event> eventos = new ArrayList<>();
        eventos.add(new Event(1L, "Evento 1", "Descricao 1", "www.game.com", "03/02/2021", games, usuarios));
        eventos.add(new Event(2L, "Campeonato estadual de KOF", "Campeonato de veteranos de KOF 2009", "https://Kof_play.com.br", "12/10/2024", games, usuarios));

        when(eventService.findAll()).thenReturn(eventos);

        ResponseEntity<List<Event>> retorno = eventController.findAll();

        assertEquals(HttpStatus.OK, retorno.getStatusCode());

        assertEquals(2, retorno.getBody().size());
        assertEquals("Evento 1", retorno.getBody().get(0).getTitulo());
        assertEquals("Campeonato estadual de KOF", retorno.getBody().get(1).getTitulo());
        
        Mockito.verify(eventService).findAll();

    }
    
    @Test
    @DisplayName("Teste -- Falha ao buscar os eventos")
    void BuscarTodosEventosFalha() {
    	
    	when(eventService.findAll()).
    	thenThrow(new RuntimeException("Erro ao Encontrar os eventos"));
    	
    	ResponseEntity<List<Event>> retorno = eventController.findAll();
    	
    	assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
    	
    	assertEquals(null, retorno.getBody());
    }
    @Test
    @DisplayName("Teste -- Buscar por id")
    void buscarEventoPorIdSucesso() {
    	Mockito.when(eventService.findById(1L)).thenReturn(event);
        ResponseEntity<Event> response = eventController.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(event.getTitulo(), response.getBody().getTitulo());
        assertEquals(event.getDescricao(), response.getBody().getDescricao());
    }

    @Test
    @DisplayName("Teste -- Falha ao Buscar Evento por id")
    void buscarEventoPorIdFalha() {
    	Mockito.when(eventService.findById(9999L)).thenThrow(new UsuarioInexistente("Usuario não Existir"));
    	
        ResponseEntity<Event> response = eventController.findById(9999L);
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        
    }
    @Test
    @DisplayName("Teste -- Buscar os últimos 5 eventos")
    void buscarUltimos5Eventos() {
        
        List<Event> eventos = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Event event = new Event();
            event.setId((long) i);
            event.setTitulo("Evento " + i);
            event.setDate("2024-09-" + i); 
            eventos.add(event);
        }

        
        Mockito.when(eventService.findLast5()).thenReturn(eventos);

       
        ResponseEntity<List<Event>> response = eventController.findLast5();

     
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5, response.getBody().size());
        assertEquals("Evento 1", response.getBody().get(0).getTitulo());

        Mockito.verify(eventService).findLast5();
    }

    @Test
    @DisplayName("Teste -- Falha ao buscar os últimos 5 eventos")
    void buscarUltimos5EventosFalha() {
        
        Mockito.when(eventService.findLast5()).thenThrow(new RuntimeException("Erro ao buscar os últimos eventos"));

        
        ResponseEntity<List<Event>> response = eventController.findLast5();

       
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody()); 
    }
    @Test
    @DisplayName("Teste -- Nenhum evento encontrado")
    void buscarUltimos5EventosVazio() {
        Mockito.when(eventService.findLast5()).thenReturn(new ArrayList<>()); 
       
        ResponseEntity<List<Event>> response = eventController.findLast5();

     
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody()); 
        assertTrue(response.getBody().isEmpty()); 
    }

}

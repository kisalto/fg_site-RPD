package app.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import app.entity.Event;
import app.entity.User;
import app.exception.NaoVeterano;
import app.repository.EventRepository;
import app.services.EventService;
import app.services.UserService;

public class EventServiceTest {

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private UserService userService;

    private Event event;
    private User user;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        
        user = new User();
        user.setId(1L);
        user.setIsVet(true);
        
        event = new Event();
        event.setId(1L);
        event.setUser(Arrays.asList(user));
    }

    @Test
    void saveEventSuccess() {
        when(userService.findById(1L)).thenReturn(user);
        when(eventRepository.save(event)).thenReturn(event);

        String result = eventService.save(event);
        
        assertEquals("Evento criado com sucesso", result);
        verify(eventRepository).save(event);
    }

    @Test
    void saveEventNaoVeterano() {
        user.setIsVet(false);
        when(userService.findById(1L)).thenReturn(user);

        Exception exception = assertThrows(NaoVeterano.class, () -> {
            eventService.save(event);
        });

        assertEquals("Voce precisa ser um usuario veterano para cadastrar um evento", exception.getMessage());
    }

    @Test
    void updateEventSuccess() {
        when(userService.findById(1L)).thenReturn(user);
        when(eventRepository.save(event)).thenReturn(event);

        String result = eventService.update(event, 1L);
        
        assertEquals("Evento atualizado com sucesso", result);
        verify(eventRepository).save(event);
    }

    @Test
    void deleteEventSuccess() {
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
        when(userService.findById(1L)).thenReturn(user);
        
        String result = eventService.delete(1L);
        
        assertEquals("Evento deletado!", result);
        verify(eventRepository).deleteById(1L);
    }

    @Test
    void findByIdEventSuccess() {
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        Event foundEvent = eventService.findById(1L);
        
        assertEquals(event, foundEvent);
    }

    @Test
    void findAllEventsSuccess() {
        when(eventRepository.findAll()).thenReturn(Arrays.asList(event));

        List<Event> events = eventService.findAll();
        
        assertEquals(1, events.size());
        assertEquals(event, events.get(0));
    }

    @Test
    void findLast5EventsSuccess() {
        when(eventRepository.findFirst5ByOrderByDateDesc()).thenReturn(Arrays.asList(event));

        List<Event> events = eventService.findLast5();
        
        assertEquals(1, events.size());
        assertEquals(event, events.get(0));
    }
}

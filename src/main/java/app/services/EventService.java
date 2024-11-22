package app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.auth.User;
import app.entity.Event;
import app.exception.UsuarioInexistente;
import app.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private UserService userService;

    public String save(Event event) {
	
	if (event.getUser() == null || event.getUser().isEmpty())
	    throw new IllegalArgumentException("A lista de usuários não pode estar vazia");
	
	List<User> users = new ArrayList<>();
	
	for (int i = 0; i < event.getUser().size(); i++) {
	    users.add(userService.findById(event.getUser().get(i).getId()));
	}
	
	event.setUser(users);
	
	this.eventRepository.save(event);

	return "Evento criado com sucesso";

    }

    public String update(Event event, Long id) {


	event.setId(id);
	this.eventRepository.save(event);

	return "Evento atualizado com sucesso";
    }

    public String delete(Long id) {

	this.eventRepository.deleteById(id);

	return "Evento deletado!";
    }

    public Event findById(Long id) {
	Optional<Event> optional = this.eventRepository.findById(id);
	if (!optional.isPresent())
	    throw new UsuarioInexistente("Usuario não Existir");
	return optional.get();
    }

    public List<Event> findAll() {
	return eventRepository.findAll();
    }

    public List<Event> findLast5() {
	return eventRepository.findFirst5ByOrderByDateDesc();
    }

}

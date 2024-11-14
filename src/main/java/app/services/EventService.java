package app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Event;
import app.exception.UsuarioInexistente;
import app.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	public String save(Event event) {
		verificarPrioridade(event);

		this.eventRepository.save(event);

		return "Evento criado com sucesso";

	}

	private void verificarPrioridade(Event event) {
		if (event.getUser() == null)
			throw new UsuarioInexistente("Usuario deve Existir");

	}

	public String update(Event event, Long id) {

		verificarPrioridade(event);

		event.setId(id);
		this.eventRepository.save(event);

		return "Evento atualizado com sucesso";
	}

	public String delete(Long id) {
		Event event = findById(id);

		verificarPrioridade(event);

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

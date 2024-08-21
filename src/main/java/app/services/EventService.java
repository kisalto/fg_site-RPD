package app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Event;
import app.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	public EventRepository eventRepository;

	public String save(Event event) {

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
		if (optional.isPresent()) {
			return optional.get();

		} else
			return null;
	}
	
	public List<Event> findAll(){
		
		return eventRepository.findAll();
	}
	
	public List<Event>findLast5(){
		
		return eventRepository.findFirst5ByOrderByDateDesc();
	}
	

}

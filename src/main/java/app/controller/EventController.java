package app.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Event;
import app.services.EventService;

@RestController
@RequestMapping("/api/evento")
public class EventController {
	
	
	@Autowired
	private EventService eventService;
	
	@PostMapping("/save")
	public ResponseEntity<String>save(@RequestBody Event event){
		try {
			String msg = this.eventService.save(event);
			
			return new ResponseEntity<String>(msg, HttpStatus.OK);
			
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<String>update(@RequestBody Event event, @PathVariable Long id){
		try {
			String msg = this.eventService.update(event,id);
			
			return new ResponseEntity<String>("Evento Criado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String>delete(@PathVariable Long id){
		try {
			
				this.eventService.delete(id);
			
			return new ResponseEntity<String>("Deletado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	
		}
	}
	@GetMapping("/findAll")
	public ResponseEntity<List<Event>>findAll(){
		try {
			List<Event> lista = this.eventService.findAll();
			
			return new ResponseEntity<List<Event>>(lista, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
		
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Event>findById(@PathVariable Long id){
		try {
			Event event = this.eventService.findById(id);
			
			return new ResponseEntity<Event>(event, HttpStatus.OK);
		} catch (Exception e) {
	
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
}

package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Guide;
import app.services.GuideService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rdp/guide")
@CrossOrigin(origins = "*")
@Validated
public class GuideController {
	
	@Autowired
	GuideService guideService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save (@Valid @RequestBody Guide guide){
		try {
			
			guideService.save(guide);
			return new ResponseEntity<>("Guia criado com sucesso!", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Erro ao criar guia"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update (@Valid @RequestBody Guide guide, @PathVariable long id){
		try {
			guideService.update(guide, id);
			return new ResponseEntity<>("Guia atualizado com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro ao atualizar guia!"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Guide> findById (@PathVariable long id){
		try {
			Guide guia = guideService.findById(id);
			return new ResponseEntity<>(guia, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByTitulo/{titulo}")
	public ResponseEntity<Guide> findByTitulo (@PathVariable String titulo){
		try {
			Guide guia = guideService.findByTitulo(titulo);
			return new ResponseEntity<>(guia, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Guide>> findAll (){
		try {
			List<Guide> lista = guideService.findAll();
			return new ResponseEntity<List<Guide>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete (@Valid @PathVariable long id) {
		try {
			guideService.delete(id);
			return new ResponseEntity<>("Guia excluído com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro ao deletar usuário"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}

package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Guide;
import app.services.GuideService;


@RestController
@RequestMapping("/api/rdp_guide")
public class GuideController {
	
	@Autowired
	GuideService guideService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save (Guide guide){
		try {
			guideService.save(guide);
			return new ResponseEntity<>("Guia criado com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Erro ao criar guia"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> update (Guide guide, long id){
		try {
			guideService.update(guide, id);
			return new ResponseEntity<>("Guia atualizado com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Erro ao atualizar guia!"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/findById")
	public ResponseEntity<Guide> findById (long id){
		try {
			Guide guia = guideService.findById(id);
			return new ResponseEntity<>(guia, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Guide>> findAll (){
		try {
			List<Guide> lista = guideService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> delete (Guide guide, long id) {
		try {
			guideService.delete(guide, id);
			return new ResponseEntity<>("Guia excluído com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Erro ao deletar usuário"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}

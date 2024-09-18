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

import app.entity.Fighter;
import app.services.FighterService;


@RestController
@RequestMapping("/api/rdp/fighter")
public class FighterController {

	@Autowired
	private FighterService fighterService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Fighter fighter){
		try {
			String mensagem = this.fighterService.save(fighter);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Deu erro!"+e.getMessage(), HttpStatus.BAD_REQUEST );
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody Fighter fighter, @PathVariable long id){
		try {
			String mensagem = this.fighterService.update(fighter, id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Deu erro!"+e.getMessage(), HttpStatus.BAD_REQUEST );
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Fighter> findById(@PathVariable long id){
		try {
			Fighter fighter = this.fighterService.findById(id);
			return new ResponseEntity<>(fighter, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST );
		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Fighter>> findAll(){
		try {
			List<Fighter> lista = this.fighterService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST );
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id){
		try {
			String mensagem = this.fighterService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST );
		}
	}
	
	@GetMapping("/findByNome/{nome}")
	public ResponseEntity<List<Fighter>> findByNome(@PathVariable String nome){
		try {
			List<Fighter> lista = this.fighterService.findByNome(nome);
			return new ResponseEntity<>(lista, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST );
		}
	}
	
	@GetMapping("/findByNomeJogo/{Gamenome}")
	public ResponseEntity<List<Fighter>> findByGameNome(@PathVariable String Gamenome){
		try {
			List<Fighter> lista = this.fighterService.findByGameNome(Gamenome);
			return new ResponseEntity<>(lista, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST );
		}
	}
	
	
}


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.auth.User;
import app.services.UserService;

@RestController
@RequestMapping("/api/rdp/user")
@CrossOrigin(origins = "*")
@Validated
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody User user) {
		try {
			this.userService.save(user);
			return new ResponseEntity<>("Usuário cadastrado com sucesso!!!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro ao cadastrar usuário" + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody User user, @PathVariable long id) {
		try {
			this.userService.update(user, id);
			return new ResponseEntity<>("Usuário atualizado com sucesso!!!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro ao atualizar usuário!" + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<User> findById(@PathVariable long id) {
		try {
			User usuario = this.userService.findById(id);
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findByApelidoContains")
	public ResponseEntity<User> findByApelidoContains(@RequestParam String apelido) {
		try {
			User user = this.userService.findByApelidoContains(apelido);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("findByEmail")
	public ResponseEntity<User> findByEmail(@RequestParam String email) {
		try {
			User user = this.userService.findByEmail(email);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<User>> findAll() {
		try {
			List<User> lista = this.userService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		try {
			userService.delete(id);
			return new ResponseEntity<>("Usuário deletado com sucesso!!!", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}

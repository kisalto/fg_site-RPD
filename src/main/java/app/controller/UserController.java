package app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.User;
import app.services.UserService;

@RestController
@RequestMapping("/api/fg_site-springboot")
public class UserController {
	
	private UserService userService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save (User user){
		try {
			userService.save(user);
			return new ResponseEntity<>("Usuário cadastrado com sucesso!!!", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Erro ao cadastrar usuário"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<String> update (User user, long id){
		try {
			user.setId(id);
			return new ResponseEntity<>("Usuário atualizado com sucesso!!!", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Erro ao atualizar usuário!"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<User> findById (long id){
		try {
			User usuario = this.userService.findById(id);
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<List<User>> findByApelidoContains (String apelido){
		try {
			List<User> lista = this.userService.findByApelidoContains(apelido);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<List<User>> findByEmail (String email){
		try {
			List<User> lista = this.userService.findByEmail(email);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<List<User>> findAll (){
		try {
			List<User> lista = this.userService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<String> delete (User user,long id){
		try {
			userService.delete(user, id);
			return new ResponseEntity<>("Usuário deletado com sucesso!!!", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}

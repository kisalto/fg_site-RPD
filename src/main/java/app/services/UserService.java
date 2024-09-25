package app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.User;
import app.exception.MesmoApelido;
import app.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public String save (User user) {
		
		mesmoNome(user);
		
		this.userRepository.save(user);
		return "Usuário criado com sucesso!";
	}
	
	private void mesmoNome (User user) {
		User userExistente = findByApelido(user.getApelido());
		
		if (userExistente != null) {
			throw new MesmoApelido("Apelido ja existente");
		}
	}
	
	public String update (User user, long id) {
		user.setId(id);
		this.userRepository.save(user);
		return "Usuário atualizado com sucesso!";
	}
	
	public User findById (long id) {
		Optional<User> optional = this.userRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}
	
	public User findByApelido(String apelido) {
		return this.userRepository.findByApelido(apelido);
	}
	
	public User findByApelidoContains (String apelido){
		return this.userRepository.findByApelidoContains(apelido);
	}
	
	public User findByEmail (String email){
		return this.userRepository.findByEmail(email);
	}
	
	public List<User> findAll (){
		return this.userRepository.findAll();
	}
	
	public void delete (long id) {
		User user  = findById(id);
		this.userRepository.delete(user);
	}
}

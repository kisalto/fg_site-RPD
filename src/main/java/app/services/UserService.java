package app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.User;
import app.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public String save (User user) {
		userRepository.save(user);
		return "Usuário criado com sucesso!";
	}
	
	public Object update (User user, long id) {
		user.setId(id);
		userRepository.save(user);
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
	
	public User findByApelidoContains (String apelido){
		return userRepository.findByApelidoContains(apelido);
	}
	
	public User findByEmail (String email){
		return userRepository.findByEmail(email);
	}
	
	public List<User> findAll (){
		return userRepository.findAll();
	}
	
	public void delete (long id) {
		User user  = findById(id);
		userRepository.delete(user);
	}
}

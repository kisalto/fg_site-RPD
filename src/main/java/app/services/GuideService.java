package app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Guide;
import app.repository.GuideRepository;

@Service
public class GuideService {
	@Autowired 
	private GuideRepository guideRepository;
	
	public String save (Guide guide) {
		guideRepository.save(guide);
		return "Usuário criado com sucesso!";
	}
	
	public String update (Guide guide, long id) {
		guide.setId(id);
		guideRepository.save(guide);
		return "Usuário atualizado com sucesso!";
	}
	
	public Guide findById (long id) {
		Optional<Guide> optional = this.guideRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}
	
	public List<Guide> findAll (){
		return guideRepository.findAll();
	}
	
	public void delete (Guide guide, long id) {
		guide.setId(id);
		guideRepository.delete(guide);
	}
}

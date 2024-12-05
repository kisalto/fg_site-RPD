package app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Guide;
import app.exception.UsuarioInexistente;
import app.repository.GuideRepository;

@Service
public class GuideService {

	@Autowired
	private GuideRepository guideRepository;

	public String save(Guide guide) {
		verificarPrioridade(guide);

		this.guideRepository.save(guide);

		return "Usuário criado com sucesso!";

	}

	private void verificarPrioridade(Guide guide) {
		if (guide.getUser() == null)
			throw new UsuarioInexistente("Usuario deve existir");

	}

	public String update(Guide guide, long id) {
		verificarPrioridade(guide);

		guide.setId(id);

		this.guideRepository.save(guide);

		return "Usuário atualizado com sucesso!";

	}

	public Guide findById(long id) {
		Optional<Guide> optional = this.guideRepository.findById(id);

		if (optional.isPresent())
			return optional.get();

		else
			return null;
	}

	public List<Guide> findAll() {
		return this.guideRepository.findAll();

	}
	
	public List<Guide> findAllByGame() {
		return this.guideRepository.findAllByGame();

	}

	public void delete(long id) {

		Guide guide = findById(id);

		verificarPrioridade(guide);

		this.guideRepository.delete(guide);
	}
}

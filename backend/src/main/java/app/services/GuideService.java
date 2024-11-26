package app.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Guide;
import app.entity.User;
import app.exception.NaoVeterano;
import app.exception.UsuarioInexistente;
import app.repository.GuideRepository;

@Service
public class GuideService {

	@Autowired
	private GuideRepository guideRepository;

	@Autowired
	private UserService userService;

	public String save(Guide guide) {
		verificarPrioridade(guide);
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = currentDate.format(formatter);

		guide.setData_cr(formattedDate);
		
		this.guideRepository.save(guide);

		return "Guia criado com sucesso!";

	}

	private void verificarPrioridade(Guide guide) {
		if (guide.getUser() == null)
			throw new UsuarioInexistente("Usuario deve existir");

		User user = userService.findById(guide.getUser().getId());

		if (user.getIsVet() != true)
			throw new NaoVeterano("Voce precisa ser um usuario veterano para cadastrar um guia");

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
	
	public Guide findByTitulo(String titulo) {
		Optional<Guide> optional = this.guideRepository.findByTitulo(titulo);

		if (optional.isPresent())
			return optional.get();

		else
			return null;
	}

	public List<Guide> findAll() {
		return this.guideRepository.findAll();

	}

	public void delete(long id) {

		Guide guide = findById(id);

		verificarPrioridade(guide);

		this.guideRepository.delete(guide);
	}
}

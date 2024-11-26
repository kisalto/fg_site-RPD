package app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Game;
import app.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	public String save(Game game) {
		this.gameRepository.save(game);
		return "Jogo Cadastrado";
	}

	public String update(Game game, long id) {
		game.setId(id);
		this.gameRepository.save(game);
		return "Personagem Atualizado";
	}

	public Game findById(long id) {

		Optional<Game> optional = this.gameRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else
			return null;

	}

	public Game findBySigla(String sigla) {

		Optional<Game> optional = this.gameRepository.findBySigla(sigla);
		if (optional.isPresent()) {
			return optional.get();
		} else
			return null;

	}

	public List<Game> findAll() {

		return this.gameRepository.findAll();

	}

	public String delete(long id) {
		this.gameRepository.deleteById(id);
		return "Personagem deletado com sucesso!";
	}

	public List<Game> findByNome(String nome) {
		return this.gameRepository.findByNomeStartsWith(nome);
	}
}

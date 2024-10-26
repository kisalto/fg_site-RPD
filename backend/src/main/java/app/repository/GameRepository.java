package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
	
	public List<Game> findByNomeStartsWith(String nome);

}

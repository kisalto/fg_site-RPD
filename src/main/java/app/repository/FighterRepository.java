package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Fighter;

public interface FighterRepository extends JpaRepository<Fighter, Long>{

	public List<Fighter> findByNomeStartsWith(String nome);
	
	public List<Fighter> findByGameNomeContains(String nome);
}

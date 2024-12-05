package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

	List<Event> findFirst5ByOrderByDateDesc();
	List<Event> findAllByGameSigla(String sigla);

}

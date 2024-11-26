package app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Guide;

public interface GuideRepository extends JpaRepository<Guide, Long> {
	public Optional<Guide> findByTitulo(String titulo);
}

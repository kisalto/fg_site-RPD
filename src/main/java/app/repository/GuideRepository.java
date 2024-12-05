package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Guide;

public interface GuideRepository extends JpaRepository<Guide, Long> {

    List<Guide> findAllByGameSigla(String sigla);
    
}

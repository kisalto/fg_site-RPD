package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Guide;

public interface GuideRepository extends JpaRepository<Guide, Long> {

}

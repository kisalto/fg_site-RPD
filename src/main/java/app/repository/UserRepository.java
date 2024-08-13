package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public List<User> findByApelidoContains (String apelido);
	public List<User> findByEmail (String email);
}

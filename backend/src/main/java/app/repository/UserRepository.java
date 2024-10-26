package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByApelido(String apelido);
	public User findByApelidoContains (String apelido);
	public User findByEmail (String email);
}

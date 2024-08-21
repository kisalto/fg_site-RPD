package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min = 2, max = 255)
	private String titulo;
	
	@NotBlank
	private String descricao;
	
	@NotBlank
	private String link;
	
	@NotBlank
	private String date;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JsonIgnoreProperties("event")
	@JoinTable(name = "game_event")
	private List<Game>game;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JsonIgnoreProperties("event")
	@JoinTable(name = "user_event")
	private List<User> user;
}

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
import jakarta.validation.constraints.NotEmpty;
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
	
	@NotBlank(message = "Titulo nao pode ficar em branco")
	@Size(min = 2, max = 255)
	private String titulo;
	
	@NotBlank(message = "Descricao nao pode ficar em branco")
	private String descricao;
	
	@NotBlank(message = "Link nao pode ficar em branco")
	private String link;
	
	@NotBlank(message = "Data nao pode ficar em branco")
	private String date;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JsonIgnoreProperties({"event", "fighter", "guides"})
	@JoinTable(name = "game_event")
	@NotEmpty(message = "pelo menos um jogo precisa ser selecionado")
	private List<Game>game;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JsonIgnoreProperties({"event", "guides"})
	@JoinTable(name = "user_event")
	@NotEmpty(message = "pelo menos um usuario precisa existir")
	private List<User> user;
}

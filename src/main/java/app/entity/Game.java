package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	@Size(min = 2, max = 80)
	private String nome;
	
	@NotBlank
	@Size(min = 2, max = 2083)
	private String descricao;
	
	@NotBlank
	private String link;
	
	@NotNull
	private Double preco;
	
	@OneToMany(mappedBy = "game")
	@JsonIgnoreProperties("fighter")
	private List<Fighter> fighter;
	
	@ManyToMany(mappedBy = "game", cascade = CascadeType.MERGE)
	@JsonIgnoreProperties("game")
	private List<Event> event;
	
	@OneToMany(mappedBy = "game")
	@JsonIgnoreProperties("game")
	private List<Guide> guide;

}

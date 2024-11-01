	package app.entity;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Nome nao pode ficar em branco")
	@Size(min = 2, max = 80)
	private String nome;
	
	@NotBlank(message = "Sigla nao pode ficar em branco")
	@Size(min = 2, max = 80)
	@Column(unique = true)
	private String sigla;
	
	@NotBlank(message = "Descricao nao pode ficar em branco")
	@Size(min = 2, max = 2083, message = "Descricao preciso de ao menos 2 caracteres")
	private String descricao;
	
	@NotBlank(message = "Link nao pode ficar em branco")
	private String link;
	
	@NotNull(message = "preco nao pode ser nulo")
	private Double preco;
	
	@OneToMany(mappedBy = "game")
	@JsonIgnoreProperties({"game", "guides"})
	@ColumnDefault("null")
	private List<Fighter> fighter;
	
	@ManyToMany(mappedBy = "game", cascade = CascadeType.MERGE)
	@JsonIgnoreProperties({"game", "user"})
	@ColumnDefault("null")
	private List<Event> event;
	
	@OneToMany(mappedBy = "game")
	@JsonIgnoreProperties({"game", "user", "fighter"})
	@ColumnDefault("null")
	private List<Guide> guides;

}

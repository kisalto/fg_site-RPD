package app.entity;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity 	
public class Fighter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Nome nao pode ficar em branco")
	@Size(min = 2, max = 30)
	private String nome;
	
	@NotBlank(message = "Tipo nao pode ficar em branco")
	private String type;
	
	@NotBlank(message = "strengths nao pode ficar em branco")
	@Size(min = 2, max = 2083)
	private String strengths;
	
	@NotBlank(message = "weaknesses nao pode ficar em branco")
	@Size(min = 2, max = 2083)
	private String weaknesses;
	
	@NotBlank(message = "Descricao nao pode ficar em branco")
	@Size(min = 2, max = 2083)
	private String descricao;
	
	@ManyToOne
	@NotNull(message = "Precisa existir um jogo")
	@JsonIgnoreProperties({"fighter", "event", "guides"})
	private Game game;
	
	@OneToMany(mappedBy = "fighter")
	@JsonIgnoreProperties({"fighter", "game", "user"})
	@ColumnDefault("null")
	private List<Guide> guides;
}

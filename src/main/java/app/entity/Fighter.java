package app.entity;

import java.util.List;

<<<<<<< HEAD
=======
import org.hibernate.annotations.ColumnDefault;

>>>>>>> d6847fc053af510710e2e4bf354a5a284d48bf24
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity 	
@Getter		
@Setter		
@NoArgsConstructor	
@AllArgsConstructor	
public class Fighter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Nome nao pode ficar em branco")
	@Size(min = 2, max = 30)
	private String nome;
	
	@NotBlank(message = "Descricao nao pode ficar em branco")
	@Size(min = 2, max = 2083)
	private String descricao;
	
	@NotBlank(message = "Tipo nao pode ficar em branco")
	private String type;
	
	@ManyToOne
<<<<<<< HEAD
	@NotNull
	@JsonIgnoreProperties("game")
	private Game game;
	
	@OneToMany(mappedBy = "fighter")
	@JsonIgnoreProperties("fighter")
	private List<Guide> guide;
=======
	@NotNull(message = "Precisa existir um jogo")
	@JsonIgnoreProperties({"fighter", "event", "guides"})
	private Game game;
	
	@OneToMany(mappedBy = "fighter")
	@JsonIgnoreProperties({"fighter", "game", "user"})
	@ColumnDefault("null")
	private List<Guide> guides;
>>>>>>> d6847fc053af510710e2e4bf354a5a284d48bf24
}

package app.entity;

import java.util.List;

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

	@NotBlank
	@Size(min = 2, max = 30)
	private String nome;
	
	@NotBlank
	@Size(min = 2, max = 2083)
	private String descricao;
	
	@NotBlank
	private String type;
	
	@ManyToOne
	@NotNull
	@JsonIgnoreProperties("game")
	private Game game;
	
	@OneToMany(mappedBy = "fighter")
	@JsonIgnoreProperties("fighter")
	private List<Guide> guide;
}

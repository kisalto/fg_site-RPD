package app.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	@Size(min = 2, max = 30)
	private String nome;
	@NotBlank
	private String descricao;
	@NotBlank
	private String link;
	@NotNull
	private double preco;
	@OneToMany(mappedBy = "game")
    private List<Fighter> fighter;
//	@OneToMany //Verificar existencia de eventos universais para o jogos
//	private Event Event; //@NotNull
//	@OneToMany
//	private Guide guide; //@NotNull
	

}

package app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Guide {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	private String tipo;
	
	@NotBlank
	private String descricao;
	
	@NotBlank
	private String link;
	
	@NotBlank
	private String data_cr;
	
	@NotNull
	private int likes;
	
	@NotNull
	private int dislikes;
	
	@ManyToOne
	private Fighter fighter;
	
	@ManyToOne
	private Game game;
	
	@ManyToOne
	private User user;
}

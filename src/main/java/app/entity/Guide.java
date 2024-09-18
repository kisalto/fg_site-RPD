package app.entity;

import org.hibernate.annotations.ColumnDefault;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@NotBlank(message = "Titulo nao pode ficar em branco")
	private String titulo;
	
	@NotBlank(message = "Tipo nao pode ficar em branco")
	private String tipo;
	
	@NotBlank(message = "Descricao nao pode ficar em branco")
	private String descricao;
	
	@NotBlank(message = "Link nao pode ficar em branco")
	private String link;
	
	@NotBlank(message = "Data nao pode ficar em branco")
	private String data_cr;
	
	@ColumnDefault("0")
	private Integer likes;
	
	@ColumnDefault("0")
	private Integer dislikes;
	
	@ManyToOne
	@JsonIgnoreProperties({"guides","game"})
	@NotNull(message = "Voce deve selecionar um personagem")
	private Fighter fighter;
	
	@ManyToOne
	@JsonIgnoreProperties({"guides", "fighter", "event"})
	@NotNull(message = "Voce deve selecionar um jogo")
	private Game game;
	
	@ManyToOne
	@JsonIgnoreProperties({"guides", "event"})
	@NotNull(message = "Um usuario deve existir")
	private User user;
}

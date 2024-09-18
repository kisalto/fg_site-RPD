package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Apelido nao pode ficar em branco")
	private String apelido;
	
	@NotBlank(message = "Email nao pode ficar em branco")
	private String email;
	
	private String dc_id;
	
	@NotBlank(message = "Senha nao pode ficar em branco")
	private String senha;
	
	@NotBlank(message = "Data nao pode ficar em branco")
	private String data_reg;
	
	@NotNull(message = "Deve existir atributo isMod")
	@ColumnDefault("false")
	private Boolean isMod;
	
	@NotNull(message = "Deve existir atributo isVet")
	@ColumnDefault("false")
	private Boolean isVet;
	
	@ManyToMany(mappedBy = "user", cascade = CascadeType.MERGE)
	@JsonIgnoreProperties({"user", "game"})
	@ColumnDefault("null")
	private List<Event> event;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnoreProperties({"user", "game", "fighter"})
	@ColumnDefault("null")
	private List<Guide> guides;
	
}

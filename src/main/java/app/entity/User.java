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
	
	@NotBlank
	private String apelido;
	
	@NotBlank
	private String email;
	
	private String dc_id;
	
	@NotBlank
	private String senha;
	
	@NotBlank
	private String data_reg;
	
	@NotNull
	private Boolean isMod;
	
	@NotNull
	private Boolean isVet;
	
	@ManyToMany(mappedBy = "user", cascade = CascadeType.MERGE)
	@JsonIgnoreProperties({"user", "game"})
	private List<Event> event;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnoreProperties({"user", "game", "fighter"})
	private List<Guide> guides;
	
}

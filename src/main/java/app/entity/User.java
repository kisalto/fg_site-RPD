package app.entity;

import java.util.List;

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
	private int eventos_qnt;
	
	@NotNull
	private int guias_qnt;
	
	@NotNull
	private Boolean isMod;
	
	@NotNull
	private Boolean isVet;
	
	@ManyToMany(mappedBy = "user")
	private List<Event> event;
	
	@OneToMany(mappedBy = "user")
	private List<Guide> guide;
	
}

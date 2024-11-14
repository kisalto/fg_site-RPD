package app.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.entity.Event;
import app.entity.Guide;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome de usuario nao pode ficar em branco")
	private String username;

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
	private String role;

	@ManyToMany(mappedBy = "user", cascade = CascadeType.MERGE)
	@JsonIgnoreProperties({ "user", "game" })
	@ColumnDefault("null")
	private List<Event> event;

	@OneToMany(mappedBy = "user")
	@JsonIgnoreProperties({ "user", "game", "fighter" })
	@ColumnDefault("null")
	private List<Guide> guides;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(this.role));
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

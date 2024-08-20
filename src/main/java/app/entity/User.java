package app.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private long id;
	private String apelido;
	private String email;
	private String dc_id;
	private String senha;
	private Date data_reg;
	private int eventos_qnt;
	private int guias_qnt;
	private boolean isMod;
	private boolean isVet;
	
}

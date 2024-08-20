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
public class Guide {
	private long id;
	private String titulo;
	private String tipo;
	private String descricao;
	private String link;
	private Date data_cr;
	private int likes;
	private int dislikes;
}

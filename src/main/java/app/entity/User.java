package app.entity;

import java.sql.Date;

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
	
	public User(long id, String apelido, String email, String dc_id, String senha, Date data_reg, int eventos_qnt,
			int guias_qnt, boolean isMod, boolean isVet) {
		super();
		this.id = id;
		this.apelido = apelido;
		this.email = email;
		this.dc_id = dc_id;
		this.senha = senha;
		this.data_reg = data_reg;
		this.eventos_qnt = eventos_qnt;
		this.guias_qnt = guias_qnt;
		this.isMod = isMod;
		this.isVet = isVet;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDc_id() {
		return dc_id;
	}
	public void setDc_id(String dc_id) {
		this.dc_id = dc_id;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Date getData_reg() {
		return data_reg;
	}
	public void setData_reg(Date data_reg) {
		this.data_reg = data_reg;
	}
	public int getEventos_qnt() {
		return eventos_qnt;
	}
	public void setEventos_qnt(int eventos_qnt) {
		this.eventos_qnt = eventos_qnt;
	}
	public int getGuias_qnt() {
		return guias_qnt;
	}
	public void setGuias_qnt(int guias_qnt) {
		this.guias_qnt = guias_qnt;
	}
	public boolean isMod() {
		return isMod;
	}
	public void setMod(boolean isMod) {
		this.isMod = isMod;
	}
	public boolean isVet() {
		return isVet;
	}
	public void setVet(boolean isVet) {
		this.isVet = isVet;
	}
	
}

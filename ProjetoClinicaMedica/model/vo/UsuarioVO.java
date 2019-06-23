package model.vo;

import java.time.LocalDate;

public class UsuarioVO extends PessoaVO {

	public static final String NIVEL_ADMIN = "Admin";
	public static final String NIVEL_MEDICO = "Médico";
	public static final String NIVEL_FUNCIONARIO = "Funcionário";

	private int idUsuario;
	private String nomeUsuario;
	private String senha;
	private String nivel;

	public UsuarioVO() {

	}

	public UsuarioVO(int idUsuario, String usuario, String senha, String nivel, String nome, LocalDate dtNascimento,
			String cpf, String telefone, String email) {
		super(nome, dtNascimento, cpf, telefone, email);
		this.idUsuario = idUsuario;
		this.nomeUsuario = usuario;
		this.senha = senha;
		this.nivel = nivel;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return this.nomeUsuario;
	}

	public void setNomeUsuario(String usuario) {
		this.nomeUsuario = usuario;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNivel() {
		return this.nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	@Override
	public String toString() {
		return this.getNomeUsuario();
	}

}

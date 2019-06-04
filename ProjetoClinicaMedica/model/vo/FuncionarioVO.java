package model.vo;

import java.sql.Date;

public class FuncionarioVO extends UsuarioVO {

	private int idFuncionario;

	public FuncionarioVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FuncionarioVO(int idUsuario, String usuario, String senha, String nivel, String nome, Date dtNascimento,
			int idFuncionario, String cpf, String telefone, String email) {
		super(idUsuario, usuario, senha, nivel, nome, dtNascimento, cpf, telefone, email);
		this.idFuncionario = idFuncionario;
	}

	public int getIdFuncionario() {
		return this.idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	@Override
	public String toString() {
		return "Funcionário(a) " + this.getNome() + " - " + this.getCpf();
	}

}

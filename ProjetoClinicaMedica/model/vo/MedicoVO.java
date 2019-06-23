package model.vo;

import java.time.LocalDate;

public class MedicoVO extends UsuarioVO {

	private int idMedico;
	private String crm;
	private String especialidade;

	public MedicoVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MedicoVO(int idUsuario, String usuario, String senha, String nivel, String nome, LocalDate dtNascimento,
			int idMedico, String crm, String especialidade, String cpf, String telefone, String email) {
		super(idUsuario, usuario, senha, nivel, nome, dtNascimento, cpf, telefone, email);
		this.idMedico = idMedico;
		this.crm = crm;
		this.especialidade = especialidade;
	}

	public int getIdMedico() {
		return this.idMedico;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public String getCrm() {
		return this.crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getEspecialidade() {
		return this.especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	@Override
	public String toString() {
		return this.especialidade + " " + this.getNome() + " - CRM: " + this.crm;
	}

}

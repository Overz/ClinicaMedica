package model.vo;

import java.sql.Date;

public class PacienteVO extends PessoaVO {
	public static final String SEXO_MASCULINO = "M";
	public static final String SEXO_FEMININO = "F";

	private int idPaciente;
	private String sexo;
	private String tipoSanguineo;
	private String rua;
	private int numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;

	public PacienteVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PacienteVO(int idPaciente, String sexo, String tipoSanguineo, String rua, int numero, String bairro,
			String cidade, String estado, String cep, String nome, Date dtNascimento, String cpf, String telefone,
			String email) {
		super(nome, dtNascimento, cpf, telefone, email);
		this.idPaciente = idPaciente;
		this.sexo = sexo;
		this.tipoSanguineo = tipoSanguineo;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
	}

	public int getIdPaciente() {
		return this.idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTipoSanguineo() {
		return this.tipoSanguineo;
	}

	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public String getRua() {
		return this.rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "Paciente " + this.getNome() + " - " + this.getCpf() + " - " + this.getEmail();
	}

}

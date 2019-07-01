package model.vo;

import java.time.LocalDate;

public class PacienteVO extends PessoaVO {
	public static final String SEXO_MASCULINO = "Masculino";
	public static final String SEXO_FEMININO = "Feminino";
	public static final String CONVENIO_PARTICULAR = "Particular";
	public static final String CONVENIO_UNIMED = "Unimed";
	public static final String CONVENIO_AGEMED = "Agemed";
	public static final String CONVENIO_AMIL = "Amil";
	public static final String CONVENIO_SULAMERICA_SAUDE = "SulAmerica Saúde";
	public static final String CONVENIO_ONE_HEALTH = "One Health";
	public static final String CONVENIO_BRADESCO_SAUDE = "Bradesco Saúde ";
	public static final String CONVENIO_GOLDEN_CROSS = "Golden Cross";
	public static final String CONVENIO_INTERMEDICA = "Intermédica";
	public static final String CONVENIO_NOTRE_DAME_SEGURO_SAUDE = "Notre Dame Seguro Saúde";
	// Tipo Sanguineo
	public static final String A = "A+";
	public static final String a = "A-";
	public static final String B = "B+";
	public static final String b = "B-";
	public static final String AB = "AB+";
	public static final String ab = "AB-";
	public static final String O = "O+";
	public static final String o = "O-";

	private int idPaciente;
	private String sexo;
	private String tipoSanguineo;
	private String rua;
	private int numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	private String convenio;

	public PacienteVO() {
		super();
	}

	public PacienteVO(int idPaciente, String sexo, String tipoSanguineo, String rua, int numero, String bairro,
			String cidade, String estado, String cep, String nome, LocalDate dtNascimento, String cpf, String telefone,
			String email, String convenio) {
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
		this.convenio = convenio;
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
		return "Nome: " + this.getNome() + " CPF: " + this.getCpf() + " Convênio: " + this.getConvenio();
	}

	public String getConvenio() {
		return this.convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

}

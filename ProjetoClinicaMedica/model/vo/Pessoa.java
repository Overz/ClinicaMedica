package model.vo;

public abstract class Pessoa {

	private String nome;
	private String sexo;
	private String dtNascimento;
	private String CPF;
	private String RG;
	private Endereco endereco;
	
	public Pessoa(String nome, String sexo, String dtNascimento, String cPF, String rG, Endereco endereco) {
		super();
		this.nome = nome;
		this.sexo = sexo;
		this.dtNascimento = dtNascimento;
		CPF = cPF;
		RG = rG;
		this.endereco = endereco;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getRG() {
		return RG;
	}
	public void setRG(String rG) {
		RG = rG;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", sexo=" + sexo + ", dtNascimento=" + dtNascimento + ", CPF=" + CPF + ", RG="
				+ RG + ", endereco=" + endereco + "]";
	}
}

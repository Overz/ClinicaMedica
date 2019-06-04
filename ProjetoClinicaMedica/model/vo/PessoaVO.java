package model.vo;

public abstract class PessoaVO {

	private String nome;
	private String dtNascimento;
	private String cpf;
	private String telefone;
	private String email;

	public PessoaVO() {
		super();
	}

	public PessoaVO(String nome, String dtNascimento, String cpf, String telefone, String email) {
		super();
		this.nome = nome;
		this.dtNascimento = dtNascimento;
		this.cpf = cpf;
		this.telefone = telefone;
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDtNascimento() {
		return this.dtNascimento;
	}

	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public abstract String toString();

}

package model.vo;

public abstract class PessoaVO {

	private String nome;
	private String dtNascimento;
	private String sexo;
	private String CpfCnpj;
	private EnderecoVO enderecoVO;
	
	public PessoaVO(String nome, String dtNascimento, String sexo, String cpfCnpj, EnderecoVO enderecoVO) {
		super();
		this.nome = nome;
		this.dtNascimento = dtNascimento;
		this.sexo = sexo;
		CpfCnpj = cpfCnpj;
		this.enderecoVO = enderecoVO;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpfCnpj() {
		return CpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		CpfCnpj = cpfCnpj;
	}

	public EnderecoVO getEnderecoVO() {
		return enderecoVO;
	}

	public void setEnderecoVO(EnderecoVO enderecoVO) {
		this.enderecoVO = enderecoVO;
	}
	
	
	

}

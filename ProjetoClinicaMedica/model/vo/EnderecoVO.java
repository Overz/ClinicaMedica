package model.vo;

public abstract class EnderecoVO {
	
	private String cidade;
	private String bairro;
	private String rua;
	private int numero;
	private String telefone;

	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "EnderecoVO [cidade=" + cidade + ", bairro=" + bairro + ", rua=" + rua + ", numero=" + numero
				+ ", telefone=" + telefone + "]";
	}
}

package model.seletor;

import java.time.LocalDate;

public class SeletorUsuario {

	private String nome;
	private String cpf;
	private String crm;
	private String especialidade;
	private LocalDate dataNascimento;
	private String nivel;

	private int limite;
	private int pagina;

	public SeletorUsuario() {
		this.limite = 0;
		this.pagina = -1;
	}

	public boolean temFiltro() {
		if (this.nome != null && !this.nome.trim().isEmpty()) {
			return true;
		}
		if (this.cpf != null && !this.cpf.trim().isEmpty()) {
			return true;
		}
		if (this.crm != null && !this.crm.trim().isEmpty()) {
			return true;
		}
		if (this.nivel != null && !this.nivel.trim().isEmpty()) {
			return true;
		}
		if (this.especialidade != null && !this.especialidade.trim().isEmpty()) {
			return true;
		}
		if (this.dataNascimento != null) {
			return true;
		}
		return false;
	}

	public boolean temPaginacao() {
		return ((this.limite > 0) && (this.pagina > -1));
	}

	public int getOffset() {
		return (this.limite * (this.pagina - 1));
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public LocalDate getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getLimite() {
		return this.limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public int getPagina() {
		return this.pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public String getNivel() {
		return this.nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

}

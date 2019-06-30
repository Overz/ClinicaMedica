package model.seletor;

import java.time.LocalDate;

public class SeletorPaciente {

	private String cpf;
	private String nome;
	private LocalDate date;

	private int limite;
	private int pagina;

	public SeletorPaciente() {
		this.limite = 0;
		this.pagina = -1;
	}

	public boolean temFiltro() {

		if ((this.cpf != null) && (this.cpf.length() > 0)) {
			return true;
		}
		if ((this.nome != null) && (this.nome.length() > 0)) {
			return true;
		}
		if (this.date != null) {
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

	// Getter Setter
	public String getCpf() {
			return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
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

}

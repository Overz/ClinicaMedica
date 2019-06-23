package model.seletor;

import java.time.LocalDate;

public class SeletorPaciente {

	private String cpf;
	private String nome;
	private LocalDate date;
	
	
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


	//Getter Setter
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
	
}

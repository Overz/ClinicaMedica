package model.seletor;

import java.time.LocalDateTime;

import model.vo.MedicoVO;
import model.vo.PacienteVO;

public class SeletorConsulta {

	private PacienteVO paciente;
	private MedicoVO medico;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;

	private int limite;
	private int pagina;

	public SeletorConsulta() {
		this.limite = 0;
		this.pagina = -1;
	}

	public boolean temFiltro() {

		if (this.paciente != null) {
			return true;
		}
		if (this.medico != null) {
			return true;
		}
		if (this.dataInicio != null) {
			return true;
		}
		if (this.dataFim != null) {
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

	public PacienteVO getPaciente() {
		return this.paciente;
	}

	public void setPaciente(PacienteVO paciente) {
		this.paciente = paciente;
	}

	public MedicoVO getMedico() {
		return this.medico;
	}

	public void setMedico(MedicoVO medico) {
		this.medico = medico;
	}

	public LocalDateTime getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
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

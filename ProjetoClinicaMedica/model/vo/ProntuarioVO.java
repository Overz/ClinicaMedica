package model.vo;

public class ProntuarioVO {

	private int idProntuario;
	private MedicoVO medico;
	private PacienteVO paciente;
	private String observacoes;
	private String dtProntuario;

	public ProntuarioVO() {
		super();
	}

	public ProntuarioVO(int idProntuario, MedicoVO medico, PacienteVO paciente, String observacoes,
			String dtProntuario) {
		super();
		this.idProntuario = idProntuario;
		this.medico = medico;
		this.paciente = paciente;
		this.observacoes = observacoes;
		this.dtProntuario = dtProntuario;
	}

	public int getIdProntuario() {
		return this.idProntuario;
	}

	public void setIdProntuario(int idProntuario) {
		this.idProntuario = idProntuario;
	}

	public MedicoVO getMedico() {
		return this.medico;
	}

	public void setMedico(MedicoVO medico) {
		this.medico = medico;
	}

	public PacienteVO getPaciente() {
		return this.paciente;
	}

	public void setPaciente(PacienteVO paciente) {
		this.paciente = paciente;
	}

	public String getObservacoes() {
		return this.observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getDtProntuario() {
		return this.dtProntuario;
	}

	public void setDtProntuario(String dtProntuario) {
		this.dtProntuario = dtProntuario;
	}

}

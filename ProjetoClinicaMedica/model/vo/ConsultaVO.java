package model.vo;

import java.awt.Checkbox;
import java.time.LocalDateTime;

public class ConsultaVO {

	private int idConsulta;
	private MedicoVO medico;
	private PacienteVO paciente;
	private LocalDateTime data_consulta;
	private FuncionarioVO funcionario;
	private boolean cbx;

	public ConsultaVO() {
		super();
	}

	public ConsultaVO(int idConsulta, MedicoVO medico, PacienteVO paciente, LocalDateTime data_consulta,
			FuncionarioVO funcionario, boolean cbx) {
		super();
		this.idConsulta = idConsulta;
		this.medico = medico;
		this.paciente = paciente;
		this.data_consulta = data_consulta;
		this.funcionario = funcionario;
		this.cbx = cbx;
	}

	public int getIdConsulta() {
		return this.idConsulta;
	}

	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
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

	public LocalDateTime getData_consulta() {
		return this.data_consulta;
	}

	public void setData_consulta(LocalDateTime data_consulta) {
		this.data_consulta = data_consulta;
	}

	public FuncionarioVO getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(FuncionarioVO funcionario) {
		this.funcionario = funcionario;
	}

	public boolean getCbx() {
		return cbx;
	}

	public void setCbx(boolean cbx) {
		this.cbx = cbx;
	}

}

package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import model.bo.ConsultaBO;
import model.vo.ConsultaVO;
import model.vo.FuncionarioVO;
import model.vo.MedicoVO;
import model.vo.PacienteVO;

public class ControllerConsulta {

	private ConsultaBO bo;

	public ArrayList<ConsultaVO> pesquisarConsultasPorDataEMedico(LocalDate data, MedicoVO medico) {
		return bo.pesquisarConsultasPorDataEMedico(data, medico);
	}
	
	
	
	
	
	
	
	
	
	
	

	public String agendarConsulta(MedicoVO medico, PacienteVO paciente, LocalDate data, LocalTime horario,
			FuncionarioVO funcionario) {
		String mensagem = "";
		if (medico == null) {
			mensagem += "Selecine um médico!";
		}
		if (paciente == null) {
			mensagem += "Selecione um paciente!";
		}
		if (data == null) {
			mensagem += "Selecione uma data!";
		}
		if (horario == null) {
			mensagem += "Selecione um horário!";
		}
		if (mensagem.equals("")) {
			ConsultaBO bo = new ConsultaBO();
			ConsultaVO consulta = montarConsulta(medico, paciente, data, horario, funcionario);
			mensagem = bo.agendarConsulta(consulta);
		}
		return mensagem;
	}

	private ConsultaVO montarConsulta(MedicoVO medico, PacienteVO paciente, LocalDate data, LocalTime horario,
			FuncionarioVO funcionario) {
		ConsultaVO consulta = new ConsultaVO();

		consulta.setData_consulta(LocalDateTime.of(data, horario));
		consulta.setFuncionario(funcionario);
		consulta.setMedico(medico);
		consulta.setPaciente(paciente);

		return consulta;
	}

	public ArrayList<?> consultarTodos() {
		return bo.consultarTudos();
	}

}

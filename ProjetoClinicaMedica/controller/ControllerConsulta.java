package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.bo.ConsultaBO;
import model.vo.ConsultaVO;
import model.vo.MedicoVO;
import model.vo.PacienteVO;

public class ControllerConsulta {

	private ConsultaBO bo;

	public ArrayList<ConsultaVO> pesquisarConsultasPorDataEMedico(LocalDate data, MedicoVO medico) {
		return bo.pesquisarConsultasPorDataEMedico(data, medico);
	}

	public List<ConsultaVO> consultarTodos() {
		return bo.consultarTudo();
	}

}

package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import model.bo.ConsultaBO;
import model.vo.ConsultaVO;
import model.vo.MedicoVO;

public class ControllerConsulta {

	private ConsultaBO bo;

	public ArrayList<ConsultaVO> pesquisarConsultasPorDataEMedico(LocalDate data, MedicoVO medico) {
		return bo.pesquisarConsultasPorDataEMedico(data, medico);
	}

	public ArrayList<?> consultarTodos() {
		return bo.consultarTudos();
	}

}

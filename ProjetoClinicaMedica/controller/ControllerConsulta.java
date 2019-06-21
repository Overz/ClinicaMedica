package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import model.bo.ConsultaBO;
import model.vo.ConsultaVO;
import model.vo.MedicoVO;

public class ControllerConsulta {

	public ArrayList<ConsultaVO> pesquisarConsultasPorDataEMedico(LocalDate data, MedicoVO medico) {
		ConsultaBO bo = new ConsultaBO();
		return bo.pesquisarConsultasPorDataEMedico(data, medico);
	}

}

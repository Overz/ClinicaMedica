package model.bo;

import java.time.LocalDate;
import java.util.ArrayList;

import model.dao.ConsultaDAO;
import model.vo.ConsultaVO;
import model.vo.MedicoVO;

public class ConsultaBO {

	private ConsultaDAO dao;

	public ArrayList<ConsultaVO> pesquisarConsultasPorDataEMedico(LocalDate data, MedicoVO medico) {
		return dao.pesquisarConsultasPorDataEMedico(data, medico);
	}

	public ArrayList<?> consultarTudos() {
		return dao.consultarTudos();
	}

}

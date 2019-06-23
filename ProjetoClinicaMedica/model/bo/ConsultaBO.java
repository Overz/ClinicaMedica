package model.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.dao.ConsultaDAO;
import model.vo.ConsultaVO;
import model.vo.MedicoVO;
import model.vo.PacienteVO;

public class ConsultaBO {

	private ConsultaDAO dao;

	public ArrayList<ConsultaVO> pesquisarConsultasPorDataEMedico(LocalDate data, MedicoVO medico) {
		return dao.pesquisarConsultasPorDataEMedico(data, medico);
	}

	public List<ConsultaVO> consultarTudo() {
		return dao.consultarTudo();
	}

}

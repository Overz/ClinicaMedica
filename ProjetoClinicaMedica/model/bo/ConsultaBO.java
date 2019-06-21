package model.bo;

import java.time.LocalDate;
import java.util.ArrayList;

import model.dao.ConsultaDAO;
import model.vo.ConsultaVO;
import model.vo.MedicoVO;

public class ConsultaBO {

	public ArrayList<ConsultaVO> pesquisarConsultasPorDataEMedico(LocalDate data, MedicoVO medico) {
		ConsultaDAO dao = new ConsultaDAO();
		return dao.pesquisarConsultasPorDataEMedico(data, medico);
	}

}

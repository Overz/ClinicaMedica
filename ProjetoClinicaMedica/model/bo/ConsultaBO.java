package model.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import model.dao.ConsultaDAO;
import model.seletor.SeletorConsulta;
import model.vo.ConsultaVO;
import model.vo.MedicoVO;

public class ConsultaBO {

	private ConsultaDAO dao = new ConsultaDAO();

	public ArrayList<ConsultaVO> pesquisarConsultasPorDataEMedico(LocalDate data, MedicoVO medico) {
		return dao.pesquisarConsultasPorDataEMedico(data, medico);
	}

	public String agendarConsulta(ConsultaVO consulta) {
		String mensagem = "";

		if (consulta.getData_consulta().isBefore(LocalDateTime.now())) {
			mensagem += "Data inválida. Selecione uma data futura!";
		}
		ConsultaDAO dao = new ConsultaDAO();
		if (dao.existeConsultaPorDataEMedico(consulta)) {
			mensagem += "Horário indisponível. " + consulta.getMedico().toString()
					+ " já possuí agendamento nesse horario.";
		}
		if (mensagem.equals("")) {
			int chaveGerada = dao.cadastrarConsulta(consulta);
			if (chaveGerada > 0) {
				mensagem += "Consulta agendada com sucesso!";
			} else {
				mensagem += "Erro ao agendar consulta!";
			}
		}
		return mensagem;
	}

	public ArrayList<ConsultaVO> consultarTudos() {
		return dao.consultarTudos();
	}

	public Boolean deletarConsulta(ConsultaVO consultaSelecionada) {
		return dao.deletarConsulta(consultaSelecionada);
	}

	public ArrayList<ConsultaVO> buscarConsultas(SeletorConsulta seletor) {
		return dao.buscarConsultas(seletor);
	}

}

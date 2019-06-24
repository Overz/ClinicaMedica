package model.bo;

import java.util.ArrayList;

import model.dao.ProntuarioDAO;
import model.vo.PacienteVO;
import model.vo.ProntuarioVO;

public class ProntuarioBO {

	private ProntuarioDAO dao;

	public ArrayList<?> consultarTodos() {
		dao = new ProntuarioDAO();
		return dao.consultarTodos();
	}

	public ArrayList<ProntuarioVO> listarProntuariosPorPaciente(PacienteVO paciente) {
		ProntuarioDAO dao = new ProntuarioDAO();
		return dao.listarProntuariosPorPaciente(paciente);
	}

	public String salvarProntuario(ProntuarioVO prontuario) {
		ProntuarioDAO dao = new ProntuarioDAO();
		String mensagem = "";
		int chaveGerada = dao.salvarProntuario(prontuario);
		if (chaveGerada > 0) {
			mensagem += "Prontuário cadastrado com sucesso!";
		} else {
			mensagem += "Erro ao salvar prontuário!";
		}
		return mensagem;
	}

}

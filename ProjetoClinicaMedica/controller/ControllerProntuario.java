package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import model.bo.ProntuarioBO;
import model.vo.MedicoVO;
import model.vo.PacienteVO;
import model.vo.ProntuarioVO;

public class ControllerProntuario {

	public ArrayList<ProntuarioVO> listarProntuariosPorPaciente(PacienteVO paciente) {
		ArrayList<ProntuarioVO> prontuarios = new ArrayList<ProntuarioVO>();
		if (paciente != null) {
			ProntuarioBO bo = new ProntuarioBO();
			prontuarios = bo.listarProntuariosPorPaciente(paciente);
		}
		return prontuarios;
	}

	public ArrayList<?> consultarTodos() {
		ProntuarioBO bo = new ProntuarioBO();
		return bo.consultarTodos();
	}

	public String salvarProntuario(MedicoVO medico, PacienteVO paciente, String observacoes) {
		String mensagem = "";
		if (observacoes == null || observacoes.trim().isEmpty()) {
			mensagem += "Prontuário está vazio!";
		}
		if (mensagem.equals("")) {
			ProntuarioBO bo = new ProntuarioBO();
			ProntuarioVO prontuario = construirProntuario(medico, paciente, observacoes);
			mensagem += bo.salvarProntuario(prontuario);
		}
		return mensagem;
	}

	private ProntuarioVO construirProntuario(MedicoVO medico, PacienteVO paciente, String observacoes) {
		ProntuarioVO prontuario = new ProntuarioVO();
		prontuario.setMedico(medico);
		prontuario.setPaciente(paciente);
		prontuario.setObservacoes(observacoes);
		prontuario.setDtProntuario(LocalDateTime.now());

		return prontuario;
	}

}

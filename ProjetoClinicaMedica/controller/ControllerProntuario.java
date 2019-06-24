package controller;

import java.util.ArrayList;

import model.bo.ProntuarioBO;
import model.vo.MedicoVO;
import model.vo.PacienteVO;
import model.vo.ProntuarioVO;

public class ControllerProntuario {
	private ProntuarioBO bo;

	public ArrayList<ProntuarioVO> listarProntuariosPorPacienteEMedico(PacienteVO paciente, MedicoVO medico) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<?> consultarTodos() {
		return bo.consultarTodos();
	}
}

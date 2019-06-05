package controller;

import java.util.ArrayList;

import model.bo.PacienteBO;
import model.seletor.SeletorPaciente;
import model.vo.PacienteVO;

public class ControllerPaciente {

	private PacienteBO pacienteBO = new PacienteBO();

	public String salvarPaciente(PacienteVO paciente) {
		String mensagem = validar(paciente);

		if (mensagem == "") {
			if (paciente.getIdPaciente() > 0) {
				if (pacienteBO.atualizarPaciente(paciente)) {
					mensagem += "Paciente atualizado com sucesso!";
				} else {
					mensagem += "Erro ao atualizar Paciente!";
				}
			} else {
				if (pacienteBO.cadastrarPaciente(paciente)) {
					mensagem += "Paciente cadastrado com sucesso!";
				} else {
					mensagem += "Erro ao cadastrar paciente!";
				}
			}
		}
		return mensagem;
	}

	public String excluirPaciente(PacienteVO paciente) {
		String mensagem = "";
		// TODO Implementar validação de ID
		return mensagem;
	}

	public String validar(PacienteVO paciente) {
		String mensagem = "";
		if (paciente == null) {
			mensagem += "Paciente está vazio";
		}
		if (paciente.getNome() == null || paciente.getNome().trim().isEmpty()) {
			mensagem += "O campo NOME é obrigatório!\n";
		}
		if (paciente.getCpf() == null || paciente.getCpf().trim().isEmpty()) {
			mensagem += "O campo CPF é obrigatório!\n";
		}
		if (paciente.getTelefone() == null || paciente.getTelefone().trim().isEmpty()) {
			mensagem += "O campo TELEFONE é obrigatório!\n";
		}
		if (paciente.getTipoSanguineo() == null || paciente.getTipoSanguineo().trim().isEmpty()) {
			mensagem += "O campo TIPO SANGUÍNEO é obrigatório!\n";
		}
		if (paciente.getSexo() == null || paciente.getSexo().trim().isEmpty()) {
			mensagem += "O campo SEXO é obrigatório!\n";
		}
		if (paciente.getConvenio() == null || paciente.getConvenio().trim().isEmpty()) {
			mensagem += "O campo CONVENIO é obrigatório!\n";
		}

		return mensagem;
	}

	public ArrayList<PacienteVO> pesquisarPaciente(SeletorPaciente seletor) {
		return pacienteBO.pesquisarPaciente(seletor);
	}

}

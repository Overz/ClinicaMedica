package model.bo;

import java.time.LocalDate;
import java.util.ArrayList;

import model.dao.PacienteDAO;
import model.seletor.SeletorPaciente;
import model.vo.PacienteVO;

public class PacienteBO {

	PacienteVO vo = new PacienteVO();
	PacienteDAO dao = new PacienteDAO();

	public String cadastrarPaciente(PacienteVO paciente) {
		String mensagem = "";

		if (paciente.getNome().length() > 150) {
			mensagem += "Nome pode conter no máximo 150 caracteres!\n";
		}
		if (paciente.getCpf().length() < 14 || paciente.getCpf().length() > 14) {
			mensagem += "CPF inválido!\n";
		}
		if (paciente.getTelefone().length() < 14 || paciente.getTelefone().length() > 14) {
			mensagem += "Telefone inválido!\n";
		}
		if (paciente.getEmail().split("@").length != 2) {
			mensagem += "Email inválido!\n";
		}
		if (paciente.getEmail().length() > 100) {
			mensagem += "Email pode conter no máximo 100 caracteres!\n";
		}
		if (dao.existePacientePorCpf(paciente)) {
			mensagem += "Já existe um paciente cadastrado com esse CPF!\n";
		}
		if (mensagem.equals("")) {
			int idGerado = dao.cadastrarPaciente(paciente);
			if (idGerado > 0) {
				mensagem += "Paciene cadastrado com sucesso!\n";
			} else {
				mensagem += "Erro ao cadastrar paciente!\n";
			}
		}
		return mensagem;
	}

	public String atualizarPaciente(PacienteVO paciente) {
		String mensagem = "";

		if (paciente.getNome().length() > 150) {
			mensagem += "Nome pode conter no máximo 150 caracteres!\n";
		}
		if (paciente.getCpf().length() < 14 || paciente.getCpf().length() > 14) {
			mensagem += "CPF inválido!\n";
		}
		if (paciente.getTelefone().length() < 14 || paciente.getTelefone().length() > 14) {
			mensagem += "Telefone inválido!\n";
		}
		if (paciente.getEmail().split("@").length != 2) {
			mensagem += "Email inválido!\n";
		}
		if (paciente.getEmail().length() > 100) {
			mensagem += "Email pode conter no máximo 100 caracteres!\n";
		}
		if (dao.existePacientePorCpf(paciente)) {
			mensagem += "Já existe um paciente cadastrado com esse CPF!\n";
		}
		if (mensagem.equals("")) {
			if (dao.atualizarPaciente(paciente)) {
				mensagem += "Paciene atualizado com sucesso!\n";
			} else {
				mensagem += "Erro ao atualizar paciente!\n";
			}
		}
		return mensagem;
	}

	public ArrayList<PacienteVO> buscarPaciente(SeletorPaciente seletor) {
		return dao.buscarPaciente(seletor);
	}

	public String salvarCadastroPaciente(PacienteVO vo) {
		String mensagem = "";

		int result = dao.cadastrarPaciente(vo);
		if (result == 1) {
			mensagem += "Paciente Cadastrado com Sucesso!";
		} else {
			mensagem += "Erro ao Cadastrar o Paciente";
		}

		return mensagem;
	}

	public String validarTelaBuscarPaciente(String nome, String cpfCrm, LocalDate data) {
		String mensagem = "";

		return mensagem;
	}

	public ArrayList<PacienteVO> consultarTodos() {
		return dao.buscarPaciente();
	}

	public String excluirPaciene(PacienteVO paciente) {
		String mensagem = "";
		if (dao.excluirPaciente(paciente.getIdPaciente())) {
			mensagem += "Paciente excluído com sucesso!";
		} else {
			mensagem += "Erro ao excluir Paciente!";
		}
		return mensagem;
	}

}

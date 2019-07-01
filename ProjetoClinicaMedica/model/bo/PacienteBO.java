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
		if (!validarCampoStrings(paciente.getNome())) {
			mensagem += "Nome não pode conter caracteres inválidos!\n";
		}
		if (paciente.getCpf().length() < 14 || paciente.getCpf().length() > 14) {
			mensagem += "CPF inválido!\n";
		}
		if (paciente.getTelefone().length() < 14 || paciente.getTelefone().length() > 14) {
			mensagem += "Telefone inválido!\n";
		}
		if (paciente.getEmail().length() > 100) {
			mensagem += "Email pode conter no máximo 100 caracteres!\n";
		}
		if (!validarEMail(paciente.getEmail())) {
			mensagem += "Email inválido!\n";
		}
		if (paciente.getDtNascimento().isAfter(LocalDate.now())) {
			mensagem += "Data inválida! Você ainda não nasceu!";
		}
		if (!validarCampoStrings(paciente.getCidade())) {
			mensagem += "Cidade não pode conter caracteres inválidos!\n";
		}
		if (!validarCampoStrings(paciente.getBairro())) {
			mensagem += "Bairro não pode conter caracteres inválidos!\n";
		}
		if (!validarCampoStrings(paciente.getRua())) {
			mensagem += "Rua não pode conter caracteres inválidos!\n";
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
		if (!validarCampoStrings(paciente.getNome())) {
			mensagem += "Nome não pode conter caracteres inválidos";
		}
		if (paciente.getCpf().length() < 14 || paciente.getCpf().length() > 14) {
			mensagem += "CPF inválido!\n";
		}
		if (paciente.getTelefone().length() < 14 || paciente.getTelefone().length() > 14) {
			mensagem += "Telefone inválido!\n";
		}
		if (paciente.getEmail().length() > 100) {
			mensagem += "Email pode conter no máximo 100 caracteres!\n";
		}
		if (!validarEMail(paciente.getEmail())) {
			mensagem += "Email inválido!\n";
		}
		if (dao.existePacientePorCpf(paciente)) {
			mensagem += "Já existe um paciente cadastrado com esse CPF!\n";
		}
		if (mensagem.equals("")) {
			if (dao.atualizarPaciente(paciente)) {
				mensagem += "Paciente atualizado com sucesso!\n";
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

	public ArrayList<PacienteVO> consultarTodos() {
		return dao.consultarTodos();
	}

	public String excluirPaciene(PacienteVO paciente) {
		String mensagem = "";
		if (dao.pacientePossuiConsultas(paciente)) {
			mensagem += "Erro ao excluir paciente. Paciente possui consultas agendadas!\n";
		}
		if (dao.pacientePossuiProntuarios(paciente)) {
			mensagem += "Erro ao excluir paciente! Paciente possui prontuários cadastrados no seu nome!\n";
		}
		if (mensagem.equals("")) {
			if (dao.excluirPaciente(paciente.getIdPaciente())) {
				mensagem += "Paciente excluído com sucesso!";
			} else {
				mensagem += "Erro ao excluir Paciente!";
			}
		}
		return mensagem;
	}

	/**
	 * Método para validar email, contendo um @ obrigatorio, dominio(.com.br)
	 * obrigatorio.
	 * 
	 * @param email
	 */
	public boolean validarEMail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);

	}

	/**
	 * Método para validar campos com Strings
	 * 
	 * @param nome
	 */
	public boolean validarCampoStrings(String nome) {
		String regex = "(?i)[a-z ãáâéêíîóôõúü]+";
		return nome.matches(regex);
	}

}

package model.bo;

import java.time.LocalDate;
import java.util.ArrayList;

import model.dao.MedicoDAO;
import model.dao.UsuarioDAO;
import model.seletor.SeletorUsuario;
import model.vo.MedicoVO;

public class MedicoBO {

	public String cadastrarMedico(MedicoVO medico) {
		String mensagem = "";
		MedicoDAO dao = new MedicoDAO();

		if (medico.getNome().length() > 150) {
			mensagem += "Nome pode conter no máximo 150 caracteres!\n";
		}
		if (!validarCampoStrings(medico.getNome())) {
			mensagem += "Nome não pode conter caracteres inválidos";
		}
		if (medico.getNomeUsuario().length() < 5) {
			mensagem += "Nome de Usuário precisa ter mais do que 5 caracteres!\n";
		} else if (medico.getNomeUsuario().length() > 45) {
			mensagem += "Nome de Usuário pode ter no máximo 45 caracteres!\n";
		}
		if (medico.getSenha().length() < 5 || medico.getSenha().length() > 45) {
			mensagem += "Senha precisa ter no mínimo 5 e no máximo 45 caracteres!\n";
		}
		if (medico.getCpf().length() < 14 || medico.getCpf().length() > 14) {
			mensagem += "CPF inválido!\n";
		}
		if (medico.getTelefone().length() < 14 || medico.getTelefone().length() > 14) {
			mensagem += "Telefone inválido!\n";
		}
		if (medico.getEmail().length() > 100) {
			mensagem += "Email pode conter no máximo 100 caracteres!\n";
		}
		if (!validarEMail(medico.getEmail())) {
			mensagem += "Email inválido!\n";
		}
		if (!validarCampoStrings(medico.getEspecialidade())) {
			mensagem += "Especialidade não pode conter caracteres especiais!\n";
		}
		if (medico.getDtNascimento().isAfter(LocalDate.now())) {
			mensagem += "Data inválida! Você ainda não nasceu!";
		}
		if (dao.existeMedicoPorCrm(medico)) {
			mensagem += "Já existe um médico cadastrado com esse CRM!\n";
		}
		if (dao.existeMedicoPorCpf(medico)) {
			mensagem += "Já existe um médico cadastrado com esse CPF!\n";
		}
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.existeNomeDeUsuario(medico)) {
			mensagem += "Nome de Usuário já está sendo usado!\n";
		}
		if (mensagem.equals("")) {
			if (dao.cadastrarMedico(medico) > -1) {
				mensagem += "Médico cadastrado com sucesso!\n";
			} else {
				mensagem += "Erro ao cadastrar médico!\n";
			}
		}
		return mensagem;
	}

	public String atualizarMedico(MedicoVO medico) {
		String mensagem = "";
		if (medico.getNome().length() > 150) {
			mensagem += "Nome pode conter no máximo 150 caracteres!\n";
		}
		if (medico.getNomeUsuario().length() < 5) {
			mensagem += "Nome de Usuário precisa ter mais do que 5 caracteres!\n";
		} else if (medico.getNome().length() > 45) {
			mensagem += "Nome de Usuário pode ter no máximo 45 caracteres!\n";
		}
		if (!validarCampoStrings(medico.getNome())) {
			mensagem += "Nome não pode conter caracteres inválidos";
		}
		if (medico.getSenha().length() < 5 || medico.getSenha().length() > 45) {
			mensagem += "Senha precisa ter no mínimo 5 e no máximo 45 caracteres!\n";
		}
		if (medico.getTelefone().length() < 14 || medico.getTelefone().length() > 14) {
			mensagem += "Telefone inválido!\n";
		}
		if (medico.getEmail().length() > 100) {
			mensagem += "Email pode conter no máximo 100 caracteres!\n";
		}
		if (!validarEMail(medico.getEmail())) {
			mensagem += "Email inválido!\n";
		}
		if (!validarCampoStrings(medico.getEspecialidade())) {
			mensagem += "Especialidade não pode conter caracteres especiais!\n";
		}
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.existeNomeDeUsuario(medico)) {
			mensagem += "Nome de Usuário já está sendo usado!\n";
		}
		if (mensagem.equals("")) {
			MedicoDAO dao = new MedicoDAO();
			if (dao.atualizarMedico(medico)) {
				mensagem += "Médico atualizado com sucesso!\n";
			} else {
				mensagem += "Erro ao atualizar médico!\n";
			}
		}

		return mensagem;
	}

	public ArrayList<MedicoVO> listarMedicos(SeletorUsuario seletor) {
		MedicoDAO dao = new MedicoDAO();
		return dao.listarMedico(seletor);
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
		String regex = "^[A-Za-z ãáâéêíîóôõúü]+$";
		return nome.matches(regex);
	}

	public String excluirMedico(MedicoVO medico) {
		String mensagem = "";
		MedicoDAO dao = new MedicoDAO();
		if (dao.medicoPossuiConsultas(medico)) {
			mensagem += "Erro ao excluir médico! Médico possui consultas agendadas!\n";
		}
		if (dao.medicoPossuiProntuarios(medico)) {
			mensagem += "Erro ao excluir médico! Médico possui prontuários cadastrados no seu nome!\n";
		}
		return mensagem;
	}

	public ArrayList<MedicoVO> consultarTodosMedicos() {
		MedicoDAO dao = new MedicoDAO();
		return dao.consultarTodosMedicos();
	}

}

package model.bo;

import model.dao.MedicoDAO;
import model.dao.UsuarioDAO;
import model.vo.MedicoVO;

public class MedicoBO {

	public String cadastrarMedico(MedicoVO medico) {
		String mensagem = "";
		MedicoDAO dao = new MedicoDAO();

		if (medico.getNome().length() > 150) {
			mensagem += "Nome pode conter no máximo 150 caracteres!\n";
		}
		if (medico.getNomeUsuario().length() < 5) {
			mensagem += "Nome de Usuário precisa ter mais do que 5 caracteres!\n";
		} else if (medico.getNome().length() > 45) {
			mensagem += "Nome de Usuário pode ter no máximo 45 caracteres!\n";
		}
		if (medico.getSenha().length() < 5 || medico.getSenha().length() > 45) {
			mensagem += "Senha precisa ter no mínimo 5 e no máximo 45 caracteres!\n";
		}
		if (medico.getCpf().length() < 14 || medico.getCpf().length() > 11) {
			mensagem += "CPF inválido!\n";
		}
		if (medico.getTelefone().length() < 14 || medico.getTelefone().length() > 11) {
			mensagem += "Telefone inválido!\n";
		}
		if (medico.getEmail().split("@").length != 2) {
			mensagem += "Email inválido!\n";
		}
		if (medico.getEmail().length() > 100) {
			mensagem += "Email pode conter no máximo 100 caracteres!\n";
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
		MedicoDAO dao = new MedicoDAO();
		if (dao.atualizarMedico(medico)) {
			mensagem += "Médico atualizado com sucesso!\n";
		} else {
			mensagem += "Erro ao atualizar médico!\n";
		}
		return mensagem;
	}

}

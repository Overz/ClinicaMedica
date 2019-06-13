package model.bo;

import model.dao.FuncionarioDAO;
import model.vo.FuncionarioVO;

public class FuncionarioBO {

	public String cadastrarFuncionario(FuncionarioVO funcionario) {
		String mensagem = "";
		if (funcionario.getNome().length() > 150) {
			mensagem += "Nome pode conter no máximo 150 caracteres!\n";
		}
		if (funcionario.getNomeUsuario().length() < 5) {
			mensagem += "Nome de Usuário precisa ter mais do que 5 caracteres!\n";
		} else if (funcionario.getNome().length() > 45) {
			mensagem += "Nome de Usuário pode ter no máximo 45 caracteres!\n";
		}
		if (funcionario.getSenha().length() < 5 || funcionario.getSenha().length() > 45) {
			mensagem += "Senha precisa ter no mínimo 5 e no máximo 45 caracteres!\n";
		}
		if (funcionario.getCpf().length() < 11 || funcionario.getCpf().length() > 11) {
			mensagem += "CPF inválido!\n";
		}
		if (funcionario.getTelefone().length() < 11 || funcionario.getTelefone().length() > 11) {
			mensagem += "Telefone inválido!\n";
		}
		if (funcionario.getEmail().split("@").length != 2) {
			mensagem += "Email inválido!\n";
		}
		if (funcionario.getEmail().length() > 100) {
			mensagem += "Email pode conter no máximo 100 caracteres!\n";
		}
		if (mensagem.equals("")) {
			FuncionarioDAO dao = new FuncionarioDAO();
			int idGerado = dao.cadastrarFuncionario(funcionario);
			if (idGerado > 0) {
				mensagem += "Funcionário cadastrado com sucesso!\n";
			} else {
				mensagem += "Erro ao cadastrar usuário!\n";
			}
		}
		return mensagem;
	}

	public String atualizarFuncionario(FuncionarioVO funcionario) {
		String mensagem = "";
		if (funcionario.getNome().length() > 150) {
			mensagem += "Nome pode conter no máximo 150 caracteres!\n";
		}
		if (funcionario.getCpf().length() < 11 || funcionario.getCpf().length() > 11) {
			mensagem += "CPF inválido!\n";
		}
		if (funcionario.getTelefone().length() < 11 || funcionario.getTelefone().length() > 11) {
			mensagem += "Telefone inválido!\n";
		}
		if (funcionario.getEmail().split("@").length != 2) {
			mensagem += "Email inválido!\n";
		}
		if (funcionario.getEmail().length() > 100) {
			mensagem += "Email pode conter no máximo 100 caracteres!\n";
		}
		if (mensagem.equals("")) {
			FuncionarioDAO dao = new FuncionarioDAO();
			if (dao.atualizarFuncionario(funcionario)) {
				mensagem += "Funcionário atualizado com sucesso!\n";
			} else {
				mensagem += "Erro ao atualizar usuário!\n";
			}
		}
		return mensagem;
	}

}

package model.bo;

import java.time.LocalDate;

import model.dao.FuncionarioDAO;
import model.dao.UsuarioDAO;
import model.vo.FuncionarioVO;

public class FuncionarioBO {

	public String cadastrarFuncionario(FuncionarioVO funcionario) {
		String mensagem = "";
		FuncionarioDAO dao = new FuncionarioDAO();
		if (funcionario.getNome().length() > 150) {
			mensagem += "Nome pode conter no máximo 150 caracteres!\n";
		}
		if (!validarCampoStrings(funcionario.getNome())) {
			mensagem += "Nome não pode conter caracteres inválidos";
		}
		if (funcionario.getNomeUsuario().length() < 5) {
			mensagem += "Nome de Usuário precisa ter mais do que 5 caracteres!\n";
		} else if (funcionario.getNomeUsuario().length() > 45) {
			mensagem += "Nome de Usuário pode ter no máximo 45 caracteres!\n";
		}
		if (funcionario.getSenha().length() < 5 || funcionario.getSenha().length() > 45) {
			mensagem += "Senha precisa ter no mínimo 5 e no máximo 45 caracteres!\n";
		}
		if (funcionario.getCpf().length() < 14 || funcionario.getCpf().length() > 14) {
			mensagem += "CPF inválido!\n";
		}
		if (funcionario.getTelefone().length() < 14 || funcionario.getTelefone().length() > 14) {
			mensagem += "Telefone inválido!\n";
		}
		if (funcionario.getEmail().length() > 100) {
			mensagem += "Email pode conter no máximo 100 caracteres!\n";
		}
		if (!validarEMail(funcionario.getEmail())) {
			mensagem += "Email inválido!\n";
		}
		if (funcionario.getDtNascimento().isAfter(LocalDate.now())) {
			mensagem += "Data inválida! Você ainda não nasceu!";
		}
		if (dao.existeFuncionarioPorCpf(funcionario)) {
			mensagem += "Já existe um funcionário cadastrado com esse CPF!\n";
		}
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.existeNomeDeUsuario(funcionario)) {
			mensagem += "Nome de Usuário já está sendo usado!\n";
		}
		if (mensagem.equals("")) {
			int idGerado = dao.cadastrarFuncionario(funcionario);
			if (idGerado > 0) {
				mensagem += "Funcionário cadastrado com sucesso!\n";
			} else {
				mensagem += "Erro ao cadastrar funciário!\n";
			}
		}
		return mensagem;
	}

	public String atualizarFuncionario(FuncionarioVO funcionario) {
		String mensagem = "";
		if (funcionario.getNome().length() > 150) {
			mensagem += "Nome pode conter no máximo 150 caracteres!\n";
		}
		if (!validarCampoStrings(funcionario.getNome())) {
			mensagem += "Nome não pode conter caracteres inválidos";
		}
		if (funcionario.getNomeUsuario().length() < 5) {
			mensagem += "Nome de Usuário precisa ter mais do que 5 caracteres!\n";
		} else if (funcionario.getNome().length() > 45) {
			mensagem += "Nome de Usuário pode ter no máximo 45 caracteres!\n";
		}
		if (funcionario.getSenha().length() < 5 || funcionario.getSenha().length() > 45) {
			mensagem += "Senha precisa ter no mínimo 5 e no máximo 45 caracteres!\n";
		}
		if (funcionario.getTelefone().length() < 14 || funcionario.getTelefone().length() > 14) {
			mensagem += "Telefone inválido!\n";
		}
		if (!validarEMail(funcionario.getEmail())) {
			mensagem += "Email inválido!\n";
		}
		if (funcionario.getEmail().length() > 100) {
			mensagem += "Email pode conter no máximo 100 caracteres!\n";
		}
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.existeNomeDeUsuario(funcionario)) {
			mensagem += "Nome de Usuário já está sendo usado!\n";
		}
		if (mensagem.equals("")) {
			FuncionarioDAO dao = new FuncionarioDAO();
			if (dao.atualizarFuncionario(funcionario)) {
				mensagem += "Funcionário atualizado com sucesso!\n";
			} else {
				mensagem += "Erro ao atualizar funciário!\n";
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
		String regex = "^[A-Za-z ãáâéêíîóôõúü]+$";
		return nome.matches(regex);
	}

	public String excluirFuncionario(FuncionarioVO funcionario) {
		String mensagem = "";
		FuncionarioDAO dao = new FuncionarioDAO();
		if (dao.funcionarioPossuiConsultas(funcionario)) {
			mensagem += "Erro ao excluir funcionário! Funcionário possui consultas agendadas!\n";
		}
		;
		return mensagem;
	}

}

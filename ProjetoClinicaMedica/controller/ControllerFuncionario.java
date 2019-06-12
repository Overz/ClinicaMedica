package controller;

import model.bo.FuncionarioBO;
import model.vo.FuncionarioVO;

public class ControllerFuncionario {

	public String cadastrarFuncionario(String nome, String cpf, String telefone, String email, String usuario,
			String senha, String confirmacaoSenha, String nivel) {
		String mensagem = validarFuncionario(nome, cpf, telefone, email, usuario, senha, confirmacaoSenha, nivel);
		if (mensagem.equals("")) {
			FuncionarioVO funcionario = new FuncionarioVO();
			funcionario.setNome(nome);
			funcionario.setCpf(cpf);
			funcionario.setTelefone(telefone);
			funcionario.setEmail(email);
			funcionario.setNomeUsuario(usuario);
			funcionario.setSenha(senha);

			FuncionarioBO bo = new FuncionarioBO();
			if (bo.cadastrarFuncionario(funcionario) != -1) {
				mensagem = "Funcionário cadastrado com sucesso!";
			} else {
				mensagem = "";
			}
		}

		return mensagem;
	}

	public String validarFuncionario(String nome, String cpf, String telefone, String email, String usuario,
			String senha, String confirmacaoSenha, String nivel) {
		String mensagem = "";
		if (nome == null || nome.trim().equals("")) {
			mensagem += "O campo Nome é obrigatório!\n";
		}
		if (usuario == null || usuario.trim().equals("")) {
			mensagem += "O campo Usuário é obrigatório!\n";
		}
		if (senha == null || senha.trim().equals("")) {
			mensagem += "O campo Senha é obrigatório!\n";
		}
		if (!senha.equals(confirmacaoSenha)) {
			mensagem += "Confirmação de senha e senha não são iguais!\n";
		}

		return mensagem;
	}

}

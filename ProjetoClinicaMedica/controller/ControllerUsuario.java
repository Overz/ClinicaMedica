package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import model.bo.UsuarioBO;
import model.seletor.SeletorUsuario;
import model.vo.UsuarioVO;

public class ControllerUsuario {

	public UsuarioVO login(String usuario, String senha) {
		UsuarioVO vo = null;
		if (usuario != null || !usuario.trim().isEmpty()) {
			if (senha != null || !senha.trim().isEmpty()) {
				UsuarioBO bo = new UsuarioBO();
				vo = bo.login(usuario, senha);
			}
		}
		return vo;
	}

	public ArrayList<UsuarioVO> listarUsuarios() {
		UsuarioBO bo = new UsuarioBO();
		return bo.listarUsuarios();
	}

	public ArrayList<UsuarioVO> listarUsuarios(SeletorUsuario seletor) {
		UsuarioBO bo = new UsuarioBO();
		return bo.listarUsuarios(seletor);
	}

	public String salvarUsuario(String nome, String cpf, String telefone, String email, String crm,
			String especialidade, String usuario, String senha, String confirmacaoSenha, String nivel,
			LocalDate dataAniversario, int idUsuario) {

		String mensagem = "";
		if (nivel.equals(UsuarioVO.NIVEL_FUNCIONARIO) || nivel.equals(UsuarioVO.NIVEL_ADMIN)) {
			mensagem = validarFuncionario(nome, cpf, telefone, email, usuario, senha, confirmacaoSenha, dataAniversario,
					idUsuario);
			if (mensagem.equals("")) {
				ControllerFuncionario controller = new ControllerFuncionario();
				if (idUsuario == 0) {
					mensagem += controller.cadastrarFuncionario(nome, cpf, telefone, email, usuario, confirmacaoSenha,
							nivel, dataAniversario);
				} else {
					mensagem += controller.atualizarFuncionario(nome, cpf, telefone, email, usuario, confirmacaoSenha,
							nivel, dataAniversario, idUsuario);
				}
			}
		}
		if (nivel.equals(UsuarioVO.NIVEL_MEDICO)) {
			mensagem = validarMedico(nome, cpf, telefone, email, crm, especialidade, usuario, senha, confirmacaoSenha,
					dataAniversario, idUsuario);
			if (mensagem.equals("")) {
				ControllerMedico controller = new ControllerMedico();
				if (idUsuario == 0) {
					mensagem += controller.cadastrarMedico(nome, cpf, telefone, email, crm, especialidade, usuario,
							senha, confirmacaoSenha, nivel, dataAniversario);
				} else {
					mensagem += controller.atualizarMedico(nome, cpf, telefone, email, crm, especialidade, usuario,
							senha, confirmacaoSenha, nivel, dataAniversario, idUsuario);
				}

			}
		}

		return mensagem;
	}

	public String validarFuncionario(String nome, String cpf, String telefone, String email, String usuario,
			String senha, String confirmacaoSenha, LocalDate dataAniversario, int idUsuario) {
		String mensagem = "";
		if (nome == null || nome.trim().equals("")) {
			mensagem += "O campo Nome é obrigatório!\n";
		}
		if (dataAniversario == null) {
			mensagem += "Data de Nascimento é obrigatória!\n";
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
		if (cpf == null || cpf.trim().replaceAll("\\.|-", "").equals("")) {
			mensagem += "O campo CPF é obrigatório!\n";
		}
		if (telefone == null || telefone.trim().equals("")) {
			mensagem += "O campo Telefone é obrigatório!\n";
		}
		if (email == null || email.trim().equals("")) {
			mensagem += "O campo Email é obrigatório! \n";
		}

		return mensagem;
	}

	public String validarMedico(String nome, String cpf, String telefone, String email, String crm,
			String especialidade, String usuario, String senha, String confirmacaoSenha, LocalDate dataAniversario,
			int idUsuario) {
		String mensagem = "";
		if (nome == null || nome.trim().equals("")) {
			mensagem += "O campo Nome é obrigatório!\n";
		}
		if (dataAniversario == null) {
			mensagem += "Data de Nascimento é obrigatória!\n";
		}
		if (especialidade == null || especialidade.trim().equals("")) {
			mensagem += "O campo Especialidade é obrigatório!\n";
		}
		if (crm == null || crm.trim().equals("")) {
			mensagem += "O campo CRM é obrigatório!\n";
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
		if (cpf == null || cpf.trim().replaceAll("\\.|-", "").equals("")) {
			mensagem += "O campo CPF é obrigatório!\n";
		}
		if (telefone == null || telefone.trim().equals("")) {
			mensagem += "O campo Telefone é obrigatório!\n";
		}
		if (email == null || email.trim().equals("")) {
			mensagem += "O campo Email é obrigatório!\n";
		}

		return mensagem;
	}

	public String excluirUsuario(UsuarioVO usuario) {
		UsuarioBO bo = new UsuarioBO();
		return bo.excluirUsuario(usuario);
	}
}

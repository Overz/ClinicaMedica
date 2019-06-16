package controller;

import model.bo.MedicoBO;
import model.vo.MedicoVO;

public class ControllerMedico {

	public String cadastrarMedico(String nome, String cpf, String telefone, String email, String crm,
			String especialidade, String usuario, String senha, String confirmacaoSenha, String nivel) {
		String mensagem = validarMedico(nome, cpf, telefone, email, crm, especialidade, usuario, senha,
				confirmacaoSenha);
		if (mensagem.equals("")) {
			MedicoVO medico = new MedicoVO();
			medico.setNome(nome);
			medico.setCpf(cpf);
			medico.setTelefone(telefone);
			medico.setEmail(email);
			medico.setCrm(crm);
			medico.setEspecialidade(especialidade);
			medico.setNomeUsuario(usuario);
			medico.setSenha(senha);

			MedicoBO bo = new MedicoBO();
			if (bo.cadastrarMedico(medico) != -1) {
				mensagem = "Médico cadastrado com sucesso!";
			} else {
				mensagem = "";
			}
		}

		return mensagem;
	}

	public String validarMedico(String nome, String cpf, String telefone, String email, String crm,
			String especialidade, String usuario, String senha, String confirmacaoSenha) {
		String mensagem = "";
		if (nome == null || nome.trim().equals("")) {
			mensagem += "O campo Nome é obrigatório!\n";
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

		return mensagem;
	}

	public String cadastrarMedico(String nome, String cpf, String telefone, String celular, String email, String crm,
			String especialidade, String rua, int numero, String bairro, String cidade, String estado, String cep,
			String usuario, String senha, String confirmacaoSenha) {
		// TODO Auto-generated method stub
		return null;
	}

}

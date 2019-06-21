package controller;

import java.time.LocalDate;

import model.bo.MedicoBO;
import model.vo.MedicoVO;

public class ControllerMedico {

	public String cadastrarMedico(String nome, String cpf, String telefone, String email, String crm,
			String especialidade, String usuario, String senha, String confirmacaoSenha, String nivel,
			LocalDate dataNascimento) {
		String mensagem = "";

		MedicoVO medico = new MedicoVO();
		medico.setNome(nome);
		medico.setCpf(cpf);
		medico.setTelefone(telefone);
		medico.setEmail(email);
		medico.setCrm(crm);
		medico.setEspecialidade(especialidade);
		medico.setNomeUsuario(usuario);
		medico.setSenha(senha);
		medico.setDtNascimento(dataNascimento);
		medico.setNivel(nivel);

		MedicoBO bo = new MedicoBO();
		mensagem = bo.cadastrarMedico(medico);

		return mensagem;
	}

	public String atualizarMedico(String nome, String cpf, String telefone, String email, String crm,
			String especialidade, String usuario, String senha, String confirmacaoSenha, String nivel,
			LocalDate dataNascimento, int idUsuario) {
		String mensagem = "";

		MedicoVO medico = new MedicoVO();
		medico.setNome(nome);
		medico.setCpf(cpf);
		medico.setTelefone(telefone);
		medico.setEmail(email);
		medico.setCrm(crm);
		medico.setEspecialidade(especialidade);
		medico.setNomeUsuario(usuario);
		medico.setSenha(senha);
		medico.setDtNascimento(dataNascimento);
		medico.setNivel(nivel);

		MedicoBO bo = new MedicoBO();
		mensagem = bo.cadastrarMedico(medico);

		return mensagem;
	}

	public String validarTelaInternaMedico(LocalDate date) {
		String mensagem = "";
		
		//TODO DATA
		if (date == null ) {
			
		}
		
		return mensagem;
	}
	
	public boolean validarEMail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	      return email.matches(regex);
	 
	}

	public String validarAgendaMedica(String nomeMedico, String nomeAgenda, String telefone, String email, String texto) {
		String mensagem = "";

		if (nomeAgenda == null || nomeMedico.trim().isEmpty()) {
			mensagem += "Por favor, Digite o Campo Nome!";
		}
		if (nomeAgenda == null || nomeAgenda.trim().isEmpty()) {
			mensagem += "Por favor, Digite o Campo AGENDA, se Referindo ao Nome da Agenda.";
		}
		if (telefone == null || telefone.trim().isEmpty()) {
			mensagem += "Por favor, Digite o Campo TELEFONE!";
		}
		if (!validarEMail(email)) {
			mensagem += "Por Favor, Digite um EMAIL Valido!";
		}
		
		return mensagem;
	}

}

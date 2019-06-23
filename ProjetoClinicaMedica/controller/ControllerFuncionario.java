package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import model.bo.FuncionarioBO;
import model.vo.FuncionarioVO;

public class ControllerFuncionario {

	private FuncionarioVO funcionario;
	private FuncionarioBO bo;

	public String cadastrarFuncionario(String nome, String cpf, String telefone, String email, String usuario,
			String senha, String nivel, LocalDate dataNascimento) {
		String mensagem = "";

		funcionario = new FuncionarioVO();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setTelefone(telefone);
		funcionario.setEmail(email);
		funcionario.setNomeUsuario(usuario);
		funcionario.setSenha(senha);
		funcionario.setNivel(nivel);
		funcionario.setDtNascimento(dataNascimento);

		bo = new FuncionarioBO();
		mensagem = bo.cadastrarFuncionario(funcionario);

		return mensagem;
	}

	public String atualizarFuncionario(String nome, String cpf, String telefone, String email, String usuario,
			String senha, String nivel, LocalDate dataNascimento, int idUsuario) {
		String mensagem = "";
		funcionario = new FuncionarioVO();

		funcionario.setIdUsuario(idUsuario);
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setTelefone(telefone);
		funcionario.setEmail(email);
		funcionario.setNomeUsuario(usuario);
		funcionario.setSenha(senha);
		funcionario.setNivel(nivel);
		funcionario.setDtNascimento(dataNascimento);

		bo = new FuncionarioBO();
		mensagem = bo.atualizarFuncionario(funcionario);

		return mensagem;
	}

	/**
	 * Método para TelaInternaBuscarPaciente que valida os campos de buscas.
	 * @param cpf
	 * @param nome
	 * @param data
	 * @return resultado da pesquisa se encontrado.
	 */

	public ArrayList<FuncionarioVO> ConsultarFuncionarioADM(String nome, String cpf) {
		return bo.consultarFuncionarioADM(nome, cpf);
	}

	public String ValidarCamposConsultasEHorarios(String nome, String cpfCrm, LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

}

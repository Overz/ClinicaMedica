package controller;

import java.time.LocalDate;

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

}

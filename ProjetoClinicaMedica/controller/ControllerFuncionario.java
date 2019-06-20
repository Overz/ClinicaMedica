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
		mensagem = bo.cadastrarFuncionario(funcionario);

		return mensagem;
	}

	/**
	 * Método para TelaInternaBuscarPaciente que valida os campos de buscas.
	 * @param cpf
	 * @param nome
	 * @param data
	 * @return resultado da pesquisa se encontrado.
	 */
	public String validarTelaBuscarPaciente(String cpf, String nome, String data) {
		String mensagem = "";

		if (cpf == null || cpf.trim().isEmpty()) {
			mensagem = "Por favor, Digite algum Campo Acima!";
		}
		if (nome == null || nome.trim().isEmpty()) {
			mensagem = "Por favor, Digite algum Campo Acima!";
		}
		if (data == null || data.trim().isEmpty()) {
			mensagem = "Por favor, Digite algum Campo Acima!";
		}

		return mensagem;
	}

	/**
	 * Método para TelaInterna de Cadastrar Paciente, que valida os campos de busca
	 * @param nome
	 * @param cpf
	 * @param rg
	 * @return resultado da pesquisa se encontrado.
	 */
	public String validarCamposPesquisarCadastroPaciente(String nome, String cpf, String rg) {
		String mensagem = "";

		if (nome == null || nome.trim().isEmpty()) {
			mensagem = "Por favor, Digite algum Campo Acima!";
		}
		if (cpf == null || cpf.trim().isEmpty()) {
			mensagem = "Por favor, Digite algum Campo Acima!";
		}
		if (rg == null || rg.trim().isEmpty()) {
			mensagem = "Por favor, Digite algum Campo Acima!";
		}

		return mensagem;
	}

	/**
	 * Método para validar os campos antes de cadastrar os dados do usuario no banco.
	 * @param nome
	 * @param cpf
	 * @param rg
	 * @param dataString
	 * @param sexo
	 * @param estadoCivil
	 * @param tipoSangue
	 * @param cidade
	 * @param bairro
	 * @param rua
	 * @param numero
	 * @param telefone
	 * @param celular
	 * @param email
	 * @return mensagem de sucesso caso os dados sejam cadastrados de maneira correta.
	 */
	public String validarSalvarCadastroPaciente(String nome, String cpf, String rg, String dataString, int sexo,
			int estadoCivil, int tipoSangue, String cidade, String bairro, String rua, String numero, String telefone,
			String celular, String email) {

		String mensagem = "";

		if (nome == null || nome.trim().isEmpty()) {
			mensagem += "Por favor, Digite o Campo NOME Acima!\n";
		}
		if (cpf == null || cpf.trim().isEmpty()) {
			mensagem += "Por favor, Digite o Campo CPF Acima!\n";
		}
		if (rg == null || rg.trim().isEmpty()) {
			mensagem += "Por favor, Digite o Campo RG Acima!\n";
		}
		if (dataString == null || dataString.trim().isEmpty()) {
			mensagem += "Por favor, Selecione o Campo DATA Acima!\n";
		}
		if (sexo == -1) {
			mensagem += "Por favor, Escolha o Campo SEXO Acima!\n";
		}
		if (estadoCivil == -1) {
			mensagem += "Por favor, Escolha o campo ESTADO CIVIL Acima!\n";
		}
		if (cidade == null || cidade.trim().isEmpty()) {
			mensagem += "Por favor, Digite o Campo CIDADE Acima!\n";
		}
		if (bairro == null || bairro.trim().isEmpty()) {
			mensagem += "Por favor, Digite o Campo BAIRRO Acima!";
		}
		if (rua == null || rua.trim().isEmpty()) {
			mensagem += "Por favor, Digite o campo RUA Acima!";
		}
		if (numero == null || numero.trim().isEmpty()) {
			mensagem += "Por favor, Digite o Campo NUMERO Acima";
		}
		if (telefone == null || telefone.trim().isEmpty()) {
			if (celular == null || celular.trim().isEmpty()) {
				mensagem = "Por favor, Digite TELEFONE ou CELULAR Acima";
			}
		}
		

		return mensagem;
	}

}

package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import model.bo.PacienteBO;
import model.seletor.SeletorPaciente;
import model.vo.PacienteVO;

public class ControllerPaciente {

	private static final String SELECIONE = "[SELECIONE]";
	private PacienteVO vo;
	private PacienteBO bo = new PacienteBO();

	public String salvarPaciente(int idPaciente, String nome, String cpf, LocalDate dtNascimento, String sexo,
			String convenio, String tipoSangue, String estado, String cidade, String bairro, String rua, String cep,
			int numero, String telefone, String email) {
		String mensagem = validarPaciente(idPaciente, nome, cpf, dtNascimento, sexo, convenio, tipoSangue, estado,
				cidade, bairro, rua, cep, numero, telefone, email);

		if (mensagem == "") {
			PacienteVO paciente = montarPaciente(idPaciente, nome, cpf, dtNascimento, sexo, convenio, tipoSangue,
					estado, cidade, bairro, rua, cep, numero, telefone, email);
			if (idPaciente > 0) {
				mensagem += bo.atualizarPaciente(paciente);
			} else {
				mensagem += bo.cadastrarPaciente(paciente);
			}
		}
		return mensagem;
	}

	private PacienteVO montarPaciente(int idPaciente, String nome, String cpf, LocalDate dtNascimento, String sexo,
			String convenio, String tipoSangue, String estado, String cidade, String bairro, String rua, String cep,
			int numero, String telefone, String email) {
		vo = new PacienteVO();
		vo.setIdPaciente(idPaciente);
		vo.setNome(nome);
		vo.setCpf(cpf);
		vo.setDtNascimento(dtNascimento);
		vo.setSexo(sexo);
		vo.setConvenio(convenio);
		vo.setTipoSanguineo(tipoSangue);
		vo.setEstado(estado);
		vo.setCidade(cidade);
		vo.setBairro(bairro);
		vo.setCep(cep);
		vo.setNumero(numero);
		vo.setTelefone(telefone);
		vo.setEmail(email);
		vo.setRua(rua);

		return vo;
	}

	public String excluirPaciente(PacienteVO paciente) {
		return bo.excluirPaciene(paciente);
	}

	/**
	 * Método para validar os campos antes de cadastrar os dados do usuario no
	 * banco.
	 * 
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
	 * @param estado
	 * @param convenio
	 * @param cep
	 * @return mensagem de sucesso caso os dados sejam cadastrados de maneira
	 *         correta.
	 */
	public String validarPaciente(int idPaciente, String nome, String cpf, LocalDate dtNascimento, String sexo,
			String convenio, String tipoSangue, String estado, String cidade, String bairro, String rua, String cep,
			int numero, String telefone, String email) {

		String mensagem = "";

		if (nome == null || nome.trim().isEmpty()) {
			mensagem += "Por favor, Digite o Campo NOME Acima!\n";
		}
		if (cpf == null || cpf.replaceAll("\\.\\.-", "").trim().isEmpty()) {
			mensagem += "Por favor, Digite o Campo CPF Acima!\n";
		}
		if (dtNascimento == null) {
			mensagem += "Por favor, Selecione o Campo DATA Acima!\n";
		}
		if (sexo == null || sexo.equalsIgnoreCase(SELECIONE)) {
			mensagem += "Por favor, Escolha o Campo SEXO Acima!\n";
		}
		if (estado == null || estado.trim().isEmpty()) {
			mensagem += "Por favor, Digite o Campo ESTAdo Acima!\n";
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
		if (numero <= 0) {
			mensagem += "Por favor, Digite o Campo NUMERO Acima Com Valores VALIDOS!\n";
		}
		if (cep == null || cep.trim().isEmpty()) {
			mensagem += "Por favor, Digite o Campo CEP Acima!";
		}
		if (telefone == null || telefone.trim().isEmpty()) {
			mensagem += "Por favor, Digite TELEFONE ou CELULAR Acima!";
		}

		return mensagem;
	}

	/**
	 * Método para consultar todos os Pacientes do Banco.
	 * 
	 * @return ArrayList<PacienteVO>
	 */
	public ArrayList<PacienteVO> consultarTodos() {
		return bo.consultarTodos();
	}

	public ArrayList<PacienteVO> listarPacientes(SeletorPaciente seletor) {
		if (seletor.getCpf().replaceAll("\\.|-", "").trim().isEmpty()) {
			seletor.setCpf(null);
		}
		return bo.buscarPaciente(seletor);
	}
}

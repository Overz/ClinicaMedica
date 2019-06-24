package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import model.bo.PacienteBO;
import model.seletor.SeletorPaciente;
import model.vo.PacienteVO;

public class ControllerPaciente {

	private static final String SELECIONE = "[SELEICONE]";
	private PacienteVO vo = new PacienteVO();
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
		PacienteVO paciente = new PacienteVO();
		paciente.setIdPaciente(idPaciente);
		paciente.setNome(nome);
		paciente.setCpf(cpf);
		paciente.setDtNascimento(dtNascimento);
		paciente.setSexo(sexo);
		paciente.setConvenio(convenio);
		paciente.setTipoSanguineo(tipoSangue);
		paciente.setEstado(estado);
		paciente.setCidade(cidade);
		paciente.setBairro(bairro);
		paciente.setCep(cep);
		paciente.setNumero(numero);
		paciente.setTelefone(telefone);
		paciente.setEmail(email);
		paciente.setRua(rua);

		return paciente;
	}

	public String excluirPaciente(PacienteVO paciente) {
		return bo.excluirPaciene(paciente);
	}

	public String validar(PacienteVO paciente) {
		String mensagem = "";
		if (paciente == null) {
			mensagem += "Paciente está vazio";
		}
		if (paciente.getNome() == null || paciente.getNome().trim().isEmpty()) {
			mensagem += "O campo NOME é obrigatório!\n";
		}
		if (paciente.getCpf() == null || paciente.getCpf().trim().isEmpty()) {
			mensagem += "O campo CPF é obrigatório!\n";
		}
		if (paciente.getTelefone() == null || paciente.getTelefone().trim().isEmpty()) {
			mensagem += "O campo TELEFONE é obrigatório!\n";
		}
		if (paciente.getTipoSanguineo() == null || paciente.getTipoSanguineo().trim().isEmpty()) {
			mensagem += "O campo TIPO SANGUÍNEO é obrigatório!\n";
		}
		if (paciente.getSexo() == null || paciente.getSexo().trim().isEmpty()) {
			mensagem += "O campo SEXO é obrigatório!\n";
		}
		if (paciente.getConvenio() == null || paciente.getConvenio().trim().isEmpty()) {
			mensagem += "O campo CONVENIO é obrigatório!\n";
		}

		// TODO
		return mensagem;
	}

	/**
	 * Método Auxiliar para setar os dados na tela.
	 * 
	 * @param validarCamposPesquisarCadastroPaciente(Seletor)
	 * @param vo
	 * @return
	 */
	public PacienteVO setarDadosNaTela(PacienteVO vo) {
		return vo;
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
		if (cpf == null || cpf.trim().isEmpty()) {
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
			mensagem += "Por favor, Digite o Campo NUMERO Acima, com Valores Validos";
		}
		if (telefone == null || telefone.trim().isEmpty()) {
			mensagem += "Por favor, Digite TELEFONE ou CELULAR Acima";
		}
		if (!validarEMail(email)) {
			mensagem += "Por favor, Digite um EMAIL Valido!";
		}

		return mensagem;
	}

	public boolean validarEMail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);

	}

	public ArrayList<?> consultarTodos() {
		return bo.consultarTodos();
	}

	public ArrayList<PacienteVO> listarPacientes(SeletorPaciente seletor) {
		return bo.buscarPaciente(seletor);
	}

}

package controller;

import java.time.LocalDate;
import java.util.List;

import model.bo.PacienteBO;
import model.seletor.SeletorPaciente;
import model.vo.PacienteVO;

public class ControllerPaciente {

	private static final String SELECIONE = "[SELEICONE]";
	private PacienteVO vo;
	private PacienteBO bo;
	

	public String salvarPaciente(PacienteVO paciente) {
		String mensagem = validar(paciente);

		if (mensagem == "") {
			if (paciente.getIdPaciente() > 0) {
				if (bo.atualizarPaciente(paciente)) {
					mensagem += "Paciente atualizado com sucesso!";
				} else {
					mensagem += "Erro ao atualizar Paciente!";
				}
			} else {
				if (bo.cadastrarPaciente(paciente)) {
					mensagem += "Paciente cadastrado com sucesso!";
				} else {
					mensagem += "Erro ao cadastrar paciente!";
				}
			}
		}
		return mensagem;
	}

	public String excluirPaciente(PacienteVO paciente) {
		String mensagem = "";
		// TODO Implementar validação de ID
		return mensagem;
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

		//TODO
		return mensagem;
	}
	
	/**
	 * Método para TelaInterna de Cadastrar Paciente, que valida os campos de busca
	 * @param nome
	 * @param cpf
	 * @param rg
	 * @return resultado da pesquisa se encontrado.
	 */
	public String validarCamposPesquisarCadastroPaciente(SeletorPaciente seletor) {
		String mensagem = "";

		if (seletor.getNome() == null || seletor.getNome().isEmpty()) {
			mensagem += "Por favor, Digite algum Campo Acima!";
		}
		if (seletor.getCpf() == null || seletor.getCpf().isEmpty()) {
			mensagem += "Por favor, Digite algum Campo Acima!";
		}
		if (seletor.getDate() == null) {
			mensagem += "Por favor, Digite algum Campo Acima!";
		}
		if (mensagem == null || mensagem.trim().isEmpty()) {
			vo = bo.pesquisarPaciente(seletor);
			setarDadosNaTela(vo);
		}
		return mensagem;
	}
	
	/**
	 * Método Auxiliar para setar os dados na tela.
	 * @param validarCamposPesquisarCadastroPaciente(Seletor)
	 * @param vo
	 * @return
	 */
	public PacienteVO setarDadosNaTela(PacienteVO vo) {
		return vo;
	}

	public boolean validarEMail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	      return email.matches(regex);
	 
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
	 * @param estado 
	 * @param convenio 
	 * @param cep 
	 * @return mensagem de sucesso caso os dados sejam cadastrados de maneira correta.
	 */
	public String validarSalvarCadastroPaciente(String nome, String cpf, String rg, LocalDate dtNascimento, String sexo, String convenio,
			String tipoSangue, String estado, String cidade, String bairro, String rua, String cep, int numero, String telefone,
			String email) {
		
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
		//TODO DATA
		if (dtNascimento == null ) {
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
		if (numero < 0) {
			mensagem += "Por favor, Digite o Campo NUMERO Acima, com Valores Validos";
		}
		if (telefone == null || telefone.trim().isEmpty()) {
				mensagem += "Por favor, Digite TELEFONE ou CELULAR Acima";
		}
		if (!validarEMail(email)) {
			mensagem += "Por favor, Digite um EMAIL Valido!";
		}
		
		if (mensagem == null || mensagem.trim().isEmpty()) {
			vo = new PacienteVO((Integer) null, sexo, tipoSangue, rua, numero, bairro, cidade, estado, cep, nome, dtNascimento, cpf, telefone, email, convenio);
			mensagem += bo.salvarCadastroPaciente(vo);
		}

		return mensagem;
	}

	/**
	 * Reaproveitação de Método, com mesmos campos.
	 * @param nome
	 * @param cpfCrm
	 * @param data
	 * @return
	 */
	public String ValidarCamposConsultasEHorarios(String nome, String cpfCrm, LocalDate data) {
		return bo.validarTelaBuscarPaciente(nome, cpfCrm, data);
	}

	public List<PacienteVO> consultarTodos() {
		return bo.consultarTodos();
	}
}

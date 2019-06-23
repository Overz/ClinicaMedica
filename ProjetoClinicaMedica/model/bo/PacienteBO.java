package model.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.dao.PacienteDAO;
import model.seletor.SeletorPaciente;
import model.vo.PacienteVO;

public class PacienteBO {
	
	PacienteVO vo = new PacienteVO();
	PacienteDAO dao = new PacienteDAO();
	
	public boolean atualizarPaciente(PacienteVO paciente) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean cadastrarPaciente(PacienteVO paciente) {
		// TODO Auto-generated method stub
		return false;
	}

	public PacienteVO pesquisarPaciente(SeletorPaciente seletor) {
		return dao.buscarDadosPacienteCpfNomeData(seletor);
	}

	public String salvarCadastroPaciente(PacienteVO vo) {
		String mensagem = "";
		
		int result = dao.cadastrarPaciente(vo);
		if (result == 1 ){
			mensagem += "Paciênte Cadastrado com Sucesso!";
		} else {
			mensagem += "Erro ao Cadastrar o Paciênte";
		}
		
		return mensagem;
	}

	public String validarTelaBuscarPaciente(String nome, String cpfCrm, LocalDate data) {
		String mensagem = "";
		
		return mensagem;
	}

	public List<PacienteVO> consultarTodos() {
		return dao.consultarTodos();
	}

}

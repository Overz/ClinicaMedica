package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.banco.Banco;
import model.vo.MedicoVO;
import model.vo.PacienteVO;
import model.vo.ProntuarioVO;

public class ProntuarioDAO {

	public int salvarProntuario(ProntuarioVO prontuario) {
		int novoId = -1;
		String query = "INSERT INTO PRONTUARIO (IDPACIENTE, IDMEDICO, OBSERVACOES, DATA_PRONTUARIO) "
				+ "VALUES (?, ?, ?, ?)";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query, Statement.RETURN_GENERATED_KEYS);

		try {
			prepStmt.setInt(1, prontuario.getPaciente().getIdPaciente());
			prepStmt.setInt(2, prontuario.getMedico().getIdMedico());
			prepStmt.setString(3, prontuario.getObservacoes());
			prepStmt.setTimestamp(4, Timestamp.valueOf(prontuario.getDtProntuario()));

			prepStmt.execute();

			ResultSet generatedKeys = prepStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				novoId = generatedKeys.getInt(1);
			}
			generatedKeys.close();
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar Prontuário: \n " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return novoId;
	}

	public boolean atualizarProntuario(ProntuarioVO prontuario) {
		boolean sucesso = false;
		String query = "UPDATE PRONTUARIO SET IDPACIENTE=?, IDMEDICO=?, DATA_PRONTUARIO=?, OBSERVACOES=? WHERE IDPRONTUARIO=?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		try {
			prepStmt.setInt(1, prontuario.getPaciente().getIdPaciente());
			prepStmt.setInt(2, prontuario.getMedico().getIdMedico());
			prepStmt.setString(3, prontuario.getObservacoes());
			prepStmt.setTimestamp(4, Timestamp.valueOf(prontuario.getDtProntuario()));
			prepStmt.setInt(5, prontuario.getIdProntuario());

			int resultado = prepStmt.executeUpdate();
			if (resultado == 1) {
				sucesso = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Prontuario: \n " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return sucesso;
	}

	public boolean excluirProntuario(int idProntuario) {
		boolean sucesso = false;

		String query = " DELETE FROM PRONTUARIO " + " WHERE IDPRONTUARIO = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, query);

		try {
			prepStmt.setInt(1, idProntuario);

			int codigoRetorno = prepStmt.executeUpdate();

			if (codigoRetorno == 1) {
				sucesso = true;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao remover Prontuário. Id = " + idProntuario + ". Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}
		return sucesso;
	}

	public ArrayList<ProntuarioVO> buscarProntuario(ProntuarioVO Prontuario) {
		// TODO Implementar método para consulta com seletor
		return null;
	}

	public ProntuarioVO montarProntuario(ResultSet resultado) {

		ProntuarioVO prontuario = new ProntuarioVO();

		try {
			prontuario.setIdProntuario(resultado.getInt("IDPRONTUARIO"));
			prontuario.setDtProntuario(resultado.getTimestamp(("DATA_PRONTUARIO")).toLocalDateTime());
			prontuario.setObservacoes(resultado.getString("OBSERVACOES"));

			PacienteDAO pacienteDAO = new PacienteDAO();
			PacienteVO paciente = pacienteDAO.buscarPacientePorId(resultado.getInt("IDPACIENTE"));
			prontuario.setPaciente(paciente);

			MedicoDAO medicoDAO = new MedicoDAO();
			MedicoVO medico = medicoDAO.buscarMedicoPorId(resultado.getInt("IDMEDICO"));
			prontuario.setMedico(medico);

		} catch (SQLException e) {
			System.out.println("Erro ao construir Consulta: \n" + e.getMessage());
		}

		return prontuario;
	}

	public ArrayList<ProntuarioVO> listarProntuariosPorPaciente(PacienteVO paciente) {
		String query = "SELECT * FROM PRONTUARIO INNER JOIN PACIENTE ON PRONTUARIO.IDPACIENTE = PACIENTE.IDPACIENTE INNER JOIN MEDICO ON PRONTUARIO.IDMEDICO = MEDICO.IDMEDICO WHERE PRONTUARIO.IDPACIENTE = ?";
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, query);

		ArrayList<ProntuarioVO> prontuarios = new ArrayList<ProntuarioVO>();
		try {
			prepStmt.setInt(1, paciente.getIdPaciente());

			ResultSet resultado = prepStmt.executeQuery();
			while (resultado.next()) {
				ProntuarioVO prontuario = montarProntuario(resultado);
				prontuarios.add(prontuario);
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println(
					"Erro ao listar Prontuários do paciente " + paciente.getNome() + ". Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}
		return prontuarios;
	}

	public ArrayList<ProntuarioVO> consultarTodos() {
		String query = " SELECT * FROM PRONTUARIO ";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
		ArrayList<ProntuarioVO> prontuarios = new ArrayList<ProntuarioVO>();

		try {
			ResultSet resultado = prepStmt.executeQuery();
			while (resultado.next()) {
				ProntuarioVO prontuario = montarProntuario(resultado);
				prontuarios.add(prontuario);
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println("Erro ao Consultar Todos os Prontuarios.\n" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return prontuarios;
	}

}

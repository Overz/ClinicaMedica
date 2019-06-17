package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.banco.Banco;
import model.vo.MedicoVO;
import model.vo.PacienteVO;
import model.vo.ProntuarioVO;

public class ProntuarioDAO {

	public int cadastrarProntuario(ProntuarioVO prontuario) {
		int novoId = -1;
		String query = "INSERT INTO PRONTUARIO (IDPACIENTE, IDMEDICO, OBSERVACOES, DATA_PRONTUARIO) "
				+ "VALUES (?, ?, ?, ?)";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query, Statement.RETURN_GENERATED_KEYS);

		try {
			prepStmt.setInt(1, prontuario.getPaciente().getIdPaciente());
			prepStmt.setInt(2, prontuario.getMedico().getIdMedico());
			prepStmt.setString(3, prontuario.getObservacoes());
			prepStmt.setDate(4, Date.valueOf(prontuario.getDtProntuario().toLocalDate()));

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
			prepStmt.setDate(4, Date.valueOf(prontuario.getDtProntuario().toLocalDate()));
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

			PacienteVO paciente = new PacienteVO();
			paciente.setIdPaciente(resultado.getInt("IDPACIENTE"));
			prontuario.setPaciente(paciente);

			MedicoVO medico = new MedicoVO();
			medico.setIdMedico(resultado.getInt("IDMEDICO"));
			prontuario.setMedico(medico);

		} catch (SQLException e) {
			System.out.println("Erro ao construir Consulta: \n" + e.getMessage());
		}

		return prontuario;
	}

}

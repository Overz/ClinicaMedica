package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.banco.Banco;
import model.vo.ConsultaVO;
import model.vo.FuncionarioVO;
import model.vo.MedicoVO;
import model.vo.PacienteVO;

public class ConsultaDAO {

	public int cadastrarConsulta(ConsultaVO consulta) {
		int novoId = -1;
		String query = "INSERT INTO CONSULTA (DATA_CONSULTA, IDPACIENTE, IDMEDICO, IDFUNCIONARIO) "
				+ "VALUES (?, ?, ?, ?)";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query, Statement.RETURN_GENERATED_KEYS);

		try {
			prepStmt.setDate(1, Date.valueOf(consulta.getData_consulta().toLocalDate()));
			prepStmt.setInt(2, consulta.getPaciente().getIdPaciente());
			prepStmt.setInt(3, consulta.getMedico().getIdMedico());
			prepStmt.setInt(4, consulta.getFuncionario().getIdFuncionario());

			prepStmt.execute();

			ResultSet generatedKeys = prepStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				novoId = generatedKeys.getInt(1);
			}
			generatedKeys.close();
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar Consulta: \n " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return novoId;
	}

	public boolean atualizarConsulta(ConsultaVO consulta) {
		boolean sucesso = false;
		String query = "UPDATE CONSULTA SET DATA_CONSULTA=?, IDPACIENTE=?,IDMEDICO=?, IDFUNCIONARIO=? WHERE IDCONSULTA=?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		try {
			prepStmt.setDate(1, Date.valueOf(consulta.getData_consulta().toLocalDate()));
			prepStmt.setInt(2, consulta.getPaciente().getIdPaciente());
			prepStmt.setInt(3, consulta.getMedico().getIdMedico());
			prepStmt.setInt(4, consulta.getFuncionario().getIdFuncionario());
			prepStmt.setInt(5, consulta.getIdConsulta());

			int resultado = prepStmt.executeUpdate();
			if (resultado == 1) {
				sucesso = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Consulta: \n " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return sucesso;
	}

	public boolean excluirConsulta(int idConsulta) {
		boolean sucesso = false;

		String query = " DELETE FROM CONSULTA " + " WHERE IDCONSULTA = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, query);

		try {
			prepStmt.setInt(1, idConsulta);

			int codigoRetorno = prepStmt.executeUpdate();

			if (codigoRetorno == 1) {
				sucesso = true;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao remover Consulta. Id = " + idConsulta + ". Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}
		return sucesso;
	}

	public ArrayList<ConsultaVO> buscarConsulta(ConsultaVO consulta) {
		// TODO Implementar método para consulta com seletor
		return null;
	}

	public ConsultaVO montarConsulta(ResultSet resultado) {

		ConsultaVO consulta = new ConsultaVO();

		try {
			consulta.setIdConsulta(resultado.getInt("IDCONSULTA"));
			consulta.setData_consulta(resultado.getTimestamp("DATA_CONSULTA").toLocalDateTime());

			PacienteVO paciente = new PacienteVO();
			paciente.setIdPaciente(resultado.getInt("IDPACIENTE"));
			consulta.setPaciente(paciente);

			MedicoVO medico = new MedicoVO();
			medico.setIdMedico(resultado.getInt("IDMEDICO"));
			consulta.setMedico(medico);

			FuncionarioVO funcionario = new FuncionarioVO();
			funcionario.setIdFuncionario(resultado.getInt("IDFUNCIONARIO"));
			consulta.setFuncionario(funcionario);
		} catch (SQLException e) {
			System.out.println("Erro ao construir Consulta: \n" + e.getMessage());
		}

		return consulta;
	}

}

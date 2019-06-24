package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

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
			prepStmt.setTimestamp(1, Timestamp.valueOf(consulta.getData_consulta()));
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
			prepStmt.setTimestamp(1, Timestamp.valueOf(consulta.getData_consulta()));
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

	public ConsultaVO montarConsulta(ResultSet resultado) {

		ConsultaVO consulta = new ConsultaVO();

		try {
			consulta.setIdConsulta(resultado.getInt("IDCONSULTA"));
			consulta.setData_consulta(resultado.getTimestamp("DATA_CONSULTA").toLocalDateTime());

			PacienteDAO pacienteDAO = new PacienteDAO();
			PacienteVO paciente = pacienteDAO.buscarPacientePorId(resultado.getInt("IDPACIENTE"));
			consulta.setPaciente(paciente);

			MedicoDAO medicoDAO = new MedicoDAO();
			MedicoVO medico = medicoDAO.buscarMedicoPorId(resultado.getInt("IDMEDICO"));
			consulta.setMedico(medico);

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			FuncionarioVO funcionario = funcionarioDAO.buscarFuncionarioPorId(resultado.getInt("IDFUNCIONARIO"));
			consulta.setFuncionario(funcionario);
		} catch (SQLException e) {
			System.out.println("Erro ao construir Consulta: \n" + e.getMessage());
		}

		return consulta;
	}

	public ArrayList<ConsultaVO> pesquisarConsultasPorDataEMedico(LocalDate data, MedicoVO medico) {
		String query = "SElECT * FROM CONSULTA INNER JOIN MEDICO ON CONSULTA.IDMEDICO = MEDICO.IDMEDICO "
				+ "INNER JOIN PACIENTE ON CONSULTA.IDPACIENTE = PACIENTE.IDPACIENTE WHERE CONSULTA.IDMEDICO = ? "
				+ "AND DATE(DATA_CONSULTA) = ?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
		ArrayList<ConsultaVO> consultas = new ArrayList<ConsultaVO>();

		try {
			prepStmt.setInt(1, medico.getIdMedico());
			prepStmt.setDate(2, Date.valueOf(data));

			ResultSet resultado = prepStmt.executeQuery();
			while (resultado.next()) {
				ConsultaVO consulta = this.montarConsulta(resultado);
				consultas.add(consulta);
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println(
					"Erro ao pesquisar consultas do médico " + medico.toString() + ". Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}

		return consultas;
	}

	public ArrayList<?> consultarTudos() {
		String query = " SELECT * FROM CONSULTA ";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
		ArrayList<ConsultaVO> consulta = new ArrayList<ConsultaVO>();

		try {

			ResultSet resultado = prepStmt.executeQuery();

			while (resultado.next()) {
				ConsultaVO consultas = montarConsulta(resultado);
				consulta.add(consultas);
			}
			resultado.close();

		} catch (SQLException e) {
			System.out.println("Erro ao Buscar Todas as Consultas.\n" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}

		return consulta;
	}
	
	public boolean existeConsultaPorDataEMedico(ConsultaVO consulta) {
		boolean sucesso = false;
		String query = "SELECT * FROM CONSULTA WHERE IDMEDICO = ? AND DATE(DATA_CONSULTA) = ?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		try {
			prepStmt.setInt(1, consulta.getMedico().getIdMedico());
			prepStmt.setTimestamp(2, Timestamp.valueOf(consulta.getData_consulta()));

			ResultSet resultado = prepStmt.executeQuery();

			if (resultado.next()) {
				sucesso = true;
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println("Erro ao verificar se existe consulta agendada com médico por data: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return sucesso;

	}

	public Boolean deletarConsulta(ConsultaVO consultaSelecionada) {
		boolean sucesso = false;
		String query = "DELETE FROM CONSULTA WHERE IDCONSULTA = ? ";
		
		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
		
		try {
			prepStmt.setInt(1, consultaSelecionada.getIdConsulta());
			ResultSet resultado = prepStmt.executeQuery();
			if (resultado.next()) {
				sucesso = true;
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println("Erro ao Excluir a Consulta.\n" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		
		return sucesso;
	}

}

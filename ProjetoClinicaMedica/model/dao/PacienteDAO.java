package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.banco.Banco;
import model.seletor.SeletorPaciente;
import model.vo.PacienteVO;

public class PacienteDAO {

	public int cadastrarPaciente(PacienteVO paciente) {
		int novoId = -1;
		String query = "INSERT INTO PACIENTE (NOME, CPF, TELEFONE, EMAIL, SEXO, "
				+ "TIPO_SANGUINEO, DATA_NASCIMENTO, CONVENIO, RUA, NUMERO, BAIRRO, CIDADE, ESTADO, "
				+ "CEP) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query, Statement.RETURN_GENERATED_KEYS);

		try {
			prepStmt.setString(1, paciente.getNome());
			prepStmt.setString(2, paciente.getCpf());
			prepStmt.setString(3, paciente.getTelefone());
			prepStmt.setString(4, paciente.getEmail());
			prepStmt.setString(5, paciente.getSexo());
			prepStmt.setString(6, paciente.getTipoSanguineo());
			prepStmt.setDate(7, Date.valueOf(paciente.getDtNascimento()));
			prepStmt.setString(8, paciente.getConvenio());
			prepStmt.setString(9, paciente.getRua());
			prepStmt.setInt(10, paciente.getNumero());
			prepStmt.setString(11, paciente.getBairro());
			prepStmt.setString(12, paciente.getCidade());
			prepStmt.setString(13, paciente.getEstado());
			prepStmt.setString(14, paciente.getCep());

			prepStmt.execute();

			ResultSet generatedKeys = prepStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				novoId = generatedKeys.getInt(1);
			}
			generatedKeys.close();
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar Paciente: \n" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return novoId;
	}

	public boolean atualizarPaciente(PacienteVO paciente) {
		boolean sucesso = false;
		String query = "UPDATE PACIENTE SET NOME=?, CPF=?, TELEFONE=?, EMAIL=?, SEXO=?, "
				+ "TIPO_SANGUINEO=?, DATA_NASCIMENTO=?, CONVENIO=?, RUA=?, NUMERO=?, BAIRRO=?, CIDADE=?, "
				+ "ESTADO=?, CEP=? WHERE IDPACIENTE = ?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		try {
			prepStmt.setString(1, paciente.getNome());
			prepStmt.setString(2, paciente.getCpf());
			prepStmt.setString(3, paciente.getTelefone());
			prepStmt.setString(4, paciente.getEmail());
			prepStmt.setString(5, paciente.getSexo());
			prepStmt.setString(6, paciente.getTipoSanguineo());
			prepStmt.setDate(7, Date.valueOf(paciente.getDtNascimento()));
			prepStmt.setString(8, paciente.getConvenio());
			prepStmt.setString(9, paciente.getRua());
			prepStmt.setInt(10, paciente.getNumero());
			prepStmt.setString(11, paciente.getBairro());
			prepStmt.setString(12, paciente.getCidade());
			prepStmt.setString(13, paciente.getEstado());
			prepStmt.setString(14, paciente.getCep());
			prepStmt.setInt(15, paciente.getIdPaciente());

			int resultado = prepStmt.executeUpdate();
			if (resultado == 1) {
				sucesso = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Paciente: \n " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return sucesso;
	}

	public boolean excluirPaciente(int idPaciente) {
		boolean sucesso = false;

		String query = " DELETE FROM PACIENTE " + " WHERE IDPACIENTE = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, query);

		try {
			prepStmt.setInt(1, idPaciente);

			int codigoRetorno = prepStmt.executeUpdate();

			if (codigoRetorno == 1) {
				sucesso = true;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao remover Paciente. Id = " + idPaciente + ". Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}
		return sucesso;
	}

	public PacienteVO montarPaciente(ResultSet resultado) {

		PacienteVO paciente = new PacienteVO();

		try {
			paciente.setIdPaciente(resultado.getInt("IDPACIENTE"));
			paciente.setNome(resultado.getString("NOME"));
			paciente.setCpf(resultado.getString("CPF"));
			paciente.setTelefone(resultado.getString("TELEFONE"));
			paciente.setEmail(resultado.getString("EMAIL"));
			paciente.setSexo(resultado.getString("SEXO"));
			paciente.setTipoSanguineo(resultado.getString("TIPO_SANGUINEO"));
			paciente.setDtNascimento(resultado.getDate("DATA_NASCIMENTO").toLocalDate());
			paciente.setConvenio(resultado.getString("CONVENIO"));
			paciente.setRua(resultado.getString("RUA"));
			paciente.setNumero(resultado.getInt("NUMERO"));
			paciente.setBairro(resultado.getString("BAIRRO"));
			paciente.setCidade(resultado.getString("CIDADE"));
			paciente.setEstado(resultado.getString("ESTADO"));
			paciente.setCep(resultado.getString("CEP"));
		} catch (SQLException e) {
			System.out.println("Erro ao construir Paciente.\nErro:" + e.getMessage());
		}

		return paciente;
	}

	public PacienteVO buscarPacientePorId(int idPaciente) {
		String query = "SELECT * FROM PACIENTE WHERE IDPACIENTE = ?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		PacienteVO paciente = null;

		try {
			prepStmt.setInt(1, idPaciente);
			ResultSet resultado = prepStmt.executeQuery();
			if (resultado.next()) {
				paciente = montarPaciente(resultado);
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println("Erro ao buscar Paciente por ID: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return paciente;
	}

	public String criarFiltro(SeletorPaciente seletor, String query) {

		query += " WHERE ";
		boolean primeiro = true;

		if (seletor.getNome() != null && !seletor.getNome().trim().isEmpty()) {
			if (!primeiro) {
				query += " AND ";
			}
			query += "NOME LIKE '%" + seletor.getNome() + "%'";
		}
		if (seletor.getCpf() != null && !seletor.getCpf().trim().isEmpty()) {
			if (!primeiro) {
				query += " AND ";
			}
			query += "CPF LIKE '%" + seletor.getCpf() + "%'";
		}
		if (seletor.getDate() != null) {
			if (!primeiro) {
				query += " AND ";
			}
			query += "DATA_NASCIMENTO BETWEEN " + seletor.getDate();
		}
		return query;
	}

	public ArrayList<PacienteVO> buscarPaciente(SeletorPaciente seletor) {
		String query = "SELECT * FROM PACIENTE";

		if (seletor.temFiltro()) {
			query = criarFiltro(seletor, query);
		}
		if (seletor.temPaginacao()) {
			query += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		}

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
		ArrayList<PacienteVO> pacientes = new ArrayList<PacienteVO>();

		try {
			ResultSet resultado = prepStmt.executeQuery();

			while (resultado.next()) {
				PacienteVO paciente = montarPaciente(resultado);
				pacientes.add(paciente);
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println("Erro ao buscar Pacientes: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return pacientes;
	}

	public ArrayList<?> consultarTodos() {
		String query = " SELECT * FROM PACIENTE ";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
		ArrayList<PacienteVO> pacientes = new ArrayList<PacienteVO>();
		try {
			ResultSet resultado = prepStmt.executeQuery();

			while (resultado.next()) {
				PacienteVO paciente = montarPaciente(resultado);
				pacientes.add(paciente);
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println("Erro ao buscar Todos os Pacientes.\n" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return pacientes;
	}

	public boolean existePacientePorCpf(PacienteVO paciente) {
		boolean sucesso = false;
		String query = "SELECT * FROM PACIENTE WHERE CPF = ? AND IDPACIENTE != ?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		try {
			prepStmt.setString(1, paciente.getCpf());
			prepStmt.setInt(2, paciente.getIdPaciente());
			ResultSet resultado = prepStmt.executeQuery();

			if (resultado.next()) {
				sucesso = true;
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println("Erro ao verificar se existe Paciente por CPF: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return sucesso;
	}

}

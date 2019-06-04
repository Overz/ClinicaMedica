package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.banco.Banco;
import model.vo.PacienteVO;

public class PacienteDAO {

	public int cadastrarPaciente(PacienteVO paciente) {
		int novoId = -1;
		String query = "INSERT INTO PACIENTE (NOME, CPF, TELEFONE, EMAIL, SEXO, "
				+ "TIPO_SANGUINEO, DATA_NASCIMENTO, RUA, NUMERO, BAIRRO, CIDADE, ESTADO, "
				+ "CEP) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query, Statement.RETURN_GENERATED_KEYS);

		try {
			prepStmt.setString(1, paciente.getNome());
			prepStmt.setString(2, paciente.getCpf());
			prepStmt.setString(3, paciente.getTelefone());
			prepStmt.setString(4, paciente.getEmail());
			prepStmt.setString(5, paciente.getSexo());
			prepStmt.setString(6, paciente.getTipoSanguineo());
			prepStmt.setDate(7, paciente.getDtNascimento());
			prepStmt.setString(8, paciente.getRua());
			prepStmt.setInt(9, paciente.getNumero());
			prepStmt.setString(10, paciente.getBairro());
			prepStmt.setString(11, paciente.getCidade());
			prepStmt.setString(12, paciente.getEstado());
			prepStmt.setString(13, paciente.getCep());

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
				+ "TIPO_SANGUINEO=?, DATA_NASCIMENTO=?, RUA=?, NUMERO=?, BAIRRO=?, CIDADE=?, "
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
			prepStmt.setDate(7, paciente.getDtNascimento());
			prepStmt.setString(8, paciente.getRua());
			prepStmt.setInt(9, paciente.getNumero());
			prepStmt.setString(10, paciente.getBairro());
			prepStmt.setString(11, paciente.getCidade());
			prepStmt.setString(12, paciente.getEstado());
			prepStmt.setString(13, paciente.getCep());
			prepStmt.setInt(14, paciente.getIdPaciente());

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

		String query = " DELETE FROM PACIENTE " + " WHERE ID = ? ";

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

	public ArrayList<PacienteVO> buscarPaciente(PacienteVO paciente) {
		// TODO Implementar método para consulta com seletor
		return null;
	}

	public String construirFiltros() {
		// TODO Implementar método de construção de filtros
		return null;
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
			paciente.setDtNascimento(resultado.getDate("DATA_NASCIMENTO"));
			paciente.setRua(resultado.getString("RUA"));
			paciente.setNumero(resultado.getInt("NUMERO"));
			paciente.setBairro(resultado.getString("BAIRRO"));
			paciente.setCidade(resultado.getString("CIDADE"));
			paciente.setEstado(resultado.getString("ESTADO"));
			paciente.setCep(resultado.getString("CEP"));
		} catch (SQLException e) {
			System.out.println("Erro ao construir Paciente: \n" + e.getMessage());
		}

		return paciente;
	}

}

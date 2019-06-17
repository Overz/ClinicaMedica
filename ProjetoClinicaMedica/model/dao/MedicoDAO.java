package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.banco.Banco;
import model.vo.MedicoVO;
import model.vo.UsuarioVO;

public class MedicoDAO {

	public MedicoVO consultarMedicoPorUsuario(UsuarioVO usuarioVO) {
		// TODO Auto-generated method stub
		return null;
	}

	public int cadastrarMedico(MedicoVO medico) {
		int novoId = -1;
		String query = "INSERT INTO MEDICO (NOME, CPF, TELEFONE, EMAIL, CRM, ESPECIALIDADE, DATA_NASCIMENTO, IDUSUARIO) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query, Statement.RETURN_GENERATED_KEYS);

		try {
			prepStmt.setString(1, medico.getNome());
			prepStmt.setString(2, medico.getCpf());
			prepStmt.setString(3, medico.getTelefone());
			prepStmt.setString(4, medico.getEmail());
			prepStmt.setString(5, medico.getCrm());
			prepStmt.setString(6, medico.getEspecialidade());
			prepStmt.setDate(7, medico.getDtNascimento());
			prepStmt.setInt(8, medico.getIdUsuario());

			prepStmt.execute();

			ResultSet generatedKeys = prepStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				novoId = generatedKeys.getInt(1);
			}
			generatedKeys.close();
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar Médico: \n " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return novoId;
	}

	public boolean atualizarMedico(MedicoVO medico) {
		boolean sucesso = false;
		String query = "UPDATE MEDICO SET NOME=?, CPF=?,TELEFONE=?, EMAIL=?, CRM=?, ESPECIALIDADE=?, DATA_NASCIMENTO=? WHERE IDMEDICO=?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		try {
			prepStmt.setString(1, medico.getNome());
			prepStmt.setString(2, medico.getCpf());
			prepStmt.setString(3, medico.getTelefone());
			prepStmt.setString(4, medico.getEmail());
			prepStmt.setString(5, medico.getCrm());
			prepStmt.setString(6, medico.getEspecialidade());
			prepStmt.setDate(7, medico.getDtNascimento());
			prepStmt.setInt(8, medico.getIdMedico());

			int resultado = prepStmt.executeUpdate();
			if (resultado == 1) {
				sucesso = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Médico: \n " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return sucesso;
	}

	public boolean excluirMedico(int idMedico) {
		boolean sucesso = false;

		String query = " DELETE FROM MEDICO " + " WHERE IDMEDICO = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, query);

		try {
			prepStmt.setInt(1, idMedico);

			int codigoRetorno = prepStmt.executeUpdate();

			if (codigoRetorno == 1) {
				sucesso = true;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao remover Médico. Id = " + idMedico + ". Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}
		return sucesso;
	}

	public ArrayList<MedicoVO> buscarMedico(MedicoVO medico) {
		// TODO Implementar método para consulta com seletor
		return null;
	}

	public String construirFiltros() {
		// TODO Implementar método de construção de filtros
		return null;
	}

}

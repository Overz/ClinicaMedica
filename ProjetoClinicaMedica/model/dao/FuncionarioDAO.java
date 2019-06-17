package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.banco.Banco;
import model.vo.FuncionarioVO;
import model.vo.UsuarioVO;

public class FuncionarioDAO {

	public FuncionarioVO consultarPorUsuario(UsuarioVO usuarioVO) {
		// TODO Auto-generated method stub
		return null;
	}

	public int cadastrarFuncionario(FuncionarioVO funcionario) {
		int novoId = -1;
		String query = "INSERT INTO FUNCIONARIO (NOME, CPF, TELEFONE, EMAIL, DATA_NASCIMENTO, IDUSUARIO) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query, Statement.RETURN_GENERATED_KEYS);

		try {
			prepStmt.setString(1, funcionario.getNome());
			prepStmt.setString(2, funcionario.getCpf());
			prepStmt.setString(3, funcionario.getTelefone());
			prepStmt.setString(4, funcionario.getEmail());
			prepStmt.setDate(5, Date.valueOf(funcionario.getDtNascimento()));

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			funcionario.setIdUsuario(usuarioDAO.cadastrarUsuario(funcionario));

			prepStmt.setInt(6, funcionario.getIdUsuario());

			prepStmt.execute();

			ResultSet generatedKeys = prepStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				novoId = generatedKeys.getInt(1);
			}
			generatedKeys.close();
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar Funcionário: \n " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return novoId;
	}

	public boolean atualizarFuncionario(FuncionarioVO funcionario) {
		boolean sucesso = false;
		String query = "UPDATE FUNCIONARIO SET NOME=?, CPF=?, TELEFONE=?, EMAIL=?, DATA_NASCIMENTO=? WHERE IDFUNCIONARIO=?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		try {
			prepStmt.setString(1, funcionario.getNome());
			prepStmt.setString(2, funcionario.getCpf());
			prepStmt.setString(3, funcionario.getTelefone());
			prepStmt.setString(4, funcionario.getEmail());
			prepStmt.setDate(5, Date.valueOf(funcionario.getDtNascimento()));
			prepStmt.setInt(6, funcionario.getIdFuncionario());

			int resultado = prepStmt.executeUpdate();
			if (resultado == 1) {
				sucesso = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Funcionário: \n " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return sucesso;
	}

	public boolean excluirFuncionario(int idFuncionario) {
		boolean sucesso = false;

		String query = " DELETE FROM FUNCIONARIO " + " WHERE ID = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, query);

		try {
			prepStmt.setInt(1, idFuncionario);

			int codigoRetorno = prepStmt.executeUpdate();

			if (codigoRetorno == 1) {
				sucesso = true;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao remover Funcionário. Id = " + idFuncionario + ". Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}
		return sucesso;
	}

	public ArrayList<FuncionarioVO> buscarFuncionario(FuncionarioVO funcionario) {
		// TODO Implementar método para consulta com seletor
		return null;
	}

	public String construirFiltros() {
		// TODO Implementar método de construção de filtros
		return null;
	}

}

package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.banco.Banco;
import model.vo.UsuarioVO;

public class UsuarioDAO {

	public int cadastrarUsuario(UsuarioVO usuario) {
		int novoId = -1;
		String query = "INSERT INTO USUARIO (USUARIO, SENHA, NIVEL) " + " VALUES (?, ?, ?)";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query, Statement.RETURN_GENERATED_KEYS);

		try {
			prepStmt.setString(1, usuario.getNomeUsuario());
			prepStmt.setString(2, usuario.getSenha());
			prepStmt.setString(3, usuario.getNivel());

			prepStmt.execute();

			ResultSet generatedKeys = prepStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				novoId = generatedKeys.getInt(1);
			}
			generatedKeys.close();
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar Usuário: \n " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return novoId;
	}

	public boolean atualizarUsuario(UsuarioVO usuario) {
		boolean sucesso = false;
		String query = "UPDATE USUARIO SET USUARIO=?, SENHA=?,NIVEL=? WHERE IDFUNCIONARIO=?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		try {
			prepStmt.setString(1, usuario.getNomeUsuario());
			prepStmt.setString(2, usuario.getSenha());
			prepStmt.setString(3, usuario.getNivel());
			prepStmt.setInt(4, usuario.getIdUsuario());

			int resultado = prepStmt.executeUpdate();
			if (resultado == 1) {
				sucesso = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar m�dico: \n " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return sucesso;
	}

	public boolean excluirUsuario(int idUsuario) {
		boolean sucesso = false;

		String query = " DELETE FROM USUARIO " + " WHERE IDUSUARIO = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, query);

		try {
			prepStmt.setInt(1, idUsuario);

			int codigoRetorno = prepStmt.executeUpdate();

			if (codigoRetorno == 1) {
				sucesso = true;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao remover Usuário. Id = " + idUsuario + ". Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}
		return sucesso;
	}

	public ArrayList<UsuarioVO> buscarUsuario(UsuarioVO usuario) {
		// TODO Implementar método para consulta com seletor
		return null;
	}

	public UsuarioVO montarUsuario(ResultSet resultado) {

		UsuarioVO usuario = new UsuarioVO();

		try {
			usuario.setIdUsuario(resultado.getInt("IDUSUARIO"));
			usuario.setNomeUsuario(resultado.getString("USUARIO"));
			usuario.setSenha(resultado.getString("SENHA"));
			usuario.setNivel(resultado.getString("NIVEL"));
		} catch (SQLException e) {
			System.out.println("Erro ao construir Usuario: \n" + e.getMessage());
		}

		return usuario;
	}

	public String construirFiltros() {
		// TODO Implementar método de construção de filtros
		return null;
	}

	public UsuarioVO login(String usuario, String senha) {
		UsuarioVO usuarioVO = new UsuarioVO();

		String query = "SELECT * FROM USUARIO WHERE USUARIO = ? AND SENHA = ?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		try {
			prepStmt.setString(1, usuario);
			prepStmt.setString(2, senha);
			ResultSet resultado = prepStmt.executeQuery();

			if (resultado.next()) {
				usuarioVO.setIdUsuario(resultado.getInt("IDUSUARIO"));
				usuarioVO.setNomeUsuario(resultado.getString("USUARIO"));
				usuarioVO.setSenha(resultado.getString("SENHA"));
				usuarioVO.setNivel(resultado.getString("NIVEL"));

				if (resultado.getString("NIVEL").equals("Médico")) {
					MedicoDAO medico = new MedicoDAO();
					usuarioVO = medico.buscarMedicoPorUsuario(usuarioVO);
				} else if (resultado.getString("NIVEL").equals("Funcionário")) {
					FuncionarioDAO funcionario = new FuncionarioDAO();
					usuarioVO = funcionario.buscarFuncionarioPorUsuario(usuarioVO);
				}
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println("Erro ao efetuar login: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}

		return usuarioVO;
	}

	public boolean existeNomeDeUsuario(UsuarioVO usuarioVO) {
		boolean sucesso = false;
		String query = "SELECT * FROM USUARIO WHERE USUARIO = ?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		try {
			prepStmt.setString(1, usuarioVO.getNomeUsuario());
			ResultSet resultado = prepStmt.executeQuery();

			if (resultado.next()) {
				sucesso = true;
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println("Erro ao verificar se nome de usuário já existe: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return sucesso;
	}

}

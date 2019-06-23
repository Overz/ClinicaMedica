package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.banco.Banco;
import model.seletor.SeletorUsuario;
import model.vo.FuncionarioVO;
import model.vo.MedicoVO;
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
		String query = "UPDATE USUARIO SET USUARIO=?, SENHA=?,NIVEL=? WHERE IDUSUARIO=?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		try {
			prepStmt.setString(1, usuario.getNomeUsuario());
			prepStmt.setString(2, usuario.getSenha());
			prepStmt.setString(3, usuario.getNivel());
			prepStmt.setInt(4, usuario.getIdUsuario());

			int resultado = prepStmt.executeUpdate();
			if (resultado > 0) {
				sucesso = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar usuário: \n " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return sucesso;
	}

	public boolean excluirUsuario(UsuarioVO usuario) {
		boolean sucesso = false;

		String query = " DELETE FROM USUARIO " + " WHERE IDUSUARIO = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, query);

		try {
			prepStmt.setInt(1, usuario.getIdUsuario());

			int resultado = 0;
			if (usuario instanceof MedicoVO) {
				MedicoDAO medicoDAO = new MedicoDAO();
				if (medicoDAO.excluirMedico((MedicoVO) usuario)) {
					resultado = prepStmt.executeUpdate();
				}
			} else if (usuario instanceof FuncionarioVO) {
				FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
				if (funcionarioDAO.excluirFuncionario((FuncionarioVO) usuario)) {
					resultado = prepStmt.executeUpdate();
				}
			}

			if (resultado > 0) {
				sucesso = true;
			}

		} catch (SQLException e) {
			System.out
					.println("Erro ao remover Usuário. Id = " + usuario.getIdUsuario() + ". Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}
		return sucesso;
	}

	public ArrayList<UsuarioVO> buscarUsuario(UsuarioVO usuario) {
		return null;
	}

	public UsuarioVO montarUsuario(ResultSet resultado) {

		UsuarioVO usuario = new UsuarioVO();

		try {
			usuario.setIdUsuario(resultado.getInt("IDUSUARIO"));
			usuario.setNomeUsuario(resultado.getString("USUARIO"));
			usuario.setSenha(resultado.getString("SENHA"));
			usuario.setNivel(resultado.getString("NIVEL"));

			if (resultado.getString("NIVEL").equals("Médico")) {
				MedicoDAO medico = new MedicoDAO();
				usuario = medico.buscarMedicoPorUsuario(usuario);
			} else if (resultado.getString("NIVEL").equals("Funcionário")) {
				FuncionarioDAO funcionario = new FuncionarioDAO();
				usuario = funcionario.buscarFuncionarioPorUsuario(usuario);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao construir Usuario: \n" + e.getMessage());
		}

		return usuario;
	}

	public UsuarioVO login(String usuario, String senha) {
		UsuarioVO usuarioVO = null;

		String query = "SELECT * FROM USUARIO WHERE USUARIO = ? AND SENHA = ?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		try {
			prepStmt.setString(1, usuario);
			prepStmt.setString(2, senha);
			ResultSet resultado = prepStmt.executeQuery();

			if (resultado.next()) {
				usuarioVO = montarUsuario(resultado);
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
		String query = "SELECT * FROM USUARIO WHERE USUARIO = ? AND IDUSUARIO != ?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		try {
			prepStmt.setString(1, usuarioVO.getNomeUsuario());
			prepStmt.setInt(2, usuarioVO.getIdUsuario());
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

	public ArrayList<UsuarioVO> listarUsuarios() {
		String query = "SELECT USUARIO.IDUSUARIO, USUARIO.USUARIO, USUARIO.SENHA, USUARIO.NIVEL, USUARIOS.IDFUNCIONARIO, USUARIOS.IDMEDICO, "
				+ "USUARIOS.NOME, USUARIOS.CPF, USUARIOS.TELEFONE, USUARIOS.EMAIL, USUARIOS.DATA_NASCIMENTO, USUARIOS.CRM, USUARIOS.ESPECIALIDADE "
				+ "FROM USUARIO LEFT JOIN ((SELECT MEDICO.IDMEDICO, 0 AS IDFUNCIONARIO, MEDICO.NOME, MEDICO.CPF, MEDICO.TELEFONE, MEDICO.EMAIL, "
				+ "MEDICO.DATA_NASCIMENTO, MEDICO.CRM, MEDICO.ESPECIALIDADE, MEDICO.IDUSUARIO FROM MEDICO) UNION (SELECT 0 AS IDMEDICO, FUNCIONARIO.IDFUNCIONARIO, "
				+ "FUNCIONARIO.NOME, FUNCIONARIO.CPF, FUNCIONARIO.TELEFONE, FUNCIONARIO.EMAIL, FUNCIONARIO.DATA_NASCIMENTO, \"\" AS CRM, \"\" AS ESPECIALIDADE, FUNCIONARIO.IDUSUARIO "
				+ "FROM FUNCIONARIO)) AS USUARIOS ON USUARIO.IDUSUARIO = USUARIOS.IDUSUARIO ORDER BY IDUSUARIO";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		ArrayList<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();
		try {
			ResultSet resultado = prepStmt.executeQuery();

			while (resultado.next()) {
				UsuarioVO usuario = montarUsuario(resultado);
				usuarios.add(usuario);
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println("Erro ao listar todos os Usuários: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return usuarios;
	}

	public ArrayList<UsuarioVO> listarUsuarios(SeletorUsuario seletor) {
		String query = "SELECT USUARIO.IDUSUARIO, USUARIO.USUARIO, USUARIO.SENHA, USUARIO.NIVEL, USUARIOS.IDFUNCIONARIO, USUARIOS.IDMEDICO, "
				+ "USUARIOS.NOME, USUARIOS.CPF, USUARIOS.TELEFONE, USUARIOS.EMAIL, USUARIOS.DATA_NASCIMENTO, USUARIOS.CRM, USUARIOS.ESPECIALIDADE "
				+ "FROM USUARIO LEFT JOIN ((SELECT MEDICO.IDMEDICO, 0 AS IDFUNCIONARIO, MEDICO.NOME, MEDICO.CPF, MEDICO.TELEFONE, MEDICO.EMAIL, "
				+ "MEDICO.DATA_NASCIMENTO, MEDICO.CRM, MEDICO.ESPECIALIDADE, MEDICO.IDUSUARIO FROM MEDICO) UNION (SELECT 0 AS IDMEDICO, FUNCIONARIO.IDFUNCIONARIO, "
				+ "FUNCIONARIO.NOME, FUNCIONARIO.CPF, FUNCIONARIO.TELEFONE, FUNCIONARIO.EMAIL, FUNCIONARIO.DATA_NASCIMENTO, \"\" AS CRM, \"\" AS ESPECIALIDADE, FUNCIONARIO.IDUSUARIO "
				+ "FROM FUNCIONARIO)) AS USUARIOS ON USUARIO.IDUSUARIO = USUARIOS.IDUSUARIO";
		if (seletor.temFiltro()) {
			query = criarFiltros(seletor, query);
		}
		if (seletor.temPaginacao()) {
			query += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		}

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
		ArrayList<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();

		try {
			ResultSet resultado = prepStmt.executeQuery();

			while (resultado.next()) {
				UsuarioVO usuario = montarUsuario(resultado);
				usuarios.add(usuario);
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println("Erro ao listar Usuários: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}

		return usuarios;
	}

	private String criarFiltros(SeletorUsuario seletor, String query) {
		query += " WHERE ";
		boolean primeiro = true;

		if (seletor.getNome() != null && !seletor.getNome().trim().isEmpty()) {
			if (!primeiro) {
				query += " AND ";
			}
			query += "USUARIOS.NOME LIKE '%" + seletor.getNome() + "%' ";
			primeiro = false;
		}
		if (seletor.getCpf() != null && !seletor.getNome().trim().isEmpty()) {
			if (!primeiro) {
				query += " AND ";
			}
			query += "USUARIOS.CPF LIKE '%" + seletor.getCpf() + "%' ";
			primeiro = false;
		}
		if (seletor.getCrm() != null && !seletor.getCrm().trim().isEmpty()) {
			if (!primeiro) {
				query += " AND ";
			}
			query += "USUARIOS.CRM LIKE '%" + seletor.getCrm() + "%' ";
			primeiro = false;
		}
		if (seletor.getEspecialidade() != null && !seletor.getEspecialidade().trim().isEmpty()) {
			if (!primeiro) {
				query += " AND ";
			}
			query += "USUARIOS.ESPECIALIDADE LIKE '%" + seletor.getEspecialidade() + "%'";
			primeiro = false;
		}
		if (seletor.getNivel() != null && !seletor.getNivel().trim().isEmpty()) {
			if (!primeiro) {
				query += " AND ";
			}
			query += "USUARIO.NIVEL LIKE '%" + seletor.getNivel() + "%'";
			primeiro = false;
		}
		if (seletor.getDataNascimento() != null) {
			if (!primeiro) {
				query += " AND ";
			}
			query += "USUARIOS.DATA_NASCIMENTO BETWEEN " + seletor.getDataNascimento();
			primeiro = false;
		}
		return query;
	}

}

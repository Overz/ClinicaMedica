package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.banco.Banco;
import model.seletor.SeletorUsuario;
import model.vo.MedicoVO;
import model.vo.UsuarioVO;

public class MedicoDAO {

	public MedicoVO buscarMedicoPorUsuario(UsuarioVO usuarioVO) {
		String query = "SELECT * FROM MEDICO INNER JOIN USUARIO ON MEDICO.IDUSUARIO = USUARIO.IDUSUARIO WHERE MEDICO.IDUSUARIO = ?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		MedicoVO medico = null;

		try {
			prepStmt.setInt(1, usuarioVO.getIdUsuario());
			ResultSet resultado = prepStmt.executeQuery();
			if (resultado.next()) {
				medico = montarMedico(resultado);
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println("Erro ao buscar Médico por Usuário: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return medico;
	}

	public boolean existeMedicoPorCrm(MedicoVO medicoVO) {
		boolean sucesso = false;
		String query = "SELECT * FROM MEDICO WHERE CRM = ?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		try {
			prepStmt.setString(1, medicoVO.getCrm());
			ResultSet resultado = prepStmt.executeQuery();

			if (resultado.next()) {
				sucesso = true;
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println("Erro ao verificar se existe Médico por CRM: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return sucesso;
	}

	public int cadastrarMedico(MedicoVO medicoVO) {
		int novoId = -1;
		String query = "INSERT INTO MEDICO (NOME, CPF, TELEFONE, EMAIL, CRM, ESPECIALIDADE, DATA_NASCIMENTO, IDUSUARIO) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query, Statement.RETURN_GENERATED_KEYS);

		try {
			prepStmt.setString(1, medicoVO.getNome());
			prepStmt.setString(2, medicoVO.getCpf());
			prepStmt.setString(3, medicoVO.getTelefone());
			prepStmt.setString(4, medicoVO.getEmail());
			prepStmt.setString(5, medicoVO.getCrm());
			prepStmt.setString(6, medicoVO.getEspecialidade());
			prepStmt.setDate(7, Date.valueOf(medicoVO.getDtNascimento()));

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			medicoVO.setIdUsuario(usuarioDAO.cadastrarUsuario(medicoVO));

			prepStmt.setInt(8, medicoVO.getIdUsuario());

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

	public boolean atualizarMedico(MedicoVO medicoVO) {
		boolean sucesso = false;
		String query = "UPDATE MEDICO SET NOME=?, CPF=?,TELEFONE=?, EMAIL=?, CRM=?, ESPECIALIDADE=?, DATA_NASCIMENTO=? WHERE IDUSUARIO=?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		try {
			prepStmt.setString(1, medicoVO.getNome());
			prepStmt.setString(2, medicoVO.getCpf());
			prepStmt.setString(3, medicoVO.getTelefone());
			prepStmt.setString(4, medicoVO.getEmail());
			prepStmt.setString(5, medicoVO.getCrm());
			prepStmt.setString(6, medicoVO.getEspecialidade());
			prepStmt.setDate(7, Date.valueOf(medicoVO.getDtNascimento()));
			prepStmt.setInt(8, medicoVO.getIdUsuario());

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			boolean sucessoUsuario = usuarioDAO.atualizarUsuario(medicoVO);
			int resultado = prepStmt.executeUpdate();
			if (resultado > 0 || sucessoUsuario == true) {
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

	public boolean excluirMedico(MedicoVO medico) {
		boolean sucesso = false;

		String query = " DELETE FROM MEDICO WHERE IDMEDICO = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, query);

		try {
			prepStmt.setInt(1, medico.getIdMedico());

			int codigoRetorno = prepStmt.executeUpdate();

			if (codigoRetorno == 1) {
				sucesso = true;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao remover Médico. Id = " + medico.getIdMedico() + ". Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}
		return sucesso;
	}

	public ArrayList<MedicoVO> listarrMedico(SeletorUsuario seletor) {
		String query = "SELECT * FROM MEDICO INNER JOIN USUARIO ON MEDICO.IDUSUARIO = USUARIO.IDUSUARIO";
		if (seletor.temFiltro()) {
			query = criarFiltros(seletor, query);
		}
		if (seletor.temPaginacao()) {
			query += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		}

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
		ArrayList<MedicoVO> medicos = new ArrayList<MedicoVO>();

		try {
			ResultSet resultado = prepStmt.executeQuery();

			while (resultado.next()) {
				MedicoVO medico = montarMedico(resultado);
				medicos.add(medico);
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println("Erro ao listar Médicos: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}

		return medicos;
	}

	public String criarFiltros(SeletorUsuario seletor, String query) {
		query += " WHERE ";
		boolean primeiro = true;

		if (seletor.getNome() != null && !seletor.getNome().trim().isEmpty()) {
			if (!primeiro) {
				query += " AND ";
			}
			query += "MEDICO.NOME LIKE '%" + seletor.getNome() + "%' ";
			primeiro = false;
		}
		if (seletor.getCpf() != null && !seletor.getCpf().trim().isEmpty()) {
			if (!primeiro) {
				query += " AND ";
			}
			query += "MEDICO.CPF LIKE '%" + seletor.getCpf() + "%' ";
			primeiro = false;
		}
		if (seletor.getCrm() != null && !seletor.getCrm().trim().isEmpty()) {
			if (!primeiro) {
				query += " AND ";
			}
			query += "MEDICO.CRM LIKE '%" + seletor.getCrm() + "%' ";
			primeiro = false;
		}
		if (seletor.getEspecialidade() != null && !seletor.getEspecialidade().trim().isEmpty()) {
			if (!primeiro) {
				query += " AND ";
			}
			query += "MEDICO.ESPECIALIDADE LIKE '%" + seletor.getEspecialidade() + "%'";
			primeiro = false;
		}
		if (seletor.getNivel() != null && !seletor.getNivel().trim().isEmpty()) {
			if (!primeiro) {
				query += " AND ";
			}
			query += "MEDICO.NIVEL LIKE '%" + seletor.getNivel() + "%'";
			primeiro = false;
		}
		if (seletor.getDataNascimento() != null) {
			if (!primeiro) {
				query += " AND ";
			}
			query += "MEDICO.DATA_NASCIMENTO = '" + seletor.getDataNascimento() + "'";
			primeiro = false;
		}
		return query;
	}

	public MedicoVO montarMedico(ResultSet resultado) throws SQLException {
		MedicoVO medico = new MedicoVO();

		medico.setIdUsuario(resultado.getInt("IDUSUARIO"));
		medico.setNomeUsuario(resultado.getString("USUARIO"));
		medico.setSenha(resultado.getString("SENHA"));
		medico.setNivel(resultado.getString("NIVEL"));
		medico.setIdMedico(resultado.getInt("IDMEDICO"));
		medico.setNome(resultado.getString("NOME"));
		medico.setCpf(resultado.getString("CPF"));
		medico.setTelefone(resultado.getString("TELEFONE"));
		medico.setEmail(resultado.getString("EMAIL"));
		medico.setCrm(resultado.getString("CRM"));
		medico.setEspecialidade(resultado.getString("ESPECIALIDADE"));
		medico.setDtNascimento(resultado.getDate("DATA_NASCIMENTO").toLocalDate());

		return medico;
	}

	public boolean existeMedicoPorCpf(MedicoVO medicoVO) {
		boolean sucesso = false;
		String query = "SELECT * FROM MEDICO WHERE CPF = ?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		try {
			prepStmt.setString(1, medicoVO.getCpf());
			ResultSet resultado = prepStmt.executeQuery();

			if (resultado.next()) {
				sucesso = true;
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println("Erro ao verificar se existe Médico por CPF: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return sucesso;
	}

	public MedicoVO buscarMedicoPorId(int idMedico) {
		String query = "SELECT * FROM MEDICO INNER JOIN USUARIO ON MEDICO.IDUSUARIO = USUARIO.IDUSUARIO WHERE IDMEDICO = ?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		MedicoVO medico = null;

		try {
			prepStmt.setInt(1, idMedico);
			ResultSet resultado = prepStmt.executeQuery();
			if (resultado.next()) {
				medico = montarMedico(resultado);
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println("Erro ao buscar Médico por ID: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return medico;
	}

	public boolean medicoPossuiConsultas(MedicoVO medico) {
		boolean sucesso = false;
		String query = "SELECT * FROM MEDICO INNER JOIN CONSULTA ON MEDICO.IDMEDICO = CONSULTA.IDMEDICO WHERE MEDICO.IDMEDICO = ?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		try {
			prepStmt.setInt(1, medico.getIdMedico());
			ResultSet resultado = prepStmt.executeQuery();

			if (resultado.next()) {
				sucesso = true;
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println("Erro ao verificar se Médico " + medico.getNome() + " possui consultas agendadas: "
					+ e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return sucesso;
	}

	public boolean medicoPossuiProntuarios(MedicoVO medico) {
		boolean sucesso = false;
		String query = "SELECT * FROM MEDICO INNER JOIN PRONTUARIO ON MEDICO.IDMEDICO = PRONTUARIO.IDMEDICO WHERE MEDICO.IDMEDICO = ?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		try {
			prepStmt.setInt(1, medico.getIdMedico());
			ResultSet resultado = prepStmt.executeQuery();

			if (resultado.next()) {
				sucesso = true;
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println("Erro ao verificar se Médico " + medico.getNome() + " possui prontuários cadastrados: "
					+ e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return sucesso;
	}

}

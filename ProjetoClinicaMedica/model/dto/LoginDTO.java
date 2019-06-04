package model.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.banco.Banco;
import model.dao.FuncionarioDAO;
import model.dao.MedicoDAO;
import model.vo.UsuarioVO;

public class LoginDTO {

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
				usuarioVO.setNomeUsuario(resultado.getString("NOME"));
				usuarioVO.setSenha(resultado.getString("SENHA"));
				usuarioVO.setNivel(resultado.getString("NIVEL"));

				if (resultado.getString("NIVEL").equals("Médico")) {
					MedicoDAO medico = new MedicoDAO();
					usuarioVO = medico.consultarMedicoPorUsuario(usuarioVO);
				} else if (resultado.getString("NIVEL").equals("Funcionário")) {
					FuncionarioDAO funcionario = new FuncionarioDAO();
					usuarioVO = funcionario.consultarPorUsuario(usuarioVO);
				}
			}
			prepStmt.close();
		} catch (SQLException e) {
			System.out.println("Erro ao efetuar login: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}

		return usuarioVO;
	}

}

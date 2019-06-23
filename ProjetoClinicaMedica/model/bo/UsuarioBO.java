package model.bo;

import java.util.ArrayList;

import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

public class UsuarioBO {

	public UsuarioVO login(String usuario, String senha) {
		UsuarioDAO dao = new UsuarioDAO();
		return dao.login(usuario, senha);
	}

	public ArrayList<UsuarioVO> listarUsuarios() {
		UsuarioDAO dao = new UsuarioDAO();
		return dao.listarUsuarios();
	}

}

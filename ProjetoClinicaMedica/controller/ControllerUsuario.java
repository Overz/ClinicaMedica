package controller;

import java.util.ArrayList;

import model.bo.UsuarioBO;
import model.vo.UsuarioVO;

public class ControllerUsuario {

	public UsuarioVO login(String usuario, String senha) {
		UsuarioVO vo = null;
		if (usuario != null || !usuario.trim().isEmpty()) {
			if (senha != null || !senha.trim().isEmpty()) {
				UsuarioBO bo = new UsuarioBO();
				vo = bo.login(usuario, senha);
			}
		}
		return vo;
	}

	public ArrayList<UsuarioVO> listarUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

}

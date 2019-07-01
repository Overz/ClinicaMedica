package model.bo;

import java.util.ArrayList;

import model.dao.UsuarioDAO;
import model.seletor.SeletorUsuario;
import model.vo.FuncionarioVO;
import model.vo.MedicoVO;
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

	public ArrayList<UsuarioVO> listarUsuarios(SeletorUsuario seletor) {
		UsuarioDAO dao = new UsuarioDAO();
		return dao.listarUsuarios(seletor);
	}

	public String excluirUsuario(UsuarioVO usuario) {
		String mensagem = "";
		if (usuario.getNivel().equals(UsuarioVO.NIVEL_MEDICO)) {
			MedicoBO medicoBO = new MedicoBO();
			mensagem += medicoBO.excluirMedico((MedicoVO) usuario);
		} else {
			FuncionarioBO funcionarioBO = new FuncionarioBO();
			mensagem += funcionarioBO.excluirFuncionario((FuncionarioVO) usuario);
		}
		if (mensagem.equals("")) {
			UsuarioDAO dao = new UsuarioDAO();
			if (dao.excluirUsuario(usuario)) {
				mensagem += "Usuário excluído com sucesso!";
			} else {
				mensagem += "Erro ao excluir Usuário!";
			}
		}
		return mensagem;
	}

}

package model.bo;

import java.util.ArrayList;

import model.dao.ProntuarioDAO;

public class ProntuarioBO {
	private ProntuarioDAO dao;

	public ArrayList<?> consultarTodos() {
		return dao.consultarTodos();
	}

}

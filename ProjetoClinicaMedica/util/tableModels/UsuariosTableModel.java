package util.tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.vo.MedicoVO;
import model.vo.UsuarioVO;

public class UsuariosTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 8848439541375443885L;
	
	private static final int ID = 0;
	private static final int TIPO_USUARIO = 1;
	private static final int NOME = 2;
	private static final int CPF = 3;
	private static final int CRM = 4;
	private static final int ESPECIALIDADE = 5;
	private static final int NOME_DE_USUARIO = 6;

	private List<UsuarioVO> linhas;
	private String[] colunas = new String[] { "ID", "Tipo de Usuário", "Nome", "CPF", "CRM", "Especialidade",
			"Usuário(Login)" };

	public UsuariosTableModel() {
		linhas = new ArrayList<UsuarioVO>();
	}

	public UsuariosTableModel(List<UsuarioVO> usuarios) {
		linhas = new ArrayList<UsuarioVO>(usuarios);
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return linhas.size();
	}

	@Override
	public String getColumnName(int coluna) {
		return colunas[coluna];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case ID:
			return int.class;
		case TIPO_USUARIO:
			return String.class;
		case NOME:
			return String.class;
		case CPF:
			return String.class;
		case CRM:
			return String.class;
		case ESPECIALIDADE:
			return String.class;
		case NOME_DE_USUARIO:
			return String.class;
		default:
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		UsuarioVO usuario = linhas.get(rowIndex);
		if (usuario instanceof MedicoVO) {
			MedicoVO medico = (MedicoVO) usuario;
			switch (columnIndex) {
			case ID:
				return medico.getIdUsuario();
			case TIPO_USUARIO:
				return medico.getNivel();
			case NOME:
				return medico.getNome();
			case CPF:
				return medico.getCpf();
			case CRM:
				return medico.getCrm();
			case ESPECIALIDADE:
				return medico.getEspecialidade();
			case NOME_DE_USUARIO:
				return medico.getNomeUsuario();
			default:
				throw new IndexOutOfBoundsException();
			}
		} else {
			switch (columnIndex) {
			case ID:
				return usuario.getIdUsuario();
			case TIPO_USUARIO:
				return usuario.getNivel();
			case NOME:
				return usuario.getNome();
			case CPF:
				return usuario.getCpf();
			case CRM:
				return "";
			case ESPECIALIDADE:
				return "";
			case NOME_DE_USUARIO:
				return usuario.getNomeUsuario();
			default:
				throw new IndexOutOfBoundsException();
			}
		}
	}

	public UsuarioVO getUsuario(int rowIndex) {
		return linhas.get(rowIndex);
	}

	public void addUsuarios(List<UsuarioVO> usuarios) {
		int indice = getRowCount();
		linhas.addAll(usuarios);
		fireTableRowsInserted(indice, indice + usuarios.size());
	}

	public void limpar() {
		linhas.clear();
		fireTableDataChanged();
	}

	public void addMedicos(ArrayList<MedicoVO> medicos) {
		int indice = getRowCount();
		linhas.addAll(medicos);
		fireTableRowsInserted(indice, indice + medicos.size());
	}

}

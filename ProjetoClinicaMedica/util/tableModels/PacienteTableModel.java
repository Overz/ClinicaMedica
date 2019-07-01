package util.tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.vo.PacienteVO;

public class PacienteTableModel extends AbstractTableModel {

	private static final int ID = 0;
	private static final int NOME = 1;
	private static final int CPF = 2;
	private static final int TELEFONE = 3;
	private static final int EMAIL = 4;

	private List<PacienteVO> linhas;
	private String[] colunas = new String[] { "ID", "Nome", "CPF", "Telefone", "Email" };

	public PacienteTableModel() {
		linhas = new ArrayList<PacienteVO>();
	}

	public PacienteTableModel(List<PacienteVO> pacientes) {
		linhas = new ArrayList<PacienteVO>(pacientes);
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
		case NOME:
			return String.class;
		case CPF:
			return String.class;
		case TELEFONE:
			return String.class;
		case EMAIL:
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
		PacienteVO paciente = linhas.get(rowIndex);
		switch (columnIndex) {
		case ID:
			return paciente.getIdPaciente();
		case NOME:
			return paciente.getNome();
		case CPF:
			return paciente.getCpf();
		case TELEFONE:
			return paciente.getTelefone();
		case EMAIL:
			return paciente.getEmail();
		default:
			throw new IndexOutOfBoundsException();
		}
	}

	public PacienteVO getPaciente(int rowIndex) {
		return linhas.get(rowIndex);
	}

	public void addPacientes(List<PacienteVO> pacientes) {
		int indice = getRowCount();
		linhas.addAll(pacientes);
		fireTableRowsInserted(indice, indice + pacientes.size());
	}

	public void limpar() {
		linhas.clear();
		fireTableDataChanged();
	}

}

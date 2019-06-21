package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.vo.ConsultaVO;
import model.vo.PacienteVO;

public class ConsultasTableModel extends AbstractTableModel {
	private static final int HORARIO = 0;
	private static final int PACIENTE = 1;

	private List<ConsultaVO> linhas;
	private String[] colunas = new String[] { "Horário", "Paciente" };

	public ConsultasTableModel() {
		linhas = new ArrayList<ConsultaVO>();
	}

	public ConsultasTableModel(List<ConsultaVO> consultas) {
		linhas = new ArrayList<ConsultaVO>(consultas);
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
		case HORARIO:
			return int.class;
		case PACIENTE:
			return PacienteVO.class;
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
		ConsultaVO consulta = linhas.get(rowIndex);
		switch (columnIndex) {
		case HORARIO:
			return null;
		case PACIENTE:
			return consulta.getPaciente();
		default:
			throw new IndexOutOfBoundsException();
		}
	}

}

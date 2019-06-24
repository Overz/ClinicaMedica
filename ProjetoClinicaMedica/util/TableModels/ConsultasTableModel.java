package util.TableModels;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.vo.ConsultaVO;
import model.vo.PacienteVO;

public class ConsultasTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -3291514113008354236L;
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
			return LocalTime.class;
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
			return consulta.getData_consulta().toLocalTime().toString();
		case PACIENTE:
			return consulta.getPaciente().toString();
		default:
			throw new IndexOutOfBoundsException();
		}
	}

	public PacienteVO getPaciente(int rowIndex) {
		return linhas.get(rowIndex).getPaciente();
	}

	public LocalTime getHorario(int rowIndex) {
		return linhas.get(rowIndex).getData_consulta().toLocalTime();
	}

	public void setHorarios(LocalDate data) {
		for (int horario = 8; horario <= 17; horario++) {
			if (horario != 12) {
				LocalTime localTime = LocalTime.of(horario, 0);
				ConsultaVO consulta = new ConsultaVO();
				consulta.setPaciente(new PacienteVO());
				consulta.getPaciente().setNome("------------------------------");
				consulta.getPaciente().setCpf("--------------");
				consulta.getPaciente().setConvenio("");
				consulta.setData_consulta(LocalDateTime.of(data, localTime));
				linhas.add(consulta);
			}
		}
	}

	public void setConsultas(List<ConsultaVO> consultas) {
		Collections.sort(consultas, new Comparator<ConsultaVO>() {
			@Override
			public int compare(ConsultaVO consulta1, ConsultaVO consulta2) {
				return consulta1.getData_consulta().compareTo(consulta2.getData_consulta());
			}
		});
		for (ConsultaVO consulta : consultas) {
			if (consulta.getData_consulta().getHour() < 12) {
				linhas.set(consulta.getData_consulta().getHour() - 8, consulta);
			} else if (consulta.getData_consulta().getHour() > 12) {
				linhas.set(consulta.getData_consulta().getHour() - 9, consulta);
			}
		}

		fireTableRowsUpdated(0, 8);
	}

}

package view.usuarios.medico;

import java.awt.EventQueue;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.ControllerConsulta;
import model.vo.ConsultaVO;
import model.vo.MedicoVO;
import model.vo.UsuarioVO;
import net.miginfocom.swing.MigLayout;

public class TelaInternaAgendaMedico extends JInternalFrame {

	private static final long serialVersionUID = -1343244694236177024L;
	private JButton btnAtender;
	private JButton btnCancelar;
	private final DatePicker datePicker = new DatePicker();
	private JLabel lblData;
	private MedicoVO medico;
	private JTable tblConsultas;
	private ArrayList<ConsultaVO> listaConsultas;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaAgendaMedico window = new TelaInternaAgendaMedico(new MedicoVO());
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInternaAgendaMedico(MedicoVO medico) {
		super("Clínica Médica - Agenda Médica", false, true, false, false);
		this.setMedico(medico);
		setBounds(100, 100, 1077, 783);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		getContentPane()
				.setLayout(new MigLayout("", "[10][124px,grow][10][172px,grow][10][124px,grow][10][177px,grow][10]",
						"[31px,grow][31px,grow][34px,grow][][265px,grow][68px,grow][15]"));
		this.repaint();

		initialize();
	}

	private void initialize() {

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		lblData = new JLabel("Data:");
		lblData.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblData, "cell 5 1");
		datePicker.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 22));
		datePicker.getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 22));

		datePicker.setSettings(dateSettings);
		datePicker.setToolTipText("Selecione a Data");
		getContentPane().add(datePicker, "cell 7 1,grow");
		datePicker.setDate(LocalDate.now());

		tblConsultas = new JTable();
		tblConsultas.setFont(new Font("Verdana", Font.PLAIN, 18));
		tblConsultas.setRowHeight(50);
		tblConsultas.setModel(new DefaultTableModel(new String[][] { { "Horário", "Médico", "Paciente" }, },
				new String[] { "Horário", "Médico", "Paciente" }));
		getContentPane().add(tblConsultas, "cell 1 2 7 3,grow");
		DefaultTableModel modelo = (DefaultTableModel) tblConsultas.getModel();
		int linhas = 9;
		int horario = 8;
		for (int i = 0; i <= linhas; i++) {
			if (horario != 12) {
				String[] novaLinha = new String[] { LocalTime.of(horario, 0).toString(), "", "" };
				modelo.addRow(novaLinha);
				horario++;
			} else {
				horario++;
			}
		}

		preencherListaAgendamentos(datePicker.getDate());

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnCancelar, "cell 5 5,grow");
		btnCancelar.addActionListener(e -> {
			preencherListaAgendamentos(datePicker.getDate());
		});

		btnAtender = new JButton("Atender Paciente");
		btnAtender.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnAtender, "cell 7 5,grow");
		btnAtender.addActionListener(e -> {
			// PacienteVO paciente = tblConsultas.getModel().get
			// CHAMAR TELA DE CONSULTÓRIO / CADASTRO DE PRONTUÁRIOS
		});

		this.repaint();

	}

	private void preencherListaAgendamentos(LocalDate data) {
		tblConsultas.setModel(new DefaultTableModel(new String[][] { { "Horário", "Médico", "Paciente" }, },
				new String[] { "Horário", "Médico", "Paciente" }));
		DefaultTableModel modelo = (DefaultTableModel) tblConsultas.getModel();
		int linhas = 9;
		int horario = 8;
		for (int i = 0; i <= linhas; i++) {
			if (horario != 12) {
				String[] novaLinha = new String[] { LocalTime.of(horario, 0).toString(), "", "" };
				modelo.addRow(novaLinha);
				horario++;
			} else {
				horario++;
			}
		}

		ControllerConsulta controller = new ControllerConsulta();
		listaConsultas = controller.pesquisarConsultasPorDataEMedico(data, medico);

		if (listaConsultas.isEmpty()) {
			for (int i = 1; i <= 9; i++) {
				tblConsultas.setValueAt("", i, 1);
				tblConsultas.setValueAt("", i, 2);
			}
		} else {
			for (ConsultaVO consulta : listaConsultas) {
				if (consulta.getData_consulta().getHour() == 8) {
					tblConsultas.setValueAt(consulta.getMedico().toString(), 1, 1);
					tblConsultas.setValueAt(consulta.getPaciente().toString(), 1, 2);
				} else if (consulta.getData_consulta().getHour() == 9) {
					tblConsultas.setValueAt(consulta.getMedico().toString(), 2, 1);
					tblConsultas.setValueAt(consulta.getPaciente().toString(), 2, 2);
				} else if (consulta.getData_consulta().getHour() == 10) {
					tblConsultas.setValueAt(consulta.getMedico().toString(), 3, 1);
					tblConsultas.setValueAt(consulta.getPaciente().toString(), 3, 2);
				} else if (consulta.getData_consulta().getHour() == 11) {
					tblConsultas.setValueAt(consulta.getMedico().toString(), 4, 1);
					tblConsultas.setValueAt(consulta.getPaciente().toString(), 4, 2);
				} else if (consulta.getData_consulta().getHour() == 13) {
					tblConsultas.setValueAt(consulta.getMedico().toString(), 5, 1);
					tblConsultas.setValueAt(consulta.getPaciente().toString(), 5, 2);
				} else if (consulta.getData_consulta().getHour() == 14) {
					tblConsultas.setValueAt(consulta.getMedico().toString(), 6, 1);
					tblConsultas.setValueAt(consulta.getPaciente().toString(), 6, 2);
				} else if (consulta.getData_consulta().getHour() == 15) {
					tblConsultas.setValueAt(consulta.getMedico().toString(), 7, 1);
					tblConsultas.setValueAt(consulta.getPaciente().toString(), 7, 2);
				} else if (consulta.getData_consulta().getHour() == 16) {
					tblConsultas.setValueAt(consulta.getMedico().toString(), 8, 1);
					tblConsultas.setValueAt(consulta.getPaciente().toString(), 8, 2);
				} else if (consulta.getData_consulta().getHour() == 17) {
					tblConsultas.setValueAt(consulta.getMedico().toString(), 9, 1);
					tblConsultas.setValueAt(consulta.getPaciente().toString(), 9, 2);
				}
			}
		}
	}

	public void setMedico(UsuarioVO usuario) {
		this.medico = (MedicoVO) usuario;
	}
}

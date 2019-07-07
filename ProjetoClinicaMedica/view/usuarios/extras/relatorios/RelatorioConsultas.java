package view.usuarios.extras.relatorios;

import java.awt.EventQueue;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;

import controller.ControllerConsulta;
import controller.ControllerMedico;
import controller.ControllerPaciente;
import controller.ControllerRelatorio;
import model.seletor.SeletorConsulta;
import model.vo.ConsultaVO;
import model.vo.FuncionarioVO;
import model.vo.MedicoVO;
import model.vo.PacienteVO;
import net.miginfocom.swing.MigLayout;

public class RelatorioConsultas extends JInternalFrame {

	private static final long serialVersionUID = 6117203682713531593L;

	private JLabel lblPaciente;
	private JLabel lblMedico;
	private DateTimePicker datePickerAte;
	private JTable tblConsultas;
	private JButton btnGerarRelatorio;
	private JLabel lblDe;
	private JLabel lblAte;
	private DateTimePicker datePickerDe;

	private FuncionarioVO funcionario;
	private MedicoVO medico;
	private PacienteVO paciente;
	private ArrayList<PacienteVO> pacientes;
	private ArrayList<ConsultaVO> consultas;
	private ArrayList<MedicoVO> medicos;
	private JButton btnCancelar;
	private JButton btnPesquisar;
	private JComboBox cbPacientes;
	private JComboBox cbMedicos;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelatorioConsultas window = new RelatorioConsultas();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RelatorioConsultas() {
		super("Clínica Médica - Filtrar Relatorio de Consultas", true, true, false, false);
		setBounds(100, 100, 1042, 805);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new MigLayout("",
				"[10][grow][10][100px:100px:100px,grow][10,grow][grow][10][grow][10]",
				"[10][38,grow][5][38,grow][5][grow,fill][5][38,grow][5][grow][5][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][5][38,grow,fill][5]"));

		initialize();
	}

	private void initialize() {

		lblPaciente = new JLabel("Paciente: ");
		lblPaciente.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(lblPaciente, "cell 1 1,alignx trailing,growy");
		lblPaciente.setVisible(true);

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		TimePickerSettings timeSettings = new TimePickerSettings();
		timeSettings.use24HourClockFormat();
		ArrayList<LocalTime> horarios = new ArrayList<LocalTime>();
		horarios.add(LocalTime.of(8, 0));
		horarios.add(LocalTime.of(9, 0));
		horarios.add(LocalTime.of(10, 0));
		horarios.add(LocalTime.of(11, 0));
		horarios.add(LocalTime.of(13, 0));
		horarios.add(LocalTime.of(14, 0));
		horarios.add(LocalTime.of(15, 0));
		horarios.add(LocalTime.of(16, 0));
		horarios.add(LocalTime.of(17, 0));
		timeSettings.generatePotentialMenuTimes(horarios);

		DatePickerSettings dateSettings2 = new DatePickerSettings();
		dateSettings2.setAllowKeyboardEditing(false);
		TimePickerSettings timeSettings2 = new TimePickerSettings();
		timeSettings2.use24HourClockFormat();
		timeSettings2.generatePotentialMenuTimes(horarios);

		cbPacientes = new JComboBox();
		getContentPane().add(cbPacientes, "cell 3 1 5 1,grow");

		lblMedico = new JLabel("Médico: ");
		lblMedico.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(lblMedico, "cell 1 3,alignx trailing,growy");
		lblMedico.setVisible(true);

		cbMedicos = new JComboBox();
		getContentPane().add(cbMedicos, "cell 3 3 5 1,grow");

		lblDe = new JLabel("De:");
		lblDe.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(lblDe, "cell 1 5,alignx trailing,growy");

		datePickerDe = new DateTimePicker(dateSettings, timeSettings);
		datePickerDe.getDatePicker().getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 22));
		datePickerDe.getDatePicker().getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 22));
		datePickerDe.setToolTipText("Selecione a Data para Consulta");
		getContentPane().add(datePickerDe, "cell 3 5,grow");

		lblAte = new JLabel("Até:");
		lblAte.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(lblAte, "cell 1 7,alignx trailing,growy");

		datePickerAte = new DateTimePicker(dateSettings2, timeSettings2);
		datePickerAte.getDatePicker().getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 22));
		datePickerAte.getDatePicker().getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 22));
		datePickerAte.setToolTipText("Selecione a Data para Consulta");
		getContentPane().add(datePickerAte, "cell 3 7,grow");

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(btnPesquisar, "cell 7 9,grow");
		btnPesquisar.addActionListener(e -> {
			SeletorConsulta seletor = new SeletorConsulta();
			seletor.setPaciente((PacienteVO) cbPacientes.getModel().getSelectedItem());
			seletor.setMedico((MedicoVO) cbMedicos.getModel().getSelectedItem());
			if (datePickerDe.getDatePicker().getDate() != null && datePickerDe.getTimePicker().getTime() != null) {
				seletor.setDataInicio(LocalDateTime.of(datePickerDe.getDatePicker().getDate(),
						datePickerDe.getTimePicker().getTime()));
			}
			if (datePickerAte.getDatePicker().getDate() != null && datePickerAte.getTimePicker().getTime() != null) {
				seletor.setDataFim(LocalDateTime.of(datePickerAte.getDatePicker().getDate(),
						datePickerAte.getTimePicker().getTime()));
			}

			ControllerConsulta controller = new ControllerConsulta();
			consultas = controller.buscarConsultas(seletor);
			atualizarTabela();
		});

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 1 11 7 8,grow");

		tblConsultas = new JTable();
		tblConsultas.setFont(new Font("Verdana", Font.PLAIN, 18));
		tblConsultas.setRowHeight(50);
		tblConsultas.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Data", "Médico", "Especialidade", "CRM", "Paciente", "Funcionário" }));
		scrollPane.setViewportView(tblConsultas);
		tblConsultas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(btnCancelar, "cell 3 20 2 1,grow");
		btnCancelar.addActionListener(e -> {
			this.dispose();
		});

		btnGerarRelatorio = new JButton("Gerar Relatorio");
		btnGerarRelatorio.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(btnGerarRelatorio, "cell 7 20,grow");
		btnGerarRelatorio.setEnabled(false);
		btnGerarRelatorio.addActionListener(e -> {

			JFileChooser jfc = new JFileChooser();
			jfc.setDialogTitle("Salvar relatório em...");

			int resultado = jfc.showSaveDialog(null);
			if (resultado == JFileChooser.APPROVE_OPTION) {

				ControllerRelatorio controllerRelatorio = new ControllerRelatorio();

				atualizarListas();

				String caminhoEscolhido = jfc.getSelectedFile().getAbsolutePath();
				String mensagem = controllerRelatorio.gerarRelatorioConsultaComFiltro(consultas, caminhoEscolhido);

				JOptionPane.showMessageDialog(null, mensagem);
			}
		});

		atualizarListas();
		this.repaint();
	}

	private void atualizarListas() {

		ControllerPaciente p = new ControllerPaciente();
		ControllerMedico m = new ControllerMedico();

		pacientes = p.consultarTodos();
		medicos = m.consultarTodos();
		cbPacientes.setModel(new DefaultComboBoxModel(pacientes.toArray()));
		cbPacientes.setSelectedIndex(-1);
		cbMedicos.setModel(new DefaultComboBoxModel(medicos.toArray()));
		cbMedicos.setSelectedIndex(-1);
	}

	public void atualizarTabela() {
		tblConsultas.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Data", "Médico", "Especialidade", "CRM", "Paciente", "Funcionário" }));

		for (ConsultaVO consulta : consultas) {
			String[] novaLinha = { consulta.getData_consulta().toString(), consulta.getMedico().getNome(),
					consulta.getMedico().getEspecialidade(), consulta.getMedico().getCrm(),
					consulta.getPaciente().toString(), consulta.getFuncionario().toString() };

			DefaultTableModel modelo = (DefaultTableModel) tblConsultas.getModel();
			modelo.addRow(novaLinha);
		}
		btnGerarRelatorio.setEnabled(true);
	}
}

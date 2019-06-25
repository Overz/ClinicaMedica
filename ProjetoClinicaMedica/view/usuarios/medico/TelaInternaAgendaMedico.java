package view.usuarios.medico;

import java.awt.EventQueue;
import java.awt.Font;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.ControllerConsulta;
import model.vo.ConsultaVO;
import model.vo.MedicoVO;
import model.vo.PacienteVO;
import model.vo.UsuarioVO;
import net.miginfocom.swing.MigLayout;
import util.TableModels.ConsultasTableModel;

public class TelaInternaAgendaMedico extends JInternalFrame {

	private static final long serialVersionUID = -1343244694236177024L;
	private JButton btnAtender;
	private JButton btnCancelar;
	private final DatePicker datePicker = new DatePicker();
	private JLabel lblData;
	private MedicoVO medico;
	private JTable tblConsultas;
	private ArrayList<ConsultaVO> listaConsultas;
	private JButton btnListarAgendamentos;

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
		super("Clínica Médica - Agenda Médica", false, false, false, false);
		this.medico = medico;

		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

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
		getContentPane().add(lblData, "cell 1 1");
		datePicker.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 22));
		datePicker.getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 22));

		datePicker.setSettings(dateSettings);
		datePicker.setToolTipText("Selecione a Data");
		getContentPane().add(datePicker, "cell 3 1,grow");
		datePicker.setDate(LocalDate.now());
		datePicker.addDateChangeListener(e -> {
			preencherListaAgendamentos(datePicker.getDate());
		});

		btnListarAgendamentos = new JButton("Listar Agendamentos");
		btnListarAgendamentos.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnListarAgendamentos, "cell 7 1,grow");
		btnListarAgendamentos.addActionListener(e -> {
			if (datePicker.getDate() != null) {
				preencherListaAgendamentos(datePicker.getDate());
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 1 2 7 3,grow");

		tblConsultas = new JTable();
		tblConsultas.setFont(new Font("Verdana", Font.PLAIN, 18));
		tblConsultas.setRowHeight(50);
		ConsultasTableModel consultasTableModel = new ConsultasTableModel();
		consultasTableModel.setHorarios(datePicker.getDate());
		tblConsultas.setModel(consultasTableModel);
		scrollPane.setViewportView(tblConsultas);
		tblConsultas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		preencherListaAgendamentos(datePicker.getDate());

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnCancelar, "cell 5 5,grow");
		btnCancelar.addActionListener(e -> {
			this.dispose();
		});

		btnAtender = new JButton("Atender Paciente");
		btnAtender.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnAtender, "cell 7 5,grow");
		btnAtender.addActionListener(e -> {
			ConsultasTableModel modelo = (ConsultasTableModel) tblConsultas.getModel();
			PacienteVO paciente = modelo.getPaciente(tblConsultas.getSelectedRow());
			if (!paciente.getNome().trim().isEmpty()) {
				TelaInternaProntuarioMedico telaAtendimento = new TelaInternaProntuarioMedico(medico, paciente);
				getDesktopPane().add(telaAtendimento);
				telaAtendimento.setVisible(true);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Horário selecionado não tem cosulta agendada!");
			}

		});

		this.repaint();

	}

	private void preencherListaAgendamentos(LocalDate data) {
		ConsultasTableModel modelo = (ConsultasTableModel) tblConsultas.getModel();

		ControllerConsulta controller = new ControllerConsulta();
		listaConsultas = controller.pesquisarConsultasPorDataEMedico(data, medico);

		if (!listaConsultas.isEmpty()) {
			modelo.limpar(data);
			modelo.setConsultas(listaConsultas);
		}
	}

	public void setMedico(UsuarioVO usuario) {
		this.medico = (MedicoVO) usuario;
	}
}

package view.usuarios.funcionarios;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.ControllerPaciente;
import model.seletor.SeletorPaciente;
import model.vo.PacienteVO;
import net.miginfocom.swing.MigLayout;
import util.TableModels.PacienteTableModel;
import view.usuarios.medico.TelaInternaProntuarioMedico;

public class TelaInternaBuscarPaciente extends JInternalFrame {

	private static final long serialVersionUID = -3439228926572831568L;
	private final DatePicker datePicker = new DatePicker();
	private JTextField txtCpf;
	private JTextField txtNome;
	private JButton btnPesquisar;
	private JButton btnSelecionarPaciente;
	private JButton btnCancelar;
	private JTable tblPacientes;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaBuscarPaciente window = new TelaInternaBuscarPaciente();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInternaBuscarPaciente() {
		super("Clínica Médica - Buscar Paciente", false, true, false, false);
		setBounds(100, 100, 1154, 816);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		getContentPane()
				.setLayout(new MigLayout("", "[10][138px,grow][10][158.00px,grow][10][76.00px,grow][10][grow][10]",
						"[10][50][20][50][20][605px,grow][][50][20]"));

		initialize();
	}

	private void initialize() {

		JLabel lblNome = new JLabel("Digite o Nome:");
		lblNome.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblNome, "cell 1 1,grow");

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblCpf, "cell 5 1,alignx center,growy");

		JLabel lblDtNascimento = new JLabel("Data de Nascimento:");
		lblDtNascimento.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblDtNascimento, "cell 1 3,grow");

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		datePicker.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 20));
		datePicker.getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 20));
		datePicker.setSettings(dateSettings);
		getContentPane().add(datePicker, "cell 3 3,grow");

		txtCpf = new JTextField();
		txtCpf.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(txtCpf, "cell 7 1,grow");

		txtNome = new JTextField();
		txtNome.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(txtNome, "cell 3 1,grow");

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnPesquisar, "cell 5 3 3 1,grow");
		btnPesquisar.addActionListener(e -> {
			consultarPacientes();
		});

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 1 5 7 1,grow");

		tblPacientes = new JTable();
		tblPacientes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PacienteTableModel pacientesTableModel = new PacienteTableModel();
		tblPacientes.setModel(pacientesTableModel);
		scrollPane.setViewportView(tblPacientes);
		tblPacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnCancelar, "cell 3 7,grow");
		btnCancelar.addActionListener(e -> {
			this.dispose();
		});

		btnSelecionarPaciente = new JButton("Selecionar Paciente");
		btnSelecionarPaciente.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnSelecionarPaciente, "cell 5 7 3 1,grow");
		btnSelecionarPaciente.addActionListener(e -> {
			PacienteTableModel modelo = (PacienteTableModel) tblPacientes.getModel();
			PacienteVO paciente = modelo.getPaciente(tblPacientes.getSelectedRow());
			for (JInternalFrame telaInterna : getDesktopPane().getAllFrames()) {
				if (telaInterna instanceof TelaInternaConsultasEHorarios) {
					TelaInternaConsultasEHorarios telaAgendamento = (TelaInternaConsultasEHorarios) telaInterna;
					telaAgendamento.setPaciente(paciente);
					this.dispose();
				} else if (telaInterna instanceof TelaInternaProntuarioMedico) {
					TelaInternaProntuarioMedico telaProntuario = (TelaInternaProntuarioMedico) telaInterna;
					telaProntuario.setPaciente(paciente);
					telaProntuario.preencherCampos();
					this.dispose();
				}
			}
		});
	}

	public void consultarPacientes() {
		ControllerPaciente controller = new ControllerPaciente();
		SeletorPaciente seletor = new SeletorPaciente();

		seletor.setNome(txtNome.getText());
		seletor.setCpf(txtCpf.getText());
		seletor.setDate(datePicker.getDate());

		PacienteTableModel modelo = (PacienteTableModel) tblPacientes.getModel();
		modelo.limpar();
		modelo.addPacientes(controller.listarPacientes(seletor));

	}
}

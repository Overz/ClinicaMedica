package view.usuarios.funcionarios;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.ControllerConsulta;
import controller.ControllerMedico;
import model.seletor.SeletorUsuario;
import model.vo.MedicoVO;
import net.miginfocom.swing.MigLayout;
import util.tableModels.ConsultasTableModel;
import util.tableModels.UsuariosTableModel;

public class TelaInternaBuscarMedico extends JInternalFrame {

	private static final long serialVersionUID = -8419577180883062723L;
	// Atributos
	private JTextField txtCpf;
	private JTextField txtCrm;
	private JButton btnLimpar;
	private JButton btnPesquisar;
	private JButton btnCancelar;
	private JTable tblMedicos;
	private final DatePicker datePicker = new DatePicker();
	private JTextField txtNome;
	private JTextField txtEspecialidade;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaBuscarMedico frame = new TelaInternaBuscarMedico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInternaBuscarMedico() {
		super("Clinica Médica - Selecionar Médico", true, true, false, false);
		setBounds(100, 100, 1013, 748);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane()
				.setLayout(new MigLayout("", "[10][grow][grow][grow][grow][10]", "[40][40][40][40][][grow][40][10]"));

		initialize();
	}

	private void initialize() {

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblNome, "cell 1 0,grow");

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblCpf, "cell 1 1,grow");

		JLabel lblCrm = new JLabel("CRM:");
		lblCrm.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblCrm, "cell 3 1,alignx left");

		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblDataDeNascimento, "cell 1 2");

		JLabel lblEspecialidade = new JLabel("Especialidade:");
		lblEspecialidade.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblEspecialidade, "cell 3 2,grow");

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		datePicker.setSettings(dateSettings);
		datePicker.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 20));
		datePicker.getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(datePicker, "cell 2 2, grow");

		txtNome = new JTextField();
		txtNome.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtNome.setColumns(10);
		getContentPane().add(txtNome, "cell 2 0,grow");

		txtCpf = new JTextField();
		txtCpf.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(txtCpf, "cell 2 1,grow");

		txtCrm = new JTextField();
		txtCrm.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(txtCrm, "cell 4 1,grow");

		txtEspecialidade = new JTextField();
		txtEspecialidade.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(txtEspecialidade, "cell 4 2,grow");
		txtEspecialidade.setColumns(10);

		btnLimpar = new JButton("Limpar Campos");
		btnLimpar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnLimpar, "cell 2 4,grow");
		btnLimpar.addActionListener(e -> {
			txtNome.setText("");
			txtCpf.setText("");
			txtCrm.setText("");
			txtEspecialidade.setText("");
			datePicker.setDate(null);
			UsuariosTableModel modelo = (UsuariosTableModel) tblMedicos.getModel();
			modelo.limpar();
		});

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnPesquisar, "cell 3 4,grow");
		btnPesquisar.addActionListener(e -> {
			consultarMedicos();
		});

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 1 5 4 1,grow");

		tblMedicos = new JTable();
		tblMedicos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		UsuariosTableModel usuariosTableModel = new UsuariosTableModel();
		tblMedicos.setModel(usuariosTableModel);
		scrollPane.setViewportView(tblMedicos);
		tblMedicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnCancelar, "cell 2 6,grow");
		btnCancelar.addActionListener(e -> {
			this.dispose();
		});

		JButton btnSelecionarMedico = new JButton("Selecionar Médico");
		btnSelecionarMedico.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnSelecionarMedico, "cell 3 6,grow");
		btnSelecionarMedico.addActionListener(e -> {
			UsuariosTableModel modelo = (UsuariosTableModel) tblMedicos.getModel();
			MedicoVO medico = (MedicoVO) modelo.getUsuario(tblMedicos.getSelectedRow());
			for (JInternalFrame telaInterna : getDesktopPane().getAllFrames()) {
				if (telaInterna instanceof TelaInternaConsultasEHorarios) {
					TelaInternaConsultasEHorarios telaAgendamento = (TelaInternaConsultasEHorarios) telaInterna;
					telaAgendamento.setMedico(medico);
					ConsultasTableModel modeloConsultas = (ConsultasTableModel) telaAgendamento.getJTable().getModel();
					ControllerConsulta controller = new ControllerConsulta();
					modeloConsultas.setConsultas(controller
							.pesquisarConsultasPorDataEMedico(telaAgendamento.getDatePicker().getDate(), medico));
					this.dispose();
				}
			}
		});

		this.repaint();

	}

	public void consultarMedicos() {
		ControllerMedico controller = new ControllerMedico();
		SeletorUsuario seletor = new SeletorUsuario();

		seletor.setNome(txtNome.getText());
		seletor.setCpf(txtCpf.getText());

		seletor.setCrm(txtCrm.getText());
		seletor.setEspecialidade(txtEspecialidade.getText());
		seletor.setDataNascimento(datePicker.getDate());

		UsuariosTableModel modelo = (UsuariosTableModel) tblMedicos.getModel();
		modelo.limpar();
		modelo.addMedicos(controller.listarMedicos(seletor));

	}
}
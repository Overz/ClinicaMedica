package view.usuarios.funcionarios;

import java.awt.EventQueue;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalTime;
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
import model.vo.FuncionarioVO;
import model.vo.MedicoVO;
import model.vo.PacienteVO;
import net.miginfocom.swing.MigLayout;
import util.TableModels.ConsultasTableModel;

public class TelaInternaConsultasEHorarios extends JInternalFrame {

	private static final long serialVersionUID = 9221455748477846858L;
	private JLabel lblPaciente;
	private JLabel lblMedico;
	private JButton btnSelecionarMedico;
	private JButton btnCadastrarConsulta;
	private JButton btnLimparCampos;
	private JTable tblConsultas;
	private final DatePicker datePicker = new DatePicker();
	private JButton btnSelecionarPaciente;

	private MedicoVO medico;
	private PacienteVO paciente;
	private FuncionarioVO funcionario;
	private ArrayList<ConsultaVO> consultas = new ArrayList<ConsultaVO>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TelaInternaConsultasEHorarios window = new TelaInternaConsultasEHorarios();
					window.setVisible(true);
					window.setSelected(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInternaConsultasEHorarios() {
		super("Clínica Médica - Horarios Marcados", // Title
				false, // Resizeble
				false, // Closable
				false, // Maximizable
				false); // Minimizar
		// Opções que possibilitam remoção de bordas, para tela ficar sempre "estatica"
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		// this.setBounds(0, 0, 821, 609);
		this.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		this.getContentPane().setLayout(new MigLayout("",
				"[10][grow][10][100px:100px:100px,grow][grow][10][grow][100px:100px:100px,grow][grow][10]",
				"[10][38,grow][5][grow][5][grow,fill][5][38,grow][5][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][5][38,grow,fill][5]"));

		initialize();
	}

	private void initialize() {

		lblPaciente = new JLabel("Paciente: ");
		lblPaciente.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(lblPaciente, "cell 1 1,alignx trailing,growy");
		lblPaciente.setVisible(true);

		btnSelecionarPaciente = new JButton("Selecionar Paciente");
		btnSelecionarPaciente.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(btnSelecionarPaciente, "cell 6 1,grow");
		btnSelecionarPaciente.addActionListener(e -> {
			TelaInternaBuscarPaciente telaInternaBuscarPaciente = new TelaInternaBuscarPaciente();
			getDesktopPane().add(telaInternaBuscarPaciente);
			telaInternaBuscarPaciente.setVisible(true);
		});

		lblMedico = new JLabel("Médico: ");
		lblMedico.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(lblMedico, "cell 1 5,alignx trailing,growy");
		lblMedico.setVisible(true);

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		datePicker.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 22));
		datePicker.getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 22));

		datePicker.setSettings(dateSettings);
		datePicker.setToolTipText("Selecione a Data para Consulta");
		getContentPane().add(datePicker, "cell 6 7,grow");
		datePicker.setDate(LocalDate.now());

		btnSelecionarMedico = new JButton("Selecionar Médico");
		btnSelecionarMedico.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(btnSelecionarMedico, "cell 6 5,grow");
		btnSelecionarMedico.addActionListener(e -> {
			TelaInternaBuscarMedico telaInternaBuscarMedico = new TelaInternaBuscarMedico();
			getDesktopPane().add(telaInternaBuscarMedico);
			telaInternaBuscarMedico.setVisible(true);
		});

		btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(btnLimparCampos, "cell 3 18 2 1,grow");
		btnLimparCampos.addActionListener(e -> {
			TelaInternaBuscarPaciente telaInternaBuscarPaciente = new TelaInternaBuscarPaciente();
			getDesktopIcon().add(telaInternaBuscarPaciente);
			telaInternaBuscarPaciente.setVisible(true);
		});

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 1 9 8 8,grow");

		tblConsultas = new JTable();
		tblConsultas.setFont(new Font("Verdana", Font.PLAIN, 18));
		tblConsultas.setRowHeight(50);
		ConsultasTableModel consultasTableModel = new ConsultasTableModel();
		consultasTableModel.setHorarios(datePicker.getDate());
		tblConsultas.setModel(consultasTableModel);
		scrollPane.setViewportView(tblConsultas);
		tblConsultas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnCadastrarConsulta = new JButton("Cadastrar Consulta");
		btnCadastrarConsulta.setFont(new Font("Verdana", Font.PLAIN, 22));
		btnCadastrarConsulta.setToolTipText(
				"Selecione uma Linha, e Registre os Dados em Ordem na Tabela, para Realizar o Cadastro das Consultas.");
		getContentPane().add(btnCadastrarConsulta, "cell 6 18,grow");
		btnCadastrarConsulta.addActionListener(e -> {
			ConsultasTableModel modelo = (ConsultasTableModel) tblConsultas.getModel();
			LocalDate data = datePicker.getDate();
			LocalTime horario = modelo.getHorario(tblConsultas.getSelectedRow());
			ControllerConsulta controller = new ControllerConsulta();
			String mensagem = controller.agendarConsulta(medico, paciente, data, horario, funcionario);
			JOptionPane.showMessageDialog(this, mensagem);
		});
		this.repaint();
	}

	public MedicoVO getMedico() {
		return this.medico;
	}

	public void setMedico(MedicoVO medico) {
		this.medico = medico;
		this.lblMedico.setText("Médico: " + medico.toString());
		if (datePicker.getDate() != null) {
			ControllerConsulta controller = new ControllerConsulta();
			controller.pesquisarConsultasPorDataEMedico(datePicker.getDate(), medico);
		}
	}

	public PacienteVO getPaciente() {
		return this.paciente;
	}

	public void setPaciente(PacienteVO paciente) {
		this.paciente = paciente;
		this.lblPaciente.setText("Paciente: " + paciente.toString());
	}

<<<<<<< HEAD
	public void setFuncionario(FuncionarioVO funcionario) {
		this.funcionario = funcionario;
	}

	public void atualizarCampos() {

	}

=======
	/*public void atualizarCampos() {
		if (this.medico != null || )
	}*/
>>>>>>> branch 'master' of https://github.com/Overz/ClinicaMedica.git
}

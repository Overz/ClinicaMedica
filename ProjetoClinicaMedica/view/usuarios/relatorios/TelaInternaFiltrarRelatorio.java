package view.usuarios.relatorios;

import java.awt.EventQueue;
import java.awt.Font;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.ControllerConsulta;
import controller.ControllerPaciente;
import controller.ControllerProntuario;
import controller.ControllerRelatorio;
import model.vo.ConsultaVO;
import model.vo.FuncionarioVO;
import model.vo.MedicoVO;
import model.vo.PacienteVO;
import model.vo.ProntuarioVO;
import net.miginfocom.swing.MigLayout;
import util.tableModels.ConsultasTableModel;
import view.usuarios.funcionarios.TelaInternaBuscarMedico;
import view.usuarios.funcionarios.TelaInternaBuscarPaciente;

public class TelaInternaFiltrarRelatorio extends JInternalFrame {

	private static final long serialVersionUID = 6117203682713531593L;

	private JLabel lblPaciente;
	private JLabel lblMedico;
	private DatePicker datePickerFinal = new DatePicker();
	private JButton btnSelecionarPaciente;
	private JButton btnSelecionarMedico;
	private JTable tblConsultas;
	private JButton btnCadastrarConsulta;
	private JLabel lblDe;
	private JLabel lblAt;
	private DatePicker datePickerAtual;

	private FuncionarioVO funcionario;
	private MedicoVO medico;
	private PacienteVO paciente;
	private ArrayList<PacienteVO> pacienteVO;
	private ArrayList<ConsultaVO> consultasVO;
	private ArrayList<ProntuarioVO> prontuarioVO;
	private JButton btnCancelar;
	private JButton btnPesquisar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TelaInternaFiltrarRelatorio window = new TelaInternaFiltrarRelatorio();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInternaFiltrarRelatorio() {
		super("Clínica Médica - Filtrar Relatorio de Consultas", true, true, false, false);
		setBounds(100, 100, 1042, 805);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new MigLayout("",
				"[10][grow][10][100px:100px:100px,grow][10,grow][grow][10][grow][10]",
				"[10][38,grow][5][grow][5][grow,fill][5][38,grow][5][grow][5][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][5][38,grow,fill][5]"));

		initialize();
	}

	private void initialize() {

		lblPaciente = new JLabel("Paciente: ");
		lblPaciente.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(lblPaciente, "cell 1 1,alignx trailing,growy");
		lblPaciente.setVisible(true);

		btnSelecionarPaciente = new JButton("Selecionar Paciente");
		btnSelecionarPaciente.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(btnSelecionarPaciente, "cell 7 1,grow");
		btnSelecionarPaciente.addActionListener(e -> {
			TelaInternaBuscarPaciente telaInternaBuscarPaciente = new TelaInternaBuscarPaciente();
			getDesktopPane().add(telaInternaBuscarPaciente);
			telaInternaBuscarPaciente.setVisible(true);
		});

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		lblMedico = new JLabel("Médico: ");
		lblMedico.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(lblMedico, "cell 1 3,alignx trailing,growy");
		lblMedico.setVisible(true);

		btnSelecionarMedico = new JButton("Selecionar Médico");
		btnSelecionarMedico.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(btnSelecionarMedico, "cell 7 3,grow");
		btnSelecionarMedico.addActionListener(e -> {
			TelaInternaBuscarMedico telaInternaBuscarMedico = new TelaInternaBuscarMedico();
			getDesktopPane().add(telaInternaBuscarMedico);
			telaInternaBuscarMedico.setVisible(true);
		});

		lblDe = new JLabel("De:");
		lblDe.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(lblDe, "cell 1 5,alignx trailing,growy");

		datePickerAtual = new DatePicker();
		datePickerAtual.getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 22));
		datePickerAtual.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 22));
		datePickerAtual.setToolTipText("Selecione a Data para Consulta");
		getContentPane().add(datePickerAtual, "cell 7 5,grow");

		lblAt = new JLabel("Até:");
		lblAt.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(lblAt, "cell 1 7,alignx trailing,growy");
		datePickerFinal.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 22));
		datePickerFinal.getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 22));

		datePickerFinal.setSettings(dateSettings);
		datePickerFinal.setToolTipText("Selecione a Data para Consulta");
		getContentPane().add(datePickerFinal, "cell 7 7,grow");
		datePickerFinal.setDate(LocalDate.now());
		datePickerFinal.addDateChangeListener(e -> {
			atualizarTabela();
		});

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(btnPesquisar, "cell 7 9,grow");

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 1 11 7 8,grow");

		tblConsultas = new JTable();
		tblConsultas.setFont(new Font("Verdana", Font.PLAIN, 18));
		tblConsultas.setRowHeight(50);
		ConsultasTableModel consultasTableModel = new ConsultasTableModel();
		consultasTableModel.setHorarios(datePickerFinal.getDate());
		tblConsultas.setModel(consultasTableModel);
		scrollPane.setViewportView(tblConsultas);
		tblConsultas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(btnCancelar, "cell 3 20 2 1,grow");
		btnCancelar.addActionListener(e -> {
			this.dispose();
		});

		btnCadastrarConsulta = new JButton("Gerar Relatorio");
		btnCadastrarConsulta.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(btnCadastrarConsulta, "cell 7 20,grow");
		btnCadastrarConsulta.addActionListener(e -> {

			JFileChooser jfc = new JFileChooser();
			jfc.setDialogTitle("Salvar relatório em...");

			int resultado = jfc.showSaveDialog(null);
			if (resultado == JFileChooser.APPROVE_OPTION) {

				ControllerRelatorio controllerRelatorio = new ControllerRelatorio();

				atualizarListas();

				String caminhoEscolhido = jfc.getSelectedFile().getAbsolutePath();
				String mensagem = controllerRelatorio.gerarRelatorioConsultaComFiltro(consultasVO, caminhoEscolhido);

				JOptionPane.showMessageDialog(null, mensagem);
			}
		});

		this.repaint();
	}

	public MedicoVO getMedico() {
		return this.medico;
	}

	public void setMedico(MedicoVO medico) {
		this.medico = medico;
		this.lblMedico.setText("Médico: " + medico.toString());
		atualizarTabela();
	}

	public PacienteVO getPaciente() {
		return this.paciente;
	}

	public void setPaciente(PacienteVO paciente) {
		this.paciente = paciente;
		this.lblPaciente.setText("Paciente: " + paciente.toString());
	}

	public void setFuncionario(FuncionarioVO funcionario) {
		this.funcionario = funcionario;
	}

	public JTable getJTable() {
		return this.tblConsultas;
	}

	public DatePicker getDatePicker() {
		return this.datePickerFinal;
	}

	private void atualizarListas() {

		ControllerPaciente p = new ControllerPaciente();
		ControllerConsulta c = new ControllerConsulta();
		ControllerProntuario pro = new ControllerProntuario();

		this.pacienteVO = p.consultarTodos();
		this.consultasVO = c.consultarTodos();
		this.prontuarioVO = pro.consultarTodos();

	}

	public void atualizarTabela() {
		if (medico != null && datePickerFinal.getDate() != null) {
			ControllerConsulta controller = new ControllerConsulta();
			ConsultasTableModel modelo = (ConsultasTableModel) tblConsultas.getModel();
			modelo.limpar(datePickerFinal.getDate());
			modelo.setConsultas(controller.pesquisarConsultasPorDataEMedico(datePickerFinal.getDate(), medico));
		}
	}
}

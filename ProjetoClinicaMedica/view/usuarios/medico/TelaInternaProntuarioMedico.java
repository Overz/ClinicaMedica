package view.usuarios.medico;

import java.awt.EventQueue;
import java.awt.Font;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;

import controller.ControllerProntuario;
import model.vo.MedicoVO;
import model.vo.PacienteVO;
import model.vo.ProntuarioVO;
import net.miginfocom.swing.MigLayout;
import view.usuarios.func_E_medico.TelaInternaBuscarPaciente;

public class TelaInternaProntuarioMedico extends JInternalFrame {

	private static final long serialVersionUID = 6287035520900270789L;
	private final DatePicker datePicker = new DatePicker();
	private JButton btnSalvar;
	private JTextArea txtObservacoes;
	private MedicoVO medico;
	private PacienteVO paciente;
	private JLabel lblNome;
	private JLabel lblSexo;
	private JLabel lblTipoSanguineo;
	private JLabel lblDataDeNascimento;
	private JLabel lblConvenio;
	private JLabel lblCpf;
	private JLabel lblTelefone;
	private JLabel lblEmail;
	private JTable tblProntuarios;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TelaInternaProntuarioMedico window = new TelaInternaProntuarioMedico(new MedicoVO(),
							new PacienteVO());
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInternaProntuarioMedico() {
		super("Clínica Médica - Prontuario", true, true, false, false);
		setBounds(100, 100, 965, 788);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("",
				"[10][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][10]",
				"[grow][grow][grow][13,grow][][grow][][13][grow][13][][13][grow][][][][][][][grow][154px,grow][33px,grow][10]"));

		initialize();
	}

	public TelaInternaProntuarioMedico(MedicoVO medico, PacienteVO paciente) {
		super("Clínica Médica - Cadastrar/Atualizar Prontuario", false, true, false, false);
		this.medico = medico;
		this.paciente = paciente;
		setBounds(100, 100, 965, 788);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("",
				"[10][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][10]",
				"[grow][grow][grow][13,grow][][grow][][13][grow][13][][13][grow][][][][][][][grow][154px,grow][33px,grow][10]"));

		initialize();
	}

	private void initialize() {

		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblNome, "cell 1 0 4 1,alignx left,growy");

		JLabel lblHistorico = new JLabel("Histórico:");
		lblHistorico.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblHistorico, "cell 5 0");

		lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblSexo, "cell 1 2");

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 5 2 6 15,grow");

		tblProntuarios = new JTable();
		scrollPane.setViewportView(tblProntuarios);
		tblProntuarios
				.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Data", "Médico", "Observações" }));

		lblTipoSanguineo = new JLabel("Tipo Sanguíneo:");
		lblTipoSanguineo.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblTipoSanguineo, "cell 1 4");

		lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblDataDeNascimento, "cell 1 6 2 1,alignx left,growy");

		lblConvenio = new JLabel("Convênio");
		lblConvenio.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblConvenio, "cell 1 8 2 1");

		lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblCpf, "cell 1 10 4 1");

		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblTelefone, "cell 1 12 4 1");

		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblEmail, "cell 1 14 2 1");

		JLabel lblObservacoes = new JLabel("Observações:");
		lblObservacoes.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblObservacoes, "cell 1 18,alignx left,growy");

		txtObservacoes = new JTextArea();
		txtObservacoes.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtObservacoes.setLineWrap(true);
		txtObservacoes.setWrapStyleWord(true);
		txtObservacoes.setEnabled(false);

		getContentPane().add(txtObservacoes, "cell 1 19 10 2,grow");

		JButton btnSelecionarPaciente = new JButton("Selecionar Paciente");
		btnSelecionarPaciente.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnSelecionarPaciente, "cell 2 21");
		btnSelecionarPaciente.addActionListener(e -> {
			TelaInternaBuscarPaciente telaInternaBuscarPaciente = new TelaInternaBuscarPaciente();
			getDesktopPane().add(telaInternaBuscarPaciente);
			telaInternaBuscarPaciente.setVisible(true);
		});

		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnSalvar, "cell 5 21,grow");
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(e -> {
			String observacoes = txtObservacoes.getText();
			ControllerProntuario controller = new ControllerProntuario();
			String mensagem = controller.salvarProntuario(medico, paciente, observacoes);
			JOptionPane.showMessageDialog(this, mensagem);
			txtObservacoes.setText("");
			preencherCampos();
		});

		if (this.paciente != null) {
			preencherCampos();
		}

	}

	public void preencherCampos() {
		this.lblNome.setText(this.paciente.getNome());
		this.lblSexo.setText(this.paciente.getSexo());
		this.lblTipoSanguineo.setText(this.paciente.getTipoSanguineo());
		this.lblDataDeNascimento.setText(this.paciente.getDtNascimento().toString());
		this.lblConvenio.setText(this.paciente.getConvenio());
		this.lblCpf.setText(this.paciente.getCpf());
		this.lblTelefone.setText(this.paciente.getTelefone());
		this.lblEmail.setText(this.paciente.getEmail());

		ControllerProntuario controller = new ControllerProntuario();
		ArrayList<ProntuarioVO> prontuarios = controller.listarProntuariosPorPaciente(this.paciente);

		tblProntuarios.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Data", "Médico", "Observações" }));

		for (ProntuarioVO prontuario : prontuarios) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataFormatada = prontuario.getDtProntuario().format(formatter);
			String[] novaLinha = { dataFormatada, prontuario.getMedico().toString(), prontuario.getObservacoes() };
			DefaultTableModel modelo = (DefaultTableModel) tblProntuarios.getModel();
			modelo.addRow(novaLinha);
		}

		this.txtObservacoes.setEnabled(true);
		this.btnSalvar.setEnabled(true);
	}

	public void setMedico(MedicoVO medico) {
		this.medico = medico;
	}

	public void setPaciente(PacienteVO paciente) {
		this.paciente = paciente;
	}
}

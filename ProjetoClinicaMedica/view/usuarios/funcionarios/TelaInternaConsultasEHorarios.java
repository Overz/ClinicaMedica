package view.usuarios.funcionarios;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.ControllerFuncionario;
import model.vo.PacienteVO;
import net.miginfocom.swing.MigLayout;

public class TelaInternaConsultasEHorarios extends JInternalFrame {

	private static final long serialVersionUID = 9221455748477846858L;
	private JLabel lblPaciente;
	private JLabel lblMedico;
	private JButton btnSelecionarMedico;
	private JButton btnCadastrarConsulta;
	private JButton btnLimparCampos;
	private MaskFormatter mascaraNome;
	private MaskFormatter mascaraCpfCrm;
	private JTable table;
	private final DatePicker datePicker = new DatePicker();

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

		try {
			mascaraNome = new MaskFormatter(
					"LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
			mascaraCpfCrm = new MaskFormatter("####################");
		} catch (ParseException e1) {
			System.out.println("Erro ao criar Mascara" + e1.getMessage());
		}

		lblPaciente = new JLabel("Paciente: ");
		lblPaciente.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(lblPaciente, "cell 1 1,alignx trailing,growy");
		lblPaciente.setVisible(true);

		lblMedico = new JLabel("Médico: ");
		lblMedico.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(lblMedico, "cell 1 5,alignx trailing,growy");
		lblMedico.setVisible(false);

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		datePicker.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 22));
		datePicker.getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 22));

		datePicker.setSettings(dateSettings);
		datePicker.setToolTipText("Selecione a Data para Consulta");
		getContentPane().add(datePicker, "cell 6 1,grow");

		btnSelecionarMedico = new JButton("Selecionar Médico");
		btnSelecionarMedico.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(btnSelecionarMedico, "cell 6 5,grow");
		btnSelecionarMedico.addActionListener(e -> {

			try {
				ControllerFuncionario controller = new ControllerFuncionario();
				String nome = ftfNome.getText();
				String cpfCrm = ftfCampoCpfCrm.getText();
				LocalDate date = datePicker.getDate();
				String mensagem = controller.ValidarCamposConsultasEHorarios(nome, cpfCrm, date);
				if (mensagem != null) {
					JOptionPane.showMessageDialog(null, mensagem);
				}
			} catch (NullPointerException e2) {
				System.out.println(
						"Tela: Consultas e Horarios. Erro ao Validar os Campos para consulta.\n" + e2.getMessage());
			}

			// ArrayList<PacienteVO> vo = controller.consultarData(data);
			// atualizarTabela(vo);
		});

		btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(btnLimparCampos, "cell 3 18 2 1,grow");
		btnLimparCampos.addActionListener(e -> {

			cbOpcaoPesquisa.setSelectedIndex(0);
			datePicker.setDate(null);
			ftfNome.setText("");
			ftfCampoCpfCrm.setText("");

		});

		Object[][] data = new Object[][] { { "Nome", "Data", "Hora", "Telefone", "Médico", "Especialidade" }, };
		Object[] columnNames = new String[] { "Nome", "Data", "Hora", "Telefone", "Médico", "Especialidade" };

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setModel(new DefaultTableModel(data, columnNames));
		getContentPane().add(table, "cell 1 9 8 8,grow");
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table.setSelectionBackground(Color.YELLOW);
			}
		});

		btnCadastrarConsulta = new JButton("Cadastrar Consulta");
		btnCadastrarConsulta.setFont(new Font("Verdana", Font.PLAIN, 22));
		btnCadastrarConsulta.setToolTipText(
				"Selecione uma Linha, e Registre os Dados em Ordem na Tabela, para Realizar o Cadastro das Consultas.");
		getContentPane().add(btnCadastrarConsulta, "cell 6 18,grow");
		btnCadastrarConsulta.addActionListener(e -> {

			// TODO Cadastrar consulta no banco.

		});
		this.repaint();
	}

	private void atualizarTabela(ArrayList<PacienteVO> paciente) {

		/*
		 * Object novaLinha[] = new Object[5]; for (PacienteVO pacienteVO : paciente) {
		 * novaLinha[0] = pacienteVO.getNome(); novaLinha[1] =
		 * pacienteVO.getCliente().getHoraConsulta(); novaLinha[2] =
		 * pacienteVO.getCliente().getCpf(); novaLinha[3] =
		 * pacienteVO.getCliente().getTelefone(); novaLinha[4] = pacienteVO.getNome();
		 * novaLinha[5] = pacienteVO.getEspecialidade();
		 * 
		 * model.addRow(novaLinha);
		 */

	}

	/*
	 * private void limparTabela() { table.setModel(new DefaultTableModel( new
	 * Object[][] {{"Nome", "Data", "Hora", "Telefone", "Médico", "Especialidade"}},
	 * new String[] {"Nome", "Data", "Hora", "Telefone", "Médico",
	 * "Especialidade"})); }
	 */
}

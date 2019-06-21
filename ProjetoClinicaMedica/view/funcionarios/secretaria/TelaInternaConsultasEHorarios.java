package view.funcionarios.secretaria;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.ControllerFuncionario;
import model.vo.MedicoVO;
import net.miginfocom.swing.MigLayout;

public class TelaInternaConsultasEHorarios extends JInternalFrame {

	private static final long serialVersionUID = 9221455748477846858L;
	private JLabel lblPacientemdico;
	private JLabel lblCpfcrm;
	private JComboBox cbOpcaoPesquisa;
	private JButton btnPesquisarPorCampos;
	private JButton btnCadastrarConsulta;
	private JButton btnLimparCampos;
	private JFormattedTextField ftfCampoCpfCrm;
	private JFormattedTextField ftfNome;
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
				"[10][grow][100px:100px:100px,grow][grow][grow][100px:100px:100px,grow][grow][10]",
				"[10][38,grow][grow][grow,fill][38,grow][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][10]"));

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

		JLabel lblNome = new JLabel("Pesquisar por:");
		lblNome.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(lblNome, "cell 1 1,alignx right,growy");

		lblPacientemdico = new JLabel("Paciente/Médico:");
		lblPacientemdico.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(lblPacientemdico, "cell 1 2,alignx trailing,growy");
		lblPacientemdico.setVisible(true);

		lblCpfcrm = new JLabel("CPF/CRM:");
		lblCpfcrm.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(lblCpfcrm, "cell 1 3,alignx trailing,growy");
		lblCpfcrm.setVisible(false);

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		datePicker.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 22));
		datePicker.getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 22));

		datePicker.setSettings(dateSettings);
		datePicker.setToolTipText("Selecione a Data para Consulta");
		getContentPane().add(datePicker, "cell 4 1,grow");

		cbOpcaoPesquisa = new JComboBox();
		cbOpcaoPesquisa.setFont(new Font("Verdana", Font.PLAIN, 22));
		cbOpcaoPesquisa.setModel(new DefaultComboBoxModel(new String[] { "Nome (Paciente/Médico)", "CPF ou CRM" }));
		getContentPane().add(cbOpcaoPesquisa, "cell 2 1 2 1,grow");
		cbOpcaoPesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verificarCamposCbBox();
			}
		});

		ftfNome = new JFormattedTextField(mascaraNome);
		ftfNome.setFont(new Font("Verdana", Font.PLAIN, 22));
		ftfNome.setVisible(true);
		ftfNome.setEnabled(true);
		ftfNome.setEditable(true);
		ftfNome.setToolTipText("Digite o Nome do Paciente ou Médico, para realizar a Consulta Especifica.");
		getContentPane().add(ftfNome, "cell 2 2 3 1,grow");

		ftfCampoCpfCrm = new JFormattedTextField(mascaraCpfCrm);
		ftfCampoCpfCrm.setFont(new Font("Verdana", Font.PLAIN, 22));
		ftfCampoCpfCrm.setVisible(false);
		ftfCampoCpfCrm.setEditable(false);
		ftfCampoCpfCrm.setEnabled(false);
		ftfCampoCpfCrm.setToolTipText("Digite o CPF do Paciente ou Médico.");
		getContentPane().add(ftfCampoCpfCrm, "cell 2 3 3 1,grow");

		btnPesquisarPorCampos = new JButton("Pesquisar Medico/Consulta");
		btnPesquisarPorCampos.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(btnPesquisarPorCampos, "cell 2 4 3 1,grow");
		btnPesquisarPorCampos.addActionListener(e -> {

			ControllerFuncionario controller = new ControllerFuncionario();
			try {
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

		});

		btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(btnLimparCampos, "cell 2 13 2 1,grow");
		btnLimparCampos.addActionListener(e -> {

			cbOpcaoPesquisa.setSelectedIndex(0);
			datePicker.setDate(null);
			ftfNome.setText("");
			ftfCampoCpfCrm.setText("");

		});

		btnCadastrarConsulta = new JButton("Cadastrar Consulta");
		btnCadastrarConsulta.setFont(new Font("Verdana", Font.PLAIN, 22));
		btnCadastrarConsulta.setToolTipText(
				"Selecione uma Linha, e Registre os Dados em Ordem na Tabela, para Realizar o Cadastro das Consultas.");
		getContentPane().add(btnCadastrarConsulta, "cell 4 13,grow");
		btnCadastrarConsulta.addActionListener(e -> {

			// TODO Cadastrar consulta no banco.

		});

		// TENTAR ADC SCROLLBAR
		Object[][] data = new Object[][] { { "Nome", "Data", "Hora", "Telefone", "Médico", "Especialidade" }, };
		Object[] columnNames = new String[] { "Nome", "Data", "Hora", "Telefone", "Médico", "Especialidade" };
		JScrollPane scroll = new JScrollPane(table);
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setModel(new DefaultTableModel(data, columnNames));
		getContentPane().add(table, "cell 1 5 6 8,grow");
		// -------------------------- ADD SCROLL BAR -----------------------
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		table.add(scroll);
		// -------------------------- ADD SCROLL BAR -----------------------
		this.repaint();
	}

	public void verificarCamposCbBox() {

		if (cbOpcaoPesquisa.getSelectedIndex() == 0) {
			ftfNome.setText("");
			ftfNome.setVisible(true);
			ftfNome.setEnabled(true);
			ftfNome.setEditable(true);
			lblPacientemdico.setVisible(true);

			ftfCampoCpfCrm.setText("");
			ftfCampoCpfCrm.setEnabled(false);
			ftfCampoCpfCrm.setEditable(false);
			ftfCampoCpfCrm.setVisible(false);
			lblCpfcrm.setVisible(false);

		} else {

			ftfCampoCpfCrm.setText("");
			ftfCampoCpfCrm.setEnabled(true);
			ftfCampoCpfCrm.setEditable(true);
			ftfCampoCpfCrm.setVisible(true);
			lblCpfcrm.setVisible(true);

			ftfNome.setText("");
			ftfNome.setVisible(false);
			ftfNome.setEnabled(false);
			ftfNome.setEditable(false);
			lblPacientemdico.setVisible(false);
		}

		this.repaint();
	}

	protected void atualizarTabela(ArrayList<MedicoVO> medicos) {
		// DefaultTableModel model = (DefaultTableModel) table.getModel();

		Object novaLinha[] = new Object[5];
		for (MedicoVO medicoVO : medicos) {
			/*
			 * novaLinha[0] = medicoVO.getCliente().getNome(); novaLinha[1] =
			 * medicoVO.getCliente().getHoraConsulta(); novaLinha[2] =
			 * medicoVO.getCliente().getCpf(); novaLinha[3] =
			 * medicoVO.getCliente().getTelefone(); novaLinha[4] = medicoVO.getNome();
			 * novaLinha[5] = medicoVO.getEspecialidade();
			 * 
			 * model.addRow(novaLinha);
			 */
		}
	}

	/*
	 * public void limparTabela() { table.setModel(new DefaultTableModel( new
	 * Object[][] {{"Placa", "Modelo", "Ano", "Valor"}}, new String[] {"Placa",
	 * "Modelo", "Ano", "Valor"})); }
	 */
}

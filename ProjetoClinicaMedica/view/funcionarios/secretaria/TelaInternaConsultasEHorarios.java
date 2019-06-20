package view.funcionarios.secretaria;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.ControllerFuncionario;
import controller.ControllerMedico;
import model.vo.MedicoVO;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;

public class TelaInternaConsultasEHorarios extends JInternalFrame {

	private static final long serialVersionUID = 9221455748477846858L;
	private Date data;
	private JComboBox cbOpcaoPesquisa;
	private JComponent dateChooser;
	private JButton btnPesquisarPorCampos;
	private JFormattedTextField ftfCampoCpfCrm;
	private JButton btnCadastrarConsulta;
	private JTable table;
	private final DatePicker datePicker = new DatePicker();
	private JTextField txtNome;
	private JLabel lblPacientemdico;
	private JLabel lblCpfcrm;
	private JButton btnLimparCampos;

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
		//Opções que possibilitam remoção de bordas, para tela ficar sempre "estatica"
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		//this.setBounds(0, 0, 821, 609);
		this.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		this.getContentPane().setLayout(new MigLayout("", "[][grow][100px:100px:100px,grow][grow][grow][100px:100px:100px,grow][grow][]", "[38,grow][grow][grow,fill][38,grow][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill]"));

		initialize();
	}

	private void initialize() {

		JLabel lblNome = new JLabel("Pesquisar por:");
		getContentPane().add(lblNome, "cell 1 0,alignx right,growy");

		lblPacientemdico = new JLabel("Paciente/Médico:");
		getContentPane().add(lblPacientemdico, "cell 1 1,alignx trailing,growy");
		lblPacientemdico.setVisible(true);

		lblCpfcrm = new JLabel("CPF/CRM:");
		getContentPane().add(lblCpfcrm, "cell 1 2,alignx trailing,growy");
		lblCpfcrm.setVisible(false);

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		datePicker.setSettings(dateSettings);
		datePicker.setToolTipText("Selecione a Data para Consulta");
		getContentPane().add(datePicker, "cell 4 0,grow");

		cbOpcaoPesquisa = new JComboBox();
		cbOpcaoPesquisa.setModel(new DefaultComboBoxModel(new String[] { "Nome (Paciente/Médico)", "CPF ou CRM" }));
		getContentPane().add(cbOpcaoPesquisa, "cell 2 0 2 1,grow");
		cbOpcaoPesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verificarCampos();
			}
		});

		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 2 1 3 1,grow");
		txtNome.setColumns(10);

		ftfCampoCpfCrm = new JFormattedTextField();
		ftfCampoCpfCrm.setVisible(true);

		ftfCampoCpfCrm.setEditable(false);
		ftfCampoCpfCrm.setEnabled(false);
		ftfCampoCpfCrm.setToolTipText("Digite o CPF do Paciente ou Médico");
		getContentPane().add(ftfCampoCpfCrm, "cell 2 2 3 1,grow");

		btnPesquisarPorCampos = new JButton("Pesquisar Medico/Consulta");
		getContentPane().add(btnPesquisarPorCampos, "cell 2 3 3 1,grow");
		btnPesquisarPorCampos.addActionListener(e -> {

			ControllerFuncionario controller = new ControllerFuncionario();
			String nome = txtNome.getText();
			String cpfCrm = ftfCampoCpfCrm.getText();
			LocalDate date = datePicker.getDate();
			String mensagem = controller.ValidarCamposConsultasEHorarios(nome, cpfCrm, date);
				if (mensagem != null) {
					JOptionPane.showMessageDialog(null, mensagem);
				}
			
			/*LocalDate date = ((DatePicker) datePicker).getDate();
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");*/

			// ArrayList<MedicoVO> vo = controller.consultarData(data);
			// atualizarTabelaCarros(vo);
		});

		btnLimparCampos = new JButton("Limpar Campos");
		getContentPane().add(btnLimparCampos, "cell 2 12 2 1,grow");
		btnLimparCampos.addActionListener(e -> {
			
			cbOpcaoPesquisa.setSelectedIndex(0);
			datePicker.setDate(null);
			txtNome.setText("");
			ftfCampoCpfCrm.setText("");
			
		});

		btnCadastrarConsulta = new JButton("Cadastrar Consulta");
		btnCadastrarConsulta.setToolTipText("Selecione uma Linha, e Registre os Dados em Ordem na Tabela, para Realizar o Cadastro das Consultas.");
		getContentPane().add(btnCadastrarConsulta, "cell 4 12,grow");
		btnCadastrarConsulta.addActionListener(e -> {
			
			//TODO Cadastrar consulta no banco.
			
		});
		
		// TENTAR ADC SCROLLBAR
		Object[][] data = new Object[][] { { "Nome", "Data", "Hora", "Telefone", "Médico", "Especialidade" }, };
		Object[] columnNames = new String[] { "Nome", "Data", "Hora", "Telefone", "Médico", "Especialidade" };
		JScrollPane scroll = new JScrollPane(table);
		table = new JTable();
		table.setModel(new DefaultTableModel(data, columnNames));
		getContentPane().add(table, "cell 1 4 6 8,grow");
		// -------------------------- ADD SCROLL BAR -----------------------
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		table.add(scroll);
		// -------------------------- ADD SCROLL BAR -----------------------
		this.repaint();
	}

	public void verificarCampos() {

		if (cbOpcaoPesquisa.getSelectedIndex() == 0) {
			txtNome.setVisible(true);
			txtNome.setEnabled(true);
			txtNome.setEditable(true);
			lblPacientemdico.setVisible(true);


			ftfCampoCpfCrm.setEnabled(false);
			ftfCampoCpfCrm.setEditable(false);
			ftfCampoCpfCrm.setVisible(false);
			lblCpfcrm.setVisible(false);

		} else  {

			ftfCampoCpfCrm.setEnabled(true);
			ftfCampoCpfCrm.setEditable(true);
			ftfCampoCpfCrm.setVisible(true);
			lblCpfcrm.setVisible(true);

			txtNome.setVisible(false);
			txtNome.setEnabled(false);
			txtNome.setEditable(false);
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

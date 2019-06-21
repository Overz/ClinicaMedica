package view.usuarios.funcionarios;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.ControllerFuncionario;
import controller.ControllerValidacao;
import model.vo.MedicoVO;
import model.vo.PacienteVO;
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
		//Opções que possibilitam remoção de bordas, para tela ficar sempre "estatica"
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		//this.setBounds(0, 0, 821, 609);
		this.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		this.getContentPane().setLayout(new MigLayout("", "[10][grow][10][100px:100px:100px,grow][grow][10][grow][100px:100px:100px,grow][grow][10]", "[10][38,grow][5][grow][5][grow,fill][5][38,grow][5][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][5][38,grow,fill][5]"));

		initialize();
	}

	private void initialize() {

		try {
			mascaraNome = new MaskFormatter("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
			mascaraCpfCrm = new MaskFormatter("####################");
		} catch (ParseException e1) {
			System.out.println("Erro ao criar Mascara" + e1.getMessage());
		}

		JLabel lblNome = new JLabel("Pesquisar por:");
		lblNome.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(lblNome, "cell 1 1,alignx right,growy");

		lblPacientemdico = new JLabel("Paciente/Médico:");
		lblPacientemdico.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(lblPacientemdico, "cell 1 3,alignx trailing,growy");
		lblPacientemdico.setVisible(true);

		lblCpfcrm = new JLabel("CPF/CRM:");
		lblCpfcrm.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(lblCpfcrm, "cell 1 5,alignx trailing,growy");
		lblCpfcrm.setVisible(false);

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		datePicker.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 22));
		datePicker.getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 22));

		datePicker.setSettings(dateSettings);
		datePicker.setToolTipText("Selecione a Data para Consulta");
		getContentPane().add(datePicker, "cell 6 1,grow");

		cbOpcaoPesquisa = new JComboBox();
		cbOpcaoPesquisa.setFont(new Font("Verdana", Font.PLAIN, 22));
		cbOpcaoPesquisa.setModel(new DefaultComboBoxModel(new String[] { "Nome (Paciente/Médico)", "CPF ou CRM" }));
		getContentPane().add(cbOpcaoPesquisa, "cell 3 1 2 1,grow");
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
		getContentPane().add(ftfNome, "cell 3 3 4 1,grow");

		ftfCampoCpfCrm = new JFormattedTextField(mascaraCpfCrm);
		ftfCampoCpfCrm.setFont(new Font("Verdana", Font.PLAIN, 22));
		ftfCampoCpfCrm.setVisible(false);
		ftfCampoCpfCrm.setEditable(false);
		ftfCampoCpfCrm.setEnabled(false);
		ftfCampoCpfCrm.setToolTipText("Digite o CPF do Paciente ou Médico.");
		getContentPane().add(ftfCampoCpfCrm, "cell 3 5 4 1,grow");

		btnPesquisarPorCampos = new JButton("Pesquisar Medico/Consulta");
		btnPesquisarPorCampos.setFont(new Font("Verdana", Font.PLAIN, 22));
		getContentPane().add(btnPesquisarPorCampos, "cell 3 7 4 1,grow");
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
				System.out.println("Tela: Consultas e Horarios. Erro ao Validar os Campos para consulta.\n" + e2.getMessage());
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

				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();
				table.setSelectionBackground(Color.YELLOW);


				PacienteVO object = (PacienteVO) table.getModel().getValueAt(row, column);
				String value = (String) table.getValueAt(row, column);
				valoresDaTabela(value, object);
			}
		});

		btnCadastrarConsulta = new JButton("Cadastrar Consulta");
		btnCadastrarConsulta.setFont(new Font("Verdana", Font.PLAIN, 22));
		btnCadastrarConsulta.setToolTipText("Selecione uma Linha, e Registre os Dados em Ordem na Tabela, para Realizar o Cadastro das Consultas.");
		getContentPane().add(btnCadastrarConsulta, "cell 6 18,grow");
		btnCadastrarConsulta.addActionListener(e -> {

			//TODO Cadastrar consulta no banco.



		});
		this.repaint();
	}

	private String valoresDaTabela(String value, PacienteVO object) {

		ControllerValidacao controller = new ControllerValidacao();
		String mensagem = controller.valoresDaTabela(value, object);

		return mensagem;
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

		} else  {

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

	private void atualizarTabela(ArrayList<PacienteVO> paciente) {

		/*Object novaLinha[] = new Object[5];
		for (PacienteVO pacienteVO : paciente) {
			novaLinha[0] = pacienteVO.getNome();
			novaLinha[1] = pacienteVO.getCliente().getHoraConsulta();
			novaLinha[2] = pacienteVO.getCliente().getCpf();
			novaLinha[3] = pacienteVO.getCliente().getTelefone();
			novaLinha[4] = pacienteVO.getNome();
			novaLinha[5] = pacienteVO.getEspecialidade();

			model.addRow(novaLinha);*/

	}

	/*private void limparTabela() { 
		table.setModel(new DefaultTableModel(
				new Object[][] {{"Nome", "Data", "Hora", "Telefone", "Médico", "Especialidade"}},
				new String[] {"Nome", "Data", "Hora", "Telefone", "Médico", "Especialidade"}));
	}*/
}

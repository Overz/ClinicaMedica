package view.adm.funcionario;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

public class TelaInternaAtualizarFuncionario extends JInternalFrame {

	private static final long serialVersionUID = -5960289086930711613L;
	private JButton btnPesquisar;
	private JTextField txtNome;
	private JFormattedTextField ftfCpf;
	private JButton btnLimpar;
	private JButton btnPesquisarTodos;
	private JButton btnSalvar;
	private JButton btnApagar;
	private JTable tableClientes;
	private JCheckBox chckbxFuncionario;
	private JCheckBox chckbxMedico;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaAtualizarFuncionario window = new TelaInternaAtualizarFuncionario();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInternaAtualizarFuncionario() {
		super("Clínica Médica - Atualziar Dados dos Funcionarios (ADM)", false, true, false, false);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1048, 773);
		getContentPane().setLayout(new MigLayout("", "[10][60.00px,center][grow][grow][10][grow][10][grow][10]", "[5][30,fill][30.00px,fill][5][30,fill][40.00,fill][5][73.00,grow,fill][10][41.00,fill][10]"));

		initialize();
	}

	private void initialize() {
		
		JLabel lblFuncionario = new JLabel("Funcionario:");
		getContentPane().add(lblFuncionario, "cell 1 1,alignx center,growy");
		
		JLabel lblNome = new JLabel("Nome:");
		getContentPane().add(lblNome, "cell 1 2,alignx center,growy");
		
		JLabel lblCpf = new JLabel("CPF:");
		getContentPane().add(lblCpf, "cell 1 4,alignx center,growy");
		
		chckbxFuncionario = new JCheckBox("Funcionarios");
		getContentPane().add(chckbxFuncionario, "cell 2 1,alignx center,growy");
		chckbxMedico = new JCheckBox("Médicos");
		getContentPane().add(chckbxMedico, "cell 3 1,alignx center,growy");
		buttonGroup.add(chckbxFuncionario);
		buttonGroup.add(chckbxMedico);

		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 2 2 4 1,grow");
		txtNome.setColumns(10);

		ftfCpf = new JFormattedTextField();
		getContentPane().add(ftfCpf, "cell 2 4 4 1,grow");
		
		btnPesquisar = new JButton("Pesquisar");
		getContentPane().add(btnPesquisar, "cell 7 2 1 3,grow");
		btnPesquisar.addActionListener(e -> {

		});

		btnLimpar = new JButton("Limpar Campos");
		getContentPane().add(btnLimpar, "flowx,cell 2 5 2 1,grow");
		btnLimpar.addActionListener(e -> {
			txtNome.setText("");
			ftfCpf.setText("");
		});

		btnPesquisarTodos = new JButton("Pesquisar Todos");
		getContentPane().add(btnPesquisarTodos, "cell 5 5 3 1,grow");
		btnPesquisarTodos.addActionListener(e -> {

		});


		btnSalvar = new JButton("Salvar Dados");
		getContentPane().add(btnSalvar, "cell 3 9,grow");
		btnSalvar.addActionListener(e -> {

		});

		btnApagar = new JButton("APAGAR");
		btnApagar.setEnabled(false);
		getContentPane().add(btnApagar, "cell 5 9,grow");
		btnApagar.addActionListener(e -> {
			// TODO Pegar a linha/coluna selecionada e alterar diretamente na tabela
			// int[] id = tableClientes.getSelectedRows();
		});
		
		Object[][] data = new Object[][] { { "#", "Nome", "CPF", "DT Nascimento", "Telefone", "Celular", "E-Mail" }, };
		Object[] columnNames = new String[] { "#", "Nome", "CPF", "DT Nascimento", "Telefone", "Celular", "E-Mail" };


		tableClientes = new JTable();
		getContentPane().add(tableClientes, "cell 1 7 7 1,grow");
		tableClientes.setModel(new DefaultTableModel(data, columnNames));
		
		JCheckBox chkbApagarCliente = new JCheckBox("Apagar Clientes");
		getContentPane().add(chkbApagarCliente, "cell 7 9,alignx leading,growy");
		chkbApagarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkbApagarCliente.isSelected()) {
					JOptionPane.showMessageDialog(null,	"Aten��o, Cuidado ao apagar um Funcionario!\nIsso pode gerar Problemas!");
					btnApagar.setEnabled(true);
				}

			}
		});

	}
}

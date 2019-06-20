package view.adm.paciente;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class TelaInternaCadastrarAtualizarPaciente extends JInternalFrame {

	private static final long serialVersionUID = -4822124346584250830L;
	private JTextField txtNome;
	private JTable tableClientes;
	private JButton btnApagar;
	private JFormattedTextField formattedTextField;
	private JButton btnPesquisar;
	private JButton btnSalvar;
	private JButton btnLimpar;
	private JButton btnPesquisarTodos;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaCadastrarAtualizarPaciente window = new TelaInternaCadastrarAtualizarPaciente();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInternaCadastrarAtualizarPaciente() {
		super("Clínica Médica - Cadastrar/Atualizar Paciente (ADM)", false, true, false, false);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1048, 773);
		getContentPane().setLayout(new MigLayout("", "[60.00px,center][grow][grow][grow][grow]",
				"[29.00px,fill][29.00,fill][40.00,fill][73.00,grow,fill][41.00,fill]"));

		initialize();
	}

	private void initialize() {

		JLabel lblNome = new JLabel("Nome:");
		getContentPane().add(lblNome, "cell 0 0,alignx center,growy");

		btnPesquisar = new JButton("Pesquisar");
		getContentPane().add(btnPesquisar, "cell 4 0 1 2,grow");
		btnPesquisar.addActionListener(e -> {

		});

		JLabel lblCpf = new JLabel("CPF:");
		getContentPane().add(lblCpf, "cell 0 1,alignx center,growy");

		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 1 0 3 1,grow");
		txtNome.setColumns(10);

		formattedTextField = new JFormattedTextField();
		getContentPane().add(formattedTextField, "cell 1 1 3 1,grow");

		btnLimpar = new JButton("Limpar Campos");
		getContentPane().add(btnLimpar, "flowx,cell 1 2 2 1,grow");
		btnLimpar.addActionListener(e -> {
			txtNome.setText("");
			formattedTextField.setText("");
		});

		Object[][] data = new Object[][] { { "#", "Nome", "CPF", "RG", "DT Nascimento", "Sexo", "TP Sanguíneo",
				"Cidade", "Bairro", "Rua", "Numero", "Complemento", "Telefone", "Celular", "E-Mail" }, };
		Object[] columnNames = new String[] { "#", "Nome", "CPF", "RG", "DT Nascimento", "Sexo", "TP Sanguíneo",
				"Cidade", "Bairro", "Rua", "Numero", "Complemento", "Telefone", "Celular", "E-Mail" };

		btnPesquisarTodos = new JButton("Pesquisar Todos");
		getContentPane().add(btnPesquisarTodos, "cell 3 2 2 1,grow");
		btnPesquisarTodos.addActionListener(e -> {

		});

		tableClientes = new JTable();
		getContentPane().add(tableClientes, "cell 0 3 5 1,grow");
		tableClientes.setModel(new DefaultTableModel(data, columnNames));

		btnSalvar = new JButton("Salvar Dados");
		getContentPane().add(btnSalvar, "cell 2 4,grow");
		btnSalvar.addActionListener(e -> {

		});

		btnApagar = new JButton("APAGAR");
		btnApagar.setEnabled(false);
		getContentPane().add(btnApagar, "cell 3 4,grow");
		btnApagar.addActionListener(e -> {
			// TODO Pegar a linha/coluna selecionada e alterar diretamente na tabela
			// int[] id = tableClientes.getSelectedRows();
		});

		JCheckBox chkbApagarCliente = new JCheckBox("Apagar Clientes");
		getContentPane().add(chkbApagarCliente, "cell 4 4,alignx leading,growy");
		chkbApagarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkbApagarCliente.isSelected()) {
					JOptionPane.showMessageDialog(null,
							"Aten��o, Cuidado ao apagar um Cliente!\nIsso pode gerar Problemas!");
					btnApagar.setEnabled(true);
				}

			}
		});

	}
}

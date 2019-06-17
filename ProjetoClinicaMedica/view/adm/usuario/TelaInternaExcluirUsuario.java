package view.adm.usuario;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class TelaInternaExcluirUsuario extends JInternalFrame {
	private JTable tableClientes;
	private JButton btnApagar;
	private JTextField textField;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaExcluirUsuario window = new TelaInternaExcluirUsuario();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the application.
	 */
	public TelaInternaExcluirUsuario() {
		super(null,
			false,
			false,
			false,
			false);
		setBounds(100, 100, 840, 638);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[10][1px,grow][grow][grow][grow][grow][10]", "[][][][][grow][]"));
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		JLabel lblNome = new JLabel("Nome:");
		getContentPane().add(lblNome, "cell 1 0,grow");
		
		Object[][] data = new Object[][] {{"#", "Nome", "CPF", "Email", "Telefone"},};
		Object[] columnNames = new String[] {"#", "Nome", "CPF", "Email", "Telefone"};
		
		textField = new JTextField();
		getContentPane().add(textField, "cell 2 0 2 1,growx");
		textField.setColumns(10);
		
		JButton btnPesquisar01 = new JButton("Pesquisar");
		getContentPane().add(btnPesquisar01, "cell 4 0,grow");
		
		JLabel lblCpf = new JLabel("CPF:");
		getContentPane().add(lblCpf, "cell 1 1,grow");
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		getContentPane().add(formattedTextField, "cell 2 1 2 1,grow");
		
		JButton btnLimpar = new JButton("Limpar Campos");
		getContentPane().add(btnLimpar, "cell 4 1,grow");
		
		JButton btnPesquisarTodos = new JButton("Pesquisar Todos");
		getContentPane().add(btnPesquisarTodos, "cell 3 2,grow");
		
		tableClientes = new JTable();
		getContentPane().add(tableClientes, "cell 1 4 5 1,grow");
		tableClientes.setModel(new DefaultTableModel(data, columnNames));
		
		JButton btnSalvar = new JButton("Salvar Dados");
		getContentPane().add(btnSalvar, "cell 2 5,grow");
		
		
		
		btnApagar = new JButton("APAGAR");
		btnApagar.addActionListener(e -> {
			//TODO Pegar a linha/coluna selecionada e alterar diretamente na tabela
			int[] id = tableClientes.getSelectedRows();
		});
		btnApagar.setEnabled(false);
		getContentPane().add(btnApagar, "cell 3 5,grow");
		
		JCheckBox chkbApagarCliente = new JCheckBox("Apagar Clientes");
		chkbApagarCliente.addActionListener(new ActionListener() {
			private AbstractButton btnApagar;

			public void actionPerformed(ActionEvent e) {
				if (chkbApagarCliente.isSelected()) {
					JOptionPane.showMessageDialog(null, "Aten��o, Cuidado ao apagar um Cliente!\nIsso pode gerar Problemas!");
					btnApagar.setEnabled(true);
				}
				
			}
		});
		getContentPane().add(chkbApagarCliente, "cell 4 5,grow");
		
		this.repaint();
		
	}

}

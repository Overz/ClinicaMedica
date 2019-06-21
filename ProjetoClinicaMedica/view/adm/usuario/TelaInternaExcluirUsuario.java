package view.adm.usuario;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

public class TelaInternaExcluirUsuario extends JInternalFrame {

	private static final long serialVersionUID = -4416105711278801014L;
	private JTable tableClientes;
	private JFormattedTextField ftfNome;
	private JFormattedTextField ftfCpf;
	private JButton btnApagar;
	private JButton btnLimpar;
	private JButton btnSalvar;
	private JButton btnPesquisar;
	private JButton btnPesquisarTodos;
	
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

	public TelaInternaExcluirUsuario() {
		super("Clinica Médica - Exclusão de Usuario",
			false,
			false,
			false,
			false);
		setBounds(100, 100, 840, 638);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[10][1px,grow][10][grow][10][grow][10][grow][grow][10]", "[40][10][40][40][10][grow][40][10]"));
		
		initialize();
	}

	private void initialize() {

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblNome, "cell 1 0,grow");
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblCpf, "cell 1 2,grow");
		
		ftfNome = new JFormattedTextField();
		ftfNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(ftfNome, "cell 3 0 3 1,grow");
		
		ftfCpf = new JFormattedTextField();
		ftfCpf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(ftfCpf, "cell 3 2 3 1,grow");
		
		btnLimpar = new JButton("Limpar Campos");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnLimpar, "cell 7 2,grow");
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnPesquisar, "cell 7 0,grow");
		
		btnPesquisarTodos = new JButton("Pesquisar Todos");
		btnPesquisarTodos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnPesquisarTodos, "cell 3 3 5 1,grow");
		
		btnSalvar = new JButton("Salvar Dados");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnSalvar, "cell 3 6,grow");
		
		btnApagar = new JButton("APAGAR");
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnApagar.setEnabled(false);
		getContentPane().add(btnApagar, "cell 5 6,grow");
		btnApagar.addActionListener(e -> {
			//TODO Pegar a linha/coluna selecionada e alterar diretamente na tabela
			int[] id = tableClientes.getSelectedRows();
		});
		
		JCheckBox chkbApagarCliente = new JCheckBox("Apagar Cliente");
		chkbApagarCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(chkbApagarCliente, "cell 7 6,grow");
		chkbApagarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkbApagarCliente.isSelected()) {
					JOptionPane.showMessageDialog(null, "Aten��o, Cuidado ao apagar um Cliente!\nIsso pode gerar Problemas!");
					btnApagar.setEnabled(true);
				}
			}
		});
		
		Object[][] data = new Object[][] {{"#", "Nome", "CPF", "Usuário(Login)", "Senha", "Tipo de Usuário" },};
		Object[] columnNames = new String[] {"#", "Nome", "CPF", "Usuário(Login)", "Senha", "Tipo de Usuário" };
		
		tableClientes = new JTable();
		tableClientes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(tableClientes, "cell 1 5 8 1,grow");
		tableClientes.setModel(new DefaultTableModel(data, columnNames));
		
		this.repaint();
	}
}

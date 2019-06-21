package view.adm.usuario;

import java.awt.Component;
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

public class TelaInternaAtualizarUsuario extends JInternalFrame {

	private static final long serialVersionUID = -3919117480409507850L;
	private JTable table;
	private JButton btnApagar;
	private Component ftfEmail;
	private JButton btnPesquisar;
	private JFormattedTextField ftfCpf;
	private JButton btnSalvar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaAtualizarUsuario window = new TelaInternaAtualizarUsuario();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInternaAtualizarUsuario() {
		super("Clínica Médica - Atualizar Dados do Usúario (ADM)", false, true, false, false);
		setBounds(100, 100, 764, 634);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[10][grow][10][grow][grow][10][grow][10][grow][10]", "[10][40][10][40][10][grow][grow][grow][grow][40][10]"));
		

		initialize();
	}

	private void initialize() {
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblEmail, "cell 1 1,grow");
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblCpf, "cell 1 3,grow");
		
		ftfEmail = new JFormattedTextField();
		ftfEmail.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(ftfEmail, "cell 3 1 4 1,grow");
		
		ftfCpf = new JFormattedTextField();
		ftfCpf.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(ftfCpf, "cell 3 3 4 1,grow");

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnPesquisar, "cell 8 1 1 3,grow");

		btnSalvar = new JButton("Salvar");
		getContentPane().add(btnSalvar, "cell 3 9 2 1,grow");
		
		btnApagar = new JButton("Apagar");
		getContentPane().add(btnApagar, "cell 6 9,grow");
		
		JCheckBox chkbApagarCliente = new JCheckBox("Apagar Clientes");
		getContentPane().add(chkbApagarCliente, "cell 8 9,alignx leading,growy");
		chkbApagarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkbApagarCliente.isSelected()) {
					JOptionPane.showMessageDialog(null,	"Aten��o, Cuidado ao apagar um Funcionario!\nIsso pode gerar Problemas!");
					btnApagar.setEnabled(true);
				}

			}
		});

		Object[][] data = new Object[][] {{"#", "Nome", "Usuário(Login)", "Senha", "Tipo de Usuário" },};
		Object[] columnNames = new String[] {"#", "Nome","Usuário(Login)", "Senha", "Tipo de Usuário" };
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(data, columnNames));
		getContentPane().add(table, "cell 1 5 8 4,grow");
	}
}

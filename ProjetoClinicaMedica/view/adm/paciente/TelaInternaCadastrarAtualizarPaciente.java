package view.adm.paciente;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

public class TelaInternaCadastrarAtualizarPaciente extends JInternalFrame {

	private static final long serialVersionUID = -4822124346584250830L;
	private JTable tableClientes;
	private JButton btnApagar;
	private JFormattedTextField ftfCpf;
	private JButton btnPesquisar;
	private JButton btnSalvar;
	private JButton btnLimpar;
	private JButton btnPesquisarTodos;
	private JFormattedTextField ftfNome;

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
		getContentPane().setLayout(new MigLayout("", "[10][60.00px,center][10][grow][10][grow][10][grow][10][grow][10]", "[10][29.00px,fill][5][29.00,fill][10][40.00,fill][10][73.00,grow,fill][10][41.00,fill][10]"));

		initialize();
	}

	private void initialize() {

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblNome, "cell 1 1,alignx center,growy");
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblCpf, "cell 1 3,alignx center,growy");
		
		ftfNome = new JFormattedTextField();
		ftfNome.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(ftfNome, "cell 3 1 5 1,grow");

		ftfCpf = new JFormattedTextField();
		ftfCpf.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(ftfCpf, "cell 3 3 5 1,grow");
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnPesquisar, "cell 9 1 1 3,grow");
		btnPesquisar.addActionListener(e -> {

		});

		btnLimpar = new JButton("Limpar Campos");
		btnLimpar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnLimpar, "flowx,cell 3 5 3 1,grow");
		btnLimpar.addActionListener(e -> {
			ftfNome.setText("");
			ftfCpf.setText("");
		});


		btnPesquisarTodos = new JButton("Pesquisar Todos");
		btnPesquisarTodos.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnPesquisarTodos, "cell 7 5 3 1,grow");
		btnPesquisarTodos.addActionListener(e -> {

		});

		btnSalvar = new JButton("Salvar Dados");
		btnSalvar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnSalvar, "cell 5 9,grow");
		btnSalvar.addActionListener(e -> {

		});

		btnApagar = new JButton("APAGAR");
		btnApagar.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnApagar.setEnabled(false);
		getContentPane().add(btnApagar, "cell 7 9,grow");
		btnApagar.addActionListener(e -> {
			// TODO Pegar a linha/coluna selecionada e alterar diretamente na tabela
			// int[] id = tableClientes.getSelectedRows();
		});

		Object[][] data = new Object[][] { { "#", "Nome", "CPF", "RG", "DT Nascimento", "Sexo", "TP Sanguíneo",
			"Cidade", "Bairro", "Rua", "Numero", "Complemento", "Telefone", "Celular", "E-Mail" }, };
		Object[] columnNames = new String[] { "#", "Nome", "CPF", "RG", "DT Nascimento", "Sexo", "TP Sanguíneo",
			"Cidade", "Bairro", "Rua", "Numero", "Complemento", "Telefone", "Celular", "E-Mail" };

		tableClientes = new JTable();
		tableClientes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().add(tableClientes, "cell 1 7 9 1,grow");
		tableClientes.setModel(new DefaultTableModel(data, columnNames));
		
		JCheckBox chkbApagarCliente = new JCheckBox("Apagar Clientes");
		chkbApagarCliente.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(chkbApagarCliente, "cell 9 9,alignx leading,growy");
		chkbApagarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkbApagarCliente.isSelected()) {
					JOptionPane.showMessageDialog(null,	"Aten��o, Cuidado ao apagar um Cliente!\nIsso pode gerar Problemas!");
					btnApagar.setEnabled(true);
				}

			}
		});

	}
}

package view.funcionarios.secretaria;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;

public class TelaInternaBuscarPaciente extends JInternalFrame {
	private JTextField txtNome;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaBuscarPaciente window = new TelaInternaBuscarPaciente();
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
	public TelaInternaBuscarPaciente() {
		setTitle("Buscar Paciente");
		setBounds(100, 100, 945, 748);
		setClosable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[][138px][24px][200px][10px][109px][10px][418px][]", "[25px,grow][26px,grow][8px,grow][605px,grow]"));
		
		JLabel lblNome = new JLabel("Digite o Nome:");
		getContentPane().add(lblNome, "cell 1 0,grow");
		
		JLabel lblCpf = new JLabel("CPF");
		getContentPane().add(lblCpf, "cell 5 0,grow");
		
		JFormattedTextField ftfCpf = new JFormattedTextField();
		getContentPane().add(ftfCpf, "cell 7 0,grow");
		
		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 3 0,grow");
		txtNome.setColumns(10);
		
		JLabel lblDtNascimento = new JLabel("Data de Nascimento:");
		getContentPane().add(lblDtNascimento, "cell 1 1,grow");
		
		JDateChooser dateChooser = new JDateChooser();
		getContentPane().add(dateChooser, "cell 3 1,grow");
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		getContentPane().add(separator, "cell 1 2 7 1,grow");
		
		JButton btnPesquisar = new JButton("Pesquisar");
		getContentPane().add(btnPesquisar, "cell 5 1 3 1,grow");
		
		table = new JTable();
		getContentPane().add(table, "cell 1 3 7 1,grow");
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//TODO adcionar filtros de pesquisar: nome,cpf,dtNascimento + jtable para buscar os dados, deixar parecido com a tela de cadastro.
	}
}

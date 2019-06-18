package view.funcionarios.secretaria;

import java.awt.Color;
import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import net.miginfocom.swing.MigLayout;

public class TelaInternaBuscarPaciente extends JInternalFrame {
	private JTextField txtNome;
	private JTable table;
	private JButton btnPesquisar;
	private JFormattedTextField ftfCpf;
	private final DatePicker datePicker = new DatePicker();

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
		super("Buscar Paciente", true, true, true, false);
		setBounds(100, 100, 945, 748);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[][138px][24px][200px][10px][109px][10px][418px][]",
				"[25px,grow][26px,grow][8px,grow][605px,grow]"));

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// TODO adcionar filtros de pesquisar: nome,cpf,dtNascimento + jtable para
		// buscar os dados, deixar parecido com a tela de cadastro.

		try {
			MaskFormatter mascaraCPF = new MaskFormatter("###.###.###-##");
		} catch (ParseException e) {
			System.out.println("Erro ao gerar a Mascar de CPF");
			System.out.println(e.getMessage());
		}

		JLabel lblNome = new JLabel("Digite o Nome:");
		getContentPane().add(lblNome, "cell 1 0,grow");

		JLabel lblCpf = new JLabel("CPF");
		getContentPane().add(lblCpf, "cell 5 0,grow");

		JLabel lblDtNascimento = new JLabel("Data de Nascimento:");
		getContentPane().add(lblDtNascimento, "cell 1 1,grow");

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		getContentPane().add(separator, "cell 1 2 7 1,grow");

		ftfCpf = new JFormattedTextField();
		getContentPane().add(ftfCpf, "cell 7 0,grow");

		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 3 0,grow");
		txtNome.setColumns(10);

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		datePicker.setSettings(dateSettings);
		getContentPane().add(datePicker, "cell 3 1,grow");

		btnPesquisar = new JButton("Pesquisar");
		getContentPane().add(btnPesquisar, "cell 5 1 3 1,grow");

		Object[][] data = new Object[][] {
				{ "Nome", "Data de Nascimento", "CPF", "Telefone", "Bairro", "Endereço", "Nº", "Consultas" }, };
		Object[] columnNames = new String[] { "Nome", "Data de Nascimento", "CPF", "Telefone", "Bairro", "Endereço",
				"Nº", "Consultas" };
		table = new JTable();
		table.setModel(new DefaultTableModel(data, columnNames));
		getContentPane().add(table, "cell 1 3 7 1,grow");

	}
}

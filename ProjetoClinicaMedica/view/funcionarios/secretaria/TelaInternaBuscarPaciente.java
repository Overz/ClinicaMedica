package view.funcionarios.secretaria;

import java.awt.Color;
import java.awt.EventQueue;
import java.text.ParseException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.ControllerFuncionario;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;

public class TelaInternaBuscarPaciente extends JInternalFrame {

	private static final long serialVersionUID = -3439228926572831568L;
	private final DatePicker datePicker = new DatePicker();
	private JFormattedTextField ftfCpf;
	private JFormattedTextField ftfNome;
	private MaskFormatter mascaraCPF;
	private MaskFormatter mascaraNome;
	private JButton btnPesquisar;
	private JTable table;

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

	public TelaInternaBuscarPaciente() {
		super("Clínica Médica - Buscar Paciente", false, true, false, false);
		setBounds(100, 100, 945, 748);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[10][138px,grow][158.00px,grow][76.00px,grow][grow][10]", "[20][25px,grow][26px,grow][10][15][605px,grow][20]"));

		initialize();
	}

	private void initialize() {

		try {
			mascaraCPF = new MaskFormatter("###.###.###-##");
			mascaraNome = new MaskFormatter("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
		} catch (ParseException e) {
			System.out.println("Erro ao gerar a Mascar de CPF");
			System.out.println(e.getMessage());
		}

		JLabel lblNome = new JLabel("Digite o Nome:");
		lblNome.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblNome, "cell 1 1,grow");

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblCpf, "cell 3 1,alignx center,growy");

		JLabel lblDtNascimento = new JLabel("Data de Nascimento:");
		lblDtNascimento.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblDtNascimento, "cell 1 2,grow");

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		getContentPane().add(separator, "cell 1 4 4 1,grow");

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		datePicker.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 20));
		datePicker.getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 20));
		datePicker.setSettings(dateSettings);
		getContentPane().add(datePicker, "cell 2 2,grow");

		ftfCpf = new JFormattedTextField(mascaraCPF);
		ftfCpf.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(ftfCpf, "cell 4 1,grow");

		ftfNome = new JFormattedTextField(mascaraNome);
		ftfNome.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(ftfNome, "cell 2 1,grow");

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnPesquisar, "cell 3 2 2 1,grow");
		btnPesquisar.addActionListener(e -> {

			try {

				String nome = ftfNome.getText();
				String cpf = ftfCpf.getText();
				LocalDate date = datePicker.getDate();

				ControllerFuncionario controller = new ControllerFuncionario();
				String mensagem = controller.validarTelaBuscarPaciente(nome, cpf, date);

				if (!(mensagem == null)) {
					JOptionPane.showMessageDialog(null, mensagem);
				} else {
					//TODO trazer busca dos campos selecionados
				}
			} catch (Exception e2) {
				System.out.println("Tela: Buscar Paciente. Erro ao Validar os Campos para Consulta.\n" + e2.getMessage());
			}

		});

		Object[][] data = new Object[][] {{ "Nome", "Data de Nascimento", "CPF", "Telefone", "Bairro", "Endereço", "Nº", "Consultas" }, };
		Object[] columnNames = new String[] { "Nome", "Data de Nascimento", "CPF", "Telefone", "Bairro", "Endereço", "Nº", "Consultas" };
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setModel(new DefaultTableModel(data, columnNames));
		getContentPane().add(table, "cell 1 5 4 1,grow");
	}
}

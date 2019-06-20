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

public class TelaInternaBuscarPaciente extends JInternalFrame {

	private static final long serialVersionUID = -3439228926572831568L;
	private JTable table;
	private JButton btnPesquisar;
	private JFormattedTextField ftfCpf;
	private final DatePicker datePicker = new DatePicker();
	private JFormattedTextField ftfNome;
	private MaskFormatter mascaraCPF;
	private MaskFormatter mascaraNome;

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
		super("Clínica Médica - Buscar Paciente", true, true, true, false);
		setBounds(100, 100, 945, 748);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[10][138px,grow][158.00px,grow][76.00px,grow][grow][10]", "[25px,grow][26px,grow][8px,grow][605px,grow]"));

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
		getContentPane().add(lblNome, "cell 1 0,grow");

		JLabel lblCpf = new JLabel("CPF:");
		getContentPane().add(lblCpf, "cell 3 0,alignx center,growy");

		JLabel lblDtNascimento = new JLabel("Data de Nascimento:");
		getContentPane().add(lblDtNascimento, "cell 1 1,grow");

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		getContentPane().add(separator, "cell 1 2 4 1,grow");

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		datePicker.setSettings(dateSettings);
		getContentPane().add(datePicker, "cell 2 1,grow");

		ftfCpf = new JFormattedTextField(mascaraCPF);
		getContentPane().add(ftfCpf, "cell 4 0,grow");

		ftfNome = new JFormattedTextField(mascaraNome);
		getContentPane().add(ftfNome, "cell 2 0,grow");

		btnPesquisar = new JButton("Pesquisar");
		getContentPane().add(btnPesquisar, "cell 3 1 2 1,grow");
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
		table.setModel(new DefaultTableModel(data, columnNames));
		getContentPane().add(table, "cell 1 3 4 1,grow");
	}
}

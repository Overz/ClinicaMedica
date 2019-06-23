package view.usuarios.funcionarios;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.ControllerFuncionario;
import controller.ControllerPaciente;
import model.seletor.SeletorPaciente;
import net.miginfocom.swing.MigLayout;

public class TelaInternaBuscarPaciente extends JInternalFrame {

	private static final long serialVersionUID = -3439228926572831568L;
	private final DatePicker datePicker = new DatePicker();
	private JFormattedTextField ftfCpf;
	private JFormattedTextField ftfNome;
	private MaskFormatter mascaraCPF;
	private MaskFormatter mascaraNome;
	private JButton btnPesquisar;
	private JTable table;
	private JButton btnSelecionarPaciente;
	private JButton btnCancelar;

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
		setBounds(100, 100, 1190, 842);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		getContentPane()
				.setLayout(new MigLayout("", "[10][138px,grow][10][158.00px,grow][10][76.00px,grow][10][grow][10]",
						"[10][25px,grow][20][26px,grow][20][605px,grow][][][20]"));

		initialize();
	}

	private void initialize() {

		try {
			mascaraCPF = new MaskFormatter("###.###.###-##");
			mascaraNome = new MaskFormatter(
					"LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
		} catch (ParseException e) {
			System.out.println("Erro ao gerar a Mascar de CPF");
			System.out.println(e.getMessage());
		}

		JLabel lblNome = new JLabel("Digite o Nome:");
		lblNome.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblNome, "cell 1 1,grow");

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblCpf, "cell 5 1,alignx center,growy");

		JLabel lblDtNascimento = new JLabel("Data de Nascimento:");
		lblDtNascimento.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblDtNascimento, "cell 1 3,grow");

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		datePicker.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 20));
		datePicker.getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 20));
		datePicker.setSettings(dateSettings);
		getContentPane().add(datePicker, "cell 3 3,grow");

		ftfCpf = new JFormattedTextField(mascaraCPF);
		ftfCpf.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(ftfCpf, "cell 7 1,grow");

		ftfNome = new JFormattedTextField(mascaraNome);
		ftfNome.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(ftfNome, "cell 3 1,grow");

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnPesquisar, "cell 5 3 3 1,grow");
		btnPesquisar.addActionListener(e -> {

			try {

				String nome = ftfNome.getText();
				String cpf = ftfCpf.getText();
				LocalDate date = datePicker.getDate();
				

				ControllerPaciente controller = new ControllerPaciente();
				SeletorPaciente seletor = new SeletorPaciente();
				seletor.setNome(nome);
				seletor.setCpf(cpf);
				seletor.setDate(date);
				
				String mensagem = controller.validarCamposPesquisarCadastroPaciente(seletor);

				if (!(mensagem == null)) {
					JOptionPane.showMessageDialog(null, mensagem);
				} else {
					
				}
			} catch (Exception e2) {
				System.out
						.println("Tela: Buscar Paciente. Erro ao Validar os Campos para Consulta.\n" + e2.getMessage());
			}

		});

		Object[][] data = new Object[][] {
				{ "Nome", "Data de Nascimento", "CPF", "Telefone", "Bairro", "Endereço", "Nº", "Consultas" }, };
		Object[] columnNames = new String[] { "Nome", "Data de Nascimento", "CPF", "Telefone", "Bairro", "Endereço",
				"Nº", "Consultas" };
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setModel(new DefaultTableModel(data, columnNames));
		getContentPane().add(table, "cell 1 5 7 1,grow");

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnCancelar, "cell 5 7,alignx right");

		btnSelecionarPaciente = new JButton("Selecionar Paciente");
		btnSelecionarPaciente.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnSelecionarPaciente, "cell 7 7");
	}
}

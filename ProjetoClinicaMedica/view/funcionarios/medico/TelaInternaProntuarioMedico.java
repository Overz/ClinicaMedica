package view.funcionarios.medico;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import net.miginfocom.swing.MigLayout;

public class TelaInternaProntuarioMedico extends JInternalFrame {

	private final DatePicker datePicker = new DatePicker();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaProntuarioMedico window = new TelaInternaProntuarioMedico();
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
	public TelaInternaProntuarioMedico() {
		setTitle("Cadastrar / Atualizar Prontuario");
		setClosable(true);
		setMaximizable(true);
		setResizable(true);
		setBounds(100, 100, 965, 788);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		getContentPane()
				.setLayout(new MigLayout("", "[10][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][10]",
						"[13][grow][13][grow][13][grow][13][13][grow][154.00,grow][grow][154px,grow][33px,grow][13]"));

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblNome, "cell 1 1 4 1,grow");

		JLabel lblNomeDaMae = new JLabel("Nome da Mãe:");
		lblNomeDaMae.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblNomeDaMae, "cell 5 1,grow");

		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblDataDeNascimento, "cell 1 3 4 1,grow");

		JLabel lblNomeDoPai = new JLabel("Nome do Pai:");
		lblNomeDoPai.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblNomeDoPai, "cell 5 3,grow");

		JLabel lblAtendimento = new JLabel("Atendimento:");
		lblAtendimento.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblAtendimento, "cell 1 5,grow");

		JLabel lblRetorno = new JLabel("Retorno:");
		lblRetorno.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblRetorno, "cell 5 5,grow");

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		datePicker.setSettings(dateSettings);
		getContentPane().add(datePicker, "cell 6 5,grow");

		JLabel lblPatologiaApresentada = new JLabel("Patologia Apresentada:");
		lblPatologiaApresentada.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblPatologiaApresentada, "cell 1 8 4 1,alignx left,growy");

		// TODO TENTAR ADC DATA ATUAL DENTRO DO TEXTPANE PRÉ-DEFINIDO
		JTextPane jtxtPanePatologia = new JTextPane();
		getContentPane().add(jtxtPanePatologia, "cell 1 9 10 1,grow");

		JLabel lblTratamentoIndicado = new JLabel("Tratamento Indicado:");
		lblTratamentoIndicado.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblTratamentoIndicado, "cell 1 10,alignx left,growy");

		JTextPane jtxtPaneTratamento = new JTextPane();
		getContentPane().add(jtxtPaneTratamento, "cell 1 11 10 1,grow");

		JButton btnSalvar = new JButton("Salvar / Imprimir");
		btnSalvar.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(btnSalvar, "cell 3 12 4 1,grow");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

	}
}

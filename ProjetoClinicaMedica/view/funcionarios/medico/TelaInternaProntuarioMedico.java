package view.funcionarios.medico;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.UIManager;

public class TelaInternaProntuarioMedico extends JInternalFrame {

	private static final long serialVersionUID = 6287035520900270789L;
	private final DatePicker datePicker = new DatePicker();
	private JButton btnSalvar;
	private JTextArea txtAreaPatologia;
	private JTextArea txtAreaTratamento;

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

	public TelaInternaProntuarioMedico() {
		super("Clínica Médica - Cadastrar/Atualizar Prontuario", false, true, false, false);
		setBounds(100, 100, 965, 788);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[10][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][10]", "[10][grow][13][grow][13][grow][13][13][grow][154.00,grow][grow][154px,grow][33px,grow][10]"));

		initialize();
	}

	private void initialize() {
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblNome, "cell 1 1 4 1,alignx left,growy");

		JLabel lblNomeDaMae = new JLabel("Nome da Mãe:");
		lblNomeDaMae.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblNomeDaMae, "cell 5 1 2 1,grow");

		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblDataDeNascimento, "cell 1 3 4 1,alignx left,growy");

		JLabel lblNomeDoPai = new JLabel("Nome do Pai:");
		lblNomeDoPai.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblNomeDoPai, "cell 5 3 2 1,grow");

		JLabel lblAtendimento = new JLabel("Atendimento:");
		lblAtendimento.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblAtendimento, "cell 1 5 4 1,alignx left,growy");

		JLabel lblRetorno = new JLabel("Retorno:");
		lblRetorno.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblRetorno, "cell 5 5,grow");
		
		
		JLabel lblTratamentoIndicado = new JLabel("Tratamento Indicado:");
		lblTratamentoIndicado.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblTratamentoIndicado, "cell 1 10 2 1,alignx center,growy");

		JLabel lblPatologiaApresentada = new JLabel("Patologia Apresentada:");
		lblPatologiaApresentada.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblPatologiaApresentada, "cell 1 8 2 1,alignx center,growy");

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		datePicker.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 20));
		datePicker.getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 20));
		datePicker.setBorder(null);

		datePicker.setSettings(dateSettings);
		getContentPane().add(datePicker, "cell 6 5,grow");
		
		txtAreaPatologia = new JTextArea();
		txtAreaPatologia.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtAreaPatologia.setLineWrap(true);
		txtAreaPatologia.setWrapStyleWord(true);
		getContentPane().add(txtAreaPatologia, "cell 1 9 10 1,grow");

		txtAreaTratamento = new JTextArea();
		txtAreaTratamento.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtAreaTratamento.setLineWrap(true);
		txtAreaTratamento.setWrapStyleWord(true);

		getContentPane().add(txtAreaTratamento, "cell 1 11 10 1,grow");

		btnSalvar = new JButton("Salvar / Imprimir");
		btnSalvar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnSalvar, "cell 3 12 4 1,grow");
		btnSalvar.addActionListener(e -> {
			
			//TODO Fazer Validação??
			//TODO Salvar no Banco, salvar como arquivo, e deixar disponivel para impressão.
		});

	}
}

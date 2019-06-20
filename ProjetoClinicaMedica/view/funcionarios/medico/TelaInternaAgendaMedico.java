package view.funcionarios.medico;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

public class TelaInternaAgendaMedico extends JInternalFrame {

	private static final long serialVersionUID = -1343244694236177024L;
	private JTextField textMedico;
	private JTextField textAgenda;
	private JTextField textTelefone;
	private JTextField textEmail;
	private JButton btnCadastrar;
	private JButton btnCancelar;
	private JButton btnAlterarAgenda;
	private JTextArea textArea;
	private final DatePicker datePicker = new DatePicker();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaAgendaMedico window = new TelaInternaAgendaMedico();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInternaAgendaMedico() {
		super("Clínica Médica - Cadastro de Agenda Médica", true, true, true, true);
		setBounds(100, 100, 763, 605);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		this.repaint();

		initialize();
	}

	private void initialize() {

		// TODO tentar:
		// - Cadastrar primeira vez, na segunda vez, ao abrir, os dados ja vem descritos
		// nos campos, para atualizar.

		JLabel lblDados = new JLabel("Dados Da Agenda");
		lblDados.setFont(new Font("Verdana", Font.BOLD, 14));
		lblDados.setBounds(10, 11, 141, 31);
		getContentPane().add(lblDados);

		JLabel lblMedico = new JLabel("Médico:");
		lblMedico.setBounds(10, 74, 124, 31);
		getContentPane().add(lblMedico);

		JLabel lblNomeDaAgenda = new JLabel("Nome da Agenda:");
		lblNomeDaAgenda.setBounds(10, 122, 124, 31);
		getContentPane().add(lblNomeDaAgenda);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(370, 74, 124, 31);
		getContentPane().add(lblTelefone);

		JLabel labelEmail = new JLabel("Email:");
		labelEmail.setBounds(370, 122, 124, 31);
		getContentPane().add(labelEmail);

		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setBounds(10, 207, 124, 31);
		getContentPane().add(lblDescricao);

		textMedico = new JTextField();

		textMedico.setBounds(144, 74, 216, 31);
		getContentPane().add(textMedico);
		textMedico.setColumns(10);

		textAgenda = new JTextField();
		textAgenda.setColumns(10);
		textAgenda.setBounds(144, 119, 216, 34);
		getContentPane().add(textAgenda);

		textTelefone = new JTextField();
		textTelefone.setColumns(10);
		textTelefone.setBounds(504, 74, 216, 31);
		getContentPane().add(textTelefone);

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(504, 122, 216, 31);
		getContentPane().add(textEmail);

		textArea = new JTextArea();
		textArea.setBounds(144, 210, 576, 262);
		getContentPane().add(textArea);

		btnCadastrar = new JButton("Cadastrar Agenda");
		btnCadastrar.setBounds(170, 483, 172, 68);
		getContentPane().add(btnCadastrar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(543, 483, 160, 68);
		getContentPane().add(btnCancelar);

		btnAlterarAgenda = new JButton("Alterar Agenda");
		btnAlterarAgenda.setBounds(352, 483, 172, 68);
		getContentPane().add(btnAlterarAgenda);

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		datePicker.setSettings(dateSettings);
		getContentPane().add(datePicker);

		this.repaint();

	}
}

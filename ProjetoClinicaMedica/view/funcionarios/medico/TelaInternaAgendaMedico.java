package view.funcionarios.medico;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.ControllerMedico;
import net.miginfocom.swing.MigLayout;
import javax.swing.JFormattedTextField;

public class TelaInternaAgendaMedico extends JInternalFrame {

	private static final long serialVersionUID = -1343244694236177024L;
	private JButton btnCadastrar;
	private JButton btnCancelar;
	private JButton btnAlterarAgenda;
	private JTextArea textArea;
	private JFormattedTextField ftfNomeMedico;
	private JFormattedTextField ftfNomeAgenda;
	private JFormattedTextField ftfTelefone;
	private JFormattedTextField ftfEmail;
	private MaskFormatter mascaraNome;
	private MaskFormatter mascaraTelefone;
	private MaskFormatter mascaraEmail;

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
		super("Clínica Médica - Cadastro de Agenda Médica", false, true, false, false);
		setBounds(100, 100, 763, 605);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[124px,grow][10][172px,grow][10][124px,grow][10][177px,grow][10]", "[31px,grow][31px,grow][34px,grow][][265px,grow][68px,grow][15]"));
		this.repaint();

		initialize();
	}

	private void initialize() {

		// TODO tentar:
		// - Cadastrar primeira vez, na segunda vez, ao abrir, os dados ja vem descritos
		// nos campos, para atualizar.

		try {
			mascaraNome = new MaskFormatter("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
			mascaraEmail = new MaskFormatter("************************************************");
			mascaraTelefone = new MaskFormatter("(##)#########");
		} catch (ParseException e1) {
			System.out.println("Tela: Agenda Médica. Erro ao Criar a Mascara.\n" + e1.getMessage());
		}
		
		JLabel lblDados = new JLabel("Dados Da Agenda");
		lblDados.setFont(new Font("Verdana", Font.BOLD, 14));
		getContentPane().add(lblDados, "cell 0 0,grow");

		JLabel lblMedico = new JLabel("Médico:");
		getContentPane().add(lblMedico, "cell 0 1,grow");

		JLabel lblNomeDaAgenda = new JLabel("Nome da Agenda:");
		getContentPane().add(lblNomeDaAgenda, "cell 0 2,grow");

		JLabel lblTelefone = new JLabel("Telefone:");
		getContentPane().add(lblTelefone, "cell 4 1,alignx center,growy");

		JLabel labelEmail = new JLabel("Email:");
		getContentPane().add(labelEmail, "cell 4 2,alignx center,growy");

		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setFont(new Font("Arial", Font.PLAIN, 16));
		getContentPane().add(lblDescricao, "cell 0 4,growx,aligny top");

		ftfNomeMedico = new JFormattedTextField(mascaraNome);
		getContentPane().add(ftfNomeMedico, "cell 2 1,grow");

		ftfNomeAgenda = new JFormattedTextField(mascaraNome);
		getContentPane().add(ftfNomeAgenda, "cell 2 2,grow");

		ftfEmail = new JFormattedTextField(mascaraEmail);
		getContentPane().add(ftfEmail, "cell 6 2,grow");

		ftfTelefone = new JFormattedTextField(mascaraTelefone);
		getContentPane().add(ftfTelefone, "cell 6 1,grow");

		textArea = new JTextArea();
		getContentPane().add(textArea, "cell 2 4 5 1,grow");

		btnCadastrar = new JButton("Cadastrar Agenda");
		getContentPane().add(btnCadastrar, "cell 2 5,grow");
		btnCadastrar.addActionListener(e -> {

			try {
				String nomeMedico = ftfNomeMedico.getText();
				String nomeAgenda = ftfNomeAgenda.getText();
				String telefone = ftfTelefone.getText();
				String email = ftfEmail.getText();
				String texto = textArea.getText();

				ControllerMedico controller = new ControllerMedico();
				String mensagem = controller.validarAgendaMedica(nomeMedico, nomeAgenda, telefone, email, texto);
				if (mensagem != null) {
					JOptionPane.showMessageDialog(null, mensagem);
				} else {
					//TODO ATUALIZAR Agenda no Banco
				}
			} catch (NullPointerException e2) {	
				System.out.println("Tela: Agenda Médica. Erro ao validar os Campos para CADASTRAR a Agenda.\n" + e2.getMessage());
			}
		});

		btnAlterarAgenda = new JButton("Alterar Agenda");
		getContentPane().add(btnAlterarAgenda, "cell 4 5,grow");
		btnAlterarAgenda.addActionListener(e -> {

			try {
				String nomeMedico = ftfNomeMedico.getText();
				String nomeAgenda = ftfNomeAgenda.getText();
				String telefone = ftfTelefone.getText();
				String email = ftfEmail.getText();
				String texto = textArea.getText();

				ControllerMedico controller = new ControllerMedico();
				String mensagem = controller.validarAgendaMedica(nomeMedico, nomeAgenda, telefone, email, texto);
				if (mensagem != null) {
					JOptionPane.showMessageDialog(null, mensagem);
				} else {
					//TODO ATUALIZAR Agenda no Banco
				}
			} catch (NullPointerException e2) {
				System.out.println("Tela: Agenda Médica. Erro ao validar os Campos para ATUALIZAR a Agenda." + e2.getMessage());
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		getContentPane().add(btnCancelar, "cell 6 5,grow");
		btnCadastrar.addActionListener(e -> {

			ftfNomeMedico.setText("");
			ftfNomeAgenda.setText("");
			ftfEmail.setText("");
			ftfTelefone.setText("");
			textArea.setText("");

			this.dispose();
		});

		this.repaint();

	}
}

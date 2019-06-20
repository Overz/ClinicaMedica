package view.funcionarios.medico;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.MaskFormatter;

import controller.ControllerMedico;
import net.miginfocom.swing.MigLayout;

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
		setBounds(100, 100, 1077, 783);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[10][124px,grow][10][172px,grow][10][124px,grow][10][177px,grow][10]", "[31px,grow][31px,grow][34px,grow][][265px,grow][68px,grow][15]"));
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
		lblDados.setFont(new Font("Verdana", Font.BOLD, 25));
		getContentPane().add(lblDados, "cell 1 0,grow");

		JLabel lblMedico = new JLabel("Médico:");
		lblMedico.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblMedico, "cell 1 1,grow");

		JLabel lblNomeDaAgenda = new JLabel("Nome da Agenda:");
		lblNomeDaAgenda.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblNomeDaAgenda, "cell 1 2,grow");

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblTelefone, "cell 5 1,alignx center,growy");

		JLabel labelEmail = new JLabel("Email:");
		labelEmail.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(labelEmail, "cell 5 2,alignx center,growy");

		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblDescricao, "cell 1 4,alignx left,aligny top");

		ftfNomeMedico = new JFormattedTextField(mascaraNome);
		ftfNomeMedico.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(ftfNomeMedico, "cell 3 1,grow");

		ftfNomeAgenda = new JFormattedTextField(mascaraNome);
		ftfNomeAgenda.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(ftfNomeAgenda, "cell 3 2,grow");

		ftfEmail = new JFormattedTextField(mascaraEmail);
		ftfEmail.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(ftfEmail, "cell 7 2,grow");

		ftfTelefone = new JFormattedTextField(mascaraTelefone);
		ftfTelefone.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(ftfTelefone, "cell 7 1,grow");

		textArea = new JTextArea();
		textArea.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(textArea, "cell 3 4 5 1,grow");

		btnCadastrar = new JButton("Cadastrar Agenda");
		btnCadastrar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnCadastrar, "cell 3 5,grow");
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
		btnAlterarAgenda.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnAlterarAgenda, "cell 5 5,grow");
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
		btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnCancelar, "cell 7 5,grow");
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

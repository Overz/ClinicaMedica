package view.funcionarios.medico;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.ControllerMedico;
import net.miginfocom.swing.MigLayout;

public class TelaInternaAgendaMedico extends JInternalFrame {

	private static final long serialVersionUID = -1343244694236177024L;
	private JTextField txtMedico;
	private JTextField txtAgenda;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JButton btnCadastrar;
	private JButton btnCancelar;
	private JButton btnAlterarAgenda;
	private JTextArea textArea;

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
		this.repaint();

		initialize();
	}

	private void initialize() {
		getContentPane().setLayout(new MigLayout("", "[124px,grow][10][172px,grow][10][124px,grow][10][177px,grow][10]", "[31px,grow][31px,grow][34px,grow][][265px,grow][68px,grow][15]"));

		// TODO tentar:
		// - Cadastrar primeira vez, na segunda vez, ao abrir, os dados ja vem descritos
		// nos campos, para atualizar.

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

		txtMedico = new JTextField();
		getContentPane().add(txtMedico, "cell 2 1,grow");
		txtMedico.setColumns(10);

		txtAgenda = new JTextField();
		txtAgenda.setColumns(10);
		getContentPane().add(txtAgenda, "cell 2 2,grow");

		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		getContentPane().add(txtTelefone, "cell 6 1,grow");

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		getContentPane().add(txtEmail, "cell 6 2,grow");

		textArea = new JTextArea();
		getContentPane().add(textArea, "cell 2 4 5 1,grow");

		btnCadastrar = new JButton("Cadastrar Agenda");
		getContentPane().add(btnCadastrar, "cell 2 5,grow");
		btnCadastrar.addActionListener(e -> {
			
			ControllerMedico controller = new ControllerMedico();
			
			String nomeMedico = txtMedico.getText();
			String nomeAgenda = txtAgenda.getText();
			String telefone = txtTelefone.getText();
			String email = txtEmail.getText();
			
			String mensagem = controller.validarAgendaMedica(nomeMedico, nomeAgenda, telefone, email, null);
			
			if (mensagem != null) {
				JOptionPane.showMessageDialog(null, mensagem);
			} else {
				//TODO Cadastrar Agenda no Banco.
			}
		});

		btnCancelar = new JButton("Cancelar");
		getContentPane().add(btnCancelar, "cell 6 5,grow");
		btnCadastrar.addActionListener(e -> {
			
			txtAgenda.setText("");
			txtEmail.setText("");
			txtMedico.setText("");
			txtTelefone.setText("");
			textArea.setText("");

			this.dispose();
			
		});

		btnAlterarAgenda = new JButton("Alterar Agenda");
		getContentPane().add(btnAlterarAgenda, "cell 4 5,grow");
		btnAlterarAgenda.addActionListener(e -> {
			
			ControllerMedico controller = new ControllerMedico();
			
			String nomeMedico = txtMedico.getText();
			String nomeAgenda = txtAgenda.getText();
			String telefone = txtTelefone.getText();
			String email = txtEmail.getText();
			String texto = textArea.getText();
			
			String mensagem = controller.validarAgendaMedica(nomeMedico, nomeAgenda, telefone, email, texto);
			if (mensagem != null) {
				JOptionPane.showMessageDialog(null, mensagem);
			} else {
				//TODO ATUALIZAR Agenda no Banco
			}
			
		});

		this.repaint();

	}
}

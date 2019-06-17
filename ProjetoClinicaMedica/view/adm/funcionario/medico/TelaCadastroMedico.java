package view.adm.funcionario.medico;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ControllerMedico;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TelaCadastroMedico extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEspecialidade;
	private JTextField txtCrm;
	private JTextField txtTelefone;
	private JTextField txtCelular;
	private JTextField txtEmail;
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	private JPasswordField passwordField_confirm;
	private JTextField txtCep;
	private JTextField txtRua;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtEstado;
	private JButton btnCadastrar;
	private JButton btnCancelar;
	private JComboBox comboBox;
	private JLabel lblNivel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroMedico frame = new TelaCadastroMedico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroMedico() {
		setTitle("Clínica Médica - Cadastro de Médicos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 840, 663);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][grow][][grow][grow][]", "[][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][]"));

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNome, "cell 1 1,alignx left,growy");

		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(txtNome, "cell 2 1,grow");
		txtNome.setColumns(10);

		txtEspecialidade = new JTextField();
		txtEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(txtEspecialidade, "cell 5 1,grow");
		txtEspecialidade.setColumns(10);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblCpf, "cell 1 2,alignx left,growy");

		JLabel lblEspecialidade = new JLabel("Especialidade:");
		lblEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblEspecialidade, "cell 4 1,alignx left,growy");

		txtCpf = new JTextField();
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCpf.setColumns(10);
		contentPane.add(txtCpf, "cell 2 2,grow");

		JLabel lblCrm = new JLabel("CRM:");
		lblCrm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblCrm, "cell 4 2,alignx left,growy");

		txtCrm = new JTextField();
		txtCrm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(txtCrm, "cell 5 2,grow");
		txtCrm.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblTelefone, "cell 1 3,alignx left,growy");

		txtTelefone = new JTextField();
		txtTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(txtTelefone, "cell 2 3,grow");
		txtTelefone.setColumns(10);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblEmail, "cell 4 3,growy");

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		contentPane.add(txtEmail, "cell 5 3,grow");

		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblCelular, "cell 1 4,alignx left,growy");

		txtCelular = new JTextField();
		txtCelular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(txtCelular, "cell 2 4,grow");
		txtCelular.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuário:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblUsuario, "cell 1 6,alignx left,growy");

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(txtUsuario, "cell 2 6,grow");
		txtUsuario.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblSenha, "cell 4 6,alignx left,growy");

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(passwordField, "cell 5 6,grow");
		
		lblNivel = new JLabel("Nivel:");
		lblNivel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNivel, "cell 1 7,alignx left,growy");
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Normal", "Médico", "Administrador"}));
		contentPane.add(comboBox, "cell 2 7,grow");

		JLabel lblConfirmeSuaSenha = new JLabel("Confirme sua Senha:");
		lblConfirmeSuaSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblConfirmeSuaSenha, "cell 4 7,alignx left,growy");

		passwordField_confirm = new JPasswordField();
		passwordField_confirm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(passwordField_confirm, "cell 5 7,grow");

		JLabel lblCep = new JLabel("CEP:");
		lblCep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblCep, "cell 1 9,alignx left,growy");

		txtCep = new JTextField();
		txtCep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(txtCep, "cell 2 9,grow");
		txtCep.setColumns(10);

		JLabel lblRua = new JLabel("Rua:");
		lblRua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblRua, "cell 1 10,alignx left,growy");

		txtRua = new JTextField();
		txtRua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(txtRua, "cell 2 10,grow");
		txtRua.setColumns(10);

		JLabel lblNumero = new JLabel("Número:");
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNumero, "cell 4 10,alignx left,growy");

		txtNumero = new JTextField();
		txtNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(txtNumero, "cell 5 10,grow");
		txtNumero.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblBairro, "cell 1 11,alignx left,growy");

		txtBairro = new JTextField();
		txtBairro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(txtBairro, "cell 2 11,grow");
		txtBairro.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblCidade, "cell 4 11,alignx left,growy");

		txtCidade = new JTextField();
		txtCidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(txtCidade, "cell 5 11,grow");
		txtCidade.setColumns(10);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblEstado, "cell 1 12,alignx left,growy");

		txtEstado = new JTextField();
		txtEstado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(txtEstado, "cell 2 12,grow");
		txtEstado.setColumns(10);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnCancelar, "cell 2 15,grow");
		btnCancelar.addActionListener(e -> {
			this.dispose();
		});

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnCadastrar, "cell 4 15,grow");
		btnCadastrar.addActionListener(e -> {
			cadastrar();
		});

	}

	public void cadastrar() {

		String nome = txtNome.getText();
		String cpf = txtCpf.getText();
		String telefone = txtTelefone.getText();
		String celular = txtCelular.getText();
		String email = txtEmail.getText();
		String crm = txtCrm.getText();
		String especialidade = txtEspecialidade.getText();
		String rua = txtRua.getText();
		int numero = Integer.parseInt(txtNumero.getText());
		String bairro = txtBairro.getText();
		String cidade = txtCidade.getText();
		String estado = txtEstado.getText();
		String cep = txtCep.getText();
		String usuario = txtUsuario.getText();
		String senha = new String(passwordField.getPassword());
		String confirmacaoSenha = new String(passwordField_confirm.getPassword());

		ControllerMedico controller = new ControllerMedico();
		/*String mensagem = controller.cadastrarMedico(nome, cpf, telefone, celular, email, crm, especialidade,
				rua, numero, bairro, cidade, estado, cep, usuario, senha, confirmacaoSenha);
		JOptionPane.showMessageDialog(contentPane, mensagem);-*/
	}
}

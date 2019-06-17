package view.adm.usuario;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ControllerFuncionario;
import controller.ControllerMedico;
import model.vo.UsuarioVO;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;

public class TelaCadastroUsuario extends JFrame {

	private JPanel getContentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEspecialidade;
	private JTextField txtCrm;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldConfirm;
	private JComboBox cbxTipoUsuario;
	private JButton btnCancelar;
	private JButton btnCadastrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroUsuario frame = new TelaCadastroUsuario();
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
	public TelaCadastroUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*super("Cinica Médica - Cadastro de Usuarios",
			true,
			true,
			true,
			true);*/
		//setTitle("Clinica Médica - Cadastro de Usuario");
		setBounds(100, 100, 800, 636);
		getContentPane = new JPanel();
		getContentPane.setBackground(Color.WHITE);
		getContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(getContentPane);
		getContentPane.setLayout(new MigLayout("", "[10][][grow][20][20][grow][grow][10]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]"));
		
		initialize();
	}
	
	public void initialize() {
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane.add(lblNome, "cell 1 2,alignx left,growy");
		
		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuário:");
		lblTipoDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane.add(lblTipoDeUsuario, "cell 2 0,alignx trailing,growy");
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane.add(lblEmail, "cell 1 4,alignx left,growy");
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane.add(lblCpf, "cell 5 2,grow");
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane.add(lblTelefone, "cell 5 4,grow");
		
		JLabel lblCrm = new JLabel("CRM:");
		lblCrm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane.add(lblCrm, "cell 1 6,alignx left,growy");

		JLabel lblEspecialidade = new JLabel("Especialidade:");
		lblEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane.add(lblEspecialidade, "cell 5 6,grow");

		JLabel lblUsuario = new JLabel("Usuário:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane.add(lblUsuario, "cell 1 9,alignx left,growy");
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane.add(lblSenha, "cell 1 11,alignx left,growy");
		
		JLabel lblConfirmeSuaSenha = new JLabel("Confirme sua Senha:");
		lblConfirmeSuaSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane.add(lblConfirmeSuaSenha, "cell 5 11,grow");
		
		cbxTipoUsuario = new JComboBox();
		cbxTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxTipoUsuario.setModel(new DefaultComboBoxModel(new String[] { UsuarioVO.NIVEL_FUNCIONARIO, UsuarioVO.NIVEL_MEDICO }));
		getContentPane.add(cbxTipoUsuario, "cell 5 0,grow");
		
		cbxTipoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbxTipoUsuario.getSelectedIndex() > -1) {
					String tipoUsuario = (String) cbxTipoUsuario.getModel().getSelectedItem();
					if (tipoUsuario.equals(UsuarioVO.NIVEL_FUNCIONARIO)) {
						txtNome.setEnabled(true);
						txtCpf.setEnabled(true);
						txtEmail.setEnabled(true);
						txtTelefone.setEnabled(true);
						txtUsuario.setEnabled(true);
						passwordField.setEnabled(true);
						passwordFieldConfirm.setEnabled(true);
						txtCrm.setEnabled(false);
						txtEspecialidade.setEnabled(false);
					} else if (tipoUsuario.equals(UsuarioVO.NIVEL_MEDICO)) {
						txtNome.setEnabled(true);
						txtCpf.setEnabled(true);
						txtEmail.setEnabled(true);
						txtTelefone.setEnabled(true);
						txtUsuario.setEnabled(true);
						passwordField.setEnabled(true);
						passwordFieldConfirm.setEnabled(true);
						txtCrm.setEnabled(true);
						txtEspecialidade.setEnabled(true);
					}
				}

			}
		});
		cbxTipoUsuario.setSelectedIndex(-1);

		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane.add(txtNome, "cell 2 2,grow");
		txtNome.setColumns(10);
		txtNome.setEnabled(false);

		txtCpf = new JTextField();
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCpf.setColumns(10);
		getContentPane.add(txtCpf, "cell 6 2,grow");
		txtCpf.setEnabled(false);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		getContentPane.add(txtEmail, "cell 2 4,grow");
		txtEmail.setEnabled(false);
		
		txtTelefone = new JTextField();
		txtTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane.add(txtTelefone, "cell 6 4,grow");
		txtTelefone.setColumns(10);
		txtTelefone.setEnabled(false);

		txtCrm = new JTextField();
		txtCrm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane.add(txtCrm, "cell 2 6,grow");
		txtCrm.setColumns(10);
		txtCrm.setEnabled(false);

		txtEspecialidade = new JTextField();
		txtEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane.add(txtEspecialidade, "cell 6 6,grow");
		txtEspecialidade.setColumns(10);
		txtEspecialidade.setEnabled(false);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane.add(txtUsuario, "cell 2 9,grow");
		txtUsuario.setColumns(10);
		txtUsuario.setEnabled(false);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane.add(passwordField, "cell 2 11,grow");
		passwordField.setEnabled(false);

		passwordFieldConfirm = new JPasswordField();
		passwordFieldConfirm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane.add(passwordFieldConfirm, "cell 6 11,grow");
		passwordFieldConfirm.setEnabled(false);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane.add(btnCancelar, "cell 2 14,grow");
		btnCancelar.addActionListener(e -> {
			this.dispose();
		});

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane.add(btnCadastrar, "cell 5 14,grow");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensagem = "";
				if (cbxTipoUsuario.getModel().getSelectedItem().equals(UsuarioVO.NIVEL_FUNCIONARIO)) {
					String nome = txtNome.getText();
					String cpf = txtCpf.getText();
					String telefone = txtTelefone.getText();
					String email = txtEmail.getText();
					String usuario = txtUsuario.getText();
					String senha = new String(passwordField.getPassword());
					String confirmacaoSenha = new String(passwordFieldConfirm.getPassword());
					String nivel = (String) cbxTipoUsuario.getModel().getSelectedItem();
					// TODO passar data_nascimento;

					ControllerFuncionario controller = new ControllerFuncionario();
					mensagem = controller.cadastrarFuncionario(nome, cpf, telefone, email, usuario, senha,
							confirmacaoSenha, nivel);
				} else if (cbxTipoUsuario.getModel().getSelectedItem().equals(UsuarioVO.NIVEL_MEDICO)) {
					String nome = txtNome.getText();
					String cpf = txtCpf.getText();
					String telefone = txtTelefone.getText();
					String email = txtEmail.getText();
					String crm = txtCrm.getText();
					String especialidade = txtEspecialidade.getText();
					String usuario = txtUsuario.getText();
					String senha = new String(passwordField.getPassword());
					String confirmacaoSenha = new String(passwordFieldConfirm.getPassword());
					String nivel = (String) cbxTipoUsuario.getModel().getSelectedItem();
					// TODO passar data_nascimento;

					ControllerMedico controller = new ControllerMedico();
					mensagem = controller.cadastrarMedico(nome, cpf, telefone, email, crm, especialidade, usuario,
							senha, confirmacaoSenha, nivel);
				}
				JOptionPane.showMessageDialog(getContentPane, mensagem);
			}
		});

	}

}

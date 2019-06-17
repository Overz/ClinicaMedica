package view.adm.funcionario;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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

public class TelaCadastroUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEspecialidade;
	private JTextField txtCrm;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldConfirm;

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
		setTitle("Clínica Médica - Cadastro de Usuários");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 552, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(
				new MigLayout("", "[][][grow][][][grow][grow][][][]", "[][][][][][][][][][][][][][][][][][][][][]"));

		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuário:");
		lblTipoDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblTipoDeUsuario, "cell 2 0,alignx trailing");

		JComboBox cbxTipoUsuario = new JComboBox();
		cbxTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxTipoUsuario.setModel(
				new DefaultComboBoxModel(new String[] { UsuarioVO.NIVEL_FUNCIONARIO, UsuarioVO.NIVEL_MEDICO }));
		contentPane.add(cbxTipoUsuario, "cell 5 0,growx");
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

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNome, "cell 1 2");

		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(txtNome, "cell 2 2");
		txtNome.setColumns(10);
		txtNome.setEnabled(false);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblCpf, "cell 5 2");

		txtCpf = new JTextField();
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCpf.setColumns(10);
		contentPane.add(txtCpf, "cell 6 2");
		txtCpf.setEnabled(false);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblEmail, "cell 1 4");

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		contentPane.add(txtEmail, "cell 2 4,growx");
		txtEmail.setEnabled(false);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblTelefone, "cell 5 4");

		txtTelefone = new JTextField();
		txtTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(txtTelefone, "cell 6 4,aligny top");
		txtTelefone.setColumns(10);
		txtTelefone.setEnabled(false);

		JLabel lblCrm = new JLabel("CRM:");
		lblCrm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblCrm, "cell 1 6,alignx left");

		txtCrm = new JTextField();
		txtCrm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(txtCrm, "cell 2 6,growx");
		txtCrm.setColumns(10);
		txtCrm.setEnabled(false);

		JLabel lblEspecialidade = new JLabel("Especialidade:");
		lblEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblEspecialidade, "cell 5 6,alignx left");

		txtEspecialidade = new JTextField();
		txtEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(txtEspecialidade, "cell 6 6,growx,aligny top");
		txtEspecialidade.setColumns(10);
		txtEspecialidade.setEnabled(false);

		JLabel lblUsuario = new JLabel("Usuário:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblUsuario, "cell 1 9");

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(txtUsuario, "cell 2 9,growx");
		txtUsuario.setColumns(10);
		txtUsuario.setEnabled(false);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblSenha, "cell 1 11");

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(passwordField, "cell 2 11,growx");
		passwordField.setEnabled(false);

		JLabel lblConfirmeSuaSenha = new JLabel("Confirme sua Senha:");
		lblConfirmeSuaSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblConfirmeSuaSenha, "cell 5 11");

		passwordFieldConfirm = new JPasswordField();
		passwordFieldConfirm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(passwordFieldConfirm, "cell 6 11,growx");
		passwordFieldConfirm.setEnabled(false);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnCancelar, "cell 2 17");

		JButton btnCadastrar = new JButton("Cadastrar");
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
				JOptionPane.showMessageDialog(contentPane, mensagem);
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnCadastrar, "cell 5 17,alignx right");

	}

}

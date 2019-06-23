package view.adm.usuario;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.ControllerUsuario;
import model.vo.MedicoVO;
import model.vo.UsuarioVO;
import net.miginfocom.swing.MigLayout;

public class TelaInternaCadastroUsuario extends JInternalFrame {

	private static final long serialVersionUID = 3403537686533368539L;
	private JTextField txtNome;
	private JFormattedTextField txtCpf;
	private JTextField txtEspecialidade;
	private JTextField txtCrm;
	private JFormattedTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldConfirm;
	private JComboBox cbxUsuarios;
	private ArrayList<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();
	private final DatePicker datePicker = new DatePicker();
	private JComboBox cbxTipoUsuario;
	private JButton btnCancelar;
	private MaskFormatter mascaraCpf;
	private MaskFormatter mascaraTelefone;
	private JButton btnSalvar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaCadastroUsuario frame = new TelaInternaCadastroUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInternaCadastroUsuario() {

		super("Clínica Médica - Cadastro de Usuarios", true, true, true, true);
		setBounds(100, 100, 777, 620);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[10][grow,fill][grow,fill][grow,center][grow][10]",
				"[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][10]"));

		initialize();

	}

	public void initialize() {
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraTelefone = new MaskFormatter("(##)#####-####");
		} catch (ParseException e1) {
			System.out.println("Erro ao formar máscaras: " + e1.getMessage());
		}

		JLabel lblSelecionarUsurio = new JLabel("Usuário");
		lblSelecionarUsurio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblSelecionarUsurio, "cell 2 1,alignx left,growy");

		JLabel lblUsuario = new JLabel("Usuário:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblUsuario, "flowx,cell 1 5,grow");

		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuário:");
		lblTipoDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblTipoDeUsuario, "flowx,cell 3 5,alignx center,growy");

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblSenha, "flowx,cell 1 7,grow");

		JLabel lblConfirmeSuaSenha = new JLabel("Confirme sua Senha:");
		lblConfirmeSuaSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblConfirmeSuaSenha, "flowx,cell 3 7,alignx center,growy");

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblNome, "flowx,cell 1 11,grow");

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblCpf, "cell 3 11,alignx center,growy");

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblEmail, "flowx,cell 1 13,grow");

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblTelefone, "cell 3 13,alignx center,growy");

		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblDataDeNascimento, "cell 1 15,grow");

		JLabel lblCrm = new JLabel("CRM:");
		lblCrm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblCrm, "flowx,cell 1 19,grow");

		JLabel lblEspecialidade = new JLabel("Especialidade:");
		lblEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblEspecialidade, "cell 3 19,alignx center,growy");

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		getContentPane().add(separator, "cell 1 3 4 1,grow");

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		getContentPane().add(separator_1, "cell 1 9 4 1,grow");

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBackground(Color.BLACK);
		getContentPane().add(separator_2, "cell 1 17 4 1,grow");

		cbxUsuarios = new JComboBox(usuarios.toArray());
		getContentPane().add(cbxUsuarios, "cell 3 1 2 1,grow");
		cbxUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarCampos((String) cbxTipoUsuario.getModel().getSelectedItem(),
						(UsuarioVO) cbxUsuarios.getModel().getSelectedItem());
			}
		});

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(txtUsuario, "cell 2 5,grow");
		txtUsuario.setColumns(10);
		txtUsuario.setEnabled(false);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(txtNome, "cell 2 11,grow");
		txtNome.setColumns(10);
		txtNome.setEnabled(false);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		getContentPane().add(txtEmail, "cell 2 13,grow");
		txtEmail.setEnabled(false);

		txtCrm = new JTextField();
		txtCrm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(txtCrm, "cell 2 19,grow");
		txtCrm.setColumns(10);
		txtCrm.setEnabled(false);

		txtCpf = new JFormattedTextField(mascaraCpf);
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCpf.setColumns(10);
		getContentPane().add(txtCpf, "cell 4 11,grow");
		txtCpf.setEnabled(false);

		txtTelefone = new JFormattedTextField(mascaraTelefone);
		txtTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(txtTelefone, "cell 4 13,grow");
		txtTelefone.setColumns(10);
		txtTelefone.setEnabled(false);

		txtEspecialidade = new JTextField();
		txtEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(txtEspecialidade, "cell 4 19,grow");
		txtEspecialidade.setColumns(10);
		txtEspecialidade.setEnabled(false);

		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(passwordField, "cell 2 7,grow");
		passwordField.setEnabled(false);

		passwordFieldConfirm = new JPasswordField();
		passwordFieldConfirm.setColumns(10);
		passwordFieldConfirm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(passwordFieldConfirm, "cell 4 7,grow");
		passwordFieldConfirm.setEnabled(false);

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		datePicker.setSettings(dateSettings);
		getContentPane().add(datePicker, "cell 2 15,grow");
		datePicker.setEnabled(false);

		cbxTipoUsuario = new JComboBox();
		cbxTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxTipoUsuario.setModel(new DefaultComboBoxModel(
				new String[] { UsuarioVO.NIVEL_FUNCIONARIO, UsuarioVO.NIVEL_MEDICO, UsuarioVO.NIVEL_ADMIN }));
		getContentPane().add(cbxTipoUsuario, "cell 4 5,grow");
		cbxTipoUsuario.setSelectedIndex(-1);
		cbxTipoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarCampos((String) cbxTipoUsuario.getModel().getSelectedItem(), null);
			}
		});

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(btnCancelar, "cell 1 21 2 1,grow");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(btnSalvar, "cell 3 21 2 1,grow");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensagem = "";

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
				LocalDate dataNascimento = datePicker.getDate();
				int idUsuario = 0;
				if (cbxUsuarios.getSelectedIndex() >= 0) {
					UsuarioVO usuarioVO = (UsuarioVO) cbxUsuarios.getModel().getSelectedItem();
					idUsuario = usuarioVO.getIdUsuario();
				}

				ControllerUsuario controller = new ControllerUsuario();
				mensagem = controller.salvarUsuario(nome, cpf, telefone, email, crm, especialidade, usuario, senha,
						confirmacaoSenha, nivel, dataNascimento, idUsuario);

				JOptionPane.showMessageDialog(null, mensagem);
				limparCampos();
			}
		});

		listarUsuarios();
	}

	public void limparCampos() {
		cbxUsuarios.setSelectedIndex(-1);
		cbxTipoUsuario.setSelectedIndex(-1);
		txtNome.setText("");
		txtNome.setEnabled(false);
		txtCpf.setText("");
		txtCpf.setEnabled(false);
		txtEmail.setText("");
		txtEmail.setEnabled(false);
		txtTelefone.setText("");
		txtTelefone.setEnabled(false);
		txtUsuario.setText("");
		txtUsuario.setEnabled(false);
		passwordField.setText("");
		passwordField.setEnabled(false);
		passwordFieldConfirm.setText("");
		passwordFieldConfirm.setEnabled(false);
		datePicker.setDate(LocalDate.now());
		datePicker.setEnabled(false);
		txtCrm.setText("");
		txtCrm.setEnabled(false);
		txtEspecialidade.setText("");
		txtEspecialidade.setEnabled(false);
	}

	public void atualizarCampos(String tipoUsuario, UsuarioVO usuario) {
		if (usuario != null) {
			txtNome.setText(usuario.getNome());
			txtCpf.setText(usuario.getCpf());
			txtCpf.setEnabled(false);
			txtEmail.setText(usuario.getEmail());
			txtTelefone.setText(usuario.getTelefone());
			txtUsuario.setText(usuario.getNomeUsuario());
			txtUsuario.setEnabled(false);
			passwordField.setText(usuario.getSenha());
			passwordFieldConfirm.setText(usuario.getSenha());
			datePicker.setDate(usuario.getDtNascimento());
			if (usuario.getNivel().equals(UsuarioVO.NIVEL_MEDICO)) {
				MedicoVO medico = (MedicoVO) usuario;
				txtCrm.setText(medico.getCrm());
				txtEspecialidade.setText(medico.getEspecialidade());
				txtEspecialidade.setEnabled(false);
			} else {
				txtCrm.setText("");
				txtEspecialidade.setText("");
			}
			cbxTipoUsuario.setSelectedItem(usuario.getNivel());
			cbxTipoUsuario.setEnabled(false);
			txtCrm.setEnabled(false);
		} else if (tipoUsuario != null) {
			if (tipoUsuario.equals(UsuarioVO.NIVEL_FUNCIONARIO) || tipoUsuario.equals(UsuarioVO.NIVEL_ADMIN)) {
				txtNome.setEnabled(true);
				txtCpf.setEnabled(true);
				txtEmail.setEnabled(true);
				txtTelefone.setEnabled(true);
				txtUsuario.setEnabled(true);
				passwordField.setEnabled(true);
				passwordFieldConfirm.setEnabled(true);
				txtCrm.setEnabled(false);
				txtEspecialidade.setEnabled(false);
				datePicker.setEnabled(true);
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
				datePicker.setEnabled(true);
			}
		}
	}

	public void listarUsuarios() {
		ControllerUsuario controller = new ControllerUsuario();
		usuarios = controller.listarUsuarios();
		usuarios.add(0, null);
		cbxUsuarios.setModel(new DefaultComboBoxModel(usuarios.toArray()));
		cbxUsuarios.setSelectedIndex(-1);
	}

}

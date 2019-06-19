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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
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
		getContentPane().setLayout(new MigLayout("", "[][center][center][grow]", "[][][][][][][][][][][][][][][][][][][][][][]"));

	}			

	public void initialize() {
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraTelefone = new MaskFormatter("(##)#####-####");
		} catch (ParseException e1) {
			System.out.println("Erro ao formar máscaras: " + e1.getMessage());
		}


		JLabel lblSelecionarUsurio = new JLabel("Selecionar Usuário para Editar:");
		lblSelecionarUsurio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblSelecionarUsurio, "cell 1 1,alignx left");

		JLabel lblUsuario = new JLabel("Usuário:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblUsuario, "flowx,cell 0 5,alignx left");

		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuário:");
		lblTipoDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblTipoDeUsuario, "flowx,cell 2 5,alignx left");

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblSenha, "flowx,cell 0 7,alignx left");

		JLabel lblConfirmeSuaSenha = new JLabel("Confirme sua Senha:");
		lblConfirmeSuaSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblConfirmeSuaSenha, "flowx,cell 2 7,alignx left");

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblNome, "flowx,cell 0 11");

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblCpf, "cell 2 11,alignx left");

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblEmail, "flowx,cell 0 13");

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblTelefone, "cell 2 13,alignx left");

		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblDataDeNascimento, "cell 0 15");

		JLabel lblCrm = new JLabel("CRM:");
		lblCrm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblCrm, "flowx,cell 0 19,alignx left");

		JLabel lblEspecialidade = new JLabel("Especialidade:");
		lblEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblEspecialidade, "flowx,cell 3 19,alignx left");

		cbxUsuarios = new JComboBox(usuarios.toArray());
		getContentPane().add(cbxUsuarios, "cell 2 1 2 1,growx");
		cbxUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarCampos((String) cbxTipoUsuario.getModel().getSelectedItem(),
						(UsuarioVO) cbxUsuarios.getModel().getSelectedItem());
			}
		});

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		getContentPane().add(separator, "cell 0 3 4 1,grow");

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		getContentPane().add(separator_1, "cell 0 9 4 1,grow");

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBackground(Color.BLACK);
		getContentPane().add(separator_2, "cell 0 17 4 1,grow");

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(txtUsuario, "cell 1 5,alignx left");
		txtUsuario.setColumns(10);
		txtUsuario.setEnabled(false);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(txtNome, "cell 1 11,alignx left");
		txtNome.setColumns(10);
		txtNome.setEnabled(false);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		getContentPane().add(txtEmail, "cell 1 13,alignx left");
		txtEmail.setEnabled(false);

		txtCrm = new JTextField();
		txtCrm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(txtCrm, "cell 1 19,alignx left");
		txtCrm.setColumns(10);
		txtCrm.setEnabled(false);

		txtCpf = new JFormattedTextField(mascaraCpf);
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCpf.setColumns(10);
		getContentPane().add(txtCpf, "cell 3 11,alignx left");
		txtCpf.setEnabled(false);

		txtTelefone = new JFormattedTextField(mascaraTelefone);
		txtTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(txtTelefone, "cell 3 13,aligny top");
		txtTelefone.setColumns(10);
		txtTelefone.setEnabled(false);

		txtEspecialidade = new JTextField();
		txtEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(txtEspecialidade, "cell 3 19,alignx center,aligny top");
		txtEspecialidade.setColumns(10);
		txtEspecialidade.setEnabled(false);

		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(passwordField, "cell 1 7,alignx left");
		passwordField.setEnabled(false);

		passwordFieldConfirm = new JPasswordField();
		passwordFieldConfirm.setColumns(10);
		passwordFieldConfirm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(passwordFieldConfirm, "cell 3 7,alignx left");
		passwordFieldConfirm.setEnabled(false);

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		datePicker.setSettings(dateSettings);
		getContentPane().add(datePicker, "cell 1 15");
		datePicker.setEnabled(false);

		cbxTipoUsuario = new JComboBox();
		cbxTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxTipoUsuario.setModel(new DefaultComboBoxModel(new String[] { UsuarioVO.NIVEL_FUNCIONARIO, UsuarioVO.NIVEL_MEDICO, UsuarioVO.NIVEL_ADMIN }));
		getContentPane().add(cbxTipoUsuario, "cell 3 5,growx");
		cbxTipoUsuario.setSelectedIndex(-1);
		cbxTipoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarCampos((String) cbxTipoUsuario.getModel().getSelectedItem(), null);
			}
		});

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(btnCancelar, "cell 1 21,alignx center,growy");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(btnSalvar, "cell 2 21,alignx center,growy");
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
				if (cbxUsuarios.getSelectedIndex() > -1) {
					idUsuario = cbxUsuarios.getSelectedIndex() + 1;
				}

				ControllerUsuario controller = new ControllerUsuario();
				mensagem = controller.salvarUsuario(nome, cpf, telefone, email, crm, especialidade, usuario, senha,
						confirmacaoSenha, nivel, dataNascimento, idUsuario);

				JOptionPane.showMessageDialog(null, mensagem);
			}
		});
	}

	public void atualizarCampos(String tipoUsuario, UsuarioVO usuario) {
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
		if (usuario != null) {
			txtNome.setText(usuario.getNome());
			txtCpf.setText(usuario.getCpf());
			txtEmail.setText(usuario.getEmail());
			txtTelefone.setText(usuario.getTelefone());
			txtUsuario.setEnabled(false);
			txtUsuario.setText(usuario.getNomeUsuario());
			passwordField.setEnabled(false);
			passwordFieldConfirm.setEnabled(false);
			datePicker.setDate(usuario.getDtNascimento());
			cbxTipoUsuario.setSelectedItem(usuario.getNivel());
			if (tipoUsuario.equals(UsuarioVO.NIVEL_MEDICO)) {
				MedicoVO medico = (MedicoVO) usuario;
				txtCrm.setText(medico.getCrm());
				txtEspecialidade.setText(medico.getEspecialidade());
			}
		}
	}

	public void listarUsuarios() {
		ControllerUsuario controller = new ControllerUsuario();
		usuarios = controller.listarUsuarios();
		cbxUsuarios.setModel(new DefaultComboBoxModel(usuarios.toArray()));
		cbxUsuarios.setSelectedIndex(-1);
	}
}

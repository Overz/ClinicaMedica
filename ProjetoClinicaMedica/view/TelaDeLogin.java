package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.ControllerUsuario;
import model.vo.FuncionarioVO;
import model.vo.MedicoVO;
import model.vo.UsuarioVO;
import net.miginfocom.swing.MigLayout;
import view.adm.usuario.TelaCadastroUsuario;

public class TelaDeLogin extends JFrame {

	private JPasswordField passwordField;
	private JTextField txtUsuario;

	private JButton btnLogin;
	private JButton btnNovoUsuario;
	private static TelaCadastroUsuario telaCadastroUsuario;

	
	private static TelaGeral telaGeral = new TelaGeral();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeLogin window = new TelaDeLogin();
					window.setVisible(true);
					telaGeral.setVisible(false);
					telaGeral.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaDeLogin() {
			
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		int y = (int) (height * 0.35);
		int x = (int) (width * 0.35);
		
		setTitle("Clinica Médica");
		setBounds(0, 0, 496, 359);
		//setBounds(x, y, 496, 359);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[10][183px,grow][grow][grow][10]", "[grow][grow][grow][25px,grow][29px,grow][grow][grow][grow][grow][grow][grow][grow][10]"));

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JLabel lblUsuario = new JLabel("Usuário:");
		lblUsuario.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(lblUsuario, "cell 1 3,alignx center,growy");
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(lblSenha, "flowx,cell 1 5,alignx center,growy");

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Verdana", Font.PLAIN, 16));
		getContentPane().add(txtUsuario, "cell 3 3,grow");
		txtUsuario.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Verdana", Font.PLAIN, 16));
		getContentPane().add(passwordField, "cell 3 5,grow");
		
		btnNovoUsuario = new JButton("Novo Usuário");
		btnNovoUsuario.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(btnNovoUsuario, "cell 1 8,grow");
		btnNovoUsuario.addActionListener(e -> {

			try {
				telaCadastroUsuario = new TelaCadastroUsuario();
				this.setVisible(false);
				this.dispose();
				this.repaint();
				
				telaCadastroUsuario.setVisible(true);
				telaCadastroUsuario.show();
				telaCadastroUsuario.repaint();
				
				
				
			} catch (Exception e2) {
				System.out.println("Erro ao abrir Janela Interna de Cadastrar Usuario.");
				System.out.println(e2.getMessage());
			}
		});

		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(btnLogin, "cell 3 8,grow");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ControllerUsuario controllerUsuario = new ControllerUsuario();

				String usuario = txtUsuario.getText();
				String senha = new String(passwordField.getPassword());
				UsuarioVO vo = controllerUsuario.login(usuario, senha);

				if (vo != null) {
					if (vo.getNivel() == vo.NIVEL_MEDICO) {
						JOptionPane.showMessageDialog(null, "Login efetuado com sucesso!\nMédico " + vo.getNome());
						MedicoVO medico = (MedicoVO) vo;
						// TODO Chamar tela de médico
					} else if (vo.getNivel() == vo.NIVEL_FUNCIONARIO) {
						JOptionPane.showMessageDialog(null, "Login efetuado com sucesso!\nFuncionário " + vo.getNome());
						FuncionarioVO funcionario = (FuncionarioVO) vo;
						// TODO Chamar tela de funcionário
					} else if (vo.getNivel() == vo.NIVEL_ADMIN) {
						JOptionPane.showMessageDialog(null, "Login efetuado com sucesso!");
						// TODO Chamar tela de administrador
					}

				} else {
					JOptionPane.showMessageDialog(null, "Usuario e/ou senha inválidos.");
				}
			}
		});
	}
}

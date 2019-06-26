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
import model.vo.UsuarioVO;
import net.miginfocom.swing.MigLayout;

public class TelaDeLogin extends JFrame {

	private static final long serialVersionUID = -3175900965023551054L;

	private JPasswordField passwordField;
	private JTextField txtUsuario;

	private JButton btnLogin;
	private static TelaDeLogin window;
	private static TelaGeral telaGeral;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new TelaDeLogin();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaDeLogin() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		int y = (int) (height * 0.35);
		int x = (int) (width * 0.35);

		setTitle("Clinica Médica - Login");
		// setBounds(0, 0, 496, 359);
		setBounds(x, y, 496, 359); // Caso de problemas no Design(modo grafico, comente esta linha, e remova o comentario acima.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[10][183px,grow][grow][10]",
				"[grow][grow][grow][25px,grow][29px,grow][grow][grow][grow][grow][grow][grow][grow]"));

		initialize();
	}

	private void initialize() {

		JLabel lblUsuario = new JLabel("Usuário:");
		lblUsuario.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(lblUsuario, "cell 1 3,alignx center,growy");

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(lblSenha, "flowx,cell 1 5,alignx center,growy");

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Verdana", Font.PLAIN, 16));
		getContentPane().add(txtUsuario, "cell 2 3,grow");
		txtUsuario.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Verdana", Font.PLAIN, 16));
		getContentPane().add(passwordField, "cell 2 5,grow");

		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(btnLogin, "cell 1 8 2 1,grow");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ControllerUsuario controllerUsuario = new ControllerUsuario();

				String usuario = txtUsuario.getText();
				String senha = new String(passwordField.getPassword());
				UsuarioVO vo = controllerUsuario.login(usuario, senha);

				if (vo != null) {
					JLabel aprovado = new JLabel("Login efetuado com Sucesso!");
					aprovado.setFont(new Font("Verdana", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(window, aprovado);
					telaGeral = new TelaGeral(vo);
					telaGeral.setExtendedState(MAXIMIZED_BOTH);
					dispose();
					telaGeral.setVisible(true);

				} else {
					JLabel negado = new JLabel("Usuário e/ou senha Invádliso!");
					negado.setFont(new Font("Verdana", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(window, negado);
				}
			}
		});
	}
}

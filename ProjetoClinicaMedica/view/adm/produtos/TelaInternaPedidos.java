package view.adm.produtos;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TelaInternaPedidos extends JInternalFrame {

	/**
	 * serial da classe, tela static.
	 */
	private static final long serialVersionUID = 4729491226827126006L;
	private static TelaInternaPedidos window;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new TelaInternaPedidos();
					window.setVisible(true);
					window.setClosable(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Atributos da tela.
	 */

	
	/**
	 * Create the application.
	 */
	public TelaInternaPedidos() {
		setBounds(100, 100, 937, 676);
		this.setClosable(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblIdDoPedido = new JLabel("Id do pedido:");
		lblIdDoPedido.setBounds(10, 11, 96, 28);
		getContentPane().add(lblIdDoPedido);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(116, 11, 141, 28);
		getContentPane().add(textField);
		textField.setColumns(10);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	}
}

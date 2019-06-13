package view.funcionarios.medico;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public class TelaInternaBuscarProntuario extends JInternalFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaBuscarProntuario window = new TelaInternaBuscarProntuario();
					window.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaInternaBuscarProntuario() {
		setBounds(100, 100, 450, 300);
		setClosable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
	
	
	}
	
}

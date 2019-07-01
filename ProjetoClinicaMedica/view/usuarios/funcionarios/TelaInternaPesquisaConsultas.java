package view.usuarios.funcionarios;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class TelaInternaPesquisaConsultas extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TelaInternaPesquisaConsultas frame = new TelaInternaPesquisaConsultas();
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
	public TelaInternaPesquisaConsultas() {
		setBounds(100, 100, 450, 300);

	}

}

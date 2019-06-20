package view.adm.funcionario;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.WindowConstants;

public class TelaInternaAtualizarFuncionario extends JInternalFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaAtualizarFuncionario window = new TelaInternaAtualizarFuncionario();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInternaAtualizarFuncionario() {
		super("Clínica Médica - Atualizar Dados de Funcionarios", false, true, false, false);
		setBounds(100, 100, 845, 585);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

		initialize();
	}

	private void initialize() {
	
		
	
	
	}

}

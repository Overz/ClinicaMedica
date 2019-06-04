package view.funcionarios.secretaria;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class TelaInternaCadastroPacienteEndereco extends JInternalFrame {
	private JTextField txtBairro;
	private JTextField txtRua;
	private JTextField textField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaCadastroPacienteEndereco window = new TelaInternaCadastroPacienteEndereco();
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
	public TelaInternaCadastroPacienteEndereco() {
		setBounds(100, 100, 787, 708);
		setClosable(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[][47.00,grow][]", "[][][][][][][][][]"));
		
		JLabel lblInformacoes = new JLabel("Informações >");
		getContentPane().add(lblInformacoes, "flowx,cell 0 0,grow");
		
		JLabel lblEndereco = new JLabel("Endereço >");
		getContentPane().add(lblEndereco, "cell 0 0,grow");
		
		JLabel lblContato = new JLabel("Contato");
		getContentPane().add(lblContato, "cell 0 0");
		
		JLabel lblEstado = new JLabel("Estado:");
		getContentPane().add(lblEstado, "cell 0 2,alignx center,growy");
		
		JComboBox cbEstado = new JComboBox();
		getContentPane().add(cbEstado, "cell 1 2,growx");
		
		JLabel lblCidade = new JLabel("Cidade:");
		getContentPane().add(lblCidade, "cell 0 3,alignx center,growy");
		
		JComboBox cbCidade = new JComboBox();
		getContentPane().add(cbCidade, "cell 1 3,growx");
		
		JLabel lblBairro = new JLabel("Bairro:");
		getContentPane().add(lblBairro, "cell 0 4,alignx trailing,growy");
		
		txtBairro = new JTextField();
		getContentPane().add(txtBairro, "cell 1 4,growx");
		txtBairro.setColumns(10);
		
		JLabel lblRua = new JLabel("Rua:");
		getContentPane().add(lblRua, "cell 0 5,alignx trailing,growy");
		
		txtRua = new JTextField();
		getContentPane().add(txtRua, "cell 1 5,growx");
		txtRua.setColumns(10);
		
		JLabel lblNumero = new JLabel("Numero:");
		getContentPane().add(lblNumero, "cell 0 6,alignx trailing,growy");
		
		textField = new JTextField();
		getContentPane().add(textField, "cell 1 6,growx");
		textField.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP:");
		getContentPane().add(lblCep, "cell 0 7,alignx center,growy");
		
		JLabel lblComplemento = new JLabel("Complemento");
		getContentPane().add(lblComplemento, "cell 0 8,alignx center,growy");
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	}

}

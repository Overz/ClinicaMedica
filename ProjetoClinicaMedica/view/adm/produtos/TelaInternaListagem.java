package view.adm.produtos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.SwingConstants;

public class TelaInternaListagem extends JInternalFrame {
	
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JDateChooser dataChooserInicial;
	private Component dataChooserFinal;
	private JButton btnPesquisar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaListagem window = new TelaInternaListagem();
					window.setVisible(true);
					window.setClosable(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaInternaListagem() {
		setBounds(100, 100, 796, 607);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		super.setClosable(true);
		getContentPane().setLayout(new MigLayout("", "[154px][10px][119px][10px][129px][10px][328px]", "[26px,grow][27px,grow][47px][grow][grow][420px,grow]"));
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		JLabel lblNomeDoProduto = new JLabel("Nome do Produto:");
		getContentPane().add(lblNomeDoProduto, "cell 4 0,grow");

		JLabel lblId = new JLabel("ID:");
		getContentPane().add(lblId, "cell 0 0,grow");

		JLabel lblData = new JLabel("Data de Inicio:");
		getContentPane().add(lblData, "cell 0 1,grow");

		JLabel lblDataFinal = new JLabel("Data Final:");
		getContentPane().add(lblDataFinal, "cell 4 1,grow");


		textField = new JTextField();
		getContentPane().add(textField, "cell 6 0,growx,aligny center");
		textField.setColumns(10);

		textField_1 = new JTextField();
		getContentPane().add(textField_1, "cell 2 0,growx,aligny center");
		textField_1.setColumns(10);

		dataChooserInicial = new JDateChooser();
		getContentPane().add(dataChooserInicial, "cell 2 1,grow");


		dataChooserFinal = new JDateChooser();
		getContentPane().add(dataChooserFinal, "cell 6 1,alignx left,growy");

		btnPesquisar = new JButton("Pesquisar");
		getContentPane().add(btnPesquisar, "cell 4 2,grow");

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		getContentPane().add(separator, "cell 0 3 7 1,grow");

		table = new JTable();
		getContentPane().add(table, "cell 0 4 7 2,grow");

	}
}

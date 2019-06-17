package view.funcionarios.medico;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import com.toedter.calendar.JDateChooser;

import controller.ControllerMedico;
import model.vo.MedicoVO;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

public class TelaInternaMedico extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaMedico window = new TelaInternaMedico();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JDateChooser dateChooser;
	private ControllerMedico controller;
	private JLabel lblData;
	private JButton btnPesquisarConsulta;
	private DefaultListModel<Object> lista = new DefaultListModel<Object>();
	private JTable table;

	/**
	 * Create the application.
	 */
	public TelaInternaMedico() {
		setResizable(true);
		setClosable(true);
		setBounds(100, 100, 812, 621);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][]", "[grow][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][10]"));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		Object[][] data = new Object[][] { {  "Nome", "Data", "Hora", "Causa" }, };
		Object[] columnNames = new String[] { "Nome", "Data", "Hora", "Causa" };
				
				lblData = new JLabel("Data:");
				getContentPane().add(lblData, "cell 1 0,alignx center,growy");
		
				dateChooser = new JDateChooser();
				dateChooser.setToolTipText("Selecione a Data para Consulta");
				getContentPane().add(dateChooser, "cell 2 0 2 1,grow");
		
		btnPesquisarConsulta = new JButton("Pesquisar Consultas");
		btnPesquisarConsulta.setToolTipText("Selecione uma Linha, e Pesquise dados mais especifos.");
		getContentPane().add(btnPesquisarConsulta, "cell 5 0,grow");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(data, columnNames));
		getContentPane().add(table, "cell 1 1 7 10,grow");
	}

/*	protected void atualizarTabela(ArrayList<MedicoVO> medicos) {
		//DefaultTableModel model = (DefaultTableModel) table.getModel();

		Object novaLinha[] = new Object[5];
		for(MedicoVO medicoVO: medicos){
			/*novaLinha[0] = medicoVO.getCliente().getNome();
			novaLinha[1] = medicoVO.getCliente().getHoraConsulta();
			novaLinha[2] = medicoVO.getCliente().getCpf();
			novaLinha[3] = medicoVO.getCliente().getTelefone();
			novaLinha[4] = medicoVO.getNome();
			novaLinha[5] = medicoVO.getEspecialidade();

			model.addRow(novaLinha);
		}
	}

	public void limparTabela() {
		table.setModel(new DefaultTableModel(
				new Object[][] {{"Placa", "Modelo", "Ano", "Valor"}},
				new String[] {"Placa", "Modelo", "Ano", "Valor"}));
	}
*/
	
}

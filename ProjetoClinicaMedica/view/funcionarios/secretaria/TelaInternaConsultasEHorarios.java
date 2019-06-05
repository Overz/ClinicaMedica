package view.funcionarios.secretaria;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controller.ControllerMedico;
import model.vo.MedicoVO;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JComponent;

public class TelaInternaConsultasEHorarios extends JInternalFrame {

	private JTable table;
	private ControllerMedico controller;
	private Date data;
	private JTextField txtNome;
	private JComboBox cbEspecialidade;
	private JComponent dateChooser;
	private JButton btnPesquisarAgenda;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaConsultasEHorarios window = new TelaInternaConsultasEHorarios();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInternaConsultasEHorarios() {
		setTitle("Tela Consulta");
		setResizable(true);
		setBounds(100, 100, 821, 609);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[][grow][100px:100px:100px,grow][grow][grow][pref!,grow][100px:100px:100px,grow][grow][]", "[38,grow][38,grow,fill][38,grow][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill]"));
		initialize();
	}

	/**
	 * Tela Principal da Receção.
	 */
	private void initialize() {

		JLabel lblNome = new JLabel("Nome:");
		getContentPane().add(lblNome, "cell 1 0,alignx right,growy");

		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 2 0 2 1,grow");
		txtNome.setColumns(10);

		Object[][] data = new Object[][] { {  "Nome", "Data-Hora", "Telefone", "Médico", "Especialidade" }, };
		Object[] columnNames = new String[] { "Nome", "Data-Hora", "Telefone", "Médico", "Especialidade" };


		dateChooser = new JDateChooser();
		dateChooser.setToolTipText("Selecione a Data para Consulta");
		getContentPane().add(dateChooser, "cell 4 0 2 1,grow");

		cbEspecialidade = new JComboBox();
		getContentPane().add(cbEspecialidade, "cell 6 0 2 1,grow");

		btnPesquisarAgenda = new JButton("Pesquisar Medico/Consulta");
		getContentPane().add(btnPesquisarAgenda, "cell 3 1 3 1,grow");
		btnPesquisarAgenda.addActionListener(e -> {

			controller = new ControllerMedico();

			Date date = ((JDateChooser) dateChooser).getDate();
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

			//ArrayList<MedicoVO> vo = controller.consultarData(data);
			//atualizarTabelaCarros(vo);

		});

		table = new JTable();
		getContentPane().add(table, "cell 1 3 7 8,grow");
		table.setModel(new DefaultTableModel(data, columnNames));


	}

	protected void atualizarTabela(ArrayList<MedicoVO> medicos) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		Object novaLinha[] = new Object[5];
		for(MedicoVO medicoVO: medicos){
			/*novaLinha[0] = medicoVO.getCliente().getNome();
			novaLinha[1] = medicoVO.getCliente().getHoraConsulta();
			novaLinha[2] = medicoVO.getCliente().getCpf();
			novaLinha[3] = medicoVO.getCliente().getTelefone();
			novaLinha[4] = medicoVO.getNome();
			novaLinha[5] = medicoVO.getEspecialidade();

			model.addRow(novaLinha);*/
		}
	}

	public void limparTabela() {
		table.setModel(new DefaultTableModel(
				new Object[][] {{"Placa", "Modelo", "Ano", "Valor"}},
				new String[] {"Placa", "Modelo", "Ano", "Valor"}));
	}

}

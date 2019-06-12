package view.funcionarios.secretaria;

import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controller.ControllerMedico;
import model.vo.MedicoVO;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaInternaConsultasEHorarios extends JInternalFrame {

	private JTable table;
	private ControllerMedico controller;
	private Date data;
	private JComboBox cbOpcaoPesquisa;
	private JComponent dateChooser;
	private JButton btnPesquisarPorCampos;
	private JFormattedTextField ftfCampoCpfCrm;
	private JButton btnNewButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TelaInternaConsultasEHorarios window = new TelaInternaConsultasEHorarios();
					window.setVisible(true);
					//window.setBorder(null);
					window.setMaximizable(true);
					
					//window.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInternaConsultasEHorarios() {

		setTitle("Tela Consulta");
		setResizable(false);
		setBounds(100, 100, 821, 609);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[][grow][100px:100px:100px,grow][grow][grow][pref!,grow][100px:100px:100px,grow][grow][]", "[38,grow][38,grow,fill][38,grow][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill][38,grow,fill]"));
		initialize();
	}

	/**
	 * Tela Principal da Receção.
	 */
	private void initialize() {

		JLabel lblNome = new JLabel("Pesquisar por:");
		getContentPane().add(lblNome, "cell 1 0,alignx right,growy");

		dateChooser = new JDateChooser();
		dateChooser.setToolTipText("Selecione a Data para Consulta");
		getContentPane().add(dateChooser, "cell 4 0 2 1,grow");

		Object[][] data = new Object[][] { {  "Nome", "Data", "Hora", "Telefone", "Médico", "Especialidade" }, };
		Object[] columnNames = new String[] { "Nome", "Data", "Hora", "Telefone", "Médico", "Especialidade" };

		cbOpcaoPesquisa = new JComboBox();
		cbOpcaoPesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//TODO adc método validarCampos()
				
			}
		});
		cbOpcaoPesquisa.setModel(new DefaultComboBoxModel(new String[] {"Nome (Paciente/Médico)", "CPF ou CRM"}));
		getContentPane().add(cbOpcaoPesquisa, "cell 2 0 2 1,grow");
		
		//verificarCampos();
		
		ftfCampoCpfCrm = new JFormattedTextField();
		ftfCampoCpfCrm.setVisible(false);
		ftfCampoCpfCrm.setEditable(false);
		ftfCampoCpfCrm.setEnabled(false);
		
		//verificarCampos();
		
		ftfCampoCpfCrm.setToolTipText("Digite o CPF do Paciente ou Médico");
		getContentPane().add(ftfCampoCpfCrm, "cell 2 1 4 1,grow");

		btnPesquisarPorCampos = new JButton("Pesquisar Medico/Consulta");
		getContentPane().add(btnPesquisarPorCampos, "cell 3 2 2 1,grow");
		btnPesquisarPorCampos.addActionListener(e -> {

			controller = new ControllerMedico();

			Date date = ((JDateChooser) dateChooser).getDate();
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

			//ArrayList<MedicoVO> vo = controller.consultarData(data);
			//atualizarTabelaCarros(vo);
		});

		table = new JTable();
		getContentPane().add(table, "cell 1 3 7 8,grow");
		table.setModel(new DefaultTableModel(data, columnNames));

		btnNewButton = new JButton("Pesquisa Especifica");
		btnNewButton.setToolTipText("Selecione uma Linha, e Pesquise dados mais especifos.");
		getContentPane().add(btnNewButton, "cell 3 11 2 1,grow");
	}
	
	public void verificarCampos_() {
		//TODO implementar
		
		if (cbOpcaoPesquisa.getSelectedIndex() == 1) {
			ftfCampoCpfCrm.setEnabled(true);
			ftfCampoCpfCrm.setEditable(true);
			ftfCampoCpfCrm.setVisible(true);
		} else {
			ftfCampoCpfCrm.setEnabled(false);
			ftfCampoCpfCrm.setEditable(false);
			ftfCampoCpfCrm.setVisible(false);
		}
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

package view.funcionarios.medico;

import java.awt.EventQueue;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.ControllerMedico;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;

public class TelaInternaMedico extends JInternalFrame {

	private static final long serialVersionUID = -5426592037700061418L;
	private JLabel lblData;
	private JButton btnPesquisarConsulta;
	private JTable table;
	private final DatePicker datePicker = new DatePicker();

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

	public TelaInternaMedico() {
		super("Clínica Médica - Horario das Consultas", false, true, false, false);
		setBounds(100, 100, 812, 621);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[10][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][10]", "[grow][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][10]"));

		initialize();
	}

	private void initialize() {

		lblData = new JLabel("Data:");
		lblData.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblData, "cell 1 0,alignx center,growy");

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		datePicker.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 20));
		datePicker.getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 20));

		datePicker.setSettings(dateSettings);
		getContentPane().add(datePicker, "cell 2 0 2 1,grow");

		btnPesquisarConsulta = new JButton("Pesquisar Consultas");
		btnPesquisarConsulta.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnPesquisarConsulta.setToolTipText("Selecione uma Linha, e Pesquise dados mais especifos.");
		getContentPane().add(btnPesquisarConsulta, "cell 4 0 3 1,grow");
		btnPesquisarConsulta.addActionListener(e -> {

			try {
				ControllerMedico controller = new ControllerMedico();
				LocalDate date = datePicker.getDate();
				String mensagem = controller.validarTelaInternaMedico(date);
			
				if (mensagem != null) {
					JOptionPane.showMessageDialog(null, mensagem);
				} else {
					//TODO Trazer as Consultas do médico logado.
				}

			} catch (NullPointerException e2) {
				System.out.println("Tela: Tela Interna Médico. Erro ao validar o campo Data.\n" + e2.getMessage());
			}

		});

		Object[][] data = new Object[][] { { "Nome", "Data", "Hora", "Causa/Motivo" }, };
		Object[] columnNames = new String[] { "Nome", "Data", "Hora", "Causa/Motivo" };

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setModel(new DefaultTableModel(data, columnNames));
		getContentPane().add(table, "cell 1 1 7 10,grow");
	}

	/*
	 * protected void atualizarTabela(ArrayList<MedicoVO> medicos) {
	 * //DefaultTableModel model = (DefaultTableModel) table.getModel();
	 * 
	 * Object novaLinha[] = new Object[5]; for(MedicoVO medicoVO: medicos){
	 * /*novaLinha[0] = medicoVO.getCliente().getNome(); novaLinha[1] =
	 * medicoVO.getCliente().getHoraConsulta(); novaLinha[2] =
	 * medicoVO.getCliente().getCpf(); novaLinha[3] =
	 * medicoVO.getCliente().getTelefone(); novaLinha[4] = medicoVO.getNome();
	 * novaLinha[5] = medicoVO.getEspecialidade();
	 * 
	 * model.addRow(novaLinha); } }
	 * 
	 * public void limparTabela() { table.setModel(new DefaultTableModel( new
	 * Object[][] {{"Placa", "Modelo", "Ano", "Valor"}}, new String[] {"Placa",
	 * "Modelo", "Ano", "Valor"})); }
	 */
}

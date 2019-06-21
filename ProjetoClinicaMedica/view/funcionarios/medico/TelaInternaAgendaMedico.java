package view.funcionarios.medico;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.ControllerConsulta;
import model.vo.ConsultaVO;
import model.vo.MedicoVO;
import model.vo.UsuarioVO;
import net.miginfocom.swing.MigLayout;

public class TelaInternaAgendaMedico extends JInternalFrame {

	private static final long serialVersionUID = -1343244694236177024L;
	private JButton btnCadastrar;
	private JButton btnCancelar;
	private JButton btnAlterarAgenda;
	private JFormattedTextField ftfNomeMedico;
	private MaskFormatter mascaraNome;
	private MaskFormatter mascaraTelefone;
	private MaskFormatter mascaraEmail;
	private final DatePicker datePicker = new DatePicker();
	private JLabel lblData;
	private MedicoVO medico;
	private JTable tblConsultas;
	private ArrayList<ConsultaVO> listaConsultas;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaAgendaMedico window = new TelaInternaAgendaMedico();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInternaAgendaMedico() {
		super("Clínica Médica - Agenda Médica", false, true, false, false);
		setBounds(100, 100, 1077, 783);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		getContentPane()
				.setLayout(new MigLayout("", "[10][124px,grow][10][172px,grow][10][124px,grow][10][177px,grow][10]",
						"[31px,grow][31px,grow][34px,grow][][265px,grow][68px,grow][15]"));
		this.repaint();

		initialize();
	}

	private void initialize() {

		// TODO tentar:
		// - Cadastrar primeira vez, na segunda vez, ao abrir, os dados ja vem descritos
		// nos campos, para atualizar.

		try {
			mascaraNome = new MaskFormatter(
					"LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
			mascaraEmail = new MaskFormatter("************************************************");
			mascaraTelefone = new MaskFormatter("(##)#########");
		} catch (ParseException e1) {
			System.out.println("Tela: Agenda Médica. Erro ao Criar a Mascara.\n" + e1.getMessage());
		}

		JLabel lblMedico = new JLabel("Médico:");
		lblMedico.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblMedico, "cell 1 1,grow");

		ftfNomeMedico = new JFormattedTextField(mascaraNome);
		ftfNomeMedico.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(ftfNomeMedico, "cell 3 1,grow");

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		lblData = new JLabel("Data:");
		lblData.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblData, "cell 5 2");
		datePicker.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 22));
		datePicker.getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 22));

		datePicker.setSettings(dateSettings);
		datePicker.setToolTipText("Selecione a Data");
		getContentPane().add(datePicker, "cell 7 2,grow");
		datePicker.setDate(LocalDate.now());
		// consultarAgendamentos(datePicker.getDate());

		tblConsultas = new JTable();
		tblConsultas.setModel(new DefaultTableModel(new String[][] { { "Horário", "Médico", "Paciente" }, },
				new String[] { "Horário", "Médico", "Paciente" }));
		DefaultTableModel modelo = (DefaultTableModel) tblConsultas.getModel();
		int linhas = 9;
		int horario = 8;
		getContentPane().add(tblConsultas, "cell 1 4 7 1,grow");
		for (int i = 0; i <= linhas; i++) {
			if (horario != 12) {
				String[] novaLinha = new String[] { LocalTime.of(horario, 0).toString(), "", "" };
				modelo.addRow(novaLinha);
				horario++;
			} else {
				horario++;
			}
		}

		btnCadastrar = new JButton("Cadastrar Agenda");
		btnCadastrar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnCadastrar, "cell 3 5,grow");
		btnCadastrar.addActionListener(e -> {

		});

		btnAlterarAgenda = new JButton("Alterar Agenda");
		btnAlterarAgenda.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnAlterarAgenda, "cell 5 5,grow");
		btnAlterarAgenda.addActionListener(e -> {

		});

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnCancelar, "cell 7 5,grow");
		btnCadastrar.addActionListener(e -> {

			ftfNomeMedico.setText("");

			this.dispose();
		});

		this.repaint();

	}

	private void consultarAgendamentos(LocalDate data) {
		ControllerConsulta controller = new ControllerConsulta();
		listaConsultas = controller.pesquisarConsultasPorDataEMedico(data, medico);
	}

	private void preencherListaAgendamentos(List<ConsultaVO> listaConsultas) {

	}

	public void setMedico(UsuarioVO usuario) {
		this.medico = (MedicoVO) usuario;
	}
}

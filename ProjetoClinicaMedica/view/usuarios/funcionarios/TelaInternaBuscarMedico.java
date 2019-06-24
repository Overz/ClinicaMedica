package view.usuarios.funcionarios;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.ControllerMedico;
import model.seletor.SeletorUsuario;
import model.vo.MedicoVO;
import model.vo.UsuarioVO;
import net.miginfocom.swing.MigLayout;
import util.TableModels.UsuariosTableModel;

public class TelaInternaBuscarMedico extends JInternalFrame {

	private JTable tblMedicos;
	private JFormattedTextField txtCpf;
	private JButton btnLimpar;
	private JButton btnPesquisar;
	private JLabel lblDataDeNascimento;
	private JTextField txtNome;
	private final DatePicker datePicker = new DatePicker();
	private JLabel lblCrm;
	private JTextField txtCrm;
	private JButton btnCancelar;
	private ArrayList<UsuarioVO> usuarios;
	private JLabel lblEspecialidade;
	private JTextField txtEspecialidade;
	private JComboBox cbxNivel;
	private JLabel lblNivel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaBuscarMedico frame = new TelaInternaBuscarMedico();
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
	public TelaInternaBuscarMedico() {
		super("Clinica Médica - Exclusão de Usuario", false, true, true, false);
		setBounds(100, 100, 840, 638);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[10][1px][150px][50px:50px:50px][200px:200px:200px,grow][10]",
				"[40][40][40][40][][grow][40][10]"));

		initialize();
	}

	private void initialize() {

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblNome, "cell 1 0,grow");

		txtNome = new JFormattedTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(txtNome, "cell 2 0,grow");

		lblNivel = new JLabel("Nível:");
		lblNivel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblNivel, "cell 3 0,alignx trailing");

		cbxNivel = new JComboBox();
		cbxNivel.setModel(new DefaultComboBoxModel(
				new String[] { "", UsuarioVO.NIVEL_FUNCIONARIO, UsuarioVO.NIVEL_MEDICO, UsuarioVO.NIVEL_ADMIN }));
		getContentPane().add(cbxNivel, "cell 4 0,grow");
		cbxNivel.setSelectedIndex(-1);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblCpf, "cell 1 1,grow");

		MaskFormatter mascaraCpf;
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			txtCpf = new JFormattedTextField(mascaraCpf);
			txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 20));
			getContentPane().add(txtCpf, "cell 2 1,grow");
		} catch (ParseException e1) {
			System.out.println("Erro ao criar máscara de CPF: " + e1.getMessage());
		}

		lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblDataDeNascimento, "cell 1 2");

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		datePicker.setSettings(dateSettings);
		getContentPane().add(datePicker, "cell 2 2, grow");
		datePicker.setEnabled(false);

		lblCrm = new JLabel("CRM:");
		lblCrm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblCrm, "cell 1 3,alignx left");

		txtCrm = new JFormattedTextField();
		txtCrm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(txtCrm, "cell 2 3,grow");

		btnLimpar = new JButton("Limpar Campos");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnLimpar, "cell 4 3,grow");
		btnLimpar.addActionListener(e -> {
			txtNome.setText("");
			txtCpf.setText("");
			txtCrm.setText("");
			txtEspecialidade.setText("");
			datePicker.setDate(null);
		});

		lblEspecialidade = new JLabel("Especialidade:");
		lblEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblEspecialidade, "cell 1 4,alignx left");

		txtEspecialidade = new JFormattedTextField();
		txtEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(txtEspecialidade, "cell 2 4,growx");

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnPesquisar, "cell 4 4,grow");
		btnPesquisar.addActionListener(e -> {
			consultarMedicos();
		});

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 1 5 4 1,grow");

		tblMedicos = new JTable();
		tblMedicos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		UsuariosTableModel usuariosTableModel = new UsuariosTableModel();
		tblMedicos.setModel(usuariosTableModel);
		scrollPane.setViewportView(tblMedicos);
		tblMedicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnCancelar, "cell 2 6,alignx right");
		btnCancelar.addActionListener(e -> {
			this.dispose();
		});

		JButton btnSelecionarMedico = new JButton("Selecionar Médico");
		btnSelecionarMedico.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnSelecionarMedico, "cell 4 6,grow");
		btnSelecionarMedico.addActionListener(e -> {
			UsuariosTableModel modelo = (UsuariosTableModel) tblMedicos.getModel();
			MedicoVO medico = (MedicoVO) modelo.getUsuario(tblMedicos.getSelectedRow());
			for (JInternalFrame telaInterna : getDesktopPane().getAllFrames()) {
				if (telaInterna instanceof TelaInternaConsultasEHorarios) {
					TelaInternaConsultasEHorarios telaAgendamento = (TelaInternaConsultasEHorarios) telaInterna;
					telaAgendamento.setMedico(medico);
					this.dispose();
				}
			}
		});

		this.repaint();

	}

	public void consultarMedicos() {
		ControllerMedico controller = new ControllerMedico();
		SeletorUsuario seletor = new SeletorUsuario();

		seletor.setNome(txtNome.getText());
		if (txtCpf.getText().trim().length() < 14) {
			seletor.setCpf(null);
		} else {
			seletor.setCpf(txtCpf.getText());
		}
		seletor.setCrm(txtCrm.getText());
		seletor.setEspecialidade(txtEspecialidade.getText());
		seletor.setDataNascimento(datePicker.getDate());
		seletor.setNivel((String) cbxNivel.getModel().getSelectedItem());

		UsuariosTableModel modelo = (UsuariosTableModel) tblMedicos.getModel();
		modelo.limpar();
		modelo.addMedicos(controller.listarMedicos(seletor));

	}
}
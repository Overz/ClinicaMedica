package view.adm.paciente;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.ControllerPaciente;
import model.seletor.SeletorPaciente;
import model.vo.PacienteVO;
import net.miginfocom.swing.MigLayout;
import util.TableModels.PacienteTableModel;

public class TelaInternaExcluirPaciente extends JInternalFrame {

	private static final long serialVersionUID = -4416105711278801014L;
	private JTable tblPacientes;
	private JFormattedTextField txtCpf;
	private JButton btnExcluirUsuario;
	private JButton btnLimpar;
	private JButton btnPesquisar;
	private JLabel lblDataDeNascimento;
	private JTextField txtNome;
	private final DatePicker datePicker = new DatePicker();
	private JButton btnCancelar;
	private ArrayList<PacienteVO> pacientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaExcluirPaciente frame = new TelaInternaExcluirPaciente();
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
	public TelaInternaExcluirPaciente() {
		super("Clinica Médica - Exclusão de Usuario", false, false, false, false);
		setBounds(100, 100, 840, 638);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[10][1px][150px][50px:50px:50px][200px:200px:200px,grow][10]",
				"[40][40][40][40][][grow][40][10]"));

		initialize();
	}

	private void initialize() {

		MaskFormatter mascaraCpf;
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");

			txtNome = new JTextField();
			txtNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
			getContentPane().add(txtNome, "cell 2 2,grow");
			txtCpf = new JFormattedTextField(mascaraCpf);
			txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 20));
			getContentPane().add(txtCpf, "cell 2 3,grow");
		} catch (ParseException e1) {
			System.out.println("Erro ao criar máscara de CPF: " + e1.getMessage());
		}

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblNome, "cell 1 2,grow");

		btnLimpar = new JButton("Limpar Campos");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnLimpar, "cell 4 3,grow");
		btnLimpar.addActionListener(e -> {
			txtNome.setText("");
			txtCpf.setText("");
			datePicker.setDate(null);
		});

		datePicker.setSettings(dateSettings);
		getContentPane().add(datePicker, "cell 2 4, grow");
		datePicker.setEnabled(false);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblCpf, "cell 1 3,grow");

		lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblDataDeNascimento, "cell 1 4");

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnPesquisar, "cell 4 4,grow");
		btnPesquisar.addActionListener(e -> {
			consultarPacientes();
		});

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 1 5 4 1,grow");

		tblPacientes = new JTable();
		tblPacientes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PacienteTableModel pacientesTableModel = new PacienteTableModel();
		tblPacientes.setModel(pacientesTableModel);
		scrollPane.setViewportView(tblPacientes);
		tblPacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnCancelar, "cell 2 6,alignx right");
		btnCancelar.addActionListener(e -> {
			this.dispose();
		});

		btnExcluirUsuario = new JButton("Excluir Usuário");
		btnExcluirUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnExcluirUsuario, "cell 4 6,grow");
		btnExcluirUsuario.addActionListener(e -> {
			PacienteTableModel modelo = (PacienteTableModel) tblPacientes.getModel();
			PacienteVO paciente = modelo.getPaciente(tblPacientes.getSelectedRow());
			ControllerPaciente controller = new ControllerPaciente();
			String mensagem = controller.excluirPaciente(paciente);
			JOptionPane.showMessageDialog(this, mensagem);
			consultarPacientes();
		});

		this.repaint();

	}

	public void consultarPacientes() {
		ControllerPaciente controller = new ControllerPaciente();
		SeletorPaciente seletor = new SeletorPaciente();

		seletor.setNome(txtNome.getText());
		if (txtCpf.getText().trim().length() < 14) {
			seletor.setCpf(null);
		} else {
			seletor.setCpf(txtCpf.getText());
		}

		seletor.setDate(datePicker.getDate());

		PacienteTableModel modelo = (PacienteTableModel) tblPacientes.getModel();
		modelo.limpar();
		modelo.addPacientes(controller.buscarPaciente(seletor));

	}
}
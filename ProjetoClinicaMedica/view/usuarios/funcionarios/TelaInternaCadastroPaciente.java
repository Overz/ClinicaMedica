package view.usuarios.funcionarios;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.ControllerPaciente;
import model.vo.PacienteVO;
import net.miginfocom.swing.MigLayout;

public class TelaInternaCadastroPaciente extends JInternalFrame {

	private static final long serialVersionUID = -7169289567115539328L;
	// Atributos
	private static final String SELECIONE = "[SELECIONE]";
	private JFormattedTextField ftfCPF;
	private JFormattedTextField ftfTelefone;
	private JTextField txtNumero;
	private JFormattedTextField ftfCep;
	private JFormattedTextField ftfEstado;
	private MaskFormatter mascaraCpf;
	private MaskFormatter mascaraTelefone;
	private MaskFormatter mascaraEstado;
	private MaskFormatter mascaraCep;
	private JComboBox cbSexo;
	private JComboBox cbTipoSanguineo;
	private JComboBox cbConvenio;
	private JComboBox cbxPacientes;
	private JButton btnLimpar;
	private JButton btnCadastrar;
	private final DatePicker datePicker = new DatePicker();
	// Classes
	private ControllerPaciente controller;
	private static TelaInternaCadastroPaciente window;
	private static ArrayList<PacienteVO> pacientes = new ArrayList<PacienteVO>();
	private JTextField txtNome;
	private JTextField txtCidade;
	private JTextField txtBairro;
	private JTextField txtRua;
	private JTextField txtEmail;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					window = new TelaInternaCadastroPaciente();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInternaCadastroPaciente() {
		super("Clínica Médica - Cadastrar Paciente", true, true, false, false);
		getContentPane().setFont(new Font("Verdana", Font.PLAIN, 14));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1293, 829);
		getContentPane().setLayout(new MigLayout("", "[10][grow][grow][grow][grow][grow][10]",
				"[10][38,grow][40][38,grow][30][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38px,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][36px][38,grow][38,grow][38,grow][][60][10][10]"));
		initialize();
	}

	private void initialize() {

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraTelefone = new MaskFormatter("(##)#####-####");
			mascaraEstado = new MaskFormatter("UU");
			mascaraCep = new MaskFormatter("#####-###");
		} catch (ParseException e) {
			System.out.println("Erro ao criar m�scaras. Causa: " + e.getMessage());
		}

		JLabel informacao = new JLabel("Informações Pessoais");
		informacao.setFont(new Font("Arial", Font.BOLD, 25));
		getContentPane().add(informacao, "cell 1 1 5 1,alignx center,growy");

		JLabel lblSelecioneUmPaciente = new JLabel("Selecione um Paciente para editar:");
		lblSelecioneUmPaciente.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblSelecioneUmPaciente, "cell 2 3,grow");

		cbxPacientes = new JComboBox(pacientes.toArray());
		cbxPacientes.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(cbxPacientes, "cell 4 3 2 1,grow");
		cbxPacientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((PacienteVO) cbxPacientes.getModel().getSelectedItem() == null) {
					limparCampos();
				} else {
					preencherCampos((PacienteVO) cbxPacientes.getModel().getSelectedItem());
				}
			}
		});

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblNome, "cell 1 5,grow");

		txtNome = new JTextField();
		txtNome.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(txtNome, "cell 2 5,grow");
		txtNome.setColumns(10);

		JLabel lblCPF_CNPJ = new JLabel("CPF:");
		lblCPF_CNPJ.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblCPF_CNPJ, "cell 4 5,alignx center,growy");

		JLabel lblDataDeNacimento = new JLabel("Data de Nacimento:");
		lblDataDeNacimento.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblDataDeNacimento, "cell 1 6,alignx left,growy");

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblSexo, "cell 1 7,alignx left,growy");

		JLabel lblTipoSanguineo = new JLabel("Tipo Sanguineo:");
		lblTipoSanguineo.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblTipoSanguineo, "cell 4 7,alignx center,growy");

		JLabel lblConvenio = new JLabel("Convenio:");
		lblConvenio.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblConvenio, "cell 1 9,alignx left,growy");

		JLabel endereco = new JLabel("Endereço");
		endereco.setFont(new Font("Arial", Font.BOLD, 25));
		getContentPane().add(endereco, "cell 1 11 5 1,alignx center,growy");

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblCidade, "cell 1 13,grow");

		txtCidade = new JTextField();
		txtCidade.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(txtCidade, "cell 2 13,grow");
		txtCidade.setColumns(10);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblEstado, "cell 4 13,alignx center,growy");

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblBairro, "cell 1 14,grow");

		txtBairro = new JTextField();
		txtBairro.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtBairro.setColumns(10);
		getContentPane().add(txtBairro, "cell 2 14,grow");

		JLabel lblCep = new JLabel("CEP:");
		lblCep.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblCep, "cell 4 14,alignx center,growy");

		JLabel lblRua = new JLabel("Rua:");
		lblRua.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblRua, "cell 1 15,grow");

		txtRua = new JTextField();
		txtRua.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtRua.setColumns(10);
		getContentPane().add(txtRua, "cell 2 15,grow");

		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblNumero, "cell 1 16,alignx left,growy");

		JLabel contato = new JLabel("Contato");
		contato.setFont(new Font("Arial", Font.BOLD, 25));
		getContentPane().add(contato, "cell 1 18 5 1,alignx center,growy");

		JLabel lblNumeroTelefone = new JLabel("Telefone:");
		lblNumeroTelefone.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblNumeroTelefone, "cell 1 20,alignx left,growy");

		ftfCPF = new JFormattedTextField(mascaraCpf);
		ftfCPF.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(ftfCPF, "cell 5 5,grow");

		txtNumero = new JTextField();
		txtNumero.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(txtNumero, "cell 2 16,grow");

		ftfEstado = new JFormattedTextField(mascaraEstado);
		ftfEstado.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(ftfEstado, "cell 5 13,grow");

		ftfCep = new JFormattedTextField(mascaraCep);
		ftfCep.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(ftfCep, "cell 5 14,grow");

		ftfTelefone = new JFormattedTextField(mascaraTelefone);
		ftfTelefone.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(ftfTelefone, "cell 2 20,grow");

		cbSexo = new JComboBox();
		cbSexo.setFont(new Font("Verdana", Font.PLAIN, 20));
		cbSexo.setModel(new DefaultComboBoxModel(
				new String[] { SELECIONE, PacienteVO.SEXO_MASCULINO, PacienteVO.SEXO_FEMININO }));
		getContentPane().add(cbSexo, "cell 2 7,grow");

		cbConvenio = new JComboBox();
		cbConvenio.setFont(new Font("Verdana", Font.PLAIN, 20));
		cbConvenio.setModel(new DefaultComboBoxModel(new String[] { SELECIONE, PacienteVO.CONVENIO_PARTICULAR,
				PacienteVO.CONVENIO_UNIMED, PacienteVO.CONVENIO_AGEMED, PacienteVO.CONVENIO_AMIL,
				PacienteVO.CONVENIO_SULAMERICA_SAUDE, PacienteVO.CONVENIO_ONE_HEALTH,
				PacienteVO.CONVENIO_BRADESCO_SAUDE, PacienteVO.CONVENIO_GOLDEN_CROSS, PacienteVO.CONVENIO_INTERMEDICA,
				PacienteVO.CONVENIO_NOTRE_DAME_SEGURO_SAUDE }));
		getContentPane().add(cbConvenio, "cell 2 9,grow");

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		datePicker.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 20));
		datePicker.getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 20));

		datePicker.setSettings(dateSettings);
		getContentPane().add(datePicker, "cell 2 6,grow");

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(lblEmail, "cell 4 20,alignx center,growy");

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(txtEmail, "cell 5 20,grow");
		txtEmail.setColumns(10);

		btnLimpar = new JButton("Limpar Campos");
		btnLimpar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnLimpar, "cell 1 23 2 1,grow");
		btnLimpar.addActionListener(e -> {

			limparCampos();

		});

		cbTipoSanguineo = new JComboBox();
		cbTipoSanguineo.setFont(new Font("Verdana", Font.PLAIN, 20));
		cbTipoSanguineo.setModel(new DefaultComboBoxModel(new String[] { SELECIONE, PacienteVO.A, PacienteVO.a,
				PacienteVO.B, PacienteVO.b, PacienteVO.AB, PacienteVO.ab, PacienteVO.o, PacienteVO.O }));
		getContentPane().add(cbTipoSanguineo, "cell 5 7,grow");

		btnCadastrar = new JButton("Salvar");
		btnCadastrar.setFont(new Font("Verdana", Font.PLAIN, 20));
		getContentPane().add(btnCadastrar, "cell 4 23 2 1,grow");
		btnCadastrar.addActionListener(e -> {

			String nome = txtNome.getText();
			String cpf = ftfCPF.getText();
			LocalDate dtNascimento = datePicker.getDate();
			String sexo = cbSexo.getSelectedItem().toString();
			String convenio = cbConvenio.getSelectedItem().toString();
			String tipoSangue = cbTipoSanguineo.getSelectedItem().toString();
			String estado = ftfEstado.getText();
			String cidade = txtCidade.getText();
			String bairro = txtBairro.getText();
			String rua = txtRua.getText();
			String cep = ftfCep.getText();
			int numero = Integer.parseInt(txtNumero.getText());

			String telefone = ftfTelefone.getText();
			String email = txtEmail.getText();

			int idPaciente = 0;
			if (cbxPacientes.getSelectedIndex() >= 0) {
				PacienteVO pacienteVO = (PacienteVO) cbxPacientes.getModel().getSelectedItem();
				idPaciente = pacienteVO.getIdPaciente();
			}

			controller = new ControllerPaciente();
			String mensagem = controller.salvarPaciente(idPaciente, nome, cpf, dtNascimento, sexo, convenio, tipoSangue,
					estado, cidade, bairro, rua, cep, numero, telefone, email);
			JOptionPane.showMessageDialog(null, mensagem);
			if (mensagem.contains("sucesso")) {
				listarPacientes();
			}
		});

		listarPacientes();
	}

	protected void preencherCampos(PacienteVO paciente) {
		// Info Pessoal
		txtNome.setText(paciente.getNome());
		ftfCPF.setText(paciente.getCpf());
		cbConvenio.setSelectedItem(paciente.getConvenio());
		cbSexo.setSelectedItem(paciente.getSexo());
		cbTipoSanguineo.setSelectedItem(paciente.getTipoSanguineo());

		datePicker.setDate(paciente.getDtNascimento());
		// Endereço
		ftfEstado.setText(paciente.getEstado());
		ftfCep.setText(paciente.getCep());
		txtCidade.setText(paciente.getCidade());
		txtBairro.setText(paciente.getBairro());
		txtRua.setText(paciente.getRua());
		txtNumero.setText(Integer.toString(paciente.getNumero()));
		ftfEstado.setText(paciente.getEstado());
		// Contato
		ftfTelefone.setText(paciente.getTelefone());
		txtEmail.setText(paciente.getEmail());
	}

	protected void limparCampos() {
		// Info Pessoal
		txtNome.setText("");
		ftfCPF.setText("");
		cbConvenio.setSelectedItem(SELECIONE);
		cbSexo.setSelectedItem(SELECIONE);

		datePicker.setDate(null);
		// Endereço
		txtCidade.setText("");
		txtBairro.setText("");
		txtRua.setText("");
		txtNumero.setText("");
		ftfEstado.setText("");
		ftfCep.setText("");
		// Contato
		ftfTelefone.setText("");
		txtEmail.setText("");
		cbxPacientes.setSelectedIndex(-1);
		cbTipoSanguineo.setSelectedItem(SELECIONE);
	}

	public void listarPacientes() {
		controller = new ControllerPaciente();
		pacientes = controller.consultarTodos();
		pacientes.add(0, null);
		cbxPacientes.setModel(new DefaultComboBoxModel(pacientes.toArray()));
		cbxPacientes.setSelectedIndex(-1);
	}
}

package view.usuarios.funcionarios;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;
import java.time.LocalDate;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.ControllerPaciente;
import model.seletor.SeletorPaciente;
import model.vo.PacienteVO;
import net.miginfocom.swing.MigLayout;

public class TelaInternaCadastroPaciente extends JInternalFrame {

	private static final long serialVersionUID = -7169289567115539328L;
	//Atributos
	private static final String SELECIONE = "[SELEICONE]";
	private JFormattedTextField ftfNome;
	private JFormattedTextField ftfCPF;
	private JFormattedTextField ftfCidadeEndereco;
	private JFormattedTextField ftfRuaEndereco;
	private JFormattedTextField ftfBairroEndereco;
	private JFormattedTextField ftfNumeroEndereco;
	private JFormattedTextField ftfTelefone;
	private JFormattedTextField ftfEmail;
	private MaskFormatter mascaraNome;
	private MaskFormatter mascaraCpf;
	private MaskFormatter mascaraTelefone;
	private MaskFormatter mascaraNumeroEndereco;
	private MaskFormatter mascarEmail;
	private MaskFormatter mascaraCep;
	private JComboBox cbSexo;
	private JComboBox cbTipoSanguineo;
	private JComboBox cbConvenio;
	private JButton btnLimpar;
	private JButton btnCadastrar;
	private JButton btnPesquisar;
	private final DatePicker datePicker = new DatePicker();
	private JFormattedTextField ftfCep;
	private JFormattedTextField ftfEstado;
	//Classes
	private ControllerPaciente controller;
	private PacienteVO vo;

	private static TelaInternaCadastroPaciente window;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
		super("Clínica Médica - Cadastrar Paciente", false, true, false, false);
		getContentPane().setFont(new Font("Verdana", Font.PLAIN, 14));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1040, 741);
		getContentPane().setLayout(new MigLayout("", "[][grow][grow][grow][][grow][grow][grow][grow][grow]", "[38,grow][38,grow][38,grow][31px,grow][38,grow][][38,grow][][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38px,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][36px][38,grow][38,grow][38,grow][][38,grow][][38,grow]"));
		initialize();
	}

	private void initialize() {

		try {
			mascaraNome = new MaskFormatter("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
			mascarEmail = new MaskFormatter("******************************************");
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraTelefone = new MaskFormatter("(##)#####-####");
			mascaraNumeroEndereco = new MaskFormatter("####");
			mascaraCep = new MaskFormatter("#####-###");
		} catch (ParseException e) {
			System.out.println("Erro ao criar m�scaras. Causa: " + e.getMessage());
		}

		JLabel informacao = new JLabel("Informações Pessoais");
		informacao.setFont(new Font("Arial", Font.BOLD, 25));
		getContentPane().add(informacao, "cell 1 1 7 1,alignx center,growy");

		JLabel lblCPF_CNPJ = new JLabel("CPF:");
		lblCPF_CNPJ.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblCPF_CNPJ, "cell 1 3,alignx left,growy");

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblNome, "cell 6 3,alignx left,growy");

		ftfNome = new JFormattedTextField(mascaraNome);
		ftfNome.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfNome, "cell 7 3,grow");

		JLabel lblDataDeNacimento = new JLabel("Data de Nacimento:");
		lblDataDeNacimento.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblDataDeNacimento, "cell 1 9,alignx left,growy");

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblSexo, "cell 1 10,alignx left,growy");

		JLabel lblTipoSanguineo = new JLabel("Tipo Sanguineo:");
		lblTipoSanguineo.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblTipoSanguineo, "cell 6 10,alignx center,growy");

		JLabel lblConvenio = new JLabel("Convenio:");
		lblConvenio.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblConvenio, "cell 1 12,alignx left,growy");

		JLabel endereco = new JLabel("Endereço");
		endereco.setFont(new Font("Arial", Font.BOLD, 25));
		getContentPane().add(endereco, "cell 1 14 8 1,alignx center,growy");

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblCidade, "cell 1 16,alignx left,growy");

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblEstado, "cell 6 16,alignx center,growy");

		ftfEstado = new JFormattedTextField(mascaraNome);
		ftfEstado.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfEstado, "cell 7 16,grow");

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblBairro, "cell 1 17,alignx left,growy");

		JLabel lblCep = new JLabel("CEP:");
		lblCep.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblCep, "cell 6 17,alignx center,growy");

		JLabel lblRua = new JLabel("Rua:");
		lblRua.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblRua, "cell 1 18,grow");

		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblNumero, "cell 1 19,grow");

		JLabel contato = new JLabel("Contato");
		contato.setFont(new Font("Arial", Font.BOLD, 25));
		getContentPane().add(contato, "cell 1 21 8 1,alignx center,growy");

		JLabel lblNumeroTelefone = new JLabel("Telefone:");
		lblNumeroTelefone.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblNumeroTelefone, "cell 1 23,alignx left,growy");

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblEmail, "cell 6 23,alignx center,growy");

		ftfCPF = new JFormattedTextField(mascaraCpf);
		ftfCPF.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfCPF, "cell 2 3,grow");
		ftfCPF.setVisible(true);

		ftfCidadeEndereco = new JFormattedTextField(mascaraNome);
		ftfCidadeEndereco.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfCidadeEndereco, "cell 2 16,growx,aligny top");

		ftfBairroEndereco = new JFormattedTextField(mascaraNome);
		ftfBairroEndereco.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfBairroEndereco, "cell 2 17,growx");

		ftfRuaEndereco = new JFormattedTextField(mascaraNome);
		ftfRuaEndereco.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfRuaEndereco, "cell 2 18,grow");

		ftfNumeroEndereco = new JFormattedTextField(mascaraNumeroEndereco);
		ftfNumeroEndereco.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfNumeroEndereco, "cell 2 19,grow");

		ftfCep = new JFormattedTextField(mascaraCep);
		ftfCep.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfCep, "cell 7 17,growx");


		ftfTelefone = new JFormattedTextField(mascaraTelefone);
		ftfTelefone.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfTelefone, "cell 2 23,grow");

		ftfEmail = new JFormattedTextField(mascarEmail);
		ftfEmail.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfEmail, "cell 7 23,grow");

		cbSexo = new JComboBox();
		cbSexo.setFont(new Font("Verdana", Font.PLAIN, 18));
		cbSexo.setModel(new DefaultComboBoxModel(new String[] {SELECIONE, PacienteVO.SEXO_MASCULINO, PacienteVO.SEXO_FEMININO}));
		getContentPane().add(cbSexo, "cell 2 10,grow");

		cbTipoSanguineo = new JComboBox();
		cbTipoSanguineo.setFont(new Font("Verdana", Font.PLAIN, 18));
		cbTipoSanguineo.setModel(new DefaultComboBoxModel(new String[] { SELECIONE, PacienteVO.A,PacienteVO.a, PacienteVO.B, PacienteVO.b, PacienteVO.AB, PacienteVO.ab, PacienteVO.o, PacienteVO.O}));
		getContentPane().add(cbTipoSanguineo, "cell 7 10,grow");

		cbConvenio = new JComboBox();
		cbConvenio.setFont(new Font("Verdana", Font.PLAIN, 18));
		cbConvenio.setModel(new DefaultComboBoxModel(new String[] { SELECIONE, PacienteVO.CONVENIO_AGEMED, PacienteVO.CONVENIO_AMIL, PacienteVO.CONVENIO_SULAMERICA_SAUDE,
				PacienteVO.CONVENIO_ONE_HEALTH, PacienteVO.CONVENIO_BRADESCO_SAUDE, PacienteVO.CONVENIO_GOLDEN_CROSS, PacienteVO.CONVENIO_INTERMEDICA, PacienteVO.CONVENIO_NOTRE_DAME_SEGURO_SAUDE }));
		getContentPane().add(cbConvenio, "cell 2 12,grow");

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		datePicker.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 18));
		datePicker.getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 18));

		datePicker.setSettings(dateSettings);
		getContentPane().add(datePicker, "cell 2 9,grow");

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(btnPesquisar, "cell 3 5 3 3,grow");
		btnPesquisar.addActionListener(e -> {

			try {
				String nome = ftfNome.getText();
				String cpf = ftfCPF.getText();

				vo = new PacienteVO();
				ControllerPaciente controller = new ControllerPaciente();
				SeletorPaciente seletor = new SeletorPaciente();
				controller.setarDadosNaTela(vo);

				seletor.setCpf(cpf);
				seletor.setNome(nome);
				seletor.setDate(vo.getDtNascimento());

				String mensagem = controller.validarCamposPesquisarCadastroPaciente(seletor);

				if (mensagem != null || (!mensagem.trim().isEmpty())) {
					JOptionPane.showMessageDialog(null, mensagem);
				} else {
					controller.setarDadosNaTela(vo);
					ftfNome.setText(vo.getNome());
					ftfCPF.setText(vo.getCpf());
					cbConvenio.setSelectedItem(vo.getConvenio());
					cbSexo.setSelectedItem(vo.getSexo());

					datePicker.setDate(vo.getDtNascimento());
					// Endere�o
					ftfCidadeEndereco.setText(vo.getCidade());
					ftfBairroEndereco.setText(vo.getBairro());
					ftfRuaEndereco.setText(vo.getRua());
					ftfNumeroEndereco.setText(Integer.toString(vo.getNumero()));
					// Contato
					ftfTelefone.setText(vo.getTelefone());
					ftfEmail.setText("");
				}

			} catch (NullPointerException e2) {
				System.out.println("Tela: Cadastrar Paciente. Erro ao Validar os Campos para Pesquisar(btnPesquisar).\n" + e2.getMessage());
			}
		});

		btnCadastrar = new JButton("Salvar");
		btnCadastrar.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(btnCadastrar, "cell 2 25 3 3,grow");
		btnCadastrar.addActionListener(e -> {

			try {
				String nome = ftfNome.getText();
				String cpf = ftfCPF.getText();
				String rg = ftfCPF.getText();
				LocalDate dtNascimento = datePicker.getDate();
				String sexo = cbSexo.getSelectedItem().toString();
				String convenio = cbConvenio.getSelectedItem().toString();
				String tipoSangue = cbTipoSanguineo.getSelectedItem().toString();
				String estado = ftfEstado.getText();
				String cidade = ftfCidadeEndereco.getText();
				String bairro = ftfBairroEndereco.getText();
				String rua = ftfRuaEndereco.getText();
				String cep = ftfCep.getText();
				int numero = Integer.parseInt(ftfNumeroEndereco.getText());
				String telefone = ftfTelefone.getText();
				String email = ftfEmail.getText();

				controller = new ControllerPaciente();
				String mensagem = controller.validarSalvarCadastroPaciente(nome, cpf, rg, dtNascimento, sexo, convenio, tipoSangue, estado, cidade, bairro, rua, cep, numero, telefone, email);

				if (mensagem != null || (!(mensagem.trim().isEmpty()))) {
					JOptionPane.showMessageDialog(null, mensagem);
				} else {
					mensagem = controller.validarSalvarCadastroPaciente(nome, cpf, rg, dtNascimento, sexo, convenio, tipoSangue, estado, cidade, bairro, rua, cep, numero, telefone, email);
					JOptionPane.showMessageDialog(null, mensagem);
				}
			} catch (NullPointerException e2) {
				System.out.println("Tela: Cadastrar Paciente. Erro ao validar os campos para Salvar(btnSalvar).\n" + e2.getMessage());
			}

		});

		btnLimpar = new JButton("Limpar Campos");
		btnLimpar.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(btnLimpar, "cell 6 25 2 3,grow");
		btnLimpar.addActionListener(e -> {

			// Info Pessoal
			ftfNome.setText("");
			ftfCPF.setText("");
			cbConvenio.setSelectedItem(SELECIONE);
			cbSexo.setSelectedItem(SELECIONE);

			datePicker.setDate(null);
			// Endere�o
			ftfCidadeEndereco.setText("");
			ftfBairroEndereco.setText("");
			ftfRuaEndereco.setText("");
			ftfNumeroEndereco.setText("");
			// Contato
			ftfTelefone.setText("");
			ftfEmail.setText("");

		});

	}
}

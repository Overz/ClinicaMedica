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

import controller.ControllerFuncionario;
import net.miginfocom.swing.MigLayout;

public class TelaInternaCadastroPaciente extends JInternalFrame {

	private static final long serialVersionUID = -7169289567115539328L;
	private JFormattedTextField ftfNome;
	private JFormattedTextField ftfCPF;
	private JFormattedTextField ftfRg;
	private JFormattedTextField ftfCidadeEndereco;
	private JFormattedTextField ftfRuaEndereco;
	private JFormattedTextField ftfBairroEndereco;
	private JFormattedTextField ftfNumeroEndereco;
	private JFormattedTextField ftfComplementoEndereco;
	private JFormattedTextField ftfTelefone;
	private JFormattedTextField ftfCelular;
	private JFormattedTextField ftfEmail;
	private MaskFormatter mascaraNome;
	private MaskFormatter mascaraCpf;
	private MaskFormatter mascaraRG;
	//private MaskFormatter mascaraData;
	private MaskFormatter mascaraTelefone;
	private MaskFormatter mascaraNumeroEndereco;
	private MaskFormatter mascarEmail;
	private MaskFormatter mascaraCep;
	//private JLabel lblVoltar;

	private JComboBox cbSexo;
	private JComboBox cbTipoSanguineo;
	private JComboBox cbConvenio;
	private JComboBox cbEstadoCivil;

	private JButton btnLimpar;
	private JButton btnCadastrar;
	private JButton btnPesquisar;

	private final DatePicker datePicker = new DatePicker();
	private JFormattedTextField ftfCep;

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
		getContentPane().setLayout(new MigLayout("", "[][grow][grow][grow][][grow][grow][grow][grow][grow]", "[38,grow][38,grow][38,grow][31px,grow][38px,grow][38,grow][][38,grow][][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38px,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][36px][38,grow][38,grow][38,grow][38,grow][][38,grow][][38,grow]"));
		initialize();
	}

	private void initialize() {

		try {
			mascaraNome = new MaskFormatter("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
			mascarEmail = new MaskFormatter("******************************************");
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraRG = new MaskFormatter("############");
			//mascaraData = new MaskFormatter("##/##/####");
			mascaraTelefone = new MaskFormatter("(##)#####-####");
			mascaraNumeroEndereco = new MaskFormatter("####");
			mascaraCep = new MaskFormatter("#####-###");
		} catch (ParseException e) {
			System.out.println("Erro ao criar m�scaras. Causa: " + e.getMessage());
		}

		/*	lblVoltar = new JLabel();
		lblVoltar.setVisible(false);
		lblVoltar.setText("<< Voltar");
		lblVoltar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVoltar.setToolTipText("Cique Aqui para Voltar");
		getContentPane().add(lblVoltar, "cell 1 0,alignx left,growy");
		lblVoltar.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				lblVoltar.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				lblVoltar.setFont(new Font ("Arial", Font.BOLD, 18));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					window.setClosed(true);
				} catch (PropertyVetoException e1) {
					System.out.println("Erro ao fechar Janela\n" + e1.getMessage());
				}

			}
		});*/

		JLabel informacao = new JLabel("Informações Pessoais");
		informacao.setFont(new Font("Arial", Font.BOLD, 25));
		getContentPane().add(informacao, "cell 1 1 7 1,alignx center,growy");

		JLabel lblCPF_CNPJ = new JLabel("CPF:");
		lblCPF_CNPJ.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblCPF_CNPJ, "cell 1 3,alignx left,growy");

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblNome, "cell 1 4,alignx left,growy");

		JLabel lblRg = new JLabel("RG:");
		lblRg.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblRg, "cell 6 4,alignx center,growy");

		JLabel lblDataDeNacimento = new JLabel("Data de Nacimento:");
		lblDataDeNacimento.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblDataDeNacimento, "cell 1 10,alignx left,growy");

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblSexo, "cell 1 11,alignx left,growy");

		JLabel lblTipoSanguineo = new JLabel("Tipo Sanguineo:");
		lblTipoSanguineo.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblTipoSanguineo, "cell 6 11,alignx center,growy");

		JLabel lblConvenio = new JLabel("Convenio:");
		lblConvenio.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblConvenio, "cell 1 13,alignx left,growy");

		JLabel lblEstadoCivil = new JLabel("Estado Civil:");
		lblEstadoCivil.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblEstadoCivil, "cell 1 14,alignx left,growy");

		JLabel endereco = new JLabel("Endereço");
		endereco.setFont(new Font("Arial", Font.BOLD, 25));
		getContentPane().add(endereco, "cell 1 16 8 1,alignx center,growy");

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblCidade, "cell 1 18,alignx left,growy");

		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblComplemento, "cell 6 18,alignx center,growy");

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblBairro, "cell 1 19,alignx left,growy");

		JLabel lblCep = new JLabel("CEP:");
		lblCep.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblCep, "cell 6 19,alignx center,growy");

		JLabel lblRua = new JLabel("Rua:");
		lblRua.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblRua, "cell 1 20,grow");

		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblNumero, "cell 1 21,grow");

		JLabel contato = new JLabel("Contato");
		contato.setFont(new Font("Arial", Font.BOLD, 25));
		getContentPane().add(contato, "cell 1 23 8 1,alignx center,growy");

		JLabel lblNumeroTelefone = new JLabel("Telefone:");
		lblNumeroTelefone.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblNumeroTelefone, "cell 1 25,alignx left,growy");

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblEmail, "cell 6 25,alignx center,growy");

		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(lblCelular, "cell 1 26,alignx left,growy");

		ftfNome = new JFormattedTextField(mascaraNome);
		ftfNome.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfNome, "cell 2 4 2 1,grow");

		ftfCPF = new JFormattedTextField(mascaraCpf);
		ftfCPF.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfCPF, "cell 2 3,grow");
		ftfCPF.setVisible(true);

		ftfRg = new JFormattedTextField(mascaraRG);
		ftfRg.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfRg, "cell 7 4,grow");

		ftfCidadeEndereco = new JFormattedTextField(mascaraNome);
		ftfCidadeEndereco.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfCidadeEndereco, "cell 2 18,growx,aligny top");

		ftfBairroEndereco = new JFormattedTextField(mascaraNome);
		ftfBairroEndereco.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfBairroEndereco, "cell 2 19,growx");

		ftfRuaEndereco = new JFormattedTextField(mascaraNome);
		ftfRuaEndereco.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfRuaEndereco, "cell 2 20,grow");

		ftfNumeroEndereco = new JFormattedTextField(mascaraNumeroEndereco);
		ftfNumeroEndereco.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfNumeroEndereco, "cell 2 21,grow");

		ftfComplementoEndereco = new JFormattedTextField(mascaraNome);
		ftfComplementoEndereco.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfComplementoEndereco, "cell 7 18,growx");
		
		ftfCep = new JFormattedTextField(mascaraCep);
		ftfCep.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfCep, "cell 7 19,growx");

		ftfTelefone = new JFormattedTextField(mascaraTelefone);
		ftfTelefone.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfTelefone, "cell 2 25,grow");

		ftfCelular = new JFormattedTextField(mascaraTelefone);
		ftfCelular.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfCelular, "cell 2 26,grow");

		ftfEmail = new JFormattedTextField(mascarEmail);
		ftfEmail.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(ftfEmail, "cell 7 25,grow");

		cbSexo = new JComboBox();
		cbSexo.setFont(new Font("Verdana", Font.PLAIN, 18));
		cbSexo.setModel(new DefaultComboBoxModel(new String[] {"[SELEICONE]", "Masculino", "Feminino"}));
		getContentPane().add(cbSexo, "cell 2 11,grow");

		cbEstadoCivil = new JComboBox();
		cbEstadoCivil.setFont(new Font("Verdana", Font.PLAIN, 18));
		cbEstadoCivil.setModel(new DefaultComboBoxModel(new String[] { "[SELECIONE]", "Solteiro", "Casado", "Divorciado", "Separado", "Viúvo" }));
		getContentPane().add(cbEstadoCivil, "cell 2 14,grow");

		cbTipoSanguineo = new JComboBox();
		cbTipoSanguineo.setFont(new Font("Verdana", Font.PLAIN, 18));
		cbTipoSanguineo.setModel(new DefaultComboBoxModel(new String[] { "[SELECIONE]", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }));
		getContentPane().add(cbTipoSanguineo, "cell 7 11,grow");

		cbConvenio = new JComboBox();
		cbConvenio.setFont(new Font("Verdana", Font.PLAIN, 18));
		cbConvenio.setModel(new DefaultComboBoxModel(new String[] { "[SELECIONE]", "Unimed", "Agemed", "Amil", "SulAmerica Saúde",
				"One Health", "Bradesco Saúde\t", "Golden Cross", "Intermédica", "Notre Dame Seguro Saúde" }));
		getContentPane().add(cbConvenio, "cell 2 13,grow");

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		datePicker.getComponentDateTextField().setFont(new Font("Verdana", Font.PLAIN, 18));
		datePicker.getComponentToggleCalendarButton().setFont(new Font("Verdana", Font.PLAIN, 18));

		datePicker.setSettings(dateSettings);
		getContentPane().add(datePicker, "cell 2 10,grow");

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(btnPesquisar, "cell 3 6 3 3,grow");
		btnPesquisar.addActionListener(e -> {

			try {
				String nome = ftfNome.getText();
				String cpf = ftfCPF.getText();
				String rg = ftfCPF.getText();

				ControllerFuncionario controller = new ControllerFuncionario();
				String mensagem = controller.validarCamposPesquisarCadastroPaciente(nome, cpf, rg);
				if (mensagem != null || (!(mensagem.trim().isEmpty()))) {
					JOptionPane.showMessageDialog(null, mensagem);
				}

			} catch (NullPointerException e2) {
				System.out.println("Tela: Cadastrar Paciente. Erro ao Validar os Campos para Pesquisar(btnPesquisar).\n" + e2.getMessage());
			}
		});

		btnCadastrar = new JButton("Salvar");
		btnCadastrar.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(btnCadastrar, "cell 2 28 3 3,grow");
		btnCadastrar.addActionListener(e -> {

			try {
				String nome = ftfNome.getText();
				String cpf = ftfCPF.getText();
				String rg = ftfCPF.getText();
				LocalDate date = datePicker.getDate();
				int sexo = cbSexo.getSelectedIndex();
				int estadoCivil = cbEstadoCivil.getSelectedIndex();
				int tipoSangue = cbTipoSanguineo.getSelectedIndex();
				String cidade = ftfCidadeEndereco.getText();
				String bairro = ftfBairroEndereco.getText();
				String rua = ftfRuaEndereco.getText();
				String numero = ftfNumeroEndereco.getText();
				String telefone = ftfTelefone.getText();
				String celular = ftfCelular.getText();
				String email = ftfEmail.getText();

				ControllerFuncionario controller = new ControllerFuncionario();
				String mensagem = controller.validarSalvarCadastroPaciente(nome, cpf, rg, date, sexo, estadoCivil, tipoSangue, cidade, bairro, rua, numero, telefone, celular, email);
				if (mensagem != null || (!(mensagem.trim().isEmpty()))) {
					JOptionPane.showMessageDialog(null, mensagem);
				} else {
					// TODO fazer alguma coisa ?
				}
			} catch (NullPointerException e2) {
				System.out.println("Tela: Cadastrar Paciente. Erro ao validar os campos para Salvar(btnSalvar).\n" + e2.getMessage());
			}

		});

		btnLimpar = new JButton("Limpar Campos");
		btnLimpar.setFont(new Font("Verdana", Font.PLAIN, 18));
		getContentPane().add(btnLimpar, "cell 6 28 2 3,grow");
		btnLimpar.addActionListener(e -> {

			// Info Pessoal
			ftfNome.setText("");
			ftfCPF.setText("");
			ftfCPF.setVisible(false);
			ftfRg.setText("");
			cbConvenio.setSelectedIndex(-1);
			cbEstadoCivil.setSelectedIndex(-1);
			cbSexo.setSelectedIndex(-1);

			datePicker.setDate(null);
			// Endere�o
			ftfCidadeEndereco.setText("");
			ftfBairroEndereco.setText("");
			ftfRuaEndereco.setText("");
			ftfNumeroEndereco.setText("");
			ftfComplementoEndereco.setText("");
			// Contato
			ftfTelefone.setText("");
			ftfCelular.setText("");
			ftfEmail.setText("");

		});

	}
}

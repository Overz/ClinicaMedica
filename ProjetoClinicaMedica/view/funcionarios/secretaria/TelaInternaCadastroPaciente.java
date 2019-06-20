package view.funcionarios.secretaria;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.time.LocalDate;

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

import controller.ControllerFuncionario;
import controller.ControllerValidacao;
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
	private MaskFormatter mascaraData;
	private MaskFormatter mascaraCelular;
	private MaskFormatter mascaraTelefone;
	private MaskFormatter mascaraNumeroEndereco;
	private MaskFormatter mascarEmail;
	private JLabel lblVoltar;

	private JComboBox cbSexo;
	private JComboBox cbTipoSanguineo;
	private JComboBox cbConvenio;
	private JComboBox cbEstadoCivil;

	private JButton btnLimpar;
	private JButton btnCadastrar;
	private JButton btnPesquisar;
	
	private final DatePicker datePicker = new DatePicker();

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
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1040, 741);
		getContentPane().setLayout(new MigLayout("", "[][pref!,grow][grow][grow][][grow][grow][grow][grow][grow]", "[38,grow][38,grow][38,grow][31px,grow][38px,grow][38,grow][][38,grow][][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38px,grow][38,grow][38,grow][38,grow][38,grow][38,grow][36px][38,grow][38,grow][38,grow][38,grow][][38,grow][][38,grow]"));
		initialize();
	}

	private void initialize() {

		try {
			mascaraNome = new MaskFormatter("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
			mascarEmail = new MaskFormatter("******************************************");
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraRG = new MaskFormatter("############");
			mascaraData = new MaskFormatter("##/##/####");
			mascaraTelefone = new MaskFormatter("(##)####-####");
			mascaraCelular = new MaskFormatter("(##)#-####-####");
			mascaraNumeroEndereco = new MaskFormatter("####");
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

		JLabel lblInformacao = new JLabel("Informações Pessoais");
		lblInformacao.setFont(new Font("Verdana", Font.BOLD, 14));
		getContentPane().add(lblInformacao, "cell 2 1 6 1,alignx center,growy");

		JLabel lblCPF_CNPJ = new JLabel("CPF:");
		lblCPF_CNPJ.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblCPF_CNPJ, "cell 1 3,alignx left,growy");

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblNome, "cell 1 4,alignx left,growy");
		
		JLabel lblRg = new JLabel("RG:");
		lblRg.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblRg, "cell 6 4,alignx center,growy");
		
		JLabel lblDataDeNacimento = new JLabel("Data de Nacimento:");
		lblDataDeNacimento.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblDataDeNacimento, "cell 1 10,alignx left,growy");

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblSexo, "cell 1 11,alignx left,growy");

		JLabel lblTipoSanguineo = new JLabel("Tipo Sanguineo:");
		lblTipoSanguineo.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblTipoSanguineo, "cell 6 11,alignx center,growy");

		JLabel lblConvenio = new JLabel("Convenio:");
		lblConvenio.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblConvenio, "cell 1 13,alignx left,growy");

		JLabel lblEstadoCivil = new JLabel("Estado Civil:");
		lblEstadoCivil.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblEstadoCivil, "cell 1 14,alignx left,growy");

		JLabel endereco = new JLabel("Endereço");
		endereco.setFont(new Font("Verdana", Font.BOLD, 14));
		getContentPane().add(endereco, "cell 1 16 8 1,alignx center,growy");

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblCidade, "cell 1 18,alignx left,growy");
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblBairro, "cell 1 19,alignx left,growy");
		
		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblNumero, "cell 6 19,alignx center,growy");
		
		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblComplemento, "cell 1 20,alignx left,growy");
		
		JLabel lblContato = new JLabel("Contato");
		lblContato.setFont(new Font("Verdana", Font.BOLD, 14));
		getContentPane().add(lblContato, "cell 1 22 8 1,alignx center,growy");

		JLabel lblNumeroTelefone = new JLabel("Telefone:");
		lblNumeroTelefone.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblNumeroTelefone, "cell 1 24,alignx left,growy");

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblEmail, "cell 6 24,alignx center,growy");
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblCelular, "cell 1 25,alignx left,growy");

		JLabel lblRua = new JLabel("Rua:");
		lblRua.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblRua, "cell 6 18,alignx center,growy");
		
		ftfNome = new JFormattedTextField(mascaraNome);
		getContentPane().add(ftfNome, "cell 2 4 2 1,grow");
		
		ftfCPF = new JFormattedTextField(mascaraCpf);
		ftfCPF.setFont(new Font("Arial", Font.PLAIN, 14));
		getContentPane().add(ftfCPF, "cell 2 3,grow");
		ftfCPF.setVisible(true);

		ftfRg = new JFormattedTextField(mascaraRG);
		getContentPane().add(ftfRg, "cell 7 4 2 1,grow");

		ftfCidadeEndereco = new JFormattedTextField(mascaraNome);
		getContentPane().add(ftfCidadeEndereco, "cell 2 18,growx,aligny top");
		
		ftfBairroEndereco = new JFormattedTextField(mascaraNome);
		getContentPane().add(ftfBairroEndereco, "cell 2 19,growx");
		
		ftfRuaEndereco = new JFormattedTextField(mascaraNome);
		getContentPane().add(ftfRuaEndereco, "cell 7 18 2 1,grow");
		
		ftfNumeroEndereco = new JFormattedTextField();
		getContentPane().add(ftfNumeroEndereco, "cell 7 19,grow");
		
		ftfComplementoEndereco = new JFormattedTextField(mascaraNome);
		getContentPane().add(ftfComplementoEndereco, "cell 2 20,growx");
		
		ftfTelefone = new JFormattedTextField(mascaraTelefone);
		getContentPane().add(ftfTelefone, "cell 2 24,grow");

		ftfCelular = new JFormattedTextField(mascaraTelefone);
		getContentPane().add(ftfCelular, "cell 2 25,grow");
		
		ftfEmail = new JFormattedTextField(mascarEmail);
		getContentPane().add(ftfEmail, "cell 7 24 2 1,grow");

		cbSexo = new JComboBox();
		cbSexo.setModel(new DefaultComboBoxModel(new String[] {"[SELEICONE]", "Masculino", "Feminino", "Outros"}));
		getContentPane().add(cbSexo, "cell 2 11,grow");

		cbEstadoCivil = new JComboBox();
		cbEstadoCivil.setModel(new DefaultComboBoxModel(new String[] { "[SELECIONE]", "Solteiro", "Casado", "Divorciado", "Separado", "Viúvo" }));
		getContentPane().add(cbEstadoCivil, "cell 2 14,grow");

		cbTipoSanguineo = new JComboBox();
		cbTipoSanguineo.setModel(new DefaultComboBoxModel(new String[] { "[SELECIONE]", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }));
		getContentPane().add(cbTipoSanguineo, "cell 7 11,grow");

		cbConvenio = new JComboBox();
		cbConvenio.setModel(new DefaultComboBoxModel(new String[] { "[SELECIONE]", "Unimed", "Agemed", "Amil", "SulAmerica Saúde",
						"One Health", "Bradesco Saúde\t", "Golden Cross", "Intermédica", "Notre Dame Seguro Saúde" }));
		getContentPane().add(cbConvenio, "cell 2 13,grow");

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		datePicker.setSettings(dateSettings);
		getContentPane().add(datePicker, "cell 2 10,grow");

		btnPesquisar = new JButton("Pesquisar");
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
		getContentPane().add(btnCadastrar, "cell 2 27 1 3,grow");
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
		getContentPane().add(btnLimpar, "cell 6 27 2 3,grow");
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

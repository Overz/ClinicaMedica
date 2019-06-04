package view.funcionarios.secretaria;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TelaInternaCadastroPacienteInformacao extends JInternalFrame {

	private JTextField txtNome;
	private JFormattedTextField ftfCPF;

	private MaskFormatter mascaraCpf;
	private MaskFormatter mascaraCnpj;
	private MaskFormatter mascaraData;
	private MaskFormatter mascaraTelefone;
	private JTextField txtBairroEndereco;
	private JTextField txtRuaEndereco;
	private JTextField txtNumeroEndereco;
	private JTextField txtComplementoEndereco;
	private JTextField txtEmail;
	private JTextField txtCidade;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaCadastroPacienteInformacao window = new TelaInternaCadastroPacienteInformacao();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInternaCadastroPacienteInformacao() {
		setClosable(true);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 866, 741);
		getContentPane().setLayout(new MigLayout("", "[grow,center][grow,fill][grow,fill][grow,fill]", "[14px][31px][38px][25px][26.00px][25px][][fill][][][29px][31px][31px][30px][36px][31px][][]"));
		initialize();
	}

	private void initialize() {

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCnpj = new MaskFormatter("##.###.###/####-##");
			mascaraData = new MaskFormatter("##/##/####");
			mascaraTelefone = new MaskFormatter("(##)####-####");
		} catch (ParseException e) {
			System.out.println("Erro ao criar m�scaras. Causa: " + e.getMessage());
		}

		JLabel lblInformacaoPessoal = new JLabel("Informações >");
		getContentPane().add(lblInformacaoPessoal, "flowx,cell 0 0,grow");

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblNome, "cell 0 1,alignx left,growy");

		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 1 1 2 1,grow");
		txtNome.setColumns(10);

		JLabel lblCPF_CNPJ = new JLabel("CPF/CNPJ:");
		lblCPF_CNPJ.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblCPF_CNPJ, "cell 0 2,alignx left,growy");

		ftfCPF = new JFormattedTextField(mascaraCpf);
		getContentPane().add(ftfCPF, "cell 1 2 2 1,grow");
		ftfCPF.setVisible(false);

		JLabel lblDataDeNacimento = new JLabel("Data de Nacimento:");
		lblDataDeNacimento.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblDataDeNacimento, "cell 0 3,alignx left,aligny center");

		JFormattedTextField ftfData = new JFormattedTextField(mascaraData);
		getContentPane().add(ftfData, "cell 1 3,grow");

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblSexo, "cell 0 4,alignx left,growy");

		JComboBox cbSexo = new JComboBox();
		cbSexo.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Feminino", "Outros"}));
		getContentPane().add(cbSexo, "cell 1 4,grow");

		JLabel lblTipoSanguineo = new JLabel("Tipo Sanguineo:");
		lblTipoSanguineo.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblTipoSanguineo, "cell 0 5,alignx left,growy");

		JComboBox cbTipoSanguineo = new JComboBox();
		getContentPane().add(cbTipoSanguineo, "cell 1 5,grow");

		JLabel lblConvenio = new JLabel("Convenio:");
		lblConvenio.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblConvenio, "cell 0 7,alignx left,growy");

		JComboBox cbConvenio = new JComboBox();
		cbConvenio.setModel(new DefaultComboBoxModel(new String[] {"Unimed", "Agemed", "Amil", "SulAmerica Saúde", "One Health", "Bradesco Saúde\t", "Golden Cross", "Intermédica", "Notre Dame Seguro Saúde"}));
		getContentPane().add(cbConvenio, "cell 1 7,grow");

		JButton btnProximo = new JButton("Proximo");
		getContentPane().add(btnProximo, "cell 1 9,grow");

		JButton button = new JButton("Proximo");
		getContentPane().add(button, "cell 2 9,grow");

		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setFont(new Font("Verdana", Font.BOLD, 14));
		getContentPane().add(lblEndereo, "cell 0 10 4 1,grow");

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblCidade, "cell 0 11,growx,aligny center");

		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		getContentPane().add(txtCidade, "cell 1 11,grow");

		JLabel lblRua = new JLabel("Rua:");
		lblRua.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblRua, "cell 2 11,alignx right,aligny center");

		txtRuaEndereco = new JTextField();
		getContentPane().add(txtRuaEndereco, "cell 3 11,grow");
		txtRuaEndereco.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblBairro, "cell 0 12,growx,aligny center");


		txtBairroEndereco = new JTextField();
		getContentPane().add(txtBairroEndereco, "cell 1 12,grow");
		txtBairroEndereco.setColumns(10);

		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblNumero, "cell 2 12,alignx right,aligny center");

		txtNumeroEndereco = new JTextField();
		getContentPane().add(txtNumeroEndereco, "cell 3 12,grow");
		txtNumeroEndereco.setColumns(10);

		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblComplemento, "cell 0 13,growx,aligny center");

		txtComplementoEndereco = new JTextField();
		getContentPane().add(txtComplementoEndereco, "cell 1 13,grow");
		txtComplementoEndereco.setColumns(10);

		JLabel lblContato = new JLabel("Contato");
		lblContato.setFont(new Font("Verdana", Font.BOLD, 14));
		getContentPane().add(lblContato, "cell 0 14 4 1,grow");

		JLabel lblNumeroTelefone = new JLabel("Telefone:");
		lblNumeroTelefone.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblNumeroTelefone, "cell 0 15,growx,aligny center");

		JFormattedTextField ftfTelefone = new JFormattedTextField(mascaraTelefone);
		getContentPane().add(ftfTelefone, "cell 1 15,grow");

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblEmail, "cell 2 15,alignx right,aligny center");

		txtEmail = new JTextField();
		getContentPane().add(txtEmail, "cell 3 15,grow");
		txtEmail.setColumns(10);

		JButton btnCadastrar = new JButton("Cadastrar");
		getContentPane().add(btnCadastrar, "cell 1 16,grow");
		btnCadastrar.addActionListener(e -> {

		});

		JLabel lblEndereco = new JLabel("Endereço >");
		getContentPane().add(lblEndereco, "cell 0 0,grow");

		JLabel lblContato_1 = new JLabel("Contato");
		getContentPane().add(lblContato_1, "cell 0 0,grow");

		JButton btnLimpar = new JButton("Limpar Campos");
		getContentPane().add(btnLimpar, "cell 2 16,grow");
		btnLimpar.addActionListener(e -> {

			// Info Pessoal
			txtNome.setText("");
			ftfCPF.setText("");
			ftfCPF.setVisible(false);

			ftfData.setText("");
			// Endere�o
			txtCidade.setText("");
			txtBairroEndereco.setText("");
			txtRuaEndereco.setText("");
			txtNumeroEndereco.setText("");
			txtComplementoEndereco.setText("");
			// Contato
			ftfTelefone.setText("");
			txtEmail.setText("");

		});

		/*try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/aaaa");
			Date data = sdf.parse(ftfData.getText());
		} catch (ParseException e) {
			e.getMessage();
			System.out.println(e);
			System.out.println("2� Try/Catch. Causa: " + e.getMessage() );
		}*/
	}
}

package view.funcionarios.secretaria;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import net.miginfocom.swing.MigLayout;
import com.toedter.calendar.JDateChooser;

public class TelaInternaCadastroPaciente extends JInternalFrame {

	private static TelaInternaCadastroPaciente window;

	private JTextField txtNome;
	private JFormattedTextField ftfCPF;
	private JFormattedTextField ftfRg;
	private MaskFormatter mascaraCpf;
	private MaskFormatter mascaraRG;
	private MaskFormatter mascaraData;
	private MaskFormatter mascaraCelular;
	private MaskFormatter mascaraTelefone;
	private JTextField txtBairroEndereco;
	private JTextField txtRuaEndereco;
	private JTextField txtNumeroEndereco;
	private JTextField txtComplementoEndereco;
	private JTextField txtEmail;
	private JTextField txtCidade;
	private JComboBox cbSexo;
	private Component cbTipoSanguineo;
	private JLabel lblVoltar;

	private JFormattedTextField ftfCelular;

	private JDateChooser dateChooser;

	private JComboBox cbConvenio;

	private JButton btnLimpar;
private JFormattedTextField ftfTelefone;

private JButton btnCadastrar;
private JButton btnPesquisar;


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
		setClosable(true);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 1040, 741);
		getContentPane().setLayout(new MigLayout("", "[][pref!,grow][grow][grow][][grow][grow][pref!,grow][grow][grow][grow]", "[38,grow][38,grow][31px,grow][38px,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow][38px,grow][38,grow][38,grow][38,grow][38,grow][38,grow][36px][38,grow][38,grow][38,grow][38,grow][38,grow][38,grow]"));
		initialize();
	}

	private void initialize() {

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraRG = new MaskFormatter("#########");
			mascaraData = new MaskFormatter("##/##/####");
			mascaraTelefone = new MaskFormatter("(##)####-####");
			mascaraCelular = new MaskFormatter("(##)#-####-####");
		} catch (ParseException e) {
			System.out.println("Erro ao criar m�scaras. Causa: " + e.getMessage());
		}

		lblVoltar = new JLabel("<< Voltar");
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

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				//TelaInternaCadastroPaciente = null;

			}
		});

		JLabel lblInformacao = new JLabel("Informações Pessoais");
		lblInformacao.setFont(new Font("Verdana", Font.BOLD, 14));
		getContentPane().add(lblInformacao, "cell 2 0 7 1,alignx center,growy");
		
				JLabel lblCPF_CNPJ = new JLabel("CPF:");
				lblCPF_CNPJ.setFont(new Font("Verdana", Font.PLAIN, 14));
				getContentPane().add(lblCPF_CNPJ, "cell 1 2,alignx left,growy");
		
				ftfCPF = new JFormattedTextField(mascaraCpf);
				getContentPane().add(ftfCPF, "cell 2 2,grow");
				ftfCPF.setVisible(false);
		
				JLabel lblNome = new JLabel("Nome:");
				lblNome.setFont(new Font("Verdana", Font.PLAIN, 14));
				getContentPane().add(lblNome, "cell 1 3,alignx left,growy");
		
				txtNome = new JTextField();
				getContentPane().add(txtNome, "cell 2 3 2 1,grow");
				txtNome.setColumns(10);
		
		JLabel lblRg = new JLabel("RG:");
		lblRg.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblRg, "cell 6 3,alignx center,growy");
		
		btnPesquisar = new JButton("Pesquisar");
		getContentPane().add(btnPesquisar, "cell 3 5 3 1,grow");
		
		JLabel lblDataDeNacimento = new JLabel("Data de Nacimento:");
		lblDataDeNacimento.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblDataDeNacimento, "cell 1 7,alignx left,growy");
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblSexo, "cell 1 8,alignx left,growy");
		
		JLabel lblTipoSanguineo = new JLabel("Tipo Sanguineo:");
		lblTipoSanguineo.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblTipoSanguineo, "cell 6 8,alignx center,growy");
		
		JLabel lblConvenio = new JLabel("Convenio:");
		lblConvenio.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblConvenio, "cell 1 10,alignx left,growy");

		JLabel endereco = new JLabel("Endereço");
		endereco.setFont(new Font("Verdana", Font.BOLD, 14));
		getContentPane().add(endereco, "cell 1 12 9 1,alignx center,growy");

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblCidade, "cell 1 14,alignx left,aligny center");

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblBairro, "cell 1 15,growx,aligny center");

		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblNumero, "cell 6 15,alignx center,growy");

		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblComplemento, "cell 1 16,growx,aligny center");
		
		JLabel lblContato = new JLabel("Contato");
		lblContato.setFont(new Font("Verdana", Font.BOLD, 14));
		getContentPane().add(lblContato, "cell 1 18 9 1,alignx center,growy");

		JLabel lblNumeroTelefone = new JLabel("Telefone:");
		lblNumeroTelefone.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblNumeroTelefone, "cell 1 20,growx,aligny center");
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblEmail, "cell 6 20,alignx center,growy");
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblCelular, "cell 1 21,alignx left,growy");
		
		JLabel lblRua = new JLabel("Rua:");
		lblRua.setFont(new Font("Verdana", Font.PLAIN, 14));
		getContentPane().add(lblRua, "cell 6 14,alignx center,growy");
		
		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		getContentPane().add(txtCidade, "cell 2 14,grow");
		
		txtEmail = new JTextField();
		getContentPane().add(txtEmail, "cell 7 20 3 1,grow");
		txtEmail.setColumns(10);
		
		txtRuaEndereco = new JTextField();
		getContentPane().add(txtRuaEndereco, "cell 7 14 3 1,grow");
		txtRuaEndereco.setColumns(10);

		
		txtBairroEndereco = new JTextField();
		getContentPane().add(txtBairroEndereco, "cell 2 15,grow");
		txtBairroEndereco.setColumns(10);

		txtNumeroEndereco = new JTextField();
		getContentPane().add(txtNumeroEndereco, "cell 7 15,grow");
		txtNumeroEndereco.setColumns(10);
		
		txtComplementoEndereco = new JTextField();
		getContentPane().add(txtComplementoEndereco, "cell 2 16,grow");
		txtComplementoEndereco.setColumns(10);

		
		ftfRg = new JFormattedTextField(mascaraRG);
		getContentPane().add(ftfRg, "cell 7 3 2 1,grow");
		
		ftfTelefone = new JFormattedTextField(mascaraTelefone);
		getContentPane().add(ftfTelefone, "cell 2 20,grow");
		
		ftfCelular = new JFormattedTextField(mascaraTelefone);
		getContentPane().add(ftfCelular, "cell 2 21,grow");
		
		
		cbSexo = new JComboBox();
		cbSexo.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Feminino", "Outros"}));
		getContentPane().add(cbSexo, "cell 2 8,grow");

		
		cbTipoSanguineo = new JComboBox();
		getContentPane().add(cbTipoSanguineo, "cell 7 8,grow");

		
		cbConvenio = new JComboBox();
		cbConvenio.setModel(new DefaultComboBoxModel(new String[] {"Unimed", "Agemed", "Amil", "SulAmerica Saúde", "One Health", "Bradesco Saúde\t", "Golden Cross", "Intermédica", "Notre Dame Seguro Saúde"}));
		getContentPane().add(cbConvenio, "cell 2 10,grow");

		
		dateChooser = new JDateChooser();
		getContentPane().add(dateChooser, "cell 2 7,grow");
		

		btnCadastrar = new JButton("Salvar");
		getContentPane().add(btnCadastrar, "cell 2 23 2 1,grow");
		btnCadastrar.addActionListener(e -> {

		});

		btnLimpar = new JButton("Limpar Campos");
		getContentPane().add(btnLimpar, "cell 5 23 2 1,grow");
		btnLimpar.addActionListener(e -> {

			// Info Pessoal
			txtNome.setText("");
			ftfCPF.setText("");
			ftfCPF.setVisible(false);

			dateChooser.setDate(null);
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
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date data = sdf.parse(ftfData.getText());
		} catch (ParseException e) {
			e.getMessage();
			System.out.println(e);
			System.out.println("2� Try/Catch. Causa: " + e.getMessage() );
		}*/
	}
}

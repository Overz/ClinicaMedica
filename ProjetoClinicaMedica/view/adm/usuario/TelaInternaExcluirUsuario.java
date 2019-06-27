package view.adm.usuario;

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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.ControllerUsuario;
import model.seletor.SeletorUsuario;
import model.vo.UsuarioVO;
import net.miginfocom.swing.MigLayout;
import util.TableModels.UsuariosTableModel;

public class TelaInternaExcluirUsuario extends JInternalFrame {

	private static final long serialVersionUID = -4416105711278801014L;
	private JTextField txtCrm;
	private JTextField txtNome;
	private JTextField txtEspecialidade;
	private JFormattedTextField txtCpf;
	private MaskFormatter mascaraCpf;
	private JButton btnExcluirUsuario;
	private JButton btnLimpar;
	private JButton btnPesquisar;
	private JButton btnCancelar;
	private JComboBox cbxNivel;
	private JTable tblUsuarios;
	private final DatePicker datePicker = new DatePicker();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaExcluirUsuario window = new TelaInternaExcluirUsuario();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInternaExcluirUsuario() {
		super("Clinica Médica - Exclusão de Usuario", true, true, false, false);
		setBounds(100, 100, 996, 774);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[10][grow][grow][grow][grow][10]", "[40][40][40][40][10][][10][grow][40][10]"));

		initialize();
	}

	private void initialize() {
		
		try {
			mascaraCpf = new MaskFormatter("###########");
			
		} catch (ParseException e1) {
			System.out.println("Erro ao criar máscara de CPF(Tela Excluir Úsuario).\n" + e1.getMessage());
		}

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblNome, "cell 1 0,grow");

		JLabel lblNivel = new JLabel("Nível:");
		lblNivel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblNivel, "cell 3 0,grow");
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblDataDeNascimento, "cell 1 2");

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblCpf, "cell 1 1,grow");
		
		JLabel lblEspecialidade = new JLabel("Especialidade:");
		lblEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblEspecialidade, "cell 1 3,grow");

		JLabel lblCrm = new JLabel("CRM:");
		lblCrm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblCrm, "cell 3 1,grow");
		
		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 2 0,grow");
		txtNome.setColumns(10);
		
		txtCpf = new JFormattedTextField(mascaraCpf);
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(txtCpf, "cell 2 1,grow");

		txtCrm = new JFormattedTextField();
		txtCrm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(txtCrm, "cell 4 1,grow");

		txtEspecialidade = new JTextField();
		getContentPane().add(txtEspecialidade, "cell 2 3,grow");
		txtEspecialidade.setColumns(10);
		
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		datePicker.setSettings(dateSettings);
		getContentPane().add(datePicker, "cell 2 2, grow");
		
		cbxNivel = new JComboBox();
		cbxNivel.setModel(new DefaultComboBoxModel(
				new String[] { "", UsuarioVO.NIVEL_FUNCIONARIO, UsuarioVO.NIVEL_MEDICO, UsuarioVO.NIVEL_ADMIN }));
		getContentPane().add(cbxNivel, "cell 4 0,grow");
		cbxNivel.setSelectedIndex(-1);

		btnLimpar = new JButton("Limpar Campos");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnLimpar, "cell 2 5,grow");
		btnLimpar.addActionListener(e -> {
			txtNome.setText("");
			txtCpf.setText("");
			txtCrm.setText("");
			txtEspecialidade.setText("");
			datePicker.setDate(null);
		});

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnPesquisar, "cell 3 5 2 1,grow");
		btnPesquisar.addActionListener(e -> {
			consultarUsuarios();
		});

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 1 7 4 1,grow");

		tblUsuarios = new JTable();
		tblUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		UsuariosTableModel usuariosTableModel = new UsuariosTableModel();
		tblUsuarios.setModel(usuariosTableModel);
		scrollPane.setViewportView(tblUsuarios);
		tblUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnCancelar, "cell 2 8,grow");
		btnCancelar.addActionListener(e -> {
			this.dispose();
		});

		btnExcluirUsuario = new JButton("Excluir Usuário");
		btnExcluirUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnExcluirUsuario, "cell 3 8 2 1,grow");
		btnExcluirUsuario.addActionListener(e -> {
			UsuariosTableModel modelo = (UsuariosTableModel) tblUsuarios.getModel();
			UsuarioVO usuario = modelo.getUsuario(tblUsuarios.getSelectedRow());
			ControllerUsuario controller = new ControllerUsuario();
			String mensagem = controller.excluirUsuario(usuario);
			JOptionPane.showMessageDialog(this, mensagem);
			consultarUsuarios();
		});

		this.repaint();

	}

	public void consultarUsuarios() {
		ControllerUsuario controller = new ControllerUsuario();
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

		UsuariosTableModel modelo = (UsuariosTableModel) tblUsuarios.getModel();
		modelo.limpar();
		modelo.addUsuarios(controller.listarUsuarios(seletor));

	}
}

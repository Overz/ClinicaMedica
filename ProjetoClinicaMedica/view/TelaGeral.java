package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import model.vo.MedicoVO;
import model.vo.PacienteVO;
import model.vo.UsuarioVO;
import view.adm.paciente.TelaInternaCadastrarAtualizarPaciente;
import view.adm.usuario.TelaInternaCadastroUsuario;
import view.adm.usuario.TelaInternaExcluirUsuario;
import view.usuarios.funcionarios.TelaInternaBuscarPaciente;
import view.usuarios.funcionarios.TelaInternaCadastroPaciente;
import view.usuarios.funcionarios.TelaInternaConsultasEHorarios;
import view.usuarios.medico.TelaInternaAgendaMedico;
import view.usuarios.medico.TelaInternaProntuarioMedico;

public class TelaGeral extends JFrame {

	private static final long serialVersionUID = -1850173995181721117L;
	private JDesktopPane desktopPane;
	private JMenuBar menuBar;
	private JMenu mnPaciente;
	private JMenu mnMedico;
	private JMenu mnAdm;
	private JMenuItem mntmBuscarPaciente;
	private JMenuItem mntmCadastrarPaciente;
	private JMenuItem mntmAgendaMedica;
	private JMenuItem mntmProntuario;
	private JMenuItem mntmGerarRelatorioDeConsultas;
	private JMenuItem mntmCadastrarAtualizarFuncionarios;
	private JMenuItem mntmBuscarExcluirFuncionario;
	private JMenuItem mnBuscarExcluirPacienteADM;
	private JMenuItem mnCadastrarAtualizarPacienteADM;
	private JMenuItem mntmExcluirUsuarios;
	private JMenuItem mntmCadastrarUsuarios;

	private UsuarioVO usuario;
	private PacienteVO paciente;

	private ArrayList<Component> componentesDaTela = new ArrayList<Component>();
	private TelaInternaConsultasEHorarios janelinhaPrincipalRecepcao = new TelaInternaConsultasEHorarios();
	private TelaInternaCadastroPaciente janelinhaCadastroPaciente = new TelaInternaCadastroPaciente();
	private TelaInternaBuscarPaciente janelinhaBuscarPaciente = new TelaInternaBuscarPaciente();
	private TelaInternaAgendaMedico janelinhaAgendaMedica;
	private TelaInternaProntuarioMedico janelinhaProntuario = new TelaInternaProntuarioMedico();
	private TelaInternaExcluirUsuario janelinhaExcluirUsuario = new TelaInternaExcluirUsuario();
	private TelaInternaCadastroUsuario janelinhaUsuario = new TelaInternaCadastroUsuario();
	private TelaInternaCadastrarAtualizarPaciente janelinhaCadastrarAtualizarPaciente = new TelaInternaCadastrarAtualizarPaciente();

	int width_int;
	int height_int;
	private JMenuItem mntmRelatorioDeProntuarios;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaGeral window = new TelaGeral(new UsuarioVO());
					window.setExtendedState(JFrame.MAXIMIZED_BOTH);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaGeral(UsuarioVO usuario) {
		this.setUsuario(usuario);
		// Abrir a janelinha em posição X,Y preferencial
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		int y = (int) (height * 0.1);
		int x = (int) (width * 0.2);
		width_int = (int) width;
		height_int = (int) height;

		setTitle("Clinica Médica");
		setBounds(5, 5, 1073, 700);
		// setBounds(x, y, width_int, height_int);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaGeral.class.getResource("/icones/icons8-caduceu-5.png")));
		getContentPane().setLayout(null);

		desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, screenSize.width, screenSize.height);
		desktopPane.setBackground(Color.WHITE);
		desktopPane.setVisible(true);
		desktopPane.repaint();
		getContentPane().add(desktopPane);

		this.repaint();

		initialize();
	}

	private void initialize() {

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// MENU PACIENTE
		mnPaciente = new JMenu("Paciente");
		mnPaciente.setFont(new Font("Arial", Font.BOLD, 16));
		mnPaciente.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-elenco-filled-38.png")));
		menuBar.add(mnPaciente);

		mntmCadastrarPaciente = new JMenuItem("Cadastrar/Atualizar Paciente");
		mntmCadastrarPaciente.setIcon(
				new ImageIcon(TelaGeral.class.getResource("/icones/icons8-adicionar-usuário-masculino-38.png")));
		mnPaciente.add(mntmCadastrarPaciente);
		mntmCadastrarPaciente.addActionListener(e -> {
			adicionarInternalFrame(janelinhaCadastroPaciente);
			this.repaint();
		});

		mntmBuscarPaciente = new JMenuItem("Buscar Paciente");
		mntmBuscarPaciente.setIcon(
				new ImageIcon(TelaGeral.class.getResource("/icones/icons8-procurar-usuário-masculino-38.png")));
		mnPaciente.add(mntmBuscarPaciente);
		mntmBuscarPaciente.addActionListener(e -> {
			adicionarInternalFrame(janelinhaBuscarPaciente);
			this.repaint();
		});

		// MENU MEDICO
		mnMedico = new JMenu("Medico");
		mnMedico.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-estetoscópio-filled-38.png")));
		mnMedico.setFont(new Font("Arial", Font.BOLD, 16));
		menuBar.add(mnMedico);

		mntmAgendaMedica = new JMenuItem("Agenda Medica");
		mntmAgendaMedica.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-pasta-dos-médicos-38.png")));
		mnMedico.add(mntmAgendaMedica);
		mntmAgendaMedica.addActionListener(e -> {
			janelinhaAgendaMedica = new TelaInternaAgendaMedico((MedicoVO) usuario);
			adicionarInternalFrame(janelinhaAgendaMedica);
			this.repaint();
		});

		mntmProntuario = new JMenuItem("Prontuario");
		mntmProntuario
				.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-adicionar-arquivo-filled-38.png")));
		mnMedico.add(mntmProntuario);
		mntmProntuario.addActionListener(e -> {
			adicionarInternalFrame(janelinhaProntuario);
			this.repaint();
		});

		JMenu mnRelatorios = new JMenu("Relatorios");
		mnRelatorios.setFont(new Font("Arial", Font.BOLD, 16));
		menuBar.add(mnRelatorios);
		mnRelatorios.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-gráfico-combinado.png")));

		mntmGerarRelatorioDeConsultas = new JMenuItem("Relatorio de Consultas");
		mnRelatorios.add(mntmGerarRelatorioDeConsultas);

		mntmRelatorioDeProntuarios = new JMenuItem("Relatorio de Prontuários");
		mnRelatorios.add(mntmRelatorioDeProntuarios);
		mntmGerarRelatorioDeConsultas.addActionListener(e -> {

		});

		// MENU ADM
		mnAdm = new JMenu("Administração");
		mnAdm.setFont(new Font("Arial", Font.BOLD, 16));
		mnAdm.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-escudo-de-usuário-filled-38.png")));
		menuBar.add(mnAdm);

		JMenu mnPacientes = new JMenu("Pacientes");
		mnPacientes.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-fila.png")));
		mnAdm.add(mnPacientes);

		JMenu mnFuncionarios = new JMenu("Funcionarios");
		mnFuncionarios.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-suporte-on-line-filled.png")));
		mnAdm.add(mnFuncionarios);

		JMenu mnUsuarios = new JMenu("Usuarios");
		mnUsuarios
				.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-adicionar-usuário-masculino.png")));
		mnAdm.add(mnUsuarios);

		mntmExcluirUsuarios = new JMenuItem("Excluir Usuarios");
		mnUsuarios.add(mntmExcluirUsuarios);
		mntmExcluirUsuarios.addActionListener(e -> {
			adicionarInternalFrame(janelinhaExcluirUsuario);
		});

		mntmCadastrarUsuarios = new JMenuItem("Cadastrar Usuarios");
		mnUsuarios.add(mntmCadastrarUsuarios);
		mntmCadastrarUsuarios.addActionListener(e -> {
			adicionarInternalFrame(janelinhaUsuario);
		});

		mnCadastrarAtualizarPacienteADM = new JMenuItem("Cadastrar/Atualizar Paciente");
		mnPacientes.add(mnCadastrarAtualizarPacienteADM);
		mnCadastrarAtualizarPacienteADM.addActionListener(e -> {
			adicionarInternalFrame(janelinhaCadastrarAtualizarPaciente);
		});

		mnBuscarExcluirPacienteADM = new JMenuItem("Buscar/Excluir Cadastro de Paciente");
		mnPacientes.add(mnBuscarExcluirPacienteADM);
		mnBuscarExcluirPacienteADM.addActionListener(e -> {

		});

		mntmCadastrarAtualizarFuncionarios = new JMenuItem("Cadastrar/Atualizar Funcionario");
		mnFuncionarios.add(mntmCadastrarAtualizarFuncionarios);
		mntmCadastrarAtualizarFuncionarios.addActionListener(e -> {

		});

		mntmBuscarExcluirFuncionario = new JMenuItem("Buscar/Excluir Funcionario");
		mnFuncionarios.add(mntmBuscarExcluirFuncionario);
		mntmBuscarExcluirFuncionario.addActionListener(e -> {

		});

		JMenu mnSair = new JMenu("Sair");
		mnSair.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-sair-50.png")));
		mnSair.setFont(new Font("Arial", Font.BOLD, 16));
		menuBar.add(mnSair);

		JMenuItem mntmLogout = new JMenuItem("Logout");
		mnSair.add(mntmLogout);
		mntmLogout.addActionListener(e -> {
			TelaDeLogin telaLogin = new TelaDeLogin();
			this.dispose();
			telaLogin.setVisible(true);
		});

		JMenuItem mntmFechar = new JMenuItem("Fechar");
		mnSair.add(mntmFechar);
		mntmFechar.addActionListener(e -> {
			System.exit(0);
		});

		this.verificarPermissaoParaTela();

	}

	public void fecharJanelinha(JInternalFrame janelinha) {
		componentesDaTela.remove(janelinha);
	}

	private void adicionarInternalFrame(JInternalFrame janelinha) {
		if (!componentesDaTela.contains(janelinha)) {
			desktopPane.add(janelinha);
			componentesDaTela.add(janelinha);
			janelinha.setBounds(0, 0, width_int, height_int - 70);
			janelinha.setVisible(true);
			janelinha.show();
		}
		janelinha.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent evt) {
				fecharJanelinha(evt.getInternalFrame());
			}
		});
	}

	public UsuarioVO getUsuario() {
		return this.usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	public PacienteVO getPaciente() {
		return this.paciente;
	}

	public void setPaciente(PacienteVO paciente) {
		this.paciente = paciente;
	}

	public void verificarPermissaoParaTela() {

		if (UsuarioVO.NIVEL_FUNCIONARIO.equals(this.usuario.getNivel())) {
			mnMedico.setEnabled(false);
			mnAdm.setEnabled(false);
			// chamar telas agenda e/ou cadastro paciente
		} else if (UsuarioVO.NIVEL_MEDICO.equals(this.usuario.getNivel())) {
			mnPaciente.setEnabled(false);
			mnAdm.setEnabled(false);
			janelinhaAgendaMedica = new TelaInternaAgendaMedico((MedicoVO) usuario);
			adicionarInternalFrame(janelinhaAgendaMedica);
		} else if (UsuarioVO.NIVEL_ADMIN.equals(this.usuario.getNivel())) {
			mnPaciente.setEnabled(false);
			mnMedico.setEnabled(false);
			mnAdm.setEnabled(true);
		}
	}

}

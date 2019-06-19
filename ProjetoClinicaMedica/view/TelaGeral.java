package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import model.vo.UsuarioVO;
import view.funcionarios.medico.TelaInternaAgendaMedico;
import view.funcionarios.medico.TelaInternaProntuarioMedico;
import view.funcionarios.secretaria.TelaInternaBuscarPaciente;
import view.funcionarios.secretaria.TelaInternaCadastroPaciente;
import view.funcionarios.secretaria.TelaInternaConsultasEHorarios;

public class TelaGeral extends JFrame {

	private static final long serialVersionUID = -1850173995181721117L;
	private JDesktopPane desktopPane;
	private JMenuBar menuBar;
	private JMenu mnPaciente;
	private JMenu mnMedico;
	private JMenuItem mntmBuscarPaciente;
	private JMenuItem mntmCadastrarPaciente;
	private JMenuItem mntmAgendaMedica;
	private JMenuItem mntmProntuario;
	private JMenuItem mntmGerarRelatoriosDeFuncionarios;
	private JMenuItem mntmGerarRelatorioDoEstoque;
	private JMenuItem mntmCadastrarAtualizarFuncionarios;
	private JMenuItem mntmBuscarExcluirFuncionario;
	private JMenuItem mnBuscarExcluirPacienteADM;
	private JMenuItem mnCadastrarAtualizarPacienteADM;
	private JMenuItem mntmGerarRelatorioDePaciente;
	private JMenu mnUsuarios;
	private JMenuItem mntmExcluirUsuarios;
	private JMenuItem mntmCadastrarUsuarios;
	private UsuarioVO usuario;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaGeral window = new TelaGeral();
					window.setVisible(true);
					window.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaGeral() {
		// Abrir a janelinha em posição X,Y preferencial
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		int y = (int) (height * 0.1);
		int x = (int) (width * 0.2);
		int width_int = (int) width;
		int height_int = (int) height;

		setTitle("Clinica Médica");
		setBounds(5, 5, 1073, 700);
		//setBounds(x, y, width_int, height_int);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaGeral.class.getResource("/icones/icons8-caduceu-5.png")));
		getContentPane().setLayout(null);

		desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, screenSize.width, screenSize.height);
		desktopPane.setBackground(Color.WHITE);
		desktopPane.setVisible(true);
		desktopPane.repaint();
		getContentPane().add(desktopPane);


		TelaInternaConsultasEHorarios janelinhaInternaPrincipalRecepcao = new TelaInternaConsultasEHorarios();
		desktopPane.add(janelinhaInternaPrincipalRecepcao);
		janelinhaInternaPrincipalRecepcao.setBounds(0, 0, width_int, height_int);
		janelinhaInternaPrincipalRecepcao.setVisible(true);
		janelinhaInternaPrincipalRecepcao.show();

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

			TelaInternaCadastroPaciente janelinhaCadastroPaciente = new TelaInternaCadastroPaciente();
			desktopPane.add(janelinhaCadastroPaciente);
			janelinhaCadastroPaciente.setVisible(true);
			janelinhaCadastroPaciente.show();
			this.repaint();
		});

		mntmBuscarPaciente = new JMenuItem("Buscar Paciente");
		mntmBuscarPaciente.setIcon(	new ImageIcon(TelaGeral.class.getResource("/icones/icons8-procurar-usuário-masculino-38.png")));
		mnPaciente.add(mntmBuscarPaciente);
		mntmBuscarPaciente.addActionListener(e -> {

			TelaInternaBuscarPaciente janelinhaBuscarPaciente = new TelaInternaBuscarPaciente();
			desktopPane.add(janelinhaBuscarPaciente);
			janelinhaBuscarPaciente.setVisible(true);
			janelinhaBuscarPaciente.show();
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

			TelaInternaAgendaMedico janelinhaAgendaMedica = new TelaInternaAgendaMedico();
			desktopPane.add(janelinhaAgendaMedica);
			janelinhaAgendaMedica.setVisible(true);
			janelinhaAgendaMedica.show();
			this.repaint();

		});

		mntmProntuario = new JMenuItem("Prontuario");
		mntmProntuario
		.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-adicionar-arquivo-filled-38.png")));
		mnMedico.add(mntmProntuario);
		mntmProntuario.addActionListener(e -> {

			TelaInternaProntuarioMedico janelinhaProntuario = new TelaInternaProntuarioMedico();
			desktopPane.add(janelinhaProntuario);
			janelinhaProntuario.setVisible(true);
			janelinhaProntuario.show();
			this.repaint();

		});

		// MENU ADM
		JMenu mnAdm = new JMenu("Adm");
		mnAdm.setFont(new Font("Arial", Font.BOLD, 16));
		mnAdm.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-escudo-de-usuário-filled-38.png")));
		menuBar.add(mnAdm);

		JMenu mnPacientes = new JMenu("Pacientes");
		mnPacientes.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-fila.png")));
		mnAdm.add(mnPacientes);

		JMenu mnFuncionarios = new JMenu("Funcionarios");
		mnFuncionarios.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-suporte-on-line-filled.png")));
		mnAdm.add(mnFuncionarios);

		JMenu mnRelatorios = new JMenu("Relatorios");
		mnRelatorios.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-gráfico-combinado.png")));
		mnAdm.add(mnRelatorios);

		mnUsuarios = new JMenu("Usuarios");
		mnUsuarios
		.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-adicionar-usuário-masculino.png")));
		mnAdm.add(mnUsuarios);

		mntmExcluirUsuarios = new JMenuItem("Excluir Usuarios");
		mnUsuarios.add(mntmExcluirUsuarios);
		mntmExcluirUsuarios.addActionListener(e -> {

		});

		mntmCadastrarUsuarios = new JMenuItem("Cadastrar Usuarios");
		mnUsuarios.add(mntmCadastrarUsuarios);
		mntmCadastrarUsuarios.addActionListener(e -> {

		});

		mnCadastrarAtualizarPacienteADM = new JMenuItem("Cadastrar/Atualizar Paciente");
		mnPacientes.add(mnCadastrarAtualizarPacienteADM);
		mnCadastrarAtualizarPacienteADM.addActionListener(e -> {

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

		mntmGerarRelatorioDePaciente = new JMenuItem("Gerar Relatorio de Paientes");
		mnRelatorios.add(mntmGerarRelatorioDePaciente);
		mntmGerarRelatorioDePaciente.addActionListener(e -> {

		});

		mntmGerarRelatoriosDeFuncionarios = new JMenuItem("Gerar Relatorios de Funcionarios");
		mnRelatorios.add(mntmGerarRelatoriosDeFuncionarios);
		mntmGerarRelatoriosDeFuncionarios.addActionListener(e -> {

		});

		mntmGerarRelatorioDoEstoque = new JMenuItem("Gerar Relatorio do Estoque");
		mnRelatorios.add(mntmGerarRelatorioDoEstoque);
		mntmGerarRelatorioDoEstoque.addActionListener(e -> {

		});

	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}
}

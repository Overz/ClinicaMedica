package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import controller.ControllerConsulta;
import controller.ControllerPaciente;
import controller.ControllerProntuario;
import controller.ControllerRelatorio;
import model.vo.FuncionarioVO;
import model.vo.ConsultaVO;
import model.vo.MedicoVO;
import model.vo.PacienteVO;
import model.vo.UsuarioVO;
import model.vo.ProntuarioVO;
import view.adm.paciente.TelaInternaExcluirPaciente;
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
	private JMenuItem mntmGerarRelatorioPaciente;
	private JMenuItem mntmRelatorioDeProntuarios;
	private JMenuItem mnExcluirPacientes;
	private JMenuItem mnCadastroDePaciente;
	private JMenuItem mntmExcluirUsuarios;
	private JMenuItem mntmCadastroDeUsuarios;
	private JMenuItem mntmAgenda;

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
	private TelaInternaExcluirPaciente janelinhaExcluirPaciente = new TelaInternaExcluirPaciente();

	int width_int;
	int height_int;
	private ArrayList<PacienteVO> pacienteVO;
	private ArrayList<ConsultaVO> consultasVO;
	private ArrayList<ProntuarioVO> prontuarioVO;
	
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

	/**
	 * Criação de parametros para o JFrame.
	 * Se a Tela no Design não abrir, comentar ' setBounds(x, y, width_int, height_int); ', e substituir pelo outro padrão com numeros.
	 * @param usuario
	 */
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

		mntmAgenda = new JMenuItem("Agenda");
		mntmAgenda.setFont(new Font("Arial", Font.BOLD, 16));
		mntmAgenda.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-calendário-12-50.png")));
		menuBar.add(mntmAgenda);
		mntmAgenda.addActionListener(e -> {
			if (usuario.getNivel().equals(UsuarioVO.NIVEL_ADMIN)
					|| usuario.getNivel().equals(UsuarioVO.NIVEL_FUNCIONARIO)) {
				janelinhaPrincipalRecepcao.setFuncionario((FuncionarioVO) usuario);
				adicionarInternalFrame(janelinhaPrincipalRecepcao);
			} else if (usuario.getNivel().equals(UsuarioVO.NIVEL_MEDICO)) {
				janelinhaAgendaMedica = new TelaInternaAgendaMedico((MedicoVO) usuario);
				adicionarInternalFrame(janelinhaAgendaMedica);
			}
		});

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

		//Menu Relatorio
		JMenu mnRelatorios = new JMenu("Relatorios");
		mnRelatorios.setFont(new Font("Arial", Font.BOLD, 16));
		mnRelatorios.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-gráfico-combinado.png")));
		menuBar.add(mnRelatorios);

		mntmGerarRelatorioDeConsultas = new JMenuItem("Relatorio de Consultas");
		mntmGerarRelatorioDeConsultas
				.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-arquivo-estatístico-50.png")));
		mnRelatorios.add(mntmGerarRelatorioDeConsultas);
		mntmGerarRelatorioDeConsultas.addActionListener(e -> {
			JFileChooser jfc = new JFileChooser();
			jfc.setDialogTitle("Salvar relatório em...");
		
			int resultado = jfc.showSaveDialog(null);
			if (resultado == JFileChooser.APPROVE_OPTION) {
				
				ControllerRelatorio controllerRelatorio = new ControllerRelatorio();
				ControllerConsulta controllerConsulta = new ControllerConsulta();
				
				ArrayList<ConsultaVO> vo = (ArrayList<ConsultaVO>) controllerConsulta.consultarTodos();
				atualizarListas(vo);
				String caminhoEscolhido = jfc.getSelectedFile().getAbsolutePath();
				String mensagem = controllerRelatorio.gerarRelatorioConsultas(consultasVO , caminhoEscolhido);

				JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		
		mntmGerarRelatorioPaciente = new JMenuItem("Relatorio de Pacientes");
		mntmGerarRelatorioPaciente.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-relatório-gráfico-filled-38.png")));
		mnRelatorios.add(mntmGerarRelatorioPaciente);
		mntmGerarRelatorioPaciente.addActionListener(e -> {
			
			JFileChooser jfc = new JFileChooser();
			jfc.setDialogTitle("Salvar relatório em...");

			int resultado = jfc.showSaveDialog(null);
			if (resultado == JFileChooser.APPROVE_OPTION) {

				String caminhoEscolhido = jfc.getSelectedFile().getAbsolutePath();
				
				ControllerRelatorio controllerRelatorio = new ControllerRelatorio();
				ControllerPaciente controllerPaciente = new ControllerPaciente();
				
				ArrayList<PacienteVO> vo =  (ArrayList<PacienteVO>) controllerPaciente.consultarTodos();
				atualizarListas(vo);
				
				String mensagem = controllerRelatorio.gerarRelatorioPaciente(pacienteVO, caminhoEscolhido);

				JOptionPane.showMessageDialog(null, mensagem);
			}
		});

		mntmRelatorioDeProntuarios = new JMenuItem("Relatorio de Prontuários");
		mntmRelatorioDeProntuarios
				.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-analisar-currículos-50.png")));
		mnRelatorios.add(mntmRelatorioDeProntuarios);
		mntmRelatorioDeProntuarios.addActionListener(e -> {
			
			JFileChooser jfc = new JFileChooser();
			jfc.setDialogTitle("Salvar relatório em...");
			
			int resultado = jfc.showSaveDialog(null);
			if (resultado == JFileChooser.APPROVE_OPTION) {
				
				String caminhoEscolhido = jfc.getSelectedFile().getAbsolutePath();
				
				ControllerRelatorio controllerRelatorio = new ControllerRelatorio();
				ControllerProntuario controllerProntuario = new ControllerProntuario();
				
				ArrayList<ProntuarioVO> vo = (ArrayList<ProntuarioVO>) controllerProntuario.consultarTodos(); // TODO REALIZAR CONSULTA NO DAO
				atualizarListas(vo);
				
				String mensagem = controllerRelatorio.gerarRelatorio(prontuarioVO, caminhoEscolhido);
			}
		});

		// MENU ADM
		mnAdm = new JMenu("Administração");
		mnAdm.setFont(new Font("Arial", Font.BOLD, 16));
		mnAdm.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-escudo-de-usuário-filled-38.png")));
		menuBar.add(mnAdm);

		JMenu mnPacientes = new JMenu("Pacientes");
		mnPacientes.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-fila.png")));
		mnAdm.add(mnPacientes);

		JMenu mnUsuarios = new JMenu("Usuarios");
		mnUsuarios
				.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-adicionar-usuário-masculino.png")));
		mnAdm.add(mnUsuarios);

		mntmExcluirUsuarios = new JMenuItem("Excluir Usuarios");
		mnUsuarios.add(mntmExcluirUsuarios);
		mntmExcluirUsuarios.addActionListener(e -> {
			adicionarInternalFrame(janelinhaExcluirUsuario);
		});

		mntmCadastroDeUsuarios = new JMenuItem("Cadastro de Usuarios");
		mnUsuarios.add(mntmCadastroDeUsuarios);
		mntmCadastroDeUsuarios.addActionListener(e -> {
			adicionarInternalFrame(janelinhaUsuario);
		});

		mnCadastroDePaciente = new JMenuItem("Cadastro de Pacientes");
		mnPacientes.add(mnCadastroDePaciente);
		mnCadastroDePaciente.addActionListener(e -> {
			adicionarInternalFrame(janelinhaCadastroPaciente);
		});

		mnExcluirPacientes = new JMenuItem("Excluir Pacientes");
		mnPacientes.add(mnExcluirPacientes);
		mnExcluirPacientes.addActionListener(e -> {
			adicionarInternalFrame(janelinhaExcluirPaciente);
			this.repaint();
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

	/**
	 * Método que impede abrir o mesmo JInternalFrame mais de uma vez.
	 * @param janelinha
	 */
	public void fecharJanelinha(JInternalFrame janelinha) {
		componentesDaTela.remove(janelinha);
	}

	/**
	 * Método que confere se o JInternalFrame esta Aberto ou não, se sim, impede de abrir o mesmo novamente.
	 * @param janelinha
	 */
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


	/**
	 * Método que verifica a permissão do usuario logado, para disponibilizar Apenas os campos necessarios na tela.
	 */
	protected void verificarPermissaoParaTela() {

		if (UsuarioVO.NIVEL_FUNCIONARIO.equals(this.usuario.getNivel())) {

			//mntmAgenda.add(janelinhaPrincipalRecepcao);
			mnMedico.setEnabled(false);
			mnAdm.setEnabled(false);
			janelinhaPrincipalRecepcao = new TelaInternaConsultasEHorarios();
			janelinhaPrincipalRecepcao.setFuncionario((FuncionarioVO) usuario);
			adicionarInternalFrame(janelinhaPrincipalRecepcao);
		} else if (UsuarioVO.NIVEL_MEDICO.equals(this.usuario.getNivel())) {

			//mntmAgenda.add(janelinhaAgendaMedica);
			mnPaciente.setEnabled(false);
			mnAdm.setEnabled(false);
			janelinhaAgendaMedica = new TelaInternaAgendaMedico((MedicoVO) usuario);
			adicionarInternalFrame(janelinhaAgendaMedica);
		} else if (UsuarioVO.NIVEL_ADMIN.equals(this.usuario.getNivel())) {
			mntmAgenda.setEnabled(false);
			mnPaciente.setEnabled(true);
			mnMedico.setEnabled(false);
			mnAdm.setEnabled(true);
		}
	}
	
	/**
	 * Atualizar lista para gerar o Relatorio;
	 * @param lista
	 */
	private void atualizarListas(ArrayList<?> lista) {
		
		this.pacienteVO = (ArrayList<PacienteVO>) lista;
		this.consultasVO = (ArrayList<ConsultaVO>) lista;
		this.prontuarioVO = (ArrayList<ProntuarioVO>) lista;
		
	}
	
	// Getter Setter
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
}

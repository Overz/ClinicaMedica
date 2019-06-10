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

import view.funcionarios.medico.TelaInternaAgendaMedico;
import view.funcionarios.secretaria.TelaInternaCadastroPaciente;
import view.funcionarios.secretaria.TelaInternaConsultasEHorarios;

public class TelaGeral extends JFrame {

	private static TelaGeral window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new TelaGeral();
					window.setVisible(true);
					//window.setExtendedState(MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JDesktopPane desktopPane;
	private JMenuBar menuBar;
	private JMenu mnPaciente;
	private JMenuItem mntmBuscarCliente;
	private JMenu mnMedico;
	private JMenuItem mntmCadastrarPaciente;
	private JMenuItem mntmAgendaMedica;
	private JMenuItem mntmCadastrarProntuario;
	private JMenuItem mntmPesquisarProntuario;

	/**
	 * Create the application.
	 */
	public TelaGeral() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		int w2 = (int) (width * 0.2);
		int h2 = (int) (height * 0.1);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaGeral.class.getResource("/icones/icons8-caduceu-5.png")));
		setTitle("Clinica Médica");
		setBounds(w2, h2, 1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		desktopPane.setVisible(true);
		
		
		/*double w2 = ((width/2) - this.getBounds().getWidth()/2 );
		double h2 = ((height/2) - this.getBounds().getHeight()/2);*/
		desktopPane.setBounds(0, 0, screenSize.width, screenSize.height);
		getContentPane().add(desktopPane);

		
		//double h2 = ((this.getBounds().getHeight()/2) - height/2);
		
		TelaInternaConsultasEHorarios janelinhaInternaPrincipalRecepcao = new TelaInternaConsultasEHorarios();

		//TODO .. PROBLEMA AO INICIALIZAR A TELA PADRAO DA RECEPÇÂO
		
		desktopPane.add(janelinhaInternaPrincipalRecepcao);
		janelinhaInternaPrincipalRecepcao.setVisible(true);
		janelinhaInternaPrincipalRecepcao.show();
		
		/*window.add(desktopPane.add(janelinhaInternaPrincipalRecepcao));
		janelinhaInternaPrincipalRecepcao.setVisible(true);
		janelinhaInternaPrincipalRecepcao.show();*/

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// MENU PACIENTE
		mnPaciente = new JMenu("Paciente");
		mnPaciente.setFont(new Font("Arial", Font.BOLD, 16));
		mnPaciente.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-elenco-filled-38.png")));
		menuBar.add(mnPaciente);

		mntmCadastrarPaciente = new JMenuItem("Cadastrar/Atualizar Paciente");
		mntmCadastrarPaciente.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-adicionar-usuário-masculino-38.png")));
		mnPaciente.add(mntmCadastrarPaciente);
		mntmCadastrarPaciente.addActionListener(e -> {
			
			TelaInternaCadastroPaciente janelinhaCadastroPaciente = new TelaInternaCadastroPaciente();
			desktopPane.add(janelinhaCadastroPaciente);
			janelinhaCadastroPaciente.setVisible(true);
			janelinhaCadastroPaciente.show();
			window.repaint();
			
			
		});

		mntmBuscarCliente = new JMenuItem("Buscar Cliente");
		mntmBuscarCliente.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-procurar-usuário-masculino-38.png")));
		mnPaciente.add(mntmBuscarCliente);
		mntmBuscarCliente.addActionListener(e -> {
			
			

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
			getContentPane().add(janelinhaAgendaMedica);
			janelinhaAgendaMedica.setVisible(true);
			janelinhaAgendaMedica.show();
			
		});

		mntmCadastrarProntuario = new JMenuItem("Cadastrar Prontuario");
		mntmCadastrarProntuario.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-adicionar-arquivo-filled-38.png")));
		mnMedico.add(mntmCadastrarProntuario);
		mntmCadastrarProntuario.addActionListener(e -> {
			
			
			
		});
		
		mntmPesquisarProntuario = new JMenuItem("Pesquisar Prontuario");
		mntmPesquisarProntuario.setIcon(new ImageIcon(TelaGeral.class.getResource("/icones/icons8-atualizar-arquivo-filled-38.png")));
		mnMedico.add(mntmPesquisarProntuario);
		mntmPesquisarProntuario.addActionListener(e -> {
			
			
			
		});

		// MENU ADM
		JMenu mnAdm = new JMenu("Adm");
		menuBar.add(mnAdm);



	}
}

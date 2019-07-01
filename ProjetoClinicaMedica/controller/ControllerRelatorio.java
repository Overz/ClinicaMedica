package controller;

import java.util.ArrayList;

import model.vo.ConsultaVO;
import model.vo.PacienteVO;
import model.vo.ProntuarioVO;
import util.relatorios.GeradorRelatorioConsulta;
import util.relatorios.GeradorRelatorioPaciente;
import util.relatorios.GeradorRelatorioProntuario;

public class ControllerRelatorio {

	public static final String TIPO_RELATORIO = "xls";

	public String gerarRelatorioPaciente(ArrayList<PacienteVO> vo, String caminhoEscolhido) {
		GeradorRelatorioPaciente gerador = new GeradorRelatorioPaciente();
		return gerador.gerarPlanilhaPaciente(vo, caminhoEscolhido);
	}

	public String gerarRelatorioConsultas(ArrayList<ConsultaVO> vo, String caminhoEscolhido) {
		GeradorRelatorioConsulta gerador = new GeradorRelatorioConsulta();
		return gerador.gerarPlanilhaConsulta(vo, caminhoEscolhido);
	}

	public String gerarRelatorioProntuario(ArrayList<ProntuarioVO> vo, String caminhoEscolhido) {
		GeradorRelatorioProntuario gerador = new GeradorRelatorioProntuario();
		return gerador.gerarPlanilhaConsulta(vo, caminhoEscolhido);
	}

	public String gerarRelatorioConsultaComFiltro(ArrayList<ConsultaVO> vo, String caminhoEscolhido) {
		GeradorRelatorioConsulta gerador = new GeradorRelatorioConsulta();
		return gerador.gerarPlanilhaConsultaComFiltro(vo, caminhoEscolhido);
	}
}

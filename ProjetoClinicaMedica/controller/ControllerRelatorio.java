package controller;

import java.util.List;

import model.vo.ConsultaVO;
import model.vo.PacienteVO;
import model.vo.ProntuarioVO;
import util.relatorios.GeradorRelatorioConsulta;
import util.relatorios.GeradorRelatorioPaciente;
import util.relatorios.GeradorRelatorioProntuario;

public class ControllerRelatorio {

	public static final String TIPO_RELATORIO = "xls";

	public String gerarRelatorioPaciente(List<PacienteVO> vo, String caminhoEscolhido) {
		GeradorRelatorioPaciente gerador = new GeradorRelatorioPaciente();
		return gerador.gerarPlanilhaPaciente(vo, caminhoEscolhido);
	}

	public String gerarRelatorioConsultas(List<ConsultaVO> vo, String caminhoEscolhido) {
		GeradorRelatorioConsulta gerador = new GeradorRelatorioConsulta();
		return gerador.gerarPlanilhaConsulta(vo, caminhoEscolhido);
	}

	public String gerarRelatorio(List<ProntuarioVO> prontuarioVO, String caminhoEscolhido) {
		GeradorRelatorioProntuario gerador = new GeradorRelatorioProntuario();
		return gerador.gerarPlanilhaConsulta(prontuarioVO, caminhoEscolhido);
	}
	

}

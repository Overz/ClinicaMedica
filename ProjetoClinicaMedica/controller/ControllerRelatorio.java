package controller;

import java.util.List;

import model.vo.ConsultaVO;
import model.vo.PacienteVO;
import util.relatorios.GeradorRelatorioConsulta;
import util.relatorios.GeradorRelatorioPaciente;

public class ControllerRelatorio {

	public static final String TIPO_RELATORIO = "xls";

	public String gerarRelatorioPaciente(List<PacienteVO> vo, String caminhoEscolhido) {
		GeradorRelatorioPaciente geradorExcel = new GeradorRelatorioPaciente();
		return geradorExcel.gerarPlanilhaPaciente(vo, caminhoEscolhido);
	}

	public String gerarRelatorioConsultas(List<ConsultaVO> vo, String caminhoEscolhido) {
		GeradorRelatorioConsulta geradorExcel = new GeradorRelatorioConsulta();
		return geradorExcel.gerarPlanilhaConsulta(vo, caminhoEscolhido);
	}
	

}

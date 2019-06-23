package controller;

import java.util.List;

import model.vo.PacienteVO;
import util.GeradorPlanilha;

public class ControllerRelatorio {

	public static final String TIPO_RELATORIO = "xls";

	public String gerarRelatorio(List<PacienteVO> vo, String caminhoEscolhido) {
		GeradorPlanilha geradorExcel = new GeradorPlanilha();
		return geradorExcel.gerarPlanilhaProdutos(vo, caminhoEscolhido);
	}
	

}

package util.relatorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.vo.ProntuarioVO;

public class GeradorRelatorioProntuario {

	/**
	 * Gera uma planilha Excel (formato .xlsx) a partir de uma lista de produtos
	 * 
	 * @param caminhoArquivo onde a planilha será salva
	 * @param List<> a lista do objeto que deseja fazer o relatorio
	 * 
	 * @return uma mensagem informando ao usuário o que ocorreu.
	 */
	public String gerarPlanilhaConsulta(List<ProntuarioVO> consulta, String caminhoArquivo) {
		String[] columnName = { "# Prontuario", "Data do Prontuario", "# Paciênte", "Nome Paciênte", "# Médico", "Observações" };

		// Criar a planilha (Workbook)
		XSSFWorkbook planilha = new XSSFWorkbook();

		// Criar uma aba (Sheet)
		XSSFSheet abaPlanilha = planilha.createSheet("Consultas");


		XSSFRow headerRow = abaPlanilha.createRow(0);
		//Criar cabeçalho apartir do array
		for (int i = 0; i < columnName.length; i++) {
			Cell novaCelula = headerRow.createCell(i);
			novaCelula.setCellValue(columnName[i]);
		}
		// Preencher as linhas com os objetos
		int row = 1;
		for (ProntuarioVO p : consulta) {
			XSSFRow linhaAtual = abaPlanilha.createRow(row++);

			linhaAtual.createCell(0).setCellValue(p.getIdProntuario());
			linhaAtual.createCell(1).setCellValue(p.getDtProntuario().toLocalDate().toString());			
			linhaAtual.createCell(1).setCellValue(p.getPaciente().getIdPaciente());
			linhaAtual.createCell(1).setCellValue(p.getPaciente().getNome());
			linhaAtual.createCell(1).setCellValue(p.getMedico().getIdMedico());
			linhaAtual.createCell(1).setCellValue(p.getMedico().getNome());
			linhaAtual.createCell(1).setCellValue(p.getObservacoes());
		}

		//Ajusta o Tamanho de todas as colunas conforme o conteúdo
		for (int i = 0; i < columnName.length; i++) {
			abaPlanilha.autoSizeColumn(i);
		}
		
		// Salvar o arquivo gerado no disco
		return salvarNoDisco(planilha, caminhoArquivo);
	}

	private String salvarNoDisco(XSSFWorkbook planilha, String caminhoArquivo) {
		String mensagem = "";
		FileOutputStream saida = null;

		try {
			saida = new FileOutputStream(new File(caminhoArquivo + ".xlsx"));
			planilha.write(saida);
			mensagem = "Planilha gerada com sucesso!";
		} catch (FileNotFoundException e) {
			mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + ".xlsx";
			System.out.println("Causa: " + e.getMessage());
		} catch (IOException e) {
			mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + ".xlsx";
			System.out.println("Causa: " + e.getMessage());
		} finally {
			if (saida != null) {
				try {
					saida.close();
					planilha.close();
				} catch (IOException e) {
					mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + ".xlsx";
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}

		return mensagem;
	}
}

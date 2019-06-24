package util.relatorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.vo.ConsultaVO;

public class GeradorRelatorioConsulta {

	/**
	 * Gera uma planilha Excel (formato .xlsx) a partir de uma lista de produtos
	 * 
	 * @param caminhoArquivo onde a planilha será salva
	 * @param List<> a lista do objeto que deseja fazer o relatorio
	 * 
	 * @return uma mensagem informando ao usuário o que ocorreu.
	 */
	public String gerarPlanilhaConsulta(List<ConsultaVO> vo, String caminhoArquivo) {
		String[] columnNames = { "# Consulta", "Data de Nascimento", "# Paciênte", "Nome Paciênte", "# Funcionario", "Funcionario Atendente" };

		XSSFWorkbook planilha = new XSSFWorkbook();

		//Cria uma aba na planilha (Sheet)
		XSSFSheet abaPlanilha = planilha.createSheet("Consultas");

		Row headerRow = abaPlanilha.createRow(0);

		//Cria o cabeçalho a partir de um array columns
		for (int i = 0; i < columnNames.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columnNames[i]);
		}

		// Preencher as linhas com os produtos
		int row = 1;
		for (ConsultaVO c : vo) {
			Row linhaAtual = abaPlanilha.createRow(row++);

			linhaAtual.createCell(0).setCellValue(c.getIdConsulta());
			linhaAtual.createCell(1).setCellValue(c.getData_consulta().toLocalDate().toString());
			linhaAtual.createCell(2).setCellValue(c.getPaciente().getIdPaciente());
			linhaAtual.createCell(3).setCellValue(c.getPaciente().getNome());
			linhaAtual.createCell(4).setCellValue(c.getMedico().getIdMedico());
			linhaAtual.createCell(5).setCellValue(c.getMedico().getNome());
			linhaAtual.createCell(6).setCellValue(c.getFuncionario().getIdFuncionario());
			linhaAtual.createCell(7).setCellValue(c.getFuncionario().getNome());

		}

		//Ajusta o tamanho de todas as colunas conforme a largura do conteudo
		for (int i = 0; i < columnNames.length; i++) {
			abaPlanilha.autoSizeColumn(i);
		}
		
		// Salvar o arquivo gerado no disco
		return salvarNoDisco(planilha, caminhoArquivo);
	}

	// Criação do Caminho para salvar
	private String salvarNoDisco(XSSFWorkbook planilha, String caminhoArquivo) {
		String mensagem = "";
		FileOutputStream saida = null;

		try {
			saida = new FileOutputStream(new File(caminhoArquivo + ".xls"));
			planilha.write(saida);
			mensagem = "Planilha gerada com sucesso!";
		} catch (FileNotFoundException e) {
			mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + ".xls";
			System.out.println("Causa: " + e.getMessage());
		} catch (IOException e) {
			mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + ".xls";
			System.out.println("Causa: " + e.getMessage());
		} finally {
			if (saida != null) {
				try {
					saida.close();
					planilha.close();
				} catch (IOException e) {
					mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + ".xls";
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}

		return mensagem;
	}
}
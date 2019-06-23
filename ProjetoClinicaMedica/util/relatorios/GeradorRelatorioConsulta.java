package util.relatorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
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
	public String gerarPlanilhaConsulta(List<ConsultaVO> consulta, String caminhoArquivo) {
		// Criar a planilha (Workbook)
		XSSFWorkbook planilha = new XSSFWorkbook();

		// Criar uma aba (Sheet)
		XSSFSheet aba = planilha.createSheet("Consultas");

		int linhaAtual = 0;

		// Criar o cabeçalho (header)
		String[] nomesColunas = { "# Consulta", "Data de Nascimento", "# Paciênte", "Nome Paciênte", "# Funcionario", "Funcionario Atendente" };
		criarCabecalho(nomesColunas, aba, linhaAtual);

		// Preencher as linhas com os produtos
		criarLinhasProdutos(consulta, aba, linhaAtual);

		// Salvar o arquivo gerado no disco
		return salvarNoDisco(planilha, caminhoArquivo, ".xlsx");
	}

	private void criarLinhasProdutos(List<ConsultaVO> consulta, XSSFSheet aba, int posicaoLinhaAtual) {
		for (ConsultaVO c : consulta) {
			XSSFRow linhaAtual = aba.createRow(posicaoLinhaAtual);
			
			linhaAtual.createCell(0).setCellValue(c.getIdConsulta());
			linhaAtual.createCell(1).setCellValue(c.getData_consulta().toLocalDate().toString());
			linhaAtual.createCell(2).setCellValue(c.getPaciente().getIdPaciente());
			linhaAtual.createCell(3).setCellValue(c.getPaciente().getNome());
			linhaAtual.createCell(4).setCellValue(c.getMedico().getIdMedico());
			linhaAtual.createCell(5).setCellValue(c.getMedico().getNome());
			linhaAtual.createCell(6).setCellValue(c.getFuncionario().getIdFuncionario());
			linhaAtual.createCell(7).setCellValue(c.getFuncionario().getNome());
			
			posicaoLinhaAtual++;
		}
	}

	private void criarCabecalho(String[] nomesColunas, XSSFSheet aba, int posicaoLinhaAtual) {
		Row linhaAtual = aba.createRow(posicaoLinhaAtual);
		
		posicaoLinhaAtual++;
		
		for (int i = 0; i < nomesColunas.length; i++) {
			Cell novaCelula = linhaAtual.createCell(i);
			novaCelula.setCellValue(nomesColunas[i]);
		}
		
	}
	private String salvarNoDisco(XSSFWorkbook planilha, String caminhoArquivo, String extensao) {
		String mensagem = "";
		FileOutputStream saida = null;
		
		try {
			saida = new FileOutputStream(new File(caminhoArquivo + extensao));
			planilha.write(saida);
			mensagem = "Planilha gerada com sucesso!";
		} catch (FileNotFoundException e) {
			mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} catch (IOException e) {
			mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} finally {
			if (saida != null) {
				try {
					saida.close();
					planilha.close();
				} catch (IOException e) {
					mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}
		
		return mensagem;
	}


}

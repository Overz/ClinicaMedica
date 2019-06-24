package util.relatorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.vo.PacienteVO;

public class GeradorRelatorioPaciente {
	

	/**
	 * Gera uma planilha Excel (formato .xlsx) a partir de uma lista de produtos
	 * 
	 * @param caminhoArquivo onde a planilha será salva
	 * @param List<> a lista do objeto que deseja fazer o relatorio
	 * 
	 * @return uma mensagem informando ao usuário o que ocorreu.
	 */
	public String gerarPlanilhaPaciente(List<PacienteVO> consulta, String caminhoArquivo) {
		String[] columnName = { "#", "Nome", "Data de Nascimento", "CPF", "Telefone", "Sexo", "Tipo Sanguíneo",
				"Estado", "Cidade", "Bairro", "Rua", "Numero", "CEP" };

		//Cria Planilha
		XSSFWorkbook planilha = new XSSFWorkbook();

		// Criar uma aba (Sheet)
		XSSFSheet abaPlanilha = planilha.createSheet("Pacientes");

		XSSFRow headerRow = abaPlanilha.createRow(0);
		//Criar cabeçalho apartir do array
		for (int i = 0; i < columnName.length; i++) {
			Cell novaCelula = headerRow.createCell(i);
			novaCelula.setCellValue(columnName[i]);
		}

		// Preencher as linhas com os pacientes
		int row = 1;
		for (PacienteVO p : consulta) {
			XSSFRow linhaAtual = abaPlanilha.createRow(row++);
			
			linhaAtual.createCell(0).setCellValue(p.getIdPaciente());
			linhaAtual.createCell(1).setCellValue(p.getNome());
			linhaAtual.createCell(2).setCellValue(Date.valueOf(p.getDtNascimento()));
			linhaAtual.createCell(3).setCellValue(p.getCpf());
			linhaAtual.createCell(4).setCellValue(p.getTelefone());
			linhaAtual.createCell(5).setCellValue(p.getSexo());
			linhaAtual.createCell(6).setCellValue(p.getTipoSanguineo());
			linhaAtual.createCell(7).setCellValue(p.getEstado());
			linhaAtual.createCell(8).setCellValue(p.getCidade());
			linhaAtual.createCell(9).setCellValue(p.getBairro());
			linhaAtual.createCell(10).setCellValue(p.getRua());
			linhaAtual.createCell(11).setCellValue(p.getNumero());
			linhaAtual.createCell(12).setCellValue(p.getCep());
			
		}
		
		//Ajusta o Tamanho de todas as colunas conforme o conteúdo
		for (int i = 0; i < columnName.length; i++) {
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
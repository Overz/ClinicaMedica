package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.vo.PacienteVO;

public class GeradorPlanilha {
	

	/**
	 * Gera uma planilha Excel (formato .xlsx) a partir de uma lista de produtos
	 * 
	 * @param caminhoArquivo onde a planilha será salva
	 * @param produtos       a lista de produtos
	 * 
	 * @return uma mensagem informando ao usuário o que ocorreu.
	 */
	public String gerarPlanilhaProdutos(List<PacienteVO> paciente, String caminhoArquivo) {
		// Criar a planilha (Workbook)
		XSSFWorkbook planilha = new XSSFWorkbook();

		// Criar uma aba (Sheet)
		XSSFSheet aba = planilha.createSheet("Pacientes");

		int linhaAtual = 0;

		// Criar o cabeçalho (header)
		String[] nomesColunas = { "#", "Nome", "Data de Nascimento", "CPF", "Telefone", "Sexo", "Tipo Sanguíneo",
				"Estado", "Cidade", "Bairro", "Rua", "Numero", "CEP" };
		criarCabecalho(nomesColunas, aba, linhaAtual);

		// Preencher as linhas com os produtos
		criarLinhasProdutos(paciente, aba, linhaAtual);

		// Salvar o arquivo gerado no disco
		return salvarNoDisco(planilha, caminhoArquivo, ".xlsx");
	}

	private void criarLinhasProdutos(List<PacienteVO> pacientes, XSSFSheet aba, int posicaoLinhaAtual) {
		for (PacienteVO p : pacientes) {
			XSSFRow linhaAtual = aba.createRow(posicaoLinhaAtual);
			
			linhaAtual.createCell(0).setCellValue(p.getIdPaciente());
			linhaAtual.createCell(1).setCellValue(p.getNome());
			linhaAtual.createCell(1).setCellValue(Date.valueOf(p.getDtNascimento()));
			linhaAtual.createCell(1).setCellValue(p.getCpf());
			linhaAtual.createCell(1).setCellValue(p.getTelefone());
			linhaAtual.createCell(1).setCellValue(p.getSexo());
			linhaAtual.createCell(1).setCellValue(p.getTipoSanguineo());
			linhaAtual.createCell(1).setCellValue(p.getEstado());
			linhaAtual.createCell(1).setCellValue(p.getCidade());
			linhaAtual.createCell(1).setCellValue(p.getBairro());
			linhaAtual.createCell(1).setCellValue(p.getRua());
			linhaAtual.createCell(1).setCellValue(p.getNumero());
			linhaAtual.createCell(1).setCellValue(p.getCep());
			
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
		
		return null;
	}


}

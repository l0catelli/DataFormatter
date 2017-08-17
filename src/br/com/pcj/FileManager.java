package br.com.pcj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

/**
 * Manipula um arquivo desejado. 
 * <p>
 * O arquico ser� criado
 * no diret�rio <b>"C:/temp/"</b>. Se j� existir um arquivo no diret�rio
 * com o mesmo nome, o arquivo � removido e um novo arquivo � criado.
 * <ul>
 * <li> Cria um arquivo utilizando o nome informado.
 * <li> Escreve as linhas informadas no arquivo.
 * </ul>
 * 
 * @author      Jo�o Augusto Locatelli
 * @version     1.0
 * @since       1.0
 */
public class FileManager {
	
	private static final String url = "C:/temp/";
	
	private static String fileName;
	
	
	public static String getFileName() {
		return fileName;
	}

	public static void setFileName(String fileName) {
		FileManager.fileName = fileName;
	}
	
	/**
	 * Concatena o diret�rio que ser� utilizado com o nome do arquivo informado.
	 *
	 * @return	Retorna o caminho completo do arquivo.
	 */
	private String getFileUrl() {
		
		return url+fileName;
		
	}
	
	/**
	 * Cria um novo arquivo seguindo a seguinte logica:
	 * <ul>
	 * <li> Verifica se o diret�rio que ser� utilizado existe.
	 * <li> Cria o diret�rio caso ele n�o exista.
	 * <li> Verifica se existe um arquivo com o mesmo nome.
	 * <li> Se o arquivo existir ele � deletado.
	 * <li> Cria um novo arquivo.
	 * </ul>
	 *
	 * @see		File
	 */
	private void createFile() throws IOException {
		
		File folder = new File(url);
		
		if (!folder.exists()) {
			
			folder.mkdirs();
			
		}
		
		File file = new File(getFileUrl()); 
		
        if (file.exists()) {
        	
        	file.delete();
        	
        	file.createNewFile();
        	
        } else {
        	
        	file.createNewFile();
        	
        } 
        
	}
	
	/**
	 * Escreve os dados em um arquivo existente.
	 *
	 * @param	lines				- Lista de Strings contendo as linhas que dever�o ser inseridas no arquivo.
	 * @see		FileOutputStream
	 * @see		PrintStream
	 */
	private void write(List<String> lines) throws FileNotFoundException {
		
		FileOutputStream fos = new FileOutputStream(getFileUrl());
		
        PrintStream ps = new PrintStream(fos);
        
        for (int i = 0; i < lines.size(); i++) {
        	
        	ps.println(lines.get(i));
        	
        }
        
        ps.close();
        
	}
	
	/**
	 * Cria um novo arquivo e escreve os dados informados.
	 *
	 * @param	lines	- Lista de Strings contendo as linhas que dever�o ser inseridas no arquivo.
	 */
	public void writeLines(List<String> lines) throws IOException {
		
		createFile();
		
		write(lines);
		
	}

}

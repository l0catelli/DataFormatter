package br.com.pcj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

/**
 * Realiza a formatação dos dados recebidos utilizando as regras
 * configuradas e salva em um arquivo no formato de texto.
 * 
 * @author      João Augusto Locatelli
 * @version     1.0
 * @since       1.0
 */
public class FileFormatter {
	
	private FileManager fileManager = new FileManager();
	
	/**
	 * Formata um campo adicionando zeros a esquerda ou espaços a direita
	 * até totalizar o seu tamanho máximo. O resultado será uma String
	 * de tamanho igual ao tamanho máximo informado.
	 * <p>
	 * É realizada uma validação simples para ver se existe valor no campo
	 * quando ele é obrigatório e se o valor não ultrapassa o tamanho máximo
	 * informado.
	 *
	 * @param  field	- Informações que serão utilizadas para formatar o campo.
	 * @return      	String do campo formatado.
	 * @see         	Field
	 */
	private String fieldFormatter(Field field) throws DataFormatException {
		
		String		name 		= field.getName();
		FieldType 	fieldType 	= field.getFieldType();
		int 		length 		= field.getLength();
		boolean 	required 	= field.isRequired();
		String 		value 		= field.getValue();
		
		fieldValidator(name, length, required, value);
		
		String formattedField = "";
		
		switch (fieldType) {
			case NUMERIC:
				if (value == null) {
					formattedField = String.format("%0" + length + "d", 0);
				} else {
					formattedField = String.format("%0" + length + "d", Integer.parseInt(value));
				}
				break;
			case TEXT:
				if (value == null) {
					formattedField = String.format("%-" + length + "s", "");
				} else {
					formattedField = String.format("%-" + length + "s", value);
				}
				break;
		}
		
		return formattedField;
		
	}
	
	/**
	 * Realizada uma validação simples para ver se existe valor no campo
	 * quando ele é obrigatório e se o valor não ultrapassa o tamanho máximo
	 * informado.
	 *
	 * @param	name		- Nome de campo. Será utilizado para informar em qual campo o erro aconteceu.
	 * @param	lenght		- Tamanho máximo permitido no campo.
	 * @param	required	- Indica se é ou não um campo obrigatório.
	 * @param	value		- Valor do campo.
	 * @throws 	DataFormatException Quando alguma restrição não é atendiada.
	 */
	private void fieldValidator(String name, int lenght, boolean required, String value) throws DataFormatException {
		
		if (required && (value == null || value.isEmpty())) {
			throw new DataFormatException("O campo " + name + " é obrigatório mas não possui valor.");
		}
		
		if (value != null && value.length() > lenght) {
			throw new DataFormatException("O campo " + name + "com valor igual a \""+ value +"\" irá ultrapassar o limite possivel. Limite igual a 50 caracteres.");
		}
		
	}
	
	/**
	 * Formata uma linha de dados seguindo as regras informadas.
	 *
	 * @param  fields	- Lista de regras utilizada para formatar a linha.
	 * @return      	String da linha formatada.
	 * @see         	Field
	 */
	private String lineFormatter(List<Field> fields) throws DataFormatException {
		
		String line = "";
		
		for (int i=0;i<fields.size();i++) {
			
			line += fieldFormatter(fields.get(i));
			
		}
		
		return line;
		
	}
	
	/**
	 * Realiza o vinculo dos dados buscados no arquivo Access com as regras
	 * definidas. Para que seja possível atribuir uma regra a um campo é necessário
	 * utilizar a mesma chave (nome da regra e nome da coluna/alias) tanto na regra
	 * como no do dado buscado.
	 * <p>
	 * Também será executada uma formatação especial para os campos desejados.
	 *
	 * @param	rows	- Uma lista contendo os dados que serão formatados.
	 * @param	fields	- Lista de regras que serão utilizadas para formatar os dados.
	 * @param	format	- Classe que contem as regras para formatação de campos.
	 * @return      	Retorna uma lista contendo as linhas formadas.
	 * @see         	List
	 * @see         	Format
	 */
	private List<String> format(List<Map<String,String>> rows, List<Field> fields, Format format) throws DataFormatException {
		
		List<String> lines = new ArrayList<String>();
		
		for (int i=0;i<rows.size();i++) {
			
			Map<String, String> values = rows.get(i);
		
			for (int j=0; j<fields.size();j++) {
				
				String value = format.formatFields(fields.get(j).getName(), values.get(fields.get(j).getName()));
				
				fields.get(j).setValue(value);
				
			}
			
			lines.add(lineFormatter(fields));
		
		}
		
		return lines;
		
	}
	
	/**
	 * Ao informar os dados que serão exibidos no header, body e footer do arquivo
	 * e as regras que deverão ser aplicadas, executa a formatação e salva em um arquivo
	 * de texto.
	 * <p>
	 * Cada elemento das listas informadas representará uma linha no arquivo final.
	 *
	 * @param  headerRows	- Lista de dados que serão exibidos na sessão header do arquivo.
	 * @param  bodyRows		- Lista de dados que serão exibidos na sessão body do arquivo.
	 * @param  footerRows	- Lista de dados que serão exibidos na sessão footer do arquivo.
	 * @param  format		- Regras que serão aplicadas aos dados informados.
	 */
	public void fileFormatter(List<Map<String, String>> headerRows, List<Map<String, String>> bodyRows, List<Map<String, String>> footerRows, Format format) throws DataFormatException, IOException {
		
		List<String> fileLines = new ArrayList<String>();
		
		fileLines.addAll(format(headerRows, format.getHeaderFormat(), format));
		
		fileLines.addAll(format(bodyRows, format.getBodyFormat(), format));
		
		fileLines.addAll(format(footerRows, format.getFooterFormat(), format));
		
		fileManager.writeLines(fileLines);
		
	}

}

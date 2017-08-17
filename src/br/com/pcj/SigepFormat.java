package br.com.pcj;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da interface Format.
 * <p>
 * Layout do arquivo de Importação de Destinatários Nacionais.
 * Caso o cliente tenha um banco de dados próprio que não seja provindo do SIGEP II, é
 * disponibilizado o layout para a importação dentro da pasta do SIGEP WEB. Para visualizar o
 * layout abrir a pasta do SIGEP WEB, clicar na subpasta “sigepcliente”, em seguida na subpasta
 * “docs”. Abrir o arquivo “Layout_Importacao_de_Destinatarios”. 
 * <p>
 * Gerar arquivo em .txt e importar no SIGEP WEB no menu Processos=>Importar
 * Destinatários.
 * 
 * @author      João Augusto Locatelli
 * @version     1.0
 * @since       1.0
 */
public class SigepFormat implements Format{
	
	/**
	 * Registro Header (tipo "1")
	 * <p>
	 * Apenas uma linha contendo valores fixos.
	 *
	 * @return	Retorna uma lista contendo a formatação dos campos para cada linha.
	 * @see		Field
	 */
	public List<Field> getHeaderFormat() {
		
		// Registro Header (tipo "1")
		List<Field> fields = new ArrayList<Field>();
		
		// Número 			9(001) 			Valor Fixo - “1”
		fields.add(new Field("NUMERO", FieldType.NUMERIC, 1, true));
		
		// SIGEP 			X(027) 			Valor Fixo - “SIGEP DESTINATARIO NACIONAL”
		fields.add(new Field("SIGEP", FieldType.TEXT, 27, true));
		
		return fields;
		
	}
	
	/**
	 * Dados do destinatário (tipo "2")
	 * <p>
	 * Formatação que será aplicada em todas as linhas de dados.
	 *
	 * @return	Retorna uma lista contendo a formatação dos campos para cada linha.
	 * @see		Field
	 */
	public List<Field> getBodyFormat() {
		
		// Dados do destinatário (tipo "2")
		List<Field> fields = new ArrayList<Field>();

		// Número 			9(001) 			Valor Fixo - “2”
		fields.add(new Field("NUMERO", FieldType.NUMERIC, 1, true));
		
		// CNPJ/CPF 		9(014) 			Opcional e sem máscara
		fields.add(new Field("CNPJ_CPF", FieldType.NUMERIC, 14, false));
		
		// Nome 			X(050) 			Obrigatório
		fields.add(new Field("NOME", FieldType.TEXT, 50, true));
		
		// EMAIL 			X(050) 			Opcional
		fields.add(new Field("EMAIL", FieldType.TEXT, 50, false));
		
		// Aos cuidados 	X(050) 			Opcional
		fields.add(new Field("AOS_CUIDADOS", FieldType.TEXT, 50, false));
		
		// Contato 			X(050) 			Opcional
		fields.add(new Field("CONTATO", FieldType.TEXT, 50, false));
		
		// Cep 				9(008) 			Obrigatório e sem máscara
		fields.add(new Field("CEP", FieldType.NUMERIC, 8, true));
		
		// Logradouro 		X(050) 			Obrigatório
		fields.add(new Field("LOGRADOURO", FieldType.TEXT, 50, true));
		
		// Numero End			9(006) 			Obrigatório
		fields.add(new Field("NUMERO_END", FieldType.NUMERIC, 6, true));
		
		// Complemento 		X(030) 			Opcional
		fields.add(new Field("COMPLEMENTO", FieldType.TEXT, 30, false));
		
		// Bairro 			X(050) 			Opcional
		fields.add(new Field("BAIRRO", FieldType.TEXT, 50, false));
		
		// Cidade 			X(050) 			Obrigatório
		fields.add(new Field("CIDADE", FieldType.TEXT, 50, true));
		
		// Telefone 		9(018) 			Opcional e sem máscara
		fields.add(new Field("TELEFONE", FieldType.TEXT, 18, false));
		
		// Celular 			9(012) 			Opcional e sem máscara
		fields.add(new Field("CELULAR", FieldType.TEXT, 12, false));
		
		// Fax 				9(012) 			Opcional e sem máscara
		fields.add(new Field("FAX", FieldType.TEXT, 12, false));
		
		return fields;
		
	}
	
	/**
	 * Registro Footing (tipo "9")
	 * <p>
	 * Informa a quantidade de destinatários que foram gerados.
	 *
	 * @return	Retorna uma lista contendo a formatação dos campos para cada linha.
	 * @see		Field
	 */
	public List<Field> getFooterFormat() {
		
		// Registro Footing (tipo "9")
		List<Field> fields = new ArrayList<Field>();
		
		// Número 			9(001) 			Valor Fixo - “9”
		fields.add(new Field("NUMERO", FieldType.NUMERIC, 1, true));
		
		// Quantidade 		9(006) 			Quantidade de Destinatários a Importar
		fields.add(new Field("QUANTIDADE", FieldType.NUMERIC, 6, true));
		
		return fields;
		
	}
	
	/**
	 *	Método que contem regras específicas que serão aplicadas aos
	 *	campos existentes.
	 *
	 * @param  fieldName	- Nome do campo que será formatado.
	 * @param  fieldValue	- Valor a ser formatado.
	 * @return				Retorna o valor informado formatado.
	 */
	public String formatFields(String fieldName, String fieldValue) {
		
		switch (fieldName) {
			case "CEP":
				return  fieldValue.replace("-", "").trim();
			case "NOME":
				return fieldValue.toUpperCase();
			case "LOGRADOURO":
				return fieldValue.toUpperCase();
			case "CIDADE":
				return fieldValue.toUpperCase();
		}
		
		return fieldValue;
		
	}

}

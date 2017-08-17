package br.com.pcj;

import java.util.List;

/**
 * Interface para permitir a implementação de diversas regras
 * de formatação de dados.
 * <p>
 * As regras de formatação para cada campo devem ser inseridas 
 * em um objeto <b>Field</b>. As regras permitidas são:
 * <ul>
 * <li>Tipo do campo (texto ou numérico).
 * <li>Tamanho máximo do campo.
 * <li>Campo obrigatório ou opcional.
 * </ul>
 * Para a formatação especifica de cada campo, deve ser utilizado 
 * o metodo <b>formatFields</b>. Para aplicar uma regra especifica a um
 * determinado campo deve ser feito utilizando o campo <b>name</b> no 
 * objeto <b>Field</b>.
 * 
 * @author      João Augusto Locatelli
 * @version     1.0
 * @since       1.0
 */
public interface Format {
	
	/**
	 * Método que deve conter as regras de formatação dos campos
	 * existentes na(s) linha(s) do cabeçalho.
	 *
	 * @return	Retorna uma lista contendo a formatação dos campos do cabeçalho para cada linha de dados existente.
	 * @see		Field
	 */
	public List<Field> getHeaderFormat();
	
	/**
	 * Método que deve conter as regras de formatação dos campos
	 * existentes na(s) linha(s) do corpo do arquivo.
	 *
	 * @return	Retorna uma lista contendo a formatação dos campos do corpo do arquivo para cada linha de dados existente.
	 * @see		Field
	 */
	public List<Field> getBodyFormat();
	
	/**
	 * Método que deve conter as regras de formatação dos campos
	 * existentes na(s) linha(s) do rodapé.
	 *
	 * @return	Retorna uma lista contendo a formatação dos campos do rodapé para cada linha de dados existente.
	 * @see		Field
	 */
	public List<Field> getFooterFormat();
	
	/**
	 * Formatação de campos especificos. Utilizar o nome do 
	 * campo (atributo <b>name</b> do objeto <b>Field</b>) para 
	 * identificar qual formatação deverá ser aplicada ao valor informado.
	 *
	 * @param	fieldName	- Nome do campo. É o atributo <b>name</b> do objeto <b>Field</b>.
	 * @param	fieldValue	- Valor do campo. É o atributo <b>value</b> do objeto <b>Field</b>.
	 * @return				String com o valor do campo formatado.
	 */
	public String formatFields(String fieldName, String fieldValue);

}

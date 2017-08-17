package br.com.pcj;

import java.util.List;

/**
 * Interface para permitir a implementa��o de diversas regras
 * de formata��o de dados.
 * <p>
 * As regras de formata��o para cada campo devem ser inseridas 
 * em um objeto <b>Field</b>. As regras permitidas s�o:
 * <ul>
 * <li>Tipo do campo (texto ou num�rico).
 * <li>Tamanho m�ximo do campo.
 * <li>Campo obrigat�rio ou opcional.
 * </ul>
 * Para a formata��o especifica de cada campo, deve ser utilizado 
 * o metodo <b>formatFields</b>. Para aplicar uma regra especifica a um
 * determinado campo deve ser feito utilizando o campo <b>name</b> no 
 * objeto <b>Field</b>.
 * 
 * @author      Jo�o Augusto Locatelli
 * @version     1.0
 * @since       1.0
 */
public interface Format {
	
	/**
	 * M�todo que deve conter as regras de formata��o dos campos
	 * existentes na(s) linha(s) do cabe�alho.
	 *
	 * @return	Retorna uma lista contendo a formata��o dos campos do cabe�alho para cada linha de dados existente.
	 * @see		Field
	 */
	public List<Field> getHeaderFormat();
	
	/**
	 * M�todo que deve conter as regras de formata��o dos campos
	 * existentes na(s) linha(s) do corpo do arquivo.
	 *
	 * @return	Retorna uma lista contendo a formata��o dos campos do corpo do arquivo para cada linha de dados existente.
	 * @see		Field
	 */
	public List<Field> getBodyFormat();
	
	/**
	 * M�todo que deve conter as regras de formata��o dos campos
	 * existentes na(s) linha(s) do rodap�.
	 *
	 * @return	Retorna uma lista contendo a formata��o dos campos do rodap� para cada linha de dados existente.
	 * @see		Field
	 */
	public List<Field> getFooterFormat();
	
	/**
	 * Formata��o de campos especificos. Utilizar o nome do 
	 * campo (atributo <b>name</b> do objeto <b>Field</b>) para 
	 * identificar qual formata��o dever� ser aplicada ao valor informado.
	 *
	 * @param	fieldName	- Nome do campo. � o atributo <b>name</b> do objeto <b>Field</b>.
	 * @param	fieldValue	- Valor do campo. � o atributo <b>value</b> do objeto <b>Field</b>.
	 * @return				String com o valor do campo formatado.
	 */
	public String formatFields(String fieldName, String fieldValue);

}

package br.com.pcj;

/**
 * Objeto para armazenar de forma estruturada os valores que
 * ser�o utilizando em conjunto com suas regras de formata��o.
 * <ul>
 * <li> name 		- Nome do campo. Dever� ser igual ao nome da coluna/alias 
 * buscada utilizando um comando SQL.
 * <li> fieldType	- Constante indicando se � um campo de texto ou num�rico.
 * <li> length		- Tamanho m�ximo que o campo pode ocupar.
 * <li> required	- Indica se � um campo opcional ou obrigat�rio.
 * <li> value		- Valor de campo.
 * </ul>
 * <p>
 * Essa classe � utilizada para unir os valores buscados no banco
 * com as regras de formata��es que dever�o ser seguidas. Para que seja
 * poss�vel essa liga��o de regra com valor, o atributo name dever� ser igual
 * ao da coluna/alias da consulta SQL realizada.
 * 
 * @author      Jo�o Augusto Locatelli
 * @version     1.0
 * @since       1.0
 */
public class Field {
	
	public Field(String name, FieldType fieldType, int length, boolean required) {
		this.name = name;
		this.fieldType = fieldType;
		this.length = length;
		this.required = required;
	}
	
	public Field(String name, FieldType fieldType, int length, boolean required, String value) {
		this.name = name;
		this.fieldType = fieldType;
		this.length = length;
		this.required = required;
		this.value = value;
	}
	
	private String name;
	
	private FieldType fieldType;
	
	private int length;
	
	boolean required;
	
	String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FieldType getFieldType() {
		return fieldType;
	}

	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

package br.com.pcj;

/**
 * Objeto para armazenar de forma estruturada os valores que
 * serão utilizando em conjunto com suas regras de formatação.
 * <ul>
 * <li> name 		- Nome do campo. Deverá ser igual ao nome da coluna/alias 
 * buscada utilizando um comando SQL.
 * <li> fieldType	- Constante indicando se é um campo de texto ou numérico.
 * <li> length		- Tamanho máximo que o campo pode ocupar.
 * <li> required	- Indica se é um campo opcional ou obrigatório.
 * <li> value		- Valor de campo.
 * </ul>
 * <p>
 * Essa classe é utilizada para unir os valores buscados no banco
 * com as regras de formatações que deverão ser seguidas. Para que seja
 * possível essa ligação de regra com valor, o atributo name deverá ser igual
 * ao da coluna/alias da consulta SQL realizada.
 * 
 * @author      João Augusto Locatelli
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

package br.com.pcj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que encapsula a conexão com o arquivo Access e
 * executa instruções SQL.
 * 
 * @author      João Augusto Locatelli
 * @version     1.0
 * @since       1.0
 */
public class AccessManager {
	
	private String accdbURL;
	
	public String getAccdbURL() {
		return accdbURL;
	}

	public void setAccdbURL(String accdbURL) {
		this.accdbURL = accdbURL;
	}

	/**
	 * Retorna uma sessão do arquivo Access para permitir a manipução
	 * dos dados existentes.
	 *
	 * @return	Retorna uma sessão do arquivo Access.
	 * @see		Connection
	 */
	private Connection getAccess() throws SQLException {
		return DriverManager.getConnection("jdbc:ucanaccess://" + accdbURL);
	}
	
	/**
	 * Executa um comando informado no arquivo Access retornando
	 * um objeto ResultSet.
	 *
	 * @param  sqlQuery	- String contendo o comando que será executado no Access.
	 * @return      	O resultado da consulta executada.
	 * @see         	ResultSet
	 */
	public ResultSet executeQuery(String sqlQuery) throws SQLException {
		
		Connection conn = null;
		
		Statement s = null;
		
		try {
		
			conn = getAccess();
	
			s = conn.createStatement();
	
			return s.executeQuery(sqlQuery);
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			
		} finally {
			
			if (s != null) {
				s.close();
			}

			if (conn != null) {
				conn.close();
			}
			
		}
		
		return null;
		
	}
	
	/**
	 * Executa um comando informado no arquivo Access.
	 *
	 * @param  sqlUpdate	- String contendo o comando que será executado no Access.
	 */
	public void executeUpdate(String sqlUpdate) throws SQLException {
		
		Connection conn = null;
		
		Statement s = null;
		
		try {
		
			conn = getAccess();

			s = conn.createStatement();
		
			s.execute(sqlUpdate);
		
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			
		} finally {
			
			if (s != null) {
				s.close();
			}

			if (conn != null) {
				conn.close();
			}
			
		}

	}
	
	/**
	 * Executa uma consulta recebida como parametro no arquivo Access retornando
	 * uma lista de valores.
	 * <p>
	 * Cada elemento contido na lista é uma linha retornada da consulta. Os
	 * dados da linha foram estruturados em chaves contendo valores. O nome
	 * da coluna/alias será a chave para seu respectivo valor em cada linha.
	 * <p>
	 * Todos os valores retornados serão convertidos em String.
	 *
	 * @param  sqlQuery	- String contendo a consulta SQL que será executada no arquivo Access.
	 * @return      	O resultado da consulta executada convertido em uma lista de chaves e valores.
	 * @see         	List
	 * @see         	Map
	 */
	public List<Map<String,String>> query(String sqlQuery) throws SQLException {
		
		Connection conn = null;
		
		Statement s = null;
		
		try {
		
			conn = getAccess();
	
			s = conn.createStatement();
	
			ResultSet rs = s.executeQuery(sqlQuery);
			
			ResultSetMetaData rsmd = rs.getMetaData();

			int columnsNumber = rsmd.getColumnCount();
			
			List<Map<String,String>> rows = new ArrayList<Map<String,String>>();
			
			while (rs.next()) {
				
				Map<String,String> row = new HashMap<String,String>();
				
				for (int i=1;i<=columnsNumber;i++) {
					
					String columnName = rsmd.getColumnLabel(i);
					
					String columnValue = rs.getString(i);
					
					row.put(columnName, columnValue);
					
				}
				
				rows.add(row);
				
			}
			
			return rows;
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			
		} finally {
			
			if (s != null) {
				s.close();
			}

			if (conn != null) {
				conn.close();
			}
			
		}
		
		return null;
		
	}

}

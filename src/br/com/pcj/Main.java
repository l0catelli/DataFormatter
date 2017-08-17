package br.com.pcj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * http://ucanaccess.sourceforge.net/site.html#home
 */
public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("------ Inicio ------");
		
		// Sigep WEB
		generateNaionalRecipents(sqlQuery);
		
		System.out.println("------ FIM    ------");
		
	}
	
	private static final String sqlQuery = 
			  "	SELECT										"
			+ "		2 AS NUMERO,							"
			+ "		Cadastro.[Nome-I] AS NOME,				"
			+ "		Cadastro.Cep AS CEP, 					"
			+ "		Cadastro.Endereco AS LOGRADOURO,		"
			+ "		Cadastro.Numero AS NUMERO_END,			"
			+ "		Cadastro.Complemento AS COMPLEMENTO,	"
			+ "		Cadastro.Bairro AS BAIRRO,				"
			+ "		Cadastro.Cidade AS CIDADE				"
			+ "	FROM Cadastro								"
			+ "	WHERE										"
			+ "		Cadastro.[Nome-I] IS NOT NULL			"
			+ "		AND Cadastro.Cep IS NOT NULL 			"
			+ "		AND Cadastro.Endereco IS NOT NULL		"
			+ "		AND Cadastro.Numero IS NOT NULL			"
			+ "		AND Cadastro.Cidade IS NOT NULL			"
			+ "	GROUP BY									"
			+ "		Cadastro.[Nome-I],						"
			+ "		Cadastro.Cep,							"
			+ "		Cadastro.Endereco,						"
			+ "		Cadastro.Numero,						"
			+ "		Cadastro.Complemento,					"
			+ "		Cadastro.Bairro,						"
			+ "		Cadastro.Cidade							"
			+ "	ORDER BY Cadastro.[Nome-I];					";
	
	private static void generateNaionalRecipents(String query) {
		
		try {
			
			FileManager.setFileName("Destinatarios_Nacionais.txt");
			
			AccessManager am = new AccessManager();
			
			am.setAccdbURL("C:/temp/2.0MenuPCJBMT.accdb");
			
			Format formatter = new SigepFormat();
			
			FileFormatter fileFormatter = new FileFormatter();
				
			/*
			 * HEADER
			 */
			List<Map<String, String>> headerRows = new ArrayList<Map<String, String>>();
			Map<String, String> headerValues = new HashMap<String, String>();
			
			headerValues.put("NUMERO", "1");
			headerValues.put("SIGEP", "SIGEP DESTINATARIO NACIONAL");
			
			headerRows.add(headerValues);
			
			
			/*
			 * BODY
			 */
			List<Map<String, String>> bodyRows = am.query(query);
			
			
			/*
			 * FOOTER
			 */
			int lines = bodyRows.size();
			
			List<Map<String, String>> footerRows = new ArrayList<Map<String, String>>();
			Map<String, String> footerValues = new HashMap<String, String>();
			
			footerValues.put("NUMERO", "9");
			footerValues.put("QUANTIDADE", String.valueOf(lines));
			
			footerRows.add(footerValues);
			
			fileFormatter.fileFormatter(headerRows, bodyRows, footerRows, formatter);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

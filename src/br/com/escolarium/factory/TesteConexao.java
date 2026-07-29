package br.com.escolarium.factory;

import java.sql.Connection;


public class TesteConexao {
	public static void main(String[] args) {
		
		try (Connection conn = ConexaoFactory.obterConexao()){
			
			System.out.println("✅ TUDO OK! Conexão estabelecida com sucesso!");
			
		} catch (Exception e) {
			System.err.println("❌ ERRO! Não foi possível conectar: " + e.getMessage());
		}
	}
	
}
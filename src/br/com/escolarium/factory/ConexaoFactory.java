package br.com.escolarium.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	private static final String URL = "jdbc:mysql://localhost:3306/escolarium?useSSL=false";
	private static final String USUARIO = "root";
	private static final String SENHA = "";

	public static Connection obterConexao() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
           
            e.printStackTrace(); 
            throw new RuntimeException("Erro ao se conectar ao banco de dados: " + e.getMessage(), e);
        }
    }

}

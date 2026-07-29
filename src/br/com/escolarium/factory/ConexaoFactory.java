package br.com.escolarium.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	private static final String URL = "jdbc:mysql://localhost:3306/escolarium";
	private static final String USUARIO = "root";
	private static final String SENHA = "";

	public static Connection obterConexao() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            // O "e" é o objeto que contém a mensagem real do MySQL
            e.printStackTrace(); // ISSO VAI MOSTRAR O ERRO TÉCNICO COMPLETO NO CONSOLE
            throw new RuntimeException("Erro ao se conectar ao banco de dados: " + e.getMessage(), e);
        }
    }

}

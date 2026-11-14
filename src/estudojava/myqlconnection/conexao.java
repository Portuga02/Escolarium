package estudojava.myqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexao {

    private static String url = "jdbc:mysql://localhost:3306/escolarium?useTimezone=true&serverTimezone=UTC";    
    private static String usuario = "root"; 
    private static String senha = ""; // Ajuste se houver senha

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão OK!");
            return conexao;
        } catch (Exception e) {
            // ISSO VAI MOSTRAR O ERRO REAL NO CONSOLE DO ECLIPSE
            System.err.println("DETALHE DO ERRO DE CONEXÃO:");
            e.printStackTrace();
            return null;
        }
    }
}
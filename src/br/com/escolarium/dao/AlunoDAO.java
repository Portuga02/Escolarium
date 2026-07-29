package br.com.escolarium.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.escolarium.factory.ConexaoFactory;
import br.com.escolarium.model.Aluno;

public class AlunoDAO {
	
	public void cadastrar(Aluno aluno) {
		
		String sql = "INSERT INTO alunos (nome, idade,cpf,matricula) VALUES (?,?,?,?)";
		try (
			Connection conn = ConexaoFactory.obterConexao();
			PreparedStatement  stmt = conn.prepareStatement(sql)){
			
				stmt.setString(1, aluno.getNome());
				stmt.setInt(2,aluno.getIdade());
				stmt.setString(3, aluno.getCpf());
				stmt.setString(4, aluno.getMatricula());
				
				stmt.execute();
				System.out.println("DADOS SALVOS NO BANCO DE DADOS COM SUCESSO");
			}
		 catch (SQLException erro) {
			throw new RuntimeException("Erro ao salvar aluno no banco"+erro.getMessage()); 
		}
	}

}

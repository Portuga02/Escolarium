package br.com.escolarium.controller;

import br.com.escolarium.dao.AlunoDAO;
import br.com.escolarium.model.Aluno;


public class AlunoController {

	private AlunoDAO dao;

	public AlunoController() {
		this.dao = new AlunoDAO();
	}

	public void cadastrarAluno(Aluno aluno) {
		if (aluno.getNome() == null || aluno.getNome().trim().isEmpty()) {
			throw new RuntimeException("Erro: O nome do aluno é obrigatório");
		}
		dao.cadastrar(aluno);
		System.out.println("Aluno" + aluno.getNome() + "Enviado ao mysql com sucesso");

	}
}

package br.com.escolarium.controller;

import br.com.escolarium.model.Aluno;
import java.util.ArrayList;
import java.util.List;

public class AlunoController {
	private List<Aluno> listaDeAlunos = new ArrayList<>();

	public void cadastrarAluno(Aluno aluno) {
		listaDeAlunos.add(aluno);
	}

	public List<Aluno> listarTodos() {
		return listaDeAlunos;
	}
}

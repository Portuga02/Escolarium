package br.com.escolarium.model;

import java.time.LocalDate;

public class Aluno {
	private String nome;
	private int idade;
	private String cpf;
	private String matricula;
	private LocalDate dataNascimento;
	private String serie;
	private String turma;
	private Endereco endereco;
	private Responsaveis responsaveis;

	public Aluno(String nome, int idade, String cpf, String matricula, LocalDate dataNascimento, String serie,
			String turma) {
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
		this.matricula = matricula;
		this.dataNascimento = dataNascimento;
		this.serie = serie;
		this.turma = turma;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Responsaveis getResponsaveis(){
		return responsaveis;
	}
	public void setResponsaveis(Responsaveis responsaveis) {
		this.responsaveis = responsaveis;
	}
	
}

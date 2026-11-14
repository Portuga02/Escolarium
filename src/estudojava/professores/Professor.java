package estudojava.professores;

import java.sql.Date;

public class Professor {
	public String nomeProfessor;
	public int idadeProfessor;
	public Date dataAniversarioProfessor;
	public String grauInstrucaoProfessor;
	public String cursoProfessor;
	public String UniversidadeFormacaoProfessor;
	public Date anoConclusaoCursoProfessor;
	public double turnosMatriculados;
	public double quantidaDeDisciplinasMatriculadas;
	public double quantidadeDeTurmasReponsaveis;

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

	public String getNomeProfessor() {
		return nomeProfessor;
	}

}

package estudojava.entidades;

import java.util.Objects;

public class Disciplina {
	String disciplinas;
	String nota;
	private Aluno Aluno = new Aluno();

	public Aluno getAluno() {
		return Aluno;
	}

	public void setAluno(Aluno aluno) {
		Aluno = aluno;
	}

	public String getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(String disciplinas) {
		this.disciplinas = disciplinas;
	}

	public String getNota() {
		return nota;
	}

	@Override
	public String toString() {
		return "Disciplinas [disciplinas=" + disciplinas + ", nota=" + nota + "]";
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	@Override
	public int hashCode() {
		return Objects.hash(disciplinas, nota);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		return Objects.equals(disciplinas, other.disciplinas) && Objects.equals(nota, other.nota);
	}
}

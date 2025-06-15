package edu.curso;

public class Curso {

	private long id;
	private String nome;
	private int codigoCurso;
	private String nomeCoordenador;
	private int qtdAlunos;
	
	public Curso() {
		super();
	}
	
	public Curso(long id, String nome, int codigoCurso, String nomeCoordenador, int qtdAlunos) {
		super();
		this.id = id;
		this.nome = nome;
		this.codigoCurso = codigoCurso;
		this.nomeCoordenador = nomeCoordenador;
		this.qtdAlunos = qtdAlunos;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getCodigoCurso() {
		return codigoCurso;
	}
	public void setCodigoCurso(int codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	
	public String getNomeCoordenador() {
		return nomeCoordenador;
	}
	
	public void setNomeCoordenador(String nomeCoordenador) {
		this.nomeCoordenador = nomeCoordenador;
	}
	
	public int getQtdAlunos() {
		return qtdAlunos;
	}
	
	public void setQtdAlunos(int qtdAlunos) {
		this.qtdAlunos = qtdAlunos;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nome=" + nome + ", codigoCurso=" + codigoCurso + ", nomeCoordenador="
				+ nomeCoordenador + ", qtdAlunos=" + qtdAlunos + "]";
	}
	

}

package model;

public class Livro {
	private long id;
	private String titulo;
	private String autor;
	private String editora;
	private String categoria;
	private float preco;
	private int quantidade;
	
	
	
	public Livro(long id, String titulo, String autor, String editora, String categoria, float preco, int quantidade) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.editora = editora;
		this.categoria = categoria;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	
	public Livro() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override
	public String toString() {
		return "Livro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", editora=" + editora + ", categoria="
				+ categoria + ", preco=" + preco + ", quantidade=" + quantidade + "]";
	}
	
	
}

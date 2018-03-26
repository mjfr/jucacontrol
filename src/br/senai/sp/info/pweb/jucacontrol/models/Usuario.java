package br.senai.sp.info.pweb.jucacontrol.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

// Marca a classe como uma entidade manipulada pelo Hibernate
@Entity
public class Usuario {

	// Primary Key
	@Id
	private Long id;
	@Column(length = 30, nullable = false, unique = false)
	private String nome;
	@Column(length = 50, nullable = false, unique = false)
	private String sobrenome;
	@Column(length = 120, nullable = false, unique = true)
	private String email;
	@Column(length = 32, nullable = false, unique = false)
	private String senha;
	@Column(nullable = false, unique = false)
	private TiposUsuario tipo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public TiposUsuario getTipo() {
		return tipo;
	}
	public void setTipo(TiposUsuario tipo) {
		this.tipo = tipo;
	}
}
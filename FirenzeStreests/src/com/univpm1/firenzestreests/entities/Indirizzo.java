package com.univpm1.firenzestreests.entities;

/**
 * Classe rappresentante la relazione Indirizzo del Database sono presenti i
 * metodi get{NomeCampo} e set{NomeCampo} i quali rispettivamente assegnano il
 * valore e restituisconao il valore
 */

public class Indirizzo {

	private int id_indirizzo;
	private String nome;
	private String longitudine;
	private String latitudine;

	public Indirizzo() {

	}

	public Indirizzo( String nome,String longitudine,String latitudine) {
		this.nome=nome;
		this.latitudine=latitudine;
		this.longitudine=longitudine;
	}

	public void setId(int id_indirizzo) {
		this.id_indirizzo = id_indirizzo;
	}

	public int getId() {
		return id_indirizzo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setLongitudine(String longitudine) {
		this.longitudine = longitudine;
	}

	public String getLongitudine() {
		return longitudine;
	}

	public void setLatitudine(String latitudine) {
		this.latitudine = latitudine;
	}

	public String getLatitudine() {
		return latitudine;
	}
}

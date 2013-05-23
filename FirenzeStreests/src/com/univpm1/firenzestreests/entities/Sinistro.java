package com.univpm1.firenzestreests.entities;


/** Classe rappresentante la relazione Sinistro del Database
 * sono presenti i metodi get{NomeCampo} e set{NomeCampo} i quali rispettivamente
 * assegnano il valore e restituisconao il valore
 */


public class Sinistro {
	private int id_sinistro;
	private int id_via;
	private int anno;
	private int numero;
	
	public Sinistro(){
		
	}
	
	public void setIdSinistro(int id_sinistro){
		this.id_sinistro=id_sinistro;
	}
	public int getIdSinistro(){
		return id_sinistro;
	}
	
	public void setIdVia(int id_via){
		this.id_via=id_via;
	}
	public int getIdVia(){
		return id_via;
	}
	
	public void setAnno(int anno){
		this.anno=anno;
	}
	public int getAnno(){
		return anno;
	}
	public void setNumero(int numero){
		this.numero=numero;
	}
	public int getNumero(){
		return numero;
	}

}

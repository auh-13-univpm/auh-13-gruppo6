package com.univpm1.firenzestreests.entities;


/** Classe rappresentante la relazione Danno del Database
 * sono presenti i metodi get{NomeCampo} e set{NomeCampo} i quali rispettivamente
 * assegnano il valore e restituisconao il valore
 */


public class Danno {
	
	private int id_danno;
	private int id_via;
	private int lesioni;
	private int contusi;
	private int morti;
	
	public Danno(){
		
	}
	
	public void setIdDanno(int id_danno){
		this.id_danno=id_danno;
	}
	
	public int getIdDanno(){
		return id_danno;
	}
}

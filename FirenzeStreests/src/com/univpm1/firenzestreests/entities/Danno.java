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
	
	public void setIdVia(int id_via){
		this.id_via=id_via;
	}
	public int getIdVia(){
		return id_via;
	}
	
	public void setLesioni(int lesioni){
		this.lesioni=lesioni;
	}
	public int getLesioni(){
		return lesioni;
	}
	
	public void setContusi(int contusi){
		this.contusi=contusi;
	}
	public int getContusi(){
		return contusi;
	}
	
	public void setMorti(int morti){
		this.morti=morti;
	}
	public int getMorti(){
		return morti;
	}
}

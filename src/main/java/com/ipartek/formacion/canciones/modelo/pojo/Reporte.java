package com.ipartek.formacion.canciones.modelo.pojo;

public class Reporte {
	
	private int total;
	private int anyo;
	private int mes;
	private int semana;
	private int dia;
	
	public Reporte() {
		super();
		this.total = -1;
		this.anyo = -1;
		this.mes = -1;
		this.semana = -1;
		this.dia = -1;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getAnyo() {
		return anyo;
	}
	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getSemana() {
		return semana;
	}
	public void setSemana(int semana) {
		this.semana = semana;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	@Override
	public String toString() {
		return "Reporte [total=" + total + ", anyo=" + anyo + ", mes=" + mes + ", semana=" + semana + ", dia=" + dia
				+ "]";
	}
	
	
	

}

package com.macroproyectos.cargue.vo;

import com.opencsv.bean.CsvBindByName;

public class GrupoVo {

	@CsvBindByName
	private String nombre;

	@CsvBindByName
	private String usuario;

	@CsvBindByName
	private String maxdep;

	@CsvBindByName
	private String codigo;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getMaxdep() {
		return maxdep;
	}

	public void setMaxdep(String maxdep) {
		this.maxdep = maxdep;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}

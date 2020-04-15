package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "personas")
@EntityListeners(AuditingEntityListener.class)
public class Persona implements Serializable  {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long code;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	
	public Persona() {
			
		}
	
	
	public Persona(long id, String nombre) {
		
		this.nombre = nombre;
		this.code = id;
		
	}
	
	/**
	 * 
	 * Metodos Get y SET
	 */
	
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	@Override
    public String toString() {
        return "Persona{" +
                "id=" + code +
                ", nombre='" + nombre + '\'' +
                '}';
    }

}

package com.edurush.pe.model;

import jakarta.persistence.*;

	
@Entity
@Table(name = "carrera")

public class Carrera {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrera")
    private Integer idCarrera;

    private String nombre;

    @Column(name = "duracion_ciclos")
    private Integer duracionCiclos;

    private String turno;

    private String modalidad;

    private String estado;

    public Carrera() {
    }

    public Integer getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Integer idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDuracionCiclos() {
        return duracionCiclos;
    }

    public void setDuracionCiclos(Integer duracionCiclos) {
        this.duracionCiclos = duracionCiclos;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}




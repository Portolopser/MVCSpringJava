package com.example.demo.application.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Camionero {
    private String dni;
    private String nombre;
    private String apellidos;
    private Integer telefono;
    private String direccion;
    private Integer salario;
    private String poblacion;
    private Date fechaNacimiento;
    private Destinatario destinatarioByDniDestinatario;

    @Id
    @Column(name = "dni", nullable = false, length = 9)
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Basic
    @Column(name = "nombre", nullable = false, length = 20)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "apellidos", nullable = false, length = 50)
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Basic
    @Column(name = "telefono", nullable = false)
    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    @Basic
    @Column(name = "direccion", nullable = false, length = 100)
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Basic
    @Column(name = "salario", nullable = false)
    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }

    @Basic
    @Column(name = "poblacion", nullable = false, length = 50)
    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    @Basic
    @Column(name = "fecha_nacimiento", nullable = false)
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @ManyToOne
    @JoinColumn(name = "dni_destinatario", referencedColumnName = "dni", nullable = false)
    public Destinatario getDestinatarioByDniDestinatario() {
        return destinatarioByDniDestinatario;
    }

    public void setDestinatarioByDniDestinatario(Destinatario destinatarioByDniDestinatario) {
        this.destinatarioByDniDestinatario = destinatarioByDniDestinatario;
    }
}

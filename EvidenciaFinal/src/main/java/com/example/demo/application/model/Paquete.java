package com.example.demo.application.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Paquete {
    private Integer codigo;
    private String descripcion;
    private Integer peso;
    private Integer codigoProvinciaFk;
    private Provincia provinciaByCodigoProvinciaFk;

    @Id
    @Column(name = "codigo", nullable = false)
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Basic
    @Column(name = "descripcion", nullable = true, length = 100)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "peso", nullable = false)
    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    @Basic
    @Column(name = "codigo_provincia_fk", nullable = false)
    public Integer getCodigoProvinciaFk() {
        return codigoProvinciaFk;
    }

    public void setCodigoProvinciaFk(Integer codigoProvinciaFk) {
        this.codigoProvinciaFk = codigoProvinciaFk;
    }

    @ManyToOne
    @JoinColumn(name = "codigo_provincia_fk", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
    public Provincia getProvinciaByCodigoProvinciaFk() {
        return provinciaByCodigoProvinciaFk;
    }

    public void setProvinciaByCodigoProvinciaFk(Provincia provinciaByCodigoProvinciaFk) {
        this.provinciaByCodigoProvinciaFk = provinciaByCodigoProvinciaFk;
    }
}

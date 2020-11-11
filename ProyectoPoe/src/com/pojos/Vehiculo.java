package com.pojos;
// Generated Nov 10, 2020 5:30:03 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Vehiculo generated by hbm2java
 */
@Entity
@Table(name="vehiculo"
    ,catalog="importadorapoe"
)
public class Vehiculo  implements java.io.Serializable {


     private int idVehiculo;
     private Categoria categoria;
     private UnidadMedida unidadMedida;
     private String foto;
     private String nombre;
     private String color;
     private String marca;
     private String modelo;
     private Integer numPuertas;
     private double precio;
     private Set<DetalleOferta> detalleOfertas = new HashSet<DetalleOferta>(0);

    public Vehiculo() {
    }

	
    public Vehiculo(int idVehiculo, Categoria categoria, UnidadMedida unidadMedida, String foto, String nombre, double precio) {
        this.idVehiculo = idVehiculo;
        this.categoria = categoria;
        this.unidadMedida = unidadMedida;
        this.foto = foto;
        this.nombre = nombre;
        this.precio = precio;
    }
    public Vehiculo(int idVehiculo, Categoria categoria, UnidadMedida unidadMedida, String foto, String nombre, String color, String marca, String modelo, Integer numPuertas, double precio, Set<DetalleOferta> detalleOfertas) {
       this.idVehiculo = idVehiculo;
       this.categoria = categoria;
       this.unidadMedida = unidadMedida;
       this.foto = foto;
       this.nombre = nombre;
       this.color = color;
       this.marca = marca;
       this.modelo = modelo;
       this.numPuertas = numPuertas;
       this.precio = precio;
       this.detalleOfertas = detalleOfertas;
    }
   
     @Id 

    
    @Column(name="id_vehiculo", unique=true, nullable=false)
    public int getIdVehiculo() {
        return this.idVehiculo;
    }
    
    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_categoria", nullable=false)
    public Categoria getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_unidad_medida", nullable=false)
    public UnidadMedida getUnidadMedida() {
        return this.unidadMedida;
    }
    
    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    
    @Column(name="foto", nullable=false, length=150)
    public String getFoto() {
        return this.foto;
    }
    
    public void setFoto(String foto) {
        this.foto = foto;
    }

    
    @Column(name="nombre", nullable=false, length=200)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="color", length=25)
    public String getColor() {
        return this.color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }

    
    @Column(name="marca", length=30)
    public String getMarca() {
        return this.marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }

    
    @Column(name="modelo", length=30)
    public String getModelo() {
        return this.modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    
    @Column(name="num_puertas")
    public Integer getNumPuertas() {
        return this.numPuertas;
    }
    
    public void setNumPuertas(Integer numPuertas) {
        this.numPuertas = numPuertas;
    }

    
    @Column(name="precio", nullable=false, precision=22, scale=0)
    public double getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="vehiculo")
    public Set<DetalleOferta> getDetalleOfertas() {
        return this.detalleOfertas;
    }
    
    public void setDetalleOfertas(Set<DetalleOferta> detalleOfertas) {
        this.detalleOfertas = detalleOfertas;
    }




}



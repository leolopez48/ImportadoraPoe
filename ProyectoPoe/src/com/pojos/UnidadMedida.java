package com.pojos;
// Generated Nov 10, 2020 5:30:03 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * UnidadMedida generated by hbm2java
 */
@Entity
@Table(name="unidad_medida"
    ,catalog="importadorapoe"
)
public class UnidadMedida  implements java.io.Serializable {


     private int idUnidadMedida;
     private String nombre;
     private Set<Vehiculo> vehiculos = new HashSet<Vehiculo>(0);

    public UnidadMedida() {
    }
    
    public UnidadMedida(int idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

	
    public UnidadMedida(int idUnidadMedida, String nombre) {
        this.idUnidadMedida = idUnidadMedida;
        this.nombre = nombre;
    }
    public UnidadMedida(int idUnidadMedida, String nombre, Set<Vehiculo> vehiculos) {
       this.idUnidadMedida = idUnidadMedida;
       this.nombre = nombre;
       this.vehiculos = vehiculos;
    }
   
     @Id 

    
    @Column(name="id_unidad_medida", unique=true, nullable=false)
    public int getIdUnidadMedida() {
        return this.idUnidadMedida;
    }
    
    public void setIdUnidadMedida(int idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    
    @Column(name="nombre", nullable=false, length=100)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="unidadMedida")
    public Set<Vehiculo> getVehiculos() {
        return this.vehiculos;
    }
    
    public void setVehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }




}



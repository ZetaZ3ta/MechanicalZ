package Aplicacion.Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author Victor
 */
@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona {

    @Id
    @Column
    private String DNI;
    @Column
    private String nombre;
    @Column
    private String apellidos;

    public Persona(String DNI, String nombre, String apellidos) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Persona() {
        
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        return "Persona{" + "DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos + '}';
    }

}

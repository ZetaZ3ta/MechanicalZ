package Aplicacion.Modelo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Victor
 */
@Entity
@Table
public class Secretario {

    @Id
    @Column
    private String DNI;

    @Column
    private String nombre;

    @Column
    private String apellidos;
    
    @Column
    private int Telefono;

    @Column
    private Date Fecha_Nacimiento;

    public Secretario(String DNI, String nombre, String apellidos, int Telefono, Date Fecha_Nacimiento) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.Telefono = Telefono;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
    }

    public Secretario() {
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

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    public Date getFecha_Nacimiento() {
        return Fecha_Nacimiento;
    }

    public void setFecha_Nacimiento(Date Fecha_Nacimiento) {
        this.Fecha_Nacimiento = Fecha_Nacimiento;
    }

    @Override
    public String toString() {
        return DNI;
    }

}

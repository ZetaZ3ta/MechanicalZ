package Aplicacion.Modelo;

import java.util.Date;
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
public class Cliente {

    @Id
    @Column
    private String DNI;

    @Column
    private String nombre;

    @Column
    private String apellidos;

    @Column
    private String Direccion;

    @Column
    private int Telefono;

    @Column
    private Date Fecha_Nacimiento;

    public Cliente() {

    }

    public Cliente(String DNI, String nombre, String apellidos, String Direccion, int Telefono, Date Fecha_Nacimiento) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
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

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
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
        return "Cliente{" + "DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos + ", Direccion=" + Direccion + ", Telefono=" + Telefono + ", Fecha_Nacimiento=" + Fecha_Nacimiento + '}';
    }

}

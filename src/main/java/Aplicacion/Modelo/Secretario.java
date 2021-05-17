package Aplicacion.Modelo;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author ZetaZeta
 */
@Entity
public class Secretario {

    @Id
    @Column
    private String DNI;

    @Column
    private String Nombre;

    @Column
    private String Apellidos;

    @Column
    private int Telefono;

    @Column
    private Date Fecha_Nacimiento;

    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "secretarios")
    private List<Proveedor> proveedores;

    public Secretario(String DNI, String Nombre, String Apellidos, int Telefono, Date Fecha_Nacimiento) {
        this.DNI = DNI;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
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
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
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
        return "Secretario{" + "DNI=" + DNI + ", Nombre=" + Nombre + ", Apellidos=" + Apellidos + ", Telefono=" + Telefono + ", Fecha_Nacimiento=" + Fecha_Nacimiento + '}';
    }

}

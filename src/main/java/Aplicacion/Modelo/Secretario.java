package Aplicacion.Modelo;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Victor
 */
@Entity
@Table
public class Secretario extends Persona {

    @Column
    private int Telefono;

    @Column
    private Date Fecha_Nacimiento;

    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "secretarios")
    private List<Proveedor> proveedores;

    public Secretario(String DNI, String nombre, String apellidos, int Telefono, Date Fecha_Nacimiento, List<Proveedor> proveedores) {
        super(DNI, nombre, apellidos);
        this.Telefono = Telefono;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
        this.proveedores = proveedores;
    }

        public Secretario(String DNI, String nombre, String apellidos, int Telefono, Date Fecha_Nacimiento) {
        super(DNI, nombre, apellidos);
        this.Telefono = Telefono;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
    }
    
    public Secretario() {

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

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    @Override
    public String toString() {
        return "Secretario{" + "Telefono=" + Telefono + ", Fecha_Nacimiento=" + Fecha_Nacimiento + ", proveedores=" + proveedores + '}';
    }

}

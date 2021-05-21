package Aplicacion.Modelo;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Victor
 */
@Entity
@Table
public class Cliente extends Persona {

    @Column
    private String Direccion;

    @Column
    private int Telefono;

    @Column
    private Date Fecha_Nacimiento;

    @OneToMany
    @JoinColumn(name = "Dueño")
    private List<Moto> Motos;

    public Cliente(String DNI, String nombre, String apellidos, String Direccion, int Telefono, Date Fecha_Nacimiento, List<Moto> Motos) {
        super(DNI, nombre, apellidos);
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
        this.Motos = Motos;
    }
    
    public Cliente() {
        
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

    public List<Moto> getMotos() {
        return Motos;
    }

    public void setMotos(List<Moto> Motos) {
        this.Motos = Motos;
    }

    @Override
    public String toString() {
        return "Cliente{" + "Direccion=" + Direccion + ", Telefono=" + Telefono + ", Fecha_Nacimiento=" + Fecha_Nacimiento + ", Motos=" + Motos + '}';
    }

}

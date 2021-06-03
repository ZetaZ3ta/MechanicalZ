package Aplicacion.Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Victor
 */
@Entity
@Table
public class Proveedor {

    @Id
    @Column
    private int CIF;

    @Column
    private String Nombre_Empresa;

    @Column
    private String Direccion;

    @ManyToOne
    private Secretario Secretario;

    public Proveedor() {

    }

    public Proveedor(int CIF, String Nombre_Empresa, String Direccion, Secretario Secretario) {
        this.CIF = CIF;
        this.Nombre_Empresa = Nombre_Empresa;
        this.Direccion = Direccion;
        this.Secretario = Secretario;
    }

    public int getCIF() {
        return CIF;
    }

    public void setCIF(int CIF) {
        this.CIF = CIF;
    }

    public String getNombre_Empresa() {
        return Nombre_Empresa;
    }

    public void setNombre_Empresa(String Nombre_Empresa) {
        this.Nombre_Empresa = Nombre_Empresa;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public Secretario getSecretario() {
        return Secretario;
    }

    public void setSecretario(Secretario Secretario) {
        this.Secretario = Secretario;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "CIF=" + CIF + ", Nombre_Empresa=" + Nombre_Empresa + ", Direccion=" + Direccion + ", Secretario=" + Secretario + '}';
    }

}

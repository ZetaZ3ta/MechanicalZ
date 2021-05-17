package Aplicacion.Modelo;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author ZetaZeta
 */
@Entity
public class Proveedor {

    @Id
    @Column
    private int CIF;
    
    @Column
    private String Nombre_Empresa;
    
    @Column
    private String Direccion;
    
    @ManyToMany
    private List<Secretario> secretarios;

    public Proveedor(int CIF, String Nombre_Empresa, String Direccion) {
        this.CIF = CIF;
        this.Nombre_Empresa = Nombre_Empresa;
        this.Direccion = Direccion;
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

    @Override
    public String toString() {
        return "Proveedor{" + "CIF=" + CIF + ", Nombre_Empresa=" + Nombre_Empresa + ", Direccion=" + Direccion + '}';
    }

}

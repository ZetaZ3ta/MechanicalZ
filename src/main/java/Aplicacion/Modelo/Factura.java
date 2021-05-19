package Aplicacion.Modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

/**
 *
 * @author ZetaZeta
 */
@Entity
@Table
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int ID;

    @Column
    private String Descripcion;

    @Column
    private int Precio;

    @Column
    private int IVA;

    @OneToOne(cascade = {CascadeType.ALL})
    private Cliente Cliente;

    @OneToOne(cascade = {CascadeType.ALL})
    private Secretario Secretario;

    public Factura() {
    }

    public Factura(int ID, String Descripcion, int Precio, int IVA, Cliente Cliente, Secretario Secretario) {
        this.ID = ID;
        this.Descripcion = Descripcion;
        this.Precio = Precio;
        this.IVA = IVA;
        this.Cliente = Cliente;
        this.Secretario = Secretario;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int Precio) {
        this.Precio = Precio;
    }

    public int getIVA() {
        return IVA;
    }

    public void setIVA(int IVA) {
        this.IVA = IVA;
    }

    public Cliente getCliente() {
        return Cliente;
    }

    public void setCliente(Cliente Cliente) {
        this.Cliente = Cliente;
    }

    public Secretario getSecretario() {
        return Secretario;
    }

    public void setSecretario(Secretario Secretario) {
        this.Secretario = Secretario;
    }

    @Override
    public String toString() {
        return "Factura{" + "ID=" + ID + ", Descripcion=" + Descripcion + ", Precio=" + Precio + ", IVA=" + IVA + ", Cliente=" + Cliente + ", Secretario=" + Secretario + '}';
    }

}

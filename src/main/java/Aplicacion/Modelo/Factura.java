package Aplicacion.Modelo;

import com.sun.istack.internal.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Victor
 */
@Entity
@Table
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int ID;

    @NotNull
    @Column
    private String Descripcion;

    @NotNull
    @Column
    private int Precio;

    @NotNull
    @Column
    private int IVA;

    @NotNull
    @Column
    private double Total;

    @NotNull
    @ManyToOne
    private Cliente cliente;

    @NotNull
    @ManyToOne
    private Secretario secretario;

    public Factura() {
    }

    public Factura(int ID, String Descripcion, int Precio, int IVA, double Total, Cliente cliente, Secretario secretario) {
        this.ID = ID;
        this.Descripcion = Descripcion;
        this.Precio = Precio;
        this.IVA = IVA;
        this.Total = Total;
        this.cliente = cliente;
        this.secretario = secretario;
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

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Secretario getSecretario() {
        return secretario;
    }

    public void setSecretario(Secretario secretario) {
        this.secretario = secretario;
    }

    @Override
    public String toString() {
        return "Factura{" + "ID=" + ID + ", Descripcion=" + Descripcion + ", Precio=" + Precio + ", IVA=" + IVA + ", Total=" + Total + ", cliente=" + cliente + ", secretario=" + secretario + '}';
    }

}

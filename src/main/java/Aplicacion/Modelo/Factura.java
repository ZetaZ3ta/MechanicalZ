package Aplicacion.Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @Column
    private String Descripcion;

    @Column
    private int Precio;

    @Column
    private int IVA;

    public Factura() {
    }

    public Factura(int ID, String Descripcion, int Precio, int IVA) {
        this.ID = ID;
        this.Descripcion = Descripcion;
        this.Precio = Precio;
        this.IVA = IVA;
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

    @Override
    public String toString() {
        return "Factura{" + "ID=" + ID + ", Descripcion=" + Descripcion + ", Precio=" + Precio + ", IVA=" + IVA + '}';
    }

}

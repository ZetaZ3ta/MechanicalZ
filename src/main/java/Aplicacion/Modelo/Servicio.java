package Aplicacion.Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author ZetaZeta
 */
@Entity
@Table
public class Servicio {

    @Id
    @Column
    private String Nombre;

    @OneToOne
    private Moto moto;

    @OneToOne
    private Mecanico mecanico;

    public Servicio(String Nombre, Moto moto, Mecanico mecanico) {
        this.Nombre = Nombre;
        this.moto = moto;
        this.mecanico = mecanico;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Moto getMoto() {
        return moto;
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    @Override
    public String toString() {
        return "Servicio{" + "Nombre=" + Nombre + ", moto=" + moto + ", mecanico=" + mecanico + '}';
    }

}

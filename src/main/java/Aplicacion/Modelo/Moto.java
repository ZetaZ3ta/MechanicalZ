package Aplicacion.Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Victor
 */
@Entity
@Table
public class Moto {

    @Id
    @Column
    private String Bastidor;

    @Column
    private String Marca;

    @Column
    private String Modelo;

    @Column
    private String Matricula;

    @Column
    private int KM;

    public Moto(String Bastidor, String Marca, String Modelo, String Matricula, int KM) {
        this.Bastidor = Bastidor;
        this.Marca = Marca;
        this.Modelo = Modelo;
        this.Matricula = Matricula;
        this.KM = KM;
    }
    
    public Moto() {
        
    }

    public String getBastidor() {
        return Bastidor;
    }

    public void setBastidor(String Bastidor) {
        this.Bastidor = Bastidor;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public String getMatricula() {
        return Matricula;
    }

    public void setMatricula(String Matricula) {
        this.Matricula = Matricula;
    }

    public int getKM() {
        return KM;
    }

    public void setKM(int KM) {
        this.KM = KM;
    }

    @Override
    public String toString() {
        return "Moto{" + "Bastidor=" + Bastidor + ", Marca=" + Marca + ", Modelo=" + Modelo + ", Matricula=" + Matricula + ", KM=" + KM + '}';
    }

}

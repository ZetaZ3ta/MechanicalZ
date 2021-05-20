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
public class Usuario {

    @Id
    @Column
    private String Usuario;

    @Column
    private String Contraseña;

    @Column
    private String Tipo;

    public Usuario(String Usuario, String Contraseña, String Tipo) {
        this.Usuario = Usuario;
        this.Contraseña = Contraseña;
        this.Tipo = Tipo;
    }

    public Usuario() {

    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

}

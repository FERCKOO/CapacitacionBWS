/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import static data.HexDigest.hexDigest;
import static java.awt.SystemColor.window;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import modelos.UsuarioModel;
import objetos.Usuario;
import sesiones.Sesion;

/**
 *
 * @author Blueweb
 */
@ManagedBean
@ViewScoped
public class UsuarioBean {

    private String username = "";
    private String password = "";

    private Usuario usuario;

    UsuarioModel model = new UsuarioModel();

    public String validarDatos() {
        usuario = new Usuario();
        usuario.setUsuario(username);
        usuario.setContraseña(hexDigest(password));

        if (model.VerificarConexion(usuario)) {
            return "views/galeria";
        } else {
            return "login";
        }
    }

    public void logout() {

        HttpSession session = Sesion.getSession();
        session.invalidate();
        
    }

    //<editor-fold defaultstate="collapsed" desc="gets y sets">
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    //</editor-fold>
}

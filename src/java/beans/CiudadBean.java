package beans;

import objetos.Ciudad;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import modelos.CiudadModel;
import objetos.Respuesta;
import org.primefaces.PrimeFaces;

@ManagedBean
@ViewScoped
public class CiudadBean {

    private List<Ciudad> ciudades;
    private List<Ciudad> ciudadesFiltradas;
    private Ciudad ciudadSeleccionada;
    private List<Ciudad> ciudadesSeleccionadas;
    
    private String descripcion = "";
    private String codigo = "";
    private int lada;
    private int estadoCiudad;

    private Ciudad ciudad;

    CiudadModel model = new CiudadModel();

    @PostConstruct
    public void init() {
        ciudades = new ArrayList<>();

        ciudades = model.traerCiudades();
    }

    public void agregarCiudad() {
        ciudad = new Ciudad();
        Respuesta respuesta = new Respuesta();

        ciudad.setDescripcion(descripcion);
        ciudad.setCodigo(codigo);
        ciudad.setLada(lada);
        ciudad.setEstadoCiudad(estadoCiudad);

        ciudad.setIdUsuario();

        descripcion = "";
        codigo = "";
        lada = 0;
        estadoCiudad = 0;

        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('añadirDialog').hide();");

        respuesta = model.añadirCiudad(ciudad);

        ciudades = model.traerCiudades();

        addMessage(respuesta.getTipoRespuesta(), respuesta.getHead(), respuesta.getMsg() + respuesta.getIdRespuesta());

    }

    public void eliminarCiudad(int ciudadId) {
        Respuesta respuesta = new Respuesta();

        respuesta = model.eliminarCiudad(ciudadId);

        ciudades = model.traerCiudades();

        addMessage(respuesta.getTipoRespuesta(), respuesta.getHead(), respuesta.getMsg() + respuesta.getIdRespuesta());

    }

    public void editarCiudad(Ciudad ciudadAux) {
        Respuesta respuesta = new Respuesta();

        respuesta = model.editarCiudad(ciudadAux);

        addMessage(respuesta.getTipoRespuesta(), respuesta.getHead(), respuesta.getMsg() + respuesta.getIdRespuesta());

    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    //<editor-fold defaultstate="collapsed" desc="gets y sets">
    /**
     * @return the ciudades
     */
    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    /**
     * @param ciudades the ciudades to set
     */
    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    /**
     * @return the ciudadesFiltradas
     */
    public List<Ciudad> getCiudadesFiltradas() {
        return ciudadesFiltradas;
    }

    /**
     * @param ciudadesFiltradas the ciudadesFiltradas to set
     */
    public void setCiudadesFiltradas(List<Ciudad> ciudadesFiltradas) {
        this.ciudadesFiltradas = ciudadesFiltradas;
    }

    /**
     * @return the ciudadSeleccionada
     */
    public Ciudad getCiudadSeleccionada() {
        return ciudadSeleccionada;
    }

    /**
     * @param ciudadSeleccionada the ciudadSeleccionada to set
     */
    public void setCiudadSeleccionada(Ciudad ciudadSeleccionada) {
        System.out.println(ciudadSeleccionada.getDescripcion());
        this.ciudadSeleccionada = ciudadSeleccionada;
    }

    /**
     * @return the ciudadesSeleccionadas
     */
    public List<Ciudad> getCiudadesSeleccionadas() {
        return ciudadesSeleccionadas;
    }

    /**
     * @param ciudadesSeleccionadas the ciudadesSeleccionadas to set
     */
    public void setCiudadesSeleccionadas(List<Ciudad> ciudadesSeleccionadas) {
        this.ciudadesSeleccionadas = ciudadesSeleccionadas;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the estadoCiudad
     */
    public int getEstadoCiudad() {
        return estadoCiudad;
    }

    /**
     * @param estadoCiudad the estadoCiudad to set
     */
    public void setEstadoCiudad(int estadoCiudad) {
        this.estadoCiudad = estadoCiudad;
    }

    /**
     * @return the lada
     */
    public int getLada() {
        return lada;
    }

    /**
     * @param lada the lada to set
     */
    public void setLada(int lada) {
        this.lada = lada;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
//</editor-fold>
}

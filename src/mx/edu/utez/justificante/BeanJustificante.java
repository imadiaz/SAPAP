package mx.edu.utez.justificante;

import mx.edu.utez.persona.BeanPersona;
import mx.edu.utez.proyecto.BeanProyecto;

public class BeanJustificante {

    private int idJustificante;
    private BeanPersona estudiante;
    private String fechaElaboracion;
    private byte[] imagen;
    private String motivoJustifica;
    private String motivoRechazo;
    private int estadoJustificante;
    private int estadoRAPE;
    private int estadoCCDS;
    private int estadoARH;
    private BeanProyecto proyecto;
    private BeanPersona justifica;

    public BeanJustificante() {
    }

    public BeanJustificante(int idJustificante, BeanPersona estudiante, String fechaElaboracion, byte[] imagen, String motivoJustifica, String motivoRechazo, int estadoJustificante, int estadoRAPE, int estadoCCDS, int estadoARH, BeanProyecto proyecto, BeanPersona justifica) {
        this.idJustificante = idJustificante;
        this.estudiante = estudiante;
        this.fechaElaboracion = fechaElaboracion;
        this.imagen = imagen;
        this.motivoJustifica = motivoJustifica;
        this.motivoRechazo = motivoRechazo;
        this.estadoJustificante = estadoJustificante;
        this.estadoRAPE = estadoRAPE;
        this.estadoCCDS = estadoCCDS;
        this.estadoARH = estadoARH;
        this.proyecto = proyecto;
        this.justifica = justifica;
    }

    public int getIdJustificante() {
        return idJustificante;
    }

    public void setIdJustificante(int idJustificante) {
        this.idJustificante = idJustificante;
    }

    public BeanPersona getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(BeanPersona estudiante) {
        this.estudiante = estudiante;
    }

    public String getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(String fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getMotivoJustifica() {
        return motivoJustifica;
    }

    public void setMotivoJustifica(String motivoJustifica) {
        this.motivoJustifica = motivoJustifica;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public int getEstadoJustificante() {
        return estadoJustificante;
    }

    public void setEstadoJustificante(int estadoJustificante) {
        this.estadoJustificante = estadoJustificante;
    }

    public int getEstadoRAPE() {
        return estadoRAPE;
    }

    public void setEstadoRAPE(int estadoRAPE) {
        this.estadoRAPE = estadoRAPE;
    }

    public int getEstadoCCDS() {
        return estadoCCDS;
    }

    public void setEstadoCCDS(int estadoCCDS) {
        this.estadoCCDS = estadoCCDS;
    }

    public int getEstadoARH() {
        return estadoARH;
    }

    public void setEstadoARH(int estadoARH) {
        this.estadoARH = estadoARH;
    }

    public BeanProyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(BeanProyecto proyecto) {
        this.proyecto = proyecto;
    }

    public BeanPersona getJustifica() {
        return justifica;
    }

    public void setJustifica(BeanPersona justifica) {
        this.justifica = justifica;
    }
}

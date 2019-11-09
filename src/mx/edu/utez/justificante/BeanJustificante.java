package mx.edu.utez.justificante;

import mx.edu.utez.persona.BeanPersona;
import mx.edu.utez.proyecto.BeanProyecto;

public class BeanJustificante {

    private BeanPersona persona;
    private BeanPersona justifica;
    private BeanProyecto proyecto;
    private int idJustificante;
    private String fechaElaboracion;
    private byte[] evidencia;
    private String motivo;

    public BeanJustificante() {
    }

    public BeanJustificante(BeanPersona persona, BeanPersona justifica, BeanProyecto proyecto, int idJustificante, String fechaElaboracion, byte[] evidencia, String motivo) {
        this.persona = persona;
        this.justifica = justifica;
        this.proyecto = proyecto;
        this.idJustificante = idJustificante;
        this.fechaElaboracion = fechaElaboracion;
        this.evidencia = evidencia;
        this.motivo = motivo;
    }

    public BeanPersona getPersona() {
        return persona;
    }

    public void setPersona(BeanPersona persona) {
        this.persona = persona;
    }

    public BeanPersona getJustifica() {
        return justifica;
    }

    public void setJustifica(BeanPersona justifica) {
        this.justifica = justifica;
    }

    public BeanProyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(BeanProyecto proyecto) {
        this.proyecto = proyecto;
    }

    public int getIdJustificante() {
        return idJustificante;
    }

    public void setIdJustificante(int idJustificante) {
        this.idJustificante = idJustificante;
    }

    public String getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(String fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public byte[] getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(byte[] evidencia) {
        this.evidencia = evidencia;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}

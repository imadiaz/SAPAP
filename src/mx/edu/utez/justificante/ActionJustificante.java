package mx.edu.utez.justificante;

import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionJustificante extends ActionSupport {

    List<BeanJustificante> justificantes;
    BeanJustificante beanJustificante;
    String mensaje;
    Map respuestas = new HashMap();
    DaoJustificante daoJustificante = new DaoJustificante();

    public String consultarJustificantesPendientes(){
        daoJustificante = new DaoJustificante();
        respuestas.put("prueba", daoJustificante.getLista());
        return SUCCESS;
    }

    public List<BeanJustificante> getJustificantes() {
        return justificantes;
    }

    public void setJustificantes(List<BeanJustificante> justificantes) {
        this.justificantes = justificantes;
    }

    public BeanJustificante getBeanJustificante() {
        return beanJustificante;
    }

    public void setBeanJustificante(BeanJustificante beanJustificante) {
        this.beanJustificante = beanJustificante;
    }

    public DaoJustificante getDaoJustificante() {
        return daoJustificante;
    }

    public void setDaoJustificante(DaoJustificante daoJustificante) {
        this.daoJustificante = daoJustificante;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Map getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(Map respuestas) {
        this.respuestas = respuestas;
    }
}

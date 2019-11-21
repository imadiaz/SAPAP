package mx.edu.utez.justificante;

import com.opensymphony.xwork2.ActionSupport;
import mx.edu.utez.persona.BeanPersona;
import mx.edu.utez.proyecto.BeanProyecto;
import org.apache.struts2.json.annotations.JSON;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

public class ActionJustificante extends ActionSupport {

    List<BeanJustificante> justificantes;
    BeanJustificante beanJustificante;
    String mensaje;
    String parametro;
    Map respuestas = new HashMap();
    DaoJustificante daoJustificante = new DaoJustificante();

    public String consultarJustificantesPendientes(){
        daoJustificante = new DaoJustificante();
        respuestas.put("listaJPendientes", daoJustificante.listaJustificantesPendientes(1));
        return SUCCESS;
    }

    public String consultarHistorialJustificantes(){
        daoJustificante = new DaoJustificante();
        respuestas.put("listaJPendientes", daoJustificante.historialJustificantes(1));
        return SUCCESS;
    }

    public String consultarHistorialJustificantesRH(){
        daoJustificante = new DaoJustificante();
        respuestas.put("listaJPendientes", daoJustificante.historialJustificantesRH(3));
        return SUCCESS;
    }

    public String consultarHistorialJustificantesCOD(){
        daoJustificante = new DaoJustificante();
        respuestas.put("listaJPendientes", daoJustificante.historialJustificantesCOD(3));
        return SUCCESS;
    }

    public String consultarHistorialJustificantesRAPE(){
        daoJustificante = new DaoJustificante();
        respuestas.put("listaJPendientes", daoJustificante.historialJustificantesRAPE(3));
        return SUCCESS;
    }

    public String consultarJustificantesPendientesRAPE(){
        daoJustificante = new DaoJustificante();
        respuestas.put("listaJPendientes", daoJustificante.listaJustificantesPendientesRAPE(3));
        return SUCCESS;
    }

    public String consultarJustificantesPendientesCOD(){
        daoJustificante = new DaoJustificante();
        respuestas.put("listaJPendientes", daoJustificante.listaJustificantesPendientesCOD(3));
        return SUCCESS;
    }

    public String consultarJustificantesPendientesRH(){
        daoJustificante = new DaoJustificante();
        respuestas.put("listaJPendientes", daoJustificante.listaJustificantesPendientesRH(3));
        return SUCCESS;
    }

    public String consultarSelectAgregar(){
        daoJustificante = new DaoJustificante();
        int id = Integer.parseInt(parametro);
        respuestas.put("listaRape", daoJustificante.consultarRapeJustificante());
        respuestas.put("listaProyecto", daoJustificante.consultarProyectoJustificante(id));
        return SUCCESS;
    }

    public String consultarJustificantePendienteEspecifico(){
        daoJustificante = new DaoJustificante();
        int id = Integer.parseInt(parametro);
        beanJustificante = daoJustificante.consultarDatosJustificante(id);

        respuestas.put("listaJustificante", beanJustificante);
        respuestas.put("listaRape", daoJustificante.consultarRapeJustificanteModificar(beanJustificante.getJustifica().getIdPersona()));
        respuestas.put("listaProyecto", daoJustificante.consultarProyectoJustificanteModificar(1, beanJustificante.getProyecto().getNombre()));
        return SUCCESS;
    }

    public String agregarJustificante() throws JSONException {
        daoJustificante = new DaoJustificante();
        beanJustificante = new BeanJustificante();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date objDate = new Date();
        String fecha = simpleDateFormat.format(objDate);
        JSONObject object = new JSONObject(parametro);
        BeanPersona beanPersona = new BeanPersona();
        BeanPersona estudiante = new BeanPersona();
        BeanProyecto beanProyecto = new BeanProyecto();
        estudiante.setIdPersona(object.getInt("id"));
        beanPersona.setNombre(object.getString("rape"));
        String[] array = beanPersona.getNombre().split("-");
        String nombre = "";
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                beanPersona.setIdPersona(Integer.parseInt(array[i].replace(" ", "")));
            } else {
                nombre += array[i] + " ";
            }
        }
        beanPersona.setNombre(nombre);
        beanProyecto.setNombre(object.getString("proyecto"));
        beanJustificante.setMotivoJustifica(object.getString("motivo"));
        beanJustificante.setReferencia(object.getString("referencia"));
        beanJustificante.setEvidencia(object.getString("evidencia"));
        beanJustificante.setFechaElaboracion(fecha);
        beanJustificante.setEstudiante(estudiante);
        beanJustificante.setJustifica(beanPersona);
        beanJustificante.setProyecto(beanProyecto);

        if (daoJustificante.registrarJustificante(beanJustificante)){
            respuestas.put("mensaje", "¡Justificante registrado exitosamente!");
        } else {
            respuestas.put("mensaje", "El justificante no se pudo registrar");
        }
        return SUCCESS;
    }

    public String modificarJustificante() throws JSONException{
        daoJustificante = new DaoJustificante();
        beanJustificante = new BeanJustificante();

        JSONObject object = new JSONObject(parametro);
        BeanPersona beanPersona = new BeanPersona();
        BeanPersona estudiante = new BeanPersona();
        BeanProyecto beanProyecto = new BeanProyecto();

        beanJustificante.setIdJustificante(object.getInt("id"));
        beanPersona.setNombre(object.getString("rape"));
        String[] array = beanPersona.getNombre().split("-");
        String nombre = "";
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                beanPersona.setIdPersona(Integer.parseInt(array[i].replace(" ", "")));
            } else {
                nombre += array[i] + " ";
            }
        }

        beanPersona.setNombre(nombre);
        beanProyecto.setNombre(object.getString("proyecto"));
        beanJustificante.setMotivoJustifica(object.getString("motivo"));

        beanJustificante.setEstudiante(estudiante);
        beanJustificante.setJustifica(beanPersona);
        beanJustificante.setProyecto(beanProyecto);

        if (daoJustificante.modificarJustificante(beanJustificante)){
            respuestas.put("mensaje", "¡Justificante modificado exitosamente!");
        } else {
            respuestas.put("mensaje", "El justificante no se pudo modificar");
        }

        return SUCCESS;
    }

    public String actualizarArchivo() throws JSONException {
        daoJustificante = new DaoJustificante();
        beanJustificante = new BeanJustificante();

        JSONObject object = new JSONObject(parametro);

        beanJustificante.setIdJustificante(object.getInt("idJustificante"));
        beanJustificante.setReferencia(object.getString("ref"));
        beanJustificante.setEvidencia(object.getString("evidencia"));

        if(daoJustificante.actualizarArchivoJustificante(beanJustificante)){
            respuestas.put("mensaje", "¡Archivo modificado exitosamente!");
        } else {
            respuestas.put("mensaje", "El archivo no se pudo modificar");
        }
        return SUCCESS;
    }

    public String eliminarJustificante(){
        daoJustificante = new DaoJustificante();
        beanJustificante = new BeanJustificante();
        int id = Integer.parseInt(parametro);
        beanJustificante.setIdJustificante(id);

        if (daoJustificante.eliminarJustificante(beanJustificante)){
            respuestas.put("mensaje", "¡Justificante eliminado exitosamente!");
        } else {
            respuestas.put("mensaje", "El justificante no se pudo eliminar");
        }
        return SUCCESS;
    }

    public String validarContrasena () throws JSONException {
        daoJustificante = new DaoJustificante();
        JSONObject object = new JSONObject(parametro);

        int idJus = object.getInt("idJus");
        int idUs = object.getInt("idUs");
        String contrasena = object.getString("contrasena");
        int aprobar = object.getInt("aprobar");

        String contrasenaBase = daoJustificante.getContrasena(idUs);

        if (contrasena.equals(contrasenaBase)){
            respuestas.put("mensaje", "yes");
        } else {
            respuestas.put("mensaje", "Contraseña incorrecta");
        }
        return SUCCESS;
    }

    public String accionJustificante() throws JSONException {
        daoJustificante = new DaoJustificante();
        JSONObject object = new JSONObject(parametro);
        beanJustificante = new BeanJustificante();

        beanJustificante.setIdJustificante(object.getInt("idJus"));
        beanJustificante.setEstadoRAPE(object.getInt("aprobar"));
        beanJustificante.setMotivoRechazo(object.getString("motivo"));

        if (daoJustificante.aprobarJustificante(beanJustificante)){
            respuestas.put("mensaje", "¡Justificante gestionado exitosamente!");
        } else {
            respuestas.put("mensaje", "El justificante no se pudo gestionar");
        }

        return SUCCESS;
    }

    public String accionJustificanteCOD() throws JSONException {
        daoJustificante = new DaoJustificante();
        JSONObject object = new JSONObject(parametro);
        beanJustificante = new BeanJustificante();

        beanJustificante.setIdJustificante(object.getInt("idJus"));
        beanJustificante.setEstadoRAPE(object.getInt("aprobar"));
        beanJustificante.setMotivoRechazo(object.getString("motivo"));

        if (daoJustificante.aprobarJustificanteCOD(beanJustificante)){
            respuestas.put("mensaje", "¡Justificante gestionado exitosamente!");
        } else {
            respuestas.put("mensaje", "El justificante no se pudo gestionar");
        }

        return SUCCESS;
    }

    public String accionJustificanteRH() throws JSONException {
        daoJustificante = new DaoJustificante();
        JSONObject object = new JSONObject(parametro);
        beanJustificante = new BeanJustificante();

        beanJustificante.setIdJustificante(object.getInt("idJus"));
        beanJustificante.setEstadoRAPE(object.getInt("aprobar"));
        beanJustificante.setMotivoRechazo(object.getString("motivo"));

        if (daoJustificante.aprobarJustificanteRH(beanJustificante)){
            respuestas.put("mensaje", "¡Justificante gestionado exitosamente!");
        } else {
            respuestas.put("mensaje", "El justificante no se pudo gestionar");
        }

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

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
}

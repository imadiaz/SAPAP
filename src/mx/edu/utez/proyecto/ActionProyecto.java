
package mx.edu.utez.proyecto;

import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionProyecto extends ActionSupport {
    List<BeanProyecto> proyectos;
    BeanProyecto beanProyecto;
    String mensaje;
    Map respuestas = new HashMap();
    DaoProyecto dao = new DaoProyecto();
    String params="";
        public ActionProyecto() {
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public List<BeanProyecto> getProyectos() {
        return this.proyectos;
    }

    public void setProyectos(List<BeanProyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public BeanProyecto getBeanProyecto() {
        return this.beanProyecto;
    }

    public void setBeanProyecto(BeanProyecto beanProyecto) {
        this.beanProyecto = beanProyecto;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Map getRespuestas() {
        return this.respuestas;
    }

    public void setRespuestas(Map respuestas) {
        this.respuestas = respuestas;
    }

    public DaoProyecto getDao() {
        return this.dao;
    }

    public void setDao(DaoProyecto dao) {
        this.dao = dao;
    }

    public String consultaProyectos() {
        dao = new DaoProyecto();
        System.out.println("entra");
        ArrayList<BeanProyecto> proyectos=dao.getLista();
       respuestas.put("proyectos", proyectos);
        return "success";
    }
    public String eliminarProyectos() {
        dao = new DaoProyecto();
 beanProyecto=new BeanProyecto();
 beanProyecto.setIdProyecto(Integer.parseInt(params));
        if (dao.eliminar(beanProyecto)){
            respuestas.put("response",true);
        }else{
            respuestas.put("response",false);
        }
        return "success";
    }
}

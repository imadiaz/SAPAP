
package mx.edu.utez.proyecto;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionProyecto extends ActionSupport {
    List<BeanProyecto> proyectos;
    List<BeanProyecto> rds;
    List<BeanProyecto> aps;
    List<BeanProyecto> rapes;
    BeanProyecto beanProyecto;
    String mensaje;

    Map respuestas = new HashMap();
    DaoProyecto dao = new DaoProyecto();
    String params;

        public ActionProyecto() {
    }



    public List<BeanProyecto> getRds() {
        return rds;
    }

    public void setRds(List<BeanProyecto> rds) {
        this.rds = rds;
    }

    public List<BeanProyecto> getAps() {
        return aps;
    }

    public void setAps(List<BeanProyecto> aps) {
        this.aps = aps;
    }

    public List<BeanProyecto> getRapes() {
        return rapes;
    }

    public void setRapes(List<BeanProyecto> rapes) {
        this.rapes = rapes;
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
    public String consultaProyectosIn() {
        dao = new DaoProyecto();
        System.out.println("entra");
        JsonObject object=new JsonParser().parse(params).getAsJsonObject();
        ArrayList<BeanProyecto> proyectos=dao.getListaIndividual(object.get("id").getAsInt());
        respuestas.put("proyectos", proyectos);
        return "success";
    }
    public String consultarRecursos() {
        dao = new DaoProyecto();
        System.out.println("entra");
       rds=dao.getrds();
       aps=dao.getaps();
       rapes=dao.getRapes();
        System.out.println(rapes.size());
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
    public String registrarProyecto() {
        dao = new DaoProyecto();
        System.out.println(params);
        dao.registrarPersona(params);
        return "success";
    }
    public String buscarProyecto() {
        dao = new DaoProyecto();
        System.out.println("id"+params);
        rds=dao.getrds();
        aps=dao.getaps();
        rapes=dao.getRapes();
        beanProyecto=new BeanProyecto();
        beanProyecto=(BeanProyecto) dao.buquedaByID(Integer.parseInt(params));
         JsonArray aps=new JsonArray();
         JsonArray rds=new JsonArray();
         JsonArray raps=new JsonArray();

        for (int i=0; i<beanProyecto.getAps().size();i++){

          aps.add(beanProyecto.getAps().get(i).getIdPersona());

        }

        for (int i=0; i<beanProyecto.getRapes().size();i++){

            raps.add(beanProyecto.getRapes().get(i).getIdPersona());

        }

        for (int i=0; i<beanProyecto.getRds().size();i++){

            rds .add(beanProyecto.getRds().get(i).getIdPersona());

        }
        respuestas.put("aps",aps);
        respuestas.put("rds",rds);
        respuestas.put("rapesitos",raps);
        System.out.println(beanProyecto.getIdProyecto());
        return "success";
    }
    public String modificarProyecto() {
        System.out.println(params);

         boolean var=dao.modificarPersona(params);
        if (var){
            var=true;
        }
        respuestas.put("response",var);
        return "success";
    }
}



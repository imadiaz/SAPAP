package mx.edu.utez.persona;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opensymphony.xwork2.ActionContext;
import mx.edu.utez.horario.BeanHorario;
import mx.edu.utez.persona_rol.DaoPersonaRol;
import mx.edu.utez.rol.BeanRol;
import netscape.javascript.JSObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.opensymphony.xwork2.Action.SUCCESS;

public class ActionPersona {

    private BeanPersona bean = new BeanPersona();
    private DaoPersona dao = new DaoPersona();
    private DaoPersonaRol daoPersonaRol = new DaoPersonaRol();
    private Map session;
    private Map respuestas=new HashMap();
    private List<BeanRol> listaRoles = new ArrayList();
    private List<BeanHorario> listaHorario = new ArrayList();
    private String mensaje;
    private String params;

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Map getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(Map respuestas) {
        this.respuestas = respuestas;
    }

    public DaoPersonaRol getDaoPersonaRol() {
        return daoPersonaRol;
    }

    public void setDaoPersonaRol(DaoPersonaRol daoPersonaRol) {
        this.daoPersonaRol = daoPersonaRol;
    }

    public List<BeanRol> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<BeanRol> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public BeanPersona getBean() {
        return bean;
    }

    public void setBean(BeanPersona bean) {
        this.bean = bean;
    }

    public DaoPersona getDao() {
        return dao;
    }

    public void setDao(DaoPersona dao) {
        this.dao = dao;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }


    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String consultarPersonas(){
        session = ActionContext.getContext().getSession();
        String correo = bean.getCorreoInstitucional();
        String contra = bean.getContrasenia();

        bean = dao.consultarPersonas(bean.getCorreoInstitucional(),bean.getContrasenia());

        if (bean != null) {

            if (correo.equals(bean.getCorreoInstitucional()) && contra.equals(bean.getContrasenia())) {
                listaRoles =daoPersonaRol.consultarRoles(bean);
                for (BeanRol rol: listaRoles) {
                    System.out.println(rol.getTipo());
                }
                mensaje = "¡Bienvenido!";
                session.put("usuario", bean);
                System.out.println(bean.getNombre());
                return "SUCCESS";
            } else {
                mensaje = "Usuario y/o contraseña incorrecta";
                System.out.println(mensaje);
                return "ERROR";
            }

        } else {
            mensaje = "Usuario y/o contraseña incorrecta";
            System.out.println(bean);
            return "ERROR";
        }

    }


    public String consultaPersonas(){

        respuestas.put("personas",dao.getLista());
        return SUCCESS;
    }

    public List<BeanHorario> getListaHorario() {
        return listaHorario;
    }

    public void setListaHorario(List<BeanHorario> listaHorario) {
        this.listaHorario = listaHorario;
    }

    public String eliminarPersona(){
        bean.setIdPersona(Integer.parseInt(params));
        Object bean=getBean();
        System.out.println("id"+params);
        System.out.println(getBean().getIdPersona());
        if (dao.eliminar(bean)){
            respuestas.put("response",true);
        }else{
            respuestas.put("response",false);
        }

        return SUCCESS;
    }
    public String consultaRegistro(){
        listaHorario=new ArrayList<BeanHorario>();
        listaRoles=new ArrayList<BeanRol>();


      respuestas.put("horario",dao.obtenerHorarios());
      respuestas.put("roles",dao.obtenerRoles());
        return SUCCESS;
    }
    public String registroPersona(){
        System.out.println(params);
        BeanPersona bean=new BeanPersona();
        Gson g=new Gson();
        JsonObject object=new JsonParser().parse(params).getAsJsonObject();
        System.out.println(object);
        dao.registrarPersona(params);
        return SUCCESS;
    }
}

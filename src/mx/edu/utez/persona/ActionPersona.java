package mx.edu.utez.persona;

import com.opensymphony.xwork2.ActionContext;
import mx.edu.utez.persona_rol.DaoPersonaRol;
import mx.edu.utez.rol.BeanRol;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.opensymphony.xwork2.Action.SUCCESS;

public class ActionPersona {

    private BeanPersona bean = new BeanPersona();
    private DaoPersona dao = new DaoPersona();
    private DaoPersonaRol daoPersonaRol = new DaoPersonaRol();
    private Map session;
    private List<BeanRol> listaRoles = new ArrayList();
    private String mensaje;

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
    public String estadia(){
        return "SUCCESS";
    }
    public String rh(){
        return "SUCCESS";
    }
}

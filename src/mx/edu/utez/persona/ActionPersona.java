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
        session = ActionContext.getContext().getSession();
        bean = dao.consultarPersonas(bean.getCorreoInstitucional(),bean.getContrasenia());

        if (bean != null) {

            if (correo.equals(bean.getCorreoInstitucional()) && contra.equals(bean.getContrasenia())) {
                listaRoles =daoPersonaRol.consultarRoles(bean);
                String rolUsuario="sin un  rol";
                session.put("usuario", bean);
                if(listaRoles.size()==1){
                    for (BeanRol rol :listaRoles) {
                        rolUsuario=rol.getTipo();
                    }
                    if (rolUsuario.equals("Estadia"))
                    {
                        System.out.println(rolUsuario);

                        return "Estadia";
                    }else if(rolUsuario.equals("Administradora de Recursos Humanos")){
                        System.out.println(rolUsuario);

                        return "Humanos";
                    }
                    else if(rolUsuario.equals("Responsable de Desarrollo")){
                        System.out.println(rolUsuario);

                        return "Desarrollo";
                    }
                    else if(rolUsuario.equals("RAPE")){
                        System.out.println(rolUsuario);

                        return "RAPE";
                    }
                    else if(rolUsuario.equals("Coordinador del CDS")){
                        System.out.println(rolUsuario);

                        return "COD";
                    }
                    else if(rolUsuario.equals("Analista Programador")){
                        System.out.println(rolUsuario);

                        return "Analista";
                    }
                }else {


                    mensaje = "¡Bienvenido!";

                    session.put("roles", listaRoles);
                    System.out.println(bean.getNombre());
                    return "SUCCESS";
                }
            } else {
                mensaje = "Usuario y/o contraseña incorrecta";
                System.out.println(mensaje);
                return "ERROR";
            }
            return "ERROR";
        } else {
            mensaje = "Usuario y/o contraseña incorrecta";
            System.out.println(bean);
            return "ERROR";
        }

    }
    public String estadia(){
        //session = ActionContext.getContext().getSession();

        return "SUCCESS";
    }
    public String rh(){
        //session = ActionContext.getContext().getSession();

        return "SUCCESS";
    }
    public String rape(){
        //session = ActionContext.getContext().getSession();

        return "SUCCESS";
    }
    public String coo(){
        //session = ActionContext.getContext().getSession();

        return "SUCCESS";
    }
    public String ap(){
        //session = ActionContext.getContext().getSession();

        return "SUCCESS";
    }
    public String rd(){
        //session = ActionContext.getContext().getSession();

        return "SUCCESS";
    }

    public String cerrarSesion(){
        session = ActionContext.getContext().getSession();
        session.clear();
        return SUCCESS;
    }

}

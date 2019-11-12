package mx.edu.utez.persona;

import mx.edu.utez.rol.BeanRol;

import java.util.ArrayList;
import java.util.List;
import static com.opensymphony.xwork2.Action.SUCCESS;

public class ActionPersona {

    private BeanPersona bean = new BeanPersona();
    private DaoPersona dao = new DaoPersona();
    private List<BeanRol> listaPersonas = new ArrayList();
    private String mensaje;

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

    public List<BeanRol> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(List<BeanRol> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String consultarPersonas(){
        String correo = bean.getCorreoInstitucional();
        String matricula = bean.getMatricula();
        listaPersonas = dao.consultarPersonas(bean.getCorreoInstitucional(),bean.getMatricula());
        for (BeanRol rol:listaPersonas             ) {
            System.out.println(rol.getTipo());
        }
        System.out.println(correo+" " +matricula);
        return "SUCCESS";
    }
}

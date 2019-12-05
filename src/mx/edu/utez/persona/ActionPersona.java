package mx.edu.utez.persona;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opensymphony.xwork2.ActionContext;
import mx.edu.utez.horario.BeanHorario;
import mx.edu.utez.persona_rol.DaoPersonaRol;
import mx.edu.utez.rol.BeanRol;
import netscape.javascript.JSObject;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static com.opensymphony.xwork2.Action.SUCCESS;

public class ActionPersona {

    private BeanPersona bean = new BeanPersona();
    private DaoPersona dao = new DaoPersona();
    private DaoPersonaRol daoPersonaRol = new DaoPersonaRol();
    private Map session;
    private Map respuestas = new HashMap();
    private List<BeanRol> listaRoles = new ArrayList();
    private List<BeanHorario> listaHorario = new ArrayList();
    private String mensaje="";
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


    public String consultarPersonas() throws NoSuchAlgorithmException {

        String correo = bean.getCorreoInstitucional();
        String contra = Encriptar(bean.getContrasenia());
        session = ActionContext.getContext().getSession();

        System.out.println(correo+" "+contra);
        bean = dao.consultarPersonas(bean.getCorreoInstitucional(), Encriptar(bean.getContrasenia()));

        if (bean != null) {
            System.out.println(contra+" "+bean.getContrasenia());
            if (correo.equals(bean.getCorreoInstitucional()) && contra.equals(bean.getContrasenia())) {
                listaRoles = daoPersonaRol.consultarRoles(bean);
                String rolUsuario = "sin un  rol";
                session.put("usuario", bean);
                session.put("roles", listaRoles);

                if (listaRoles.size() == 1) {
                    for (BeanRol rol : listaRoles) {
                        rolUsuario = rol.getTipo();
                    }
                    if (rolUsuario.equals("Estadia")) {
                        System.out.println(rolUsuario);

                        return "Estadia";
                    } else if (rolUsuario.equals("Administradora de Recursos Humanos")) {
                        System.out.println(rolUsuario);

                        return "Humanos";
                    } else if (rolUsuario.equals("Responsable de Desarrollo")) {
                        System.out.println(rolUsuario);

                        return "Desarrollo";
                    } else if (rolUsuario.equals("RAPE")) {
                        System.out.println(rolUsuario);

                        return "RAPE";
                    } else if (rolUsuario.equals("Coordinador del CDS")) {
                        System.out.println(rolUsuario);

                        return "COD";
                    } else if (rolUsuario.equals("Analista Programador")) {
                        System.out.println(rolUsuario);

                        return "Analista";
                    }
                } else {


                    mensaje = "¡Bienvenido!";


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
//return "ERROR";
    }


    public String consultaPersonas2() {
        respuestas.put("personas", dao.getLista());
        System.out.println(listaRoles);
        System.out.println("sdfjkshfkjksj");
        System.out.println(session.get("usuario"));
        return SUCCESS;
    }

    public List<BeanHorario> getListaHorario() {
        return listaHorario;
    }

    public void setListaHorario(List<BeanHorario> listaHorario) {
        this.listaHorario = listaHorario;
    }

    public String eliminarPersona() {
        bean.setIdPersona(Integer.parseInt(params));
        Object bean = getBean();
        System.out.println("id" + params);
        System.out.println(getBean().getIdPersona());
        if (dao.eliminar(bean)) {
            respuestas.put("response", true);
        } else {
            respuestas.put("response", false);
        }

        return SUCCESS;
    }

    public String consultaRegistro() {
        listaHorario = new ArrayList<BeanHorario>();
        listaRoles = new ArrayList<BeanRol>();


        respuestas.put("horario", dao.obtenerHorarios());
        respuestas.put("roles", dao.obtenerRoles());
        return SUCCESS;
    }

    public String registroPersona() {
        System.out.println(params);
        BeanPersona bean = new BeanPersona();
        Gson g = new Gson();
        JsonObject object = new JsonParser().parse(params).getAsJsonObject();
        System.out.println(object);
        dao.registrarPersona(params);
        boolean var=false;
        if (object!=null){
            var=true;
        }
        respuestas.put("response",var);
        return SUCCESS;
    }

    public String estadia() {


        //session = ActionContext.getContext().getSession();

        return "SUCCESS";
    }

    public String rh() {
        //session = ActionContext.getContext().getSession();

        return "SUCCESS";
    }

    public String rape() {
        //session = ActionContext.getContext().getSession();

        return "SUCCESS";
    }

    public String coo() {
        //session = ActionContext.getContext().getSession();

        return "SUCCESS";
    }

    public String ap() {
        //session = ActionContext.getContext().getSession();

        return "SUCCESS";
    }

    public String rd() {
        //session = ActionContext.getContext().getSession();

        return "SUCCESS";
    }

    public String cerrarSesion() {
        session = ActionContext.getContext().getSession();
        System.out.println(session.get("usuario"));
        session.clear();
        System.out.println("Adios Sesion");
        System.out.println(session.get("usuario"));
        return "SUCCESS";
    }

    public String consultaMisEstudiantes() {

        respuestas.put("personas", dao.getLista());
        return SUCCESS;
    }

    public String buscarPersona() {

        System.out.println("id" + params);
        bean = new BeanPersona();
        bean = (BeanPersona) dao.buquedaByID(Integer.parseInt(params));
        String correo = bean.getCorreoInstitucional();
        correo = correo.replace("@utez.edu.mx", "");
        JsonArray arr = new JsonArray();
        for (int i = 0; i < bean.getRoles().size(); i++) {

            arr.add(bean.getRoles().get(i).getIdRol());
        }
        params = arr.toString();
        System.out.println(params);
        respuestas.put("epa", params);
        respuestas.put("bean", bean);
        bean.setCorreoInstitucional(correo);
        System.out.println(bean.getNombre());
        return "success";
    }

    public String modificarPersona() {
        System.out.println(params);
        boolean var=dao.act(params);
        if (var){
            var=true;
        }
        respuestas.put("response",var);

        return "success";
    }

    public String consultaMod() {
        listaHorario = new ArrayList<BeanHorario>();
        listaRoles = new ArrayList<BeanRol>();
        System.out.println(params);
        Gson g = new Gson();
        JsonObject object = new JsonParser().parse(params).getAsJsonObject();
        System.out.println("id" + params);
        BeanPersona binsito = new BeanPersona();

        binsito = new BeanPersona();
        binsito = (BeanPersona) dao.buquedaByID(object.get("id").getAsInt());

        JsonArray arr = new JsonArray();
        for (int i = 0; i < binsito.getRoles().size(); i++) {
            System.out.println("binsito" + binsito.getRoles().get(i).getIdRol());
            arr.add(binsito.getRoles().get(i).getIdRol());
        }
        params = arr.toString();
        System.out.println(params);
        respuestas.put("rolesDePersona", arr.toString());
        respuestas.put("horario", dao.obtenerHorarios());
        respuestas.put("roles", dao.obtenerRoles());
        return SUCCESS;
    }

    public String actualizarDesempenio() {
        System.out.println(params);
        BeanPersona bean = new BeanPersona();
        Gson g = new Gson();
        JsonObject object = new JsonParser().parse(params).getAsJsonObject();
        System.out.println(object);
        dao.desemp(params);
        boolean var=true;
        if (object!=null){
            var=true;
        }
        respuestas.put("response",var);
        return SUCCESS;
    }

    public String buscarPerfil() {
        int id = bean.getIdPersona();

        bean = dao.consultarPersonaPorId(id);
        if (bean != null) {
            mensaje = "null";
            System.out.println("mensaje vale"+mensaje);
            return "SUCCESS";
        } else {
            return "ERROR";
        }
    }

    public String modificarPerfil() {
        System.out.println(bean.getIdPersona());
        if (dao.actulizarPerfil(bean)) {
            mensaje="Datos Modificados Correctamente";
            return "SUCCESS";
        } else {
            mensaje="Ocurrio un error al actualizar ";
            System.out.println("ERROR");
            return "ERROR";
        }

    }

    public String modificarContra() {
        System.out.println(bean.getIdPersona());
        if (dao.cambiarContra2(bean.getIdPersona(),bean.getContrasenia())) {
            System.out.println("CORRECTO");
            int id = bean.getIdPersona();
            return "SUCCESS";
        } else {
            System.out.println("ERROR");
            return "ERROR";
        }

    }



    /*public String encriptar(String cadena) throws NoSuchAlgorithmException {
        // TODO code application logic here
        String password = cadena;

        MessageDigest md = MessageDigest.getInstance("SHA");
        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

// bytes to hex
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

*/
    public static String Encriptar(String texto) {

        String secretKey = "SAPAP"; //llave para encriptar datos
        String base64EncryptedString = "";

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = org.apache.commons.codec.binary.Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    public static String Desencriptar(String textoEncriptado) throws Exception {

        String secretKey = "SAPAP"; //llave para encriptar datos
        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

}

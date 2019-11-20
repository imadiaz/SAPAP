package mx.edu.utez.servicios;

import mx.edu.utez.persona.BeanPersona;
import mx.edu.utez.persona.DaoPersona;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static com.opensymphony.xwork2.Action.SUCCESS;

public class RecuperarConstraseña {
    private DaoPersona dao = new DaoPersona();
    private BeanPersona bean = new BeanPersona();



    private String from ="mariovalverde@utez.edu.mx";
    private String password="Pituchin360";
    private String to = bean.getCorreoInstitucional();

    private String subject="Recuperaicón de Contraseña";
    private String body;
    private  String mensaje;


    static Properties properties = new Properties();

    static {
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
    }

    public String execute() {
        to = bean.getCorreoInstitucional();
        bean=dao.consultarCorreo(to);
        if(bean ==null){
          return "ERROR";
        }else {
            System.out.println(to);


            try {
                Session session = Session.getDefaultInstance(properties,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication
                            getPasswordAuthentication() {
                                return new
                                        PasswordAuthentication(from, password);
                            }
                        }
                );
                Random ran = new Random();
                String alfa = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
                String cadena = "";
                int num;
                int forma;
                forma = (int) (ran.nextDouble() * alfa.length() - 1 + 0);
                num = (int) (ran.nextDouble() * 999 + 100);
                cadena = cadena + alfa.charAt(forma) + num;
                dao.codigo(to.toString(), cadena);
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(to));
                message.setSubject(subject);
                message.setContent("<html><head><meta charser='utf-8'></head><body><h1>SAPAP</h1>\n<h3>Recuperación de Contraseña</h3>\n<strong> Se solicito la recuperación de la contraseña " +
                        " al correo" + to.toString() + "\n " + "Código: " + cadena + "</strong></body></html>", "text/html; charset = ISO-8859-1");
                Transport.send(message);
            } catch (Exception e) {
                mensaje = "El correo no se encuentra registrado intente con uno valido por favor";
                e.printStackTrace();
                return "ERROR";

            }
            return "SUCCESS";
        }
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public static Properties getProperties() {
        return properties;
    }

    public static void setProperties(Properties properties) {
        RecuperarConstraseña.properties = properties;
    }

    public DaoPersona getDao() {
        return dao;
    }

    public void setDao(DaoPersona dao) {
        this.dao = dao;
    }

    public BeanPersona getBean() {
        return bean;
    }

    public void setBean(BeanPersona bean) {
        this.bean = bean;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
/**
 * public static void main(String[] args) {
 * Random ran = new Random();
 * String alfa ="ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
 * String cadena="";
 * int num;
 * int forma;
 * forma=(int)(ran.nextDouble()*alfa.length()-1+0);
 * num=(int)(ran.nextDouble() * 999+100);
 * cadena=cadena+alfa.charAt(forma)+num;
 * <p>
 * System.out.println(cadena);
 * }
 */
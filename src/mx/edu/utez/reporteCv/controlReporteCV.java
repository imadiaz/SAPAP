package mx.edu.utez.reporteCv;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import mx.edu.utez.conexion.MySQLConexion;
import mx.edu.utez.persona.BeanPersona;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class controlReporteCV extends ActionSupport {

    private Map respuestas = new HashMap();
    private BeanPersona unUsuarioCargado = (BeanPersona) ActionContext.getContext().getSession().get("usuario");
    private Connection conexion;
    private List myList;
    Map parametros;


    public String reporteCV() throws SQLException, IOException {
        try{
           /* URL url = new URL("http://localhost:8080/SAPAP/img/email1.png");
            BufferedImage bufferedImage = ImageIO.read(url);

            URL url1 = new URL("http://localhost:8080/SAPAP/img/celular.jpg");
            BufferedImage bufferedImage1 = ImageIO.read(url1);

            URL url2 = new URL("http://localhost:8080/SAPAP/img/direccion.png");
            BufferedImage bufferedImage2 = ImageIO.read(url2);

            URL url3 = new URL("http://localhost:8080/SAPAP/img/telefono.png");
            BufferedImage bufferedImage3 = ImageIO.read(url3);

            URL url4 = new URL("http://localhost:8080/SAPAP/img/celular.png");
            BufferedImage bufferedImage4 = ImageIO.read(url4);

            */
            myList = new ArrayList();
            parametros = new HashMap();
            conexion = MySQLConexion.getConnection();
            parametros.put("idPersona", unUsuarioCargado.getIdPersona());
           /* parametros.put("imagen1",bufferedImage);
            parametros.put("imagen2",bufferedImage1);
            parametros.put("imagen3",bufferedImage2);
            parametros.put("imagen4",bufferedImage3);
            parametros.put("imagen5",bufferedImage4);

            */
            System.out.println("idPersona"+unUsuarioCargado.getIdPersona());
            System.out.println("Conexión:  " + conexion.toString());

        }catch (Exception e){
            System.out.println("Error reporte" + e.getMessage());
        }
        return SUCCESS;
    }

    public Map getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(Map respuestas) {
        this.respuestas = respuestas;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public List getMyList() {
        return myList;
    }

    public void setMyList(List myList) {
        this.myList = myList;
    }

    public Map getParametros() {
        return parametros;
    }

    public void setParametros(Map parametros) {
        this.parametros = parametros;
    }

    public BeanPersona getUnUsuarioCargado() {
        return unUsuarioCargado;
    }

    public void setUnUsuarioCargado(BeanPersona unUsuarioCargado) {
        this.unUsuarioCargado = unUsuarioCargado;
    }
}
package mx.edu.utez.reporteCv;

import com.opensymphony.xwork2.ActionSupport;
import mx.edu.utez.conexion.MySQLConexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class controlReporteCV extends ActionSupport {

    private Map respuestas = new HashMap();
    private Connection conexion;
    private List myList;
    Map parametros;


    public String reporteCV() throws SQLException {
        myList = new ArrayList();
        parametros = new HashMap();
        conexion = MySQLConexion.getConnection();
        parametros.put("idPersona", 1);
        System.out.println("Conexión:  " + conexion.toString());
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
}

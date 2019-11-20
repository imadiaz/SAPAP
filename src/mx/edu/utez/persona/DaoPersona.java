package mx.edu.utez.persona;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import mx.edu.utez.conexion.MySQLConexion;
import mx.edu.utez.rol.BeanRol;
import org.apache.struts2.components.Bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoPersona {

    private Connection conexion;
    private PreparedStatement pstm;
    private ResultSet rs;

    final private String SQL_CONSULTAR_PERSONA = "call login(?,?)";
    final private String SQL_GENERAR_CODIGO = "call generarCodigo(?,?)";
    final private String SQL_Consultar_Correo = "Select * from Persona where correoInstitucional=?;";


    public BeanPersona consultarPersonas(String correo, String contra) {
        BeanPersona bean = null;
        try {
            conexion = MySQLConexion.getConnection();
            pstm = conexion.prepareStatement(SQL_CONSULTAR_PERSONA);
            pstm.setString(1, correo);
            pstm.setString(2, contra);
            rs = pstm.executeQuery();
            while (rs.next()) {
                bean = new BeanPersona();
                bean.setIdPersona(rs.getInt("id"));
                bean.setNombre(rs.getString("nombre"));
                bean.setPrimerApellido(rs.getString("nombre"));
                bean.setSegundoApellido(rs.getString("nombre"));
                bean.setFechaDeNacimiento(rs.getString("fechaNacimiento"));
                bean.setCorreoInstitucional(rs.getString("correoInstitucional"));
                bean.setCorreoPersonal(rs.getString("correoInstitucional"));
                bean.setMatricula(rs.getString("matricula"));
                bean.setContrasenia(rs.getString("contrasenia"));

            }
            if (bean == null) {
                System.out.println("es nulo");
            } else {
                System.out.println(bean.getNombre());
            }
            rs.close();
            pstm.close();
            conexion.close();
        } catch (Exception e) {
            System.out.println("Error en el metodo consultar personas: " + e.getMessage());
            System.out.println(e.getCause());
        } finally {
            try {
                rs.close();
                pstm.close();
                conexion.close();
            } catch (Exception e) {

            }
        }
        return bean;
    }

    public BeanPersona consultarCorreo(String correo){
        BeanPersona bean = null;
        try{
            conexion = MySQLConexion.getConnection();
            pstm = conexion.prepareStatement(SQL_Consultar_Correo);
            pstm.setString(1,correo);
            rs = pstm.executeQuery();
            while (rs.next()) {
                bean = new BeanPersona();
                bean.setIdPersona(rs.getInt("id"));
                bean.setNombre(rs.getString("nombre"));
                bean.setPrimerApellido(rs.getString("nombre"));
                bean.setSegundoApellido(rs.getString("nombre"));
                bean.setFechaDeNacimiento(rs.getString("fechaNacimiento"));
                bean.setCorreoInstitucional(rs.getString("correoInstitucional"));
                bean.setCorreoPersonal(rs.getString("correoInstitucional"));
                bean.setMatricula(rs.getString("matricula"));
                bean.setContrasenia(rs.getString("contrasenia"));
                bean.setCodigo(rs.getString("codigo"));

            }
            if (bean == null) {
                System.out.println("es nulo");
            } else {
                System.out.println(bean.getNombre());
            }
            rs.close();
            pstm.close();
            conexion.close();
        } catch (Exception e) {
            System.out.println("Error en el metodo consultar correo: " + e.getMessage());
            System.out.println(e.getCause());
        } finally {
            try {
                rs.close();
                pstm.close();
                conexion.close();
            } catch (Exception e) {

            }
        }
        return bean;
    }


    public void codigo(String correo, String codigo) {
        boolean res = false;
        try {
            conexion = MySQLConexion.getConnection();
            pstm = conexion.prepareStatement(SQL_GENERAR_CODIGO);
            pstm.setString(1, correo);
            pstm.setString(2, codigo);
            rs = pstm.executeQuery();
            res=true;
            rs.close();
            pstm.close();
            conexion.close();
        } catch (Exception e) {
            System.out.println("Error en el metodo consultar personas: " + e.getMessage());
            System.out.println(e.getCause());
        } finally {
            try {
                rs.close();
                pstm.close();
                conexion.close();
            } catch (Exception e) {

            }
        }
    }

}

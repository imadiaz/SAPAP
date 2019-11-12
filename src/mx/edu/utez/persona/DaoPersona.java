package mx.edu.utez.persona;

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

    final private String SQL_CONSULTAR_PERSONA ="call sp_user_rol(?,?)";


    public List<BeanRol> consultarPersonas(String correo, String matricula){
        List<BeanRol> personas = new ArrayList();
        try{
            conexion = MySQLConexion.getConnection();
            pstm = conexion.prepareStatement(SQL_CONSULTAR_PERSONA);
            pstm.setString(1,correo);
            pstm.setString(2,matricula);
            rs = pstm.executeQuery();
            while (rs.next()){
                BeanRol bean = new BeanRol();
                bean.setTipo(rs.getString("descripcion"));
                personas.add(bean);
            }
            rs.close();
            pstm.close();
            conexion.close();
        }catch (Exception e){
            System.out.println("Error en el metodo consultar personas: "+e.getMessage());
            System.out.println(e.getCause());
        }finally {
            try{
                rs.close();
                pstm.close();
                conexion.close();
            }catch(Exception e){

            }
        }
        return personas;
    }


}

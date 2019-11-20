package mx.edu.utez.persona_rol;

import mx.edu.utez.conexion.MySQLConexion;
import mx.edu.utez.persona.BeanPersona;
import mx.edu.utez.rol.BeanRol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoPersonaRol {
    private Connection conexion;
    private PreparedStatement pstm;
    private ResultSet rs;

    private final String ROLES = "call sp_roles(?);";

    public List<BeanRol> consultarRoles(BeanPersona beanPersona){
        List<BeanRol> roles = new ArrayList();
        try{
            System.out.println(beanPersona.getIdPersona());
            conexion= MySQLConexion.getConnection();
            pstm=conexion.prepareStatement(ROLES);
            pstm.setInt(1,beanPersona.getIdPersona());
            rs=pstm.executeQuery();
            while (rs.next()){
                BeanRol rol = new BeanRol();
                rol.setIdRol(rs.getInt("idRol"));
                rol.setTipo(rs.getString("descripcion"));
                roles.add(rol);

            }
        }catch(Exception e){

        }
        return roles;
    }

}

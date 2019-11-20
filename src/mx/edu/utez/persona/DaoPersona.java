package mx.edu.utez.persona;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import mx.edu.utez.conexion.MySQLConexion;
import mx.edu.utez.horario.BeanHorario;
import mx.edu.utez.modelo.Dao;
import mx.edu.utez.modelo.DaoInterfaz;
import mx.edu.utez.rol.BeanRol;
import org.apache.struts2.components.Bean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoPersona extends Dao implements DaoInterfaz {

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


    @Override
    public boolean registrar(Object bean) {
return true;
    }
    public boolean registrarPersona(String bean) {
        Gson g=new Gson();
        JsonObject object=new JsonParser().parse(bean).getAsJsonObject();
        int idHorairo=object.get("horario").getAsInt();
        System.out.println(idHorairo);
        System.out.println("nombre"+object.get("nombre").getAsString());
        String fecha=object.get("fechaDeIngreso").getAsString();
        System.out.println(fecha);
        try{
            conexion = MySQLConexion.getConnection();
            pstm = conexion.prepareStatement("insert into Persona (nombre,primerApellido,segundoApellido,estado,fechaNacimiento,correoInstitucional,correoPersona,numeroTelefonico,numeroCasa,matricula,carreraDeEgreso," +
                    "universidadDeEgreso,horarios_id,fechaDeIngreso,contrasenia,direccion) values(?,?,?,1,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

                  pstm.setString(1,object.get("nombre").getAsString());
                pstm.setString(2,object.get("primerApellido").getAsString());
                pstm.setString(3,object.get("segundoApellido").getAsString());
                pstm.setString(4,object.get("fechaNac").getAsString());
                pstm.setString(5,object.get("correoInstitucional").getAsString()+"@utez.edu.mx");
                pstm.setString(6,object.get("correoP").getAsString());
                pstm.setString(7,object.get("numeroTel").getAsString());
                pstm.setString(8,object.get("numeroPersonal").getAsString());
                pstm.setString(9,object.get("matricula").getAsString());
                pstm.setString(10,object.get("carreraDeEgreso").getAsString());
                pstm.setString(11,object.get("universidadDeEgreso").getAsString());
                pstm.setString(11,object.get("universidadDeEgreso").getAsString());
                pstm.setInt(12,idHorairo);

                pstm.setString(13,fecha);
                pstm.setString(14,object.get("contrasena").getAsString());
                pstm.setString(15,object.get("direccion").getAsString());
                pstm.execute();
            rs = pstm.getGeneratedKeys();
            int id=0;
            while (rs.next()){
              id=rs.getInt(1);
          }
            int ids[]=new int[10];
            JsonArray json=object.get("roles").getAsJsonArray();
         for (int i=0;i<json.size();i++){
             ids[i]=json.get(i).getAsInt();
             pstm = conexion.prepareStatement("insert into Persona_Rol (Rol_id,Persona_id) values(?,?)");
             pstm.setInt(1,ids[i]);
             pstm.setInt(2,id);
             pstm.execute();
         }
            if (Boolean.parseBoolean(object.get("estudiante").getAsString())){
                pstm = conexion.prepareStatement("insert into Estudiante (cuatrimestreDeIngreso,grupoActual,cuatrimestreActual,estado,Persona_id) values(?,?,?,1,?)");
                pstm.setString(1,object.get("cuatrimestreDeIngreso").getAsString());
                pstm.setString(2,object.get("grupoActual").getAsString());
                pstm.setString(3,object.get("cuatrimestreActual").getAsString());
                pstm.setInt(4,id);
                pstm.execute();
                if (Boolean.parseBoolean(object.get("becario").getAsString())){
                    pstm = conexion.prepareStatement("insert into becario (beca,Persona_Rol_id) values(?,?)");
                    pstm.setDouble(1,object.get("beca").getAsDouble());

                    pstm.setInt(2,id);
                    pstm.execute();
                }
            }
            rs.close();
            pstm.close();
            conexion.close();
        }catch (Exception e){
            System.out.println("Error en el metodo registrar personas: "+e);
        }finally {
            try{
                rs.close();
                pstm.close();
                conexion.close();
            } catch (Exception e) {

            }
        }
        return true;

    }

    @Override
    public boolean eliminar(Object bean) {
        BeanPersona beanP=(BeanPersona) bean;
        boolean res=false;
        try{
            mySQLRepositorio("update Persona set estado=0 where id=?");
            preparedStatement.setInt(1,beanP.getIdPersona());
           if (preparedStatement.executeUpdate()>=1){
               res=true;
           }else {
               res =false;
           }


        }catch(Exception e){
            System.out.println(e);
        } finally {
            cerrarConexiones();
        }
        return res;
    }

    @Override
    public boolean actualizar(Object bean) {
        return false;
    }

    @Override
    public ArrayList getLista() {
        Connection con=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;
        ArrayList lista = new ArrayList();
        BeanPersona beanPersona = new BeanPersona();
        try{
             con=MySQLConexion.getConnection();
            pstm=con.prepareStatement("SELECT * FROM Persona as p inner join Horario as h on p.horarios_id=h.id where  estado=1");

            resultSet = pstm.executeQuery();
            while(resultSet.next()){
                beanPersona=new BeanPersona();
                beanPersona.setCarreraDeEgreso(resultSet.getString("universidadDeEgreso"));
                beanPersona.setContrasenia(resultSet.getString("contrasenia"));
                beanPersona.setCorreoInstitucional(resultSet.getString("correoInstitucional"));
                beanPersona.setCorreoPersonal(resultSet.getString("correoPersona"));
                beanPersona.setDesempenio(resultSet.getString("desempeño"));
                beanPersona.setFechaDeEgreso(resultSet.getString("fechaDeEgreso"));
                beanPersona.setFechaDeIngreso(resultSet.getString("fechaDeIngreso"));
                beanPersona.setFechaDeNacimiento(resultSet.getString("fechaNacimiento"));
                BeanHorario horario=new BeanHorario();
                horario.setHorario(resultSet.getString("horario"));
                horario.setIdHorario(resultSet.getInt("h.id"));
                beanPersona.setHorario(horario);
                beanPersona.setIdPersona(resultSet.getInt("p.id"));
                beanPersona.setMatricula(resultSet.getString("matricula"));
                beanPersona.setNombre(resultSet.getString("nombre"));
                beanPersona.setNumeroCasa(resultSet.getString("numeroCasa"));
                beanPersona.setNumeroTelefonico(resultSet.getString("numeroTelefonico"));
                beanPersona.setPrimerApellido(resultSet.getString("primerApellido"));
                beanPersona.setSegundoApellido(resultSet.getString("segundoApellido"));
                beanPersona.setUniversidadDeEgreso(resultSet.getString("universidadDeEgreso"));
                beanPersona.setEstado((resultSet.getInt("estado")==1)?true:false );
                lista.add(beanPersona);
            }

        }catch(Exception e){
            System.out.println(e);
        } finally {
            try {

                con.close();
                rs.close();
                pstm.close();
            }catch (Exception e){
                System.out.println();
            }
        }
        System.out.println(lista.size());
        return lista;
    }

    @Override
    public Object buquedaByID(int id) {
        return null;

    }



    public ArrayList obtenerRoles(){
        ArrayList lista = new ArrayList();
        BeanRol beanRol = new BeanRol();
        try{
            mySQLRepositorio("SELECT * FROM Rol");

            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                beanRol=new BeanRol();
                beanRol.setIdRol(resultSet.getInt("idRol"));
                beanRol.setTipo(resultSet.getString("descripcion"));

                lista.add(beanRol);
            }

        }catch(Exception e){
            System.out.println(e);
        } finally {
            cerrarConexiones();
        }
        System.out.println(lista.size());
        return lista;
    }
    public ArrayList obtenerHorarios(){
        ArrayList lista = new ArrayList();
        BeanHorario bean=new BeanHorario();
        try{
            mySQLRepositorio("SELECT * FROM Horario");

            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                bean=new BeanHorario();
                bean.setIdHorario(resultSet.getInt("id"));
                bean.setHorario(resultSet.getString("horario"));

                lista.add(bean);
            }

        }catch(Exception e){
            System.out.println(e);
        } finally {
            cerrarConexiones();
        }
        System.out.println(lista.size());
        return lista;
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

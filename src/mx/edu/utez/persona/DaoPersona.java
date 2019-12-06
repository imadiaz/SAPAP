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

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoPersona extends Dao implements DaoInterfaz {

    private Connection conexion;
    private PreparedStatement pstm;
    private ResultSet rs;

    final private String SQL_CONSULTAR_PERSONA = "call login(?,?)";
    final private String SQL_GENERAR_CODIGO = "call generarCodigo(?,?)";
    final private String SQL_CONSULTAR_CODIGO = "call consultarCodigo(?)";
    final private String SQL_BUSCAR_PERSONA = "call buscarPersona(?)";
    final private String SQL_MODIFICAR_PERFIL = "call modificarPerfil(?,?,?,?,?,?,?,?)";
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
                BeanHorario beanHorario = new BeanHorario();
                bean.setIdPersona(rs.getInt("id"));
                bean.setNombre(rs.getString("nombre"));
                bean.setPrimerApellido(rs.getString("primerApellido"));
                bean.setSegundoApellido(rs.getString("segundoApellido"));
                bean.setContrasenia(rs.getString("contrasenia"));
                bean.setCorreoPersonal(rs.getString("correoPersona"));
                bean.setCorreoInstitucional(rs.getString("correoInstitucional"));
                bean.setMatricula(rs.getString("matricula"));
                bean.setNumeroTelefonico(rs.getString("numeroTelefonico"));
                bean.setNumeroCasa(rs.getString("numeroCasa"));
                bean.setFechaDeNacimiento(rs.getString("fechaNacimiento"));
                bean.setCarreraDeEgreso(rs.getString("carreraDeEgreso"));
                beanHorario.setIdHorario(rs.getInt("idHorario"));
                beanHorario.setHorario(rs.getString("horario"));
                bean.setHorario(beanHorario);
                bean.setUniversidadDeEgreso(rs.getString("universidadDeEgreso"));
                bean.setFechaDeIngreso(rs.getString("fechaDeIngreso"));
                bean.setDireccion(rs.getString("direccion"));

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
                pstm.setString(14,ActionPersona.Encriptar(object.get("contrasena").getAsString()));
                pstm.setString(15,object.get("direccion").getAsString());
                pstm.execute();
            rs = pstm.getGeneratedKeys();
            int id=0;
            while (rs.next()){
              id=rs.getInt(1);
          }
            List<Integer> ids=new ArrayList<>();
            JsonArray json=object.get("roles").getAsJsonArray();
         for (int i=0;i<json.size();i++){
             ids.add(json.get(i).getAsInt());
             pstm = conexion.prepareStatement("insert into Persona_Rol (Rol_id,Persona_id) values(?,?)");
             pstm.setInt(1,ids.get(i));
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
                    System.out.println("persona ID becario: "+id);
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
        Connection con=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;
        ArrayList lista = new ArrayList();
        BeanPersona beanPersona = new BeanPersona();
        try{
            con=MySQLConexion.getConnection();
            pstm=con.prepareStatement("SELECT * FROM Persona as p inner join Horario as h on p.horarios_id=h.id where  estado=1 and p.id=?");
            pstm.setInt(1,id);
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
                beanPersona.setDireccion(resultSet.getString("direccion"));
                horario.setHorario(resultSet.getString("horario"));
                horario.setIdHorario(resultSet.getInt("h.id"));
                beanPersona.setHorario(horario);
                beanPersona.setContrasenia(resultSet.getString("contrasenia"));
                beanPersona.setIdPersona(resultSet.getInt("p.id"));
                beanPersona.setMatricula(resultSet.getString("matricula"));
                beanPersona.setNombre(resultSet.getString("nombre"));
                beanPersona.setNumeroCasa(resultSet.getString("numeroCasa"));
                beanPersona.setNumeroTelefonico(resultSet.getString("numeroTelefonico"));
                beanPersona.setPrimerApellido(resultSet.getString("primerApellido"));
                beanPersona.setSegundoApellido(resultSet.getString("segundoApellido"));
                beanPersona.setUniversidadDeEgreso(resultSet.getString("universidadDeEgreso"));
                beanPersona.setEstado((resultSet.getInt("estado")==1)?true:false );
                pstm=con.prepareStatement("SELECT * FROM Persona_Rol  where  Persona_id=?");
                pstm.setInt(1,beanPersona.getIdPersona());
                resultSet = pstm.executeQuery();
                List<BeanRol> roles=new ArrayList<>();
                JsonArray arr=new JsonArray();

                while (resultSet.next()){
                    BeanRol bean=new BeanRol();
                    bean.setIdRol(resultSet.getInt("Rol_id"));
                    roles.add(bean);
                }
                beanPersona.setRoles(roles);
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
        return lista.get(0);

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
            res = true;
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

    public BeanPersona consultarCodigo(String codigo) {
        BeanPersona bean = null;
        try {
            conexion = MySQLConexion.getConnection();
            pstm = conexion.prepareStatement(SQL_CONSULTAR_CODIGO);
            pstm.setString(1, codigo);

            rs = pstm.executeQuery();
            while (rs.next()) {
                bean = new BeanPersona();
                bean.setIdPersona(rs.getInt("id"));
                bean.setNombre(rs.getString("nombre"));
                bean.setPrimerApellido(rs.getString("primerApellido"));
                bean.setSegundoApellido(rs.getString("segundoApellido"));
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
            System.out.println("Error en el metodo consultar codigo: " + e.getMessage());
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
    public BeanPersona consultarPersonaPorId(int idPersona) {
        BeanPersona bean = null;
        try {
            conexion = MySQLConexion.getConnection();
            pstm = conexion.prepareStatement(SQL_BUSCAR_PERSONA);
            pstm.setInt(1, idPersona);
            rs = pstm.executeQuery();
            if (rs.next()) {
                bean = new BeanPersona();
                BeanHorario beanHorario = new BeanHorario();
                bean.setIdPersona(rs.getInt("id"));
                bean.setNombre(rs.getString("nombre"));
                bean.setPrimerApellido(rs.getString("primerApellido"));
                bean.setSegundoApellido(rs.getString("segundoApellido"));
                bean.setContrasenia(ActionPersona.Desencriptar(rs.getString("contrasenia")));
                bean.setCorreoPersonal(rs.getString("correoPersona"));
                bean.setCorreoInstitucional(rs.getString("correoInstitucional"));
                bean.setMatricula(rs.getString("matricula"));
                bean.setNumeroTelefonico(rs.getString("numeroTelefonico"));
                bean.setNumeroCasa(rs.getString("numeroCasa"));
                bean.setFechaDeNacimiento(rs.getString("fechaNacimiento"));
                bean.setCarreraDeEgreso(rs.getString("carreraDeEgreso"));
                beanHorario.setIdHorario(rs.getInt("idHorario"));
                beanHorario.setHorario(rs.getString("horario"));

                bean.setHorario(beanHorario);
                bean.setUniversidadDeEgreso(rs.getString("universidadDeEgreso"));
                bean.setFechaDeIngreso(rs.getString("fechaDeIngreso"));
                bean.setDireccion(rs.getString("direccion"));


            }
            rs.close();
            pstm.close();
            conexion.close();
        } catch (Exception e) {
            System.out.println("Error en método consultarPersonaPorId() del DaoPersona -> " + e.getMessage());
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

    public boolean actulizarPerfil(BeanPersona bean) {
        boolean res = false;
        System.out.println(bean.getIdPersona());
        try {
            conexion = MySQLConexion.getConnection();
            pstm = conexion.prepareStatement(SQL_MODIFICAR_PERFIL);
            pstm.setInt(1, bean.getIdPersona());
            pstm.setString(2, bean.getNombre());
            pstm.setString(3, bean.getPrimerApellido());
            pstm.setString(4, bean.getSegundoApellido());
            pstm.setString(5, bean.getCorreoPersonal());
            pstm.setString(6, bean.getNumeroCasa());
            pstm.setString(7, bean.getNumeroTelefonico());
            pstm.setString(8, bean.getDireccion());

            res = pstm.executeUpdate() == 1;
            pstm.close();
            conexion.close();

        } catch (Exception e) {
            System.out.println("Error en el método de modifcarPerfil " + e.getMessage());
            e.getCause();
        } finally {
            try {
                pstm.close();
                conexion.close();
            } catch (Exception e) {

            }
        }

        return res;
    }

    public void borrarCodigo(String codigo) {
        boolean res = false;
        try {
            conexion = MySQLConexion.getConnection();
            pstm = conexion.prepareStatement("call borrarCodigo(?)");
            pstm.setString(1, codigo);
            rs = pstm.executeQuery();
            res = true;
            rs.close();
            pstm.close();
            conexion.close();
        } catch (Exception e) {
            System.out.println("Error en el metodo consultar personas: " + e.getMessage());
            System.out.println(e.getCause());
            res = false;
        } finally {
            try {
                rs.close();
                pstm.close();
                conexion.close();
            } catch (Exception e) {

            }
        }
    }

    public boolean cambiarContra2(int id, String contra) {
        boolean res = false;
        try {
            conexion = MySQLConexion.getConnection();
            pstm = conexion.prepareStatement("call cambiarContra2(?,?);");
            pstm.setString(1,ActionPersona.Encriptar(contra));
            pstm.setInt(2, id);
            rs = pstm.executeQuery();
            res = true;
            cerrarConexiones();
        } catch (Exception e) {
            System.out.println("Error en el metodo cambiar contra: " + e.getMessage());
            System.out.println(e.getCause());
            res = false;
        } finally {
            try {
                cerrarConexiones();
            } catch (Exception e) {

            }
        }
        return res;
    }

    public boolean cambiarContra(String codigo,String contra) {
        boolean res=false;
        try {
            conexion = MySQLConexion.getConnection();
            pstm = conexion.prepareStatement("call cambiarContra(?,?);");
            pstm.setString(1, contra);
            pstm.setString(2, codigo);
            rs = pstm.executeQuery();
            res = true;
          cerrarConexiones();
        } catch (Exception e) {
            System.out.println("Error en el metodo cambiar contra: " + e.getMessage());
            System.out.println(e.getCause());
            res = false;
        } finally {
            try {
                cerrarConexiones();
            } catch (Exception e) {

            }
        }
        return res;
    }
    public boolean act(String bean){
        Gson g=new Gson();
        JsonObject object=new JsonParser().parse(bean).getAsJsonObject();
        int idHorairo=object.get("horario").getAsInt();
        System.out.println(idHorairo);
        System.out.println("nombre"+object.get("nombre").getAsString());
        String fecha=object.get("fechaDeIngreso").getAsString();
        System.out.println(fecha);
        try{
            System.out.println("1");
            conexion = MySQLConexion.getConnection();
            pstm=conexion.prepareStatement("update Persona set nombre=?,primerApellido=?,segundoApellido=?,fechaNacimiento=?" +
                    ",correoInstitucional=?,correoPersona=?,numeroTelefonico=?,numeroCasa=?,matricula=?,carreraDeEgreso=?,universidadDeEgreso=?,horarios_id=?,fechaDeIngreso=?,contrasenia=?,direccion=? where id=?");
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

            System.out.println("2");
            pstm.setInt(12,idHorairo);

            System.out.println("3");
            System.out.println(fecha);
            pstm.setString(13,fecha);

            System.out.println("4");
            pstm.setString(14,ActionPersona.Encriptar(object.get("contrasena").getAsString()));

            System.out.println("5");
            pstm.setString(15,object.get("direccion").getAsString());
            pstm.setInt(16,object.get("idPersona").getAsInt());
            pstm.execute();

            System.out.println("13");
            int id=object.get("idPersona").getAsInt();
            pstm=conexion.prepareStatement("delete from Persona_Rol where Persona_id=?");
            pstm.setInt(1,id);
            pstm.execute();
            pstm=conexion.prepareStatement("delete from becario where Persona_Rol_id=?");
            pstm.setInt(1,id);
            pstm.execute();
            List<Integer> ids=new ArrayList<>();
            System.out.println("14");
            JsonArray json=object.get("roles").getAsJsonArray();
            for (int i=0;i<json.size();i++){
                ids.add(json.get(i).getAsInt());
                pstm = conexion.prepareStatement("insert into Persona_Rol (Rol_id,Persona_id) values(?,?)");
                pstm.setInt(1,ids.get(i));
                pstm.setInt(2,id);
                pstm.execute();
            }
            System.out.println("15");
            if (Boolean.parseBoolean(object.get("estudiante").getAsString())){
                pstm = conexion.prepareStatement("insert into Estudiante (cuatrimestreDeIngreso,grupoActual,cuatrimestreActual,estado,Persona_id) values(?,?,?,1,?)");
                System.out.println("entra a estudiante");
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

        }catch (Exception e){
            System.out.println("Error en el metodo |ficar personas: "+e);
        }finally {
            try{
                if (rs!=null){
                    rs.close();
                }
                if (conexion!=null){
                    conexion.close();
                }
                if (pstm!=null){
                    pstm.close();
                }


            } catch (Exception e) {

            }
        }
        return true;
    }
    public boolean desemp(String bean){
        Gson g=new Gson();
        JsonObject object=new JsonParser().parse(bean).getAsJsonObject();
        try{
            String var="";
            conexion = MySQLConexion.getConnection();
            pstm=conexion.prepareStatement("update Persona set desempeño=?  where id=?");
            double des=object.get("desempenio").getAsDouble();
            if (des>=3.60  ){
                var="A+";
            }else if (des>=3.10 && des<=3.59 ){
                var="A";
            }else if (des>=2.60 && des<=3.09 ){
                var="B";
            }else if (des>=2.10 && des<=2.59 ){
                var="B-";
            }else if (des<=2.09 ){
                var="C";
            }



            pstm.setString(1,var);

            pstm.setInt(2,object.get("idPersona").getAsInt());
            pstm.execute();





        }catch (Exception e){
            System.out.println("Error en el desemp personas: "+e);
        }finally {
            try{
                if (rs!=null){
                    rs.close();
                }
                if (conexion!=null){
                    conexion.close();
                }
                if (pstm!=null){
                    pstm.close();
                }


            } catch (Exception e) {

            }
        }
        return true;
    }
    public String encriptar(String cadena) throws NoSuchAlgorithmException {
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
}

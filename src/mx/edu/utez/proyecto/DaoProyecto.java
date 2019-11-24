
package mx.edu.utez.proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import mx.edu.utez.conexion.MySQLConexion;
import mx.edu.utez.horario.BeanHorario;
import mx.edu.utez.modelo.Dao;
import mx.edu.utez.modelo.DaoInterfaz;
import mx.edu.utez.persona.BeanPersona;
import org.apache.struts2.components.Bean;

public class DaoProyecto extends Dao implements DaoInterfaz {
    public DaoProyecto() {
    }
    private Connection conexion;
    private PreparedStatement pstm;
    private ResultSet rs;
    public boolean registrar(Object bean) {





        return false;
    }

    public boolean eliminar(Object bean) {
        BeanProyecto beanP=(BeanProyecto) bean;
        boolean res=false;
        try{
            mySQLRepositorio("update Proyecto set estado=0 where idProyecto=?");
            preparedStatement.setInt(1,beanP.getIdProyecto());
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

    public boolean actualizar(Object bean) {


        return false;
    }

    public ArrayList getLista() {
        ArrayList lista = new ArrayList();
        ArrayList lista2 = new ArrayList();
        ArrayList ap = new ArrayList<BeanPersona>();
        ArrayList rd = new ArrayList<BeanPersona>();
        ArrayList rape = new ArrayList<BeanPersona>();
        BeanProyecto beanProyecto = new BeanProyecto();
        Connection con=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;
        int idp=0;
        try {
            con= MySQLConexion.getConnection();

            pstm=con.prepareStatement("SELECT * FROM Proyecto where estado=1");

            rs = pstm.executeQuery();

            while(rs.next()) {

                beanProyecto =new BeanProyecto();
                beanProyecto.setCorreoDelCliente(rs.getString("correoDelCliente"));
                beanProyecto.setCosto(rs.getString("costo"));
                beanProyecto.setDescripcion(rs.getString("descripcion"));
                beanProyecto.setDireccionCliente(rs.getString("direccionCliente"));
                beanProyecto.setEstado(rs.getInt("estado"));
                beanProyecto.setFechaFin(rs.getString("fechaFin"));
                beanProyecto.setFechaInicio(rs.getString("fechaInicio"));
                beanProyecto.setIdentificador(rs.getString("identificador"));
                beanProyecto.setNombre(rs.getString("nombre"));

                beanProyecto.setNombreDelCliente(rs.getString("nombreDelCliente"));
                beanProyecto.setNumTelefonico(rs.getString("numTelefonico"));
                beanProyecto.setIdProyecto(rs.getInt("idProyecto"));
                lista.add(beanProyecto);

            }
        } catch (Exception var7) {
            System.out.println(var7);
        } finally {

        }
        try {
            System.out.println("tamaño lista"+lista.size());
            for ( int i=0;i<lista.size();i++){
                pstm=con.prepareStatement("SELECT * FROM btzhjn0ppg33wdzyeiml.Proyecto inner join Persona_Proyecto on Persona_Proyecto.proyecto_id=Proyecto.idProyecto inner join Persona as p on p.id=Persona_Proyecto.Persona_id inner join RolProyecto on RolProyecto.idRol=Persona_Proyecto.proyecto_Rol_Persona where idProyecto=? and idRol =1");

                rd=new ArrayList();
                beanProyecto =new BeanProyecto();
                beanProyecto=(BeanProyecto)lista.get(i);
                pstm.setInt(1,beanProyecto.getIdProyecto());
                rs = pstm.executeQuery();
                while (rs.next()){
                    BeanPersona beanPersona=new BeanPersona();
                            beanPersona=new BeanPersona();
                            beanPersona.setCarreraDeEgreso(rs.getString("universidadDeEgreso"));
                            beanPersona.setContrasenia(rs.getString("contrasenia"));
                            beanPersona.setCorreoInstitucional(rs.getString("correoInstitucional"));
                            beanPersona.setCorreoPersonal(rs.getString("correoPersona"));
                            beanPersona.setDesempenio(rs.getString("desempeño"));
                            beanPersona.setFechaDeEgreso(rs.getString("fechaDeEgreso"));
                            beanPersona.setFechaDeIngreso(rs.getString("fechaDeIngreso"));
                            beanPersona.setFechaDeNacimiento(rs.getString("fechaNacimiento"));

                            beanPersona.setIdPersona(rs.getInt("p.id"));
                            beanPersona.setMatricula(rs.getString("matricula"));
                            beanPersona.setNombre(rs.getString("p.nombre"));
                            beanPersona.setNumeroCasa(rs.getString("numeroCasa"));
                            beanPersona.setNumeroTelefonico(rs.getString("numeroTelefonico"));
                            beanPersona.setPrimerApellido(rs.getString("primerApellido"));
                            beanPersona.setSegundoApellido(rs.getString("segundoApellido"));
                            beanPersona.setUniversidadDeEgreso(rs.getString("universidadDeEgreso"));
                            beanPersona.setEstado((rs.getInt("estado")==1)?true:false );
                            rd.add(beanPersona);




                }
                pstm=con.prepareStatement("SELECT * FROM btzhjn0ppg33wdzyeiml.Proyecto inner join Persona_Proyecto on Persona_Proyecto.proyecto_id=Proyecto.idProyecto inner join Persona as p on p.id=Persona_Proyecto.Persona_id inner join RolProyecto on RolProyecto.idRol=Persona_Proyecto.proyecto_Rol_Persona where idProyecto=? and idRol =2");

                ap=new ArrayList();
                beanProyecto =new BeanProyecto();
                beanProyecto=(BeanProyecto)lista.get(i);
                pstm.setInt(1,beanProyecto.getIdProyecto());
                rs = pstm.executeQuery();
                while (rs.next()){
                    BeanPersona beanPersona=new BeanPersona();
                    beanPersona=new BeanPersona();
                    beanPersona.setCarreraDeEgreso(rs.getString("universidadDeEgreso"));
                    beanPersona.setContrasenia(rs.getString("contrasenia"));
                    beanPersona.setCorreoInstitucional(rs.getString("correoInstitucional"));
                    beanPersona.setCorreoPersonal(rs.getString("correoPersona"));
                    beanPersona.setDesempenio(rs.getString("desempeño"));
                    beanPersona.setFechaDeEgreso(rs.getString("fechaDeEgreso"));
                    beanPersona.setFechaDeIngreso(rs.getString("fechaDeIngreso"));
                    beanPersona.setFechaDeNacimiento(rs.getString("fechaNacimiento"));

                    beanPersona.setIdPersona(rs.getInt("p.id"));
                    beanPersona.setMatricula(rs.getString("matricula"));
                    beanPersona.setNombre(rs.getString("p.nombre"));
                    beanPersona.setNumeroCasa(rs.getString("numeroCasa"));
                    beanPersona.setNumeroTelefonico(rs.getString("numeroTelefonico"));
                    beanPersona.setPrimerApellido(rs.getString("primerApellido"));
                    beanPersona.setSegundoApellido(rs.getString("segundoApellido"));
                    beanPersona.setUniversidadDeEgreso(rs.getString("universidadDeEgreso"));
                    beanPersona.setEstado((rs.getInt("estado")==1)?true:false );
                    ap.add(beanPersona);




                }

                pstm=con.prepareStatement("SELECT * FROM btzhjn0ppg33wdzyeiml.Proyecto inner join Persona_Proyecto on Persona_Proyecto.proyecto_id=Proyecto.idProyecto inner join Persona as p on p.id=Persona_Proyecto.Persona_id inner join RolProyecto on RolProyecto.idRol=Persona_Proyecto.proyecto_Rol_Persona where idProyecto=? and idRol =3");
                System.out.println("for"+i);
                rape=new ArrayList();
                beanProyecto =new BeanProyecto();
                beanProyecto=(BeanProyecto)lista.get(i);
                pstm.setInt(1,beanProyecto.getIdProyecto());
                rs = pstm.executeQuery();
                while (rs.next()){
                    BeanPersona beanPersona=new BeanPersona();
                    beanPersona=new BeanPersona();
                    beanPersona.setCarreraDeEgreso(rs.getString("universidadDeEgreso"));
                    beanPersona.setContrasenia(rs.getString("contrasenia"));
                    beanPersona.setCorreoInstitucional(rs.getString("correoInstitucional"));
                    beanPersona.setCorreoPersonal(rs.getString("correoPersona"));
                    beanPersona.setDesempenio(rs.getString("desempeño"));
                    beanPersona.setFechaDeEgreso(rs.getString("fechaDeEgreso"));
                    beanPersona.setFechaDeIngreso(rs.getString("fechaDeIngreso"));
                    beanPersona.setFechaDeNacimiento(rs.getString("fechaNacimiento"));

                    beanPersona.setIdPersona(rs.getInt("p.id"));
                    beanPersona.setMatricula(rs.getString("matricula"));
                    beanPersona.setNombre(rs.getString("p.nombre"));
                    beanPersona.setNumeroCasa(rs.getString("numeroCasa"));
                    beanPersona.setNumeroTelefonico(rs.getString("numeroTelefonico"));
                    beanPersona.setPrimerApellido(rs.getString("primerApellido"));
                    beanPersona.setSegundoApellido(rs.getString("segundoApellido"));
                    beanPersona.setUniversidadDeEgreso(rs.getString("universidadDeEgreso"));
                    beanPersona.setEstado((rs.getInt("estado")==1)?true:false );
                    rape.add(beanPersona);




                }
                beanProyecto.setRapes(rape);
                beanProyecto.setAps(ap);
                beanProyecto.setRds(rd);
                lista2.add(beanProyecto);
                }


        }catch (Exception e){
            System.out.println(e);
        }
        try {

            con.close();
            rs.close();
            pstm.close();
        }catch (Exception e){
            System.out.println();
        }
        return lista2;
    }

    public Object buquedaByID(int id) {
        ArrayList lista = new ArrayList();
        ArrayList lista2 = new ArrayList();
        ArrayList ap = new ArrayList<BeanPersona>();
        ArrayList rd = new ArrayList<BeanPersona>();
        ArrayList rape = new ArrayList<BeanPersona>();
        BeanProyecto beanProyecto = new BeanProyecto();
        Connection con=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;
        int idp=0;
        try {
            con= MySQLConexion.getConnection();

            pstm=con.prepareStatement("SELECT * FROM Proyecto where estado=1 and idProyecto=?");
            pstm.setInt(1,id);
            rs = pstm.executeQuery();

            while(rs.next()) {

                beanProyecto =new BeanProyecto();
                beanProyecto.setCorreoDelCliente(rs.getString("correoDelCliente"));
                beanProyecto.setCosto(rs.getString("costo"));
                beanProyecto.setDescripcion(rs.getString("descripcion"));
                beanProyecto.setDireccionCliente(rs.getString("direccionCliente"));
                beanProyecto.setEstado(rs.getInt("estado"));
                beanProyecto.setFechaFin(rs.getString("fechaFin"));
                beanProyecto.setFechaInicio(rs.getString("fechaInicio"));
                beanProyecto.setIdentificador(rs.getString("identificador"));
                beanProyecto.setNombre(rs.getString("nombre"));
                beanProyecto.setIdProyecto(rs.getInt("idProyecto"));
                beanProyecto.setNombreDelCliente(rs.getString("nombreDelCliente"));
                beanProyecto.setNumTelefonico(rs.getString("numTelefonico"));
                beanProyecto.setIdProyecto(rs.getInt("idProyecto"));
                lista.add(beanProyecto);

            }
        } catch (Exception var7) {
            System.out.println(var7);
        } finally {

        }
        try {
            System.out.println("tamaño lista"+lista.size());
            for ( int i=0;i<lista.size();i++){
                pstm=con.prepareStatement("SELECT * FROM btzhjn0ppg33wdzyeiml.Proyecto inner join Persona_Proyecto on Persona_Proyecto.proyecto_id=Proyecto.idProyecto inner join Persona as p on p.id=Persona_Proyecto.Persona_id inner join RolProyecto on RolProyecto.idRol=Persona_Proyecto.proyecto_Rol_Persona where idProyecto=? and idRol =1");

                rd=new ArrayList();
                beanProyecto =new BeanProyecto();
                beanProyecto=(BeanProyecto)lista.get(i);
                pstm.setInt(1,beanProyecto.getIdProyecto());
                rs = pstm.executeQuery();
                while (rs.next()){
                    BeanPersona beanPersona=new BeanPersona();
                    beanPersona=new BeanPersona();
                    beanPersona.setCarreraDeEgreso(rs.getString("universidadDeEgreso"));
                    beanPersona.setContrasenia(rs.getString("contrasenia"));
                    beanPersona.setCorreoInstitucional(rs.getString("correoInstitucional"));
                    beanPersona.setCorreoPersonal(rs.getString("correoPersona"));
                    beanPersona.setDesempenio(rs.getString("desempeño"));
                    beanPersona.setFechaDeEgreso(rs.getString("fechaDeEgreso"));
                    beanPersona.setFechaDeIngreso(rs.getString("fechaDeIngreso"));
                    beanPersona.setFechaDeNacimiento(rs.getString("fechaNacimiento"));

                    beanPersona.setIdPersona(rs.getInt("p.id"));
                    beanPersona.setMatricula(rs.getString("matricula"));
                    beanPersona.setNombre(rs.getString("p.nombre"));
                    beanPersona.setNumeroCasa(rs.getString("numeroCasa"));
                    beanPersona.setNumeroTelefonico(rs.getString("numeroTelefonico"));
                    beanPersona.setPrimerApellido(rs.getString("primerApellido"));
                    beanPersona.setSegundoApellido(rs.getString("segundoApellido"));
                    beanPersona.setUniversidadDeEgreso(rs.getString("universidadDeEgreso"));
                    beanPersona.setEstado((rs.getInt("estado")==1)?true:false );
                    rd.add(beanPersona);




                }
                pstm=con.prepareStatement("SELECT * FROM btzhjn0ppg33wdzyeiml.Proyecto inner join Persona_Proyecto on Persona_Proyecto.proyecto_id=Proyecto.idProyecto inner join Persona as p on p.id=Persona_Proyecto.Persona_id inner join RolProyecto on RolProyecto.idRol=Persona_Proyecto.proyecto_Rol_Persona where idProyecto=? and idRol =2");

                ap=new ArrayList();
                beanProyecto =new BeanProyecto();
                beanProyecto=(BeanProyecto)lista.get(i);
                pstm.setInt(1,beanProyecto.getIdProyecto());
                rs = pstm.executeQuery();
                while (rs.next()){
                    BeanPersona beanPersona=new BeanPersona();
                    beanPersona=new BeanPersona();
                    beanPersona.setCarreraDeEgreso(rs.getString("universidadDeEgreso"));
                    beanPersona.setContrasenia(rs.getString("contrasenia"));
                    beanPersona.setCorreoInstitucional(rs.getString("correoInstitucional"));
                    beanPersona.setCorreoPersonal(rs.getString("correoPersona"));
                    beanPersona.setDesempenio(rs.getString("desempeño"));
                    beanPersona.setFechaDeEgreso(rs.getString("fechaDeEgreso"));
                    beanPersona.setFechaDeIngreso(rs.getString("fechaDeIngreso"));
                    beanPersona.setFechaDeNacimiento(rs.getString("fechaNacimiento"));

                    beanPersona.setIdPersona(rs.getInt("p.id"));
                    beanPersona.setMatricula(rs.getString("matricula"));
                    beanPersona.setNombre(rs.getString("p.nombre"));
                    beanPersona.setNumeroCasa(rs.getString("numeroCasa"));
                    beanPersona.setNumeroTelefonico(rs.getString("numeroTelefonico"));
                    beanPersona.setPrimerApellido(rs.getString("primerApellido"));
                    beanPersona.setSegundoApellido(rs.getString("segundoApellido"));
                    beanPersona.setUniversidadDeEgreso(rs.getString("universidadDeEgreso"));
                    beanPersona.setEstado((rs.getInt("estado")==1)?true:false );
                    ap.add(beanPersona);




                }

                pstm=con.prepareStatement("SELECT * FROM btzhjn0ppg33wdzyeiml.Proyecto inner join Persona_Proyecto on Persona_Proyecto.proyecto_id=Proyecto.idProyecto inner join Persona as p on p.id=Persona_Proyecto.Persona_id inner join RolProyecto on RolProyecto.idRol=Persona_Proyecto.proyecto_Rol_Persona where idProyecto=? and idRol =3");
                System.out.println("for"+i);
                rape=new ArrayList();
                beanProyecto =new BeanProyecto();
                beanProyecto=(BeanProyecto)lista.get(i);
                pstm.setInt(1,beanProyecto.getIdProyecto());
                rs = pstm.executeQuery();
                while (rs.next()){
                    BeanPersona beanPersona=new BeanPersona();
                    beanPersona=new BeanPersona();
                    beanPersona.setCarreraDeEgreso(rs.getString("universidadDeEgreso"));
                    beanPersona.setContrasenia(rs.getString("contrasenia"));
                    beanPersona.setCorreoInstitucional(rs.getString("correoInstitucional"));
                    beanPersona.setCorreoPersonal(rs.getString("correoPersona"));
                    beanPersona.setDesempenio(rs.getString("desempeño"));
                    beanPersona.setFechaDeEgreso(rs.getString("fechaDeEgreso"));
                    beanPersona.setFechaDeIngreso(rs.getString("fechaDeIngreso"));
                    beanPersona.setFechaDeNacimiento(rs.getString("fechaNacimiento"));

                    beanPersona.setIdPersona(rs.getInt("p.id"));
                    beanPersona.setMatricula(rs.getString("matricula"));
                    beanPersona.setNombre(rs.getString("p.nombre"));
                    beanPersona.setNumeroCasa(rs.getString("numeroCasa"));
                    beanPersona.setNumeroTelefonico(rs.getString("numeroTelefonico"));
                    beanPersona.setPrimerApellido(rs.getString("primerApellido"));
                    beanPersona.setSegundoApellido(rs.getString("segundoApellido"));
                    beanPersona.setUniversidadDeEgreso(rs.getString("universidadDeEgreso"));
                    beanPersona.setEstado((rs.getInt("estado")==1)?true:false );
                    rape.add(beanPersona);




                }
                beanProyecto.setRapes(rape);
                beanProyecto.setAps(ap);
                beanProyecto.setRds(rd);
                lista2.add(beanProyecto);
            }


        }catch (Exception e){
            System.out.println(e);
        }
        try {

            con.close();
            rs.close();
            pstm.close();
        }catch (Exception e){
            System.out.println();
        }
        return lista2.get(0);
       
    }
    public ArrayList getRapes() {
        ArrayList lista=new ArrayList();
        try{
            mySQLRepositorio("SELECT * from Rol inner join Persona_Rol on Rol.idRol=Persona_Rol.Rol_id inner join Persona on Persona_Rol.Persona_id=Persona.id and estado=1  where descripcion='RAPE' ");
            resultSet=preparedStatement.executeQuery();
          while (resultSet.next()){
              BeanPersona bean=new BeanPersona();
              bean.setNombre(resultSet.getString("nombre"));
              bean.setPrimerApellido(resultSet.getString("primerApellido"));
              bean.setSegundoApellido(resultSet.getString("segundoApellido"));
              bean.setIdPersona(resultSet.getInt("Persona_id"));
            lista.add(bean);
          }


        }catch(Exception e){
            System.out.println(e);
        } finally {
            cerrarConexiones();
        }

        return lista;
    }

    public ArrayList getaps() {
        ArrayList lista=new ArrayList();
        try{
            mySQLRepositorio("SELECT * from Rol inner join Persona_Rol on Rol.idRol=Persona_Rol.Rol_id inner join Persona on Persona_Rol.Persona_id=Persona.id and estado=1 where descripcion='Analista Programador' or descripcion='Estadia'  group by Persona_id  ");
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                BeanPersona bean=new BeanPersona();
                bean.setNombre(resultSet.getString("nombre"));
                bean.setPrimerApellido(resultSet.getString("primerApellido"));
                bean.setSegundoApellido(resultSet.getString("segundoApellido"));
                bean.setIdPersona(resultSet.getInt("Persona_id"));
                lista.add(bean);
            }


        }catch(Exception e){
            System.out.println(e);
        } finally {
            cerrarConexiones();
        }

        return lista;
    }

    public ArrayList getrds() {
        ArrayList lista=new ArrayList();
        try{
            mySQLRepositorio("SELECT * from Rol inner join Persona_Rol on Rol.idRol=Persona_Rol.Rol_id inner join Persona on Persona_Rol.Persona_id=Persona.id and estado=1 where descripcion='Responsable de Desarrollo' ");
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                BeanPersona bean=new BeanPersona();
                bean.setNombre(resultSet.getString("nombre"));
                bean.setPrimerApellido(resultSet.getString("primerApellido"));
                bean.setSegundoApellido(resultSet.getString("segundoApellido"));
                bean.setIdPersona(resultSet.getInt("Persona_id"));
                lista.add(bean);
            }


        }catch(Exception e){
            System.out.println(e);
        } finally {
            cerrarConexiones();
        }

        return lista;
    }
    public boolean registrarPersona(String bean) {
        Gson g=new Gson();
        JsonObject object=new JsonParser().parse(bean).getAsJsonObject();


        try{
            conexion = MySQLConexion.getConnection();
            pstm = conexion.prepareStatement("insert into Proyecto (identificador,nombre,nombreDelCliente,correoDelcliente" +
                    ",numTelefonico,direccionCliente,fechaInicio,fechaFin,descripcion,costo," +
                    "estado) values(?,?,?,?,?,?,?,?,?,?,1)", Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1,object.get("identificador").getAsString());
            pstm.setString(2,object.get("nombreProyecto").getAsString());
            pstm.setString(3,object.get("nombreCliente").getAsString());
            pstm.setString(4,object.get("correoE").getAsString());
            pstm.setString(5,object.get("numeroTelefonico").getAsString());
            pstm.setString(6,object.get("direccionDelCliente").getAsString());
            pstm.setString(7,object.get("fechaInicio").getAsString());
            pstm.setString(8,object.get("fechaFin").getAsString());
            pstm.setString(9,object.get("descripcion").getAsString());
            pstm.setString(10,object.get("costo").getAsString());


            pstm.execute();
            rs = pstm.getGeneratedKeys();
            int id=0;
            while (rs.next()){
                id=rs.getInt(1);
            }
            JsonArray aps=object.get("aps").getAsJsonArray();
            JsonArray rds=object.get("rds").getAsJsonArray();
            JsonArray rapes=object.get("rapes").getAsJsonArray();
            List<Integer> ap=new ArrayList<>();
            List<Integer> rd=new ArrayList<>();
            List<Integer> rape=new ArrayList<>();
            for (int i=0;i<aps.size();i++){
                pstm=conexion.prepareStatement("insert into Persona_Proyecto (proyecto_id,Persona_id,proyecto_Rol_Persona) values (?,?,?)");
                pstm.setInt(1,id);
                pstm.setInt(2,aps.get(i).getAsInt());
                pstm.setInt(3,2);
                pstm.execute();
            }
            for (int i=0;i<rds.size();i++){
                pstm=conexion.prepareStatement("insert into Persona_Proyecto (proyecto_id,Persona_id,proyecto_Rol_Persona) values (?,?,?)");
                pstm.setInt(1,id);
                pstm.setInt(2,rds.get(i).getAsInt());
                pstm.setInt(3,1);
                pstm.execute();
            }
            for (int i=0;i<rapes.size();i++){
                pstm=conexion.prepareStatement("insert into Persona_Proyecto (proyecto_id,Persona_id,proyecto_Rol_Persona) values (?,?,?)");
                pstm.setInt(1,id);
                pstm.setInt(2,rapes.get(i).getAsInt());
                pstm.setInt(3,3);
                pstm.execute();
            }
        }catch (Exception e){
            System.out.println("Error en el metodo registrar proyectos: "+e);
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
    public boolean modificarPersona(String bean) {
        Gson g=new Gson();
        JsonObject object=new JsonParser().parse(bean).getAsJsonObject();


        try{
            conexion = MySQLConexion.getConnection();
            pstm = conexion.prepareStatement("update Proyecto set identificador=?, nombre=?,nombreDelCliente=?,correoDelCliente=?,numTelefonico=?,direccionCliente=?,fechaInicio=?,fechaFin=?," +
                    "descripcion=?,costo=? where idProyecto=?");

            pstm.setString(1,object.get("identificador").getAsString());
            pstm.setString(2,object.get("nombreProyecto").getAsString());
            pstm.setString(3,object.get("nombreCliente").getAsString());
            pstm.setString(4,object.get("correoE").getAsString());
            pstm.setString(5,object.get("numeroTelefonico").getAsString());
            pstm.setString(6,object.get("direccionDelCliente").getAsString());
            pstm.setString(7,object.get("fechaInicio").getAsString());
            pstm.setString(8,object.get("fechaFin").getAsString());
            pstm.setString(9,object.get("descripcion").getAsString());
            pstm.setString(10,object.get("costo").getAsString());
            pstm.setString(11,object.get("idProyecto").getAsString());


            pstm.execute();


            int id=0;
                id=object.get("idProyecto").getAsInt();
            JsonArray aps=object.get("aps").getAsJsonArray();
            JsonArray rds=object.get("rds").getAsJsonArray();
            JsonArray rapes=object.get("rapes").getAsJsonArray();
            pstm=conexion.prepareStatement("delete  from Persona_Proyecto where proyecto_id=? ");
            pstm.setString(1,object.get("idProyecto").getAsString());
            pstm.execute();
            List<Integer> ap=new ArrayList<>();
            List<Integer> rd=new ArrayList<>();
            List<Integer> rape=new ArrayList<>();
            for (int i=0;i<aps.size();i++){
                pstm=conexion.prepareStatement("insert into Persona_Proyecto (proyecto_id,Persona_id,proyecto_Rol_Persona) values (?,?,?)");
                pstm.setInt(1,id);
                pstm.setInt(2,aps.get(i).getAsInt());
                pstm.setInt(3,2);
                pstm.execute();
            }
            for (int i=0;i<rds.size();i++){
                pstm=conexion.prepareStatement("insert into Persona_Proyecto (proyecto_id,Persona_id,proyecto_Rol_Persona) values (?,?,?)");
                pstm.setInt(1,id);
                pstm.setInt(2,rds.get(i).getAsInt());
                pstm.setInt(3,1);
                pstm.execute();
            }
            for (int i=0;i<rapes.size();i++){
                pstm=conexion.prepareStatement("insert into Persona_Proyecto (proyecto_id,Persona_id,proyecto_Rol_Persona) values (?,?,?)");
                pstm.setInt(1,id);
                pstm.setInt(2,rapes.get(i).getAsInt());
                pstm.setInt(3,3);
                pstm.execute();
            }
        }catch (Exception e){
            System.out.println("Error en el metodo registrar proyectos: "+e);
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
}

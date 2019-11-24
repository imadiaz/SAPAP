
package mx.edu.utez.proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import mx.edu.utez.conexion.MySQLConexion;
import mx.edu.utez.horario.BeanHorario;
import mx.edu.utez.modelo.Dao;
import mx.edu.utez.modelo.DaoInterfaz;
import mx.edu.utez.persona.BeanPersona;
import org.apache.struts2.components.Bean;

public class DaoProyecto extends Dao implements DaoInterfaz {
    public DaoProyecto() {
    }

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
        return null;
       
    }
}

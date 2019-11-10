package mx.edu.utez.justificante;

import mx.edu.utez.modelo.Dao;
import mx.edu.utez.modelo.DaoInterfaz;

import java.sql.SQLException;
import java.util.ArrayList;

public class DaoJustificante extends Dao implements DaoInterfaz {


    @Override
    public boolean registrar(Object bean) {

        try {
            mySQLRepositorio("");
            mySQLRepositorio("INSERT INTO usuarios(nombre,apellido,sexo) values(?,?,?)");
//            preparedStatement.setString(bean.getNombre());
//            preparedStatement.setString(bean.getApellido());
//            preparedStatement.setString(bean.getSeco());
            estatus = preparedStatement.executeUpdate() == 1;
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e){
            System.out.println(e);
        }
        cerrarConexiones();
        return estatus;
    }

    @Override
    public boolean eliminar(Object bean) {
        try{
            mySQLRepositorio("DELETE FROM usuarios WHERE ID=?");
            //  preparedStatement.setInt(bean.getID);
            estatus = preparedStatement.executeUpdate() == 1;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        cerrarConexiones();
        return estatus;    }

    @Override
    public boolean actualizar(Object bean) {
        try{
            mySQLRepositorio("UPDATE usuarios SET nombre=?,apellido=?,sexo=?");
            // preparedStatement.setString(bean.getNombre());
            // preparedStatement.setString(bean.getApellido());
            // preparedStatement.setString(bean.getSeco());
            estatus = preparedStatement.executeUpdate() == 1;
            resultSet = preparedStatement.executeQuery();
        }catch(Exception e){
        }
        cerrarConexiones();
        return estatus;    }

    @Override
    public ArrayList getLista() {
        ArrayList lista = new ArrayList();
        BeanJustificante beanJustificante = new BeanJustificante();
        try{
            mySQLRepositorio("select j.fechaElaboracion, j.nombreRape, j.proyectoJustificante,j.motivoJustifica from \n" +
                    "Persona as p inner join Estudiante as e on e.Persona_id = p.id \n" +
                    "inner join Justificante as j on j.Estudiante_id = e.idEstudiante\n" +
                    "where idEstudiante = ? ;");
            preparedStatement.setInt(1, 1);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                beanJustificante.setFechaElaboracion(resultSet.getString("fechaElaboracion"));
                lista.add(beanJustificante);
            }

        }catch(Exception e){
        } finally {
            cerrarConexiones();
        }
        return lista;    }

    @Override
    public Object buquedaByID(int id) {
        return null;
    }
}

package mx.edu.utez.modelo;

import java.util.ArrayList;
import java.util.List;

public class EjemploDao extends Dao implements DaoInterfaz {
    @Override
    public boolean registrar(Object bean) {
        try{
            mySQLRepositorio("INSERT INTO usuarios(nombre,apellido,sexo) values(?,?,?)");
           // preparedStatement.setString(bean.getNombre());
           // preparedStatement.setString(bean.getApellido());
           // preparedStatement.setString(bean.getSeco());
            estatus = preparedStatement.executeUpdate() == 1;
            resultSet = preparedStatement.executeQuery();
        }catch(Exception e){
        }
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
        return estatus;
    }

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
return estatus;
    }

    @Override
    public ArrayList getLista() {
        ArrayList lista = new ArrayList();
        try{
            mySQLRepositorio("Select * from usuarios;");
            resultSet = preparedStatement.executeQuery();
//            while(resultSet.next()){
//                lista.add(
//                        new BeanUsuario(
//                                resultSet.getString("nombre"),
//                                resultSet.getString("apellido"),
//                                resultSet.getString("sexo")
//                        ));
//            }

        }catch(Exception e){
        }
        return lista;
    }

    @Override
    public Object buquedaByID(int id) {
        return null;
    }
}

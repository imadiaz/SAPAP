package mx.edu.utez.curso;

import mx.edu.utez.modelo.Dao;
import mx.edu.utez.modelo.DaoInterfaz;

import java.util.ArrayList;

public class DaoCurso extends Dao implements DaoInterfaz {
    @Override
    public boolean registrar(Object bean) {
        try{
            BeanCurso beanCurso = (BeanCurso) bean;
            System.out.println("el bean"+bean.toString());
            mySQLRepositorio("call sp_registraCurso(?,?,?,?,?,?);");
            preparedStatement.setString(1,beanCurso.getNombre());
            preparedStatement.setString(2,beanCurso.getFecha());
            preparedStatement.setString(3,beanCurso.getDescripcion());
            preparedStatement.setString(4,beanCurso.getEvidencia());
            preparedStatement.setInt(5,Integer.parseInt(beanCurso.getTipoCurso()));
            preparedStatement.setInt(6,beanCurso.getIdPersona());

            estatus = preparedStatement.executeUpdate() == 1;

        }catch(Exception e){
            System.out.println("al catch");
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
        }finally {
            cerrarConexiones();
        }
        return estatus;
    }

    @Override
    public boolean eliminar(Object bean) {
        return false;
    }

    @Override
    public boolean actualizar(Object bean) {
        return false;
    }

    @Override
    public ArrayList getLista() {
        ArrayList<BeanCurso> lista = new ArrayList<>();
        try{
            mySQLRepositorio("");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                lista.add(new BeanCurso(
                        resultSet.getInt("idCurso"),
                        resultSet.getString("nombre"),
                        resultSet.getString("fecha"),
                        resultSet.getString("descripcion"),
                        resultSet.getString("evidencia"),
                        resultSet.getString("tipoCurso")
                ));
            }
        }catch(Exception e){

        }finally {
            cerrarConexiones();
        }

        return lista;
    }
    public ArrayList getListaByID(int id) {
        ArrayList<BeanCurso> lista = new ArrayList<>();
        try{
            mySQLRepositorio("call sp_consultaCursos(?);");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                lista.add(new BeanCurso(
                        resultSet.getInt("idCurso"),
                        resultSet.getString("nombre"),
                        resultSet.getString("fecha"),
                        resultSet.getString("descripcion"),
                        resultSet.getString("tipoCurso"),
                        resultSet.getString("evidencia"),
                        resultSet.getInt("Persona_id")
                ));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            cerrarConexiones();
        }

        return lista;
    }

    @Override
    public Object buquedaByID(int id) {
        return null;
    }
}

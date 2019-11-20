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
            mySQLRepositorio("call sp_registraCurso(?,?,?,?,?,?,?);");
            preparedStatement.setString(1,beanCurso.getNombre());
            preparedStatement.setString(2,beanCurso.getFecha());
            preparedStatement.setString(3,beanCurso.getDescripcion());
            preparedStatement.setString(4,beanCurso.getEvidencia());
            preparedStatement.setString(5,beanCurso.getReferencia());
            preparedStatement.setInt(6,Integer.parseInt(beanCurso.getTipoCurso()));
            preparedStatement.setInt(7,beanCurso.getIdPersona());

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
        try{
            Integer id = (Integer) bean;
            mySQLRepositorio("call sp_eliminaCurso(?);");
            preparedStatement.setInt(1,id);
            estatus = preparedStatement.executeUpdate() ==1;
        }catch(Exception e){
            System.out.println("Error eliminar DAO" + e.getMessage());
        }finally {
            cerrarConexiones();
        }
        return estatus;
    }

    @Override
    public boolean actualizar(Object bean) {
        try{
                BeanCurso beanCurso = (BeanCurso) bean;
                mySQLRepositorio("call sp_modificaCurso(?,?,?,?,?);");
                preparedStatement.setString(1,beanCurso.getNombre());
                preparedStatement.setString(2,beanCurso.getFecha());
                preparedStatement.setString(3,beanCurso.getDescripcion());
                preparedStatement.setInt(4,Integer.parseInt(beanCurso.getTipoCurso()));
                preparedStatement.setInt(5,beanCurso.getIdCurso());

                estatus = preparedStatement.executeUpdate() == 1;

        }catch(Exception e){
            System.out.println("Error al actualizar DAO"+e.getMessage());
        }finally {
            cerrarConexiones();
        }

        return estatus;
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
                        resultSet.getString("tipoCurso"),
                        resultSet.getString("referencia")
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
            mySQLRepositorio("call sp_consultaCurso(?);");
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
                        resultSet.getInt("Persona_id"),
                        resultSet.getString("referencia")
                ));
            }
        }catch(Exception e){
            System.out.println("Error, DAO Curso" +e.getMessage());
            System.out.println(e.getMessage());
        }finally {
            cerrarConexiones();
        }
        return lista;
    }

    @Override
    public Object buquedaByID(int id) {
        try{
            mySQLRepositorio("SELECT * from Curso WHERE idCurso=?;");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new BeanCurso(
                       resultSet.getInt("idCurso"),
                       resultSet.getString("nombre"),
                        resultSet.getString("fecha"),
                        resultSet.getString("descripcion"),
                        String.valueOf(resultSet.getInt("Catalogo_id")),
                        resultSet.getString("evidencia")
                );
            }
        }catch(Exception e){
            System.out.println("Error busquedaByID DAOCurso"+e.getMessage());
        }finally {
            cerrarConexiones();
        }
        return null;
    }


    public boolean actualizarArchivo(String url,String referencia,String idCurso){
        try{
            mySQLRepositorio("call sp_modificaArchivo(?,?,?);");
            preparedStatement.setString(1,referencia);
            preparedStatement.setString(2,url);
            preparedStatement.setInt(3,Integer.parseInt(idCurso));
            estatus = preparedStatement.executeUpdate() == 1;
        }catch(Exception e){
            System.out.println("Error actualizar archivo DAO"+e.getMessage());
        }finally {
            cerrarConexiones();
        }
        return estatus;
    }

    public static void main(String[] args) {
        ArrayList<BeanCurso> lista = new DaoCurso().getListaByID(1);
        System.out.println(lista.size());
    }
}

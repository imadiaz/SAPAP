package mx.edu.utez.justificante;

import mx.edu.utez.modelo.Dao;
import mx.edu.utez.modelo.DaoInterfaz;
import mx.edu.utez.persona.BeanPersona;
import mx.edu.utez.proyecto.BeanProyecto;

import java.sql.SQLException;
import java.util.ArrayList;

public class DaoJustificante extends Dao implements DaoInterfaz {


    @Override
    public boolean registrar(Object bean) {
        try {
            mySQLRepositorio("");
            mySQLRepositorio("INSERT INTO justificante(Estudiante_id,fechaElaboracion,motivoJustifica, " +
                    "proyectoJustificante,rape_id,nombreRape) " +
                    "values(?,?,?,?,?,?)");
//            preparedStatement.setString(bean.get;
//            preparedStatement.setString(bean.getApellido());
//            preparedStatement.setString(bean.getSeco());
            estatus = preparedStatement.executeUpdate() == 1;
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
        cerrarConexiones();
        return estatus;
    }

    @Override
    public boolean eliminar(Object bean) {
        try {
            mySQLRepositorio("DELETE FROM Justificante WHERE idJustificante=?");
//              preparedStatement.setInt(bean.);
            estatus = preparedStatement.executeUpdate() == 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        cerrarConexiones();
        return estatus;
    }

    @Override
    public boolean actualizar(Object bean) {
        try {
            mySQLRepositorio("UPDATE usuarios SET nombre=?,apellido=?,sexo=?");
            // preparedStatement.setString(bean.getNombre());
            // preparedStatement.setString(bean.getApellido());
            // preparedStatement.setString(bean.getSeco());
            estatus = preparedStatement.executeUpdate() == 1;
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
        }
        cerrarConexiones();
        return estatus;
    }

    @Override
    public ArrayList getLista() {
        ArrayList lista = new ArrayList();
        BeanJustificante beanJustificante = null;
        try {
            mySQLRepositorio("select j.fechaElaboracion, j.nombreRape, j.proyectoJustificante,j.motivoJustifica from \n" +
                    "Persona as p inner join Estudiante as e on e.Persona_id = p.id \n" +
                    "inner join Justificante as j on j.Estudiante_id = e.idEstudiante\n" +
                    "where idEstudiante = ? ;");
            preparedStatement.setInt(1, 1);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                beanJustificante = new BeanJustificante();
                beanJustificante.setFechaElaboracion(resultSet.getString("fechaElaboracion"));
                lista.add(beanJustificante);
            }

        } catch (Exception e) {
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    @Override
    public Object buquedaByID(int id) {
        return null;
    }

    public ArrayList listaJustificantesPendientes(int id) {
        ArrayList lista = new ArrayList();
        BeanJustificante beanJustificante = null;
        BeanPersona beanPersona = null;
        BeanProyecto beanProyecto = null;
        try {
            mySQLRepositorio("select j.idJustificante, j.evidencia, j.referencia, j.fechaElaboracion, j.nombreRape, j.proyectoJustificante,j.motivoJustifica from \n" +
                    "                    Persona as p inner join Estudiante as e on e.Persona_id = p.id \n" +
                    "                    inner join Justificante as j on j.Estudiante_id = p.id\n" +
                    "                    where p.id = ? and estadoJustificante = 1 and estadoRAPE is null;");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                beanPersona = new BeanPersona();
                beanProyecto = new BeanProyecto();
                beanJustificante = new BeanJustificante();

                beanPersona.setNombre(resultSet.getString("nombreRape"));
                beanProyecto.setNombre(resultSet.getString("proyectoJustificante"));

                beanJustificante.setFechaElaboracion(resultSet.getString("fechaElaboracion"));
                beanJustificante.setJustifica(beanPersona);
                beanJustificante.setProyecto(beanProyecto);
                beanJustificante.setMotivoJustifica(resultSet.getString("motivoJustifica"));
                beanJustificante.setEvidencia(resultSet.getString("evidencia"));
                beanJustificante.setReferencia(resultSet.getString("referencia"));
                beanJustificante.setIdJustificante(resultSet.getInt("idJustificante"));
                lista.add(beanJustificante);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        cerrarConexiones();
        return lista;
    }

    public ArrayList listaJustificantesPendientesRAPE(int id) {
        ArrayList lista = new ArrayList();
        BeanJustificante beanJustificante = null;
        BeanPersona beanPersona = null;
        BeanProyecto beanProyecto = null;
        try {
            mySQLRepositorio("select j.idJustificante, j.fechaElaboracion, p.nombre, j.referencia, j.evidencia, " +
                    "p.primerApellido, p.SegundoApellido, j.motivoJustifica, p.id, j.nombreRape, j.proyectoJustificante \n" +
                    "from Persona as p inner join Estudiante as e on e.Persona_id = p.id\n" +
                    "inner join Justificante as j on j.Estudiante_id = p.id\n" +
                    "where estadoJustificante = 1 and rape_id = ? and estadoRAPE is null;");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                beanPersona = new BeanPersona();
                beanProyecto = new BeanProyecto();
                beanJustificante = new BeanJustificante();

                beanPersona.setNombre(resultSet.getString("nombre"));
                beanPersona.setPrimerApellido(resultSet.getString("primerApellido"));
                beanPersona.setSegundoApellido(resultSet.getString("SegundoApellido"));
                beanProyecto.setNombre(resultSet.getString("proyectoJustificante"));
                beanJustificante.setFechaElaboracion(resultSet.getString("fechaElaboracion"));

                beanJustificante.setEstudiante(beanPersona);
                beanJustificante.setProyecto(beanProyecto);
                beanJustificante.setMotivoJustifica(resultSet.getString("motivoJustifica"));
                beanJustificante.setEvidencia(resultSet.getString("evidencia"));
                beanJustificante.setReferencia(resultSet.getString("referencia"));
                beanJustificante.setIdJustificante(resultSet.getInt("idJustificante"));

                lista.add(beanJustificante);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        cerrarConexiones();
        return lista;
    }

    public ArrayList listaJustificantesPendientesCOD() {
        ArrayList lista = new ArrayList();
        BeanJustificante beanJustificante = null;
        BeanPersona beanPersona = null;
        BeanProyecto beanProyecto = null;
        try {
            mySQLRepositorio("select j.idJustificante, j.fechaElaboracion, p.nombre, j.referencia, j.evidencia, " +
                    "p.primerApellido, p.SegundoApellido, j.motivoJustifica, p.id, j.nombreRape, j.proyectoJustificante \n" +
                    "from Persona as p inner join Estudiante as e on e.Persona_id = p.id\n" +
                    "inner join Justificante as j on j.Estudiante_id = p.id\n" +
                    "where estadoJustificante = 1 and estadoRAPE =1 and estadoCCDS is null");
//            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                beanPersona = new BeanPersona();
                beanProyecto = new BeanProyecto();
                beanJustificante = new BeanJustificante();

                beanPersona.setNombre(resultSet.getString("nombre"));
                beanPersona.setPrimerApellido(resultSet.getString("primerApellido"));
                beanPersona.setSegundoApellido(resultSet.getString("SegundoApellido"));
                beanProyecto.setNombre(resultSet.getString("proyectoJustificante"));
                beanJustificante.setFechaElaboracion(resultSet.getString("fechaElaboracion"));

                beanJustificante.setEstudiante(beanPersona);
                beanJustificante.setProyecto(beanProyecto);
                beanJustificante.setMotivoJustifica(resultSet.getString("motivoJustifica"));
                beanJustificante.setEvidencia(resultSet.getString("evidencia"));
                beanJustificante.setReferencia(resultSet.getString("referencia"));
                beanJustificante.setIdJustificante(resultSet.getInt("idJustificante"));

                lista.add(beanJustificante);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        cerrarConexiones();
        return lista;
    }

    public ArrayList listaJustificantesPendientesRH() {
        ArrayList lista = new ArrayList();
        BeanJustificante beanJustificante = null;
        BeanPersona beanPersona = null;
        BeanProyecto beanProyecto = null;
        try {
            mySQLRepositorio("select j.idJustificante, j.fechaElaboracion, p.nombre, j.referencia, j.evidencia, " +
                    "p.primerApellido, p.SegundoApellido, j.motivoJustifica, p.id, j.nombreRape, j.proyectoJustificante \n" +
                    "from Persona as p inner join Estudiante as e on e.Persona_id = p.id\n" +
                    "inner join Justificante as j on j.Estudiante_id = p.id\n" +
                    "where estadoJustificante = 1 and estadoCCDS =1 and estadoARH is null");
//            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                beanPersona = new BeanPersona();
                beanProyecto = new BeanProyecto();
                beanJustificante = new BeanJustificante();

                beanPersona.setNombre(resultSet.getString("nombre"));
                beanPersona.setPrimerApellido(resultSet.getString("primerApellido"));
                beanPersona.setSegundoApellido(resultSet.getString("SegundoApellido"));
                beanProyecto.setNombre(resultSet.getString("proyectoJustificante"));
                beanJustificante.setFechaElaboracion(resultSet.getString("fechaElaboracion"));

                beanJustificante.setEstudiante(beanPersona);
                beanJustificante.setProyecto(beanProyecto);
                beanJustificante.setMotivoJustifica(resultSet.getString("motivoJustifica"));
                beanJustificante.setEvidencia(resultSet.getString("evidencia"));
                beanJustificante.setReferencia(resultSet.getString("referencia"));
                beanJustificante.setIdJustificante(resultSet.getInt("idJustificante"));

                lista.add(beanJustificante);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        cerrarConexiones();
        return lista;
    }

    public ArrayList historialJustificantes(int id) {
        ArrayList lista = new ArrayList();
        BeanJustificante beanJustificante = null;
        BeanPersona beanPersona = null;
        BeanProyecto beanProyecto = null;
        try {
            mySQLRepositorio("select j.idJustificante, j.motivoRechazo, j.evidencia, j.referencia, j.fechaElaboracion, j.nombreRape, j.proyectoJustificante,j.motivoJustifica from \n" +
                    "Persona as p inner join Estudiante as e on e.Persona_id = p.id \n" +
                    "inner join Justificante as j on j.Estudiante_id = p.id\n" +
                    "where p.id = ? and estadoJustificante = 0");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                beanPersona = new BeanPersona();
                beanProyecto = new BeanProyecto();
                beanJustificante = new BeanJustificante();

                beanPersona.setNombre(resultSet.getString("nombreRape"));
                beanProyecto.setNombre(resultSet.getString("proyectoJustificante"));

                beanJustificante.setFechaElaboracion(resultSet.getString("fechaElaboracion"));
                beanJustificante.setJustifica(beanPersona);
                beanJustificante.setProyecto(beanProyecto);
                beanJustificante.setMotivoJustifica(resultSet.getString("motivoJustifica"));
                beanJustificante.setMotivoRechazo(resultSet.getString("motivoRechazo"));
                beanJustificante.setEvidencia(resultSet.getString("evidencia"));
                beanJustificante.setReferencia(resultSet.getString("referencia"));
                beanJustificante.setIdJustificante(resultSet.getInt("idJustificante"));
                lista.add(beanJustificante);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        cerrarConexiones();
        return lista;
    }

    public ArrayList historialJustificantesRH() {
        ArrayList lista = new ArrayList();
        BeanJustificante beanJustificante = null;
        BeanPersona beanPersona = null;
        BeanProyecto beanProyecto = null;
        try {
            mySQLRepositorio("select j.idJustificante, j.motivoRechazo, j.evidencia, j.referencia, j.fechaElaboracion, p.nombre, p.primerApellido, p.SegundoApellido, j.proyectoJustificante,j.motivoJustifica from \n" +
                    "                    Persona as p inner join Estudiante as e on e.Persona_id = p.id \n" +
                    "                    inner join Justificante as j on j.Estudiante_id = p.id\n" +
                    "                    where estadoARH is not null;");
//            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                beanPersona = new BeanPersona();
                beanProyecto = new BeanProyecto();
                beanJustificante = new BeanJustificante();

                beanPersona.setNombre(resultSet.getString("nombre"));
                beanPersona.setPrimerApellido(resultSet.getString("primerApellido"));
                beanPersona.setSegundoApellido(resultSet.getString("SegundoApellido"));
                beanProyecto.setNombre(resultSet.getString("proyectoJustificante"));

                beanJustificante.setFechaElaboracion(resultSet.getString("fechaElaboracion"));
                beanJustificante.setEstudiante(beanPersona);
                beanJustificante.setProyecto(beanProyecto);
                beanJustificante.setMotivoJustifica(resultSet.getString("motivoJustifica"));
                beanJustificante.setMotivoRechazo(resultSet.getString("motivoRechazo"));
                beanJustificante.setEvidencia(resultSet.getString("evidencia"));
                beanJustificante.setReferencia(resultSet.getString("referencia"));
                beanJustificante.setIdJustificante(resultSet.getInt("idJustificante"));
                lista.add(beanJustificante);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        cerrarConexiones();
        return lista;
    }


    public ArrayList historialJustificantesCOD() {
        ArrayList lista = new ArrayList();
        BeanJustificante beanJustificante = null;
        BeanPersona beanPersona = null;
        BeanProyecto beanProyecto = null;
        try {
            mySQLRepositorio("select j.idJustificante, j.motivoRechazo, j.evidencia, j.referencia, j.fechaElaboracion, p.nombre, p.primerApellido, p.SegundoApellido, j.proyectoJustificante,j.motivoJustifica from \n" +
                    "                    Persona as p inner join Estudiante as e on e.Persona_id = p.id \n" +
                    "                    inner join Justificante as j on j.Estudiante_id = p.id\n" +
                    "                    where estadoCCDS is not null and motivoRechazo is not null;");
//            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                beanPersona = new BeanPersona();
                beanProyecto = new BeanProyecto();
                beanJustificante = new BeanJustificante();

                beanPersona.setNombre(resultSet.getString("nombre"));
                beanPersona.setPrimerApellido(resultSet.getString("primerApellido"));
                beanPersona.setSegundoApellido(resultSet.getString("SegundoApellido"));
                beanProyecto.setNombre(resultSet.getString("proyectoJustificante"));

                beanJustificante.setFechaElaboracion(resultSet.getString("fechaElaboracion"));
                beanJustificante.setEstudiante(beanPersona);
                beanJustificante.setProyecto(beanProyecto);
                beanJustificante.setMotivoJustifica(resultSet.getString("motivoJustifica"));
                beanJustificante.setMotivoRechazo(resultSet.getString("motivoRechazo"));
                beanJustificante.setEvidencia(resultSet.getString("evidencia"));
                beanJustificante.setReferencia(resultSet.getString("referencia"));
                beanJustificante.setIdJustificante(resultSet.getInt("idJustificante"));
                lista.add(beanJustificante);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        cerrarConexiones();
        return lista;
    }

    public ArrayList historialJustificantesRAPE(int id) {
        ArrayList lista = new ArrayList();
        BeanJustificante beanJustificante = null;
        BeanPersona beanPersona = null;
        BeanProyecto beanProyecto = null;
        try {
            mySQLRepositorio("select j.idJustificante, j.motivoRechazo, j.evidencia, j.referencia, j.fechaElaboracion, p.nombre, p.primerApellido, p.SegundoApellido, j.proyectoJustificante,j.motivoJustifica from \n" +
                    "                    Persona as p inner join Estudiante as e on e.Persona_id = p.id \n" +
                    "                    inner join Justificante as j on j.Estudiante_id = p.id\n" +
                    "                    where rape_id = ? and estadoJustificante = 0 and estadoRAPE is not null;");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                beanPersona = new BeanPersona();
                beanProyecto = new BeanProyecto();
                beanJustificante = new BeanJustificante();

                beanPersona.setNombre(resultSet.getString("nombre"));
                beanPersona.setPrimerApellido(resultSet.getString("primerApellido"));
                beanPersona.setSegundoApellido(resultSet.getString("SegundoApellido"));
                beanProyecto.setNombre(resultSet.getString("proyectoJustificante"));

                beanJustificante.setFechaElaboracion(resultSet.getString("fechaElaboracion"));
                beanJustificante.setEstudiante(beanPersona);
                beanJustificante.setProyecto(beanProyecto);
                beanJustificante.setMotivoJustifica(resultSet.getString("motivoJustifica"));
                beanJustificante.setMotivoRechazo(resultSet.getString("motivoRechazo"));
                beanJustificante.setEvidencia(resultSet.getString("evidencia"));
                beanJustificante.setReferencia(resultSet.getString("referencia"));
                beanJustificante.setIdJustificante(resultSet.getInt("idJustificante"));
                lista.add(beanJustificante);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        cerrarConexiones();
        return lista;
    }

    public boolean eliminarJustificante(BeanJustificante bean) {
        try {
            mySQLRepositorio("Update Justificante set estadoJustificante = 0 WHERE idJustificante=?");
            preparedStatement.setInt(1,bean.getIdJustificante());
            estatus = preparedStatement.executeUpdate() == 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        cerrarConexiones();
        return estatus;
    }

    public ArrayList consultarProyectoJustificante(int id) {
        ArrayList lista = new ArrayList();
        BeanProyecto beanProyecto = null;
        try {
            mySQLRepositorio("select pr.nombre, pr.idProyecto from Proyecto as pr INNER JOIN Persona_Proyecto as pp on pr.idProyecto = pp.proyecto_id\n" +
                    "inner join Persona as p on p.id = pp.Persona_id where p.id = ? and pr.estado = 1;");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                beanProyecto = new BeanProyecto();

                beanProyecto.setNombre(resultSet.getString("nombre"));
                beanProyecto.setIdProyecto(resultSet.getInt("idProyecto"));
                lista.add(beanProyecto);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        cerrarConexiones();
        return lista;
    }

    public boolean registrarJustificante(BeanJustificante bean) {

        try {
            mySQLRepositorio("INSERT INTO Justificante(Estudiante_id,fechaElaboracion,motivoJustifica, " +
                    "proyectoJustificante,rape_id,nombreRape, referencia, evidencia) " +
                    "values(?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1,bean.getEstudiante().getIdPersona());
            preparedStatement.setString(2,bean.getFechaElaboracion());
            preparedStatement.setString(3,bean.getMotivoJustifica());
            preparedStatement.setString(4,bean.getProyecto().getNombre());
            preparedStatement.setInt(5,bean.getJustifica().getIdPersona());
            preparedStatement.setString(6,bean.getJustifica().getNombre());
            preparedStatement.setString(7,bean.getReferencia());
            preparedStatement.setString(8,bean.getEvidencia());
            estatus = preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e);
        }
        cerrarConexiones();
        return estatus;
    }

    public BeanJustificante consultarDatosJustificante(int id) {
        ArrayList lista = new ArrayList();
        BeanJustificante beanJustificante = null;
        BeanPersona beanPersona = null;
        BeanProyecto beanProyecto = null;
        try {
            mySQLRepositorio("select j.idJustificante, j.motivoJustifica, p.id, j.nombreRape, j.proyectoJustificante from Persona as p \n" +
                    "inner join Justificante as j on p.id = j.rape_id\n" +
                    "where j.idJustificante = ?;");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                beanJustificante = new BeanJustificante();
                beanPersona = new BeanPersona();
                beanProyecto = new BeanProyecto();

                beanPersona.setIdPersona(resultSet.getInt("id"));
                beanPersona.setNombre(resultSet.getString("nombreRape"));
                beanProyecto.setNombre(resultSet.getString("proyectoJustificante"));


                beanJustificante.setIdJustificante(resultSet.getInt("idJustificante"));
                beanJustificante.setMotivoJustifica(resultSet.getString("motivoJustifica"));

                beanJustificante.setJustifica(beanPersona);
                beanJustificante.setProyecto(beanProyecto);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        cerrarConexiones();
        return beanJustificante;
    }

    public boolean modificarJustificante(BeanJustificante bean) {
        try {
            mySQLRepositorio("update Justificante set motivoJustifica = ?, proyectoJustificante = ?, rape_id = ?,\n" +
                    "nombreRape = ? where idJustificante = ?;");
            preparedStatement.setString(1,bean.getMotivoJustifica());
            preparedStatement.setString(2,bean.getProyecto().getNombre());
            preparedStatement.setInt(3,bean.getJustifica().getIdPersona());
            preparedStatement.setString(4,bean.getJustifica().getNombre());
            preparedStatement.setInt(5,bean.getIdJustificante());
            estatus = preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e);
        }
        cerrarConexiones();
        return estatus;
    }

    public boolean actualizarArchivoJustificante(BeanJustificante bean) {
        try {
            mySQLRepositorio("update Justificante set referencia = ?,\n" +
                    "evidencia = ? where idJustificante = ?;");
            preparedStatement.setString(1,bean.getReferencia());
            preparedStatement.setString(2,bean.getEvidencia());
            preparedStatement.setInt(3,bean.getIdJustificante());
            estatus = preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e);
        }
        cerrarConexiones();
        return estatus;
    }

    public String getContrasena(int id) {
        String contra = null;
        try {
            mySQLRepositorio("select contrasenia from Persona where id = ?; ");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                contra = resultSet.getString("contrasenia");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        cerrarConexiones();
        return contra;
    }

    public boolean aprobarJustificante(BeanJustificante bean) {
        try {
            if(bean.getEstadoRAPE() == 1){
                mySQLRepositorio("update Justificante set estadoRAPE = ? WHERE idJustificante=?");
                preparedStatement.setInt(1,bean.getEstadoRAPE());
                preparedStatement.setInt(2,bean.getIdJustificante());
                estatus = preparedStatement.executeUpdate() == 1;
            } else {
                mySQLRepositorio("update Justificante set motivoRechazo=?, estadoRAPE = ?, estadoJustificante = 0 WHERE idJustificante=?");
                preparedStatement.setString(1,bean.getMotivoRechazo());
                preparedStatement.setInt(2,bean.getEstadoRAPE());
                preparedStatement.setInt(3,bean.getIdJustificante());
                estatus = preparedStatement.executeUpdate() == 1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        cerrarConexiones();
        return estatus;
    }

    public boolean aprobarJustificanteCOD(BeanJustificante bean) {
        try {
            if(bean.getEstadoCCDS() == 1){
                mySQLRepositorio("update Justificante set estadoCCDS = ? WHERE idJustificante=?");
                preparedStatement.setInt(1,bean.getEstadoCCDS());
                preparedStatement.setInt(2,bean.getIdJustificante());
                estatus = preparedStatement.executeUpdate() == 1;
            } else {
                mySQLRepositorio("update Justificante set motivoRechazo=?, estadoCCDS = ?, estadoJustificante = 0 WHERE idJustificante=?");
                preparedStatement.setString(1,bean.getMotivoRechazo());
                preparedStatement.setInt(2,bean.getEstadoCCDS());
                preparedStatement.setInt(3,bean.getIdJustificante());
                estatus = preparedStatement.executeUpdate() == 1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        cerrarConexiones();
        return estatus;
    }

    public boolean aprobarJustificanteRH(BeanJustificante bean) {
        try {
            if(bean.getEstadoARH() == 1){
                mySQLRepositorio("update Justificante set motivoRechazo=?, estadoARH = ?, estadoJustificante = 0 WHERE idJustificante=?");
                preparedStatement.setString(1, "El justificante fue aprobado");
                preparedStatement.setInt(2,bean.getEstadoARH());
                preparedStatement.setInt(3,bean.getIdJustificante());
                estatus = preparedStatement.executeUpdate() == 1;
            } else {
                mySQLRepositorio("update Justificante set motivoRechazo=?, estadoARH = ?, estadoJustificante = 0 WHERE idJustificante=?");
                preparedStatement.setString(1,bean.getMotivoRechazo());
                preparedStatement.setInt(2,bean.getEstadoARH());
                preparedStatement.setInt(3,bean.getIdJustificante());
                estatus = preparedStatement.executeUpdate() == 1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        cerrarConexiones();
        return estatus;
    }


    //SHAME

    public ArrayList consultarRapeJustificanteModificar(int id) {
        ArrayList lista = new ArrayList();
        BeanPersona beanPersona = null;
        try {
            mySQLRepositorio("select p.nombre, p.primerApellido, p.segundoApellido, p.id from Persona as p inner join Persona_Rol as pr \n" +
                    "                    on pr.Persona_id = p.id inner join Rol as r on r.idRol = pr.Rol_id \n" +
                    "                    where r.descripcion = 'RAPE' and p.id not in (select per.id from Persona\n" +
                    "                    as per WHERE per.id= ?);");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                beanPersona = new BeanPersona();

                beanPersona.setNombre(resultSet.getString("nombre"));
                beanPersona.setPrimerApellido(resultSet.getString("primerApellido"));
                beanPersona.setSegundoApellido(resultSet.getString("segundoApellido"));
                beanPersona.setIdPersona(resultSet.getInt("id"));

                lista.add(beanPersona);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        cerrarConexiones();
        return lista;
    }

    public ArrayList consultarProyectoJustificanteModificar(int idPersona, String nombreProyecto) {
        ArrayList lista = new ArrayList();
        BeanProyecto beanProyecto = null;
        try {
            mySQLRepositorio("select pr.nombre, pr.idProyecto from Proyecto as pr INNER JOIN Persona_Proyecto as pp on pr.idProyecto = pp.proyecto_id\n" +
                    "                    inner join Persona as p on p.id = pp.Persona_id where p.id = ? and pr.nombre not in \n" +
                    "                    (select pro.nombre from Proyecto as pro where pro.nombre = ?);");
            preparedStatement.setInt(1, idPersona);
            preparedStatement.setString(2, nombreProyecto);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                beanProyecto = new BeanProyecto();

                beanProyecto.setNombre(resultSet.getString("nombre"));
                beanProyecto.setIdProyecto(resultSet.getInt("idProyecto"));
                lista.add(beanProyecto);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        cerrarConexiones();
        return lista;
    }

    public int consultarIDProyecto(String nombreProyecto) {
        int id = 0;
        try {
            mySQLRepositorio("select idProyecto from Proyecto where nombre = ?;");
            preparedStatement.setString(1, nombreProyecto);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                id =resultSet.getInt("idProyecto");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        cerrarConexiones();
        return id;
    }

    public ArrayList consultarSelectRapeJustificante(int id) {
        ArrayList lista = new ArrayList();
        BeanPersona beanPersona = null;
        try {
            mySQLRepositorio("select  p.nombre, p.primerApellido, p.segundoApellido, p.id, pp.proyecto_id from Persona as p inner join Persona_Proyecto\n" +
                    "as pp on p.id = pp.Persona_id inner join Persona_Rol as pr on pr.Persona_id = p.id inner join Rol as r on\n" +
                    "r.idRol = pr.Rol_id inner join Proyecto as pro on pp.proyecto_id = pro.idProyecto\n" +
                    "where proyecto_Rol_Persona = 3 and pro.estado = 1 and pp.proyecto_id = ? group by pp.proyecto_id;");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                beanPersona = new BeanPersona();

                beanPersona.setNombre(resultSet.getString("nombre"));
                beanPersona.setPrimerApellido(resultSet.getString("primerApellido"));
                beanPersona.setSegundoApellido(resultSet.getString("segundoApellido"));
                beanPersona.setIdPersona(resultSet.getInt("id"));

                lista.add(beanPersona);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        cerrarConexiones();
        return lista;
    }

//    public ArrayList consultarRapeJustificante() {
//        ArrayList lista = new ArrayList();
//        BeanPersona beanPersona = null;
//        try {
//            mySQLRepositorio("select p.nombre, p.primerApellido, p.segundoApellido, p.id from Persona as p inner join Persona_Rol as pr \n" +
//                    "on pr.Persona_id = p.id inner join Rol as r on r.idRol = pr.Rol_id \n" +
//                    "where r.descripcion = 'RAPE' and p.estado = 1");
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                beanPersona = new BeanPersona();
//
//                beanPersona.setNombre(resultSet.getString("nombre"));
//                beanPersona.setPrimerApellido(resultSet.getString("primerApellido"));
//                beanPersona.setSegundoApellido(resultSet.getString("segundoApellido"));
//                beanPersona.setIdPersona(resultSet.getInt("id"));
//
//                lista.add(beanPersona);
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        cerrarConexiones();
//        return lista;
//    }
}

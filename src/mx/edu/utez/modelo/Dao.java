package mx.edu.utez.modelo;

import mx.edu.utez.conexion.MySQLConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {
    protected Connection connection;
    protected ResultSet resultSet;
    protected PreparedStatement preparedStatement;
    protected boolean estatus;

    protected  Dao(){
        this.estatus=false;
    }
protected void mySQLRepositorio(String sentenciaSQL){
    try {
        this.connection = MySQLConexion.getConnection();
        this.preparedStatement = this.connection.prepareStatement(sentenciaSQL);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

protected void cerrarConexiones(){
    try {
        if (this.connection != null) {
            this.connection.close();
        }
        if (this.resultSet != null) {
            this.resultSet.close();
        }
        if (this.preparedStatement != null) {
            this.preparedStatement.close();
        }
    } catch (SQLException e) {
        System.err.println("Error al cerrar conexiones: " + e.getMessage());
    }

}
}

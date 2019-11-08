package mx.edu.utez.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MySQLConexion {

    public static Connection getConnection() throws SQLException {
        ResourceBundle resourceBundle=null;
        try {
            resourceBundle = ResourceBundle.getBundle("databaseParameters");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        final String DB_NAME = resourceBundle.getString("MySQLDataBase");
        final String USER = resourceBundle.getString("MySQLUser");
        final String PASS = resourceBundle.getString("MySQLPass");
        final String IP_ADD = resourceBundle.getString("MySQLIPAdd");
        final String PORT = resourceBundle.getString("MySQLPort");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
         return DriverManager.getConnection("jdbc:mysql://" + IP_ADD + "/" + DB_NAME + "?user=" + USER + "&password=" + PASS + "&useSSL=false");

    }

    public static void main(String[] args) {
        Connection c = null;
        try{
                c=MySQLConexion.getConnection();
        }catch (Exception e){
            System.out.println(e);
        }
        if(c!=null){
            System.out.println("succcess");
            System.out.println(c);
        }else {
            System.out.println("error");
        }
    }

}

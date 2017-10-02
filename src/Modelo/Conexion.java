/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Control.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author cesar
 */
public class Conexion {
///parametros de configuracion de usuario

    private Connection conexion;
    static String url = "jdbc:oracle:thin:@localhost:1521/XE"; //Descargar ojdbc6.jar e incluirlo en la libreria
    static String user = "system";
    static String password = "root";
    private boolean exito;
    private Control gestor;

    private ArrayList<String> resultados = new ArrayList<String>();

    /*Metodos*/
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        Conexion.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Conexion.password = password;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void conectar() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            exito = true;
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            conexion = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado");
            exito = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            System.out.println(e.getMessage());
            exito = false;
        }
    }

    // se obtienen los segmentos de la base de datos
    public ArrayList<Log> getlogs() throws SQLException {
        String group,mem,stat;
        Statement stm = null;
        ArrayList<Log> logs= new ArrayList<>();
        try {
            stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery("select * from v$log");

            getColumnNames(rs);
            while (rs.next()) {
                   group=rs.getString("GROUP#");
                   mem= rs.getString("MEMBERS");
                   stat=rs.getString("STATUS");
                   logs.add(new Log(group,mem,stat));
                

            }
            stm.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (stm != null) {
                stm.close();
            }
        }

       return logs;
    }
 public ArrayList<LogLocal> getruts(String group) throws SQLException {
        String mem,stat;
        Statement stm = null;
        ArrayList<LogLocal> logs= new ArrayList<>();
        try {
            stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery("select TYPE,MEMBER from v$logfile where group#='"+group+"'");

            getColumnNames(rs);
            while (rs.next()) {
                   
                   mem= rs.getString("MEMBER");
                   stat=rs.getString("TYPE");
                   logs.add(new LogLocal(stat,mem));
                

            }
            stm.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (stm != null) {
                stm.close();
            }
        }

       return logs;
    }
  
    
    /*Devuelve columna*/
    public static void getColumnNames(ResultSet rs) throws SQLException {
        if (rs == null) {
            return;
        }
        // get result set meta data
        ResultSetMetaData rsMetaData = rs.getMetaData();
        int numberOfColumns = rsMetaData.getColumnCount();

        // get the column names; column indexes start from 1
        for (int i = 1; i < numberOfColumns + 1; i++) {
            String columnName = rsMetaData.getColumnName(i);
            // Get the name of the column's table name
            String tableName = rsMetaData.getTableName(i);

        }
    }

   
}

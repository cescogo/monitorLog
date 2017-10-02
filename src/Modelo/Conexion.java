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
        String th,gr,mi,tam,est,arc,tip,rdf,seq,fir,sec;
        Statement stm = null;
        ArrayList<LogLocal> logs= new ArrayList<>();
        try {
            stm = conexion.createStatement();
            //ResultSet rs = stm.executeQuery("select TYPE,MEMBER from v$logfile where group#='"+group+"'");
            ResultSet rs = stm.executeQuery("SELECT l.thread# as Thread,\n" +
"       lf.group# as Grupo,\n" +
"       lf.member as Miembro,\n" +
"       TRUNC(l.bytes/1024/1024) AS Tam_MB,\n" +
"       l.status as Estado,\n" +
"       l.archived as Archive,\n" +
"       lf.type as Tipo,\n" +
"       lf.is_recovery_dest_file AS RDF,\n" +
"       l.sequence# as Sequencia,\n" +
"       l.first_change# as First,\n" +
"       l.next_change# as Second   \n" +
"FROM   v$logfile lf\n" +
"       JOIN v$log l ON l.group# = lf.group# where lf.group#='"+group+"'");
            getColumnNames(rs);
            while (rs.next()) {
                   
                  
                    th= rs.getString("Thread");
                    gr= rs.getString("Grupo");
                    mi= rs.getString("Miembro");
                    tam= rs.getString("Tam_MB");
                    est= rs.getString("Estado");
                    arc= rs.getString("Archive");
                    tip= rs.getString("Tipo");
                    rdf= rs.getString("RDF");
                    seq= rs.getString("Sequencia");
                    fir= rs.getString("First");
                    sec= rs.getString("Second");
                   
                   
                   logs.add(new LogLocal(th,gr,mi,tam,est,arc,tip,rdf,seq,fir,sec));
                

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
  
 public float getaverage(int cant) throws SQLException {
        float mem=0;
                int stat=cant;
        Statement stm = null;
        
        try {
            stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery("select \n" +
"   b.recid,\n" +
"   to_char(b.first_time,' hh24:mi:ss') start_time, \n" +
"   to_char(b.first_time,' dd-mon-yy ') start_Date,\n" +
"   a.recid,\n" +
"   to_char(a.first_time,'hh24:mi:ss') end_time,\n" +
"   to_char(a.first_time,' dd-mon-yy ') start_Date,\n" +
"   round(((a.first_time-b.first_time)*25)*60,2) minutes\n" +
"from\n" +
" v$log_history a,\n" +
"   v$log_history b\n" +
"   where \n" +
"   a.recid = b.recid+1\n" +
"order by \n" +
"   a.SEQUENCE# desc");

            getColumnNames(rs);
            while (stat!=0) {
                   rs.next();
                   mem+= rs.getFloat("MINUTES");
                   stat--;
             }
            stm.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (stm != null) {
                stm.close();
            }
        }

       return (float)mem/(float)cant;
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

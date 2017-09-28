package Control;

import Modelo.*;
import Vista.ventini;
import java.sql.SQLException;
import java.util.ArrayList;



/**
 *
 * @author cesar
 */
public class Control {

    private Conexion model;
    private ventini ini;


    public Control()  {
        model = new Conexion();
        model.conectar();
        ini= new ventini(this);
       
        
    }

    public void iniciar() throws SQLException  {
        ArrayList<Log> logs= new ArrayList<>();
      logs=model.getlogs();
      ini.init(logs);
    }
    
    public void getlogs() throws SQLException
    {
        model.getlogs();
    }



}

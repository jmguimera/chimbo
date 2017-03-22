package baseDatos;

import oracle.jdbc.pool.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Clase rara la conexion a base de datos oracle. */
public class AOracle {
   
    Connection conn=null;
    /*
     * @param ipServer Dirección IP del servidor o nombre del PC donde está el servidor (FQDN).
     * es caso de usarse el nombre dle PC, lo usual es que sea "localhost", debido a que 
     * normalmente instalamos el servidor de Base de Datos en la misma máquina donde desarrolamos
     * las aplicaciones.
     * @param puertoServer que por defecto es el puerto 1521 cuando se instala ORACLE Express,
     * si el servidor se ha instalado con otro puerto debe escribirse ese.
     * @param sid Nombre de la instancia. En Oracle XE el nombre de la instancia de la base de
     * datos es "XE".
     * @param user Usuario para conectarse, generalmente "HR". Este ususario viene desahabilitado
     * y hay que habilitarlo para poder usarlo. Tambien se puede crear uno nuevo
     * @param password es la contraseña para conectarse y esta asociada al usuaeio utilizado
     * @return Devuelve una instancia de la clase Connection, con la conexión establecida  o con 
     * un valor null de no haber sido posible la conexión. 
     */
    public Connection creaConexion (String ipServer,
            int puertoServer, String sid, String user,
            String password)
    {
        try {
            OracleDataSource ods = new OracleDataSource();                                
            String url = "jdbc:oracle:thin:@//"+ipServer+":"+puertoServer+"/"+sid;
            ods.setURL(url);
            ods.setUser(user);
            ods.setPassword(password);
            conn = ods.getConnection();                                   
        } catch (SQLException ex) {
            Logger.getLogger(AOracle.class.getName()).log(Level.SEVERE, null, ex);
            conn=null;
        }
        return conn;
    }

    /**
     * Crea una conexión a oracle cuando está instalado en el mismo ordenador
     * donde se desarrolla y al puerto por defecto (1521).
     * @param sid Nombre de la instancia. En el caso de Oracle XE el nombre
     * de la instancia de la base de datos es "XE".
     * @param user Usuario para conectarse, generalmente "HR".
     * @param password Password para conectarse, cuyo dato depende de como 
     * tengas configurada la base de datos.
     * @return El identificador de la conexión, instancia de la Connection o
     * null si no se ha podido conectar.     
     */
    public Connection construirConexion (String sid,
            String usuario, String password) {
        return creaConexion("127.0.0.1", 1521, sid, usuario, password);
    }

    // Constructor con datos ya indicados en el constructor
    public Connection creaConexion () {
        return creaConexion("127.0.0.1", 1521, "xe", "josem","viviana1");
    }    
    
    /**
     * Obtiene la conexión realizada a Oracle o null si todavía no se ha conectado.
     * @return Una instancia de la clase Connection, con la conexión a la base 
     * de datos, o null en caso de no haberse conectado.
     */
    public Connection getConn ()
    {
        return conn;
    }
    

	public void cerrarConexion() {
            try {
                
                conn.commit();
                conn.close();
                conn=null;
                System.out.println("Conexion Cerrada!!!");                
                
             }
            catch (SQLException  sqlerror) {
                System.out.println("Ha ocurrido un error al intentar cerrar la conexion con Oracle. Error:" + sqlerror.getMessage());
                System.out.println("Estado del SQL: "+sqlerror.getSQLState());
                System.out.println("Código del Error:"+sqlerror.getErrorCode());
             }
			
	}    
    
} // fin de la Clase AOracle

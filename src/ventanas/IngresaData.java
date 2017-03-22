/** @author José Miguel Guimerá Padrón. PROG 1º DAM */
package ventanas;

import baseDatos.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.JTextField;


public class IngresaData extends JFrame implements ActionListener,FocusListener {
                
        private JTextField  textReferencia,textSector,textDireccion,textPrecio;
        private JButton botonGuardar;
    
        private AOracle ConBaseDatos=new AOracle();
        
        public IngresaData(){
            
            componentes();    

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // cierra el programa al salir
            setSize(500,500);
            setLayout(null);
            setLocationRelativeTo(null); // centra la ventana
            setTitle("** Datos de la Propiedad **");            
            setVisible(true);   

        }
        
        public void componentes(){
                        
            JLabel  labelTitle,labelReferencia,labelSector,labelDireccion,
                    labelPrecio;
                        
            labelTitle= new JLabel();
            labelTitle.setBounds(100, 20, 200, 20);
            labelTitle.setText("Datos de la propiedad");
            add(labelTitle);
            
            labelReferencia=new JLabel();
            labelReferencia.setBounds(50, 70, 200, 20);
            labelReferencia.setText("Referencia: ");
            add(labelReferencia);
            
            textReferencia=new JTextField();
            textReferencia.setBounds(135, 70, 150, 20);
            add(textReferencia);
            
            labelSector=new JLabel();
            labelSector.setText("Sector: ");
            labelSector.setBounds(50, 95, 100,20);
            add(labelSector);
            
            textSector=new JTextField();
            textSector.setBounds(135, 95, 150, 20);
            add(textSector);
            
            labelDireccion=new JLabel();
            labelDireccion.setText("Direccion: ");
            labelDireccion.setBounds(50, 120, 100,20);
            add(labelDireccion);
            
            textDireccion=new JTextField();
            textDireccion.setBounds(135, 120, 150, 20);
            add(textDireccion);            
//            labelDireccion
//            labelPrecio        
            
            botonGuardar=new JButton();
            botonGuardar.setText("Guardar");
            botonGuardar.setBounds(150,450, 95, 20);
            botonGuardar.addActionListener(this);
            add(botonGuardar);
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource()==botonGuardar){
            
            System.out.println("Okey listo para Guardar lo que sea!!!!!");

            if(ConBaseDatos.creaConexion()==null){
                     JOptionPane.showMessageDialog(null,"Error de Conexión a la Base de Datos", "Error", ERROR_MESSAGE);
            }  
            
            String ref="'"+textReferencia.getText().trim()+"'";
            String sec="'"+textSector.getText().trim()+"'";
//            String dir="'"+textDireccion.getText().trim()+"'";            

 /* tipovia VARCHAR2(10), 

 nombrevia VARCHAR2(20),

 numero INTEGER,

 dirinterior VARCHAR2(30),

 codigopostal CHAR(5),

 localidad VARCHAR(20),

 provincia VARCHAR(20),

 region VARCHAR(20),

 pais VARCHAR(15)) */

            String tipo="'Avenida',";
            String via="'Los Mojuelos',";
 //           String dir="cualquier direccion";            
            Object TIPO_DIRECCION=null;
            int num=36;
            String dirint="'Edif. Tahiche, Piso 1, Puerta Izq.',";
            String postal="'38108',";
            String local="'La Laguna',";
            String prov="'Tenerife',";
            String region="'Canarias',";
            String pais="'España'";

//"TIPO_DIRECCION("+tipo+via+num+postal+local+prov+region+pais+")";
//            String sqlq="INSERT INTO PROPIEDADES VALUES(?,?,?)";
//            sqlq="INSERT INTO PROPIEDADES VALUES ("+ref+","+sec+","+dir+")";                

//            String sqlq="INSERT INTO PROPIEDADES VALUES("+ref+","+sec+","+dir+")";

            String sqlq="INSERT INTO PROPIEDADES VALUES(?,?,TIPO_DIRECCION(?,?,?,?,?,?,?,?,?))";
            
          PreparedStatement ps = null;
           
            try {
                ps = ConBaseDatos.getConn().prepareStatement(sqlq);

                   ps.setString(1,ref);
                   ps.setString(2,sec);
                   ps.setObject(3,TIPO_DIRECCION);
                   ps.setString(4,tipo);
                   ps.setString(5,via);
                   ps.setInt(6,num);
                   ps.setString(7,dirint);
                   ps.setString(8,postal);                   
                   ps.setString(9,local);
                   ps.setString(10,prov);
                   ps.setString(11,region);
                   ps.setString(12,pais);                   

             //    ps.setDate(campo, x);
             //   ps.setDouble(campo, x);
             //   ps.setInt(campo, x);
                ps.executeUpdate();
           
                ConBaseDatos.getConn().close();
            }
                catch (SQLException ex) {
                Logger.getLogger(IngresaData.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Mensaje del Error SQL: "+ex.getMessage());                
                System.out.println("Estado del SQL: "+ex.getSQLState());
                System.out.println("Código del Error:"+ex.getErrorCode());
                } 
                finally{
                try {
                    ps.close();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(IngresaData.class.getName()).log(Level.SEVERE, null, ex);
                }
                ConBaseDatos.cerrarConexion();
            
                }

            
        }
        
    }

    @Override
    public void focusGained(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusLost(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            
    
    
}


/* rutimas para la creacion de base de datos en oracle tipo objeto

/*create or replace type Propiedad as OBJECT(
    referencia VARCHAR2(25),
    sector VARCHAR2(25),
    direccion VARCHAR2(40)
) ;

create or replace TYPE tipo_conductor AS OBJECT (

  nombre VARCHAR(20), apellidos VARCHAR(20), fecha_nacimiento DATE,



);


create table propiedades of propiedad(
  referencia primary key
);*/

/*
            String sqlq="insert into propiedades values(PROPIEDAD(textReferencia.getText(),textSector.getText(),textDireccion.getText()))";

*/
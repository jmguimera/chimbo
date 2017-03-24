/** @author José Miguel Guimerá Padrón. **/
package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Menu extends JFrame implements ActionListener,FocusListener {

    JLabel lblRegistrar;
    JTextField txtRegistrar;
    JButton btnGuardar;
    
    public Menu(){
    
       componentes();
        
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //termina el programa al salir de la ventana
       setLayout(null); // no hay plantilla a seguir
       setSize(600,800); // tamaño de la ventana
       setLocationRelativeTo(null);// centra la ventana en la pantalla del equipo
       setVisible(true); // hace a la ventana visible para ser utilizada
       setTitle("Menu Principal del Sistema"); // Coloca un Titulo a la ventana

    }
    
    public final void componentes(){
    
        lblRegistrar=new JLabel();
        lblRegistrar.setBounds(50, 70, 70, 20);
        lblRegistrar.setText("Registrar");
        add(lblRegistrar);
        
        txtRegistrar=new JTextField();
        txtRegistrar.setBounds(125, 70, 80, 20);
        txtRegistrar.addFocusListener(this);            
        add(txtRegistrar);
        
        
        btnGuardar=new JButton();
        btnGuardar.setBounds(70, 500, 100, 20);
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this);
        add(btnGuardar);
        
    
    }
    
    
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(e.getSource().equals(btnGuardar)){
               
                System.out.println("Grabando los datos introducidos");
               // Rutina de guardado de los datos, ya sea en una coleccion, un archivo o en una base de datos 
                 
            }
    }

    @Override
    public void focusGained(FocusEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(e.getSource().equals(txtRegistrar)){}
    }

    @Override
    public void focusLost(FocusEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

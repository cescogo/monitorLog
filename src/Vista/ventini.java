/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Modelo.*;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;


/**
 *
 * @author cesar
 */
public class ventini extends JFrame implements ActionListener {
     private JPanel pan_prc,pan_log;
     private Control gestor;
     
     private JLabel inf;
  
     private JButton boton;
     private ImageIcon image;
 
    
     Color[] colors;
      int[] param;
    public ventini(Control c)
    {
        super("Ventana de logs");
    gestor=c;
     
      
        
       
    }

    
    public void init(ArrayList<Log> logs)
    {
        int poy=0;
         pan_prc = new JPanel();
     pan_log=new JPanel();
     boton= new JButton();
        Log aux= new Log();
        JLabel carac= new JLabel("inactico ");
        carac.setForeground(Color.GREEN);
        pan_log.add(carac,FlowLayout.LEFT);
         carac= new JLabel("activo ");
        carac.setForeground(Color.YELLOW);
        pan_log.add(carac,FlowLayout.CENTER);
         carac= new JLabel("current");
        carac.setForeground(Color.RED);
        pan_log.add(carac,FlowLayout.RIGHT);
        GridBagLayout tb= new GridBagLayout();
        pan_prc.setLayout(tb);
        pan_log.setLayout(tb);
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets=new Insets(0,0,10,10);
        
        for(int i=0;i<logs.size();i++)
        {
           
          aux=logs.get(i);
          if(poy%4==0)
          {
              poy++;
          }
            if(aux.getStatus().equals("INACTIVE"))
            {
                image= new ImageIcon(getClass().getResource("/imagenes/verde.png"));
            }else
                if(aux.getStatus().equals("ACTIVE"))
            {
                image= new ImageIcon(getClass().getResource("/imagenes/amarillo.png"));
            }else
                    if(aux.getStatus().equals("CURRENT"))
            {
                image= new ImageIcon(getClass().getResource("/imagenes/RED.png"));
            }
            gc.gridx=i%4;
            gc.gridy=poy%3;
            inf= new JLabel("grupo # : "+aux.getGroup());
            pan_prc.add(inf,gc);
            
            gc.gridx=i%4;
            gc.gridy=(poy%3)+1;
            inf= new JLabel();
            inf.setIcon(image);
            pan_prc.add(inf,gc);
            
            gc.gridx=i%4;
            gc.gridy=(poy%3)+2;
            inf= new JLabel("miembros: "+aux.getMember());
            pan_prc.add(inf,gc);
            
           
                
            
        }
      
           
     
       /* boton= new JButton("Atras");
        boton.setActionCommand("Atras");
        boton.addActionListener(this);*/
        
        
       
       add(pan_log,BorderLayout.NORTH);
        add(pan_prc,BorderLayout.CENTER);
         setSize(600,300);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
}

    public void atras (char ban) throws InterruptedException
  {
      if(ban=='v')
      {
        
      }
  }

    
}
 


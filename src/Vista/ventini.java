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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author cesar
 */
public class ventini extends JFrame implements ActionListener {
     private JPanel pan_prc,pan_log,pan_rut;
     private Control gestor;
     
     private JLabel inf;
  
     private JButton boton;
     private ImageIcon image;
 private ModeloTablalog tabla;
 private ModeloTablalocal tab;
    private JTable table,ta;
    
     Color[] colors;
      int[] param;
    public ventini(Control c)
    {
        super("Ventana de logs");
        gestor=c;
         tabla= new ModeloTablalog();
     
      
        
       
    }

    
    public void init(ArrayList<Log> logs)
    {
       
         pan_prc = new JPanel();
     pan_log=new JPanel();
     boton= new JButton();
        Log aux= new Log();
      final JScrollPane scrollPane = new JScrollPane( pan_prc);
      GridBagLayout tb= new GridBagLayout();
    pan_prc.setLayout(tb);
    
     GridBagConstraints gc = new GridBagConstraints();
    gc.insets=new Insets(0,0,10,10);
    int poy=0;
        for(int i=0;i<logs.size();i++)
        {
           
          aux=logs.get(i);
          
             if(i%4==0)
          {
              poy+=3;
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
            gc.gridy=poy;
            inf= new JLabel("grupo # : "+aux.getGroup());
            pan_prc.add(inf,gc);
            
            gc.gridx=i%4;
            gc.gridy=(poy)+1;
            JButton log= new JButton();
            log.setIcon(image);
            log.setOpaque(false);
            log.setContentAreaFilled(false);
            log.setBorderPainted(false);
            log.addActionListener(this);
            log.setActionCommand(aux.getGroup());
            pan_prc.add(log,gc);
            
            gc.gridx=i%4;
            gc.gridy=poy+2;
            inf= new JLabel("miembros: "+aux.getMember());
            pan_prc.add(inf,gc);
           
            
         }
        
      pan_rut= new JPanel();
       pan_rut.setLayout(new BorderLayout());
       pan_rut.setPreferredSize(new Dimension(800,200));
            tab= new ModeloTablalocal();
        ta= new JTable();
        ta.setModel(tab);
        JScrollPane desplazamientoTab = new JScrollPane(
                  ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
         desplazamientoTab.setViewportView(ta);
         
        pan_rut.add(BorderLayout.CENTER,desplazamientoTab);
        pan_rut.setVisible(false);
        
       /* boton= new JButton("Atras");
        boton.setActionCommand("Atras");
        boton.addActionListener(this);*/
        
        
    scrollPane.setBounds(0, 0, 780, 100);
    JPanel contentPane = new JPanel(null);
    contentPane.add(scrollPane);

    add(contentPane,BorderLayout.CENTER);
      
       add(pan_rut,BorderLayout.SOUTH);
       add(pan_log,BorderLayout.NORTH);
        
         setSize(800,400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
        if(e.getActionCommand()!="atras"){
            try {
                System.out.println(e.getActionCommand());
                rutas(gestor.getLocal(e.getActionCommand()));
            } catch (SQLException ex) {
                Logger.getLogger(ventini.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
      
        
        
}

    public void atras (char ban) throws InterruptedException
  {
      
      if(ban=='v')
      {
        
      }
  }

    private void rutas(ArrayList<LogLocal> ruts)
    {
         int aux=ta.getRowCount();
        for (int i = 0; i < aux; i++) {
           
          tab.removeRow(0);
          
        }
       
       for(int i=0;i<ruts.size();i++)
        {
           tab.addRow(new Object[]{ruts.get(i).getLocal(),ruts.get(i).getStatus()});
          
        }
        
        pan_rut.setVisible(true);
    }
    
}
 class ModeloTablalog extends DefaultTableModel {

        public ModeloTablalog() {
            super(new Object[][]{},
                    new String[]{            
            "grupo","miembros","status"});
            
            }
        
        @Override
        public boolean isCellEditable(int filas, int columnas)
        {
            return false;
        }
    }
 class ModeloTablalocal extends DefaultTableModel {

        public ModeloTablalocal() {
            super(new Object[][]{},
                    new String[]{            
            "local","status"});
            
            }
        
        @Override
        public boolean isCellEditable(int filas, int columnas)
        {
            return false;
        }
    }
 


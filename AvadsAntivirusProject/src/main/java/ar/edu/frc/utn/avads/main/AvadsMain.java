/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.frc.utn.avads.main;

import ar.edu.frc.utn.avads.db.service.DBService;
import ar.edu.frc.utn.avads.db.service.impl.DBServiceMongoImpl;
import ar.edu.frc.utn.avads.igu.VentanaPrincipal;
import ar.edu.frc.utn.avads.util.AvadsUtil;
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import ar.edu.frc.utn.avads.util.PropertiesClientUtil;


/**
 *
 * @author zurdoju
 */
public class AvadsMain {

    public static final DBService serviceDB = new DBServiceMongoImpl();
    public static TrayIcon trayIcon;
    public static final SystemTray tray = SystemTray.getSystemTray();    
    static VentanaPrincipal ventPrincipal;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        cargarEstiloVentanas();
        
        serviceDB.startDB(PropertiesClientUtil.getProperty("db.mongo.user"), PropertiesClientUtil.getProperty("db.mongo.pwd"), 
                PropertiesClientUtil.getProperty("db.mongo.url"), PropertiesClientUtil.getProperty("db.mongo.puerto"),
                PropertiesClientUtil.getProperty("db.mongo.nombre.db"));
        ventPrincipal = new VentanaPrincipal(); 
        if (!SystemTray.isSupported()) {
            ventPrincipal.setVisible(true);
        }
        else 
        {
            SwingUtilities.invokeLater(() -> {
                armarMenu();
            });
        }             
    }
       
    private static void cargarEstiloVentanas() {
        
        try {
            UIManager.setLookAndFeel(PropertiesClientUtil.getProperty("estilo.ventana"));
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }         
    }
    
    private static void armarMenu() {
        
        final PopupMenu popup = new PopupMenu();
        trayIcon = new TrayIcon(AvadsUtil.createImage(PropertiesClientUtil.getProperty("icono.system.tray"), PropertiesClientUtil.getProperty("principal.tooltip.text")));
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip(PropertiesClientUtil.getProperty("principal.tooltip.text"));
        
        MenuItem about = new MenuItem(PropertiesClientUtil.getProperty("principal.acerca.avads"));
        MenuItem vPrincipal = new MenuItem(PropertiesClientUtil.getProperty("principal.ventana"));    
        MenuItem salir = new MenuItem(PropertiesClientUtil.getProperty("principal.salir")); 
        
        popup.add(about);
        popup.addSeparator();
        popup.add(vPrincipal);
        popup.addSeparator();
        popup.add(salir);
        
        trayIcon.setPopupMenu(popup);
        
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println(PropertiesClientUtil.getProperty("principal.error.agregar.trayIcon"));
            return;
        }
        
        ActionListener listener = (ActionEvent e) -> {
            MenuItem item = (MenuItem)e.getSource();
            if (PropertiesClientUtil.getProperty("principal.acerca.avads").equals(item.getLabel())) {
                //ventAbout.setVisible(true);
                //ventAbout.setLocationRelativeTo(null);
            } else if (PropertiesClientUtil.getProperty("principal.ventana").equals(item.getLabel())) {
                ventPrincipal.setVisible(true);
                ventPrincipal.update(ventPrincipal.getGraphics()); 
            }
        };        
        
        vPrincipal.addActionListener(listener);
        about.addActionListener(listener);
        
        salir.addActionListener((ActionEvent e) -> {
            int resp = JOptionPane.showConfirmDialog(ventPrincipal, PropertiesClientUtil.getProperty("principal.pregunta.salir"), 
                    PropertiesClientUtil.getProperty("ventana.dialogo.atencion"), JOptionPane.YES_NO_OPTION);
            if(resp == JOptionPane.YES_OPTION)
            {
                tray.remove(trayIcon);
                serviceDB.closeDB();
                System.exit(0);
            }
        });
    }        
    
}

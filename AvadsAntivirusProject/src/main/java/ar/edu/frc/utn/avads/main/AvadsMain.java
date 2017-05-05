/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.frc.utn.avads.main;

import ar.edu.frc.utn.avads.db.impl.DBServiceMongoImpl;
import ar.edu.frc.utn.avads.db.service.DBService;
import ar.edu.frc.utn.avads.igu.VentanaPrincipal;
import ar.edu.frc.utn.avads.scan.service.impl.VirusTotalFileScanServiceImpl;
import ar.edu.frc.utn.avads.util.AvadsUtil;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 * @author zurdoju
 */
public class AvadsMain {

    public static Properties propC = new Properties();
    public static final DBService serviceDB = new DBServiceMongoImpl();
    public static TrayIcon trayIcon;
    public static final SystemTray tray = SystemTray.getSystemTray();    
    static VentanaPrincipal ventPrincipal;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        cargarPropiedades();
        cargarEstiloVentanas();
        
        serviceDB.startDB();
        ventPrincipal = new VentanaPrincipal();        
        if (!SystemTray.isSupported()) {
            System.out.println(propC.getProperty("principal.sysTray"));
            ventPrincipal.setVisible(true);
        }
        else 
        {
            SwingUtilities.invokeLater(() -> {
                armarMenu();
            });
        }             
        
        try {
                new VirusTotalFileScanServiceImpl().scanFile(new File("/home/gustavo/TestVS.exe"));
                new VirusTotalFileScanServiceImpl().fileReport("c65f8a83fa607f9e98d041ea90c6e6cb06d9d27ec5077e48beb62bd39a19e8f5-1492475018");

        } catch (UniformInterfaceException | ClientHandlerException e) {
                e.printStackTrace();
        }        

    }
   
    private static void cargarPropiedades() {
        
        try 
        {    
            InputStream inputC = new FileInputStream(AvadsUtil.getFileResourceByPath("ApplicationResources_es.properties"));
            try 
            {
                propC.load(inputC);                        
                inputC.close();
            } catch (IOException ex) {
                Logger.getLogger(AvadsMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(AvadsMain.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }        
    }
    
    private static void cargarEstiloVentanas() {
        
        try {
            UIManager.setLookAndFeel(propC.getProperty("estilo.ventana"));
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }         
    }
    
    private static void armarMenu() {
        
        final PopupMenu popup = new PopupMenu();
        trayIcon = new TrayIcon(AvadsUtil.createImage(propC.getProperty("icono.system.tray"), propC.getProperty("principal.tooltip.text")));
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip(propC.getProperty("principal.tooltip.text"));
        
        MenuItem about = new MenuItem(propC.getProperty("principal.acerca.avads"));
        MenuItem vPrincipal = new MenuItem(propC.getProperty("principal.ventana"));    
        MenuItem salir = new MenuItem(propC.getProperty("principal.salir")); 
        
        popup.add(about);
        popup.addSeparator();
        popup.add(vPrincipal);
        popup.addSeparator();
        popup.add(salir);
        
        trayIcon.setPopupMenu(popup);
        
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println(propC.getProperty("principal.error.agregar.trayIcon"));
            return;
        }
        
        ActionListener listener = (ActionEvent e) -> {
            MenuItem item = (MenuItem)e.getSource();
            if (propC.getProperty("principal.acerca.avads").equals(item.getLabel())) {
                //ventAbout.setVisible(true);
                //ventAbout.setLocationRelativeTo(null);
            } else if (propC.getProperty("principal.ventana").equals(item.getLabel())) {
                ventPrincipal.setVisible(true);
                ventPrincipal.update(ventPrincipal.getGraphics()); 
            }
        };        
        
        vPrincipal.addActionListener(listener);
        about.addActionListener(listener);
        
        salir.addActionListener((ActionEvent e) -> {
            int resp = JOptionPane.showConfirmDialog(ventPrincipal, propC.getProperty("principal.pregunta.salir"), propC.getProperty("ventana.dialogo.atencion"), JOptionPane.YES_NO_OPTION);
            if(resp == JOptionPane.YES_OPTION)
            {
                tray.remove(trayIcon);
                serviceDB.closeDB();
                System.exit(0);
            }
        });
    }        
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.frc.utn.avads.main;

import ar.edu.frc.utn.avads.scan.service.impl.VirusTotalFileScanServiceImpl;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author zurdoju
 */
public class AvadsMain {

    public static Properties propC = new Properties();
    static final SystemTray tray = SystemTray.getSystemTray();
    static TrayIcon trayIcon;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {    
            InputStream inputC = new FileInputStream("ApplicationResources_es.properties");
            try 
            {
                propC.load(inputC);                        
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(AvadsMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                inputC.close();
            } catch (IOException ex) {
                Logger.getLogger(AvadsMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(AvadsMain.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }              
        
        try {
                new VirusTotalFileScanServiceImpl().scanFile(new File("/home/gustavo/TestVS.exe"));
                new VirusTotalFileScanServiceImpl().fileReport("c65f8a83fa607f9e98d041ea90c6e6cb06d9d27ec5077e48beb62bd39a19e8f5-1492475018");

        } catch (UniformInterfaceException e) {
                e.printStackTrace();
        } catch (ClientHandlerException e) {
                e.printStackTrace();
        }        

    }
   
    
}

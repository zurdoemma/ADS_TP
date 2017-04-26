/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.frc.utn.avads.main;

import java.awt.SystemTray;
import java.awt.TrayIcon;
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
        
        try 
        {    
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
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(AvadsMain.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }              
        

    }
   
    
}

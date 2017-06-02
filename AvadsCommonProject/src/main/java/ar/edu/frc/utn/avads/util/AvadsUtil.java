/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.frc.utn.avads.util;

import com.google.gson.Gson;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.apache.commons.lang.WordUtils;

/**
 *
 * @author zurdoju
 */
public class AvadsUtil {

    public static Gson gson = new Gson();
    
    public AvadsUtil() {
    }
 
    public static Image getImageByPath(String path) {
        URL imgURL = AvadsUtil.class.getResource(path);
        Image imagen = Toolkit.getDefaultToolkit().getImage(imgURL);

        return imagen;
    }
    
    public static ImageIcon getImageIconByPath(String path) {
        URL imgURL = AvadsUtil.class.getResource(path);
        ImageIcon imagenIcon = new ImageIcon(imgURL);
        return imagenIcon;
    }

    public static File getFileByPath(String path) {
        URL imgURL = AvadsUtil.class.getResource(path);
        File file = new File(imgURL.getFile());
        return file;
    }
    
    public static File getFileResourceByPath(String path)
    {
	return new File(AvadsUtil.class.getClassLoader().getResource(path).getFile());        
    }        
    
    public static long daysBetween(Date one, Date two) { 
        long difference = (one.getTime()-two.getTime())/86400000; 
        return Math.abs(difference); 
    }
    
    public static Image createImage(String path, String description) {
        URL imageURL = AvadsUtil.class.getResource(path);
        
        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
    
    public static ImageIcon createImageIcon(String path, String description) {
        URL imageURL = AvadsUtil.class.getResource(path);
        
        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return new ImageIcon(imageURL, description);
        }
    }  
    
    public static String getDate(Date date) {
        SimpleDateFormat fech = new SimpleDateFormat("dd/MM/yyyy");
        return fech.format(date);
    } 
    
    public static String getHour(Date hour) {
        SimpleDateFormat fech = new SimpleDateFormat("HH:mm:ss");
        return fech.format(hour);
    } 
    
    public static String getDateReportUpdateAV(String date) {
        String dateR = null;
        SimpleDateFormat fech = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat fech2 = new SimpleDateFormat("dd/MM/yyyy");
        
        if(date == null || date.trim().compareTo("null") == 0) return null;
        try {
            dateR = fech2.format(fech.parse(date));
        } catch (ParseException ex) {
            Logger.getLogger(AvadsUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dateR;
    }
    
    public static String addDaysToDate(String date, int daysToAdd)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(dateFormat.parse(date));
        } catch (ParseException ex) {
            Logger.getLogger(AvadsUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.add(Calendar.DATE, daysToAdd);  
        
        return dateFormat.format(c.getTime());
    }
    
    public static Object getClassReflection(Class clase, Map<String, Object> values) {
        Map<String, Object> valuesNormal = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : values.entrySet())
        {
            String entryN = "";
            String[] entryA = entry.getKey().split("_");
            for(int i=0;i<entryA.length;i++)
            {
                if(i != 0) entryA[i] = WordUtils.capitalize(entryA[i]);
                entryN = entryN + entryA[i];
            }
            
            valuesNormal.put(entryN, entry.getValue());
        }
                
        
        Field[] fields = clase.getDeclaredFields();
        
        Object object = null;
        try {
            object = clase.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(AvadsUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AvadsUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(Field f: fields)
        {
            Object valueField = valuesNormal.get(f.getName());
            Method method = null;

            if(valueField != null)
            { 
                try {
                    method = clase.getDeclaredMethod("set"+WordUtils.capitalize(f.getName()), valueField.getClass());
                    try {
                        method.invoke(object, valueField);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(AvadsUtil.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(AvadsUtil.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(AvadsUtil.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(AvadsUtil.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(AvadsUtil.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

        return object;
    }
}

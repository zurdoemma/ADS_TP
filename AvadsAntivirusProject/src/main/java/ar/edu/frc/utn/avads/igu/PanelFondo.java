package ar.edu.frc.utn.avads.igu;

import java.awt.Graphics;
import java.awt.Image;

public class PanelFondo extends javax.swing.JPanel {
    
    private Image image = null;
    
    public PanelFondo(Image image) 
    {
        initComponents();
        this.image = image;
    }
    
    public void setImage(Image image)
    {
        this.image = image;
    }
    
    public Image getImage(Image image)
    {
        return image;
    }
    
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g); 
        
        if (image != null) 
        { 
            int height = this.getSize().height;
            int width = this.getSize().width;         
            g.drawImage(image,0,0, width, height, this);
             
         } 
    }

      
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
  
}




    
    
    
      

   
   



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.frc.utn.avads.igu;

import ar.edu.frc.utn.avads.controller.impl.FileController;
import ar.edu.frc.utn.avads.controller.impl.TaskRunController;
import static ar.edu.frc.utn.avads.igu.VentanaPrincipal.vEstadoArchivos;
import ar.edu.frc.utn.avads.main.AvadsMain;
import ar.edu.frc.utn.avads.util.AvadsUtil;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author zurdoju
 */
public class VentanaExaminarArchivo extends javax.swing.JDialog {

    /**
     * Creates new form VentanaExaminarArchivo
     */
    private java.awt.Frame padre;
    private FileController fileControler;
    
    public VentanaExaminarArchivo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        padre = parent;
        Image icono = AvadsUtil.getImageByPath(AvadsMain.propC.getProperty("icono.system.image"));
        this.setIconImage(icono);
        
        this.setTitle(AvadsMain.propC.getProperty("igu.configuracion.title")); 
        
        jPanelParametrosGenerales.setBorder(javax.swing.BorderFactory.createTitledBorder(AvadsMain.propC.getProperty("igu.examinarArchivos.panelParametrosGenerales.title")));
        jPanelArchivosExaminar.setBorder(javax.swing.BorderFactory.createTitledBorder(AvadsMain.propC.getProperty("igu.examinarArchivos.panelSeleccionArchivos.title")));
        
        jLabelCantidadArchivos.setText(AvadsMain.propC.getProperty("igu.examinarArchivos.cantidadArchivos"));
        jLabelGuardarReporte.setText(AvadsMain.propC.getProperty("igu.examinarArchivos.guardarReporte"));
        
        jRadioButtonNO.setText(AvadsMain.propC.getProperty("igu.examinarArchivos.guardarReporte.NO"));
        jRadioButtonSI.setText(AvadsMain.propC.getProperty("igu.examinarArchivos.guardarReporte.SI"));
        
        jButtonEjecutar.setIcon(AvadsUtil.getImageIconByPath(AvadsMain.propC.getProperty("igu.examinarArchivos.imagen.boton.ejecutar")));
        jButtonEjecutar.setToolTipText(AvadsMain.propC.getProperty("igu.examinarArchivos.boton.ejecutar"));
        jButtonCancelar.setIcon(AvadsUtil.getImageIconByPath(AvadsMain.propC.getProperty("igu.configuracion.imagen.boton.cancelar")));
        jButtonCancelar.setToolTipText(AvadsMain.propC.getProperty("igu.configuracion.boton.cancelar"));

        String[] cantidadArchivos = AvadsMain.propC.getProperty("igu.examinarArchivos.cantidadArchivos.numero").split(",");
        for(String cantArch : cantidadArchivos) jComboBoxCantidadArchivos.addItem(cantArch);
        
        jFileChooserArchivo.setFileSelectionMode(jFileChooserArchivo.FILES_ONLY);
        jFileChooserArchivo.setDialogTitle(AvadsMain.propC.getProperty("igu.examinarArchivos.seleccionarArchivo.title"));
        jFileChooserArchivo.setMultiSelectionEnabled(false);
        
        fileControler = new FileController(null);
        
        if(vEstadoArchivos == null) vEstadoArchivos = new VentanaEstadoAnalisis();       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupGuardarReporte = new javax.swing.ButtonGroup();
        jPanelParametrosGenerales = new javax.swing.JPanel();
        jLabelCantidadArchivos = new javax.swing.JLabel();
        jComboBoxCantidadArchivos = new javax.swing.JComboBox<String>();
        jLabelGuardarReporte = new javax.swing.JLabel();
        jRadioButtonSI = new javax.swing.JRadioButton();
        jRadioButtonNO = new javax.swing.JRadioButton();
        jPanelArchivosExaminar = new javax.swing.JPanel();
        jTextFieldArchivo1 = new javax.swing.JTextField();
        jButtonSeleccionArchivo1 = new javax.swing.JButton();
        jButtonSeleccionArchivo2 = new javax.swing.JButton();
        jTextFieldArchivo2 = new javax.swing.JTextField();
        jButtonSeleccionArchivo3 = new javax.swing.JButton();
        jTextFieldArchivo3 = new javax.swing.JTextField();
        jButtonSeleccionArchivo4 = new javax.swing.JButton();
        jTextFieldArchivo4 = new javax.swing.JTextField();
        jButtonCancelar = new javax.swing.JButton();
        jButtonEjecutar = new javax.swing.JButton();
        jFileChooserArchivo = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanelParametrosGenerales.setBorder(javax.swing.BorderFactory.createTitledBorder("Parametros Generales"));
        jPanelParametrosGenerales.setPreferredSize(new java.awt.Dimension(360, 65));

        jLabelCantidadArchivos.setText("Cantidad Archivos:");

        jComboBoxCantidadArchivos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxCantidadArchivosItemStateChanged(evt);
            }
        });
        jComboBoxCantidadArchivos.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBoxCantidadArchivosPropertyChange(evt);
            }
        });

        jLabelGuardarReporte.setText("Guardar Reporte:");

        buttonGroupGuardarReporte.add(jRadioButtonSI);
        jRadioButtonSI.setText("Si");

        buttonGroupGuardarReporte.add(jRadioButtonNO);
        jRadioButtonNO.setSelected(true);
        jRadioButtonNO.setText("No");

        javax.swing.GroupLayout jPanelParametrosGeneralesLayout = new javax.swing.GroupLayout(jPanelParametrosGenerales);
        jPanelParametrosGenerales.setLayout(jPanelParametrosGeneralesLayout);
        jPanelParametrosGeneralesLayout.setHorizontalGroup(
            jPanelParametrosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelParametrosGeneralesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelCantidadArchivos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxCantidadArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelGuardarReporte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonSI)
                .addGap(12, 12, 12)
                .addComponent(jRadioButtonNO)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelParametrosGeneralesLayout.setVerticalGroup(
            jPanelParametrosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelParametrosGeneralesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelParametrosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCantidadArchivos)
                    .addComponent(jComboBoxCantidadArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelGuardarReporte)
                    .addComponent(jRadioButtonSI)
                    .addComponent(jRadioButtonNO))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelArchivosExaminar.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccion Archivos"));

        jTextFieldArchivo1.setEnabled(false);

        jButtonSeleccionArchivo1.setText("...");
        jButtonSeleccionArchivo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionArchivo1ActionPerformed(evt);
            }
        });

        jButtonSeleccionArchivo2.setText("...");
        jButtonSeleccionArchivo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionArchivo2ActionPerformed(evt);
            }
        });

        jTextFieldArchivo2.setEnabled(false);

        jButtonSeleccionArchivo3.setText("...");
        jButtonSeleccionArchivo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionArchivo3ActionPerformed(evt);
            }
        });

        jTextFieldArchivo3.setEnabled(false);

        jButtonSeleccionArchivo4.setText("...");
        jButtonSeleccionArchivo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionArchivo4ActionPerformed(evt);
            }
        });

        jTextFieldArchivo4.setEnabled(false);

        javax.swing.GroupLayout jPanelArchivosExaminarLayout = new javax.swing.GroupLayout(jPanelArchivosExaminar);
        jPanelArchivosExaminar.setLayout(jPanelArchivosExaminarLayout);
        jPanelArchivosExaminarLayout.setHorizontalGroup(
            jPanelArchivosExaminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelArchivosExaminarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelArchivosExaminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelArchivosExaminarLayout.createSequentialGroup()
                        .addComponent(jTextFieldArchivo4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSeleccionArchivo4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelArchivosExaminarLayout.createSequentialGroup()
                        .addComponent(jTextFieldArchivo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSeleccionArchivo1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelArchivosExaminarLayout.createSequentialGroup()
                        .addComponent(jTextFieldArchivo2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSeleccionArchivo2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelArchivosExaminarLayout.createSequentialGroup()
                        .addComponent(jTextFieldArchivo3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSeleccionArchivo3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelArchivosExaminarLayout.setVerticalGroup(
            jPanelArchivosExaminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelArchivosExaminarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelArchivosExaminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldArchivo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSeleccionArchivo1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelArchivosExaminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldArchivo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSeleccionArchivo2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelArchivosExaminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldArchivo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSeleccionArchivo3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelArchivosExaminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldArchivo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSeleccionArchivo4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEjecutarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jFileChooserArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEjecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanelParametrosGenerales, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                        .addComponent(jPanelArchivosExaminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelParametrosGenerales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelArchivosExaminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonEjecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jFileChooserArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxCantidadArchivosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBoxCantidadArchivosPropertyChange

    }//GEN-LAST:event_jComboBoxCantidadArchivosPropertyChange

    private void jComboBoxCantidadArchivosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxCantidadArchivosItemStateChanged
        switch(Integer.parseInt(jComboBoxCantidadArchivos.getSelectedItem().toString().trim()))
        {
            case 1:
            {
                jTextFieldArchivo1.setVisible(true);
                jButtonSeleccionArchivo1.setVisible(true);
                        
                jTextFieldArchivo2.setVisible(false);
                jButtonSeleccionArchivo2.setVisible(false);
                
                jTextFieldArchivo3.setVisible(false);
                jButtonSeleccionArchivo3.setVisible(false);
                
                jTextFieldArchivo4.setVisible(false);
                jButtonSeleccionArchivo4.setVisible(false);
                
                break;
            }
            
            case 2:
            {
                jTextFieldArchivo1.setVisible(true);
                jButtonSeleccionArchivo1.setVisible(true);
                        
                jTextFieldArchivo2.setVisible(true);
                jButtonSeleccionArchivo2.setVisible(true);
                
                jTextFieldArchivo3.setVisible(false);
                jButtonSeleccionArchivo3.setVisible(false);
                
                jTextFieldArchivo4.setVisible(false);
                jButtonSeleccionArchivo4.setVisible(false);
                
                break;
            }

            case 3:
            {
                jTextFieldArchivo1.setVisible(true);
                jButtonSeleccionArchivo1.setVisible(true);
                        
                jTextFieldArchivo2.setVisible(true);
                jButtonSeleccionArchivo2.setVisible(true);
                
                jTextFieldArchivo3.setVisible(true);
                jButtonSeleccionArchivo3.setVisible(true);
                
                jTextFieldArchivo4.setVisible(false);
                jButtonSeleccionArchivo4.setVisible(false);
                
                break;
            }

            case 4:
            {
                jTextFieldArchivo1.setVisible(true);
                jButtonSeleccionArchivo1.setVisible(true);
                        
                jTextFieldArchivo2.setVisible(true);
                jButtonSeleccionArchivo2.setVisible(true);
                
                jTextFieldArchivo3.setVisible(true);
                jButtonSeleccionArchivo3.setVisible(true);
                
                jTextFieldArchivo4.setVisible(true);
                jButtonSeleccionArchivo4.setVisible(true);
                
                break;
            }            
        }
    }//GEN-LAST:event_jComboBoxCantidadArchivosItemStateChanged

    private void jButtonEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEjecutarActionPerformed
        int cantArch = Integer.parseInt(jComboBoxCantidadArchivos.getSelectedItem().toString());
        String[] archivosAnalizar = new String[cantArch];

        if(cantArch > 0)
        {
            if(cantArch == 1) 
            {
                if(jTextFieldArchivo1.getText().isEmpty()) 
                {
                    mostrarMensaje(AvadsMain.propC.getProperty("igu.examinarArchivos.mensaje.eleccion.archivo"));
                    return;
                }
                archivosAnalizar[0] = jTextFieldArchivo1.getText();
            }
            if(cantArch == 2) 
            {
                if(jTextFieldArchivo1.getText().isEmpty())
                {
                    mostrarMensaje(AvadsMain.propC.getProperty("igu.examinarArchivos.mensaje.eleccion.archivo"));
                    return;
                }
                if(jTextFieldArchivo2.getText().isEmpty()) 
                {
                    mostrarMensaje(AvadsMain.propC.getProperty("igu.examinarArchivos.mensaje.eleccion.archivo"));
                    return;
                }
                archivosAnalizar[0] = jTextFieldArchivo1.getText();
                archivosAnalizar[1] = jTextFieldArchivo2.getText();
            }
            if(cantArch == 3) 
            {
                if(jTextFieldArchivo1.getText().isEmpty())
                {
                    mostrarMensaje(AvadsMain.propC.getProperty("igu.examinarArchivos.mensaje.eleccion.archivo"));
                    return;
                }
                if(jTextFieldArchivo2.getText().isEmpty()) 
                {
                    mostrarMensaje(AvadsMain.propC.getProperty("igu.examinarArchivos.mensaje.eleccion.archivo"));
                    return;
                }
                if(jTextFieldArchivo3.getText().isEmpty()) 
                {
                    mostrarMensaje(AvadsMain.propC.getProperty("igu.examinarArchivos.mensaje.eleccion.archivo"));
                    return;
                } 
                archivosAnalizar[0] = jTextFieldArchivo1.getText();
                archivosAnalizar[1] = jTextFieldArchivo2.getText();
                archivosAnalizar[2] = jTextFieldArchivo3.getText();
            }             
            if(cantArch == 4) 
            {
                if(jTextFieldArchivo1.getText().isEmpty())
                {
                    mostrarMensaje(AvadsMain.propC.getProperty("igu.examinarArchivos.mensaje.eleccion.archivo"));
                    return;
                }
                if(jTextFieldArchivo2.getText().isEmpty()) 
                {
                    mostrarMensaje(AvadsMain.propC.getProperty("igu.examinarArchivos.mensaje.eleccion.archivo"));
                    return;
                }
                if(jTextFieldArchivo3.getText().isEmpty()) 
                {
                    mostrarMensaje(AvadsMain.propC.getProperty("igu.examinarArchivos.mensaje.eleccion.archivo"));
                    return;
                } 
                if(jTextFieldArchivo4.getText().isEmpty()) 
                {
                    mostrarMensaje(AvadsMain.propC.getProperty("igu.examinarArchivos.mensaje.eleccion.archivo"));
                    return;
                }
                archivosAnalizar[0] = jTextFieldArchivo1.getText();
                archivosAnalizar[1] = jTextFieldArchivo2.getText();
                archivosAnalizar[2] = jTextFieldArchivo3.getText();
                archivosAnalizar[3] = jTextFieldArchivo4.getText();
            }              
        }
        else return;

        mostrarMensaje(AvadsMain.propC.getProperty("igu.examinarArchivos.mensaje.espera"));
        
        TaskRunController taskA = new TaskRunController(archivosAnalizar);
        taskA.start();
        
        try {
            Thread.sleep((Long.parseLong(AvadsMain.propC.getProperty("igu.examinarArchivos.mensaje.espera.tiempo")))*1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(VentanaExaminarArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.dispose();
        vEstadoArchivos.setLocationRelativeTo(null);
        vEstadoArchivos.setVisible(true);
        vEstadoArchivos.requestFocus();  
    }//GEN-LAST:event_jButtonEjecutarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSeleccionArchivo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionArchivo1ActionPerformed
    int s = jFileChooserArchivo.showOpenDialog(this);
    if(s == jFileChooserArchivo.APPROVE_OPTION) 
    {
        fileControler.setFileAnalizar(jFileChooserArchivo.getSelectedFile());
        if(fileControler.esEjecutable())
        {
            if(fileControler.isSizeAccepted()) jTextFieldArchivo1.setText(jFileChooserArchivo.getSelectedFile().getAbsolutePath());
            else mostrarMensaje(AvadsMain.propC.getProperty("igu.examinarArchivos.mensaje.tamanoArchivo")+AvadsMain.propC.getProperty("igu.examinarArchivos.revision.tamano")+" MB");
        }
        else mostrarMensaje(AvadsMain.propC.getProperty("igu.examinarArchivos.mensaje.archivoNoEjecutable"));
        
    }
  
    }//GEN-LAST:event_jButtonSeleccionArchivo1ActionPerformed

    private void jButtonSeleccionArchivo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionArchivo2ActionPerformed
    int s = jFileChooserArchivo.showOpenDialog(this);
    if(s == jFileChooserArchivo.APPROVE_OPTION) 
    {
        fileControler.setFileAnalizar(jFileChooserArchivo.getSelectedFile());
        if(fileControler.esEjecutable())jTextFieldArchivo2.setText(jFileChooserArchivo.getSelectedFile().getAbsolutePath());
        else mostrarMensaje(AvadsMain.propC.getProperty("igu.examinarArchivos.mensaje.archivoNoEjecutable"));
    }
    }//GEN-LAST:event_jButtonSeleccionArchivo2ActionPerformed

    private void jButtonSeleccionArchivo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionArchivo3ActionPerformed
    int s = jFileChooserArchivo.showOpenDialog(this);
    if(s == jFileChooserArchivo.APPROVE_OPTION) 
    {
        fileControler.setFileAnalizar(jFileChooserArchivo.getSelectedFile());
        if(fileControler.esEjecutable())jTextFieldArchivo3.setText(jFileChooserArchivo.getSelectedFile().getAbsolutePath());
        else mostrarMensaje(AvadsMain.propC.getProperty("igu.examinarArchivos.mensaje.archivoNoEjecutable"));
    }
    }//GEN-LAST:event_jButtonSeleccionArchivo3ActionPerformed

    private void jButtonSeleccionArchivo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionArchivo4ActionPerformed
    int s = jFileChooserArchivo.showOpenDialog(this);
    if(s == jFileChooserArchivo.APPROVE_OPTION) 
    {
        fileControler.setFileAnalizar(jFileChooserArchivo.getSelectedFile());
        if(fileControler.esEjecutable())jTextFieldArchivo4.setText(jFileChooserArchivo.getSelectedFile().getAbsolutePath());
        else mostrarMensaje(AvadsMain.propC.getProperty("igu.examinarArchivos.mensaje.archivoNoEjecutable"));
    }
    }//GEN-LAST:event_jButtonSeleccionArchivo4ActionPerformed

    private void mostrarMensaje(String mensaje)
    {
        JOptionPane.showMessageDialog(this, mensaje, AvadsMain.propC.getProperty("ventana.dialogo.atencion"), JOptionPane.INFORMATION_MESSAGE);
    }  
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupGuardarReporte;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEjecutar;
    private javax.swing.JButton jButtonSeleccionArchivo1;
    private javax.swing.JButton jButtonSeleccionArchivo2;
    private javax.swing.JButton jButtonSeleccionArchivo3;
    private javax.swing.JButton jButtonSeleccionArchivo4;
    private javax.swing.JComboBox<String> jComboBoxCantidadArchivos;
    private javax.swing.JFileChooser jFileChooserArchivo;
    private javax.swing.JLabel jLabelCantidadArchivos;
    private javax.swing.JLabel jLabelGuardarReporte;
    private javax.swing.JPanel jPanelArchivosExaminar;
    private javax.swing.JPanel jPanelParametrosGenerales;
    private javax.swing.JRadioButton jRadioButtonNO;
    private javax.swing.JRadioButton jRadioButtonSI;
    private javax.swing.JTextField jTextFieldArchivo1;
    private javax.swing.JTextField jTextFieldArchivo2;
    private javax.swing.JTextField jTextFieldArchivo3;
    private javax.swing.JTextField jTextFieldArchivo4;
    // End of variables declaration//GEN-END:variables
}
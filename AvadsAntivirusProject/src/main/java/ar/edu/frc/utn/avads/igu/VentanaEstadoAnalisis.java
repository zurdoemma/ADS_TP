/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.frc.utn.avads.igu;

import ar.edu.frc.utn.avads.main.AvadsMain;
import ar.edu.frc.utn.avads.model.AnalisisArchivo;
import ar.edu.frc.utn.avads.model.ExaminarArchivo;
import ar.edu.frc.utn.avads.report.JasperReportAvads;
import ar.edu.frc.utn.avads.util.AvadsUtil;
import java.awt.Image;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Emmanuel
 */
public class VentanaEstadoAnalisis extends javax.swing.JFrame {

    /**
     * Creates new form VentanaEstadoAnalisis
     */
    private static DefaultTableModel estadoArchivosModel = new DefaultTableModel();
    
    public VentanaEstadoAnalisis() {
        initComponents();
        
        Image icono = AvadsUtil.getImageByPath(AvadsMain.propC.getProperty("icono.system.image"));
        this.setIconImage(icono);
        
        this.setTitle(AvadsMain.propC.getProperty("igu.estadoArchivos.title"));
        
        jPanelEstadoArchivos.setBorder(javax.swing.BorderFactory.createTitledBorder(AvadsMain.propC.getProperty("igu.estadoArchivos.panelEstadoArchivos.title")));
        jPanelFiltroEstadoArchivos.setBorder(javax.swing.BorderFactory.createTitledBorder(AvadsMain.propC.getProperty("igu.estadoArchivos.panelFiltroEstadoArchivos.title")));
        
        jLabelFecha.setText(AvadsMain.propC.getProperty("igu.estadoArchivos.fecha"));
        jButtonVer.setText(AvadsMain.propC.getProperty("igu.estadoArchivos.boton.ver"));
        
        jButtonVerReporte.setIcon(AvadsUtil.getImageIconByPath(AvadsMain.propC.getProperty("igu.estadoArchivos.imagen.boton.ver.reporte.analisis.archivo")));
        jButtonVerReporte.setToolTipText(AvadsMain.propC.getProperty("igu.estadoArchivos.boton.ver.estadoArchivos"));
        
        jTableEstadoArchivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                AvadsMain.propC.getProperty("igu.estadoArchivos.columna.fecha"), AvadsMain.propC.getProperty("igu.estadoArchivos.columna.idProceso"), AvadsMain.propC.getProperty("igu.estadoArchivos.columna.archivosEnProceso"), AvadsMain.propC.getProperty("igu.estadoArchivos.columna.estado")
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Long.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        }); 
        
        estadoArchivosModel = (DefaultTableModel) jTableEstadoArchivos.getModel();  
        jTableEstadoArchivos.setSelectionMode(jTableEstadoArchivos.getSelectionModel().SINGLE_SELECTION);
        
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        
        jTableEstadoArchivos.getColumnModel().getColumn(0).setPreferredWidth(80);
        jTableEstadoArchivos.getColumnModel().getColumn(1).setPreferredWidth(60);
        jTableEstadoArchivos.getColumnModel().getColumn(2).setPreferredWidth(250);        
        jTableEstadoArchivos.getColumnModel().getColumn(3).setPreferredWidth(90);

        
        jTableEstadoArchivos.getColumnModel().getColumn(0).setCellRenderer(tcr);
        jTableEstadoArchivos.getColumnModel().getColumn(1).setCellRenderer(tcr); 
        jTableEstadoArchivos.getColumnModel().getColumn(2).setCellRenderer(tcr);
        jTableEstadoArchivos.getColumnModel().getColumn(3).setCellRenderer(tcr); 
        
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(estadoArchivosModel);
        jTableEstadoArchivos.setRowSorter(elQueOrdena);      
        
        jXDatePickerFechaEjecucion.setFormats(AvadsMain.propC.getProperty("igu.estadoArchivos.fecha.formato"));
        
        List<String> examArch = AvadsMain.serviceDB.getAllCollection("Examinar_Archivo");

        for(String exA : examArch)
        {
            ExaminarArchivo exaAr = (ExaminarArchivo) AvadsUtil.gson.fromJson(exA, ExaminarArchivo.class);
            List<String> analArch = AvadsMain.serviceDB.findObjectCollection("Analisis_Archivo","idProceso",exaAr.getId());
            for(String anA : analArch)
            {
                AnalisisArchivo analiArch = (AnalisisArchivo) AvadsUtil.gson.fromJson(anA, AnalisisArchivo.class);
                agregarAnalisisArchivo(analiArch);
            }
        }        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelFiltroEstadoArchivos = new javax.swing.JPanel();
        jLabelFecha = new javax.swing.JLabel();
        jXDatePickerFechaEjecucion = new org.jdesktop.swingx.JXDatePicker();
        jButtonVer = new javax.swing.JButton();
        jPanelEstadoArchivos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEstadoArchivos = new javax.swing.JTable();
        jButtonVerReporte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanelFiltroEstadoArchivos.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro Estado Archivos"));

        jLabelFecha.setText("Fecha:");

        jButtonVer.setText("Ver");

        javax.swing.GroupLayout jPanelFiltroEstadoArchivosLayout = new javax.swing.GroupLayout(jPanelFiltroEstadoArchivos);
        jPanelFiltroEstadoArchivos.setLayout(jPanelFiltroEstadoArchivosLayout);
        jPanelFiltroEstadoArchivosLayout.setHorizontalGroup(
            jPanelFiltroEstadoArchivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFiltroEstadoArchivosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelFecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXDatePickerFechaEjecucion, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonVer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelFiltroEstadoArchivosLayout.setVerticalGroup(
            jPanelFiltroEstadoArchivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFiltroEstadoArchivosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFiltroEstadoArchivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFecha)
                    .addComponent(jXDatePickerFechaEjecucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVer))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanelEstadoArchivos.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado Archivos"));

        jTableEstadoArchivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "ID Proceso", "Archivos en Proceso", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Long.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableEstadoArchivos);

        jButtonVerReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelEstadoArchivosLayout = new javax.swing.GroupLayout(jPanelEstadoArchivos);
        jPanelEstadoArchivos.setLayout(jPanelEstadoArchivosLayout);
        jPanelEstadoArchivosLayout.setHorizontalGroup(
            jPanelEstadoArchivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEstadoArchivosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonVerReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelEstadoArchivosLayout.setVerticalGroup(
            jPanelEstadoArchivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEstadoArchivosLayout.createSequentialGroup()
                .addGroup(jPanelEstadoArchivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVerReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelFiltroEstadoArchivos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelEstadoArchivos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelFiltroEstadoArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelEstadoArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVerReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerReporteActionPerformed
        
    if(jTableEstadoArchivos.getRowCount() > 0)
    {    
        if(jTableEstadoArchivos.getSelectedRow() != -1)
        {
            if(jTableEstadoArchivos.getValueAt(jTableEstadoArchivos.getSelectedRow(), 3).toString().trim().compareTo("Finalizado") == 0)
            {
                String idProceso = jTableEstadoArchivos.getValueAt(jTableEstadoArchivos.getSelectedRow(), 1).toString().trim();
                String archivo = jTableEstadoArchivos.getValueAt(jTableEstadoArchivos.getSelectedRow(), 2).toString().trim();
                
                JasperReportAvads mostrarReporte = new JasperReportAvads();
                mostrarReporte.mostrarReporte(Long.parseLong(idProceso), archivo);
            }
            else mostrarMensaje(AvadsMain.propC.getProperty("igu.estadoArchivos.tabla.estado.archivo"));
        }
        else mostrarMensaje(AvadsMain.propC.getProperty("igu.estadoArchivos.tabla.seleccion.archivo"));
    }
    else mostrarMensaje(AvadsMain.propC.getProperty("igu.estadoArchivos.tabla.sinArchivos"));
        
            
    }//GEN-LAST:event_jButtonVerReporteActionPerformed

    public void agregarAnalisisArchivo(AnalisisArchivo anA)
    {        
        int filaE = -1;
        for(int i=0; i<jTableEstadoArchivos.getRowCount();i++)
        {
            if(anA.getIdProceso() == Integer.parseInt(jTableEstadoArchivos.getValueAt(i, 1).toString()))
            {
                if(anA.getArchivo().compareTo(jTableEstadoArchivos.getValueAt(i, 2).toString()) == 0) 
                {
                    filaE = i;
                    break;
                }
            }
        }
        
        if(filaE == -1)
        {
            if (estadoArchivosModel != null)
            {
                Object[] datosAnalisisArchivo = new Object[4];

                datosAnalisisArchivo[0] = new String(AvadsUtil.getDate(anA.getFechaEnvio())+" "+AvadsUtil.getHour(anA.getFechaEnvio()));
                datosAnalisisArchivo[1] = new Long(anA.getIdProceso());
                datosAnalisisArchivo[2] = new String(anA.getArchivo());            
                datosAnalisisArchivo[3] = new String(anA.getEstado());

                estadoArchivosModel.addRow(datosAnalisisArchivo);
            }
        }
        else
        {
            jTableEstadoArchivos.setValueAt(anA.getEstado(), filaE, 3);
        }
    }  
    
    private void mostrarMensaje(String mensaje)
    {
        JOptionPane.showMessageDialog(this, mensaje, AvadsMain.propC.getProperty("ventana.dialogo.atencion"), JOptionPane.INFORMATION_MESSAGE);
    }     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonVer;
    private javax.swing.JButton jButtonVerReporte;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JPanel jPanelEstadoArchivos;
    private javax.swing.JPanel jPanelFiltroEstadoArchivos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEstadoArchivos;
    private org.jdesktop.swingx.JXDatePicker jXDatePickerFechaEjecucion;
    // End of variables declaration//GEN-END:variables
}

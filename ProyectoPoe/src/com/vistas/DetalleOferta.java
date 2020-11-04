
package com.vistas;

import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DetalleOferta extends javax.swing.JFrame {

    String []columnas ={"CODIGO","NOMBRE_EMPRESA","DESCRIPCION","FOTO URL", "ID USUARIO"};
    DefaultTableModel modelo = new DefaultTableModel(columnas,0);

   
    
    public DetalleOferta() {
        initComponents();
        //cargar();
        super.setExtendedState(Frame.MAXIMIZED_BOTH);
        cerrarVentana();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTDatos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        txtNombre1 = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombre2 = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        txtCodigo2 = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        txtCodigo3 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        txtUsuarioid = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnFoto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(49, 57, 69));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Detalle Oferta");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTDatos.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jTDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTDatos.setToolTipText("Para ver el detalle completo, seleccione una empresa y presione Enter.");
        jTDatos.setRowHeight(25);
        jTDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTDatosMouseClicked(evt);
            }
        });
        jTDatos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Enter(evt);
            }
        });
        jScrollPane1.setViewportView(jTDatos);

        jPanel1.setBackground(new java.awt.Color(49, 57, 69));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Inicio");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Nombre usuario");

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Cerrar sesión");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator3.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 145, 343, 10));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel4.setText("Unidad De Medida");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 116, -1, -1));

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel6.setText("ID");
        jLabel6.setToolTipText("");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 16, -1, -1));

        txtId.setBackground(new java.awt.Color(233, 235, 237));
        txtId.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtId.setBorder(null);
        jPanel3.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 11, 230, 28));

        jSeparator8.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 340, 10));

        txtNombre1.setBackground(new java.awt.Color(233, 235, 237));
        txtNombre1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtNombre1.setBorder(null);
        jPanel3.add(txtNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 61, 230, 28));

        jSeparator9.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 95, 343, 10));

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel8.setText("Cantidad");
        jLabel8.setToolTipText("");
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 66, -1, -1));

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel7.setText("Nombre");
        jLabel7.setToolTipText("");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 16, -1, -1));

        txtNombre2.setBackground(new java.awt.Color(233, 235, 237));
        txtNombre2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtNombre2.setBorder(null);
        jPanel3.add(txtNombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 290, 28));

        jSeparator11.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 45, 370, 10));

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel9.setText("Descripción");
        jLabel9.setToolTipText("");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 66, -1, -1));

        jSeparator4.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 370, 10));

        jSeparator5.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 343, 10));

        jLabel14.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel14.setText("Total Impuesto");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        txtCodigo2.setBackground(new java.awt.Color(233, 235, 237));
        txtCodigo2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtCodigo2.setBorder(null);
        jPanel3.add(txtCodigo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 160, 230, 28));

        jSeparator6.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 200, 370, 10));

        jLabel15.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel15.setText("Total Compra");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, -1, -1));

        txtCodigo3.setBackground(new java.awt.Color(233, 235, 237));
        txtCodigo3.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtCodigo3.setBorder(null);
        jPanel3.add(txtCodigo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 260, 28));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jPanel3.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 106, 210, 30));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 290, 70));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel12.setText("Buscar ID");
        jLabel12.setToolTipText("");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jSeparator10.setForeground(new java.awt.Color(49, 57, 69));
        jPanel4.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 445, 10));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_add_48px.png"))); // NOI18N
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_refresh_48px.png"))); // NOI18N
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, -1, -1));

        txtBuscar.setBackground(new java.awt.Color(233, 235, 237));
        txtBuscar.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtBuscar.setBorder(null);
        jPanel4.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 278, -1));

        btnBuscar.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_search_24px.png"))); // NOI18N
        btnBuscar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(49, 57, 69), 1, true));
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel4.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 66, 26));

        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel13.setText("####/##/##");
        jLabel13.setToolTipText("");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 30, -1, -1));

        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel16.setText("Fecha De Oferta");
        jLabel16.setToolTipText("");
        jLabel16.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, -1, -1));

        btnFoto.setText("Foto");
        btnFoto.setBorderPainted(false);
        btnFoto.setContentAreaFilled(false);
        btnFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFotoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 797, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1)))
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        /*this.modelo.setRowCount(0);
        emp = pro.seleccionarEmpresa(Integer.parseInt(txtBuscar.getText()));
        this.modelo.setRowCount(0);
        Object [] obj = new Object[5];
        obj[0] = emp.getId_empresa();
        obj[1] = emp.getNombre_empresa();
        obj[2] = emp.getDescripcion();
        obj[3] = emp.getUsuario().getFoto();
        obj[4] = emp.getUsuario().getId_usuario();
        this.modelo.addRow(obj);
        this.jTDatos.setModel(modelo);*/
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFotoActionPerformed

    }//GEN-LAST:event_btnFotoActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        int input = JOptionPane.showConfirmDialog(rootPane, "Desea cerrar sesión?", "Salir", JOptionPane.YES_NO_OPTION);
        if(input == 0){
            super.dispose();
            try {
                new Login().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(DetalleOferta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        super.dispose();
        //new home().setVisible(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void Enter(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Enter

        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            int row = jTDatos.getSelectedRow();
            Integer id_empresa=(Integer)jTDatos.getValueAt(row, 0);

            //new Detalle_Empresa(id_empresa).setVisible(true);

            super.dispose();
        }
    }//GEN-LAST:event_Enter

    private void jTDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTDatosMouseClicked
        int row = jTDatos.getSelectedRow();

        if (row != -1) {
           // txtCodigo.setText(jTDatos.getValueAt(row, 0).toString());
            txtId.setText((String)jTDatos.getValueAt(row, 1));
            //txtDescripcion.setText((String) jTDatos.getValueAt(row, 2));
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(jTDatos.getValueAt(row, 3).toString()).getImage().getScaledInstance(190, 190, Image.SCALE_DEFAULT));
            btnFoto.setText("");
            btnFoto.setIcon(imageIcon);

        } else {
            JOptionPane.showMessageDialog(this,"No hay filas por mostrar");
        }
    }//GEN-LAST:event_jTDatosMouseClicked

     public void cerrarVentana(){
        super.setVisible(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int input = JOptionPane.showConfirmDialog(rootPane, "Desea salir de la apicación?", "Salir", JOptionPane.YES_NO_OPTION);
                if(input == 0){
                    System.exit(0);
                }
            }
        });
    }
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DetalleOferta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetalleOferta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetalleOferta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetalleOferta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetalleOferta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnFoto;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTDatos;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo2;
    private javax.swing.JTextField txtCodigo3;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtNombre2;
    private javax.swing.JLabel txtUsuarioid;
    // End of variables declaration//GEN-END:variables
}

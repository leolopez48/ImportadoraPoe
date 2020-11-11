
package com.vistas;

import com.dao.DaoDetalleOferta;
import com.dao.DaoImpuestos;
import com.dao.DaoUsuario;
import com.dao.DaoVehiculo;
import com.pojos.DetalleOferta;
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

public class DetalleOfertas extends javax.swing.JInternalFrame {

    DaoDetalleOferta daoDO = new DaoDetalleOferta();
    DaoUsuario daoU = new DaoUsuario();
    DaoVehiculo daoV = new DaoVehiculo();
    DaoImpuestos daoI = new DaoImpuestos();
    DetalleOferta deo;
    
    public DetalleOfertas() {
        initComponents();
    }
     public void mostrar(List<DetalleOferta> lista) {
        DefaultTableModel tabla;
        String []columnas ={"ID Detalle","Nombre Usuario","ID Vehiculo", "Cantidad", "Fecha Oferta"};
        tabla = new DefaultTableModel(null, columnas);
        Object datos[] = new Object[8];

        try {
            for (int i = 0; i < lista.size(); i++) {
                deo = (DetalleOferta) lista.get(i);
                datos[0] = deo.getId();
                datos[1] = deo.getUsuario().getNombreUsuario();
                datos[2] = deo.getVehiculo().getIdVehiculo();
                datos[3] = deo.getCantidad();
                datos[4] = deo.getFechaOferta();
                
                tabla.addRow(datos);
            }
            this.tablaDetalleOferta.setModel(tabla);
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error al mostrar en formulario " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDetalleOferta = new javax.swing.JTable();
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
        jSeparator5 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        txtCodigo2 = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        txtId1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtNombre3 = new javax.swing.JTextField();
        txtUsuarioid = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnRefrescar = new javax.swing.JLabel();
        btnInsertar = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JLabel();
        btnModificar = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(49, 57, 69));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Detalle Oferta");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tablaDetalleOferta.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        tablaDetalleOferta.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaDetalleOferta.setToolTipText("Para ver el detalle completo, seleccione una empresa y presione Enter.");
        tablaDetalleOferta.setRowHeight(25);
        tablaDetalleOferta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDetalleOfertaMouseClicked(evt);
            }
        });
        tablaDetalleOferta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Enter(evt);
            }
        });
        jScrollPane1.setViewportView(tablaDetalleOferta);

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
        jLabel4.setText("Total impuesto");
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
        jPanel3.add(txtNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 230, 28));

        jSeparator9.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 95, 343, 10));

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel8.setText("ID vehiculo");
        jLabel8.setToolTipText("");
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 66, -1, -1));

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel7.setText("Nombre usuario");
        jLabel7.setToolTipText("");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 16, -1, -1));

        txtNombre2.setBackground(new java.awt.Color(233, 235, 237));
        txtNombre2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtNombre2.setBorder(null);
        jPanel3.add(txtNombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 290, 28));

        jSeparator11.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 45, 400, 10));

        jSeparator5.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 343, 10));

        jLabel14.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel14.setText("Total Compra");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, -1, -1));

        txtCodigo2.setBackground(new java.awt.Color(233, 235, 237));
        txtCodigo2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtCodigo2.setBorder(null);
        jPanel3.add(txtCodigo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 230, 28));

        jSeparator12.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 400, 10));

        txtId1.setBackground(new java.awt.Color(233, 235, 237));
        txtId1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtId1.setBorder(null);
        jPanel3.add(txtId1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 290, 28));

        jLabel17.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel17.setText("Cantidad");
        jLabel17.setToolTipText("");
        jLabel17.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 100, -1));

        txtNombre3.setBackground(new java.awt.Color(233, 235, 237));
        txtNombre3.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtNombre3.setBorder(null);
        jPanel3.add(txtNombre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 61, 230, 28));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel12.setText("Buscar ID");
        jLabel12.setToolTipText("");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jSeparator10.setForeground(new java.awt.Color(49, 57, 69));
        jPanel4.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 445, 10));

        txtBuscar.setBackground(new java.awt.Color(233, 235, 237));
        txtBuscar.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtBuscar.setBorder(null);
        jPanel4.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 278, -1));

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
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, -1, -1));

        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel16.setText("Fecha De Oferta");
        jLabel16.setToolTipText("");
        jLabel16.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 0, -1, -1));

        btnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_refresh_36px.png"))); // NOI18N
        btnRefrescar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefrescarMouseClicked(evt);
            }
        });
        jPanel4.add(btnRefrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, -1, -1));

        btnInsertar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_add_36px.png"))); // NOI18N
        btnInsertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInsertarMouseClicked(evt);
            }
        });
        jPanel4.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, -1, -1));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_delete_bin_36px.png"))); // NOI18N
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });
        jPanel4.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, -1, -1));

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_save_36px_1.png"))); // NOI18N
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarMouseClicked(evt);
            }
        });
        jPanel4.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, -1, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 120, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
       mostrar(daoDO.buscarDetalleOferta(txtBuscar.getText()));
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        int input = JOptionPane.showConfirmDialog(rootPane, "Desea cerrar sesión?", "Salir", JOptionPane.YES_NO_OPTION);
        if(input == 0){
            super.dispose();
            try {
                new Login().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(DetalleOfertas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        
    }//GEN-LAST:event_jLabel3MouseClicked

    private void Enter(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Enter

    }//GEN-LAST:event_Enter

    private void tablaDetalleOfertaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDetalleOfertaMouseClicked
        //llenarTabla();
    }//GEN-LAST:event_tablaDetalleOfertaMouseClicked

    private void btnRefrescarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefrescarMouseClicked
        mostrar(daoDO.mostrarDetalleOferta());
        //limpiar();
    }//GEN-LAST:event_btnRefrescarMouseClicked

    private void btnInsertarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertarMouseClicked
        //insertar();
    }//GEN-LAST:event_btnInsertarMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        //eliminar();
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
        //modificar();
    }//GEN-LAST:event_btnModificarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel btnEliminar;
    private javax.swing.JLabel btnInsertar;
    private javax.swing.JLabel btnModificar;
    private javax.swing.JLabel btnRefrescar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable tablaDetalleOferta;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo2;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtId1;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtNombre2;
    private javax.swing.JTextField txtNombre3;
    private javax.swing.JLabel txtUsuarioid;
    // End of variables declaration//GEN-END:variables
}

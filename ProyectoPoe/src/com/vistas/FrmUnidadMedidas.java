
package com.vistas;

import Dao.DaoCategoria;
import com.dao.DaoUnidadMedida;
import com.pojos.Categoria;
import com.pojos.UnidadMedida;
import com.utils.ValidarCampos;
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

public class FrmUnidadMedidas extends javax.swing.JInternalFrame {
     
    DaoUnidadMedida daoUM = new DaoUnidadMedida();
    UnidadMedida um;
    ValidarCampos vc=new ValidarCampos();
    
    public FrmUnidadMedidas() {
        initComponents();
        mostrar(daoUM.mostrarUnidadMedidas());
        setId();
    }
    
    public void mostrar(List<UnidadMedida> lista) {
        DefaultTableModel tabla;
        String []columnas ={"ID Unidad de Medida","Nombre Unidad de Medida"};
        tabla = new DefaultTableModel(null, columnas);
        Object datos[] = new Object[2];

        try {
            for (int i = 0; i < lista.size(); i++) {
                um = (UnidadMedida) lista.get(i);
                datos[0] = um.getIdUnidadMedida();
                datos[1] = um.getNombre();
                
                tabla.addRow(datos);
            }
            this.tablaUnidadM.setModel(tabla);
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error al mostrar en formulario " + e.getMessage());
        }
    }
    
    public void insertar() {
        try {
            um.setIdUnidadMedida(Integer.parseInt(this.txtIdUM.getText()));
            um.setNombre(this.txtNombreUM.getText());
            
            daoUM.insertarUnidadMedida(um);
            JOptionPane.showMessageDialog(null, "Insertado correctamente");
            mostrar(daoUM.mostrarUnidadMedidas());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar" + e.getMessage());
        }
    }

    public void limpiar() {
        this.txtIdUM.setText("");
        this.txtNombreUM.setText("");
        this.txtBuscarNombreCat.setText("");
        this.txtIdUM.setEnabled(false);
        setId();
    }

    public void llenarTabla() {
        int fila = this.tablaUnidadM.getSelectedRow();
        if (fila > -1) {
            this.txtIdUM.setText(String.valueOf(this.tablaUnidadM.getValueAt(fila, 0)));
            this.txtNombreUM.setText(String.valueOf(this.tablaUnidadM.getValueAt(fila, 1)));
            this.txtIdUM.setEnabled(false);
        }
    }
    
    public void setId()
    {
        txtIdUM.setText(String.valueOf(daoUM.ultimoId()));
    }

    public void modificar() {
        try {
            um.setIdUnidadMedida(Integer.parseInt(this.txtIdUM.getText()));
            um.setNombre(this.txtNombreUM.getText()); 
            
            int respuesta = JOptionPane.showConfirmDialog(this, "Desea modificar la unidad de medida",
                    "Modificar", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.OK_OPTION) {
                daoUM.modificarUnidadMedida(um);
                JOptionPane.showMessageDialog(null, "Datos modificados con exito");
                mostrar(daoUM.mostrarUnidadMedidas());
                limpiar();
            } else {
                mostrar(daoUM.mostrarUnidadMedidas());
                limpiar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar en formulario");
        }
    }
    
    public void eliminar(){
        try {
            um.setIdUnidadMedida(Integer.parseInt(this.txtIdUM.getText()));
            int respuesta = JOptionPane.showConfirmDialog(this, "Desea eliminar la unidad de medida",
                    "Eliminar", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.OK_OPTION) {
                daoUM.eliminarUnidadMedida(um);
                JOptionPane.showMessageDialog(null, "Datos eliminados con exito");
                mostrar(daoUM.mostrarUnidadMedidas());
                limpiar();
            } else {
                mostrar(daoUM.mostrarUnidadMedidas());
                limpiar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar en formulario");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUnidadM = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtIdUM = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        txtNombreUM = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        txtUsuarioid = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        txtBuscarNombreCat = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnRefrescar = new javax.swing.JLabel();
        btnInsertar = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JLabel();
        btnModificar = new javax.swing.JLabel();

        setBackground(null);
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
        jLabel2.setText("Unidad de Medida");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tablaUnidadM.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        tablaUnidadM.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaUnidadM.setToolTipText("Para ver el detalle completo, seleccione una empresa y presione Enter.");
        tablaUnidadM.setRowHeight(25);
        tablaUnidadM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUnidadMMouseClicked(evt);
            }
        });
        tablaUnidadM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Enter(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUnidadM);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel6.setText("ID");
        jLabel6.setToolTipText("");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, -1));

        txtIdUM.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtIdUM.setBorder(null);
        jPanel3.add(txtIdUM, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 18, 130, 30));

        jSeparator8.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 170, 10));

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel7.setText("Nombre");
        jLabel7.setToolTipText("");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, -1));

        txtNombreUM.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtNombreUM.setBorder(null);
        txtNombreUM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreUMKeyTyped(evt);
            }
        });
        jPanel3.add(txtNombreUM, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 220, 28));

        jSeparator11.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 280, 10));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel12.setText("Buscar nombre");
        jLabel12.setToolTipText("");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, -1, -1));

        jSeparator10.setForeground(new java.awt.Color(49, 57, 69));
        jPanel4.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 400, 10));

        txtBuscarNombreCat.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtBuscarNombreCat.setBorder(null);
        txtBuscarNombreCat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarNombreCatKeyTyped(evt);
            }
        });
        jPanel4.add(txtBuscarNombreCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 250, -1));

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
        jPanel4.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 0, 66, 26));

        btnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_refresh_36px.png"))); // NOI18N
        btnRefrescar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefrescarMouseClicked(evt);
            }
        });
        jPanel4.add(btnRefrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 0, -1, -1));

        btnInsertar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_add_36px.png"))); // NOI18N
        btnInsertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInsertarMouseClicked(evt);
            }
        });
        jPanel4.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, -1, -1));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_delete_bin_36px.png"))); // NOI18N
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });
        jPanel4.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 0, -1, -1));

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_edit_36px.png"))); // NOI18N
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarMouseClicked(evt);
            }
        });
        jPanel4.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 0, -1, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 932, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 896, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        mostrar(daoUM.buscarUnidadMedida(txtBuscarNombreCat.getText()));
        this.txtIdUM.setText("");
        this.txtNombreUM.setText("");
        this.txtIdUM.setEnabled(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void Enter(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Enter

    }//GEN-LAST:event_Enter

    private void tablaUnidadMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUnidadMMouseClicked
        llenarTabla();
    }//GEN-LAST:event_tablaUnidadMMouseClicked

    private void btnRefrescarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefrescarMouseClicked
        mostrar(daoUM.mostrarUnidadMedidas());
        limpiar();
    }//GEN-LAST:event_btnRefrescarMouseClicked

    private void btnInsertarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertarMouseClicked
        insertar();
    }//GEN-LAST:event_btnInsertarMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        eliminar();
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
        modificar();
    }//GEN-LAST:event_btnModificarMouseClicked

    private void txtNombreUMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreUMKeyTyped
        vc.wordsOnly(evt);
    }//GEN-LAST:event_txtNombreUMKeyTyped

    private void txtBuscarNombreCatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarNombreCatKeyTyped
        vc.wordsOnly(evt);
    }//GEN-LAST:event_txtBuscarNombreCatKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel btnEliminar;
    private javax.swing.JLabel btnInsertar;
    private javax.swing.JLabel btnModificar;
    private javax.swing.JLabel btnRefrescar;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTable tablaUnidadM;
    private javax.swing.JTextField txtBuscarNombreCat;
    private javax.swing.JTextField txtIdUM;
    private javax.swing.JTextField txtNombreUM;
    private javax.swing.JLabel txtUsuarioid;
    // End of variables declaration//GEN-END:variables
}

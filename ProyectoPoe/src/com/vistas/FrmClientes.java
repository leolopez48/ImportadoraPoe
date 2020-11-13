
package com.vistas;

import com.dao.DaoCliente;
import com.dao.DaoUsuario;
import com.pojos.Cliente;
import com.pojos.Usuario;
import com.utils.ComboItem;
import com.utils.ValidarCampos;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperReport;

/**
 * NombreClase: FrmClientes
 * Fecha: 13/11/2020 
 * Versión: 1.0 
 * Copyright:ITCA-FEPADE
 * @author Leonel Antonio López Valencia - 040119 
 * Roberto Alejandro Armijo Jímenez - 046719 
 * Sandra Natalia Menjívar Romero - 174218
 */

public class FrmClientes extends javax.swing.JInternalFrame {
    DaoCliente daoC = new DaoCliente();
    Cliente cli = new Cliente();
    DaoUsuario daoU = new DaoUsuario();
    JasperReport reporte;
    ValidarCampos vc=new ValidarCampos();
    
    public FrmClientes() {
        initComponents();
        mostrar(daoC.mostrarClientes());
        cargarCombo(comboUsuario, daoU.mostrarUsuarios());
    }
    
    public void mostrar(List<Cliente> lista) {
        DefaultTableModel tabla;
        String []columnas ={"Código","Nombre","Dirección","Teléfono", "Usuario", "Foto"};
        tabla = new DefaultTableModel(null, columnas);
        Object datos[] = new Object[7];

        try {
            for (int i = 0; i < lista.size(); i++) {
                cli = (Cliente) lista.get(i);
                datos[0] = cli.getIdCliente();
                datos[1] = cli.getNombreCliente();
                datos[2] = cli.getDireccionCliente();
                datos[3] = cli.getTelefonoCliente();
                datos[4] = cli.getUsuario().getNombreUsuario();
                datos[5] = cli.getUsuario().getFoto();
                tabla.addRow(datos);
            }
            this.tablaClientes.setModel(tabla);
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error al mostrar en formulario " + e.getMessage());
        }
    }

    private void cargarCombo(JComboBox combo, List<Usuario> list) {
        for (Usuario item : list) {
            combo.addItem(new ComboItem(item.getIdUsuario(),
                    item.getNombreUsuario()));
        }
    }

    public void insertar() {
        try {
            cli.setIdCliente(Integer.parseInt(this.txtCodigo.getText()));
            cli.setNombreCliente(this.txtNombre.getText());
            cli.setDireccionCliente(this.txtDireccion.getText());
            cli.setTelefonoCliente(this.txtTelefono.getText());
            String usuario = comboUsuario.getSelectedItem().toString();
            ComboItem item = new ComboItem();
            for (int i = 0; i < comboUsuario.getItemCount(); i++) {
                if (usuario.equals(comboUsuario.getItemAt(i).toString())) {
                    item = comboUsuario.getModel().getElementAt(i);
                }
            }
            Usuario prov = new Usuario(item.getValue());
            cli.setUsuario(prov);
            daoC.insertarCliente(cli);
            JOptionPane.showMessageDialog(null, "Insertado correctamente");
            mostrar(daoC.mostrarClientes());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar" + e.getMessage());
        }
    }

    public void limpiar() {
        this.txtCodigo.setText("");
        this.txtNombre.setText("");
        this.txtDireccion.setText("");
        this.txtTelefono.setText("");
        this.comboUsuario.setSelectedIndex(0);
        this.txtCodigo.setEnabled(true);
        this.btnFoto.setIcon(null);
    }

    public void llenarTabla() {
        int fila = this.tablaClientes.getSelectedRow();
        if (fila > -1) {
            this.txtCodigo.setText(String.valueOf(this.tablaClientes.getValueAt(fila, 0)));
            this.txtNombre.setText(String.valueOf(this.tablaClientes.getValueAt(fila, 1)));
            this.txtDireccion.setText(String.valueOf(this.tablaClientes.getValueAt(fila, 2)));
            this.txtTelefono.setText(String.valueOf(this.tablaClientes.getValueAt(fila, 3)));
            String usu = String.valueOf(this.tablaClientes.getValueAt(fila, 4));
            comboUsuario.getModel().setSelectedItem(usu);
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(tablaClientes.getValueAt(fila, 5).toString()).getImage().getScaledInstance(190, 190, Image.SCALE_DEFAULT));
            btnFoto.setText("");
            btnFoto.setIcon(imageIcon);
            this.txtCodigo.setEnabled(false);
        }
    }

    public void modificar() {
        try {
            cli.setIdCliente(Integer.parseInt(this.txtCodigo.getText()));
            cli.setNombreCliente(this.txtNombre.getText());
            cli.setNombreCliente(this.txtNombre.getText());
            cli.setDireccionCliente(this.txtDireccion.getText());
            cli.setTelefonoCliente(this.txtTelefono.getText());
            String usuario = comboUsuario.getSelectedItem().toString();
            ComboItem item = new ComboItem();
            
            //Agregar linea para combobox
            Usuario usu = new Usuario(item.getValue());
            
            for (int i = 0; i < comboUsuario.getItemCount(); i++) {
                if (usuario.equals(comboUsuario.getItemAt(i).toString())) {
                    item = comboUsuario.getModel().getElementAt(i);
                    usu.setIdUsuario(item.getValue());
                }
            }
            
            cli.setUsuario(usu);
            
            int respuesta = JOptionPane.showConfirmDialog(this, "Desea modificar el cliente?",
                    "Modificar", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.OK_OPTION) {
                daoC.modificarCliente(cli);
                JOptionPane.showMessageDialog(null, "Datos modificados con exito");
                mostrar(daoC.mostrarClientes());
                limpiar();
            } else {
                mostrar(daoC.mostrarClientes());
                limpiar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar en formulario");
        }
    }
    
    public void eliminar(){
        try {
            cli.setIdCliente(Integer.parseInt(this.txtCodigo.getText()));
            int respuesta = JOptionPane.showConfirmDialog(this, "Desea eliminar el cliente?",
                    "Eliminar", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.OK_OPTION) {
                daoC.eliminarCliente(cli);
                JOptionPane.showMessageDialog(null, "Datos eliminados con exito");
                mostrar(daoC.mostrarClientes());
                limpiar();
            } else {
                mostrar(daoC.mostrarClientes());
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
        tablaClientes = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        btnEditarUsuario = new javax.swing.JLabel();
        comboUsuario = new javax.swing.JComboBox<>();
        txtTelefono = new javax.swing.JFormattedTextField();
        txtUsuarioid = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnInsertar = new javax.swing.JLabel();
        btnModificar = new javax.swing.JLabel();
        btnRefrescar = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JLabel();
        btnFoto = new javax.swing.JButton();

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
        jLabel2.setText("Clientes");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tablaClientes.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaClientes.setToolTipText("Para ver el detalle completo, seleccione una empresa y presione Enter.");
        tablaClientes.setRowHeight(25);
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClientesMouseClicked(evt);
            }
        });
        tablaClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Enter(evt);
            }
        });
        jScrollPane1.setViewportView(tablaClientes);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator3.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 145, 343, 10));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel4.setText("ID Cliente");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 116, -1, -1));

        txtCodigo.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtCodigo.setBorder(null);
        jPanel3.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 111, 263, 28));

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel6.setText("Usuario");
        jLabel6.setToolTipText("");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 16, -1, -1));

        jSeparator8.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 340, 10));

        jSeparator9.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 95, 343, 10));

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel8.setText("Teléfono");
        jLabel8.setToolTipText("");
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 66, -1, -1));

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel7.setText("Nombre");
        jLabel7.setToolTipText("");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 16, -1, -1));

        txtNombre.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtNombre.setBorder(null);
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel3.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(427, 11, 281, 28));

        jSeparator11.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 45, 337, 10));

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel9.setText("Dirección");
        jLabel9.setToolTipText("");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 66, -1, -1));

        txtDireccion.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtDireccion.setBorder(null);
        jPanel3.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 61, 281, 28));

        jSeparator12.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 95, 346, 10));

        btnEditarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_edit_36px.png"))); // NOI18N
        btnEditarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditarUsuarioMouseClicked(evt);
            }
        });
        jPanel3.add(btnEditarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, -1, -1));

        comboUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jPanel3.add(comboUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 210, 40));

        txtTelefono.setBorder(null);
        try {
            txtTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel3.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 260, 30));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel12.setText("Buscar nombre");
        jLabel12.setToolTipText("");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jSeparator10.setForeground(new java.awt.Color(49, 57, 69));
        jPanel4.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 445, 10));

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

        btnInsertar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_add_36px.png"))); // NOI18N
        btnInsertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInsertarMouseClicked(evt);
            }
        });
        jPanel4.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, -1, -1));

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_edit_36px.png"))); // NOI18N
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarMouseClicked(evt);
            }
        });
        jPanel4.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, -1, -1));

        btnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_refresh_36px.png"))); // NOI18N
        btnRefrescar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefrescarMouseClicked(evt);
            }
        });
        jPanel4.add(btnRefrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 0, -1, -1));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_delete_bin_36px.png"))); // NOI18N
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });
        jPanel4.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, -1, -1));

        btnFoto.setText("Foto");
        btnFoto.setBorderPainted(false);
        btnFoto.setContentAreaFilled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        mostrar(daoC.buscarCliente(txtBuscar.getText()));
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void Enter(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Enter

    }//GEN-LAST:event_Enter

    private void tablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMouseClicked
        llenarTabla();
    }//GEN-LAST:event_tablaClientesMouseClicked

    private void btnRefrescarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefrescarMouseClicked
        mostrar(daoC.mostrarClientes());
        limpiar();
    }//GEN-LAST:event_btnRefrescarMouseClicked

    private void btnInsertarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertarMouseClicked
        insertar();
    }//GEN-LAST:event_btnInsertarMouseClicked

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
        modificar();
    }//GEN-LAST:event_btnModificarMouseClicked

    private void btnEditarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarUsuarioMouseClicked
        String usuario = comboUsuario.getSelectedItem().toString();
        ComboItem item = new ComboItem();
        for (int i = 0; i < comboUsuario.getItemCount(); i++) {
            if (usuario.equals(comboUsuario.getItemAt(i).toString())) {
                item = comboUsuario.getModel().getElementAt(i);
            }
        }
        FrmNuevoUsuario u = new FrmNuevoUsuario(item.getValue(), 0, "modificar");
        u.setVisible(true);
    }//GEN-LAST:event_btnEditarUsuarioMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        eliminar();
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        vc.wordsOnly(evt);
    }//GEN-LAST:event_txtNombreKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel btnEditarUsuario;
    private javax.swing.JLabel btnEliminar;
    private javax.swing.JButton btnFoto;
    private javax.swing.JLabel btnInsertar;
    private javax.swing.JLabel btnModificar;
    private javax.swing.JLabel btnRefrescar;
    private javax.swing.JComboBox<ComboItem> comboUsuario;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JFormattedTextField txtTelefono;
    private javax.swing.JLabel txtUsuarioid;
    // End of variables declaration//GEN-END:variables
}


package com.vistas;

import com.dao.DaoCliente;
import com.dao.DaoUsuario;
import com.pojos.Cliente;
import com.pojos.Usuario;
import com.utils.ComboItem;
import java.awt.Image;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperReport;

public class Clientes extends javax.swing.JInternalFrame {
    DaoCliente daoC = new DaoCliente();
    Cliente cli = new Cliente();
    DaoUsuario daoU = new DaoUsuario();
    JasperReport reporte;
    
    public Clientes() {
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
            
            int respuesta = JOptionPane.showConfirmDialog(this, "Desea modificar el producto",
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
            int respuesta = JOptionPane.showConfirmDialog(this, "Desea eliminar el producto",
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
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        txtTelefono = new javax.swing.JTextField();
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

        jPanel1.setBackground(new java.awt.Color(49, 57, 69));

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
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator3.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 145, 343, 10));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel4.setText("ID Cliente");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 116, -1, -1));

        txtCodigo.setBackground(new java.awt.Color(233, 235, 237));
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

        txtTelefono.setBackground(new java.awt.Color(233, 235, 237));
        txtTelefono.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtTelefono.setBorder(null);
        jPanel3.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 61, 267, 28));

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

        txtNombre.setBackground(new java.awt.Color(233, 235, 237));
        txtNombre.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtNombre.setBorder(null);
        jPanel3.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(427, 11, 281, 28));

        jSeparator11.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 45, 337, 10));

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel9.setText("Dirección");
        jLabel9.setToolTipText("");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 66, -1, -1));

        txtDireccion.setBackground(new java.awt.Color(233, 235, 237));
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

        jPanel3.add(comboUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 210, 40));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel12.setText("Buscar nombre");
        jLabel12.setToolTipText("");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jSeparator10.setForeground(new java.awt.Color(49, 57, 69));
        jPanel4.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 445, 10));

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

        btnInsertar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_add_36px.png"))); // NOI18N
        btnInsertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInsertarMouseClicked(evt);
            }
        });
        jPanel4.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, -1, -1));

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_save_36px_1.png"))); // NOI18N
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarMouseClicked(evt);
            }
        });
        jPanel4.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 0, -1, -1));

        btnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_refresh_36px.png"))); // NOI18N
        btnRefrescar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefrescarMouseClicked(evt);
            }
        });
        jPanel4.add(btnRefrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, -1, -1));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_delete_bin_36px.png"))); // NOI18N
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });
        jPanel4.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, -1, -1));

        btnFoto.setText("Foto");
        btnFoto.setBorderPainted(false);
        btnFoto.setContentAreaFilled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1)
                .addGap(14, 14, 14))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(36, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );

        getContentPane().add(jPanel2, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        mostrar(daoC.buscarCliente(txtBuscar.getText()));
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        int input = JOptionPane.showConfirmDialog(rootPane, "Desea cerrar sesión?", "Salir", JOptionPane.YES_NO_OPTION);
        if(input == 0){
            super.dispose();
            try {
                new Login().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jLabel11MouseClicked

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
        NuevoUsuario u = new NuevoUsuario(item.getValue(), 0, "modificar");
        u.setVisible(true);
    }//GEN-LAST:event_btnEditarUsuarioMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        eliminar();
    }//GEN-LAST:event_btnEliminarMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel btnEditarUsuario;
    private javax.swing.JLabel btnEliminar;
    private javax.swing.JButton btnFoto;
    private javax.swing.JLabel btnInsertar;
    private javax.swing.JLabel btnModificar;
    private javax.swing.JLabel btnRefrescar;
    private javax.swing.JComboBox<ComboItem> comboUsuario;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
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
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JLabel txtUsuarioid;
    // End of variables declaration//GEN-END:variables
}

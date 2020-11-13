
package com.vistas;

import com.dao.DaoDetalleOferta;
import com.dao.DaoImpuestos;
import com.dao.DaoUsuario;
import com.dao.DaoVehiculo;
import com.pojos.DetalleOferta;
import com.pojos.DetalleOfertaId;
import com.pojos.Impuesto;
import com.pojos.Usuario;
import com.pojos.Vehiculo;
import com.utils.ComboItem;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * NombreClase: FrmDetalleOfertas
 * Fecha: 13/11/2020 
 * Versión: 1.0 
 * Copyright:ITCA-FEPADE
 * @author Leonel Antonio López Valencia - 040119 
 * Roberto Alejandro Armijo Jímenez - 046719 
 * Sandra Natalia Menjívar Romero - 174218
 */

public class FrmDetalleOfertas extends javax.swing.JInternalFrame {

    DaoDetalleOferta daoD = new DaoDetalleOferta();
    DetalleOferta det = new DetalleOferta();
    DaoImpuestos daoI = new DaoImpuestos();
    Usuario usu = new Usuario();
    DaoUsuario daoU = new DaoUsuario();
    DaoVehiculo daoV = new DaoVehiculo();
    String rutaModificado;
    List<DetalleOferta> carrito = new ArrayList();
    DefaultTableModel tabla;
    DecimalFormat df = new DecimalFormat("#.00");
    
    public FrmDetalleOfertas() {
        initComponents();
        cargarCombo(cbVehiculo, daoV.mostrarVehiculos());
        mostrar(daoD.mostrarDetalleOferta());
        setId();
        limpiar();
    }
    
    public void setId(){
        txtId.setText(String.valueOf(daoD.ultimoId()));
        this.txtId.setEnabled(false);
        txtFecha.setDate(Calendar.getInstance().getTime());
        txtFecha.setEnabled(false);
    }
   
    
    private void cargarCombo(JComboBox combo, List<Vehiculo> list) {
        for (Vehiculo item : list) {
            combo.addItem(new ComboItem(item.getIdVehiculo(),
                    item.getNombre()));
        }
    }
    
   
    
     public void mostrar(List<DetalleOferta> lista) {
        DefaultTableModel tabla;
        String []columnas ={"ID Detalle","Nombre Usuario","Vehiculo", "Cantidad", "Precio", "Fecha Oferta"};
        tabla = new DefaultTableModel(null, columnas);
        Object datos[] = new Object[6];
        
        String usuario = FrmLogin.usuarioL;
         List<DetalleOferta> detalles = new ArrayList();
        int tipo = daoU.tipoUsuario(usuario);
        if(tipo > 1){
            for(DetalleOferta list: lista){
                if(list.getUsuario().getNombreUsuario().equals(usuario)){
                    detalles.add(list);
                }
            }
        }else{
            detalles = lista;
        }

        try {
            for (int i = 0; i < detalles.size(); i++) {
                det = (DetalleOferta) detalles.get(i);
                datos[0] = det.getId().getIdDetalle();
                datos[1] = det.getUsuario().getNombreUsuario();
                datos[2] = det.getVehiculo().getNombre();
                datos[3] = det.getCantidad();
                datos[4] = det.getVehiculo().getPrecio();
                datos[5] = det.getFechaOferta();
                
                tabla.addRow(datos);
            }
            this.tablaDetalleOferta.setModel(tabla);
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error al mostrar en formulario " + e.getMessage());
        }
    }
     
     public void limpiar() {
        this.txtId.setText("");
        this.txtNombreU.setText("");
        this.cbVehiculo.setSelectedIndex(0);
        this.txtCantidad.setValue(1);
        this.txtId.setEnabled(false);
        this.txtNombreU.setEnabled(false);
        this.txtTotalC.setEnabled(false);
        this.txtImpuesto.setEnabled(false); 
        this.txtPrecio.setEnabled(false);
    }
     
     public void llenarTabla() {
        int fila = this.tablaDetalleOferta.getSelectedRow();
        if (fila > -1) {
            this.txtId.setText(String.valueOf(this.tablaDetalleOferta.getValueAt(fila, 0)));
            this.txtNombreU.setText(String.valueOf(this.tablaDetalleOferta.getValueAt(fila, 1)));
            String vehi = String.valueOf(this.tablaDetalleOferta.getValueAt(fila, 2));
            cbVehiculo.getModel().setSelectedItem(vehi);
            this.txtCantidad.setValue(this.tablaDetalleOferta.getValueAt(fila, 3));

            this.txtFecha.setDate((Date) this.tablaDetalleOferta.getValueAt(fila, 5));
            total();
        }
    }
     
    public void total()
    {
        double imp = 0.00;
        double total = 0.00;
        double totalFinal = 0.00;
        
       
        List<Impuesto> impuestos = new ArrayList();
         
            int fila = this.tablaDetalleOferta.getSelectedRow();
            String vehi = String.valueOf(this.tablaDetalleOferta.getValueAt(fila, 2));
            
            List<Vehiculo> ve = new ArrayList();
            ve = daoV.buscarVehiculo(vehi);
            
            String tipo = ve.get(0).getCategoria().getNombreCategoria();
            impuestos = daoI.buscarImpuesto(tipo);
            
            double dai = impuestos.get(0).getValor();
            double precio = det.getVehiculo().getPrecio();
            
            impuestos = daoI.buscarImpuesto("IVA");
            double iva = impuestos.get(0).getValor();
            
            impuestos = daoI.buscarImpuesto("CESC");
            double cesc = impuestos.get(0).getValor();
            
            int cantidad = Integer.parseInt(txtCantidad.getValue().toString());
            imp = (precio*iva + precio*cesc + precio*dai);
            total = precio;
            totalFinal = total + imp;
            
            txtTotalC.setText(String.valueOf(df.format(totalFinal)));
            txtPrecio.setText(String.valueOf(df.format(precio)));
            txtImpuesto.setText(String.valueOf(df.format(imp)));
    }
    
    public void modificar() {
        try {
            int idV = Integer.parseInt(txtId.getText());
            DetalleOfertaId deta = new DetalleOfertaId(Integer.parseInt(txtId.getText()), idV);
            det.setId(deta);
            
            String vehiculo = cbVehiculo.getSelectedItem().toString();
            ComboItem item = new ComboItem();
            for (int i = 0; i < cbVehiculo.getItemCount(); i++) {
                if (vehiculo.equals(cbVehiculo.getItemAt(i).toString())) {
                    item = cbVehiculo.getModel().getElementAt(i);
                }
            }
            Vehiculo ve = new Vehiculo(item.getValue());
            det.setVehiculo(ve);
            
            det.setCantidad(Integer.parseInt(this.txtCantidad.getValue().toString()));
            
            int respuesta = JOptionPane.showConfirmDialog(this, "Desea modificar la oferta",
                    "Modificar", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.OK_OPTION) {
                daoD.modificarDetalleOferta(det);
                JOptionPane.showMessageDialog(null, "Datos modificados con exito");
                mostrar(daoD.mostrarDetalleOferta());
                limpiar();
            } else {
                mostrar(daoD.mostrarDetalleOferta());
                limpiar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar en formulario");
        }
    }
    
     public void eliminar(){
        try {
            int idV = Integer.parseInt(txtId.getText());
            DetalleOfertaId deta = new DetalleOfertaId(Integer.parseInt(txtId.getText()), idV);
            det.setId(deta);
            int respuesta = JOptionPane.showConfirmDialog(this, "Desea eliminar la oferta",
                    "Eliminar", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.OK_OPTION) {
                daoD.eliminarDetalleOferta(det);
                JOptionPane.showMessageDialog(null, "Datos eliminados con exito");
                mostrar(daoD.mostrarDetalleOferta());
                limpiar();
            } else {
                mostrar(daoD.mostrarDetalleOferta());
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
        tablaDetalleOferta = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        txtImpuesto = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombreU = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        cbVehiculo = new javax.swing.JComboBox<>();
        txtTotalC = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        txtCantidad = new javax.swing.JSpinner();
        txtUsuarioid = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        btnRefrescar = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JLabel();
        btnModificar = new javax.swing.JLabel();
        txtFecha = new com.toedter.calendar.JDateChooser();

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

        txtId.setBackground(new java.awt.Color(255, 255, 255));
        txtId.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtId.setBorder(null);
        jPanel3.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 11, 230, 28));

        jSeparator8.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 340, 10));

        txtImpuesto.setBackground(new java.awt.Color(255, 255, 255));
        txtImpuesto.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtImpuesto.setBorder(null);
        jPanel3.add(txtImpuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 230, 28));

        jSeparator9.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 95, 343, 10));

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel8.setText("Vehiculo");
        jLabel8.setToolTipText("");
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 66, -1, -1));

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel7.setText("Nombre usuario");
        jLabel7.setToolTipText("");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 16, -1, -1));

        txtNombreU.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreU.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtNombreU.setBorder(null);
        jPanel3.add(txtNombreU, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 290, 28));

        jSeparator11.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 45, 400, 10));

        jSeparator5.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 400, 10));

        jLabel14.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel14.setText("Total Compra");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, -1, -1));

        txtPrecio.setBackground(new java.awt.Color(255, 255, 255));
        txtPrecio.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtPrecio.setBorder(null);
        jPanel3.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 290, 28));

        jSeparator12.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 400, 10));

        jLabel17.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel17.setText("Cantidad");
        jLabel17.setToolTipText("");
        jLabel17.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 100, -1));

        cbVehiculo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbVehiculoItemStateChanged(evt);
            }
        });
        jPanel3.add(cbVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 230, 30));

        txtTotalC.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalC.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtTotalC.setBorder(null);
        jPanel3.add(txtTotalC, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 290, 28));

        jLabel15.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel15.setText("Precio");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, -1, -1));

        jSeparator6.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 400, 10));

        txtCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jPanel3.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 290, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel12.setText("Buscar ID");
        jLabel12.setToolTipText("");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jSeparator10.setForeground(new java.awt.Color(49, 57, 69));
        jPanel4.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 445, 10));

        txtBuscar.setBackground(new java.awt.Color(255, 255, 255));
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
        jPanel4.add(btnRefrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, -1, -1));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_delete_bin_36px.png"))); // NOI18N
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });
        jPanel4.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, -1, -1));

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_edit_36px.png"))); // NOI18N
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarMouseClicked(evt);
            }
        });
        jPanel4.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, -1, -1));
        jPanel4.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, 120, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 119, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        getContentPane().add(jPanel2, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
       mostrar(daoD.buscarDetalleOferta(txtBuscar.getText()));
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void Enter(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Enter

    }//GEN-LAST:event_Enter

    private void tablaDetalleOfertaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDetalleOfertaMouseClicked
        llenarTabla();
    }//GEN-LAST:event_tablaDetalleOfertaMouseClicked

    private void btnRefrescarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefrescarMouseClicked
        limpiar();
        txtTotalC.setText("");
        txtPrecio.setText("");
        txtImpuesto.setText("");
    }//GEN-LAST:event_btnRefrescarMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        eliminar();
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
        modificar();
    }//GEN-LAST:event_btnModificarMouseClicked

    private void cbVehiculoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbVehiculoItemStateChanged
        String usuario = cbVehiculo.getSelectedItem().toString();
        ComboItem item = new ComboItem();

        //Agregar linea para combobox
        Usuario usu = new Usuario(item.getValue());

        for (int i = 0; i < cbVehiculo.getItemCount(); i++) {
            if (usuario.equals(cbVehiculo.getItemAt(i).toString())) {
                item = cbVehiculo.getModel().getElementAt(i);
                Vehiculo veh = daoV.buscarVehiculo(item.getValue());
                
            }
        }
        
    }//GEN-LAST:event_cbVehiculoItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel btnEliminar;
    private javax.swing.JLabel btnModificar;
    private javax.swing.JLabel btnRefrescar;
    private javax.swing.JComboBox<ComboItem> cbVehiculo;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable tablaDetalleOferta;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JSpinner txtCantidad;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtImpuesto;
    private javax.swing.JTextField txtNombreU;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtTotalC;
    private javax.swing.JLabel txtUsuarioid;
    // End of variables declaration//GEN-END:variables
}

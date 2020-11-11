
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
import java.awt.Image;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Carrito extends javax.swing.JInternalFrame {
    
    DaoDetalleOferta daoD = new DaoDetalleOferta();
    DetalleOferta det = new DetalleOferta();
    DaoImpuestos daoI = new DaoImpuestos();
    Usuario usu = new Usuario();
    DaoUsuario daoU = new DaoUsuario();
    DaoVehiculo daoV = new DaoVehiculo();
    String rutaModificado;
    List<DetalleOferta> carrito = new ArrayList();
    DefaultTableModel tabla;
    NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
    
    public Carrito() {
        initComponents();
        cargarCombo(cbVehiculo, daoV.mostrarVehiculos());
        habilitar(false);
        modeloTabla();
        setUsuarioLoggeado();
        borrarFilas();
        
    }
    
    public void setUsuarioLoggeado(){
        txtUsuario.setText(Login.usuarioL);
        txtUsuario.setEnabled(false);
        setId();
        txtIdVehiculo.setEnabled(false);
        txtFecha.setDate(Calendar.getInstance().getTime());
        txtFecha.setEnabled(false);
        txtUsuario.setText("juan36");
        Vehiculo veh = daoV.mostrarVehiculos().get(0);
        txtPrecio.setText(String.valueOf(veh.getPrecio()));
        txtPrecio.setEnabled(false);
    }
    
    public void setId(){
        txtId.setText(String.valueOf(daoD.ultimoId()));
    }
    
    private void cargarCombo(JComboBox combo, List<Vehiculo> list) {
        for (Vehiculo item : list) {
            combo.addItem(new ComboItem(item.getIdVehiculo(),
                    item.getNombre()));
        }
    }
    
    public void habilitar(boolean estado){
        txtId.setEnabled(estado);
    }
    
    public void modeloTabla() {
        String[] columnas = {"Código", "Usuario", "Vehiculo", "Cantidad", "Fecha", "ID Vehiculo", "Foto Vehiculo", "Precio"};
        tabla = new DefaultTableModel(null, columnas);
    }
    
    public void mostrar(DetalleOferta det) {
        Object datos[] = new Object[8];

        try {
            datos[0] = det.getId().getIdDetalle();
            datos[1] = det.getUsuario().getNombreUsuario();
            datos[2] = det.getVehiculo().getNombre();
            datos[3] = det.getCantidad();
            datos[4] = det.getFechaOferta();
            datos[5] = det.getVehiculo().getIdVehiculo();
            datos[6] = det.getVehiculo().getFoto();
            datos[7] = det.getVehiculo().getPrecio();
            tabla.addRow(datos);
            this.tablaUsuarios.setModel(tabla);
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error al mostrar en formulario " + e.getMessage());
        }
    }

    public void insertar() {
        DetalleOferta detalle = new DetalleOferta();
        boolean encontrado = false;
        int idV = Integer.parseInt(txtIdVehiculo.getText());
        DefaultTableModel dm = (DefaultTableModel) tablaUsuarios.getModel();
        int rowCount = dm.getRowCount();
        if(rowCount >= 1){
            for (int i = 0; i < rowCount; i++) {
                int idVehiculo = Integer.parseInt(dm.getValueAt(i, 5).toString());
                if (idVehiculo == idV) {
                    encontrado = true;
                    JOptionPane.showMessageDialog(this, "Este producto ha ya sido agregado.", "Vehiculo", 0);
                    break;
                }
            }
            
            if(!encontrado){
                DetalleOfertaId deta = new DetalleOfertaId(Integer.parseInt(txtId.getText()), idV);
                detalle.setId(deta);
                detalle.setCantidad(Integer.parseInt(txtCantidad.getValue().toString()));
                detalle.setUsuario(daoU.buscarUsuarioNombre(txtUsuario.getText()).get(0));
                detalle.setFechaOferta(txtFecha.getDate());
                detalle.setVehiculo(daoV.buscarVehiculo(idV));
                carrito.add(detalle);
                mostrar(detalle);
            }
        }else{
            DetalleOfertaId deta = new DetalleOfertaId(Integer.parseInt(txtId.getText()), idV);
            detalle.setId(deta);
            detalle.setCantidad(Integer.parseInt(txtCantidad.getValue().toString()));
            detalle.setUsuario(daoU.buscarUsuarioNombre(txtUsuario.getText()).get(0));
            detalle.setFechaOferta(txtFecha.getDate());
            detalle.setVehiculo(daoV.buscarVehiculo(idV));
            carrito.add(detalle);

            mostrar(detalle);
        }
        limpiar();
    }

    public void limpiar() {
        this.txtCantidad.setValue(1);
        this.cbVehiculo.setSelectedIndex(0);
        this.btnFoto.setIcon(null);
    }
    
    public void borrarFilas(){
        DefaultTableModel dm = (DefaultTableModel) tablaUsuarios.getModel();
        int rowCount = dm.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
    }

    public void llenarTabla() {
        int fila = this.tablaUsuarios.getSelectedRow();
        if (fila > -1) {
            this.txtId.setText(String.valueOf(this.tablaUsuarios.getValueAt(fila, 0)));
            this.txtUsuario.setText(String.valueOf(this.tablaUsuarios.getValueAt(fila, 1)));
            
            this.txtCantidad.setValue(this.tablaUsuarios.getValueAt(fila, 3));
            this.txtIdVehiculo.setText(String.valueOf(this.tablaUsuarios.getValueAt(fila, 5)));
            rutaModificado = tablaUsuarios.getValueAt(fila, 5).toString();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(tablaUsuarios.getValueAt(fila, 6).toString()).getImage().getScaledInstance(190, 190, Image.SCALE_DEFAULT));
            btnFoto.setText("");
            btnFoto.setIcon(imageIcon);
            this.txtPrecio.setText(String.valueOf(this.tablaUsuarios.getValueAt(fila, 7)));
        }
    }

    public void modificar() {
        eliminar();
        System.out.println("Fila borrada");
        insertar();
    }
    
    public void eliminar(){
        int fila = this.tablaUsuarios.getSelectedRow();
        DefaultTableModel dm = (DefaultTableModel) tablaUsuarios.getModel();
        int rowCount = dm.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            if (fila == i) {
                dm.removeRow(i);
                carrito.remove(i);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        txtUsuarioid = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnInsertar = new javax.swing.JLabel();
        btnModificar = new javax.swing.JLabel();
        btnRefrescar = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JLabel();
        btnComprar = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        Nombre = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        txtUsuario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        Nombre1 = new javax.swing.JLabel();
        cbVehiculo = new javax.swing.JComboBox<>();
        txtId = new javax.swing.JTextField();
        txtFecha = new com.toedter.calendar.JDateChooser();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        txtIdVehiculo = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        txtPrecio = new javax.swing.JTextField();
        btnFoto = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Usuarios");
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(49, 57, 69));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Carrito");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tablaUsuarios.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaUsuarios.setToolTipText("Para ver el detalle completo, seleccione una empresa y presione Enter.");
        tablaUsuarios.setRowHeight(25);
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        tablaUsuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Enter(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUsuarios);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnInsertar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_add_36px.png"))); // NOI18N
        btnInsertar.setToolTipText("Agregar");
        btnInsertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInsertarMouseClicked(evt);
            }
        });
        jPanel4.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_save_36px_1.png"))); // NOI18N
        btnModificar.setToolTipText("Modificar");
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarMouseClicked(evt);
            }
        });
        jPanel4.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, -1, -1));

        btnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_refresh_36px.png"))); // NOI18N
        btnRefrescar.setToolTipText("Refrescar");
        btnRefrescar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefrescarMouseClicked(evt);
            }
        });
        jPanel4.add(btnRefrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_delete_bin_36px.png"))); // NOI18N
        btnEliminar.setToolTipText("Eliminar");
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });
        jPanel4.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, -1, -1));

        btnComprar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_buy_36px.png"))); // NOI18N
        btnComprar.setToolTipText("Comprar");
        btnComprar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnComprarMouseClicked(evt);
            }
        });
        jPanel4.add(btnComprar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator3.setForeground(new java.awt.Color(49, 57, 69));

        Nombre.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        Nombre.setText("Usuario");

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel9.setText("Vehiculo");

        jSeparator5.setForeground(new java.awt.Color(49, 57, 69));

        txtUsuario.setBackground(new java.awt.Color(233, 235, 237));
        txtUsuario.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtUsuario.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel7.setText("ID");

        jSeparator6.setForeground(new java.awt.Color(49, 57, 69));

        jSeparator11.setForeground(new java.awt.Color(49, 57, 69));

        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel16.setText("Cantidad");

        jSeparator4.setForeground(new java.awt.Color(49, 57, 69));

        Nombre1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        Nombre1.setText("Fecha");

        cbVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        cbVehiculo.setForeground(new java.awt.Color(0, 0, 0));
        cbVehiculo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbVehiculoItemStateChanged(evt);
            }
        });

        txtId.setBackground(new java.awt.Color(233, 235, 237));
        txtId.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtId.setBorder(null);

        jSeparator12.setForeground(new java.awt.Color(49, 57, 69));

        jLabel17.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel17.setText("ID Vehiculo");

        txtIdVehiculo.setBackground(new java.awt.Color(233, 235, 237));
        txtIdVehiculo.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txtIdVehiculo.setBorder(null);

        txtCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel8.setText("Precio");

        jSeparator7.setForeground(new java.awt.Color(49, 57, 69));

        txtPrecio.setBackground(new java.awt.Color(233, 235, 237));
        txtPrecio.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtPrecio.setBorder(null);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(Nombre1)
                                .addGap(53, 53, 53)
                                .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(Nombre)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(226, 226, 226)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(cbVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addGap(37, 37, 37)
                                    .addComponent(txtCantidad))
                                .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Nombre)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(cbVehiculo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator11)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Nombre1)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIdVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator12)))
        );

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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefrescarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefrescarMouseClicked
        limpiar();
        borrarFilas();
        setId();
    }//GEN-LAST:event_btnRefrescarMouseClicked

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
        modificar();
    }//GEN-LAST:event_btnModificarMouseClicked

    private void btnInsertarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertarMouseClicked
        insertar();
    }//GEN-LAST:event_btnInsertarMouseClicked

    private void Enter(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Enter

    }//GEN-LAST:event_Enter

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        llenarTabla();
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    private void btnFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFotoActionPerformed

    }//GEN-LAST:event_btnFotoActionPerformed

    private void cbVehiculoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbVehiculoItemStateChanged
        String usuario = cbVehiculo.getSelectedItem().toString();
        ComboItem item = new ComboItem();

        //Agregar linea para combobox
        Usuario usu = new Usuario(item.getValue());

        for (int i = 0; i < cbVehiculo.getItemCount(); i++) {
            if (usuario.equals(cbVehiculo.getItemAt(i).toString())) {
                item = cbVehiculo.getModel().getElementAt(i);
                txtIdVehiculo.setText(String.valueOf(item.getValue()));
                Vehiculo veh = daoV.buscarVehiculo(item.getValue());
                txtPrecio.setText(String.valueOf(veh.getPrecio()));
            }
        }
    }//GEN-LAST:event_cbVehiculoItemStateChanged

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        eliminar();
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnComprarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComprarMouseClicked
        double total = 0.00;
        double totalFinal = 0.00;
        List<Impuesto> impuestos = new ArrayList();
        
        for(DetalleOferta car: carrito){
            
            String tipo = car.getVehiculo().getCategoria().getNombreCategoria();
            impuestos = daoI.buscarImpuesto(tipo);
            
            double dai = impuestos.get(0).getValor();
            double precio = Double.parseDouble(txtPrecio.getText());
            
            impuestos = daoI.buscarImpuesto("IVA");
            double iva = impuestos.get(0).getValor();
            
            impuestos = daoI.buscarImpuesto("CESC");
            double cesc = impuestos.get(0).getValor();
            
            int cantidad = Integer.parseInt(txtCantidad.getValue().toString());
            total = (precio*iva + precio*cesc + precio*dai+precio)*cantidad;
            totalFinal += total;
            System.out.println("total: "+total+" Final: "+totalFinal);
            //daoD.insertarDetalleOferta(car);
                    
        }
        carrito.removeAll(carrito);
        
    }//GEN-LAST:event_btnComprarMouseClicked

    private String getFileExtension(String name) {
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel Nombre1;
    private javax.swing.JLabel btnComprar;
    private javax.swing.JLabel btnEliminar;
    private javax.swing.JButton btnFoto;
    private javax.swing.JLabel btnInsertar;
    private javax.swing.JLabel btnModificar;
    private javax.swing.JLabel btnRefrescar;
    private javax.swing.JComboBox<ComboItem> cbVehiculo;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JSpinner txtCantidad;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdVehiculo;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JLabel txtUsuarioid;
    // End of variables declaration//GEN-END:variables
}

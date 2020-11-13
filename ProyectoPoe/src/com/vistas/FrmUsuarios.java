
package com.vistas;

import com.dao.Crypting;
import com.dao.DaoCliente;
import com.dao.DaoUsuario;
import com.pojos.Cliente;
import com.pojos.Usuario;
import com.utils.ComboItem;
import com.utils.ValidarCampos;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class FrmUsuarios extends javax.swing.JInternalFrame {
    
    DaoCliente daoC = new DaoCliente();
    Usuario usu = new Usuario();
    DaoUsuario daoU = new DaoUsuario();
    String rutaModificado;
    ValidarCampos vc=new ValidarCampos();
    boolean estadoContra = false;
    char def;
    
    public FrmUsuarios() {
        initComponents();
        mostrar(daoU.mostrarUsuarios());
        //super.setExtendedState(Frame.MAXIMIZED_BOTH);
        def = txtContra.getEchoChar();
        habilitar(false);
        
    }
    
    public void habilitar(boolean estado){
        txtId.setEnabled(estado);
    }
    
    public void ultimoId(){
        txtId.setText(String.valueOf(daoU.ultimoId()));
    }
    
    public void mostrar(List<Usuario> lista) {
        DefaultTableModel tabla;
        String []columnas ={"Código","Nombre","Correo","Contraseña", "Prioridad", "Foto"};
        tabla = new DefaultTableModel(null, columnas);
        Object datos[] = new Object[7];

        try {
            for (int i = 0; i < lista.size(); i++) {
                usu = (Usuario) lista.get(i);
                datos[0] = usu.getIdUsuario();
                datos[1] = usu.getNombreUsuario();
                datos[2] = usu.getCorreoUsuario();
                datos[3] = usu.getContra();
                datos[4] = usu.getTipoUsuario();
                int pri = usu.getTipoUsuario();
                switch (pri) {
                    case 1:
                        datos[4] = "Administrador";
                        break;
                    case 2:
                        datos[4] = "Cliente";
                        break;
                    case 3:
                        datos[4] = "Proveedor";
                        break;
                    case 4:
                        datos[4] = "Empleado";
                        break;
                }
                datos[5] = usu.getFoto();
                tabla.addRow(datos);
            }
            this.tablaUsuarios.setModel(tabla);
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error al mostrar en formulario " + e.getMessage());
        }
    }

    public void insertar() {
        FrmNuevoUsuario nU = new FrmNuevoUsuario("insertarUsuario");
        nU.setVisible(true);
    }

    public void limpiar() {
        this.txtId.setText("");
        this.txtNombre.setText("");
        this.txtCorreo.setText("");
        this.txtContra.setText("");
        this.cbPrioridad.setSelectedIndex(0);
        this.btnFoto.setIcon(null);
    }

    public void llenarTabla() {
        int fila = this.tablaUsuarios.getSelectedRow();
        if (fila > -1) {
            this.txtId.setText(String.valueOf(this.tablaUsuarios.getValueAt(fila, 0)));
            this.txtNombre.setText(String.valueOf(this.tablaUsuarios.getValueAt(fila, 1)));
            this.txtCorreo.setText(String.valueOf(this.tablaUsuarios.getValueAt(fila, 2)));
            this.txtContra.setText(String.valueOf(this.tablaUsuarios.getValueAt(fila, 3)));
            
            String prioridad = this.tablaUsuarios.getValueAt(fila, 4).toString();
            if(prioridad.equals("Administrador")){
                this.cbPrioridad.setSelectedIndex(0);
            }else if(prioridad.equals("Cliente")){
                this.cbPrioridad.setSelectedIndex(1);
            }else if(prioridad.equals("Proveedor")){
                this.cbPrioridad.setSelectedIndex(2);
            }else if(prioridad.equals("Empleado")){
                this.cbPrioridad.setSelectedIndex(3);
            }
            rutaModificado = tablaUsuarios.getValueAt(fila, 5).toString();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(tablaUsuarios.getValueAt(fila, 5).toString()).getImage().getScaledInstance(190, 190, Image.SCALE_DEFAULT));
            btnFoto.setText("");
            btnFoto.setIcon(imageIcon);
        }
    }

    public void modificar() {
        try {
            Crypting crp = new Crypting();
            usu.setIdUsuario(Integer.parseInt(this.txtId.getText()));
            usu.setNombreUsuario(this.txtNombre.getText());
            usu.setCorreoUsuario(this.txtCorreo.getText());
            usu.setContra(crp.encrypt(new String(this.txtContra.getPassword())));
            int prioridad = cbPrioridad.getSelectedIndex()+1;

            usu.setTipoUsuario(prioridad);
            usu.setFoto(rutaModificado);
            int respuesta = JOptionPane.showConfirmDialog(this, "Desea modificar el producto",
                    "Modificar", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.OK_OPTION) {
                daoU.modificarUsuario(usu);
                JOptionPane.showMessageDialog(null, "Datos modificados con exito");
                mostrar(daoU.mostrarUsuarios());
                limpiar();
            } else {
                mostrar(daoU.mostrarUsuarios());
                limpiar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar en formulario");
        }
    }
    
    public void eliminar(){
        if(cbPrioridad.getSelectedItem().equals("Administrador")){
            JOptionPane.showMessageDialog(this, "Administradores no pueden ser eliminados.");
        }else{
            try {
                usu.setIdUsuario(Integer.parseInt(this.txtId.getText()));
                int respuesta = JOptionPane.showConfirmDialog(this, "Desea eliminar el producto",
                        "Eliminar", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.OK_OPTION) {
                    daoU.eliminarUsuario(usu);
                    JOptionPane.showMessageDialog(null, "Datos eliminados con exito");
                    mostrar(daoU.mostrarUsuarios());
                    limpiar();
                } else {
                    mostrar(daoU.mostrarUsuarios());
                    limpiar();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar en formulario " + e.getMessage());
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
        jLabel12 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnInsertar = new javax.swing.JLabel();
        btnModificar = new javax.swing.JLabel();
        btnRefrescar = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        Nombre = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        txtNombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtContra = new javax.swing.JPasswordField();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        Nombre1 = new javax.swing.JLabel();
        cbPrioridad = new javax.swing.JComboBox<>();
        btnMostrarContra = new javax.swing.JLabel();
        btnFoto = new javax.swing.JButton();
        btnEditarFoto = new javax.swing.JButton();

        setBackground(null);
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
        jLabel2.setText("Usuarios");
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
        btnInsertar.setToolTipText("Agregar");
        btnInsertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInsertarMouseClicked(evt);
            }
        });
        jPanel4.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, -1, -1));

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_edit_36px.png"))); // NOI18N
        btnModificar.setToolTipText("Guardar");
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarMouseClicked(evt);
            }
        });
        jPanel4.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, -1, -1));

        btnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_refresh_36px.png"))); // NOI18N
        btnRefrescar.setToolTipText("Refrescar");
        btnRefrescar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefrescarMouseClicked(evt);
            }
        });
        jPanel4.add(btnRefrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator3.setForeground(new java.awt.Color(49, 57, 69));

        Nombre.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        Nombre.setText("Usuario");

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel9.setText("Prioridad");

        jSeparator5.setForeground(new java.awt.Color(49, 57, 69));

        txtNombre.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtNombre.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel7.setText("Contraseña");

        txtContra.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtContra.setBorder(null);

        jSeparator6.setForeground(new java.awt.Color(49, 57, 69));

        jSeparator11.setForeground(new java.awt.Color(49, 57, 69));

        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel16.setText("Correo");

        txtCorreo.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txtCorreo.setBorder(null);
        txtCorreo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCorreoFocusLost(evt);
            }
        });

        txtId.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtId.setBorder(null);

        jSeparator4.setForeground(new java.awt.Color(49, 57, 69));

        Nombre1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        Nombre1.setText("ID");

        cbPrioridad.setBackground(new java.awt.Color(255, 255, 255));
        cbPrioridad.setForeground(new java.awt.Color(0, 0, 0));
        cbPrioridad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Cliente", "Proveedor", "Empleado" }));

        btnMostrarContra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_eye_36px_1.png"))); // NOI18N
        btnMostrarContra.setToolTipText("Mostrar Contraseña");
        btnMostrarContra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMostrarContraMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(Nombre1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(btnMostrarContra))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(Nombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(55, 55, 55)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(34, 34, 34)
                        .addComponent(txtCorreo))
                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(cbPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(cbPrioridad))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7)
                            .addComponent(btnMostrarContra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtContra, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Nombre))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator11)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Nombre1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnFoto.setText("Foto");
        btnFoto.setBorderPainted(false);
        btnFoto.setContentAreaFilled(false);
        btnFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFotoActionPerformed(evt);
            }
        });

        btnEditarFoto.setText("Editar");
        btnEditarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarFotoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(193, 193, 193))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 34, Short.MAX_VALUE)
                                .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditarFoto)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
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
                    .addComponent(btnEditarFoto)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefrescarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefrescarMouseClicked
        mostrar(daoU.mostrarUsuarios());
        limpiar();
    }//GEN-LAST:event_btnRefrescarMouseClicked

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
        modificar();
    }//GEN-LAST:event_btnModificarMouseClicked

    private void btnInsertarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertarMouseClicked
        insertar();
    }//GEN-LAST:event_btnInsertarMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        mostrar(daoU.buscarUsuario(txtBuscar.getText()));
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void Enter(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Enter

    }//GEN-LAST:event_Enter

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        llenarTabla();
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    private void btnFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFotoActionPerformed

    }//GEN-LAST:event_btnFotoActionPerformed

    private void btnEditarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarFotoActionPerformed
        String extension;
        JFileChooser guardar = new JFileChooser();
        guardar.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files","png", "jpeg", "jpg");
        
        guardar.addChoosableFileFilter(filter);
        guardar.showSaveDialog(null);
        
        if(guardar.getSelectedFile()!=null){
            try {
                File archivo = guardar.getSelectedFile();

                extension = getFileExtension(archivo.getName());

                if(extension.equals("png")){
                    File renombrado = new File(txtId.getText()+".png");
                    archivo.renameTo(renombrado);
                }else if(extension.equals("jpg")){
                    File renombrado = new File(txtId.getText()+".jpg");
                    archivo.renameTo(renombrado);
                }else if(extension.equals("jpeg")){
                    File renombrado = new File(txtId.getText()+".jpeg");
                    archivo.renameTo(renombrado);
                }

                rutaModificado = archivo.getAbsolutePath();
                String root = new File("").getAbsolutePath();
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(rutaModificado).getImage().getScaledInstance(190, 190, Image.SCALE_DEFAULT));
                btnFoto.setText("");
                btnFoto.setIcon(imageIcon);
                Files.copy(Paths.get(rutaModificado), Paths.get(root+"/src/com/recursos/fotosUsuarios").resolve(archivo.getName()), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                System.out.println("Error: "+e);
            }
        }
    }//GEN-LAST:event_btnEditarFotoActionPerformed

    private String getFileExtension(String name) {
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }
    
    private void btnMostrarContraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMostrarContraMouseClicked
        if(estadoContra){
            estadoContra = false;
            txtContra.setEchoChar((char)0);
        }else{
            estadoContra = true;
            txtContra.setEchoChar(def); 
        }
    }//GEN-LAST:event_btnMostrarContraMouseClicked

    private void txtCorreoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusLost
       if(vc.isEmail(txtCorreo.getText())==true)
       {
       
       } else {
           
       JOptionPane.showMessageDialog(null, "Email incorrecto","Validar Email",JOptionPane.INFORMATION_MESSAGE);
       txtCorreo.requestFocus();
       }
    }//GEN-LAST:event_txtCorreoFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel Nombre1;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditarFoto;
    private javax.swing.JButton btnFoto;
    private javax.swing.JLabel btnInsertar;
    private javax.swing.JLabel btnModificar;
    private javax.swing.JLabel btnMostrarContra;
    private javax.swing.JLabel btnRefrescar;
    private javax.swing.JComboBox<String> cbPrioridad;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JPasswordField txtContra;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JLabel txtUsuarioid;
    // End of variables declaration//GEN-END:variables
}

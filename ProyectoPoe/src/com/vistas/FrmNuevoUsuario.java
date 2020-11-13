
package com.vistas;

import com.dao.DaoUsuario;
import java.io.File;
import javax.swing.JOptionPane;
import com.pojos.Usuario;
import com.utils.ValidarCampos;
import java.awt.Image;
import java.awt.Toolkit;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * NombreClase: FrmNuevoUsuario
 * Fecha: 13/11/2020 
 * Versión: 1.0 
 * Copyright:ITCA-FEPADE
 * @author Leonel Antonio López Valencia - 040119 
 * Roberto Alejandro Armijo Jímenez - 046719 
 * Sandra Natalia Menjívar Romero - 174218
 */

public class FrmNuevoUsuario extends javax.swing.JFrame {
    
    Usuario usu = new Usuario();
    DaoUsuario daoU = new DaoUsuario();
    ValidarCampos vc=new ValidarCampos();
    String rutaModificado;
    String accion;
    
    public FrmNuevoUsuario() {
        initComponents();
        txtId.setEnabled(false);
        txtId.setText(String.valueOf(daoU.ultimoId()));
    }
    
    public FrmNuevoUsuario(String accion) {
        initComponents();
        txtId.setText(String.valueOf(daoU.ultimoId()));
        txtTitulo.setText("Nuevo usuario");
        this.accion = accion;
        setCombo();
    }
    
    public void setCombo(){
        if(accion.equals("insertarUsuario")){
            cbPrioridad.removeAllItems();
            cbPrioridad.addItem("Administrador");
            cbPrioridad.addItem("Cliente");
            cbPrioridad.addItem("Proveedor");
            cbPrioridad.addItem("Empleado");
        }
    }
    
    public FrmNuevoUsuario(int idUsuario, int rol, String accion) {
        initComponents();
        if (accion.equals("modificar")) {
            txtTitulo.setText("Modificar usuario");
            modificarUsuario(idUsuario, rol);
            jButton2.setVisible(false);
        } else {
            txtId.setText(String.valueOf(daoU.ultimoId()));
            txtTitulo.setText("Nuevo usuario");
        }
        this.accion = accion;
    }
    
    private void modificarUsuario(int idUsuario, int rol){
        this.cbPrioridad.setEnabled(false);
        cbPrioridad.setSelectedIndex(rol);
        Usuario u = daoU.buscarUsuario(idUsuario);
        txtNombre.setText(u.getNombreUsuario());
        txtCorreo.setText(u.getCorreoUsuario());
        txtContra.setText(u.getContra());
        txtId.setText(u.getIdUsuario().toString());
        rutaModificado = u.getFoto();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(u.getFoto()).getImage().getScaledInstance(190, 190, Image.SCALE_DEFAULT));
        btnFoto.setText("");
        btnFoto.setIcon(imageIcon);
    } 
    
    public void modificar() {
        try {
            usu.setIdUsuario(Integer.parseInt(this.txtId.getText()));
            if (this.cbPrioridad.getSelectedItem().equals("Administrador")) {
                usu.setTipoUsuario(1);
            } else if (this.cbPrioridad.getSelectedItem().equals("Cliente")) {
                usu.setTipoUsuario(2);
            } else if (this.cbPrioridad.getSelectedItem().equals("Proveedor")) {
                usu.setTipoUsuario(3);
            } else if (this.cbPrioridad.getSelectedItem().equals("Usuario")) {
                usu.setTipoUsuario(4);
            }
            usu.setNombreUsuario(this.txtNombre.getText());
            usu.setCorreoUsuario(this.txtCorreo.getText());
            usu.setContra(new String(this.txtContra.getPassword()));
            System.out.println("Contraseña: "+usu.getContra());
            usu.setFoto(rutaModificado);
            daoU.modificarUsuario(usu);
            
            int respuesta = JOptionPane.showConfirmDialog(this, "Desea modificar el usuario?",
                    "Modificar", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.OK_OPTION) {
                String resultado = daoU.modificarUsuario(usu);
                System.out.println(resultado);
                JOptionPane.showMessageDialog(null, "Datos modificados con exito");
                this.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar en formulario");
        }
    }
    
    public void insertar() {
        try {
            usu.setIdUsuario(Integer.parseInt(this.txtId.getText()));
            if(this.cbPrioridad.getSelectedItem().equals("Administrador")){
                usu.setTipoUsuario(1);
            }else if(this.cbPrioridad.getSelectedItem().equals("Cliente")){
                usu.setTipoUsuario(2);
            }else if(this.cbPrioridad.getSelectedItem().equals("Proveedor")){
                usu.setTipoUsuario(3);
            }else if(this.cbPrioridad.getSelectedItem().equals("Empleado")){
                usu.setTipoUsuario(4);
            }
            usu.setNombreUsuario(txtNombre.getText());
            usu.setCorreoUsuario(this.txtCorreo.getText());
            usu.setFoto(rutaModificado);
            usu.setContra(new String(this.txtContra.getPassword()));
            
            if(accion.equals("insertarUsuario")){
                daoU.insertarUsuario(usu);
                JOptionPane.showMessageDialog(null, "Insertado correctamente");
            }else{
                if (cbPrioridad.getSelectedItem().equals("Cliente")) {
                    //System.out.println("CLiente");
                    FrmNuevoCliente nCli = new FrmNuevoCliente(usu);
                    nCli.setVisible(true);
                    //daoU.modificarUsuario(usu);
                    //JOptionPane.showMessageDialog(null, "Insertado correctamente");
                } else {
                    FrmNuevoProveedor nPro = new FrmNuevoProveedor(usu);
                    nPro.setVisible(true);
                }
                this.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar" + e.getMessage());
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtTitulo = new javax.swing.JLabel();
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
        jButton1 = new javax.swing.JButton();
        btnFoto = new javax.swing.JButton();
        btnEditarFoto = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        Nombre1 = new javax.swing.JLabel();
        cbPrioridad = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo usuario");
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtTitulo.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        txtTitulo.setForeground(new java.awt.Color(49, 57, 69));
        txtTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTitulo.setText("Nuevo usuario");
        txtTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator3.setForeground(new java.awt.Color(49, 57, 69));

        Nombre.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        Nombre.setText("Usuario");

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel9.setText("Prioridad");

        jSeparator5.setForeground(new java.awt.Color(49, 57, 69));

        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtNombre.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel7.setText("Contraseña");

        txtContra.setBackground(new java.awt.Color(255, 255, 255));
        txtContra.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtContra.setBorder(null);

        jSeparator6.setForeground(new java.awt.Color(49, 57, 69));

        jSeparator11.setForeground(new java.awt.Color(49, 57, 69));

        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel16.setText("Correo");

        txtCorreo.setBackground(new java.awt.Color(255, 255, 255));
        txtCorreo.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtCorreo.setBorder(null);
        txtCorreo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCorreoFocusLost(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(49, 57, 69));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_forward_48px.png"))); // NOI18N
        jButton1.setText("Continuar");
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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

        txtId.setBackground(new java.awt.Color(255, 255, 255));
        txtId.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtId.setBorder(null);

        jSeparator4.setForeground(new java.awt.Color(49, 57, 69));

        Nombre1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        Nombre1.setText("ID");

        cbPrioridad.setBackground(new java.awt.Color(255, 255, 255));
        cbPrioridad.setForeground(new java.awt.Color(0, 0, 0));
        cbPrioridad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cliente", "Proveedor" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
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
                                        .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(Nombre)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(34, 34, 34)
                                .addComponent(txtCorreo))
                            .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(cbPrioridad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarFoto)
                        .addGap(147, 147, 147)))
                .addGap(45, 45, 45))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(297, 297, 297))
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
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditarFoto)
                    .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_back_48px.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(109, 109, 109))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTitulo)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(780, 780, 780))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 747, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 456, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if(accion.equals("modificar")){
           modificar();
       }else{
           insertar();
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtCorreoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusLost
        if(vc.isEmail(txtCorreo.getText())==true)
       {
       
       } else {
           
       JOptionPane.showMessageDialog(null, "Email incorrecto","Validar Email",JOptionPane.INFORMATION_MESSAGE);
       txtCorreo.requestFocus();
       }
    }//GEN-LAST:event_txtCorreoFocusLost

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        FrmLogin log = new FrmLogin();
        log.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private String getFileExtension(String name) {
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }
    
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("com/recursos/logo.png"));
        return retValue;
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
            java.util.logging.Logger.getLogger(FrmNuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmNuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmNuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmNuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmNuevoUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel Nombre1;
    private javax.swing.JButton btnEditarFoto;
    private javax.swing.JButton btnFoto;
    private javax.swing.JComboBox<String> cbPrioridad;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JPasswordField txtContra;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JLabel txtTitulo;
    // End of variables declaration//GEN-END:variables
}


package com.vistas;

import Dao.DaoCategoria;
import com.dao.DaoUnidadMedida;
import com.dao.DaoVehiculo;
import com.pojos.Categoria;
import com.pojos.UnidadMedida;
import com.pojos.Vehiculo;
import com.utils.ComboItem;
import com.utils.ValidarCampos;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 * NombreClase: FrmVehiculos
 * Fecha: 13/11/2020 
 * Versión: 1.0 
 * Copyright:ITCA-FEPADE
 * @author Leonel Antonio López Valencia - 040119 
 * Roberto Alejandro Armijo Jímenez - 046719 
 * Sandra Natalia Menjívar Romero - 174218
 */

public class FrmVehiculos extends javax.swing.JInternalFrame {

    DaoVehiculo daoVe= new DaoVehiculo();
    Vehiculo ve = new Vehiculo();
    Categoria categoria = new Categoria();
    UnidadMedida unidadMedida = new UnidadMedida();
    DaoCategoria daoCat=new DaoCategoria();
    DaoUnidadMedida daoUni=new DaoUnidadMedida();
    ValidarCampos vc=new ValidarCampos();
    String rutaModificado;
    
    public FrmVehiculos() {
        initComponents();
        mostrar(daoVe.mostrarVehiculos());
        this.txtCodigoVe.setEnabled(false);
        cargarCombo(comboCategoria1, daoCat.mostrarCategoria());
        cargarCombo2(comboUnidad, daoUni.mostrarUnidadMedidas());
    }
    
    public void mostrar(List<Vehiculo> lista) {
        DefaultTableModel tabla;
        String []columnas ={"Código","Categoria","Nombre","Color", "Marca","Modelo","Puertas","Precio","Unidad de Medida","Foto"};
        tabla = new DefaultTableModel(null, columnas);
        Object datos[] = new Object[10];

        try {
            for (int i = 0; i < lista.size(); i++) {
                ve = (Vehiculo) lista.get(i);
                datos[0] = ve.getIdVehiculo();
                datos[1] = ve.getCategoria().getNombreCategoria();
                datos[2] = ve.getNombre();
                datos[3] = ve.getColor();
                datos[4] = ve.getMarca();
                datos[5] = ve.getModelo();
                datos[6] = ve.getNumPuertas();
                datos[7]=  ve.getPrecio();
                datos[8] = ve.getUnidadMedida().getNombre();
                datos[9] = ve.getFoto(); 
                tabla.addRow(datos);
            }
            this.tablaVehiculos.setModel(tabla);
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error al mostrar en formulario " + e.getMessage());
        }
    }
    
    public void llenarTabla() {
        int fila = this.tablaVehiculos.getSelectedRow();
        if (fila > -1) {
            this.txtCodigoVe.setText(String.valueOf(this.tablaVehiculos.getValueAt(fila, 0)));
            String cate = String.valueOf(this.tablaVehiculos.getValueAt(fila, 1));
            comboCategoria1.getModel().setSelectedItem(cate);
            this.txtNombre.setText(String.valueOf(this.tablaVehiculos.getValueAt(fila, 2)));
            this.txtColor.setText(String.valueOf(this.tablaVehiculos.getValueAt(fila, 3)));
            this.txtMarca.setText(String.valueOf(this.tablaVehiculos.getValueAt(fila, 4)));
            this.txtModelo.setText(String.valueOf(this.tablaVehiculos.getValueAt(fila, 5)));
            this.txtPuertas.setText(String.valueOf(this.tablaVehiculos.getValueAt(fila, 6)));
            this.txtPrecio.setText(String.valueOf(this.tablaVehiculos.getValueAt(fila, 7)));
            String unid = String.valueOf(this.tablaVehiculos.getValueAt(fila, 8));
            comboUnidad.getModel().setSelectedItem(unid);
            //Foto
             if(rutaModificado==null)
            {
                rutaModificado = "Sin Foto";
            }
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(tablaVehiculos.getValueAt(fila, 9).toString()).getImage().getScaledInstance(190, 190, Image.SCALE_DEFAULT));
            btnFoto.setText("");
            btnFoto.setIcon(imageIcon); 
            rutaModificado=ve.getFoto();
            this.txtCodigoVe.setEnabled(false);
        }
    }
    
    private void cargarCombo(JComboBox combo, List<Categoria> list) {
        for (Categoria item : list) {
            combo.addItem(new ComboItem(item.getIdCategoria(),
                    item.getNombreCategoria()));
        }
    }
    
    private void cargarCombo2(JComboBox combo, List<UnidadMedida> list) {
        for (UnidadMedida item : list) {
            combo.addItem(new ComboItem(item.getIdUnidadMedida(),
                    item.getNombre()));
        }
    }
    
     public void insertar() {
        try {
            ve.setIdVehiculo(Integer.parseInt(this.txtCodigoVe.getText()));
            
            String categoria = comboCategoria1.getSelectedItem().toString();
            ComboItem item = new ComboItem();
            for (int i = 0; i < comboCategoria1.getItemCount(); i++) {
                if (categoria.equals(comboCategoria1.getItemAt(i).toString())) {
                    item = comboCategoria1.getModel().getElementAt(i);
                }
            }
            Categoria cat = new Categoria(item.getValue());
            ve.setCategoria(cat);
            ve.setNombre(this.txtNombre.getText());
            ve.setColor(this.txtColor.getText());
            ve.setMarca(this.txtMarca.getText());
            ve.setModelo(this.txtPrecio.getText());
            ve.setNombre(this.txtNombre.getText());
            ve.setNumPuertas(Integer.parseInt(this.txtPuertas.getText()));
            ve.setPrecio(Double.parseDouble(txtPrecio.getText()));
            String unidad = comboUnidad.getSelectedItem().toString();
            ComboItem item2 = new ComboItem();
            for (int i = 0; i < comboUnidad.getItemCount(); i++) {
                if (unidad.equals(comboUnidad.getItemAt(i).toString())) {
                    item2 = comboUnidad.getModel().getElementAt(i);
                }
            }
            UnidadMedida cat2 = new UnidadMedida(item2.getValue());
            ve.setUnidadMedida(cat2);
            ve.setFoto(rutaModificado);
            int respuesta = JOptionPane.showConfirmDialog(this, "Desea Insertar?",
                    "Insertar", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.OK_OPTION) {
                daoVe.insertarVehiculo(ve);
                JOptionPane.showMessageDialog(null, "Datos insertados con exito");
                mostrar(daoVe.mostrarVehiculos());
                limpiar();
            } else {
                mostrar(daoVe.mostrarVehiculos());
                limpiar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar" + e.getMessage());
        }
    }
     
    public void limpiar() {
        this.txtNombre.setText("");
        this.txtMarca.setText("");
        this.txtPrecio.setText("");
        this.txtPuertas.setText("");
        this.txtModelo.setText("");
        this.txtColor.setText("");
        this.comboCategoria1.setSelectedIndex(0);
        this.comboUnidad.setSelectedIndex(0);
        this.btnFoto.setIcon(null);
        this.txtCodigoVe.setText(String.valueOf(daoVe.ultimoId()));
    }
    
    public void modificar() {
        try {
             ve.setIdVehiculo(Integer.parseInt(this.txtCodigoVe.getText()));
            
            String categoria = comboCategoria1.getSelectedItem().toString();
            ComboItem item = new ComboItem();
            for (int i = 0; i < comboCategoria1.getItemCount(); i++) {
                if (categoria.equals(comboCategoria1.getItemAt(i).toString())) {
                    item = comboCategoria1.getModel().getElementAt(i);
                }
            }
            Categoria cat = new Categoria(item.getValue());
            ve.setCategoria(cat);
            ve.setNombre(this.txtNombre.getText());
            ve.setColor(this.txtColor.getText());
            ve.setMarca(this.txtMarca.getText());
            ve.setModelo(this.txtModelo.getText());
            ve.setNombre(this.txtNombre.getText());
            ve.setNumPuertas(Integer.parseInt(this.txtPuertas.getText()));
            ve.setPrecio(Double.parseDouble(txtPrecio.getText()));
            ve.setFoto(rutaModificado);
            
            String unidad = comboUnidad.getSelectedItem().toString();
            ComboItem item2 = new ComboItem();
            for (int i = 0; i < comboUnidad.getItemCount(); i++) {
                if (unidad.equals(comboUnidad.getItemAt(i).toString())) {
                    item2 = comboUnidad.getModel().getElementAt(i);
                }
            }
            UnidadMedida cat2 = new UnidadMedida(item2.getValue());
            ve.setUnidadMedida(cat2);

            int respuesta = JOptionPane.showConfirmDialog(this, "Desea modificar?",
                    "Modificar", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.OK_OPTION) {
                daoVe.modificarVehiculo(ve);
                JOptionPane.showMessageDialog(null, "Datos modificados con exito");
                mostrar(daoVe.mostrarVehiculos());
                limpiar();
            } else {
               mostrar(daoVe.mostrarVehiculos());
                limpiar();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public void eliminar(){
        try {
            ve.setIdVehiculo(Integer.parseInt(this.txtCodigoVe.getText()));
            int respuesta = JOptionPane.showConfirmDialog(this, "Desea eliminar?",
                    "Eliminar", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.OK_OPTION) {
                daoVe.eliminarVehiculo(ve);
                JOptionPane.showMessageDialog(null, "Datos eliminados con exito");
                mostrar(daoVe.mostrarVehiculos());
                limpiar();
            } else {
               mostrar(daoVe.mostrarVehiculos());
                limpiar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar en formulario");
        }
    }
    
      private String getFileExtension(String name) {
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVehiculos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCodigoVe = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        txtColor = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        txtPuertas = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        comboUnidad = new javax.swing.JComboBox<>();
        comboCategoria1 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        txtUsuarioid = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnRefrescar = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JLabel();
        btnModificar = new javax.swing.JLabel();
        btnInsertar = new javax.swing.JLabel();
        btnFoto = new javax.swing.JButton();
        btnCargarFoto = new javax.swing.JLabel();

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
        jLabel2.setText("Vehiculos");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tablaVehiculos.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        tablaVehiculos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaVehiculos.setToolTipText("Para ver el detalle completo, seleccione una empresa y presione Enter.");
        tablaVehiculos.setRowHeight(25);
        tablaVehiculos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaVehiculosMouseClicked(evt);
            }
        });
        tablaVehiculos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Enter(evt);
            }
        });
        jScrollPane1.setViewportView(tablaVehiculos);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator3.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 145, 343, 10));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel4.setText("Marca");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 116, -1, -1));

        txtMarca.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtMarca.setBorder(null);
        txtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaKeyTyped(evt);
            }
        });
        jPanel3.add(txtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 111, 263, 28));

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel6.setText("ID");
        jLabel6.setToolTipText("");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 16, -1, -1));

        txtCodigoVe.setBackground(new java.awt.Color(255, 255, 255));
        txtCodigoVe.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtCodigoVe.setBorder(null);
        jPanel3.add(txtCodigoVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 11, 260, 28));

        jSeparator8.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 303, 10));

        jSeparator9.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 95, 343, 10));

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel8.setText("Categoría");
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
        jPanel3.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(427, 11, 300, 28));

        jSeparator11.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 45, 360, 10));

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel9.setText("Color");
        jLabel9.setToolTipText("");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 66, -1, -1));

        txtColor.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtColor.setBorder(null);
        txtColor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtColorKeyTyped(evt);
            }
        });
        jPanel3.add(txtColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(427, 61, 300, 28));

        jSeparator12.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 95, 360, 10));

        jSeparator4.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 360, 10));

        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel13.setText("Modelo");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, -1, -1));

        txtPrecio.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtPrecio.setBorder(null);
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });
        jPanel3.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 160, 90, 28));

        jSeparator5.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 343, 10));

        jLabel14.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel14.setText("No. puertas");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        txtPuertas.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtPuertas.setBorder(null);
        txtPuertas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPuertasKeyTyped(evt);
            }
        });
        jPanel3.add(txtPuertas, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 263, 28));

        jSeparator6.setForeground(new java.awt.Color(49, 57, 69));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, 360, 10));

        jLabel15.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel15.setText("Precio:");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 160, -1, -1));

        jPanel3.add(comboUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, 120, 30));

        jPanel3.add(comboCategoria1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 220, 30));

        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel16.setText("Peso");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, -1, -1));

        txtModelo.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtModelo.setBorder(null);
        txtModelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtModeloKeyTyped(evt);
            }
        });
        jPanel3.add(txtModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(433, 110, 290, 28));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel12.setText("Buscar ID");
        jLabel12.setToolTipText("");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

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
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarMouseClicked(evt);
            }
        });
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel4.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 66, 26));

        btnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_refresh_36px.png"))); // NOI18N
        btnRefrescar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefrescarMouseClicked(evt);
            }
        });
        jPanel4.add(btnRefrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, -1, -1));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_delete_bin_36px.png"))); // NOI18N
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });
        jPanel4.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, -1, -1));

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_edit_36px.png"))); // NOI18N
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarMouseClicked(evt);
            }
        });
        jPanel4.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, -1, -1));

        btnInsertar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_add_36px.png"))); // NOI18N
        btnInsertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInsertarMouseClicked(evt);
            }
        });
        jPanel4.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, -1, -1));

        btnFoto.setText("Foto");
        btnFoto.setBorderPainted(false);
        btnFoto.setContentAreaFilled(false);
        btnFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFotoActionPerformed(evt);
            }
        });

        btnCargarFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/recursos/icons8_camera_identification_48px_2.png"))); // NOI18N
        btnCargarFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCargarFotoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1)
                .addGap(14, 14, 14))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(btnCargarFoto)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(34, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(btnCargarFoto))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
       
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFotoActionPerformed

    }//GEN-LAST:event_btnFotoActionPerformed

    private void Enter(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Enter

        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            int row = tablaVehiculos.getSelectedRow();
            Integer id_empresa=(Integer)tablaVehiculos.getValueAt(row, 0);

            //new Detalle_Empresa(id_empresa).setVisible(true);

            super.dispose();
        }
    }//GEN-LAST:event_Enter

    private void tablaVehiculosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVehiculosMouseClicked
       llenarTabla();
    }//GEN-LAST:event_tablaVehiculosMouseClicked

    private void btnRefrescarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefrescarMouseClicked
       mostrar(daoVe.mostrarVehiculos());
       limpiar();
    }//GEN-LAST:event_btnRefrescarMouseClicked

    private void btnCargarFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCargarFotoMouseClicked
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
                    File renombrado = new File(txtMarca.getText()+".png"); //las fotos tendran nombres de marcas 
                    archivo.renameTo(renombrado);
                }else if(extension.equals("jpg")){
                    File renombrado = new File(txtMarca.getText()+".jpg");
                    archivo.renameTo(renombrado);
                }else if(extension.equals("jpeg")){
                    File renombrado = new File(txtMarca.getText()+".jpeg");
                    archivo.renameTo(renombrado);
                }

                rutaModificado = archivo.getAbsolutePath();
                String root = new File("").getAbsolutePath();
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(rutaModificado).getImage().getScaledInstance(190, 190, Image.SCALE_DEFAULT));
                btnFoto.setText("");
                btnFoto.setIcon(imageIcon);
                Files.copy(Paths.get(rutaModificado), Paths.get(root+"/src/com/recursos/fotosVehiculos").resolve(archivo.getName()), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                System.out.println("Error: "+e);
            }
        }
    }//GEN-LAST:event_btnCargarFotoMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        eliminar();
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
       modificar();
    }//GEN-LAST:event_btnModificarMouseClicked

    private void btnInsertarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertarMouseClicked
        insertar();
    }//GEN-LAST:event_btnInsertarMouseClicked

    private void btnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseClicked
         mostrar(daoVe.buscarVehiculo(txtBuscar.getText()));
    }//GEN-LAST:event_btnBuscarMouseClicked

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped

    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtColorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColorKeyTyped
        vc.wordsOnly(evt);
    }//GEN-LAST:event_txtColorKeyTyped

    private void txtModeloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModeloKeyTyped

    }//GEN-LAST:event_txtModeloKeyTyped

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        vc.numberAndPoint(evt, txtPrecio);
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void txtPuertasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPuertasKeyTyped
       vc.numbersOnly(evt);
    }//GEN-LAST:event_txtPuertasKeyTyped

    private void txtMarcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyTyped
        vc.wordsOnly(evt);
    }//GEN-LAST:event_txtMarcaKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel btnCargarFoto;
    private javax.swing.JLabel btnEliminar;
    private javax.swing.JButton btnFoto;
    private javax.swing.JLabel btnInsertar;
    private javax.swing.JLabel btnModificar;
    private javax.swing.JLabel btnRefrescar;
    private javax.swing.JComboBox<ComboItem> comboCategoria1;
    private javax.swing.JComboBox<ComboItem> comboUnidad;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable tablaVehiculos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigoVe;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtPuertas;
    private javax.swing.JLabel txtUsuarioid;
    // End of variables declaration//GEN-END:variables
}

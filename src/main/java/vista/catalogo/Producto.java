package vista.catalogo;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Catalogoproducto;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Image;

public class Producto extends javax.swing.JPanel {
    private List<Catalogoproducto> productos = new ArrayList<>();
    private List<JCheckBox> checkBoxes = new ArrayList<>();
    private JScrollPane scrollPane;
    private JPanel panelPrincipal;

    public Producto(JFrame jFrame, boolean par) {
        initComponents();
        initCustomComponents();
    }

    private void initCustomComponents() {
        // Configuración del panel principal
        this.setBackground(new Color(242, 242, 242));
        this.setLayout(new BorderLayout());
        
        // Configuración del panel de productos
        PProductos.setBackground(new Color(242, 242, 242));
        PProductos.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        
        // Configuración del panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(242, 242, 242));
        panelBotones.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        panelBotones.add(volver);
        panelBotones.add(Nueva);
        panelBotones.add(Eliminar);
        
        // Configuración del scroll pane
        scrollPane = new JScrollPane(PProductos);
        scrollPane.setBackground(new Color(242, 242, 242));
        scrollPane.getViewport().setBackground(new Color(242, 242, 242));
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        // Agregar componentes al panel principal
        this.add(panelBotones, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        
        // Configuración de botones
        configurarBoton(Nueva);
        configurarBoton(Eliminar);
        configurarBoton(volver);
    }

    private void configurarBoton(rojeru_san.RSButtonRiple boton) {
        boton.setBackground(new Color(46, 49, 82));
        boton.setForeground(Color.WHITE);
        boton.setColorHover(new Color(70, 73, 106));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void agregarProducto(Catalogoproducto producto) {
        productos.add(producto);
        actualizarPanelProductos();
    }

    void actualizarPanelProductos() {
        PProductos.removeAll();
        PProductos.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        checkBoxes.clear();

        Color colorTarjeta = new Color(242, 242, 242);

        for (Catalogoproducto prod : productos) {
            JPanel panelProducto = crearPanelProducto(prod, colorTarjeta);
            PProductos.add(panelProducto);
        }

        PProductos.revalidate();
        PProductos.repaint();
        scrollPane.getVerticalScrollBar().setValue(0);
    }

    private JPanel crearPanelProducto(Catalogoproducto prod, Color colorTarjeta) {
        JPanel panelProducto = new JPanel(new BorderLayout());
        panelProducto.setPreferredSize(new Dimension(200, 240));
        panelProducto.setBackground(colorTarjeta);
        panelProducto.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Panel superior con checkbox y botón de editar
        panelProducto.add(crearPanelSuperior(prod), BorderLayout.NORTH);
        
        // Imagen del producto
        panelProducto.add(crearPanelImagen(prod), BorderLayout.CENTER);
        
        // Nombre del producto
        panelProducto.add(crearLabelNombre(prod), BorderLayout.SOUTH);

        return panelProducto;
    }

    private JPanel crearPanelSuperior(Catalogoproducto prod) {
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setOpaque(false);

        // Checkbox para selección
        JCheckBox checkBox = new JCheckBox();
        checkBox.setOpaque(false);
        checkBox.putClientProperty("producto", prod);
        panelSuperior.add(checkBox, BorderLayout.WEST);
        checkBoxes.add(checkBox);

        // Botón de editar
        JButton btnEditar = crearBotonEditar(prod);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnEditar);
        panelSuperior.add(buttonPanel, BorderLayout.EAST);

        return panelSuperior;
    }

    private JButton crearBotonEditar(Catalogoproducto prod) {
        JButton btnEditar = new JButton();
        btnEditar.setIcon(new ImageIcon(getClass().getResource("/catalogo/pencil1.png")));
        btnEditar.setContentAreaFilled(false);
        btnEditar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 2),
            BorderFactory.createEmptyBorder(2, 2, 2, 2)
        ));
        btnEditar.setBackground(new Color(46, 49, 82));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setFocusPainted(false);
        btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEditar.addActionListener(e -> abrirEditarProducto(prod));
        
        return btnEditar;
    }

    private JPanel crearPanelImagen(Catalogoproducto prod) {
        JPanel imagenContainer = new JPanel(new BorderLayout());
        imagenContainer.setOpaque(false);

        JLabel imagenLabel = new JLabel();
        imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        if (prod.getImagen() != null) {
            ImageIcon icon = new ImageIcon(prod.getImagen().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
            imagenLabel.setIcon(icon);
        }
        imagenContainer.add(imagenLabel, BorderLayout.CENTER);
        
        return imagenContainer;
    }

    private JLabel crearLabelNombre(Catalogoproducto prod) {
        JLabel nombreLabel = new JLabel(prod.getNombre(), SwingConstants.CENTER);
        nombreLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
        nombreLabel.setForeground(Color.BLACK);
        return nombreLabel;
    }

    private void abrirEditarProducto(Catalogoproducto producto) {
        productoEditar dialog = new productoEditar(
            (JFrame)SwingUtilities.getWindowAncestor(this), 
            true, 
            producto
        );
        dialog.setVisible(true);
        
        if (dialog.getProductoEditado() != null) {
            actualizarProducto(dialog.getProductoEditado());
        }
    }

    public void actualizarProducto(Catalogoproducto productoActualizado) {
        int index = productos.indexOf(productoActualizado);
        if (index != -1) {
            productos.set(index, productoActualizado);
            actualizarPanelProductos();
        }
    }

    public void cargarProductosDeCategoria(int idCategoria) {
        productos.clear();
        
        // Aquí deberías implementar la carga real de productos desde tu fuente de datos
        List<Catalogoproducto> todosProductos = obtenerTodosLosProductos();
        for (Catalogoproducto producto : todosProductos) {
            if (producto.getIdCategoria() == idCategoria) {
                productos.add(producto);
            }
        }
        
        actualizarPanelProductos();
    }

    private List<Catalogoproducto> obtenerTodosLosProductos() {
        // Implementa este método para obtener los productos de tu base de datos o almacenamiento
        return new ArrayList<>();
    }

 




    private JPanel findContenedor(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JPanel && "contenedor".equals(((JPanel) comp).getName())) {
                return (JPanel) comp;
            }
            if (comp instanceof Container) {
                JPanel found = findContenedor((Container) comp);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

  

       
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Nueva = new rojeru_san.RSButtonRiple();
        volver = new rojeru_san.RSButtonRiple();
        Eliminar = new rojeru_san.RSButtonRiple();
        PProductos = new javax.swing.JPanel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Nueva.setBackground(new java.awt.Color(46, 49, 82));
        Nueva.setText("Añadir");
        Nueva.setColorHover(new java.awt.Color(0, 153, 51));
        Nueva.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        Nueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevaActionPerformed(evt);
            }
        });
        add(Nueva, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 20, 140, -1));

        volver.setBackground(new java.awt.Color(46, 49, 82));
        volver.setText("volver");
        volver.setColorHover(new java.awt.Color(0, 153, 51));
        volver.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });
        add(volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 16, 140, -1));

        Eliminar.setBackground(new java.awt.Color(46, 49, 82));
        Eliminar.setText("Eliminar");
        Eliminar.setColorHover(new java.awt.Color(0, 153, 51));
        Eliminar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        add(Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 20, 140, -1));

        PProductos.setLayout(new java.awt.BorderLayout());
        add(PProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 640));
    }// </editor-fold>//GEN-END:initComponents

    private void NuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevaActionPerformed
    catalogoNuevo dialog = new catalogoNuevo((JFrame)this.getTopLevelAncestor(), true);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        
        Catalogoproducto nuevoProducto = dialog.getProductoCreado();
        if (nuevoProducto != null) {
            agregarProducto(nuevoProducto);
        }

    }//GEN-LAST:event_NuevaActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
     
        List<Catalogoproducto> productosAEliminar = new ArrayList<>();
    
        for (JCheckBox checkBox : checkBoxes) {
            if (checkBox.isSelected()) {
                Catalogoproducto producto = (Catalogoproducto) checkBox.getClientProperty("producto");
                productosAEliminar.add(producto);
            }
        }
        
        productos.removeAll(productosAEliminar);
        actualizarPanelProductos();
     
    }//GEN-LAST:event_EliminarActionPerformed
                         

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
 JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        JPanel contenedor = findContenedor(frame);
        
        if (contenedor != null) {
            contenedor.removeAll();
            catalogo catalogoPanel = new catalogo(frame, true);
            contenedor.add(catalogoPanel);
            contenedor.revalidate();
            contenedor.repaint();
        }
    }//GEN-LAST:event_volverActionPerformed

                      

                                  


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButtonRiple Eliminar;
    private rojeru_san.RSButtonRiple Nueva;
    private javax.swing.JPanel PProductos;
    private rojeru_san.RSButtonRiple volver;
    // End of variables declaration//GEN-END:variables
}


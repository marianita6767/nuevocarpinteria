package vista.catalogo;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Image;
import modelo.producto;

public class Producto extends javax.swing.JPanel {
    private List<producto> productos = new ArrayList<>();
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
    PProductos.setLayout(new GridLayout(0, 4, 20, 20)); // Cambiado a GridLayout con 4 columnas y espacios

    // Ajustar columnas dinámicamente según tamaño para responsividad básica
    PProductos.addComponentListener(new java.awt.event.ComponentAdapter() {
        @Override
        public void componentResized(java.awt.event.ComponentEvent evt) {
            int width = PProductos.getWidth();
            int columnas;
            if (width < 600) {
                columnas = 2;
            } else {
                columnas = 4;
            }
            ((GridLayout)PProductos.getLayout()).setColumns(columnas);
            PProductos.revalidate();
        }
    });
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
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Mejor evitar scroll horizontal

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

    public void agregarProducto(producto producto) {
        productos.add(producto);
        actualizarPanelProductos();
    }

    void actualizarPanelProductos() {
        PProductos.removeAll();
    // Ya no seteamos layout aquí porque se hace en initCustomComponents
    checkBoxes.clear();

    Color colorTarjeta = new Color(242, 242, 242);

    for (producto prod : productos) {
        JPanel panelProducto = crearPanelProducto(prod, colorTarjeta);
        PProductos.add(panelProducto);
    }

    PProductos.revalidate();
    PProductos.repaint();
    scrollPane.getVerticalScrollBar().setValue(0);
    }

    private JPanel crearPanelProducto(producto prod, Color colorTarjeta) {
           JPanel panelProducto = new JPanel(new BorderLayout());
    panelProducto.setPreferredSize(new Dimension(280, 320)); // Tamaño similar a la imagen
    panelProducto.setBackground(Color.WHITE);
    panelProducto.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    panelProducto.setLayout(new BorderLayout());

    // Panel para la imagen (solo imagen)
    JPanel panelImagen = new JPanel(new BorderLayout());
    panelImagen.setPreferredSize(new Dimension(280, 280));
    panelImagen.setBackground(Color.WHITE);

    JLabel imagenLabel = new JLabel();
    imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);
    if (prod.getImagen() != null) {
        ImageIcon icon = new ImageIcon(prod.getImagen().getScaledInstance(280, 280, Image.SCALE_SMOOTH));
        imagenLabel.setIcon(icon);
    }
    panelImagen.add(imagenLabel, BorderLayout.CENTER);

    panelProducto.add(panelImagen, BorderLayout.CENTER);

    // Panel inferior con nombre y botón editar a la derecha
    JPanel panelInferior = new JPanel(new BorderLayout());
    panelInferior.setBackground(Color.WHITE);
    panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Label nombre producto
    JLabel nombreLabel = new JLabel(prod.getNombre());
    nombreLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 28));
    nombreLabel.setForeground(Color.BLACK);
    nombreLabel.setVerticalAlignment(SwingConstants.CENTER);

    panelInferior.add(nombreLabel, BorderLayout.WEST);

    // Botón editar circular blanco con icono negro
    JButton btnEditar = new JButton();
    btnEditar.setPreferredSize(new Dimension(48, 48));
    btnEditar.setBackground(Color.WHITE);
    btnEditar.setFocusPainted(false);
    btnEditar.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
    btnEditar.setContentAreaFilled(true);
    btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    btnEditar.setToolTipText("Editar producto");
    btnEditar.setIcon(new ImageIcon(getClass().getResource("/catalogo/pencil1.png")));
    btnEditar.addActionListener(e -> abrirEditarProducto(prod));

    panelInferior.add(btnEditar, BorderLayout.EAST);

    panelProducto.add(panelInferior, BorderLayout.SOUTH);

    return panelProducto;
    }

    private JPanel crearPanelSuperior(producto prod) {
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

    private JButton crearBotonEditar(producto prod) {
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

    private JPanel crearPanelImagen(producto prod) {
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

    private JLabel crearLabelNombre(producto prod) {
        JLabel nombreLabel = new JLabel(prod.getNombre(), SwingConstants.CENTER);
        nombreLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
        nombreLabel.setForeground(Color.BLACK);
        return nombreLabel;
    }

    private void abrirEditarProducto(producto producto) {
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

    public void actualizarProducto(producto productoActualizado) {
        int index = productos.indexOf(productoActualizado);
        if (index != -1) {
            productos.set(index, productoActualizado);
            actualizarPanelProductos();
        }
    }

    public void cargarProductosDeCategoria(int idCategoria) {
        productos.clear();
        
        // Aquí deberías implementar la carga real de productos desde tu fuente de datos
        List<producto> todosProductos = obtenerTodosLosProductos();
        for (producto producto : todosProductos) {
            if (producto.getIdCategoria() == idCategoria) {
                productos.add(producto);
            }
        }
        
        actualizarPanelProductos();
    }

    private List<producto> obtenerTodosLosProductos() {
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
        
        producto nuevoProducto = dialog.getProductoCreado();
        if (nuevoProducto != null) {
            agregarProducto(nuevoProducto);
        }

    }//GEN-LAST:event_NuevaActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
     
        List<producto> productosAEliminar = new ArrayList<>();
    
        for (JCheckBox checkBox : checkBoxes) {
            if (checkBox.isSelected()) {
                producto producto = (producto) checkBox.getClientProperty("producto");
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

       //ol               

        //"                          


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButtonRiple Eliminar;
    private rojeru_san.RSButtonRiple Nueva;
    private javax.swing.JPanel PProductos;
    private rojeru_san.RSButtonRiple volver;
    // End of variables declaration//GEN-END:variables
}


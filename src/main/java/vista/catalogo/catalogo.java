package vista.catalogo;

import javax.swing.JFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Catalogocategoria;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class catalogo extends javax.swing.JPanel {

    private List<Catalogocategoria> categorias = new ArrayList<>();

    public catalogo(JFrame jFrame, boolean par) {
        initComponents();
        panelPrincipal = new javax.swing.JPanel();
        panelPrincipal.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 20));
        panelPrincipal.setBackground(new java.awt.Color(242, 242, 242));

        // Crea el JScrollPane que contendrá al panelPrincipal
        scrollPane = new JScrollPane(panelPrincipal);
        scrollPane.setViewportView(panelPrincipal);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Reemplaza la línea donde añades panelPrincipal a jPanel2
        jPanel2.add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 1030, 590));

    }

    public void agregarCategoria(Catalogocategoria categoria) { // Cambiado el parámetro
        categorias.add(categoria);
        actualizarPanelCategorias();

    }

void actualizarPanelCategorias() {
    // Limpiar el panel principal solo una vez al inicio
    panelPrincipal.removeAll();
    
    // Configurar layout principal (vertical para las filas)
    panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
    panelPrincipal.setBackground(new Color(242, 242, 242));

    Color colorTarjeta = new Color(242, 242, 242);
    int tarjetasPorFila = 4;
    JPanel filaActual = null;

    for (int i = 0; i < categorias.size(); i++) {
        Catalogocategoria cat = categorias.get(i);
        
        // Crear nueva fila cada 4 tarjetas o al inicio
        if (i % tarjetasPorFila == 0) {
            filaActual = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
            filaActual.setBackground(new Color(242, 242, 242));
            filaActual.setAlignmentX(Component.LEFT_ALIGNMENT);
            panelPrincipal.add(filaActual);
        }

        // Crear la tarjeta individual
        JPanel panelCategoria = new JPanel();
        panelCategoria.setLayout(new BorderLayout());
        panelCategoria.setPreferredSize(new Dimension(200, 240));
        panelCategoria.setBackground(colorTarjeta);
        panelCategoria.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(234, 234, 234)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Panel superior (checkbox y botón editar)
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setOpaque(false);

        JCheckBox checkBox = new JCheckBox();
        checkBox.setOpaque(false);
        checkBox.setName(String.valueOf(i));
        panelSuperior.add(checkBox, BorderLayout.WEST);

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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnEditar);
        panelSuperior.add(buttonPanel, BorderLayout.EAST);

        panelCategoria.add(panelSuperior, BorderLayout.NORTH);

        // Panel de imagen
        JPanel imagenContainer = new JPanel(new BorderLayout());
        imagenContainer.setOpaque(false);

        JLabel imagenLabel = new JLabel();
        imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        if (cat.getImagen() != null) {
            ImageIcon icon = new ImageIcon(cat.getImagen().getScaledInstance(180, 140, Image.SCALE_SMOOTH));
            imagenLabel.setIcon(icon);
        }
        imagenContainer.add(imagenLabel, BorderLayout.CENTER);
        panelCategoria.add(imagenContainer, BorderLayout.CENTER);

        // Nombre de la categoría
        JLabel nombreLabel = new JLabel(cat.getNombre(), SwingConstants.CENTER);
        nombreLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
        nombreLabel.setForeground(Color.BLACK);
        nombreLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nombreLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mostrarProductosDeCategoria(cat);
            }
        });
        panelCategoria.add(nombreLabel, BorderLayout.SOUTH);

        // Evento para el botón editar
        btnEditar.addActionListener(e -> abrirEditarCategoria(cat));

        // Añadir tarjeta a la fila actual
        if (filaActual != null) {
            filaActual.add(panelCategoria);
        }
    }

    // Asegurar que la última fila tenga 4 elementos (para alineación)
    if (filaActual != null && filaActual.getComponentCount() > 0 && 
        filaActual.getComponentCount() < tarjetasPorFila) {
        int faltantes = tarjetasPorFila - filaActual.getComponentCount();
        for (int i = 0; i < faltantes; i++) {
            JPanel panelVacio = new JPanel();
            panelVacio.setPreferredSize(new Dimension(200, 240));
            panelVacio.setOpaque(false);
            filaActual.add(panelVacio);
        }
    }

    // Actualizar la visualización
    panelPrincipal.revalidate();
    panelPrincipal.repaint();
    scrollPane.getVerticalScrollBar().setValue(0);
}

    private void abrirEditarCategoria(Catalogocategoria categoria) {
        categoriaeditar dialog = new categoriaeditar(
                (java.awt.Frame) SwingUtilities.getWindowAncestor(this),
                true,
                this, // Referencia al catálogo
                categoria
        // Categoría a editar
        );

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public void actualizarCategoria(Catalogocategoria categoriaActualizada) {
        int index = categorias.indexOf(categoriaActualizada);
        if (index != -1) {
            categorias.set(index, categoriaActualizada);
            actualizarPanelCategorias();
        }

    }

    private void mostrarProductosDeCategoria(Catalogocategoria categoria) {
        // Obtener el JFrame padre
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);

        // Buscar el panel contenedor recursivamente
        JPanel contenedor = findContenedor(frame);

        if (contenedor != null) {
            // Crear el panel de productos
            Producto productoPanel = new Producto(frame, true);
            productoPanel.setSize(contenedor.getWidth(), contenedor.getHeight());
            productoPanel.setLocation(0, 0);

            // Limpiar y mostrar el panel de productos
            contenedor.removeAll();
            contenedor.add(productoPanel);
            contenedor.revalidate();
            contenedor.repaint();

            // Cargar los productos de la categoría seleccionada
            productoPanel.cargarProductosDeCategoria(categoria.getId());
        }
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        panelPrincipal = new javax.swing.JPanel();
        Añadir1 = new rojeru_san.RSButtonRiple();
        Eliminar = new rojeru_san.RSButtonRiple();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Añadir1.setBackground(new java.awt.Color(46, 49, 82));
        Añadir1.setText("categoria");
        Añadir1.setColorHover(new java.awt.Color(0, 153, 51));
        Añadir1.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        Añadir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Añadir1ActionPerformed(evt);
            }
        });

        Eliminar.setBackground(new java.awt.Color(46, 49, 82));
        Eliminar.setText("Eliminar");
        Eliminar.setColorHover(new java.awt.Color(0, 153, 51));
        Eliminar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addContainerGap(667, Short.MAX_VALUE)
                .addComponent(Añadir1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Añadir1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(587, Short.MAX_VALUE))
        );

        jPanel2.add(panelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 1030, 650));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 710));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1089, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1089, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 706, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Añadir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Añadir1ActionPerformed
        // TODO add your handling code here:

        catalogocategoria dialog = new catalogocategoria(
                new javax.swing.JFrame(),
                true,
                this // Pasa la referencia al catálogo actual
        );
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_Añadir1ActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        // TODO add your handling code here:
        // Crear lista de categorías a eliminar
        List<Catalogocategoria> categoriasAEliminar = new ArrayList<>();

        // Recorrer todos los componentes del panelPrincipal
        for (Component comp : panelPrincipal.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel panelCategoria = (JPanel) comp;

                // Buscar el checkbox en el panel
                for (Component innerComp : panelCategoria.getComponents()) {
                    if (innerComp instanceof JPanel) {
                        JPanel panelSuperior = (JPanel) innerComp;
                        for (Component checkComp : panelSuperior.getComponents()) {
                            if (checkComp instanceof JCheckBox) {
                                JCheckBox checkBox = (JCheckBox) checkComp;
                                if (checkBox.isSelected()) {
                                    int index = Integer.parseInt(checkBox.getName());
                                    categoriasAEliminar.add(categorias.get(index));
                                }
                            }
                        }
                    }
                }
            }
        }

        // Eliminar las categorías seleccionadas
        categorias.removeAll(categoriasAEliminar);

        // Actualizar la vista
        actualizarPanelCategorias();

        // Mostrar mensaje de confirmación
        JOptionPane.showMessageDialog(this,
                categoriasAEliminar.size() + " categorías eliminadas",
                "Eliminación completada",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_EliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButtonRiple Añadir1;
    private rojeru_san.RSButtonRiple Eliminar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
private javax.swing.JScrollPane scrollPane;
}

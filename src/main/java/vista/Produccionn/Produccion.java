/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Produccionn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.Conexion;

/**
 *
 * @author pc
 */
public final class Produccion extends javax.swing.JPanel {

    /**
     * Creates new form produccionContenido
     */
    public Produccion(JFrame jFrame, boolean par) {
        initComponents();

        // Configuración básica de la tabla
        Tabla1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tabla1.setCellSelectionEnabled(false);
        Tabla1.setRowSelectionAllowed(true);
        Tabla1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        //lineas de la tabla
        Tabla1.setShowGrid(true);
        Tabla1.setGridColor(new Color(200, 200, 200));
        Tabla1.setShowHorizontalLines(true);
        Tabla1.setShowVerticalLines(true);
        Tabla1.setIntercellSpacing(new Dimension(1, 1));
        // Configura el modelo de tabla correctamente
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nombre", "Fecha inicio", "Fecha Final", "Estado", "Detalle"}
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class; // Esto ayuda al centrado uniforme
            }
        };
        Tabla1.setModel(model);

        // Color del texto para todas las celdas
        Color textColor = new Color(46, 49, 82);
        Tabla1.setForeground(textColor);

        // Configuración de colores de selección
        Color colorSeleccion = new Color(109, 160, 221);
        Tabla1.setSelectionBackground(colorSeleccion);
        Tabla1.setSelectionForeground(Color.WHITE); // Color del texto cuando está seleccionado

        // Centrar todo el contenido de la tabla
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setForeground(textColor);

        // Aplicar el renderizador centrado a todas las columnas
        for (int i = 0; i < Tabla1.getColumnCount(); i++) {
            Tabla1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Configura el renderizador especial para la columna de estado (sobrescribe el general)
        Tabla1.getColumnModel().getColumn(4).setCellRenderer(new EstadoTableCellRenderer());

        // Configura el renderizador especial para la columna "Ver" (sobrescribe el general)
        Tabla1.getColumnModel().getColumn(5).setCellRenderer(new VerTableCellRenderer());

        // Configura el buscador
        txtbuscar.getDocument().addDocumentListener(new BuscadorDocumentListener());

        // Carga los datos
        cargarTablaProduccion();
    }

// Renderizador para la columna de estado
    private class EstadoTableCellRenderer extends DefaultTableCellRenderer {

        public EstadoTableCellRenderer() {
            setHorizontalAlignment(JLabel.CENTER); // Centrar el texto
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            // Llamar al método padre primero
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (isSelected) {
                // Cuando está seleccionado, texto blanco y fondo de selección
                c.setForeground(Color.WHITE);
                c.setBackground(table.getSelectionBackground());
            } else {
                // Cuando no está seleccionado, mantener el color original del texto
                c.setForeground(new Color(46, 49, 82));

                // Aplicar colores de fondo según el estado
                String estado = (String) value;
                switch (estado) {
                    case "pendiente":
                        c.setBackground(new Color(255, 204, 204));
                        break;
                    case "proceso":
                        c.setBackground(new Color(255, 255, 153));
                        break;
                    case "finalizado":
                        c.setBackground(new Color(204, 255, 204));
                        break;
                    default:
                        c.setBackground(table.getBackground());
                        break;
                }
            }

            return c;
        }
    }

// Renderizador para la columna "Ver"
    private class VerTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            c.setForeground(isSelected ? Color.WHITE : Color.BLACK);
            setHorizontalAlignment(CENTER);
            setText("Ver");
            return c;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla1 = new RSMaterialComponent.RSTableMetro();
        txtbuscar = new RSMaterialComponent.RSTextFieldMaterialIcon();
        btnEditar = new RSMaterialComponent.RSButtonShape();
        btnNuevo = new RSMaterialComponent.RSButtonShape();
        btnElimi = new RSMaterialComponent.RSButtonShape();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1250, 630));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tabla1.setForeground(new java.awt.Color(255, 255, 255));
        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Tabla1.setAlignmentX(0.1F);
        Tabla1.setAlignmentY(0.1F);
        Tabla1.setBackgoundHead(new java.awt.Color(46, 49, 82));
        Tabla1.setBackgoundHover(new java.awt.Color(46, 49, 82));
        Tabla1.setColorBorderRows(new java.awt.Color(153, 153, 153));
        Tabla1.setColorPrimaryText(new java.awt.Color(46, 49, 82));
        Tabla1.setColorSecondary(new java.awt.Color(255, 255, 255));
        Tabla1.setColorSecundaryText(new java.awt.Color(46, 49, 82));
        Tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Tabla1MouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(Tabla1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1210, 490));

        txtbuscar.setBackground(new java.awt.Color(245, 245, 245));
        txtbuscar.setForeground(new java.awt.Color(29, 30, 91));
        txtbuscar.setColorIcon(new java.awt.Color(29, 30, 111));
        txtbuscar.setColorMaterial(new java.awt.Color(29, 30, 111));
        txtbuscar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        txtbuscar.setPlaceholder("Buscar");
        txtbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarActionPerformed(evt);
            }
        });
        jPanel1.add(txtbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 430, 40));

        btnEditar.setBackground(new java.awt.Color(46, 49, 82));
        btnEditar.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pencil (1).png"))); // NOI18N
        btnEditar.setText("   Editar");
        btnEditar.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnEditar.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnEditar.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, 120, 40));

        btnNuevo.setBackground(new java.awt.Color(46, 49, 82));
        btnNuevo.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (1).png"))); // NOI18N
        btnNuevo.setText("  Nuevo");
        btnNuevo.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnNuevo.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnNuevo.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, 120, 40));

        btnElimi.setBackground(new java.awt.Color(46, 49, 82));
        btnElimi.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnElimi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete (1).png"))); // NOI18N
        btnElimi.setText(" Eliminar");
        btnElimi.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnElimi.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnElimi.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnElimi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElimiActionPerformed(evt);
            }
        });
        jPanel1.add(btnElimi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 20, 120, 40));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
        filtrarTabla();
    }//GEN-LAST:event_txtbuscarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        EditProduccion dialog = new EditProduccion(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        cargarTablaProduccion();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        formuProduccion dialog = new formuProduccion(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        cargarTablaProduccion();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnElimiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElimiActionPerformed
// 1. Obtener filas seleccionadas
        int[] selectedRows = Tabla1.getSelectedRows();

        // 2. Validar si hay filas seleccionadas
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this,
                    "Por favor seleccione al menos una fila para eliminar",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 3. Mostrar confirmación
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea eliminar los " + selectedRows.length + " registros seleccionados?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        // 4. Si el usuario no confirma, salir
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // 5. Eliminar registros
        try (Connection con = new Conexion().getConnection()) {
            String sql = "DELETE FROM produccion WHERE id_produccion = ?";
            DefaultTableModel model = (DefaultTableModel) Tabla1.getModel();

            // Eliminar en orden inverso para evitar problemas con los índices
            for (int i = selectedRows.length - 1; i >= 0; i--) {
                int row = selectedRows[i];
                int id = (int) model.getValueAt(row, 0); // ID está en la columna 0

                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ps.executeUpdate();
                    model.removeRow(row); // Eliminar de la tabla visual
                }
            }

            JOptionPane.showMessageDialog(this,
                    "Registros eliminados correctamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al eliminar: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnElimiActionPerformed

    private void Tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla1MouseClicked
        int column = Tabla1.columnAtPoint(evt.getPoint());
    int row = Tabla1.rowAtPoint(evt.getPoint());

    if (column == 5) { // Columna "Ver"
        // Obtener datos de la fila
        int idProduccion = (int) Tabla1.getValueAt(row, 0);
        String nombre = (String) Tabla1.getValueAt(row, 1);
        String fechaInicio = (String) Tabla1.getValueAt(row, 2);
        String fechaFin = (String) Tabla1.getValueAt(row, 3);
        String estado = (String) Tabla1.getValueAt(row, 4);

        // Crear el panel de detalle
        DetalleProduProducto detallePanel = new DetalleProduProducto(
            idProduccion, nombre, fechaInicio, fechaFin, estado);
        
        // Configurar el botón de volver
        detallePanel.setVolverListener(e -> {
            removeAll();
            setLayout(new BorderLayout());
            add(jPanel1, BorderLayout.CENTER);
            revalidate();
            repaint();
        });

        // Reemplazar el contenido
        removeAll();
        setLayout(new BorderLayout());
        add(detallePanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    }//GEN-LAST:event_Tabla1MouseClicked

    private void Tabla1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Tabla1MouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSTableMetro Tabla1;
    private RSMaterialComponent.RSButtonShape btnEditar;
    private RSMaterialComponent.RSButtonShape btnElimi;
    private RSMaterialComponent.RSButtonShape btnNuevo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtbuscar;
    // End of variables declaration//GEN-END:variables

    private void filtrarTabla() {
        String textoBusqueda = txtbuscar.getText().trim().toLowerCase();
        DefaultTableModel modelo = (DefaultTableModel) Tabla1.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(modelo);
        Tabla1.setRowSorter(tr);

        if (textoBusqueda.isEmpty()) {
            tr.setRowFilter(null);
            return;
        }

        // Filtro para buscar en todas las columnas
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + textoBusqueda));
    }

    public void cargarTablaProduccion() {
        DefaultTableModel model = (DefaultTableModel) Tabla1.getModel();
        model.setRowCount(0); // Limpiar tabla

        // Verifica primero la conexión
        try (Connection test = new Conexion().getConnection()) {
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error de conexión: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "SELECT p.id_produccion, dp.descripcion, p.fecha_inicio, p.fecha_fin, p.estado, "
                + "dp.descripcion AS detalle "
                + "FROM produccion p "
                + "JOIN detalle_pedido dp ON p.detalle_pedido_iddetalle_pedido = dp.iddetalle_pedido "
                + "JOIN pedido pe ON dp.pedido_id_pedido = pe.id_pedido "
                + "ORDER BY p.fecha_inicio DESC";

        try (Connection con = new Conexion().getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_produccion"),
                    rs.getString("descripcion"),
                    sdf.format(rs.getDate("fecha_inicio")),
                    sdf.format(rs.getDate("fecha_fin")),
                    rs.getString("estado"),
                    "Ver"
                });
            }

            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this,
                        "No se encontraron registros de producción",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar datos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarDetalleCompleto(int idProduccion) {
        String sql = "SELECT dp.descripcion, dp.cantidad, dp.medidas, dp.precio_unitario "
                + "FROM produccion p "
                + "JOIN detalle_pedido dp ON p.detalle_pedido_iddetalle_pedido = dp.iddetalle_pedido "
                + "WHERE p.id_produccion = ?";

        try (Connection con = new Conexion().getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProduccion);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String mensaje = String.format("<html><b>Descripción:</b> %s<br>"
                        + "<b>Cantidad:</b> %s<br>"
                        + "<b>Medidas:</b> %s<br>"
                        + "<b>Precio unitario:</b> %.2f</html>",
                        rs.getString("descripcion"),
                        rs.getString("cantidad"),
                        rs.getString("medidas"),
                        rs.getDouble("precio_unitario"));
                JOptionPane.showMessageDialog(this, mensaje, "Detalle Completo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener detalles: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class BuscadorDocumentListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            filtrarTabla();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            filtrarTabla();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            filtrarTabla();
        }

        private void filtrarTabla() {
            String texto = txtbuscar.getText().trim().toLowerCase();
            DefaultTableModel modelo = (DefaultTableModel) Tabla1.getModel();
            TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(modelo);
            Tabla1.setRowSorter(tr);

            if (texto.isEmpty()) {
                tr.setRowFilter(null);
            } else {
                tr.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
            }
        }
    }

}

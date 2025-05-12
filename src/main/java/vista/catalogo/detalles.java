/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista.catalogo;


import java.awt.Image;
import javax.swing.ImageIcon;
import modelo.Catalogoproducto;
import vista.catalogo.catalogoNuevo;

public class detalles extends javax.swing.JDialog {

   

    public detalles(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
     
        initComponents();
      
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Nombre = new javax.swing.JLabel();
        material = new javax.swing.JLabel();
        alto = new javax.swing.JLabel();
        ancho = new javax.swing.JLabel();
        profundidad = new javax.swing.JLabel();
        color = new javax.swing.JLabel();
        ImgPrincipal = new javax.swing.JLabel();
        Imgenes = new javax.swing.JLabel();
        cerrar = new rojeru_san.RSButtonRiple();
        editar = new rojeru_san.RSButtonRiple();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 18, 362, 51));

        material.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        material.setText("Material:");
        jPanel1.add(material, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, 70, -1));

        alto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        alto.setText("Alto:");
        jPanel1.add(alto, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, 37, -1));

        ancho.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ancho.setText("Ancho:");
        jPanel1.add(ancho, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 50, -1));

        profundidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        profundidad.setText("Profundidad:");
        jPanel1.add(profundidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, 90, -1));

        color.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        color.setText("Color:");
        jPanel1.add(color, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, 50, -1));

        ImgPrincipal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(ImgPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 141, 255, 199));

        Imgenes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(Imgenes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 260, 80));

        cerrar.setBackground(new java.awt.Color(46, 49, 82));
        cerrar.setText("Cerrar");
        cerrar.setColorHover(new java.awt.Color(0, 153, 51));
        cerrar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarActionPerformed(evt);
            }
        });
        jPanel1.add(cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 470, 140, -1));

        editar.setBackground(new java.awt.Color(46, 49, 82));
        editar.setText("Editar");
        editar.setColorHover(new java.awt.Color(0, 153, 51));
        editar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });
        jPanel1.add(editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 470, 140, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarActionPerformed
dispose();
    }//GEN-LAST:event_cerrarActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
     

    }//GEN-LAST:event_editarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(detalles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(detalles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(detalles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(detalles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                detalles dialog = new detalles(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ImgPrincipal;
    private javax.swing.JLabel Imgenes;
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel alto;
    private javax.swing.JLabel ancho;
    private rojeru_san.RSButtonRiple cerrar;
    private javax.swing.JLabel color;
    private rojeru_san.RSButtonRiple editar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel material;
    private javax.swing.JLabel profundidad;
    // End of variables declaration//GEN-END:variables
}

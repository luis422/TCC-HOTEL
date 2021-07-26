package hotel;

import java.awt.Color;
import javax.swing.JOptionPane;

public class MessageStatus extends javax.swing.JFrame {

    String user;
    String name;

    public MessageStatus(String usuario, String nome, String message, String status) {
        initComponents();
        user = usuario;
        name = nome;
        if (message.equals("") || status.equals("")) {
            this.dispose();
        } else if (status.equals("erro") && message.length() > 0) {
            msgPanelTxt.setText(message);
            msgPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 2, false));
            ok.setBackground(new Color(226, 0, 0));
            msgPanelTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icon-error.png")));
            msgPanel.setVisible(true);
        } else if (status.equals("sucesso") && message.length() > 0) {
            msgPanelTxt.setText(message);
            msgPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 226, 175), 2, false));
            ok.setBackground(new Color(0, 226, 175));
            msgPanelTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icon-ok.png")));
            msgPanel.setVisible(true);
        } else if (status.equals("aviso") && message.length() > 0) {
            msgPanelTxt.setText(message);
            msgPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(253, 191, 0), 2, false));
            ok.setBackground(new Color(253, 191, 0));
            msgPanelTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icon-exclamation.png")));
            msgPanel.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Deu ruim aí!!!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        msgPanel = new javax.swing.JPanel();
        msgPanelTxt = new javax.swing.JLabel();
        ok = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName(""); // NOI18N
        setUndecorated(true);
        setSize(new java.awt.Dimension(480, 180));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        msgPanel.setBackground(new java.awt.Color(255, 255, 255));
        msgPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 226, 175)));
        msgPanel.setMaximumSize(new java.awt.Dimension(480, 170));
        msgPanel.setMinimumSize(new java.awt.Dimension(480, 170));
        msgPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        msgPanelTxt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        msgPanelTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        msgPanelTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icon-ok.png"))); // NOI18N
        msgPanelTxt.setText("Sucesso");
        msgPanel.add(msgPanelTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 43, 476, -1));

        ok.setBackground(new java.awt.Color(0, 226, 175));
        ok.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ok.setForeground(new java.awt.Color(255, 255, 255));
        ok.setText("OK");
        ok.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ok.setFocusable(false);
        ok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okMouseClicked(evt);
            }
        });
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });
        msgPanel.add(ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 120, 30));

        getContentPane().add(msgPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 170));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okMouseClicked
        this.dispose();
    }//GEN-LAST:event_okMouseClicked

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        this.dispose();
    }//GEN-LAST:event_okActionPerformed

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
            java.util.logging.Logger.getLogger(MessageStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MessageStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MessageStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MessageStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MessageStatus("", "", "", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel msgPanel;
    private javax.swing.JLabel msgPanelTxt;
    private javax.swing.JButton ok;
    // End of variables declaration//GEN-END:variables
}

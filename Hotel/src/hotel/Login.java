package hotel;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        errorTxt.setVisible(false);
        usuarioTxt.requestFocus();
        loading.setVisible(false);
        Image iconFrameInicial = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imgs/iconFrame.png"));
        setIconImage(iconFrameInicial);
        loadingBtn.setVisible(false);
    }

    public void Logar() {
        Thread logar = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useTimezone=true&serverTimezone=UTC",
                            new Conection().userDB, new Conection().passDB);
                    String sql = "SELECT nome,usuario,senha FROM funcionarios WHERE usuario='" + usuarioTxt.getText().replace("'", "") + "' AND senha='" + senhaTxt.getText().replace("'", "") + "';";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next() && !usuarioTxt.getText().equals("")) {
                        //quando o login é verdadeiro
                        Inicial i = new Inicial(usuarioTxt.getText().replace("'", ""), rs.getString("nome"));
                        MessageStatus m = new MessageStatus(usuarioTxt.getText().replace("'", ""), rs.getString("nome"), "", "");
                        m.setVisible(false);
                        i.setVisible(true);
                        System.out.println("Login efetuado!");
                        dispose();
                    } else {
                        //quando o login é falso
                        errorTxt.setVisible(true);
                        loadingBtn.setVisible(false);
                        loading.setVisible(false);
                        System.out.println("Erro de Login!");
                    }
                    con.close();
                } catch (ClassNotFoundException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                    System.out.println("Erro que nem fez a conexão :(");
                }
            }
        });
        logar.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        loadingBtn = new javax.swing.JPanel();
        loadingGif = new javax.swing.JLabel();
        entrarTxt = new javax.swing.JLabel();
        sairTxt = new javax.swing.JLabel();
        loading = new javax.swing.JLabel();
        usuarioTxt = new javax.swing.JTextField();
        senhaTxt = new javax.swing.JPasswordField();
        errorTxt = new javax.swing.JLabel();
        Fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loadingBtn.setBackground(new java.awt.Color(0, 127, 255));
        loadingBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loadingGif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/loading.gif"))); // NOI18N
        loadingBtn.add(loadingGif, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 50, 20));

        jPanel1.add(loadingBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 275, 70, 16));

        entrarTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        entrarTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                entrarTxtMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                entrarTxtMousePressed(evt);
            }
        });
        jPanel1.add(entrarTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, 90, 30));

        sairTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sairTxt.setFocusable(false);
        sairTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sairTxtMouseClicked(evt);
            }
        });
        jPanel1.add(sairTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 270, 50, 30));

        loading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/loading-final.gif"))); // NOI18N
        loading.setFocusable(false);
        jPanel1.add(loading, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 310, -1, 60));

        usuarioTxt.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        usuarioTxt.setForeground(new java.awt.Color(0, 127, 255));
        usuarioTxt.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        usuarioTxt.setBorder(null);
        usuarioTxt.setOpaque(false);
        usuarioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usuarioTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usuarioTxtKeyTyped(evt);
            }
        });
        jPanel1.add(usuarioTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 280, 30));

        senhaTxt.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        senhaTxt.setForeground(new java.awt.Color(0, 127, 255));
        senhaTxt.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        senhaTxt.setBorder(null);
        senhaTxt.setOpaque(false);
        senhaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                senhaTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                senhaTxtKeyTyped(evt);
            }
        });
        jPanel1.add(senhaTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 215, 280, 30));

        errorTxt.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        errorTxt.setForeground(new java.awt.Color(255, 0, 0));
        errorTxt.setText("Usuário ou senha incorretos, tente novamente.");
        errorTxt.setFocusable(false);
        jPanel1.add(errorTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, -1, -1));

        Fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/img-login.png"))); // NOI18N
        Fundo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(Fundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 400));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 400));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void entrarTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entrarTxtMouseClicked
        if (usuarioTxt.getText().equals("") || senhaTxt.getText().equals("")) {
            errorTxt.setVisible(true);
            System.out.println("Erro de Login!");
        } else {
            Logar();
        }
    }//GEN-LAST:event_entrarTxtMouseClicked

    private void sairTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sairTxtMouseClicked
        System.exit(0);
    }//GEN-LAST:event_sairTxtMouseClicked

    private void usuarioTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            senhaTxt.requestFocus();
        }
    }//GEN-LAST:event_usuarioTxtKeyPressed

    private void usuarioTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioTxtKeyTyped
        String caracteres = "0987654321abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_usuarioTxtKeyTyped

    private void senhaTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_senhaTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    loading.setVisible(true);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                    }
                    loading.setVisible(false);
                }
            });
            t.start();

            if (usuarioTxt.getText().equals("") || senhaTxt.getText().equals("")) {
                errorTxt.setVisible(true);
                System.out.println("Erro de Login!");
                loading.setVisible(false);
            } else {
                Logar();
            }
        }
    }//GEN-LAST:event_senhaTxtKeyPressed

    private void senhaTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_senhaTxtKeyTyped
        String caracteres = "0987654321abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    loading.setVisible(true);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                    }
                    loading.setVisible(false);
                }
            });
            t.start();
        }
    }//GEN-LAST:event_senhaTxtKeyTyped

    private void entrarTxtMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entrarTxtMousePressed
        Thread t = new Thread(new Runnable() {
            public void run() {
                loadingBtn.setVisible(true);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                }
                loadingBtn.setVisible(false);
            }
        });
        t.start();
        loadingBtn.setVisible(true);
    }//GEN-LAST:event_entrarTxtMousePressed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fundo;
    private javax.swing.JLabel entrarTxt;
    private javax.swing.JLabel errorTxt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel loading;
    private javax.swing.JPanel loadingBtn;
    private javax.swing.JLabel loadingGif;
    private javax.swing.JLabel sairTxt;
    private javax.swing.JPasswordField senhaTxt;
    private javax.swing.JTextField usuarioTxt;
    // End of variables declaration//GEN-END:variables
}

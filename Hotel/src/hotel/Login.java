package hotel;

import java.awt.Dimension;
import static java.awt.Frame.getFrames;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    Thread tK;
    Thread tM;

    public Login() {
        this.setAutoRequestFocus(true);
        this.setFocusable(true);
        this.requestFocus();
        initComponents();
        errorTxt.setVisible(false);
        usuarioTxt.requestFocus();
        loading.setVisible(false);
        Image iconFrameInicial = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imgs/iconFrame.png"));
        setIconImage(iconFrameInicial);
        loadingBtn.setVisible(false);

        //animação quando a tela de login aparece
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension srcsize = tk.getScreenSize();//usar <var>.getWidth() para largura e <var>.getHeight() para altura
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int x = getFrames()[0].getX();
                int y = getFrames()[0].getY();
                getFrames()[0].setBounds(getX(), (int) srcsize.getHeight() + 1, 600, 400);
                for (int c = 0; c <= y + 20; c++) {
                    try {
                        Thread.sleep(1);
                        getFrames()[0].setResizable(true);
                        getFrames()[0].setBounds(getX(), getY() - 3, 600, 400);
                    } catch (InterruptedException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
                getFrames()[0].setBounds(x, y, 600, 400);

            }
        });
        t.start();
        //fim

        //pegando o usuario salvo automaticamente
        try {
            File file = new File("config.HMS");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileReader ler = new FileReader("config.HMS");
            BufferedReader reader = new BufferedReader(ler);
            String linha;
            ArrayList<String> fileLines = new ArrayList<>();
            int c = 0;
            while ((linha = reader.readLine()) != null) {
                fileLines.add(linha);
                c++;
                System.out.println("[" + c + "] " + linha);
            }
            usuarioTxt.setText(fileLines.get(0));
            lembrarDeMim.setSelected(true);
            senhaTxt.requestFocus();
            
            /**/
        } catch (IOException | IndexOutOfBoundsException | java.lang.NullPointerException e) {
            System.out.println("Arquivo de configuração sem usuário e senha");
        }
    }

    public void Logar() {
        Thread logon = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //se tiver com lembreDeMim marcado ele grava o usuario e a senha no arquivo
                    File file = new File("config.HMS");//puxa o arquivo de configurações
                    PrintWriter gravarFile = new PrintWriter(file);//prepara para gravar no arquivo
                    if (lembrarDeMim.isSelected()) {//quando lembreDeMim está marcado
                        gravarFile.printf(usuarioTxt.getText() + "%n");//escreve o nome de usuario
                        //gravarFile.printf(senhaTxt.getText());//escreve a senha
                        gravarFile.close();//encerra a gravação no arquivo
                    } else {//quando lembreDeMim não está marcado
                        gravarFile.printf("");//grava uma string vazia no arquivo, ou remove o usuário gravado anteriormente
                    }
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useTimezone=true&serverTimezone=UTC", new Conection().userDB, new Conection().passDB);
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
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, e);
                    System.out.println("Erro que nem criou o arquivo:(");
                }
            }
        });
        logon.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Base = new javax.swing.JPanel();
        loadingBtn = new javax.swing.JPanel();
        loadingGif = new javax.swing.JLabel();
        entrarTxt = new javax.swing.JLabel();
        sairTxt = new javax.swing.JLabel();
        loading = new javax.swing.JLabel();
        usuarioTxt = new javax.swing.JTextField();
        senhaTxt = new javax.swing.JPasswordField();
        errorTxt = new javax.swing.JLabel();
        lembrarDeMim = new javax.swing.JCheckBox();
        Fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        loadingBtn.setBackground(new java.awt.Color(0, 127, 255));

        loadingGif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/loading.gif"))); // NOI18N

        javax.swing.GroupLayout loadingBtnLayout = new javax.swing.GroupLayout(loadingBtn);
        loadingBtn.setLayout(loadingBtnLayout);
        loadingBtnLayout.setHorizontalGroup(
            loadingBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loadingBtnLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(loadingGif, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        loadingBtnLayout.setVerticalGroup(
            loadingBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loadingGif, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        entrarTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        entrarTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                entrarTxtMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                entrarTxtMousePressed(evt);
            }
        });

        sairTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sairTxt.setFocusable(false);
        sairTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sairTxtMouseClicked(evt);
            }
        });

        loading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/loading-final.gif"))); // NOI18N
        loading.setFocusable(false);

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

        errorTxt.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        errorTxt.setForeground(new java.awt.Color(255, 47, 47));
        errorTxt.setText("Usuário ou senha incorreto(s). Tente novamente.");
        errorTxt.setFocusable(false);

        lembrarDeMim.setBackground(new java.awt.Color(0, 42, 84));
        lembrarDeMim.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lembrarDeMim.setForeground(new java.awt.Color(255, 255, 255));
        lembrarDeMim.setText("Lembrar usuário");
        lembrarDeMim.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lembrarDeMim.setMaximumSize(new java.awt.Dimension(105, 30));
        lembrarDeMim.setMinimumSize(new java.awt.Dimension(105, 30));
        lembrarDeMim.setPreferredSize(new java.awt.Dimension(105, 30));
        lembrarDeMim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lembrarDeMimKeyPressed(evt);
            }
        });

        Fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/img-login.png"))); // NOI18N
        Fundo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout BaseLayout = new javax.swing.GroupLayout(Base);
        Base.setLayout(BaseLayout);
        BaseLayout.setHorizontalGroup(
            BaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BaseLayout.createSequentialGroup()
                .addGap(450, 450, 450)
                .addComponent(entrarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BaseLayout.createSequentialGroup()
                .addGap(290, 290, 290)
                .addComponent(usuarioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BaseLayout.createSequentialGroup()
                .addGap(540, 540, 540)
                .addComponent(sairTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BaseLayout.createSequentialGroup()
                .addGap(460, 460, 460)
                .addComponent(loadingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BaseLayout.createSequentialGroup()
                .addGap(290, 290, 290)
                .addComponent(lembrarDeMim, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BaseLayout.createSequentialGroup()
                .addGap(530, 530, 530)
                .addComponent(loading))
            .addGroup(BaseLayout.createSequentialGroup()
                .addGap(290, 290, 290)
                .addComponent(senhaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BaseLayout.createSequentialGroup()
                .addGap(290, 290, 290)
                .addComponent(errorTxt))
            .addComponent(Fundo)
        );
        BaseLayout.setVerticalGroup(
            BaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BaseLayout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addComponent(entrarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BaseLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(usuarioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BaseLayout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addComponent(sairTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BaseLayout.createSequentialGroup()
                .addGap(275, 275, 275)
                .addComponent(loadingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BaseLayout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addComponent(lembrarDeMim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BaseLayout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(loading, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BaseLayout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(senhaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BaseLayout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(errorTxt))
            .addComponent(Fundo)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Base, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Base, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void entrarTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entrarTxtMouseClicked
        if (usuarioTxt.getText().equals("") || senhaTxt.getText().equals("") || (senhaTxt.getText().length() >= 100) || (usuarioTxt.getText().length() >= 100)) {
            errorTxt.setVisible(true);
            System.out.println("Erro de Login!");
        } else {
            Logar();
        }
    }//GEN-LAST:event_entrarTxtMouseClicked

    private void sairTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sairTxtMouseClicked
        try {
            //se tiver com lembreDeMim marcado ele grava o usuario no arquivo
            File file = new File("config.HMS");//puxa o arquivo de configurações
            PrintWriter gravarFile = new PrintWriter(file);//prepara para gravar no arquivo
            if (lembrarDeMim.isSelected()) {//quando lembreDeMim está marcado
                gravarFile.printf(usuarioTxt.getText() + "%n");//escreve o nome de usuario
                gravarFile.printf(senhaTxt.getText());//escreve a senha
                gravarFile.close();//encerra a gravação no arquivo
            } else {//quando lembreDeMim não está marcado
                gravarFile.printf("");//grava uma string vazia no arquivo
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        //animacao quando a tela de login sai
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension srcsize = tk.getScreenSize();//usar <var>.getWidth() para largura e <var>.getHeight() para altura
        Thread tOUT = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int c = 0; c <= (srcsize.getHeight() - (getFrames()[0].getY() - srcsize.getHeight())); c++) {
                    try {
                        Thread.sleep(1);
                        getFrames()[0].setResizable(true);
                        getFrames()[0].setBounds(getX(), getY() + 3, 600, 400);
                    } catch (InterruptedException ex) {
                        System.out.println("Erro no thread: " + ex);
                    }
                }
                System.exit(0);
            }
        });
        tOUT.start();
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
            tK = new Thread(new Runnable() {
                public void run() {
                    loading.setVisible(true);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                    }
                    loading.setVisible(false);
                }
            });
            tK.start();
            if (usuarioTxt.getText().equals("") || senhaTxt.getText().equals("") || (senhaTxt.getText().length() >= 100) || (usuarioTxt.getText().length() >= 100)) {
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
        if (!caracteres.contains(evt.getKeyChar() + "") && (senhaTxt.getText().length() >= 100)) {
            evt.consume();
        }
    }//GEN-LAST:event_senhaTxtKeyTyped

    private void entrarTxtMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entrarTxtMousePressed
        tM = new Thread(new Runnable() {
            public void run() {
                loadingBtn.setVisible(true);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                }
                loadingBtn.setVisible(false);
            }
        });
        tM.start();
        loadingBtn.setVisible(true);
    }//GEN-LAST:event_entrarTxtMousePressed

    private void lembrarDeMimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lembrarDeMimKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            tK = new Thread(new Runnable() {
                public void run() {
                    loadingBtn.setVisible(true);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                    }
                    loadingBtn.setVisible(false);
                }
            });
            tK.start();
            if (usuarioTxt.getText().equals("") || senhaTxt.getText().equals("") || (senhaTxt.getText().length() >= 100) || (usuarioTxt.getText().length() >= 100)) {
                errorTxt.setVisible(true);
                System.out.println("Erro de Login!");
                loading.setVisible(false);
            } else {
                Logar();
            }
        }
    }//GEN-LAST:event_lembrarDeMimKeyPressed

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
    private javax.swing.JPanel Base;
    private javax.swing.JLabel Fundo;
    private javax.swing.JLabel entrarTxt;
    private javax.swing.JLabel errorTxt;
    private javax.swing.JCheckBox lembrarDeMim;
    private javax.swing.JLabel loading;
    private javax.swing.JPanel loadingBtn;
    private javax.swing.JLabel loadingGif;
    private javax.swing.JLabel sairTxt;
    private javax.swing.JPasswordField senhaTxt;
    private javax.swing.JTextField usuarioTxt;
    // End of variables declaration//GEN-END:variables
}

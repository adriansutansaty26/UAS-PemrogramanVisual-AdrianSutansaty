package contactmanagement.ui.login;

import contactmanagement.auth.Session;
import contactmanagement.ui.home.HomeFrame;
import contactmanagement.utilities.DialogBox;
import contactmanagement.utilities.PasswordUtils;
import javax.swing.JOptionPane;
import contactmanagement.viewmodel.ViewModel;

public class LoginFrame extends javax.swing.JFrame {
    private LoginFrameViewModel loginFrameViewModel;
    
    public LoginFrame() {
        provideViewModel();
        initComponents();
        initShellActions();
    }
    
    private void generatePassword(String password) {
        String securePassword;
        String salt;
        
        salt = PasswordUtils.getSalt(40);
        securePassword = PasswordUtils.generateSecurePassword(password, salt);
        
        System.out.println("Secure Password : "+securePassword);
        System.out.println("Salt            : "+salt);
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogin = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        tfUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        tfPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        lblUsername1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login : Sistem Pendataan Kontak Pelanggan");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        lblLogin.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N
        lblLogin.setText("Login");

        lblUsername.setForeground(new java.awt.Color(102, 102, 102));
        lblUsername.setText("Username");

        tfUsername.setBackground(new java.awt.Color(224, 224, 224));
        tfUsername.setToolTipText("");
        tfUsername.setBorder(null);
        tfUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfUsernameActionPerformed(evt);
            }
        });

        lblPassword.setForeground(new java.awt.Color(102, 102, 102));
        lblPassword.setText("Password");

        tfPassword.setBackground(new java.awt.Color(224, 224, 224));
        tfPassword.setBorder(null);
        tfPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPasswordActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(77, 54, 224));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblUsername1.setForeground(new java.awt.Color(51, 51, 51));
        lblUsername1.setText("Sistem Pendataan Kontak Pelanggan");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsername1)
                    .addComponent(lblLogin)
                    .addComponent(lblUsername)
                    .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassword)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsername1)
                .addGap(26, 26, 26)
                .addComponent(lblUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        if(!validateLoginFormEmpty()) {
            String username = tfUsername.getText();
            String password = String.valueOf(tfPassword.getPassword());
            Session loginSession = loginFrameViewModel.performLogin(username, password);
            
            if(loginSession.getLoginStatus()) {
                startMainFrame(loginSession);
            } else {
                DialogBox.showMessageDialog(
                        "Gagal! Username atau Password salah!");
            }
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void tfPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPasswordActionPerformed

    private void tfUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfUsernameActionPerformed

    private void provideViewModel() {
        loginFrameViewModel = new LoginFrameViewModel();
    }
    
    private void initShellActions() {
        loginFrameViewModel.observeConnection(new ViewModel() {
            @Override
            public void connectionStatus(boolean status) {
                if(!status) {
                    showDatabaseErrorDialog();
                }
            }
        });
    }
    
    private boolean validateLoginFormEmpty() {
        boolean isEmpty = false;
        
        if(tfUsername.getText().isEmpty()) {
            isEmpty = true;
            DialogBox.showMessageDialog("Harus mengisi username!");
        } else if(tfPassword.getPassword().length == 0) {
            isEmpty = true;
            DialogBox.showMessageDialog("Harus mengisi password!");
        }
        
        return isEmpty;
    }
    
    private void startMainFrame(Session session) {
        this.dispose();
        HomeFrame mainFrame = new HomeFrame(session);
    }
    
    private void showDatabaseErrorDialog() {
        int response = DialogBox.showOptionDialog(
                            "Error", 
                            "Database gagal terhubung!\nHubungkan ulang?",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.ERROR_MESSAGE);
            
        if(response == JOptionPane.YES_OPTION) {
            loginFrameViewModel.connectDB();
        } else {
            System.exit(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblUsername1;
    private javax.swing.JPasswordField tfPassword;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables
}

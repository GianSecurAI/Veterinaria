package Vista;

import Controlador.ControladorInicio;

public class VentanaLogin extends javax.swing.JPanel {

    ControladorInicio controlador;

    public VentanaLogin() {
        initComponents();
        controlador = new ControladorInicio(this);
        controlador.initLogin();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgLogo = new Componet.Imagen();
        jlbTextRegistr = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtUsuario = new javax.swing.JTextField();
        imgUsuario = new Componet.Imagen();
        btnIngresar = new javax.swing.JButton();
        jlbTittle1 = new javax.swing.JLabel();
        btnIrRegistro = new javax.swing.JLabel();
        imgUsuario1 = new Componet.Imagen();
        jSeparator2 = new javax.swing.JSeparator();
        txtPassword = new javax.swing.JPasswordField();
        jlbTittle3 = new javax.swing.JLabel();
        jlbTittle4 = new javax.swing.JLabel();
        btnOjoPassword = new javax.swing.JLabel();

        setBackground(new java.awt.Color(232, 222, 179));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imgLogo.setImage(new javax.swing.ImageIcon(getClass().getResource("/_Icons/Logo_LOGIN.png"))); // NOI18N
        add(imgLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 80, 70));

        jlbTextRegistr.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jlbTextRegistr.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlbTextRegistr.setText("�No tienes cuenta?");
        add(jlbTextRegistr, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 120, 20));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 160, 10));

        txtUsuario.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtUsuario.setSelectionColor(new java.awt.Color(51, 0, 102));
        add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 160, 30));

        imgUsuario.setImage(new javax.swing.ImageIcon(getClass().getResource("/_Icons/User.png"))); // NOI18N
        add(imgUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 30, 30));

        btnIngresar.setBackground(new java.awt.Color(189, 47, 158));
        btnIngresar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresar.setText("Ingresar");
        btnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIngresar.setFocusPainted(false);
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, 120, 40));

        jlbTittle1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        jlbTittle1.setForeground(new java.awt.Color(51, 0, 102));
        jlbTittle1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbTittle1.setText("Contrase�a");
        add(jlbTittle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 150, 30));

        btnIrRegistro.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnIrRegistro.setForeground(new java.awt.Color(51, 0, 102));
        btnIrRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnIrRegistro.setText("Registrate");
        btnIrRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIrRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIrRegistroMouseClicked(evt);
            }
        });
        add(btnIrRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 70, 20));

        imgUsuario1.setImage(new javax.swing.ImageIcon(getClass().getResource("/_Icons/Password.png"))); // NOI18N
        add(imgUsuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 30, 30));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 160, 10));

        txtPassword.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtPassword.setSelectionColor(new java.awt.Color(51, 0, 102));
        add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 160, 30));

        jlbTittle3.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jlbTittle3.setForeground(new java.awt.Color(51, 0, 102));
        jlbTittle3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTittle3.setText("Login");
        add(jlbTittle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 120, 47));

        jlbTittle4.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        jlbTittle4.setForeground(new java.awt.Color(51, 0, 102));
        jlbTittle4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbTittle4.setText("Usuario");
        add(jlbTittle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 110, 30));

        btnOjoPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_Icons/ojoCerrado.png"))); // NOI18N
        btnOjoPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOjoPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOjoPasswordMouseClicked(evt);
            }
        });
        add(btnOjoPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, 30, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btnOjoPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOjoPasswordMouseClicked
        controlador.btnClickOjoLogin();
    }//GEN-LAST:event_btnOjoPasswordMouseClicked

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        controlador.ingresar();
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnIrRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIrRegistroMouseClicked
        controlador.btnIrRegistro();
    }//GEN-LAST:event_btnIrRegistroMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnIngresar;
    public javax.swing.JLabel btnIrRegistro;
    public javax.swing.JLabel btnOjoPassword;
    public Componet.Imagen imgLogo;
    public Componet.Imagen imgUsuario;
    public Componet.Imagen imgUsuario1;
    public javax.swing.JSeparator jSeparator1;
    public javax.swing.JSeparator jSeparator2;
    public javax.swing.JLabel jlbTextRegistr;
    public javax.swing.JLabel jlbTittle1;
    public javax.swing.JLabel jlbTittle3;
    public javax.swing.JLabel jlbTittle4;
    public javax.swing.JPasswordField txtPassword;
    public javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}

package Vista;

public class VentanaPrincipal extends javax.swing.JFrame {

    public VentanaPrincipal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        dpnEscritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        opcion01 = new javax.swing.JMenuItem();
        opcion02 = new javax.swing.JMenuItem();
        opcion03 = new javax.swing.JMenuItem();
        opcion04 = new javax.swing.JMenuItem();
        opcion05 = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dpnEscritorio.setBackground(new java.awt.Color(157, 208, 224));

        jMenu2.setText("Servicios");

        opcion01.setText("Registro Mascota");
        jMenu2.add(opcion01);

        opcion02.setText("Registro Citas");
        jMenu2.add(opcion02);

        opcion03.setText("Registro Cliente");
        jMenu2.add(opcion03);

        opcion04.setText("Datos Empleados");
        jMenu2.add(opcion04);

        opcion05.setText("Datos Servicios");
        jMenu2.add(opcion05);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpnEscritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpnEscritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JDesktopPane dpnEscritorio;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenuItem opcion01;
    public javax.swing.JMenuItem opcion02;
    public javax.swing.JMenuItem opcion03;
    public javax.swing.JMenuItem opcion04;
    public javax.swing.JMenuItem opcion05;
    // End of variables declaration//GEN-END:variables
}


package subneteoip;

import javax.swing.JOptionPane;

/**
 *
 * @author emist
 */
public class Interfaz extends javax.swing.JFrame {
    String red = "";
    String res = "";
    String nRedes = "";
    int numerosIP[] = new int[4];
    String claseIP = "";
    public Interfaz() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtRes = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        spnNRedes = new javax.swing.JSpinner();
        txtIp = new javax.swing.JTextField();
        btnSubnetear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtRes.setColumns(20);
        txtRes.setRows(5);
        jScrollPane1.setViewportView(txtRes);

        jLabel1.setText("IP");

        jLabel2.setText("Subredes");

        btnSubnetear.setText("Calcular");
        btnSubnetear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubnetearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnNRedes, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(441, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnSubnetear)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(spnNRedes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btnSubnetear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubnetearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubnetearActionPerformed
        txtRes.setText("");
        red = txtIp.getText().toString();
        nRedes = spnNRedes.getValue().toString();
        System.out.println("Red: " + red);
        System.out.println("nRedes: " + nRedes);
        String Bits = Integer.toBinaryString(Integer.parseInt(nRedes));
        System.out.println("Validar RED:"+red+"Validacion: "+validarIP(red));
        if (validarIP(red) && nRedes.length() > 0) {
            Subnetear sb = new Subnetear(red, nRedes, claseIP);
            txtRes.setText(sb.getResultado());
        } else {
            JOptionPane.showMessageDialog(this, "ERROR EN DIRECION IP");
        }

    }//GEN-LAST:event_btnSubnetearActionPerformed
    
    public boolean validarIP(String cadena) {
        boolean x = false;
        String[] sNumeros = cadena.split("\\.");
        while (sNumeros.length < 4 || sNumeros.length > 4) {
            JOptionPane.showMessageDialog(this, "ERROR EN DIRECION IP");
            break;
        }
        for (int i = 0; i < 4; i++) {
            numerosIP[i] = Integer.parseInt(sNumeros[i]);
        }
        if (numerosIP[0] <= 127 && numerosIP[1] <= 255 && numerosIP[2] <= 255 && numerosIP[3] <= 254) {
            claseIP = "A";
            x = true;
        } else if (numerosIP[0] <= 191 && numerosIP[1] <= 255 && numerosIP[2] <= 255 && numerosIP[3] <= 254) {
            claseIP = "B";
            x = true;
        } else if (numerosIP[0] <= 223 && numerosIP[1] <= 255 && numerosIP[2] <= 255 && numerosIP[3] <= 254) {
            claseIP = "C";
            x = true;
        } else if (numerosIP[0] <= 239 && numerosIP[1] <= 255 && numerosIP[2] <= 255 && numerosIP[3] <= 254) {
            claseIP = "D";
            x = true;
        } else if (numerosIP[0] <= 255 && numerosIP[1] <= 255 && numerosIP[2] <= 255 && numerosIP[3] <= 254) {
            claseIP = "E";
            x = true;
        }
        res += claseIP + "\n";
        if(x) {
        }else{
           JOptionPane.showMessageDialog(this, "Dirección IP fuera de rango");
        }
        return x;
    }
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
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubnetear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spnNRedes;
    private javax.swing.JTextField txtIp;
    private javax.swing.JTextArea txtRes;
    // End of variables declaration//GEN-END:variables
}

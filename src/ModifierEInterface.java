import java.awt.FileDialog;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class ModifierEInterface extends javax.swing.JFrame {

	// Cette interface est accessible � partir de l'interface profil
	// elle permet d'op�rer des changement sur un employ� donn�

    public ModifierEInterface(int num_emp) throws FileNotFoundException, IOException {

        num_emp_actuel = num_emp; // on r�cup�re le n° de l'employ� que l'on souhaite modifier
        initComponents(); // initialisation des composantes de l'interface graphique
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permet pour ne pas fermer le programme lorsqu'on ferme l'interface courante
        Canvas1 = new ImageLoad(null); // Instanciation d'un Canvas qui va contenir des images, plus d'�xplications au niveau de la classe ImageLoad
        Canvas1.setSize(80, 80); // d�finition de la taille du canvas
        Canvas1.setLocation(220,250); // d�finition de la position du canvas dans l'interface
        this.add(Canvas1); // ajout du canvas dans l'interface

        String url = "jdbc:mysql://localhost:3306/employe"; //definition des param�tres
        String user = "root";								//n�cessaires pour la connexion
        String pswd = "";									// � la bdd

        try {
            Class.forName("com.mysql.jdbc.Driver"); // initialisation du pilote de connexion
            try (Connection conn = DriverManager.getConnection(url, user, pswd)) {
                String sql = "SELECT * FROM employe WHERE ID="+num_emp; // requ�te sql pour afficher l'employer que l'on veut traiter
                java.sql.Statement statement = conn.createStatement();

                ResultSet result = statement.executeQuery(sql); // r�cup�ration du r�sultat de la requ�te

                if (result.next() == true) {
                    location = "image"; // initialisation de location � image
                    File monImage = new File("image"); // cr�ation d'un fichier image au niveau de la source pour pouvoir y r�cup�rer l'image de la base de donn�e
                    try (FileOutputStream ostreamImage = new FileOutputStream(monImage)) {
                        InputStream istreamImage = result.getBinaryStream("photo"); // r�cup�ration des donn�e en format BinaryStream

                                byte[] buffer = new byte[1024];
                                int length;

                                while ((length = istreamImage.read(buffer)) != -1) {
                                    ostreamImage.write(buffer, 0, length); 	// �criture des donn�es (binarystram) dans le fichier image
																			// que nous avons cr�er pour finalement obtenir l'image
                                }

                    }

                    Toolkit toolkit = Toolkit.getDefaultToolkit(); //instanciation d'un toolkit
                    image1 = toolkit.getImage("image"); // r�cup�ration du fichier image (reconstitu�e)
                    image1 = image1.getScaledInstance(100, 100, Image.SCALE_DEFAULT); // redimensionnement de l'image
                    Canvas1.setImage(image1); // placement de l'image dans le canvas
                    Canvas1.repaint(); // affichage de l'image

                    jTextField1.setText(result.getString("nom")); 	// remettre le contenu des champs
                    jTextField2.setText(result.getString("prenom"));// r�cup�r� depuis la bdd
                    jTextArea1.setText(result.getString("adresse"));
                    jDateChooser1.setDate(result.getDate("ddN"));
                    jTextField4.setText(result.getString("salaire"));


                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nom");

        jLabel2.setText("Prenom");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Adresse");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel4.setText("ddN");

        jLabel5.setText("Salaire");

        jButton1.setText("Valider");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Annuler");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Modifier image ...");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jDateChooser1.setMaxSelectableDate(new java.util.Date());
        jDateChooser1.setMinSelectableDate(new java.util.Date(-1893450478000L));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                    .addGap(3, 3, 3))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)))
                            .addComponent(jButton1))
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try{
            String msg;
            double salaire = Double.parseDouble(jTextField4.getText());
            if(salaire>0){
            msg = "Nom : "+ jTextField1.getText() +"\nPrenom : "+ jTextField2.getText() + "\nSalaire : " + jTextField4.getText() +
                "\nddN : " + jDateChooser1.getDate() + "\nAdresse : " + jTextArea1.getText() + "\n";
            System.out.println(msg);
            String url = "jdbc:mysql://localhost:3306/employe";
        String user = "root";
        String pswd = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, pswd)) {
                String sql = "UPDATE employe SET nom = ?, prenom =?, adresse=?, ddN =?,"
                        +"salaire=?, photo=? WHERE ID= ? ";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, jTextField1.getText());
                statement.setString(2, jTextField2.getText());
                statement.setString(3, jTextArea1.getText());
                java.sql.Date sqlDate = new java.sql.Date(jDateChooser1.getDate().getTime());
                statement.setDate(4, sqlDate);
                statement.setInt(5,Integer.valueOf(jTextField4.getText()));

                File monImage = new File(location);
                FileInputStream istreamImage = new FileInputStream(monImage);
                statement.setBinaryStream(6, istreamImage, (int)monImage.length());
                statement.setInt(7, num_emp_actuel);
                System.out.println("\n**********ID employe "+num_emp_actuel);
                        boolean row = statement.execute(); // ne faire cette action qu apr�s confirmation
                if (!row) {
                    System.out.println("Un employe a ete modifi� avec photo.");
                }
                else System.out.println("aucun employe n'a ete modifi� avec photo.");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
        } catch (IOException ex) {
        }
            }
            else
                jTextField4.setText("");
        }catch (NumberFormatException e)
        {
            jTextField4.setText("");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        javax.swing.JButton b = (javax.swing.JButton) evt.getSource();
        if (b == jButton3) {
            imageload();
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    void imageload() { // r�cup�re le chemin de l'image sur le disque, et l'affiche dans le canvas
        fd.show();
        if (fd.getFile() != null){
            photo_d = (fd.getDirectory() + fd.getFile());
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            image1 = toolkit.getImage(photo_d);
            image1 = image1.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            Canvas1.setImage(image1);
            Canvas1.repaint();
        }
        location=photo_d;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

    int num_emp_actuel;
    private Image image1; //BufferedImage
    ImageLoad Canvas1;
    FileDialog fd = new FileDialog(this, "Open", FileDialog.LOAD);
    String location;
    String photo_d;
    InputStream inputStream;
}

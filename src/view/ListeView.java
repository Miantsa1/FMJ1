package view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import controller.ListeController;
import modele.Classement;
import modele.Liste;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;

public class ListeView extends JFrame {
    public JPanel imageLogo = new JPanel();
    public JPanel imageLogo1 = new JPanel();
    public JPanel ptout, p1, p2;
    public JLabel titre = new JLabel("Fédération Malagasy de Judo");
    public JButton btClassement = new JButton("Classement");
    public JButton btAdversaire = new JButton("Adversaire");

    public JLabel age = new JLabel("Catégorie (âge) :");
    public JComboBox<String> ageEntree = new JComboBox<>(new String[]{"Poussin(e)", "Minime", "Cadet(tte)", "Junior", "Senior"});
    public JLabel poids = new JLabel("Catégorie (poids) :");
    public JComboBox<String> poidsEntree = new JComboBox<>(new String[]{"-36", "-40", "-44", "-48", "-52", "-60", "63", "-66", "-70", "-73", "-80", "-83", "+100"});
    public JLabel TitreNom = new JLabel("Nom :");
    public JTextField nomEntree = new JTextField();
    public JLabel TitrePrenom = new JLabel("Prénoms :");
    public JTextField prenomEntree = new JTextField();
    public JLabel TitreClub = new JLabel("Club :");
    public JTextField clubEntree = new JTextField();
    public JButton btAjouter = new RoundedButton("Ajouter", new Color(0, 102, 102), 15);
    public JButton btModifier = new RoundedButton("Modifier", new Color(36, 95, 145), 15);
    public JButton btModifier1 = new RoundedButton("Modifier", new Color(36, 95, 145), 15);
    public JButton btSupprimer = new RoundedButton("Supprimer", new Color(145, 9, 35), 15);

    public DefaultTableModel model1 = new DefaultTableModel(new Object[]{"N°", "Nom", "Prénom", "Club", "Cat Age", "Cat poids", "Modifier"}, 0);
    public JTable table1 = new JTable(model1);
    public JScrollPane scrollPane1 = new JScrollPane(table1);

    Font var1 = new Font("Arial", Font.BOLD, 23);
    Font var2 = new Font("Arial", Font.BOLD, 16);
    Font var3 = new Font("Arial", Font.BOLD, 14);

    // Couleurs thématiques judo
    private final Color MAIN_BG_COLOR = new Color(245, 245, 245);
    private final Color HEADER_BG_COLOR = new Color(200, 30, 30); // Rouge judo
    private final Color PANEL1_BG_COLOR = new Color(240, 240, 240);
    private final Color BUTTON_BG_COLOR = new Color(200, 30, 30);
    private final Color BUTTON_FG_COLOR = Color.WHITE;
    private final Color TABLE_HEADER_BG = new Color(70, 70, 70);
    private final Color TABLE_HEADER_FG = Color.WHITE;
    private final Color TABLE_SELECTION_BG = new Color(200, 30, 30, 100);

    public ListeView(ListeController controller) {
        setTitle("Liste");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1400, 750);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(MAIN_BG_COLOR);
        
        btModifier1.setSize(50, 3);
        controller.afficherTable(model1);

        ptout = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();

        ptout.setBounds(0, 0, 1400, 700);
        ptout.setLayout(null);
        ptout.setBackground(MAIN_BG_COLOR);

        // Haut
        p1.setBounds(2, 2, 1378, 320);
        p1.setLayout(null);
        p1.setBackground(PANEL1_BG_COLOR);
        p1.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(150, 150, 150)));

        imageLogo.setBounds(10, 10, 60, 60);
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/ressources/FMJ.png"));
            Image scaledImage = originalIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            imageLogo.add(logoLabel);
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Logo");
            errorLabel.setForeground(Color.RED);
            imageLogo.add(errorLabel);
            System.err.println("Erreur de chargement de l'image: " + e.getMessage());
        }
        p1.add(imageLogo);

        imageLogo1.setBounds(960, 80, 300, 200);
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/ressources/Ceinture.jpg"));
            Image scaledImage = originalIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            imageLogo1.add(logoLabel);
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Logo1");
            errorLabel.setForeground(Color.RED);
            imageLogo1.add(errorLabel);
            System.err.println("Erreur de chargement de l'image: " + e.getMessage());
        }
        p1.add(imageLogo1);

        titre.setFont(new Font("Serif", Font.BOLD, 22));
        titre.setBounds(80, 20, 350, 30);
        titre.setForeground(new Color(70, 70, 70));
        p1.add(titre);

        btClassement = new RoundedButton("Classement1", BUTTON_BG_COLOR, 8);
        btClassement.setBounds(1120, 20, 200, 35);
        btClassement.setForeground(BUTTON_FG_COLOR);
        btClassement.setFont(var3);
        btClassement.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ClassementView cadre= new ClassementView(controller);
                cadre.setVisible(true);
            }
        });
        p1.add(btClassement);

        btAdversaire = new RoundedButton("Adversaire", BUTTON_BG_COLOR, 8);
        btAdversaire.setBounds(900, 20, 200, 35);
        btAdversaire.setForeground(BUTTON_FG_COLOR);
        btAdversaire.setFont(var3);
        btAdversaire.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                AdversaireView cadre= new AdversaireView(controller);
                cadre.setVisible(true);
            }
        });
        p1.add(btAdversaire);

        // Style des labels et champs de saisie
        age.setBounds(50, 80, 150, 30);
        age.setFont(var3);
        age.setForeground(new Color(70, 70, 70));
        p1.add(age);
        
        ageEntree.setBounds(190, 80, 250, 30);
        styleComboBox(ageEntree);
        p1.add(ageEntree);

        poids.setBounds(50, 113, 150, 30);
        poids.setFont(var3);
        poids.setForeground(new Color(70, 70, 70));
        p1.add(poids);
        
        poidsEntree.setBounds(190, 113, 250, 30);
        styleComboBox(poidsEntree);
        p1.add(poidsEntree);

        TitreNom.setBounds(50, 146, 150, 30);
        TitreNom.setFont(var3);
        TitreNom.setForeground(new Color(70, 70, 70));
        p1.add(TitreNom);
        
        nomEntree.setBounds(190, 146, 250, 30);
        styleTextField(nomEntree);
        p1.add(nomEntree);

        TitrePrenom.setBounds(50, 179, 150, 30);
        TitrePrenom.setFont(var3);
        TitrePrenom.setForeground(new Color(70, 70, 70));
        p1.add(TitrePrenom);
        
        prenomEntree.setBounds(190, 179, 250, 30);
        styleTextField(prenomEntree);
        p1.add(prenomEntree);

        TitreClub.setBounds(50, 212, 150, 30);
        TitreClub.setFont(var3);
        TitreClub.setForeground(new Color(70, 70, 70));
        p1.add(TitreClub);
        
        clubEntree.setBounds(190, 212, 250, 30);
        styleTextField(clubEntree);
        p1.add(clubEntree);

        btAjouter.setBounds(50, 260, 190, 35);
        btAjouter.setFont(var3);
        btAjouter.addActionListener(e -> {
            try {
                String nom = nomEntree.getText();
                String prenom = prenomEntree.getText();
                String club = clubEntree.getText();
                String age = (String) ageEntree.getSelectedItem();
                String poids = (String) poidsEntree.getSelectedItem();
        
                Liste voiture = new Liste(nom, prenom, club ,age, poids);
                Classement classement = new Classement(nom, prenom,club , age, poids);
                controller.ajouterListe(voiture, classement);
        
                model1.setRowCount(0);
                controller.afficherTable(model1);
        
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
            
            nomEntree.setText("");
            prenomEntree.setText("");
            clubEntree.setText("");
        });
        p1.add(btAjouter);

        btModifier.setBounds(250, 260, 190, 35);
        btModifier.setFont(var3);
        btModifier.addActionListener(e -> {
            int selectedRow = table1.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ligne à modifier.");
                return;
            }
            String ancienNom = (String) model1.getValueAt(selectedRow, 1);
            String ancienPrenom = (String) model1.getValueAt(selectedRow, 2);
        
            String nouveauNom = nomEntree.getText();
            String nouveauPrenom = prenomEntree.getText();
            String club = clubEntree.getText();
            String age = (String) ageEntree.getSelectedItem();
            String poids = (String) poidsEntree.getSelectedItem();
        
            try {
                controller.modifierCombattant(ancienNom, ancienPrenom, nouveauNom, nouveauPrenom, club, age, poids);

                model1.setRowCount(0);
                controller.afficherTable(model1);
        
                JOptionPane.showMessageDialog(this, "Modification réussie !");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur lors de la modification : " + ex.getMessage());
            }

            nomEntree.setText("");
            prenomEntree.setText("");
            clubEntree.setText("");
        });
        p1.add(btModifier);

        // Panel inférieur
        p2.setBounds(2, 320, 1398, 430);
        p2.setLayout(null);
        p2.setBackground(Color.WHITE);
        p2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(150, 150, 150)));

        btSupprimer.setBounds(1140, 2, 200, 35);
        btSupprimer.setFont(var3);
        btSupprimer.addActionListener(e -> {
            int selectedRow = table1.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ligne à supprimer.");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer cette ligne ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;
        
            try {
                String nom = (String) model1.getValueAt(selectedRow, 1);
                String prenom = (String) model1.getValueAt(selectedRow, 2);
                controller.supprimerListe(nom, prenom);     
                model1.setRowCount(0);
                controller.afficherTable(model1);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }

            nomEntree.setText("");
            prenomEntree.setText("");
            clubEntree.setText("");
        });
        p2.add(btSupprimer);
        
        // Style du tableau
        table1.setRowHeight(30);
        table1.setSelectionBackground(TABLE_SELECTION_BG);
        table1.setSelectionForeground(Color.BLACK);
        table1.setFont(new Font("Arial", Font.PLAIN, 12));
        table1.setGridColor(new Color(220, 220, 220));
        
        JTableHeader header = table1.getTableHeader();
        header.setBackground(TABLE_HEADER_BG);
        header.setForeground(TABLE_HEADER_FG);
        header.setFont(new Font("Arial", Font.BOLD, 12));
        
        scrollPane1.setBounds(20, 40, 1320, 300);
        scrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)));
        p2.add(scrollPane1);
        
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table1.getSelectedRow();
                    if (selectedRow != -1) {
                        nomEntree.setText(table1.getValueAt(selectedRow, 1).toString());
                        prenomEntree.setText(table1.getValueAt(selectedRow, 2).toString());
                        clubEntree.setText(table1.getValueAt(selectedRow, 3).toString());
                        ageEntree.setSelectedItem(table1.getValueAt(selectedRow, 4).toString());
                        poidsEntree.setSelectedItem(table1.getValueAt(selectedRow, 5).toString());
                    }
                }
            }
        });

        // Configurez spécifiquement la colonne "Modifier"
table1.getColumn("Modifier").setCellRenderer(new ButtonRenderer());
table1.getColumn("Modifier").setCellEditor(new ButtonEditor());
        // Ajoutez cette ligne pour que les boutons prennent toute la hauteur de la cellule
        table1.setRowHeight(30); // Ajustez cette valeur selon vos besoins
        table1.setRowSelectionAllowed(true);
        table1.setColumnSelectionAllowed(false);
        ptout.add(p1);
        ptout.add(p2);
        add(ptout);
        setVisible(true);
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(var3);
        comboBox.setBackground(Color.WHITE);
        comboBox.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }

    private void styleTextField(JTextField textField) {
        textField.setFont(var3);
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }
    class RoundedButton extends JButton {
        private int radius;
        private Color bgColor;

        public RoundedButton(String text, Color bgColor, int radius) {
            super(text);
            this.radius = radius;
            this.bgColor = bgColor;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setForeground(BUTTON_FG_COLOR);
            setFont(var3);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            
            if (getModel().isRollover()) {
                g2.setColor(new Color(255, 255, 255, 50));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            }
            
            // Effet de pression
            if (getModel().isPressed()) {
                g2.setColor(new Color(0, 0, 0, 50));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            }
            
            super.paintComponent(g2);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor.darker());
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
            g2.dispose();
        }
    }

class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);  // Essentiel pour que le fond soit peint
        setContentAreaFilled(true);  // Pour remplir la zone du bouton
        setBorderPainted(false);  // Pour un look plus moderne
        setForeground(Color.WHITE);
        setBackground(new Color(36, 95, 145));  // Couleur de fond bleu foncé
        setFont(var3);
        setText("Modifier");  // Texte fixe pour tous les boutons
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        // Ajustement de la couleur si sélectionné
        if (isSelected) {
            setBackground(new Color(36, 95, 145).darker());
        } else {
            setBackground(new Color(36, 95, 145));
        }
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    
    public ButtonEditor() {
        super(new JCheckBox());
        button = new JButton("Modifier1");
        button.setOpaque(true);
        button.setBackground(new Color(36, 95, 145));
        button.setForeground(Color.WHITE);
        button.setFont(var3);
        button.setBorderPainted(false);
        button.addActionListener(e -> {
            fireEditingStopped();
            // Votre logique de modification ici
            int row = table1.getSelectedRow();
            if (row >= 0) {
                nomEntree.setText(table1.getValueAt(row, 1).toString());
                prenomEntree.setText(table1.getValueAt(row, 2).toString());
                clubEntree.setText(table1.getValueAt(row, 3).toString());
                ageEntree.setSelectedItem(table1.getValueAt(row, 4).toString());
                poidsEntree.setSelectedItem(table1.getValueAt(row, 5).toString());
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        return button;
    }
}
}
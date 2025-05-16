package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.ListeController;
import modele.Liste;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdversaireView extends JFrame {
    // Couleurs thématiques judo
    private final Color MAIN_BG_COLOR = new Color(245, 245, 245);
    private final Color HEADER_BG_COLOR = new Color(200, 30, 30); // Rouge judo
    private final Color PANEL1_BG_COLOR = new Color(240, 240, 240);
    private final Color BUTTON_BG_COLOR = new Color(200, 30, 30);
    private final Color BUTTON_FG_COLOR = Color.WHITE;
    private final Color TABLE_HEADER_BG = new Color(70, 70, 70);
    private final Color TABLE_HEADER_FG = Color.WHITE;
    private final Color TABLE_SELECTION_BG = new Color(200, 30, 30, 100);
    private final Color GREEN_BUTTON = new Color(0, 102, 51); // Vert pour actions principales
    
    public JPanel imageLogo = new JPanel();
    public JPanel imageLogo1 = new JPanel();
    public JPanel ptout, p1, p2;

    public JLabel titre = new JLabel("Fédération Malagasy de Judo");
    public JButton btListe = new JButton("Liste");
    public JLabel age = new JLabel("<html><i>Catégorie (âge)</i> :</html>");
    String[] items = {"Poussin(e)", "Minime", "Cadet(tte)", "Junior", "Senior"};
    JComboBox<String> ageEntree = new JComboBox<>(items);

    public JLabel poids = new JLabel("<html><i>Catégorie (poids)</i> :</html>");
    public JComboBox<String> poidsEntree = new JComboBox<>(new String[]{"-36", "-40", "-44", "-48", "-52", "-60", "63", "-66", "-70", "-73", "-80", "-83", "+100"});

    public JButton btRechercher = new JButton("Rechercher");
    public JLabel typeCombat = new JLabel("<html><i>Type combat</i> :</html>");
    public JComboBox<String> typeCombatEntree = new JComboBox<>(new String[]{"Round1", "Round2", "Quarter-final", "Semi-final", "Final"});

    DefaultTableModel model = new DefaultTableModel(new Object[]{"N°", "Nom", "Prénom", "Club", "Cat Age", "Cat poids"}, 0);
    JTable table = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(table);
    public JButton btAdversaire = new JButton("Adversaire");

    Font var1 = new Font("Arial", Font.BOLD, 23);
    Font var2 = new Font("Arial", Font.BOLD, 16);
    Font var3 = new Font("Arial", Font.BOLD, 14);
    
    public AdversaireView(ListeController controller) {
        setTitle("Adversaire");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1400, 750);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(MAIN_BG_COLOR);

        ptout = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();

        ptout.setBounds(0, 0, 1400, 850);
        ptout.setLayout(null);
        ptout.setBackground(MAIN_BG_COLOR);

        // Panel supérieur
        p1.setBounds(2, 2, 1398, 320);
        p1.setLayout(null);
        p1.setBackground(PANEL1_BG_COLOR);
        p1.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(150, 150, 150)));

        // Logo FMJ
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
        }
        p1.add(imageLogo);

        // Image ceinture
        imageLogo1.setBounds(960, 80, 300, 200);
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/ressources/Ceinture.jpg"));
            Image scaledImage = originalIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            imageLogo1.add(logoLabel);
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Logo");
            errorLabel.setForeground(Color.RED);
            imageLogo1.add(errorLabel);
        }
        p1.add(imageLogo1);

        // Titre
        titre.setBounds(80, 5, 450, 40);
        titre.setFont(new Font("Serif", Font.BOLD, 24));
        titre.setForeground(new Color(70, 70, 70));
        p1.add(titre);

        // Bouton Liste
        btListe = new RoundedButton("Liste", BUTTON_BG_COLOR, 8);
        btListe.setBounds(1010, 20, 200, 35);
        btListe.setForeground(BUTTON_FG_COLOR);
        btListe.setFont(var3);
        btListe.addActionListener(e -> {
            new ListeView(controller).setVisible(true);
        });
        p1.add(btListe);

        // Champs de formulaire
        styleLabelAndField(age, ageEntree, 50, 100);
        styleLabelAndField(poids, poidsEntree, 50, 133);

        // Bouton Rechercher
        btRechercher = new RoundedButton("Rechercher", GREEN_BUTTON, 15);
        btRechercher.setBounds(130, 177, 200, 35);
        btRechercher.setForeground(Color.WHITE);
        btRechercher.setFont(var3);
        btRechercher.addActionListener(e -> {
            try {
                String age = (String) ageEntree.getSelectedItem();
                String poids = (String) poidsEntree.getSelectedItem();
                Liste voiture = new Liste(age, poids);
                model.setRowCount(0);
                controller.rechercherPersonne(voiture, model);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });
        p1.add(btRechercher);

        // Panel inférieur
        p2.setBounds(2, 275, 1398, 430);
        p2.setLayout(null);
        p2.setBackground(Color.WHITE);
        p2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(150, 150, 150)));

        // Type combat et bouton Adversaire en haut du panel inférieur
        typeCombat.setBounds(50, 370, 150, 30);
        typeCombat.setFont(var3);
        typeCombat.setForeground(new Color(70, 70, 70));
        p2.add(typeCombat);

        typeCombatEntree.setBounds(200, 370, 150, 30);
        styleComboBox(typeCombatEntree);
        p2.add(typeCombatEntree);

        // Tableau
        styleTable();
        scrollPane.setBounds(20, 70, 1320, 300);
        p2.add(scrollPane);

        // Bouton Adversaire
        btAdversaire = new RoundedButton("Adversaire", GREEN_BUTTON, 15);
        btAdversaire.setBounds(1130, 370, 200, 35);
        btAdversaire.setForeground(Color.WHITE);
        btAdversaire.setFont(var3);
        btAdversaire.addActionListener(e -> {
            int[] selectedRows = table.getSelectedRows();
            if(selectedRows.length != 2) {
                JOptionPane.showMessageDialog(this, 
                    "Veuillez sélectionner exactement 2 combattants", 
                    "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String club1 = (String) model.getValueAt(selectedRows[0], 3);
            String nom1 = (String) model.getValueAt(selectedRows[0], 1);
            String prenom1 = (String) model.getValueAt(selectedRows[0], 2);
            
            String club2 = (String) model.getValueAt(selectedRows[1], 3);
            String nom2 = (String) model.getValueAt(selectedRows[1], 1);
            String prenom2 = (String) model.getValueAt(selectedRows[1], 2);
            String typeCombat = (String) typeCombatEntree.getSelectedItem();
            String categorieAge = (String) model.getValueAt(selectedRows[0], 4);
            String categoriePoids = (String) model.getValueAt(selectedRows[0], 5);
            
            new JudoScoreBoard(
                club1, nom1 + " " + prenom1,
                club2, nom2 + " " + prenom2,
                typeCombat, categorieAge, categoriePoids
            ).setVisible(true);
        });
        p2.add(btAdversaire);

        ptout.add(p1);
        ptout.add(p2);
        add(ptout);
        setVisible(true);
    }

    private void styleLabelAndField(JLabel label, JComponent field, int x, int y) {
        label.setBounds(x, y, 150, 30);
        label.setFont(var3);
        label.setForeground(new Color(70, 70, 70));
        p1.add(label);
        
        field.setBounds(x + 140, y, 250, 30);
        if (field instanceof JComboBox) {
            styleComboBox((JComboBox<?>) field);
        } else if (field instanceof JTextField) {
            styleTextField((JTextField) field);
        }
        p1.add(field);
    }

    private void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setFont(var3);
        comboBox.setBackground(Color.WHITE);
        comboBox.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(0, 5, 0, 5)
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

    private void styleTable() {
        table.setRowHeight(30);
        table.setSelectionBackground(TABLE_SELECTION_BG);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setGridColor(new Color(220, 220, 220));
        
        JTableHeader header = table.getTableHeader();
        header.setBackground(TABLE_HEADER_BG);
        header.setForeground(TABLE_HEADER_FG);
        header.setFont(new Font("Arial", Font.BOLD, 12));
    }

    // Classe pour les boutons arrondis
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
}
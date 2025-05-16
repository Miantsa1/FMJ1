package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import controller.ListeController;
import modele.Classement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassementView extends JFrame {
    // Couleurs thématiques judo
    private final Color MAIN_BG_COLOR = new Color(245, 245, 245);
    private final Color PANEL1_BG_COLOR = new Color(240, 240, 240);
    private final Color BUTTON_BG_COLOR = new Color(200, 30, 30); // Rouge judo
    private final Color BUTTON_FG_COLOR = Color.WHITE;
    private final Color GREEN_BUTTON = new Color(0, 102, 51); // Vert foncé
    private final Color BLUE_BUTTON = new Color(36, 95, 145); // Bleu foncé
    private final Color TABLE_HEADER_BG = new Color(70, 70, 70);
    private final Color TABLE_HEADER_FG = Color.WHITE;
    private final Color TABLE_SELECTION_BG = new Color(200, 30, 30, 100);

    // Composants existants...
    public JPanel imageLogo = new JPanel();
    public JPanel imageLogo1 = new JPanel();
    public JPanel ptout, p1, p2;
    public ListeController controller;
    public JLabel titre = new JLabel("Fédération Malagasy de Judo");
    public JButton btListe;
    public JLabel age = new JLabel("<html><i>Catégorie (âge)</i> :</html>");
    String[] items = {"Poussin(e)", "Minime", "Cadet(tte)", "Junior", "Senior"};
    JComboBox<String> ageEntree = new JComboBox<>(items);
    public JLabel poids = new JLabel("<html><i>Catégorie (poids)</i> :</html>");
    public JComboBox<String> poidsEntree = new JComboBox<>(new String[]{"-36", "-40", "-44", "-48", "-52", "-60", "63", "-66", "-70", "-73", "-80", "-83", "+100"});
    public JButton btRechercher;
    public JButton btModifierClassement;
    public JButton btModifierPoints;

    DefaultTableModel model = new DefaultTableModel(new Object[]{"N°", "Nom", "Prénom", "Club", "Classement", "Points"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 4 || column == 5;
        }
    };

    JTable table = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(table);

    Font var1 = new Font("Arial", Font.BOLD, 23);
    Font var2 = new Font("Arial", Font.BOLD, 16);
    Font var3 = new Font("Arial", Font.BOLD, 14);

    public ClassementView(ListeController controller) {
        this.controller = controller;
        setTitle("Classement");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1400, 750);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(MAIN_BG_COLOR);

        ptout = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();

        ptout.setBounds(0, 0, 1400, 700);
        ptout.setLayout(null);
        ptout.setBackground(MAIN_BG_COLOR);

        // Bandeau supérieur
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
        btListe.addActionListener(e -> new ListeView(controller).setVisible(true));
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
                Classement classement1 = new Classement(age, poids);
                model.setRowCount(0);
                controller.rechercherClassement1(classement1, model);
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

        // Boutons Modifier
        btModifierClassement = new RoundedButton("Modifier Classement", BLUE_BUTTON, 15);
        btModifierClassement.setBounds(920, 50, 200, 35);
        btModifierClassement.setForeground(Color.WHITE);
        btModifierClassement.setFont(var3);
        btModifierClassement.addActionListener(e -> modifierClassement());
        p2.add(btModifierClassement);

        btModifierPoints = new RoundedButton("Modifier Points", BLUE_BUTTON, 15);
        btModifierPoints.setBounds(1140, 50, 200, 35);
        btModifierPoints.setForeground(Color.WHITE);
        btModifierPoints.setFont(var3);
        btModifierPoints.addActionListener(e -> modifierPoints());
        p2.add(btModifierPoints);

        // Tableau
        styleTable();
        scrollPane.setBounds(20, 88, 1320, 300);
        p2.add(scrollPane);

        ptout.add(p1);
        ptout.add(p2);
        add(ptout);

        chargerDonnees();
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

    // Méthodes existantes (chargerDonnees, modifierClassement, modifierPoints)...
    private void chargerDonnees() {
        if (controller == null) {
            JOptionPane.showMessageDialog(this, "Erreur: Contrôleur non initialisé");
            return;
        }
        model.setRowCount(0);
        Classement c = new Classement();
        controller.rechercherClassement1(c, model);
    }
    
    private void modifierClassement() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un judoka");
            return;
        }
        try {
            String nom = (String) model.getValueAt(selectedRow, 1);
            String prenom = (String) model.getValueAt(selectedRow, 2);
            int nouveauClassement = Integer.parseInt(model.getValueAt(selectedRow, 4).toString());
            controller.modifierClassement(nom, prenom, nouveauClassement);
            JOptionPane.showMessageDialog(this, "Classement mis à jour avec succès");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur: " + e.getMessage());
        }
    }

    private void modifierPoints() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un judoka");
            return;
        }
        try {
            String nom = (String) model.getValueAt(selectedRow, 1);
            String prenom = (String) model.getValueAt(selectedRow, 2);
            int nouveauxPoints = Integer.parseInt(model.getValueAt(selectedRow, 5).toString());
            controller.modifierPoints(nom, prenom, nouveauxPoints);
            JOptionPane.showMessageDialog(this, "Points mis à jour avec succès");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur: " + e.getMessage());
        }
    }
}
package tout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import modele.Classement;
import modele.Liste;

public class ListeTT {
    private Connection conn;
    int nbr1;
    int nbr2;
    int nbr3;
    int nbr4;


    public ListeTT(Connection conn) {
        this.conn = conn;
    }

    public List<Liste> AfficherToutesVoiture() {
        List<Liste> listes = new ArrayList<>();
        try {
            String sql = "SELECT v.* FROM Combattant v ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Liste liste = new Liste(
                    rs.getString("nom_combattant"),
                    rs.getString("prenom_combattant"),
                    rs.getString("nom_club"),
                    rs.getString("categorie_age"),
                    rs.getString("Poids")
                );
    
                listes.add(liste);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listes;
    }
    

    public void insererVoiture(Liste v, Classement c) throws SQLException {
        // 1. Vérifier si le combattant existe déjà
        String checkSql = "SELECT COUNT(*) FROM Combattant WHERE nom_combattant = ? AND prenom_combattant = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
        checkStmt.setString(1, v.getNom_judokas());
        checkStmt.setString(2, v.getPrenom_judokas());
        ResultSet rs = checkStmt.executeQuery();
        
        if (rs.next() && rs.getInt(1) > 0) {
            throw new SQLException("Ce combattant existe déjà dans la base de données");
        }
        String propSQL = "INSERT INTO Combattant(nom_combattant, prenom_combattant, nom_club, categorie_age, Poids) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement psProp = conn.prepareStatement(propSQL, Statement.RETURN_GENERATED_KEYS)) {
            psProp.setString(1, v.getNom_judokas());
            psProp.setString(2, v.getPrenom_judokas());
            psProp.setString(3, v.getNom_club());
            psProp.setString(4, v.getCategorie_age());
            psProp.setString(5, v.getPoids());
            psProp.executeUpdate();
        }
        String propSQL1 = "INSERT INTO Classement(nom_judoka, prenom_judoka, club, categorie_age, Poids) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement psProp1 = conn.prepareStatement(propSQL1, Statement.RETURN_GENERATED_KEYS)) {
            psProp1.setString(1, c.getNom_jud());
            psProp1.setString(2, c.getPrenom_jud());
            psProp1.setString(3, c.getClub());
            psProp1.setString(4, c.getCategorie_age());
            psProp1.setString(5, c.getPoids());
            psProp1.executeUpdate();
        }
    }

    public void Rechercher(Liste v, DefaultTableModel model) throws SQLException {
        try {
            String sql = "SELECT nom_combattant, prenom_combattant, nom_club, categorie_age, Poids FROM Combattant WHERE categorie_age = ? AND Poids = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, v.getCategorie_age());
            ps.setString(2, v.getPoids());
    
            ResultSet rs = ps.executeQuery();
    
            int i = 1;
            while (rs.next()) {
                model.addRow(new Object[]{
                    i++,
                    rs.getString("nom_combattant"),
                    rs.getString("prenom_combattant"),
                    rs.getString("nom_club"),
                    rs.getString("categorie_age"),
                    rs.getString("Poids")
                });
            }
    
            rs.close();
            ps.close();
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void RechercherClassement(Classement c, DefaultTableModel model) throws SQLException {
        try {
            String sql = "SELECT nom_judoka, prenom_judoka, club, rang, point FROM Classement WHERE categorie_age = ? AND Poids = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getCategorie_age());
            ps.setString(2, c.getPoids());
    
            ResultSet rs = ps.executeQuery();
    
            int i = 1;
            while (rs.next()) {
                model.addRow(new Object[]{
                    i++,
                    rs.getString("nom_judoka"),
                    rs.getString("prenom_judoka"),
                    rs.getString("club"),
                    rs.getString("rang"),
                    rs.getString("point")
                });
            }
    
            rs.close();
            ps.close();
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void AfficherAdversaire(Liste v, DefaultTableModel model) throws SQLException {
        try {
            String sql = "SELECT nom_combattant, prenom_combattant, nom_club FROM Combattant WHERE nom_combattant = ? AND prenom_combattant = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, v.getNom_judokas());
            ps.setString(2, v.getPrenom_judokas());
    
            ResultSet rs = ps.executeQuery();
    
            rs.close();
            ps.close();
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void supprimerVoiture(Liste liste) throws SQLException {
        String supVoiture = "DELETE FROM Combattant WHERE nom_combattant = ? AND prenom_combattant = ?";
        PreparedStatement psVoiture = conn.prepareStatement(supVoiture);
        psVoiture.setString(1, liste.getNom_judokas());
        psVoiture.setString(2, liste.getPrenom_judokas());
        psVoiture.executeUpdate();
    }

    public void ModifierVoiture1(Liste liste, Classement c) throws SQLException{
        String modifVoiture= "UPDATE Combattant set nom_combattant = ?, prenom_combattant=?, nom_club=?, categorie_age =?, Poids=?"+ 
        " WHERE nom_combattant = ? AND prenom_combattant = ?";
        try(PreparedStatement psProp = conn.prepareStatement(modifVoiture, Statement.RETURN_GENERATED_KEYS))
        {
            psProp.setString(1, liste.getNom_judokas());
            psProp.setString(2, liste.getPrenom_judokas());
            psProp.setString(3, liste.getNom_club());
            psProp.setString(4, liste.getCategorie_age());
            psProp.setString(5, liste.getPoids());
            psProp.setString(6, liste.getNom_judokas());
            psProp.setString(7, liste.getPrenom_judokas());
            psProp.executeUpdate();
        } 

        String modifClassement= "UPDATE Classement set nom_judoka = ?, prenom_judoka=?, club=?, categorie_age =?, Poids=?"+ 
        " WHERE nom_judoka = ? AND prenom_judoka = ?";

        try(PreparedStatement psProp1 = conn.prepareStatement(modifClassement, Statement.RETURN_GENERATED_KEYS)){
            psProp1.setString(1, c.getNom_jud());
            psProp1.setString(2, c.getPrenom_jud());
            psProp1.setString(3, c.getClub());
            psProp1.setString(4, c.getCategorie_age());
            psProp1.setString(5, c.getPoids());
            psProp1.setString(6, c.getNom_jud());
            psProp1.setString(7, c.getPrenom_jud());
            psProp1.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        
    }
    public void AfficherTab2(DefaultTableModel model) throws SQLException {
        try {
            String sql = "SELECT nom_combattant, prenom_combattant, nom_club, categorie_age, Poids FROM Combattant";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
    
            int i = 1;
            while (rs.next()) {
                JButton modifierBtn = new JButton("Modifier");
                modifierBtn.setPreferredSize(new Dimension(80, 25));
                modifierBtn.setBackground(new Color(36, 95, 145));
                modifierBtn.setForeground(Color.WHITE);
                modifierBtn.setFont(new Font("Arial", Font.PLAIN, 12));
    
                model.addRow(new Object[]{
                    i++,
                    rs.getString("nom_combattant"),
                    rs.getString("prenom_combattant"),
                    rs.getString("nom_club"),
                    rs.getString("categorie_age"),
                    rs.getString("Poids"),
                    modifierBtn
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

public void supprimerParNomPrenom(String nom, String prenom) throws SQLException {
    String sql = "DELETE FROM Combattant WHERE nom_combattant = ? AND prenom_combattant = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, nom);
        pstmt.setString(2, prenom);
        pstmt.executeUpdate();
    }
    String sql1 = "DELETE FROM Classement WHERE nom_judoka = ? AND prenom_judoka = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql1)) {
        pstmt.setString(1, nom);
        pstmt.setString(2, prenom);
        pstmt.executeUpdate();
    }
    catch (Exception e) {
        e.printStackTrace();
    }
}
public void modifierCombattant(String ancienNom, String ancienPrenom, String nouveauNom, String nouveauPrenom, String club, String age, String poids) throws SQLException {
    String sql = "UPDATE Combattant SET nom_combattant = ?, prenom_combattant = ?, nom_club = ?, categorie_age = ?, Poids = ? WHERE nom_combattant = ? AND prenom_combattant = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, nouveauNom);
        pstmt.setString(2, nouveauPrenom);
        pstmt.setString(3, club);
        pstmt.setString(4, age);
        pstmt.setString(5, poids);
        pstmt.setString(6, ancienNom);
        pstmt.setString(7, ancienPrenom);
        pstmt.executeUpdate();
    }
    String sql1 = "UPDATE Classement set nom_judoka = ?, prenom_judoka=?, club=?, categorie_age =?, Poids=? WHERE nom_judoka = ? AND prenom_judoka = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql1)) {
        pstmt.setString(1, nouveauNom);
        pstmt.setString(2, nouveauPrenom);
        pstmt.setString(3, club);
        pstmt.setString(4, age);
        pstmt.setString(5, poids);
        pstmt.setString(6, ancienNom);
        pstmt.setString(7, ancienPrenom);
        pstmt.executeUpdate();
    }
}


public void modifierClassement(String nom, String prenom, int nouveauClassement) throws SQLException {
    String sql = "UPDATE Classement SET rang = ? WHERE nom_judoka = ? AND prenom_judoka = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, nouveauClassement);
        ps.setString(2, nom);
        ps.setString(3, prenom);
        ps.executeUpdate();
    }
}

public void modifierPoints(String nom, String prenom, int nouveauxPoints) throws SQLException {
    String sql = "UPDATE Classement SET point = ? WHERE nom_judoka = ? AND prenom_judoka = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, nouveauxPoints);
        ps.setString(2, nom);
        ps.setString(3, prenom);
        ps.executeUpdate();
    }
}

public void modifierColonne(String nom, String prenom, String colonne, String nouvelleValeur) throws SQLException {
    // Vérification sécurité des noms de colonnes
    String[] colonnesValides = {"nom", "prenom", "club", "categorie_age", "poids"};
    if (!Arrays.asList(colonnesValides).contains(colonne.toLowerCase())) {
        throw new IllegalArgumentException("Colonne non modifiable");
    }

    String sql = "UPDATE Combattant SET " + colonne + " = ? WHERE nom_combattant = ? AND prenom_combattant = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, nouvelleValeur);
        ps.setString(2, nom);
        ps.setString(3, prenom);
        ps.executeUpdate();
    }
}

    

    
}

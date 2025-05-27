package controller;

import modele.Classement;
import modele.Liste;
import tout.ListeTT;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ListeController {
    public ListeTT listeTT;

    public ListeController(ListeTT voiture) {
        this.listeTT  = voiture;
    }

    public void AdversaireAfficher(Liste v, DefaultTableModel model){
        try{
            listeTT .AfficherAdversaire(v,model);
        }catch(Exception e){
            e.printStackTrace();
        }
    }



    public void ajouterListe(Liste v, Classement c) {
        try {
            listeTT.insererVoiture(v, c);
            JOptionPane.showMessageDialog(null, "Combattant ajouté avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
            // Rafraîchir le tableau si nécessaire
        } catch (SQLException e) {
            if (e.getMessage().contains("existe déjà")) {
                JOptionPane.showMessageDialog(null, 
                    "Erreur: Ce combattant existe déjà dans la base.\nNom: " + v.getNom_judokas() + "\nPrénom: " + v.getPrenom_judokas(), 
                    "Doublon détecté", 
                    JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Erreur technique: " + e.getMessage(), 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void supprimerVoiture(Liste v) {
        try {
            listeTT .supprimerVoiture(v);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void modifierVoiture(Liste v, Classement c){
        try{
            listeTT .ModifierVoiture1(v,c);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Liste> getVoitures() {
        return listeTT .AfficherToutesVoiture();
    }

    public void afficherTable(DefaultTableModel model) {
        try {
            listeTT.AfficherTab2(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void rechercherPersonne(Liste v, DefaultTableModel model){
        try {
            listeTT.Rechercher(v,model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rechercherClassement1(Classement c, DefaultTableModel model){
        try {
            listeTT.RechercherClassement(c,model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void supprimerListe(String nom, String prenom) {
        try {
            listeTT.supprimerParNomPrenom(nom, prenom);
        } catch (Exception e) {
            e.printStackTrace();
        }
}

public void modifierCombattant(String ancienNom, String ancienPrenom, String nouveauNom, String nouveauPrenom, String club, String age, String poids) {
    try {
        listeTT.modifierCombattant(ancienNom, ancienPrenom, nouveauNom, nouveauPrenom, club, age, poids);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void modifierClassement(String nom, String prenom, int nouveauClassement) {
    try {
        listeTT.modifierClassement(nom, prenom, nouveauClassement);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void modifierPoints(String nom, String prenom, int nouveauxPoints) {
    try {
        listeTT.modifierPoints(nom, prenom, nouveauxPoints);
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public void modifierColonne(String nom, String prenom, String colonne, String nouvelleValeur) {
    try {
        listeTT.modifierColonne(nom, prenom, colonne, nouvelleValeur);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erreur de modification: " + e.getMessage());
    }
}



    
}

package modele;


public class Liste {
    public String nom_judokas, prenom_judokas, nom_club,categorie_age,poids;

    public Liste(){

    };
    public Liste(String nom_judokas, String prenom_judokas,String nom_club,String categorie_age,String poids){
        this.nom_judokas=nom_judokas;
        this.prenom_judokas=prenom_judokas;
        this.nom_club=nom_club;
        this.categorie_age=categorie_age;
        this.poids=poids;
    }
    public Liste(String categorie_age,String poids){
        this.categorie_age=categorie_age;
        this.poids=poids;
    }
    public String getNom_judokas() {
        return nom_judokas;
    }
    public void setNom_judokas(String nom_judokas) {
        this.nom_judokas = nom_judokas;
    }

    public String getPrenom_judokas() {
        return prenom_judokas;
    }
    public void setPrenom_judokas(String prenom_judokas) {
        this.prenom_judokas = prenom_judokas;
    }

    public String getNom_club() {
        return nom_club;
    }
    public void setNom_club(String nom_club) {
        this.nom_club = nom_club;
    }

    public String getCategorie_age() {
        return categorie_age;
    }
    public void setCategorie_age(String categorie_age) {
        this.categorie_age = categorie_age;
    }

    public String getPoids() {
        return poids;
    }
    public void setPoids(String poids) {
        this.poids = poids;
    }

    
}

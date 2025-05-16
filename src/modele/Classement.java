package modele;

public class Classement {
    public String nom_jud, prenom_jud, club , categorie_age,poids;
    public int classement1, points1;

    public Classement(){}
    public Classement(String nom_jud, String prenom_jud, String club){
        this.nom_jud=nom_jud;
        this.prenom_jud=prenom_jud;
        this.club=club;
    }

    public Classement(String nom_jud, String prenom_jud, String club, int classement1, int points1){
        this.nom_jud=nom_jud;
        this.prenom_jud=prenom_jud;
        this.club=club;
        this.classement1=classement1;
        this.points1=points1;
    }

    public Classement(String nom_jud, String prenom_jud, String club, String categorie_age, String poids, int classement1, int points1){
        this.nom_jud=nom_jud;
        this.prenom_jud=prenom_jud;
        this.club=club;
        this.categorie_age=categorie_age;
        this.poids=poids;
        this.classement1=classement1;
        this.points1=points1;
    }
    public Classement(String nom_jud, String prenom_jud, String club, String categorie_age, String poids){
        this.nom_jud=nom_jud;
        this.prenom_jud=prenom_jud;
        this.club=club;
        this.categorie_age=categorie_age;
        this.poids=poids;
    }

    public Classement(String categorie_age, String poids){
        this.categorie_age=categorie_age;
        this.poids=poids;
    }

    public String getNom_jud() {
        return nom_jud;
    }
    public void setNom_jud(String nom_jud) {
        this.nom_jud = nom_jud;
    }

    public String getPrenom_jud() {
        return prenom_jud;
    }
    public void setPrenom_jud(String prenom_jud) {
        this.prenom_jud = prenom_jud;
    }

    public String getClub() {
        return club;
    }
    public void setClub(String club) {
        this.club = club;
    }

    public int getClassement1() {
        return classement1;
    }
    public void setClassement1(int classement1) {
        this.classement1 = classement1;
    }

    public int getPoints1() {
        return points1;
    }
    public void setPoints1(int points1) {
        this.points1 = points1;
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

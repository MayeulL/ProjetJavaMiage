package model;

public class Personnage {

    private String Nom;
    private int PointsDeVie;
    private int Brule;

    public boolean isVivant() {
        return Vivant;
    }

    public void setVivant(boolean vivant) {
        Vivant = vivant;
    }

    private boolean Vivant = true;
    private boolean Hyponotise;

    public Personnage(String nom, int pdv){
        Nom = nom;
        PointsDeVie = pdv;
    }

    public String getNom() {
        return Nom;
    }

    public void PerdrePdv(int dommages){
        int pdv = this.getPointsDeVie()-dommages;
        setPointsDeVie(pdv);

        if (pdv <= 0)
            this.Mourir();
    }

    public void GagnerPdv(int soins){
        int pdv = this.getPointsDeVie()+soins;
        setPointsDeVie(pdv);
    }

    private void Mourir() {
        this.Vivant = false;
    }

    public void Bruler(){
        // si le compteur Brule > 0
        if (Brule > 0){
            Brule--;
            // inflige de légers dégats
            this.PerdrePdv(1);
        }
    }

    public int getPointsDeVie() {
        return PointsDeVie;
    }

    public int getBrule() {
        return Brule;
    }

    public void setBrule(int brule) {
        Brule = brule;
    }

    public void setPointsDeVie(int pointsDeVie) {
        PointsDeVie = pointsDeVie;
    }

    public boolean isHyponotise() {
        return Hyponotise;
    }

    public void setHyponotise(boolean hyponotise) {
        Hyponotise = hyponotise;
    }
}

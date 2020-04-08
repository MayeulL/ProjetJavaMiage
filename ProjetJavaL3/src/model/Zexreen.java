package model;

public class Zexreen  extends Personnage {

    public Zexreen(String nom, int pdv, int posX, int posy) {
        super(nom, pdv, posX, posy);
    }

    public void YeuxLaser(){
        // inflige des dégats
        this.PerdrePdv(2);
    }

    public void Hypnose(){
        // rend Bob confus, la porchaine attaque est redirigée vers Bob
        this.setHyponotise(true);
    }
}

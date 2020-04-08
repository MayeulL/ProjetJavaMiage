package model;
import model.Bob;

public class Zexreen  extends Personnage {

    private String[] Text = new String[3];

    public Zexreen(String nom, int pdv) {
        super(nom, pdv);
    }

    public void YeuxLaser(Bob bob){
        // inflige des dégats
        bob.PerdrePdv(2);
    }

    public void Hypnose(Bob bob){
        // rend Bob confus, la porchaine attaque est redirigée vers Bob
        bob.setHyponotise(true);
    }

}

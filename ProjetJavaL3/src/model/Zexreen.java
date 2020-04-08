package model;
import model.Bob;

public class Zexreen  extends Personnage {

    private String[] Text = new String[3];

    public Zexreen(String nom, int pdv) {
        super(nom, pdv);
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

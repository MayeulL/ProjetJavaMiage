package model;

public class Chmdak extends Personnage {

    private boolean Enrage = false;

    public Chmdak(String nom, int pdv, int posX, int posy) {
        super(nom, pdv, posX, posy);
    }

    public boolean isEnrage() {
        return Enrage;
    }

    public void setEnrage(boolean enrage) {
        Enrage = enrage;
    }

    public void Fouet(Bob bob){
        //inflige des dégats
        bob.PerdrePdv(2);
    }

    public void Charge(Bob bob){
        // inflige des dégats, assomme Bob pour un tour mais s'inflige des dégâts à lui meme
        bob.PerdrePdv(3);
        bob.setAssome(true);
        PerdrePdv(1);

    }

    public void Meditation(){
        // Seulement en état enragé
        if (isEnrage()){
            // Lui rend des points de vie
            GagnerPdv(3);
        }

    }

}

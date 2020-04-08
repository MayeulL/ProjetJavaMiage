package model;

import java.util.List;

public class Bob extends Personnage {

    private int Munitons;
    private List<Objets> Inventaire;
    private boolean Assome;

    public Bob(String nom, int pdv, int posX, int posy, int mun) {
        super(nom, pdv, posX, posy);
        Munitons = mun;
    }

    // TO DO
    public void Inventaire() {
        // ouvre l'inventaire
    }

    public void TirBlaster(Personnage Ennemi) {
        // s'il n'est pas assomé
        if (!isAssome()) {
            // s'il lui reste des munitions
            if (getMunitons() > 0) {
                setMunitons(getMunitons() - 1);
                // Inflige petits dégats et brule pendant 2 tours
                Ennemi.PerdrePdv(2);
                Ennemi.setBrule(2);
            }
        } else if(isHyponotise()){
            PerdrePdv(1);
            setHyponotise(false);
            // Info bulle "il se blesse dans sa confusion"
        } else {
            setAssome(false);
            // info bulle Bob est asomé
        }

    }

    public void CoupDePoing(Personnage Ennemi) {
        // s'il n'est pas assomé
        if (!isAssome()) {
            // Inflige de bons dégats
            Ennemi.PerdrePdv(2);
        }
        else if(isHyponotise()){
            PerdrePdv(1);
            setHyponotise(false);
            // Info bulle "il se blesse dans sa confusion"
        }
        else {
            setAssome(false);
            // info bulle Bob est asomé
        }
    }

    public void Soins() {
        // s'il n'est pas assomé
        if (!isAssome()) {
            // soigne Bob
            GagnerPdv(3);
        }
        else if(isHyponotise()){
            PerdrePdv(1);
            // Info bulle "il se blesse dans sa confusion"
        }
        else {
        setAssome(false);
        // info bulle Bob est asomé
    }
    }

    public boolean HasObject(Objets obj) {
        if (Inventaire.contains(obj))
            return true;
        else
            return false;
    }

    public int getMunitons() {
        return Munitons;
    }

    public void setMunitons(int munitons) {
        Munitons = munitons;
    }

    public boolean isAssome() {
        return Assome;
    }

    public void setAssome(boolean assome) {
        Assome = assome;
    }
}
